<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<h2 class="gch_lt"><span>带团日志</span></h2>
<p class="intro">每次带团的心得、体会、感悟和大家分享</p>
<div class="gch_slide_tabs gch_tab_2">
    <ul class="nav">
        <li><a class="current" href="#">国内路线</a></li>
        <li><a href="#">出境路线</a></li>
        <li><a href="#">定制路线</a></li>
    </ul>
    <span class="gch_indicator gch_idc_2"></span>
</div>
<div class="gch_slide_content gch_content_2">
    <div class="item">
        <div class="img">
            <div class="club_slider_04">
                <c:forEach items="${domesticRoutesList}" var="e" varStatus="s">
                    <div class="slide"><img src="${e.coverThumb}" alt=""></div>
                </c:forEach>
            </div>
        </div>
        <div class="con">
            <ul class="gchl_list">
                <c:forEach items="${domesticRoutesList}" var="e" varStatus="s">
                    <li><a target="_blank" href="javascript:void(0);" onclick="readArticleInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','${sessionScope.sessionKeyUserInfo.userId}')">${e.title}</a><span class="date"><fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd"/></span></li>
                </c:forEach>
            </ul>
            <div class="ar"><a target="_blank" href="/portal/guideHome/blogList.html?type=experienceTour">更多 》</a></div>
        </div>
    </div>
    <div class="item">
        <div class="img">
            <div class="club_slider_05">
                <c:forEach items="${exitRoutesList}" var="e" varStatus="s">
                    <div class="slide"><img src="${e.coverThumb}" alt=""></div>
                </c:forEach>
            </div>
        </div>
        <div class="con">
            <ul class="gchl_list">
                <c:forEach items="${exitRoutesList}" var="e" varStatus="s">
                    <li><a href="javascript:void(0);" onclick="readArticleInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','${sessionScope.sessionKeyUserInfo.userId}')">${e.title}</a><span class="date"><fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd"/></span></li>
                </c:forEach>
            </ul>
            <div class="ar"><a target="_blank" href="/portal/guideHome/blogList.html?type=experienceTour">更多 》</a></div>
        </div>
    </div>
    <div class="item">
        <div class="img">
            <div class="club_slider_06">
                <c:forEach items="${customRouteList}" var="e" varStatus="s">
                    <div class="slide"><img src="${e.coverThumb}" alt=""></div>
                </c:forEach>
            </div>
        </div>
        <div class="con">
            <ul class="gchl_list">
                <c:forEach items="${customRouteList}" var="e" varStatus="s">
                    <li><a target="_blank" href="javascript:void(0);" onclick="readArticleInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','${sessionScope.sessionKeyUserInfo.userId}')">${e.title}</a><span class="date"><fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd"/></span></li>
                </c:forEach>
            </ul>
            <div class="ar"><a target="_blank" href="/portal/guideHome/blogList.html?type=experienceTour">更多 》</a></div>
        </div>
    </div>
</div>
<!-- 带团日志 end -->