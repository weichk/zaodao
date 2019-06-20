<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<div class="commen_title"><span class="bold">友情链接</span></div>

<ul class="img_links">
    <!-- 第1个和最后一个的样式与中间的不一样 -->
    <c:forEach items="${friends}" var="e" varStatus="s">
        <li><a href="${e.link}" target="_blank"><img src="${mediaRoot}/${e.cover}" alt=""></a></li>
    </c:forEach>
</ul>