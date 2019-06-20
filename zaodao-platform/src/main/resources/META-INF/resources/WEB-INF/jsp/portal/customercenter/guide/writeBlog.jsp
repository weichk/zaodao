<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <link rel="stylesheet" href="/css/news.css"/>
    <%--<link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all">--%>
    <link href="/plugins/umeditor/themes/zaodao/css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="/plugins/umeditor/third-party/template.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/plugins/umeditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/plugins/umeditor/umeditor.js"></script>
    <script type="text/javascript" src="/plugins/umeditor/lang/zh-cn/zh-cn.js"></script>
    <style>
        .layui-layedit {
            border: 0px;
            border-radius: 2px;
        }
    </style>
</head>
<body class="grey_bg">
<!-- 导航 -->
<jsp:include page="/portal/portlet/navAbsolute.html?pageIndex=firstPage"/>

<div class="banner">
    <div class="inner_banner"><img src="/images/banner_08.jpg" alt=""></div>
    <%--<form action="#" method="post" name="set_blog_banner" class="set_blog_banner">--%>
    <%--<label for="banner_file" class="ui_banner_file"><i--%>
    <%--class="sprite icon_editor_uploadimg_w"></i>设置帖子头图</label><input type="file"--%>
    <%--accept="image/jpeg,image/jpg,image/gif,image/png"--%>
    <%--id="banner_file" class="banner_file">--%>
    <%--<p>图片建议选择尺寸大于1680px的高清大图，如相机原图</p>--%>
    <%--</form>--%>
</div>
<!-- banner end -->

<form action="" method="post" name="write_blog" id="write_blog" class="write_blog cf">
    <div class="left">
        <div class="commen_title"><span class="yellow">写帖子</span></div>
        <input id="title" name="title" type="text" placeholder="请输入标题" class="wblog_h1" value="${article.title}">
        <%--<div class="tools">--%>
        <%--<label class="label"><i class="sprite icon_editor_bold"></i></label>--%>
        <%--<label class="label"><i class="sprite icon_editor_italic"></i></label>--%>
        <%--<label class="label"><i class="sprite icon_editor_underline"></i></label>--%>
        <%--<label for="img_file" class="label"><i class="sprite icon_editor_uploadimg"></i></label><input type="file" accept="image/jpeg,image/jpg,image/gif,image/png" id="img_file" class="xfile">--%>
        <%--<label for="video_file" class="label"><i class="sprite icon_editor_uploadvideo"></i></label><input type="file" accept="audio/*,video/*" id="video_file" class="xfile">--%>
        <%--</div>--%>
        <textarea name="content" id="wblog_textarea" placeholder="请输入内容">请输入内容</textarea>
        <input type="hidden" name="articleStatus" id="articleStatus" value="${article.articleStatus}">
        <div class="wblog_submit"><a id="draft_blog" href="javascript:void(0);" class="save_draft">保存草稿</a>
            <input type="button" name="push_blog" value="发表帖子" id="push_blog" class="push_blog uni_btn pie"/>
        </div>
        <input type="hidden" name="articleId" id="articleId" value="${article.id}"/>
    </div>
    <!-- left end -->

    <div class="right">
        <div class="r_title t1">帖子分类</div>
        <div class="select_type">
            <span class="select i1">
                <select name="articleType" id="articleTypeSelect">
                    <c:forEach items="${allArticleTypeEnums}" var="e">
                        <option value="${e.key}" <c:if test="${e.key==article.articleType}">
                            selected</c:if>>${e.value}</option>
                    </c:forEach>
                </select>
            </span>

            <span class="select i2">
                <select id="labelSelect" name="label">
                </select>
            </span>

            <div class="mt20">
                    <span class="select"><select name="area">
                        <c:forEach items="${areaList}" var="e">
                            <option value="${e.name}" <c:if test="${e.name==article.area}">
                                selected</c:if>>${e.name}</option>
                        </c:forEach>
                    </select></span>
            </div>
        </div>

        <div class="r_title">封面图设置</div>
        <div class="set_cover">
            <div class="no_cover" id="imgcover_img">
                <c:if test="${article!=null&&article.cover!=''}">
                    <img width='180' height='180' src='${article.cover}'></img>
                </c:if>
                <c:if test="${article==null}">
                    <img width='180' height='180' src='/images/diary_post_img_01.jpg'></img>
                </c:if>
            </div>
            <label class="uni_btn pie"><i id="imgcover_file"></i>上传图片</label>
            <input type="hidden" name="cover" id="cover"
                   value="<c:if test="${article.cover!=null}">${article.cover}</c:if>
                <c:if test="${article.cover==null}">/images/diary_post_img_01.jpg</c:if>"/>
            <input type="hidden" name="coverThumb" id="coverThumb"
                   value="<c:if test="${article.coverThumb!=null}">${article.coverThumb}</c:if>
                <c:if test="${article.coverThumb==null}">/images/diary_post_img_01.jpg</c:if>"/>
        </div>

        <%--<div class="r_title">快速插图</div>--%>
        <%--<div class="insert_img">--%>
        <%--<ul class="insert_img_list">--%>
        <%--<li><img src="/images/guide_gallery_thumb_02.jpg" alt=""></li>--%>
        <%--<li><img src="/images/guide_gallery_thumb_03.jpg" alt=""></li>--%>
        <%--<li><img src="/images/guide_gallery_thumb_04.jpg" alt=""></li>--%>
        <%--</ul>--%>

        <%--<div class="mt40 ac"><a href="#" class="uni_btn pie">相册选取</a></div>--%>
        <%--<div class="ac">--%>
        <%--<label for="imgcontent_file" class="upload_content_img">上传图片</label><input type="file"--%>
        <%--accept="image/jpeg,image/jpg,image/gif,image/png"--%>
        <%--id="imgcontent_file"--%>
        <%--class="xfile">--%>
        <%--</div>--%>
        <%--</div>--%>

        <div class="redpacket">
            <input type="hidden" id="hasRedBag" name="hasRedBag" value="false">
            <div class="t"><input type="checkbox" name="redPacket" id="check_redPacket"><label
                    for="check_redPacket">使用红包</label></div>
            <div class="col mt20"><label for="point_num">使用积分</label>
                <input type="text" name="score" id="point_num" class="text"
                       onkeydown="if(event.keyCode==13)event.keyCode=9"
                       onKeypress="if((event.keyCode<48 || event.keyCode>57)) event.returnValue=false"
                       onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
                       onkeyup="maxScore(this)"
                       maxlength="9">
            </div>
            <div class="remain_points">剩余积分：${pointAccount.available}</div>
            <div class="col mt20"><label for="redPacket_num">红包个数</label>
                <input type="text" name="redBagNum" id="redPacket_num"
                       onkeydown="if(event.keyCode==13)event.keyCode=9"
                       onKeypress="if((event.keyCode<48 || event.keyCode>57)) event.returnValue=false"
                       onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
                       class="text" maxlength="3">
            </div>
        </div>
    </div>
</form>
<!-- content end -->
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>

</body>
</html>
<script type="text/javascript">
    function maxScore(e) {
        var maxScore =${pointAccount.available};
        if (e.value > maxScore) {
            e.value = maxScore;
        }
    }
    $(function () {
        getLabels(true);
        $(".write_blog").Validform({
            tiptype: function (msg, o, cssctl) {
                if (!o.obj.is("form")) {
                    var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                    // alert(o.obj[0].tagName);
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
            },
            callback: function (data) {
            }
        });

        $("#articleTypeSelect").jqTransSelectChange(function (selectedItem) {
            getLabels(false);
//            console.log(selectedItem.text()); //获取单击的内容
//            console.log(selectedItem.attr("option-value"));//获取单击下拉列表的value值
        });
        function getLabels(flag) {
            $('#labelSelect option').remove();
//            console.info($('#articleTypeSelect').val());
            <c:forEach items="${allArticleCodeTypeEnums}" var="e">
            if ('${e.type}' == $('#articleTypeSelect').val()) {
                $('#labelSelect').append("<option value='${e.code}' <c:if test="${e.code==article.label}">selected</c:if>>${e.message}</option>");
            }
            </c:forEach>
            //再调用select美化
//            if ($('#articleTypeSelect').val() == 'guideTreeHole') {
//                console.info($('#articleTypeSelect').val());
//                $('#labelSelect').append("<option value='guideTreeHole'>导游树洞</option>");
//            }
            if (flag) {
//                $('#labelSelect').jqTransSelect();
            } else {
                fix_select('select#labelSelect');
            }
        }

        function fix_select(selector) {
            var i = $(selector).parent().find('div,ul').remove().css('zIndex');
            $(selector).unwrap().removeClass('jqTransformHidden').jqTransSelect();
            $(selector).parent().css('zIndex', i);
        }
    });


</script>
<script src="/plugins/layui/layui.js" charset="utf-8"></script>
<script>
    //    layui.use(['form', 'layedit', 'upload'], function () {
    //        var layedit = layui.layedit;
    //        //自定义工具栏
    //        var index = layedit.build('wblog_textarea', {
    //            tool: ['strong', 'italic', 'underline', 'del', '|', 'left', 'center', 'right', '|', 'face', 'link',
    //                'unlink', 'image']
    //            , uploadImage: {url: '/portal/guide/layedit.html?thumbSize=600', type: 'post'}
    //            , height: 400
    //        });
    //
    //        $("#push_blog").click(function () {
    //            saveWriteBlog(layedit.getContent(index), "published");
    //        });
    //        $("#draft_blog").click(function () {
    //            saveWriteBlog(layedit.getContent(index), "draft");
    //        });
    //    });
    var um = UM.getEditor('wblog_textarea', {
        theme: 'zaodao',
        //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
        toolbar: ['undo redo |  bold italic underline horizontal | emotion forecolor backcolor | fontsize removeformat |',
            'insertorderedlist insertunorderedlist',
            '| justifyleft justifycenter justifyright justifyjustify |',
            'link unlink | image video',
            '| preview'
        ]
        //图片上传配置区
        , imageUrl: "/portal/services/guideCenter/umUploadImg.html?thumbSize=600"             //图片上传提交地址
        , imagePath: ""                     //图片修正地址，引用了fixedImagePath,如有特殊需求，可自行配置
        , imageFieldName: "write_blog"                   //图片数据的key,若此处修改，需要在后台对应文件修改对应参数
        //focus时自动清空初始化时的内容
        , autoClearinitialContent: true,
        //关闭字数统计
        wordCount: false,
        //关闭elementPath
        elementPathEnabled: false,
        //默认的编辑区域高度
        initialFrameHeight: 400,
        initialFrameWidth: 750
        //更多其他参数，请参考ueditor.config.js中的配置项
    });

    $("#push_blog").click(function () {
        saveWriteBlog(um.getContent(), "published");
    });
    $("#draft_blog").click(function () {
        saveWriteBlog(um.getContent(), "draft");
    });
    function saveWriteBlog(content, type) {
        $("#articleStatus").val(type);
        if ($("#title").val() == "") {
            top.layer.msg("请输入标题！");
            return;
        }
        if (content == "") {
            top.layer.msg("请输入内容！");
            return;
        }
        if (type == "published" && content.length < 20) {
            $.platform.simplemsg("请至少输入20个字符", 1000, 5);
            return;
        }
        if ($("#cover").val() == "") {
            top.layer.msg("请上传封面图！");
            return;
        }
        var status = $("#check_redPacket").is(':checked');
        $("#hasRedBag").val(status);
        var pointNum = parseInt($("#point_num").val());
        var redPacketNum = parseInt($("#redPacket_num").val());
        if (status == true && pointNum < redPacketNum) {
            $.platform.simplemsg("积分必须大于红包个数", 1000, 5);
            return;
        }
        $("#wblog_textarea").val(content);
        $.ajax({
            type: "POST",
            url: "/portal/services/guideCenter/saveWriteBlog.html",
            data: $('.write_blog').serialize(),// 要提交的表单
            success: function (data) {
                if (data.status == "y") {
                    if (type == "published") {
                        top.layer.msg('发布成功', {icon: 1, time: 3000});
                        window.location.href = "/portal/guideHome/getArticleInfo_" + data.id + ".html";
                    } else {
                        top.layer.msg('保存成功', {icon: 1, time: 3000});
                        window.location.href = "/portal/services/customer/childpage.html?childIndex=myArticle";
                    }
                } else {
                    $.platform.simplemsg(data.info, 1000, 5);
                }
            }
        });
    }

    $(function () {
        <c:if test="${article.content!=null}">
        um.setContent('${article.content}', false);
        </c:if>
    });
</script>
<!-- 图片上传裁剪插件 -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/cropper.js"></script>
<script src="/js/sitelogo.js"></script>
<script src="/js/html2canvas.min.js"></script>
<script type="text/javascript" src="/plugins/webuploader/webuploader.js" charset="utf-8"></script>
<script type="text/javascript">
    // 上传图片
    var uploader = WebUploader.create({

        // 选完文件后，是否自动上传。
        auto: true,

        // swf文件路径
        swf: '/plugins/webuploader/Uploader.swf',

        // 文件接收服务端。
        server: '/ofile/upload.html?thumbSize=250',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#imgcover_file',

        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/jpeg,image/jpg,image/gif,image/png,image/bmp'
        },
    });
    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on('uploadSuccess', function (file, response) {
        var id = response.rows[0].id;
        $("#imgcover_img").html("<img width='180' height='180' src='/ofile/image/" + id + "'></img>");
        $("#cover").val('/ofile/image/' + id);
        $("#coverThumb").val('/ofile/thumb/' + id);
    });
</script>

