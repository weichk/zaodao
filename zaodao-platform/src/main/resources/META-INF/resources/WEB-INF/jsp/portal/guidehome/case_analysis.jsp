<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<h2 class="gch_lt"><span>案例分析</span></h2>
<p class="intro">导游带团案例分析，与投诉有关的话题</p>
<div class="gch_slide_tabs gch_tab_3">
    <ul class="nav">
        <li><a class="current" href="#">带团案例</a></li>
        <li><a href="#">投诉案例</a></li>
        <li><a href="#">问题处理</a></li>
    </ul>
    <span class="gch_indicator gch_idc_3"></span>
</div>
<div class="gch_slide_content gch_content_3">
    <div class="item">
        <div class="img">
            <div class="club_slider_08">
                <c:forEach items="${caseTourList}" var="e" varStatus="s">
                    <div class="slide"><img src="${e.coverThumb}" alt=""></div>
                </c:forEach>
            </div>
        </div>
        <div class="con">
            <ul class="gchl_list">
                <c:forEach items="${caseTourList}" var="e" varStatus="s">
                    <li><a target="_blank" href="javascript:void(0);" onclick="readArticleInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','${sessionScope.sessionKeyUserInfo.userId}')">${e.title}</a><span class="date"><fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd"/></span></li>
                </c:forEach>
            </ul>
            <div class="ar"><a target="_blank" href="/portal/guideHome/blogList.html?type=caseAnalysis">更多 》</a></div>
        </div>
    </div>
    <div class="item">
        <div class="img">
            <div class="club_slider_09">
                <c:forEach items="${complaintCaseList}" var="e" varStatus="s">
                    <div class="slide"><img src="${e.coverThumb}" alt=""></div>
                </c:forEach>
            </div>
        </div>
        <div class="con">
            <ul class="gchl_list">
                <c:forEach items="${complaintCaseList}" var="e" varStatus="s">
                    <li><a target="_blank" href="javascript:void(0);" onclick="readArticleInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','${sessionScope.sessionKeyUserInfo.userId}')">${e.title}</a><span class="date"><fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd"/></span></li>
                </c:forEach>
            </ul>
            <div class="ar"><a target="_blank" href="/portal/guideHome/blogList.html?type=guideTreeHole">更多 》</a></div>
        </div>
    </div>
    <div class="item">
        <div class="img">
            <div class="club_slider_10">
                <c:forEach items="${problemSolvingList}" var="e" varStatus="s">
                    <div class="slide"><img src="${e.coverThumb}" alt=""></div>
                </c:forEach>
            </div>
        </div>
        <div class="con">
            <ul class="gchl_list">
                <c:forEach items="${problemSolvingList}" var="e" varStatus="s">
                    <li><a target="_blank" href="javascript:void(0);" onclick="readArticleInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','${sessionScope.sessionKeyUserInfo.userId}')">${e.title}</a><span class="date"><fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd"/></span></li>
                </c:forEach>
            </ul>
            <div class="ar"><a target="_blank" href="/portal/guideHome/blogList.html?type=caseAnalysis">更多 》</a></div>
        </div>
    </div>
</div>
<!-- 案例分析 end -->