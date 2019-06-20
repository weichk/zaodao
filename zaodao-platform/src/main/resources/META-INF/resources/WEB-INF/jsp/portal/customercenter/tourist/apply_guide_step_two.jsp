<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <link rel="stylesheet" href="/css/cropper.min.css">
    <link rel="stylesheet" href="/css/sitelogo.css">
    <link rel="stylesheet" href="/css/font-awesome.min.css">
</head>

<body class="grey_bg">
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=customerCenter"/>

<!-- header end -->
<div class="find_pwd_content apply_guide_content pie">
    <h2 class="find_pwd_t"><a href="/portal/services/customer/customeIndex.html">会员中心</a> > 申请成为导游</h2>

    <ul class="find_pwd_nav apply_guide_nav step2">
        <li class="n1">
            <span class="n pie">1</span>
            <p>基本信息</p>
        </li>
        <li class="n2">
            <span class="n pie">2</span>
            <p>影像资料(选填)</p>
        </li>
        <li class="n3 disabled">
            <span class="n pie">3</span>
            <p>完成</p>
        </li>
    </ul>

    <div class="apply_guide_wrap">
        <form action="/portal/services/customer/applyGuideStrpThree.html" name="apply_guide_media"
              class="apply_guide_media">
            <div class="form_title">上传封面照</div>

            <div class="upload_header_img">
                <div class="l">
                    <div class="preview pie"><img src="" class="pie"></div>
                    <div class="upload_control">
                        <span for="header_file" data-toggle="modal" data-target="#avatar-modal"
                              class="upload_header_btn uni_btn pie">上传</span>
                    </div>
                </div>
                <div class="header_info">注：封面海报用于导游展示，非个人头像</div>
            </div>
            <!-- 上传封面照 end -->

            <div class="form_title">上传风采照</div>
            <div class="tools mt20">
                <label class="mphoto_upload uni_btn pie"><i class="sprite icon_upload_img_w"
                                                            id="filePicker"></i>上传照片</label>
                <span class="tips">最多上传15张</span>
            </div>
            <ul class="show_media_list" id="picture_list_id">

            </ul>
            <!-- 上传风采照 end -->
            <div class="form_title">上传视频</div>
            <div class="tools mt20">
                <a href="javascript:void(0);" for="mvideo_file" class="mvideo_upload uni_btn pie"><i
                        class="sprite icon_upload_video_w"></i>上传视频</a><span class="tips">最多上传10个视频，每个视频大小不超过20M</span>
            </div>
            <ul class="show_video_list" id="video_list_id">


            </ul>
            <!-- 上传end -->


            <input type="button" name="next_stop_2" onclick="verifyStepTwoInfo()" value="下一步" class="find_pwd_btn">
        </form>
    </div>
</div>

<script id="pictureList-template" type="text/html">
    {{#each rows}}
    <li><img src="{{cusPicture}}" alt=""><a href="javascript:void(0);" onclick="deletePicture('{{id}}')"
                                            class="del_item"></a></li>
    {{/each}}
</script>

<script id="videoList-template" type="text/html">
    {{#each rows}}
    <li>
        <a class="con" id="video_play_modal_id" onclick="videoPlay('{{cover}}','{{cusVideo}}')">
            <div class="img">
                <img src="{{cover}}" alt=""><i class="sprite icon_play"></i>
            </div>
            <div class="name">{{videoName}}</div>
        </a>
        <div class="tools"><a href="javascript:void(0);" class="vdelete" onclick="deleteVideo('{{id}}')">删除</a></div>
    </li>
    {{/each}}
</script>
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>

<!-- 图片上传裁剪 -->
<div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog"
     tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <!--<form class="avatar-form" action="upload-logo.php" enctype="multipart/form-data" method="post">-->
            <form class="avatar-form">
                <div class="modal-header">
                    <button class="close" data-dismiss="modal" type="button">&times;</button>
                    <h4 class="modal-title" id="avatar-modal-label">上传图片</h4>
                </div>
                <div class="modal-body">
                    <div class="avatar-body">
                        <div class="avatar-upload">
                            <input class="avatar-src" name="avatar_src" type="hidden">
                            <input class="avatar-data" name="avatar_data" type="hidden">
                            <label for="avatarInput" style="line-height: 35px;">图片上传</label>
                            <button class="btn btn-danger" type="button" style="height: 35px;"
                                    onclick="$('input[id=avatarInput]').click();">请选择图片
                            </button>
                            <span id="avatar-name"></span>
                            <input class="avatar-input hide" id="avatarInput" name="avatar_file" type="file"></div>
                        <div class="row">
                            <div class="col-md-9">
                                <div class="avatar-wrapper"></div>
                            </div>
                            <div class="col-md-3">
                                <div class="avatar-preview preview-lg" id="imageHead"></div>
                                <!--<div class="avatar-preview preview-md"></div>
                        <div class="avatar-preview preview-sm"></div>-->
                            </div>
                        </div>
                        <div class="row avatar-btns">
                            <div class="col-md-4">
                                <div class="btn-group">
                                    <button class="btn btn-danger fa fa-undo" data-method="rotate" data-option="-90"
                                            type="button" title="Rotate -90 degrees"> 向左旋转
                                    </button>
                                </div>
                                <div class="btn-group">
                                    <button class="btn  btn-danger fa fa-repeat" data-method="rotate" data-option="90"
                                            type="button" title="Rotate 90 degrees"> 向右旋转
                                    </button>
                                </div>
                            </div>
                            <div class="col-md-5" style="text-align: right;">
                                <button class="btn btn-danger fa fa-arrows" data-method="setDragMode" data-option="move"
                                        type="button" title="移动">
                                    <span class="docs-tooltip" data-toggle="tooltip" title=""
                                          data-original-title="$().cropper(&quot;setDragMode&quot;, &quot;move&quot;)">
                                    </span>
                                </button>
                                <button type="button" class="btn btn-danger fa fa-search-plus" data-method="zoom"
                                        data-option="0.1" title="放大图片">
                                    <span class="docs-tooltip" data-toggle="tooltip" title=""
                                          data-original-title="$().cropper(&quot;zoom&quot;, 0.1)">
                                      <!--<span class="fa fa-search-plus"></span>-->
                                    </span>
                                </button>
                                <button type="button" class="btn btn-danger fa fa-search-minus" data-method="zoom"
                                        data-option="-0.1" title="缩小图片">
                                    <span class="docs-tooltip" data-toggle="tooltip" title=""
                                          data-original-title="$().cropper(&quot;zoom&quot;, -0.1)">
                                      <!--<span class="fa fa-search-minus"></span>-->
                                    </span>
                                </button>
                                <button type="button" class="btn btn-danger fa fa-refresh" data-method="reset"
                                        title="重置图片">
                                        <span class="docs-tooltip" data-toggle="tooltip" title=""
                                              data-original-title="$().cropper(&quot;reset&quot;)"
                                              aria-describedby="tooltip866214">
                                        </span>
                                </button>
                            </div>
                            <div class="col-md-3">
                                <button class="btn btn-danger btn-block avatar-save fa fa-save" type="button"
                                        data-dismiss="modal"> 保存修改
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 回到顶部 -->
<div id="gotop" class="sprite icon_top"></div>
<!-- 图片上传裁剪插件 -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/cropper.js"></script>
<script src="/js/sitelogo.js"></script>
<script src="/js/html2canvas.min.js"></script>
<script type="text/javascript" src="/plugins/webuploader/webuploader.js" charset="utf-8"></script>
<script type="text/javascript" src="/js/platformjs/apply_guide_step_two.js" charset="utf-8"></script>
<script>
    $(function () {
        $(".close_popup").on("click", function () {
            $(this).parents(".popup_service_rule").removeClass("show");
        })
        $('#avatar-modal').on('hidden.bs.modal', function () {
            $("body").removeClass("modal_open");
        })

        // 兼容IE 不支持 :nth-child(n)的情况
        $('.show_media_list li:nth-child(5n),.show_video_list li:nth-child(4n)').addClass('last');
    });

</script>
<script type="text/javascript" src="/plugins/ckplayer6.8/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript">
    // 点击视频，弹出视频播放弹窗
    $('.mvideo_upload').click(function (e) {
        layer.open({
            type: 1,
            title: false,
            area: ['402px', '366px'],
            closeBtn: 0,
            shadeClose: true,
            skin: 'upload_mvideo',
            content: '<div class="content_wrap">' +
            '<a href="javascript:void(0)" class="close_popup_video"></a>' +
            '<form action="/portal/services/customer/saveVedio.html" method="post" name="form_upload_mvideo" class="form_upload_mvideo">' +
            '<div class="form_item">' +
            '<span class="label">视频名称：</span>' +
            '<input type="text" id="video_name_id" datatype="*1-10" nullmsg="视频名称不能为空" errormsg="视频名称不能超过10位！" placeholder="请输入10字以内名称" class="input" name="name" />' +
            '<div class="Validform_checktip"></div>' +
            '</div>' +
            '<div class="form_item"><span class="label">上传封面：</span>' +
            '<label class="popup_upload_input" id="image_label_id"><i id="popup_upload_img"></i>点击选择文件</label>' +
            '<input type="hidden" name="coverImgPath" id="image_path_id"/>' +
            '</div>' +
            '<div class="progress progress-striped active hidden" id="uploader_video_progress"><div class="progress-bar progress-bar-success" role="progressbar"aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"style="width: 0%;"> <span class="sr-only">40% 完成</span> </div> </div>' +
            '<div class="form_item"><span class="label">上传视频：</span>' +
            '<label class="popup_upload_input" id="vedio_label_id"><i id="popup_upload_video"></i>点击选择文件</label>' +
            '<input type="hidden" name="vedioPath" id="vedio_path_id"/>' +
            '</div>' +
            '<input type="submit" value="提交" class="popup_upload_btn uni_btn" />' +
            '</form></div>',
            success: function (layero, index) {
                // 上传图片
                var uploaderImg = WebUploader.create({

                    // 选完文件后，是否自动上传。
                    auto: true,

                    // swf文件路径
                    swf: '/plugins/webuploader/Uploader.swf',

                    // 文件接收服务端。
                    server: '/portal/services/customer/uploadVedioFile.html',

                    // 选择文件的按钮。可选。
                    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                    pick: '#popup_upload_img',

                    // 只允许选择图片文件。
                    accept: {
                        title: 'Images',
                        extensions: 'gif,jpg,jpeg,bmp,png',
                        mimeTypes: 'image/jpeg,image/jpg,image/gif,image/png,image/bmp'
                    },
                });
                uploaderImg.on('uploadSuccess', function (file, response) {
                    if (response.success) {
                        $('#image_label_id').html(file.name);
                        $('#image_path_id').val(response.data.url);
                    }else {
                        $.platform.simplemsg('上传失败', 2000, 2);
                    }

                });
                //上传视频
                var uploaderVedio = WebUploader.create({

                    // 选完文件后，是否自动上传。
                    auto: true,

                    // swf文件路径
                    swf: '/plugins/webuploader/Uploader.swf',

                    // 文件接收服务端。
                    server: '/portal/services/customer/uploadVedioFile.html',

                    // 选择文件的按钮。可选。
                    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                    pick: '#popup_upload_video',
                });
                uploader.on('uploadError', function (file) {
                    $.platform.msg('上传失败', 1000, 2);
                });

                uploaderVedio.on('uploadSuccess', function (file, response) {
                    if (response.success) {
                        $('#vedio_label_id').html(file.name)
                        $('#vedio_path_id').val(response.data.url)
                    }else {
                        $.platform.simplemsg('上传失败', 2000, 2);
                    }

                });

                uploaderVedio.on('uploadProgress', function (file, percentage) {
                    var $li = $('#uploader_video_progress');
                    $li.removeClass('hidden');
                    $percent = $li.find('.progress-bar');
                    $percent.css('width', percentage * 100 + '%');
                    if (percentage * 100 == 100) {
                        $percent.css('width', '0%');
                        $li.addClass('hidden');
                    }
                });

                $('.close_popup_video').click(function (e) {
                    layer.close(index);
                });

                //监控form表单
                $(".form_upload_mvideo").Validform({
                    tiptype: function (msg, o, cssctl) {
                        if (!o.obj.is("form")) {
                            var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                            // alert(o.obj[0].tagName);
                            cssctl(objtip, o.type);
                            objtip.text(msg);
                        }
                    },
                    ajaxPost: true,
                    showAllError: true,
                    usePlugin: {
                        jqtransform: {
                            //会在当前表单下查找这些元素;
                            selector: ":checkbox,:radio,.decorate"
                        }
                    },
                    beforeCheck:function(curform){
                        var name = $("#video_name_id").val();
                        var vedioImg = $("#image_path_id").val();
                        var vedio = $("#vedio_path_id").val();
                        if(name == null || name == "") {
                            $.platform.simplemsg("视频名称不能为空", 1000, 5);
                            return false;
                        }
                        if(vedioImg == null || vedioImg == "") {
                            $.platform.simplemsg("视频封面不能为空", 1000, 5);
                            return false;
                        }
                        if(vedio == null || vedio == "") {
                            $.platform.simplemsg("视频不能为空", 1000, 5);
                            return false;
                        }
                    },
                    beforeSubmit: function (curform) {
                        loading = top.layer.load(2, {
                            shade: [0.3, '#eee'] //0.1透明度的白色背景
                        });
                    },
                    callback: function (data) {
                        if (data.success) {
                            cusVideoList();
                            $.platform.msg('上传成功！', 1000, 6);
                        } else {
                            $.platform.msg(data.message, 1000, 5);
                        }
                    }
                });
            }
        });
    });

    <!-- popup -->
    function videoPlay(cover, cusVideo) {

        // 弹出播放框
        $(".popup_service_rule").addClass("show");
        layer.open({
            title: false,
            btn: '',
            closeBtn: 1,
            offset: ['100px', '400px'],
            shadeClose: true,
            content: '<div id="vedio_play_div_id"></div >',
            success: function (layero, index) {
                var setT = null;

                function loadedHandler() {
                    if (CKobject.getObjectById('ckplayer_vedio_play_div_id').getType()) {
                        CKobject.getObjectById('ckplayer_vedio_play_div_id').addListener('paused', pausedHandler);
                    }
                    else {
                        CKobject.getObjectById('ckplayer_vedio_play_div_id').addListener('paused', 'pausedHandler');
                    }
                }

                function pausedHandler(b) {
                    if (setT) {
                        window.clearInterval(setT);
                    }
                    if (!b) {
                        setT = window.setInterval(setFunction, 1000);
                    }
                }

                var flashvars = {
                    f: cusVideo,
                    c: 0,
                    p: 2,
                    b: 0,
                    i: cover,
                    loaded: 'loadedHandler',
                    my_url: encodeURIComponent(window.location.href)
                };
                var video = [cusVideo + '->video/flv', cusVideo + '->video/mp4'];
                CKobject.embed('/plugins/ckplayer6.8/ckplayer.swf', 'vedio_play_div_id', 'ckplayer_vedio_play_div_id', '600', '400', false, flashvars, video);

                $('.popup_service_rule').click(function (e) {
                    layer.close(index);
                });
            }
        });
    }

</script>
</body>
</html>