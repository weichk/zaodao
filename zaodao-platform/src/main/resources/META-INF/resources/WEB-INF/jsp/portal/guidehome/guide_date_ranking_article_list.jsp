<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<h2 class="gch_right_t"><span>话题排行</span></h2>
<div class="gch_slide_tabs gch_tab_4">
    <ul class="nav">
        <li><a class="current" href="#">日</a></li>
        <li><a href="#">周</a></li>
        <li><a href="#">月</a></li>
    </ul>
    <span class="gch_indicator gch_idc_4"></span>
</div>
<div class="gch_slide_content gch_content_4">
    <div class="item">
        <ul class="hot_topic">
            <c:forEach items="${dayArticleList}" var="e" varStatus="s">
                <li>
                    <i class="n">${s.index+1}</i>
                    <a target="_blank" href="javascript:void(0);" onclick="readArticleInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','${sessionScope.sessionKeyUserInfo.userId}')" class="t">${e.title}</a>
                    <div class="info"><span class="date"><fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd"/></span><span class="view"><i class="sprite icon_eye_bl"></i>${e.readCount}</span></div>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="item">
        <ul class="hot_topic">
            <c:forEach items="${weekArticleList}" var="e" varStatus="s">
                <li>
                    <i class="n">${s.index+1}</i>
                    <a target="_blank" href="javascript:void(0);" onclick="readArticleInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','${sessionScope.sessionKeyUserInfo.userId}')" class="t">${e.title}</a>
                    <div class="info"><span class="date"><fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd"/></span><span class="view"><i class="sprite icon_eye_bl"></i>${e.readCount}</span></div>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="item">
        <ul class="hot_topic">
            <c:forEach items="${monthArticleList}" var="e" varStatus="s">
                <li>
                    <i class="n">${s.index+1}</i>
                    <a href="javascript:void(0);" onclick="readArticleInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','${sessionScope.sessionKeyUserInfo.userId}')" class="t">${e.title}</a>
                    <div class="info"><span class="date"><fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd"/></span><span class="view"><i class="sprite icon_eye_bl"></i>${e.readCount}</span></div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>