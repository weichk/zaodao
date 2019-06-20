<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<h2 class="gch_right_t"><span>时事热点</span></h2>
<ul class="gch_news_list">
    <c:forEach items="${lighthouseList}" var="e" varStatus="s">
        <li><a target="_blank" href="javascript:void(0);" onclick="readNewsInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','lighthouse','${sessionScope.sessionKeyUserInfo.userId}')">${e.title}</a></li>
    </c:forEach>
</ul>
<div class="ar"><a target="_blank" href="/portal/news/newsinfoList.html?type=lighthouse">更多 》</a></div>