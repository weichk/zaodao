<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <link rel="stylesheet" href="/css/find_pwd.css"/>
</head>

<body>
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=customerCenter"/>

<!-- header end -->
<div class="find_pwd_content apply_guide_content pie">
    <h2 class="find_pwd_t"><span class="yellow">设置密保</span></h2>
    <ul class="find_pwd_nav apply_guide_nav step1">
        <li class="n1">
            <span class="n pie">1</span>
            <p>确认身份</p>
        </li>
        <li class="n2 disabled">
            <span class="n pie">2</span>
            <p>修改问题</p>
        </li>
        <li class="n3 disabled">
            <span class="n pie">3</span>
            <p>完成</p>
        </li>
    </ul>
    <div class="apply_guide_wrap">
        <form action="/portal/services/resetPwdguard/stepTwo.html" method="post" name="apply_guide_info" class="apply_guide_info">
            <div class="form_item">
                <label for="realname" class="label">登录密码</label>
                <input type="password" name="password" datatype="*2-18" nullmsg="请输入登录密码" errormsg="密码不正确" ajaxurl="/portal/services/customer/verifyUserPassword.html" class="text">
                <div class="Validform_checktip"></div>
            </div>
            <input type="submit" name="next_stop_2" value="下一步" class="find_pwd_btn">
        </form>
    </div>
</div>

<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>
<script type="text/javascript">
    $(function() {
        $(".apply_guide_info").Validform({
            tiptype: function(msg, o, cssctl) {
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
            }
        });
    });
</script>
</body>
</html>