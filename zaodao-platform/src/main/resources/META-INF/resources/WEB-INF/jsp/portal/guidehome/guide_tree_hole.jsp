<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<h2 class="gch_lt"><span>导游树洞</span></h2>
<p class="intro">吐糟导游员带团时的委屈、心酸、不文明行为</p>
<div class="gch_slide_content">
    <div class="item">
        <div class="img">
            <div class="club_slider_07">
                <c:forEach items="${guideTreeHoleList}" var="e" varStatus="s">
                    <div class="slide"><img src="${e.coverThumb}" alt=""></div>
                </c:forEach>
            </div>
        </div>
        <div class="con">
            <ul class="gchl_list">
                <c:forEach items="${guideTreeHoleList}" var="e" varStatus="s">
                    <li><a href="javascript:void(0);" onclick="readArticleInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','${sessionScope.sessionKeyUserInfo.userId}')">${e.title}</a><span class="date"><fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd"/></span></li>
                </c:forEach>
            </ul>
            <div class="ar"><a target="_blank" href="/portal/guideHome/blogList.html?type=guideTreeHole">更多 》</a></div>
        </div>
    </div>
</div>
<!-- 导游树洞 end -->