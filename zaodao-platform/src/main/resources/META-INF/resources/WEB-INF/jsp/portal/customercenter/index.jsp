<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <link rel="stylesheet" href="/css/member.css"/>
    <link rel="stylesheet" href="/css/member_page.css"/>
    <link rel="stylesheet" href="/css/cropbox.css">
    <script src="/js/modernizr.min.js"></script>
</head>

<body class="grey_bg">
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=customerCenter"/>
<div class="member_frame cf">
    <div class="frame_left">
        <div class="member_simple_info">
            <c:choose>
                <c:when test="${sessionScope.sessionKeyUserInfo.headImg != null && sessionScope.sessionKeyUserInfo.headImg != ''}">
                    <div class="img pie"><a href="#" class="upload_header_btn pie"><img
                            src="${sessionScope.sessionKeyUserInfo.headImg}" alt="" title="修改头像" class="pie"></a></div>
                </c:when>
                <c:otherwise>
                    <div id="member_header" class="img pie"><a href="#" class="upload_header_btn no_header pie">修改头像</a>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:if test="${sessionScope.sessionKeyUserInfo.userName != null}">
                <div class="name">${sessionScope.sessionKeyUserInfo.userName}<a href="#"
                                                                                class="tool_edit_username sprite icon_pencil_s" title="修改昵称"></a>
                </div>
            </c:if>
            <c:if test="${sessionScope.sessionKeyUserInfo.userName == null}">
                <div class="name">${fn:substring(sessionScope.sessionKeyUserInfo.mobileNo,0,5)}...<a href="#"
                                                                                                     class="tool_edit_username sprite icon_pencil_s" title="修改昵称"></a>
                </div>
            </c:if>
            <div class="level">${pointGrade.title}<i class="medal"></i></div>
        </div>

        <ul class="member_nav">
            <c:if test="${isGuide == 1}">
                <li><a href="/portal/services/customer/childpage.html?childIndex=myArticle" class="grey"
                       id="myArticle"><i class="icon icon_sheet"></i>我的文章</a></li>
                <li><a href="/portal/services/customer/childpage.html?childIndex=myGuideMessage" class="grey"
                       id="myGuideMessage"><i class="sprite mc_nav_06"></i>我的消息</a></li>
                <li><a href="/portal/services/customer/childpage.html?childIndex=myMediaLib" class="grey"
                       id="myMediaLib"><i class="icon icon_media"></i>我的媒体库</a></li>
            </c:if>
            <c:if test="${isGuide !=1}">
                <li><a href="/portal/services/customer/childpage.html?childIndex=myTouristMessage" class="grey"
                       id="myTouristMessage"><i class="sprite mc_nav_05"></i>我的消息</a></li>
            </c:if>
            <c:if test="${sessionScope.sessionKeyUserInfo.isModerator == 1&&isGuide==1}">
                <li><a id="moderator" href="/portal/services/customer/childpage.html?childIndex=moderator"><i
                        class="icon_bz"></i>我是版主</a></li>
            </c:if>
            <c:if test="${isGuide == 1}">
                <li><a href="/portal/services/customer/childpage.html?childIndex=accountInfo" id="accountInfo"><i
                        class="sprite mc_nav_01"></i>小金库</a></li>
            </c:if>
            <li><a href="/portal/services/customer/childpage.html?childIndex=accountConfigue" id="accountConfigue"><i
                    class="sprite mc_nav_02"></i>账户设置</a></li>
            <li><span class="lv1"><i class="sprite mc_nav_05"></i>我的订单</span></li>
            <li><a href="/portal/services/customer/childpage.html?childIndex=tripOrder" class="grey"
                   id="tripOrder">出行订单</a></li>
            <li><a href="/portal/services/customer/childpage.html?childIndex=integralOrder" class="grey"
                   id="integralOrder">积分订单</a></li>
            <c:if test="${isGuide == 1}">
                <li><a href="/portal/services/customer/childpage.html?childIndex=deductWithdraw" id="deductWithdraw"><i
                        class="sprite mc_nav_03"></i>账户提现</a></li>
                <li><a href="/portal/services/customer/childpage.html?childIndex=incomeDetail" id="incomeDetail"><i
                        class="sprite mc_nav_04"></i>收支明细</a></li>
            </c:if>
        </ul>
        <c:if test="${isGuide == 0}">
            <a href="javascript:void(0);"
               onclick="intoApplyGuidePage(${sessionScope.sessionKeyUserInfo.isCertification})"
               class="be_guide pie">申请成为导游</a>
        </c:if>
        <c:if test="${isGuide == -1}">
            <a href="javascript:void(0);" class="be_guide pie">导游审核中</a>
        </c:if>
    </div>
    <!-- frame_left end -->

    <div class="frame_right" id="load_doc">
        <!-- 账户信息 -->
        <c:if test="${childIndex == 'accountInfo'}">
            <c:if test="${isGuide == 1}">
                <jsp:include page="/portal/services/guideCenter/accountInfo.html"/>
            </c:if>
            <c:if test="${isGuide == 0}">
                <jsp:include page="./tourist/tourist_account_info.jsp"/>
            </c:if>
        </c:if>
        <!-- 账户设置 -->
        <c:if test="${childIndex == 'accountConfigue'}">
            <jsp:include page="./tourist/tourist_account_info.jsp"/>
        </c:if>
        <!-- 出行订单 -->
        <c:if test="${childIndex == 'tripOrder'}">
            <jsp:include page="trip_order.jsp"/>
        </c:if>
        <!-- 积分订单 -->
        <c:if test="${childIndex == 'integralOrder'}">
            <jsp:include page="integral_order.jsp"/>
        </c:if>
        <!-- 充值提现 -->
        <c:if test="${childIndex == 'deductWithdraw'}">
            <jsp:include page="./tourist/deduct_withdraw.jsp"/>
        </c:if>
        <!-- 收支明细 -->
        <c:if test="${childIndex == 'incomeDetail'}">
            <jsp:include page="income_detail.jsp"/>
        </c:if>
        <!-- 导游消息 -->
        <c:if test="${childIndex == 'myGuideMessage'}">
            <jsp:include page="message.jsp"/>
        </c:if>
        <!-- 我的帖子 -->
        <c:if test="${childIndex == 'myArticle'}">
            <jsp:include page="./guide/myArticle.jsp"/>
        </c:if>
        <!-- 媒体库 -->
        <c:if test="${childIndex == 'myMediaLib'}">
            <jsp:include page="./guide/myMediaLib.jsp"/>
        </c:if>
        <!-- 游客消息 -->
        <c:if test="${childIndex == 'myTouristMessage'}">
            <jsp:include page="message.jsp"/>
        </c:if>
        <!-- 实名认证 -->
        <c:if test="${childIndex == 'certification'}">
            <jsp:include page="./tourist/certification.jsp"/>
        </c:if>
        <!-- 修改手机号码步骤1 -->
        <c:if test="${childIndex == 'resetTelOne'}">
            <jsp:include page="./tourist/reset_mobile_step_one.jsp"/>
        </c:if>
        <!-- 修改手机号码步骤2 -->
        <c:if test="${childIndex == 'resetTelTwo'}">
            <jsp:include page="./tourist/reset_mobile_step_two.jsp"/>
        </c:if>
        <!-- 修改登录密码 -->
        <c:if test="${childIndex == 'resetPwd'}">
            <jsp:include page="./tourist/reset_pwd.jsp"/>
        </c:if>
        <!-- 绑定银行卡 -->
        <c:if test="${childIndex == 'bindingCard'}">
            <jsp:include page="./tourist/binding_card.jsp"/>
        </c:if>
        <c:if test="${childIndex == 'moderator'}">
            <jsp:include page="moderator.jsp"/>
        </c:if>
    </div>
    <!-- frame_right end -->
</div>
<!-- member_frame end -->
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>
<!-- 图片上传弹窗 -->
<div class="modal_upload">
    <div class="upload_wrap">
        <div class="upload_content pie">
            <a href="#" class="sprite icon_close"></a>
            <div class="container">
                <div class="imageBox">
                    <div class="thumbBox"></div>
                    <div class="spinner" style></div>
                </div>
                <div class="action">
                    <div class="new-contentarea tc">
                        <a href="javascript:void(0)" class="upload-img">
                            <label for="upload_file_id">请先选择图片...</label>
                        </a>
                        <input type="file" name="upload-file" id="upload_file_id">
                        <input type="hidden" id="headFilePath"/>
                    </div>
                    <input type="button" id="btnCrop" value="保存" class="Btnsty_peyton2">
                    <input type="button" id="btnZoomIn" value="+" class="Btnsty_peyton">
                    <input type="button" id="btnZoomOut" value="-" class="Btnsty_peyton">
                </div>
                <div class="cropped">
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/cropbox.js" charset="utf-8"></script>
<script>
    $(document).ready(function () {
        // 默认加载页面
        var childIndex = getUrlValue("childIndex");
        $(".member_nav a").each(function (i) {
            $(".member_nav a").removeClass('active');
            $("#" + childIndex).addClass('active');
        });

        // 显示编辑昵称
//        $('.frame_left').hover(
//            function () {
//                $('.tool_edit_username').removeClass('hide');
//            },
//            function () {
//                $('.tool_edit_username').addClass('hide');
//            }
//        );

        // 弹窗：编辑昵称
        $('.tool_edit_username').click(function (e) {
            e.preventDefault();
            layer.open({
                type: 1,
                title: false,
                area: ['400px', '239px'],
                closeBtn: 0,
                shadeClose: true,
                skin: 'popup_edit_username',
                content: '<div class="content_wrap">' +
                '<a href="#" class="close_popup_video"></a>' +
                '<form action="/portal/services/customer/updateUserName.html" method="post" name="form_edit_username" class="form_edit_username form_upload_mvideo" id="edit_userName_id">' +
                '<div class="form_item"><label class="label">用户名：</label>' +
                '<input type="text" placeholder="请输入6字以内昵称" name="userName" class="input" datatype="s2-7" nullmsg="昵称不能为空" errormsg="2-7个字符，支持中英文、数字"/>' +
                '<div class="Validform_checktip"></div></div>' +
                '<input type="submit" value="确定" class="popup_edit_username uni_btn"/>' +
                '</form></div>',
                success: function (layero, index) {
                    $('.close_popup_video').click(function (e) {
                        e.preventDefault();
                        layer.close(index);
                    });
                    $("#edit_userName_id").Validform({
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
                                selector: "select,:checkbox,:radio,.decorate"
                            }
                        },
                        beforeSubmit: function (curform) {
                            loading = top.layer.load(2, {
                                shade: [0.3, '#eee'] //0.1透明度的白色背景
                            });
                        },
                        callback: function (data) {
                            if (data.success) {
                                location = location;
                            } else {
                                $.platform.simplemsg(data.message, 1000, 5);
                            }
                            layer.close(loading);
                        }
                    });
                }
            });
        });
    });

    function intoApplyGuidePage(isCertification) {
//        if (isCertification == 0) {
//            $.platform.msg('请先实名绑卡', 1000, 1);
//            return false;
//        } else {
        window.location.href = '/portal/services/customer/applyGuideStepOne.html'
//        }
    }

    $(window).load(function () {
        var options =
            {
                thumbBox: '.thumbBox',
                spinner: '.spinner',
                imgSrc: ''
            }
        var cropper = $('.imageBox').cropbox(options);
        var img = "";
        $('#upload_file_id').on('change', function () {
            var reader = new FileReader();
            reader.onload = function (e) {
                options.imgSrc = e.target.result;
                cropper = $('.imageBox').cropbox(options);
            }
            reader.readAsDataURL(this.files[0]);
            $('#headFilePath').val(this.files[0].name);
            this.files = null;
            getImg();
        })
        ;
        $('#btnCrop').on('click', function () {
            if ($('#headFilePath').val() == '') {
                $.platform.msg('请上传头像图片', 2000, 2);
                return;
            }
            var image = cropper.getDataURL();
            $.ajax({
                type: 'post',
                url: '/portal/services/customer/uploadHeadPortrait.html',
                dataType: 'json',
                async: true,
                data: {image: image},
                success: function (data) {
                    if (data.success) {
                        top.layer.msg('上传成功！', {icon: 1, time: 2000});
                        location = location;
                    } else {
                        $.platform.msg('上传失败', 2000, 2);
                    }
                }
            });
        })
        function getImg() {
            img = cropper.getDataURL();
            $('.cropped').html('');
            $('.cropped').append('<img src="' + img + '" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
            $('.cropped').append('<img src="' + img + '" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
            $('.cropped').append('<img src="' + img + '" align="absmiddle" style="width:64px;margin-top:4px;border-radius:64px;box-shadow:0px 0px 12px #7E7E7E;" ><p>64px*64px</p>');
        }

        $(".imageBox").on("mouseup", function () {
            getImg();
        });


        $('#btnZoomIn').on('click', function () {
            cropper.zoomIn();
        })
        $('#btnZoomOut').on('click', function () {
            cropper.zoomOut();
        })
    });
</script>
</body>
</html>

