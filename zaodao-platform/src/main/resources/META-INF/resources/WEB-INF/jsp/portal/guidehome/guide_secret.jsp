<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<h2 class="gch_lt"><span>导游秘籍</span></h2>
<p class="intro">全国优秀导游员、资深导游员导游词、带团技巧在此</p>
<div class="gch_slide_tabs gch_tab_1">
    <ul class="nav">
        <li><a class="current" href="#">导游词</a></li>
        <li><a href="#">带团技巧</a></li>
        <li><a href="#">导游培训</a></li>
    </ul>
    <span class="gch_indicator gch_idc_1"></span>
</div>
<div class="gch_slide_content gch_content_1">
    <div class="item">
        <div class="img">
            <div class="club_slider_01">
                <c:forEach items="${guideWordList}" var="e" varStatus="s">
                    <div class="slide"><img src="${e.coverThumb}" alt=""></div>
                </c:forEach>
            </div>
        </div>
        <div class="con">
            <ul class="gchl_list">
                <c:forEach items="${guideWordList}" var="e" varStatus="s">
                    <li><a target="_blank" href="javascript:void(0);"
                           onclick="readArticleInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','${sessionScope.sessionKeyUserInfo.userId}')">${e.title}</a><span
                            class="date"><fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd"/></span></li>
                </c:forEach>
            </ul>
            <div class="ar"><a target="_blank" href="/portal/guideHome/blogList.html?type=guideSecret">更多 》</a></div>
        </div>
    </div>
    <div class="item">
        <div class="img">
            <div class="club_slider_02">
                <c:forEach items="${skillsTourList}" var="e" varStatus="s">
                    <div class="slide"><img src="${e.coverThumb}" alt=""></div>
                </c:forEach>
            </div>
        </div>
        <div class="con">
            <ul class="gchl_list">
                <c:forEach items="${skillsTourList}" var="e" varStatus="s">
                    <li><a target="_blank" href="javascript:void(0);"
                           onclick="readArticleInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','${sessionScope.sessionKeyUserInfo.userId}')">${e.title}</a><span
                            class="date"><fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd"/></span></li>
                </c:forEach>
            </ul>
            <div class="ar"><a target="_blank" href="/portal/guideHome/blogList.html?type=guideSecret">更多 》</a></div>
        </div>
    </div>
    <div class="item">
        <div class="img">
            <div class="club_slider_03">
                <c:forEach items="${guideTrainingList}" var="e" varStatus="s">
                    <div class="slide"><img src="${e.coverThumb}" alt=""></div>
                </c:forEach>
            </div>
        </div>
        <div class="con">
            <ul class="gchl_list">
                <c:forEach items="${guideTrainingList}" var="e" varStatus="s">
                    <li><a href="javascript:void(0);"
                           onclick="readArticleInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','${sessionScope.sessionKeyUserInfo.userId}')">${e.title}</a><span
                            class="date"><fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd"/></span></li>
                </c:forEach>
            </ul>
            <div class="ar"><a target="_blank" href="/portal/guideHome/blogList.html?type=guideSecret">更多 》</a></div>
        </div>
    </div>
</div>
<!-- 导游秘籍 end -->