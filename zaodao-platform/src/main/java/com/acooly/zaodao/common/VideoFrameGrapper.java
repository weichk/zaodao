package com.acooly.zaodao.common;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import org.springframework.beans.factory.annotation.Value;
import org.springside.modules.utils.io.IOUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 截取视频帧图
 *
 * @author xiaohong
 * @create 2017-11-20 9:51
 **/
@Slf4j
public class VideoFrameGrapper {
    /**
     * 文件最大50MB
     */
    public static final long MAX_FILE_SIZE = 50 * 10485760L;
    /**
     * 0度
     */
    public static final int ROTATE_0 = 0;
    /**
     * 90度
     */
    public static final int ROTATE_90 = 90;
    /**
     * 180度
     */
    public static final int ROTATE_180 = 180;
    /**
     * 270度
     */
    public static final int ROTATE_270 = 270;
    /**
     * 目标文件路径
     */
    private String targetFilePath;
    /**
     * 保存文件路径
     */
    private String saveFilePath;
    /**
     * 图像扩展名
     */
    private String imageExtensions;
    /**
     * 视频扩展名
     */
    private String videoExtensions;

    /**
     * 构造函数
     * @param targetFilePath
     * @param saveFilePath
     */
    public VideoFrameGrapper(String targetFilePath, String saveFilePath, String imageExtensions, String
            videoExtensions){
        this.targetFilePath = targetFilePath;
        this.saveFilePath = saveFilePath;
        this.imageExtensions = imageExtensions;
        this.videoExtensions = videoExtensions;
    }
    /**
     * 抓取视频帧图
     * @throws Exception
     */
    public File grabVedioImage() throws Exception {
        log.info(String.format("视频文件路径: %s", this.targetFilePath));
        log.info(String.format("图片文件路径: %s", this.saveFilePath));
        File targetFile = new File(this.targetFilePath);
        File saveFile = new File(this.saveFilePath);

        checkTargetFile(targetFile);
        checkSaveFile(saveFile);
        log.info("-------开始截帧-------");
        Long start = System.currentTimeMillis();
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(targetFile);
        grabber.start();
        FrameInfo frameInfo = getFrameInfo(grabber);
        log.info(frameInfo.toString());
        //获取帧
        Frame frame = getFrame(grabber, frameInfo.getFrameLength(), frameInfo.getRate());
        //帧转角
        frame = getRotateFrame(frame, frameInfo.getRotate());
        log.info("-------结束截帧-------");
        log.info(String.format("保存图片[%s]中...", saveFile.getName()));
        saveFile(frame, saveFile);
        log.info(String.format("保存图片[%s]完毕", saveFile.getName()));
        log.info(String.format("耗时: %s", System.currentTimeMillis() - start));
        grabber.close();

        return saveFile;
    }
    /**
     * 检查目标文件
     */
    private boolean checkTargetFile(File file) throws Exception {
        if(file == null){
            throw new Exception("目标文件不能为空");
        } else if(!file.exists()){
            throw new Exception("目标文件不存在");
        } else if(videoExtensions.indexOf(getFileExtension(file.getName()))< 0){
            throw new Exception("系统不支持该后缀名的文件");
        } else if(file.length() > MAX_FILE_SIZE){
            throw new Exception("目标文件超过设定最大值");
        }
        return true;
    }
    /**
     * 检查保存文件
     * @throws Exception
     */
    private boolean checkSaveFile(File file) throws Exception {
        if(imageExtensions.indexOf( getFileExtension(file.getName())) < 0){
            throw new Exception("系统不支持该后缀名的文件");
        }
        return true;
    }
    /**
     * 获取文件后缀名
     * @param fileName
     * @return
     */
    private String getFileExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 取帧
     * @param grabber
     * @return
     * @throws Exception
     */
    private FrameInfo getFrameInfo(FFmpegFrameGrabber grabber) throws Exception {
        FrameInfo frameInfo = new FrameInfo();
        //获取旋转角度,帧率,帧数
        String rotate = grabber.getVideoMetadata("rotate");
        int rate = (int) grabber.getFrameRate();
        int frameLength = grabber.getLengthInFrames();
        //设置信息
        frameInfo.setRotate(Strings.isNullOrEmpty(rotate) ? 0 : Integer.parseInt(rotate));
        frameInfo.setRate(rate);
        frameInfo.setFrameLength(frameLength);

        return frameInfo;
    }

    /**
     * 抓取帧
     * @param grabber
     * @param frameLength
     * @param rate
     * @return
     * @throws Exception
     */
    private Frame getFrame(FFmpegFrameGrabber grabber, int frameLength, int rate) throws Exception {
        //视频总帧数小于1秒帧数
        if (frameLength <= rate) {
            throw new Exception("视频长度应该大于1s");
        }
        Frame frame = null;
        int index = 0;
        while (index < frameLength) {
            frame = grabber.grabImage();
            if (frame == null || frame.image == null) {
                continue;
            } else if (++index == rate) {
                log.info(String.format("当前是第%s帧", index));
                break;
            }
        }
        return frame;
    }
    /**
     * 直接保存图片
     * @param frame 帧
     * @throws IOException
     */
    public void saveFile(Frame frame, File file) throws Exception {
        if(!file.getParentFile().exists()) {
            log.info(String.format("创建[%s]父级目录", file.getParentFile().getPath()));
            file.getParentFile().mkdirs();
        }

        BufferedImage bufferedImage = (new Java2DFrameConverter()).getBufferedImage(frame);
        ImageIO.write(bufferedImage, getFileExtension(file.getName()), file);
    }
    /**
     * 输入文件流保存图片
     * @param inputStream 输入文件流
     * @param file 保存文件
     * @throws FileNotFoundException
     */
    public void saveFile(InputStream inputStream, File file) throws Exception {
        if(!file.getParentFile().exists()) {
            log.info(String.format("创建[%s]父级目录", file.getParentFile().getPath()));
            file.getParentFile().mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        IOUtil.copy(inputStream, fileOutputStream);
        IOUtil.closeQuietly(fileOutputStream);
        IOUtil.closeQuietly(inputStream);
    }

    /**
     * 按(宽)缩放方式保存图片
     * @param frame 帧
     * @param file 保存文件
     * @param targetWidth 目标尺寸宽
     * @param iplImage 原帧图
     * @throws IOException
     */
    public void saveFile(Frame frame, File file, int targetWidth, opencv_core.IplImage iplImage ) throws
            Exception {
        if(!file.getParentFile().exists()) {
            log.info(String.format("创建[%s]父级目录", file.getParentFile().getPath()));
            file.getParentFile().mkdirs();
        }

        BufferedImage bufferedImage = (new Java2DFrameConverter()).getBufferedImage(frame);
        int width = targetWidth;
        int height = (int) (((double) targetWidth / iplImage.width()) * iplImage.height());
        BufferedImage bufferedImageDraw = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        bufferedImageDraw.getGraphics().drawImage(bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,
                0, null);
        ImageIO.write(bufferedImage, getFileExtension(file.getName()), file);
    }
    /**
     * 获取旋转后的帧
     * @param frame
     * @param rotate
     * @return
     */
    private Frame getRotateFrame(Frame frame, Integer rotate) {
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        opencv_core.IplImage iplImage = converter.convert(frame);
//        if (rotate != null && rotate.intValue() != ROTATE_0 && rotate.intValue() != ROTATE_180) {
//            frame = converter.convert(rotate(iplImage, rotate));
//        }
        if(rotate != null){
            switch (rotate.intValue()){
                case ROTATE_0:
                    //frame = converter.convert(rotate(iplImage, rotate));
                    log.info(String.format("转角为%s度, 不转角度", rotate));
                    break;
                case ROTATE_90:
                    log.info(String.format("转角为%s度, 旋转%s度", rotate, rotate + 90));
                    frame = converter.convert(rotate(iplImage, rotate + 90));
                    break;
                case ROTATE_180:
//                    log.info(String.format("转角为%s度, 旋转%s度", rotate, ROTATE_90));
//                    frame = converter.convert(rotate(iplImage, ROTATE_90));
                    log.info(String.format("转角为%s度, 不转角度", rotate));
                    break;
                case ROTATE_270:
//                    log.info(String.format("转角为%s度, 旋转%s度", rotate, ROTATE_180));
//                    frame = converter.convert(rotate(iplImage, ROTATE_180));
                    log.info(String.format("转角为%s度, 不转角度", rotate));
                    break;
                default:
                    break;
            }
        }
        return frame;
    }
    /**
     * 旋转帧图片
     * @param iplImage 帧图
     * @param angle 角度
     * @return
     */
    private opencv_core.IplImage rotate(opencv_core.IplImage iplImage, int angle){
        opencv_core.IplImage tempIplImage = opencv_core.IplImage.create(iplImage.height(),iplImage.width(), iplImage.depth(), iplImage
                .nChannels());
        opencv_core.cvTranspose(iplImage, tempIplImage);
        log.info(String.format("当前图片旋转角度: %s", angle));
        opencv_core.cvFlip(tempIplImage, tempIplImage, angle);

        return  tempIplImage;
    }
    /**
     * 帧转换为输入文件流
     * @param frame
     * @return
     */
    public InputStream convertStream(Frame frame, File file) throws IOException {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, getFileExtension(file.getName()), outputStream);

        return new ByteArrayInputStream(outputStream.toByteArray());
    }
    @Getter
    @Setter
    class FrameInfo{
        /**
         * 转角
         */
        int rotate;
        /**
         * 帧率
         */
        int rate;
        /**
         * 帧数
         */
        int frameLength;

        @Override
        public String toString(){
            return String.format("旋转信息: %s, 每秒帧数: %s, 帧数信息: %s", rotate, rate, frameLength);
        }
    }
}
