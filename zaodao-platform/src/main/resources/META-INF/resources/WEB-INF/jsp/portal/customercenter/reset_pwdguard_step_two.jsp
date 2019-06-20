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

<div class="find_pwd_content apply_guide_content pie">
    <h2 class="find_pwd_t"><span class="yellow">设置密保</span></h2>
    <ul class="find_pwd_nav apply_guide_nav step2">
        <li class="n1">
            <span class="n pie">1</span>
            <p>确认身份</p>
        </li>
        <li class="n2">
            <span class="n pie">2</span>
            <p>修改问题</p>
        </li>
        <li class="n3 disabled">
            <span class="n pie">3</span>
            <p>完成</p>
        </li>
    </ul>
    <div class="apply_guide_wrap">
        <form action="/portal/services/resetPwdguard/stepThree.html" method="post" name="apply_guide_info" class="apply_guide_info">
            <c:forEach items="${secrets}" var="e" varStatus="s">
                <div class="form_item">
                    <label class="label">问题${s.index+1}:${e.content}</label>
                </div>
                <div class="form_item">
                    <label class="label">&nbsp;</label>
                    <input type="text" placeholder="请输入问题1答案" name="secret_${e.id}" datatype="*" nullmsg="请输入问题${s.index+1}答案" class="text">
                    <div class="Validform_checktip"></div>
                </div>
            </c:forEach>
            <input type="submit" name="next_stop_2" value="下一步" class="find_pwd_btn">
        </form>
    </div>
</div>

<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>
<script type="text/javascript" src="/js/platformjs/reset_pwdguard_step_two.js" charset="utf-8"></script>
</body>
</html>