$(document).ready(function () {
    pictureList();
    cusVideoList();
})
// 上传图片
var uploader = WebUploader.create({

    // 选完文件后，是否自动上传。
    auto: true,

    // swf文件路径
    swf: '/plugins/webuploader/Uploader.swf',

    // 文件接收服务端。
    server: '/portal/services/customer/uploadPicture.html',

    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick: '#filePicker',

    // 只允许选择图片文件。
    accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/jpeg,image/jpg,image/gif,image/png,image/bmp'
    },
});

// 文件上传成功，给item添加成功class, 用样式标记上传成功。
uploader.on('uploadSuccess', function (file) {
    pictureList();
});

//做个简易的验证  大小 格式
$('#avatarInput').on('change', function (e) {
    var filemaxsize = 1024 * 5;//5M
    var target = $(e.target);
    var Size = target[0].files[0].size / 1024;
    if (Size > filemaxsize) {
        alert('图片过大，请重新选择!');
        $(".avatar-wrapper").childre().remove;
        return false;
    }
    if (!this.files[0].type.match(/image.*/)) {
        alert('请选择正确的图片!')
    } else {
        var filename = document.querySelector("#avatar-name");
        var texts = document.querySelector("#avatarInput").value;
        var teststr = texts; //你这里的路径写错了
        testend = teststr.match(/[^\\]+\.[^\(]+/i); //直接完整文件名的
        filename.innerHTML = testend;
    }

});

$(".avatar-save").on("click", function () {
    var img_lg = document.getElementById('imageHead');
    // 截图小的显示框内的内容
    html2canvas(img_lg, {
        allowTaint: true,
        taintTest: false,
        onrendered: function (canvas) {
            canvas.id = "mycanvas";
            //生成base64图片数据
            var dataUrl = canvas.toDataURL("image/jpeg");
            var newImg = document.createElement("img");
            newImg.src = dataUrl;
            $.ajax({
                type: "post",
                url: "/portal/services/customer/uploadGuideConverImg.html",
                dataType: 'json',
                async: true,
                data: {"coverImg": dataUrl},
                error: function (data) {
                    layer.alert("上传失败", {icon: 1, offset: '280px'});
                    return false;
                },
                success: function (data) {
                    if (data.success) {
                        $('.preview img').attr('src', dataUrl);
                    } else {
                        layer.alert("上传失败", {icon: 1, offset: '280px'});
                    }
                },
            });
        }
    });
});
/**
 * 获取当前标签下的图片列表
 * @type {any}
 */
var pictureListSource = $("#pictureList-template").html();
var pictureListTemplate = Handlebars.compile(pictureListSource);
var pictureListContext;
function pictureList() {
    $.ajax({
        type: "post",
        url: "/portal/services/customer/getDefaultPictureList.html",
        dataType: 'json',
        async: true,
        error: function (data) {
            alert("Connection error");
            return false;
        },
        success: function (data) {
            pictureListContext = data;
            var pictureListHtml = pictureListTemplate(pictureListContext);
            $('#picture_list_id').html(pictureListHtml);
        },
    });
}
;

/**
 * 获取当前标签下的图片列表
 * @type {any}
 */
var videoListSource = $("#videoList-template").html();
var videoListTemplate = Handlebars.compile(videoListSource);
var videoListContext;
function cusVideoList() {
    $.ajax({
        type: "post",
        url: "/portal/services/customer/getCusVideoList.html",
        dataType: 'json',
        async: true,
        error: function (data) {
            alert("Connection error");
            return false;
        },
        success: function (data) {
            videoListContext = data;
            var videoListHtml = videoListTemplate(videoListContext);
            $('#video_list_id').html(videoListHtml);
        },
    });
}
;
/**
 * 删除图片
 * @param id
 */
function deletePicture(id) {
    $.ajax({
        type: 'post',
        url: "/portal/services/customer/deletePicture.html",
        dataType: "json",
        async: true,
        data: {"id": id},
        error: function (data) {
            layer.alert('删除失败', {icon: 11, offset: '280px'});
        },
        success: function (data) {
            if (data.success) {
                pictureList();
            } else {
                layer.alert(data.message, {icon: 1, offset: '280px'});
            }

        }
    });
}

/**
 * 删除视频
 * @param id
 */
function deleteVideo(id) {
    layer.confirm('确认要删除吗？', {
        icon: 3,
        offset: '280px',
        skin: 'layui-layer-rim'
    }, function (index) {
        $.ajax({
            type: "post",
            url: "/portal/services/customer/deleteVideo.html",
            dataType: 'json',
            async: true,
            data: {"id": id},
            error: function (data) {
                $.platform.msg("删除失败", 1000, 5);
                return false;
            },
            success: function (data) {
                if (data.success) {
                    $.platform.msg("删除成功", 1000, 6);
                    //获取用户视频
                    cusVideoList();
                } else {
                    $.platform.msg(data.message, 1000, 5);
                }
            }
        });
    });
}


/**
 * 校验当前用户上传的信息是否完整
 * @param id
 */
function verifyStepTwoInfo() {
    $.ajax({
        type: 'post',
        url: "/portal/services/customer/verifyStepTwoInfo.html",
        dataType: "json",
        async: true,
        error: function (data) {
            $.platform.msg('系统错误', 1000, 5);
        },
        success: function (data) {
            if (data.success) {
                window.location.href = '/portal/services/customer/applyGuideStrpThree.html';
            } else {
                $.platform.msg(data.message, 2000, 5);
            }
        }
    });
}