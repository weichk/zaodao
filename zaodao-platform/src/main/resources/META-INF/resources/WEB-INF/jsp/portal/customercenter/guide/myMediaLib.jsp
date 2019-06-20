<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!-- page: 我的媒体库 -->
<div class="media_lib page_wrap">
    <div class="mslide_tabs">
        <ul class="nav">
            <li><a class="current" href="#" onclick="getAlbumList();">相册</a></li>
            <li><a href="#">视频</a></li>
        </ul>
        <span class="m_indicator"></span>
    </div>
    <div class="mslide_content">
        <div class="item" id="photoItem">

        </div>
        <!-- 相册 end -->
        <div class="item">
            <form action="#" method="post" name="manage_video_folder" class="tools mt20 video_tools">
                <a id="centerVideoUpload" class="media_file uni_btn pie"><i
                        class="sprite icon_upload_video_w"></i>上传视频</a>
            </form>
            <ul class="video_cover_list" id="myVideoList">
            </ul>
        </div>
        <!-- 视频 end -->
        <!-- item -->
    </div>
</div>
<!-- popup window -->
<div class="popup_shadow"></div>
<div class="popup pie">
    <h2 class="popup_t pie">创建相册</h2>
    <a href="#" class="close_popup" title="关闭"><i class="sprite icon_close"></i></a>
    <form action="/portal/services/guideCenter/createAlbum.html" method="post" name="popup_add_album"
          class="popup_add_album">
        <div class="ac">
            <label for="album_name">相册名称</label>
            <input type="text" name="albumName" id="album_name" class="album_name_text" datatype="*1-10"
                   nullmsg="相册名称不能为空">
        </div>
        <div class="popup_tips">10个字符以内</div>
        <p class="ac">
            <input type="submit" name="add_album" value="创建" class="add_album_btn uni_btn pie">
        </p>
    </form>
</div>
<script type="text/javascript">
    $(function () {
        //监控form表单
        $(".popup_add_album").Validform({
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
            beforeCheck: function (curform) {
                var name = $("#album_name").val();
                if (name == null || name == "") {
                    $.platform.simplemsg("上传图片不能为空", 1000, 5);
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
                    $.platform.msg('创建成功！', 1000, 6);
                    $('.popup, .popup_shadow').hide();
                    getAlbumList();
                } else {
                    $.platform.msg(data.message, 1000, 5);
                }
            }
        });
    });
</script>


<!--数据加载，业务处理-->
<script id="videoList-template" type="text/html">
    {{#each rows}}
    <li>
        <a href="javascript:videoPlay('{{cover}}','{{cusVideo}}');" class="con pie">
            <div class="img">
                <img src="{{cover}}" alt="">
                <i class="sprite icon_play"></i>
            </div>
            <div class="folder_name">{{videoName}}</div>
        </a>
        <div class="tools">
            <a href="javascript:modifyVideo('{{videoName}}','{{id}}')" class="vedit">编辑</a>
            <a href="#" class="vdelete">删除</a>
        </div>
    </li>
    {{/each}}
</script>
<script id="photoList-template" type="text/html">
    <form action="#" method="post" name="manage_img_gallery" class="manage_img_gallery">
        <div class="tools rename_tools mt20">
            <input type="hidden" value="{{data.customerAlbum.id}}" name="albumId" id="albumId">
            <input type="text" value="{{data.customerAlbum.albumName}}" id="rename_file" class="rename_file" disabled>
            <label for="rename_file" class="sprite icon_pencil_s hide"></label>
            <label class="media_file uni_btn pie"><i id="uploadPhoto"
                                                     class="sprite icon_upload_img_w"></i>上传照片</label>
            <input type="file" id="img_xfile_1" class="xfile">
            <a href="#" class="edit_gallery">编辑</a>
            <button name="delete_item" type="button" class="delete_item">删除选中</button>
            <button name="set_cover" class="set_cover">设置为封面海报</button>
            <a href="#" class="edit_done">完成编辑</a>
        </div>
        <ul class="media_item_list">
            {{#each rows}}
            <li>
                <label>
                    <img src="{{cusPicture}}" style="width: 160px;height: 120px" alt=""
                         onclick="window.open('{{cusPicture}}')">
                    <span class="check"><input type="checkbox" name="video_cover" value="{{id}}"
                                               disabled></span>
                </label>
            </li>
            {{/each}}
        </ul>
    </form>
</script>
<script id="albumList-template" type="text/html">
    <!-- 相册文件夹 -->
    <form action="#" method="post" name="manage_video_folder" class="tools mt20">
                <span for="media_xfile" class="select_img_folder media_file uni_btn pie"><i
                        class="sprite icon_upload_img_w"></i>上传照片</span>
        <a href="#" name="create_folder" class="create_folder">创建相册</a>
    </form>

    <ul class="img_cover_list media_cover_list">
        {{#each rows}}
        <li>
            <div class="con pie">
                <a href="#" onclick="getPhotoList('{{id}}')" class="img">
                    {{#if coverImg}}
                    <img src="{{coverImg}}" alt="">
                    {{else}}
                    <img src="/images/media_cover_01.jpg" alt="">
                    {{/if}}
                    <%--<span class="num">15</span>--%>
                </a>
                {{#if type}}
                {{else}}
                <a href="#" class="tool_del_folder"></a>
                {{/if}}
                <input type="text" name="folder_name" value="{{albumName}}" alt="{{albumName}}"
                       class="folder_name"
                       disabled>
                <input type="hidden" value="{{id}}" name="albumId" class="albumId"/>
                {{#if type}}
                {{else}}
                <a href="#" class="edit">编辑</a>
                <a href="#" class="btn_done">完成</a>
                <a href="#" class="btn_cancel">取消</a>
                {{/if}}
            </div>
        </li>
        {{/each}}
    </ul>

</script>
<script type="text/javascript">

    // 删除视频
    $('.tool_delete').click(function (e) {
        e.preventDefault();

        layer.msg('确定要删除视频么？', {
            time: 0 //不自动关闭
            , skin: 'alarm__popup'
            , shade: .5
            , scrollbar: false
            , shadeClose: true
            , btn: ['删除', '取消']
            , yes: function (index) {
                layer.close(index);
                layer.msg('视频已删除');
            }
        });
    });

    $(function () {
        getAlbumList();
        getMyMediaList();
    });
    function getMyMediaList() {
        var tmpSource = $("#videoList-template").html();
        var template = Handlebars.compile(tmpSource);
        $.ajax({
            type: "get",
            url: "/portal/services/guideCenter/myVideoList.html",
            dataType: 'json',
            async: true,
            data: {},
            beforeSend: function (curform) {
                loading = top.layer.load(2, {
                    shade: [0.3, '#eee'] //0.1透明度的白色背景
                });
            },
            complete: function () {
                layer.closeAll();
            },
            error: function (data) {
                return false;
            },
            success: function (data) {
                var listHtml = template(data);
                $('#myVideoList').html(listHtml);
            },
        });
    }

    function getPhotoList(albumId) {
        var tmpSource = $("#photoList-template").html();
        var template = Handlebars.compile(tmpSource);
        $.ajax({
            type: "get",
            url: "/portal/services/guideCenter/photoList.html",
            dataType: 'json',
            async: true,
            data: {"albumId": albumId},
            beforeSend: function (curform) {
                loading = top.layer.load(2, {
                    shade: [0.3, '#eee'] //0.1透明度的白色背景
                });
            },
            complete: function () {
                layer.closeAll();
            },
            error: function (data) {
                return false;
            },
            success: function (data) {
                var listHtml = template(data);
                $('#photoItem').html(listHtml);
                bindPhotoEvent();
            },
        });
    }

    function getAlbumList() {
        var tmpSource = $("#albumList-template").html();
        var template = Handlebars.compile(tmpSource);
        $.ajax({
            type: "get",
            url: "/portal/services/guideCenter/albumList.html",
            dataType: 'json',
            async: true,
            data: {},
            beforeSend: function (curform) {
                loading = top.layer.load(2, {
                    shade: [0.3, '#eee'] //0.1透明度的白色背景
                });
            },
            complete: function () {
                layer.closeAll();
            },
            error: function (data) {
                return false;
            },
            success: function (data) {
                var listHtml = template(data);
                $('#photoItem').html(listHtml);
                bindAlbumEvent(data.rows);
            },
        });
    }

    function bindPhotoEvent() {
        // 编辑相册内容
        $('.edit_gallery').click(function (e) {
            e.preventDefault();
            $(this).hide()
                .siblings('.delete_item,.edit_done').show()
                .siblings('.icon_pencil_s').removeClass('hide');
            $(".manage_img_gallery .check").show();
            $(".manage_img_gallery input").prop('disabled', false);
        });
        // 完成编辑相片
        $('.edit_done').click(function (e) {
            e.preventDefault();
            if ($("#rename_file").val() == "") {
                layer.msg("相册名称不能为空！");
                return;
            }
            $.ajax({
                type: "POST",
                url: "/portal/services/guideCenter/renameAlbum.html",
                data: {
                    "albumId": $("#albumId").val(),
                    "albumName": $("#rename_file").val()
                },// 要提交的表单
                success: function (data) {
                    if (data.success) {
                        layer.close();
                        layer.msg('修改成功！');
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
            $('.manage_img_gallery .rename_file').prop('disabled', true);
            $(this).hide()
                .siblings('.delete_item,.edit_done,.set_cover').hide()
                .siblings('.edit_gallery').show()
                .siblings('.icon_pencil_s').addClass('hide');

            $("input").prop('checked', false);
            $('.jqTransformChecked').removeClass('jqTransformChecked');
            $(".manage_img_gallery input").prop('disabled', true);
            $(".manage_img_gallery .check").hide();
        });

        // 兼容IE 不支持 :nth-child(n)的情况
        $('.media_cover_list li:nth-child(4n),.video_cover_list li:nth-child(3n), .media_item_list li:nth-child(4n)').addClass('last');

        // 选中一个时，显示按钮：设置为封面海报（checkbox的name值：video_cover改变时，此处需做相应更改）
        $('.media_item_list :checkbox').change(function () {
            if ($('.media_item_list input[name="video_cover"]:checked').length == 1) {
                $('.set_cover').fadeIn();
            } else {
                $('.set_cover').fadeOut();
            }
        });

        $(".manage_video_folder,.manage_img_gallery").Validform({
            tiptype: function (msg, o, cssctl) {
                if (!o.obj.is("form")) {
                    var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                    cssctl(objtip, o.type);
                    objtip.text(msg);
                }
            },
            showAllError: true,
            usePlugin: {
                jqtransform: {
                    //会在当前表单下查找这些元素;
                    selector: "select,:checkbox,:radio,.decorate"
                }
            }
        });

        // 上传图片
        var uploader = WebUploader.create({

            // 选完文件后，是否自动上传。
            auto: true,

            // swf文件路径
            swf: '/plugins/webuploader/Uploader.swf',

            // 文件接收服务端。
            server: '/portal/services/guideCenter/uploadImg.html',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#uploadPhoto',

            formData: {"albumId": $("#albumId").val()},
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/jpeg,image/jpg,image/gif,image/png,image/bmp'
            },
        });
        uploader.on('uploadSuccess', function (file, response) {
            if (response.success) {
                $.platform.msg('上传成功！', 1000, 6);
                getPhotoList($("#albumId").val());
            } else {
                $.platform.simplemsg('上传失败', 2000, 2);
            }

        });

        // 删除照片
        $('.delete_item').click(function (e) {
            e.preventDefault();

            if ($('.media_item_list input[name="video_cover"]:checked').length == 0) {
                layer.msg("请选择要删除的照片！");
                return;
            }
            layer.msg('确定删除照片么？', {
                time: 0 //不自动关闭
                , skin: 'alarm__popup'
                , shade: .5
                , scrollbar: false
                , shadeClose: true
                , btn: ['删除', '取消']
                , yes: function (index) {
                    var id = "";
                    $('.media_item_list input[name="video_cover"]:checked').each(function () {
                        id += $(this).val() + ",";
                    });

                    $.ajax({
                        type: "POST",
                        url: "/portal/services/guideCenter/deleteImgs.html",
                        data: {"id": id},// 要提交的表单
                        success: function (data) {
                            if (data.success) {
                                layer.close(index);
                                layer.msg('照片已删除');
                                getPhotoList($("#albumId").val());
                            } else {
                                layer.msg(data.message);
                            }
                        }
                    });
                }
            });
        });

        // 设置为相册封面
        $('.set_cover').click(function (e) {
            e.preventDefault();

            if ($('.media_item_list input[name="video_cover"]:checked').length != 1) {
                layer.msg("请选择要设置的照片！");
                return;
            }
            layer.msg('确定设置为相册封面？', {
                time: 0 //不自动关闭
                , skin: 'alarm__popup'
                , shade: .5
                , scrollbar: false
                , shadeClose: true
                , btn: ['确定', '取消']
                , yes: function (index) {
                    var imgId = $('.media_item_list input[name="video_cover"]:checked').val();
                    $.ajax({
                        type: "POST",
                        url: "/portal/services/guideCenter/setAlbumCoverImg.html",
                        data: {
                            "albumId": $("#albumId").val(),
                            "coverImgId": imgId
                        },// 要提交的表单
                        success: function (data) {
                            if (data.success) {
                                layer.close(index);
                                layer.msg('设置成功');
                            } else {
                                layer.msg(data.message);
                            }
                        }
                    });
                }
            });
        });
    }

    function bindAlbumEvent(data) {
        $('.tool_del_folder').click(function (e) {
            e.preventDefault();
            var albumId = $(this).siblings('.albumId').val();
            layer.msg('确定删除相册么？', {
                time: 0 //不自动关闭
                , skin: 'alarm__popup'
                , shade: .5
                , scrollbar: false
                , shadeClose: true
                , btn: ['删除', '取消']
                , yes: function (index) {
                    $.ajax({
                        type: "POST",
                        url: "/portal/services/guideCenter/deleteAlbum.html",
                        data: {"albumId": albumId},// 要提交的表单
                        success: function (data) {
                            if (data.success) {
                                layer.close(index);
                                layer.msg('相册已删除');
                                getAlbumList();
                            } else {
                                layer.msg(data.message);
                            }
                        }
                    });
                }
            });
        });

        // 选择照片文件夹
        $('.select_img_folder').click(function (e) {
            e.preventDefault();
            var option = "";
            for (var i = 0, j = data.length; i < j; i++) {
                option += '<option value="' + data[i].id + '">' + data[i].albumName + '</option>';
            }
            layer.open({
                type: 1,
                title: false,
                area: ['402px', '300px'],
                closeBtn: 0,
                shadeClose: true,
                skin: 'popup_img_folder',
                content: '<div class="content_wrap">' +
                '<a href="#" class="close_popup_video"></a>' +
                '<form action="#" method="post" name="form_select_img_folder" class="form_select_img_folder form_upload_mvideo">' +
                '<div class="form_item"><label class="label">选择相册：</label>' +
                '<select name="albumId" id="popup_folder_name">' + option +
                '</select>' +
                '<div class="Validform_checktip"></div>' +
                '</div>' +
                '<div class="form_item"><label class="label">上传照片：</label>' +
                '<label class="popup_upload_input" id="uploadPhotoLabel"><i id="uploadAlbumPhoto"></i>点击选择文件</label>' +
//                '<input type="hidden" name="imgId" id="uploadPhotoInput"/>' +
                '</div>' +
                '<input type="button" value="提交" class="popup_upload_btn uni_btn" /></form></div>',
                success: function (layero, index) {
                    $(".form_select_img_folder").Validform({
                        tiptype: function (msg, o, cssctl) {
                            if (!o.obj.is("form")) {
                                var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                                cssctl(objtip, o.type);
                                objtip.text(msg);
                            }
                        },
                        showAllError: true,
                        usePlugin: {
                            jqtransform: {
                                //会在当前表单下查找这些元素;
                                selector: "select,:checkbox,:radio,.decorate"
                            }
                        }
                    });
                    $('.close_popup_video').click(function (e) {
                        e.preventDefault();
                        layer.close(index);
                    });
                    // 上传图片
                    var uploader = WebUploader.create({

                        // 选完文件后，是否自动上传。
                        auto: false,

                        // swf文件路径
                        swf: '/plugins/webuploader/Uploader.swf',

                        // 文件接收服务端。
                        server: '/portal/services/guideCenter/uploadImg.html',

                        // 选择文件的按钮。可选。
                        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                        pick: '#uploadAlbumPhoto',

                        formData: {"albumId": $("#popup_folder_name").val()},
                        // 只允许选择图片文件。
                        accept: {
                            title: 'Images',
                            extensions: 'gif,jpg,jpeg,bmp,png',
                            mimeTypes: 'image/jpeg,image/jpg,image/gif,image/png,image/bmp'
                        },
                    });
                    uploader.on('beforeFileQueued', function (file) {
                        $('#uploadPhotoLabel').html(file.name);
                    });

                    uploader.on('uploadSuccess', function (file, response) {
                        if (response.success) {
                            $.platform.msg('上传成功！', 1000, 6);
//                            $('#uploadPhotoInput').val(response.data.url);
                        } else {
                            $.platform.simplemsg('上传失败', 2000, 2);
                        }

                    });

                    $(".popup_upload_btn").click(function () {
                        uploader.upload();
                    })
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
                        beforeCheck: function (curform) {
                            var img = $("#uploadPhotoInput").val();
                            if (img == null || img == "") {
                                $.platform.simplemsg("上传图片不能为空", 1000, 5);
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
                                $.platform.msg('上传成功！', 1000, 6);
                                getMyMediaList();
                            } else {
                                $.platform.msg(data.message, 1000, 5);
                            }
                        }
                    });
                }
            });
        });

        // 编辑相册封面
        $('.img_cover_list .edit').bind("click", (function (e) {
            e.preventDefault();
            // 点击编辑且其它相册编辑未退出时，还原相册名称
            $('.img_cover_list .folder_name').each(function (i) {
                var oldName = $('.img_cover_list .folder_name').eq(i).attr('alt');
                $('.img_cover_list .folder_name').eq(i).val(oldName);
            });

            // 点击编辑且其它相册编辑未退出时，退出其它相册的编辑
            $('.img_cover_list .edit').show();
            $('.img_cover_list .btn_done,.img_cover_list .btn_cancel').hide();
            $('.img_cover_list .folder_name').prop('disabled', true);

            $(this).hide()
                .siblings('.btn_done,.btn_cancel').show()
                .siblings('.folder_name').prop('disabled', false).focus();
            editFolderName();
        }));

        function editFolderName() {
            // 完成编辑相册封面
            $('.img_cover_list .btn_done').click(function (e) {
                e.preventDefault();
                var newName = $(this).siblings('.folder_name').val();
                var albumId = $(this).siblings('.albumId').val();
                if (newName == "") {
                    layer.msg("相册名称不能为空！");
                    return;
                }
                $.ajax({
                    type: "POST",
                    url: "/portal/services/guideCenter/renameAlbum.html",
                    data: {
                        "albumId": albumId,
                        "albumName": newName
                    },// 要提交的表单
                    success: function (data) {
                        if (data.success) {
                            layer.close();
                            layer.msg('修改成功！');
                        } else {
                            layer.msg(data.message);
                        }
                    }
                });
                $(this).hide()
                    .siblings('.edit').show()
                    .siblings('.btn_cancel').hide()
                    .siblings('.folder_name').prop('disabled', true).attr('alt', newName);
            });
            // 取消编辑相册封面
            $('.img_cover_list .btn_cancel').click(function (e) {
                e.preventDefault();
                var oldName = $(this).siblings('.folder_name').attr('alt');
                $(this).siblings('.folder_name').val(oldName);
                $(this).hide()
                    .siblings('.edit').show()
                    .siblings('.btn_done').hide()
                    .siblings('.folder_name').prop('disabled', true);
            });
        }

        // popup
        $('.create_folder').click(function (e) {
            e.preventDefault();
            $('.popup, .popup_shadow').show();
        });
        $('.close_popup').click(function (e) {
            e.preventDefault();
            $('.popup, .popup_shadow').hide();
        });

        $('.img_cover_list li').hover(
            function () {
                $(this).find('.tool_del_folder').show();
            }, function () {
                $(this).find('.tool_del_folder').hide();
            }
        );
        $(".manage_video_folder,.manage_img_gallery").Validform({
            tiptype: function (msg, o, cssctl) {
                if (!o.obj.is("form")) {
                    var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                    cssctl(objtip, o.type);
                    objtip.text(msg);
                }
            },
            showAllError: true,
            usePlugin: {
                jqtransform: {
                    //会在当前表单下查找这些元素;
                    selector: "select,:checkbox,:radio,.decorate"
                }
            }
        });
    }
</script>
<!--视频上传下载-->
<script type="text/javascript" src="/plugins/webuploader/webuploader.js" charset="utf-8"></script>
<script type="text/javascript" src="/plugins/ckplayer6.8/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript">
    $('#centerVideoUpload').click(function (e) {
        layer.open({
            type: 1,
            title: false,
            area: ['402px', '366px'],
            closeBtn: 0,
            shadeClose: true,
            skin: 'upload_mvideo',
            content: '<div class="content_wrap">' +
            '<a href="#" class="close_popup_video"></a>' +
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
                    } else {
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
                    accept: {
                        title: 'videos',
                        extensions: 'mp4,flv,rmvb,mkv,avi,wmv,3gp',
                    }
                });

                uploaderVedio.on('uploadSuccess', function (file, response) {
                    if (response.success) {
                        $('#vedio_label_id').html(file.name)
                        $('#vedio_path_id').val(response.data.url)
                    } else {
                        $.platform.simplemsg('上传失败', 2000, 2);
                    }

                });

                uploaderVedio.on('uploadProgress', function (file, percentage) {

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
                    beforeCheck: function (curform) {
                        var name = $("#video_name_id").val();
                        var vedioImg = $("#image_path_id").val();
                        var vedio = $("#vedio_path_id").val();
                        if (name == null || name == "") {
                            $.platform.simplemsg("视频名称不能为空", 1000, 5);
                            return false;
                        }
                        if (vedioImg == null || vedioImg == "") {
                            $.platform.simplemsg("视频封面不能为空", 1000, 5);
                            return false;
                        }
                        if (vedio == null || vedio == "") {
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
                            $.platform.msg('上传成功！', 1000, 6);
                            getMyMediaList();
                        } else {
                            $.platform.msg(data.message, 1000, 5);
                        }
                    }
                });
            }
        });
    });
    function modifyVideo(videoName, videoId) {
        layer.open({
            type: 1,
            title: false,
            area: ['402px', '310px'],
            closeBtn: 0,
            scrollbar: false,
            shadeClose: true,
            skin: 'popup_img_folder',
            content: '<div class="content_wrap">' +
            '<a href="#" class="close_popup_video"></a>' +
            '<form action="/portal/services/guideCenter/updateVideo.html" method="post" name="form_upload_mvideo" class="form_upload_mvideo">' +
            '<div class="form_item">' +
            '<span class="label">视频名称：</span>' +
            '<input type="text" id="video_name_id" value="' + videoName + '" datatype="*1-10" nullmsg="视频名称不能为空" errormsg="视频名称不能超过10位！" placeholder="请输入10字以内名称" class="input" name="videoName" />' +
            '<div class="Validform_checktip"></div>' +
            '</div>' +
            '<div class="form_item"><span class="label">修改封面：</span>' +
            '<label class="popup_upload_input" id="image_label_id"><i id="popup_upload_img"></i>点击选择文件</label>' +
            '<input type="hidden" name="coverImgPath" id="image_path_id"/>' +
            '</div>' +
            '<input type="hidden" name="videoId" value="' + videoId + '"/>' +
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
                    } else {
                        $.platform.simplemsg('上传失败', 2000, 2);
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
                    beforeCheck: function (curform) {
                        var name = $("#video_name_id").val();
                        if (name == null || name == "") {
                            $.platform.simplemsg("视频名称不能为空", 1000, 5);
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
                            $.platform.msg('修改成功！', 1000, 6);
                            getMyMediaList();
                        } else {
                            $.platform.msg(data.message, 1000, 5);
                        }
                    }
                });
            }
        });
    }

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
<!-- tab切换 -->
<script type="text/javascript">
    $(function () {

        // tab slide function
        function tabSlide(nav, idc, item) {
            var indicator = $(idc),
                indicatorHalfWidth = indicator.width() / 2,
                lis = $(nav).children('li');

            $(nav).tabs(item, {
                effect: 'fade',
                fadeOutSpeed: 0,
                fadeInSpeed: 400,
                onBeforeClick: function (event, index) {
                    var li = lis.eq(index),
                        newPos = li.position().left + (li.width() / 2) - indicatorHalfWidth;
                    indicator.stop(true).animate({
                        left: newPos
                    }, 600, 'easeInOutExpo');
                }
            });
        }

        tabSlide(".mslide_tabs .nav", ".m_indicator", ".mslide_content .item");
    });
</script>