$(function () {
    // $(".registerform").Validform();  //就这一行代码！;
    $("#apply_guide_info_form_one_id").Validform({
        tiptype: function (msg, o, cssctl) {
            if (!o.obj.is("form")) {
                var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                // alert(o.obj[0].tagName);
                cssctl(objtip, o.type);
                objtip.text(msg);
            }
        },
        ajaxPost:true,
        showAllError: true,
        usePlugin: {
            jqtransform: {
                //会在当前表单下查找这些元素;
                selector: "select,:checkbox,:radio,.decorate"
            }
        },
        beforeSubmit:function (curform) {
            loading = top.layer.load(2, {
                shade: [0.3,'#eee'] //0.1透明度的白色背景
            });
        },
        callback:function(data){
            if (data.success) {
                window.location.href = '/portal/services/customer/applyGuideStepTwo.html'
            } else {
                $.platform.msg(data.message, 1000, 5);
            }
        }
    });

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
        pick: '#guider_certification_id',

        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/jpeg,image/jpg,image/gif,image/png,image/bmp'
        },
    });
    uploaderImg.on('uploadSuccess', function (file, response) {
        if (response.success) {
            $('#guider_certification_img_id').attr('src',response.data.url);
            $('#guide_certificate_img_id').val(response.data.url);
        }else {
            $.platform.simplemsg('上传失败', 2000, 2);
        }

    });
});