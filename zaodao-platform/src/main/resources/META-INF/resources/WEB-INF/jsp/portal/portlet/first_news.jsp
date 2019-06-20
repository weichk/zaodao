<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>

<div class="home_box">
    <div class="home_title0809"><span class="name pie">名导之约</span>
        <a target="_blank" href="/portal/search.html" class="share pie">精英导游汇精彩讲解来，我要约导~【搜索】<i
                class="heart"></i></a>
    </div>
    <ul class="home_guider_list">
        <c:forEach items="${famousGuides}" var="e" varStatus="s">
            <li><a target="_blank" href="/portal/guide/detail/${e.userId}"><img src="${e.headImg}" alt=""><span
                    class="name">${e.realName}</span></a></li>
        </c:forEach>
    </ul>
</div>
<!-- 精华热帖 -->
<div class="home_box">
    <div class="home_title0809"><span class="name pie">精华热帖</span>
        <c:if test="${sessionScope.sessionKeyUserInfo != null}">
            <c:if test="${sessionScope.sessionKeyUserInfo.isTourGuide != 1}">
                <a href="javascript:$.platform.msg('请申请为导游', 1000, 5);" class="share pie">记录导游生涯点点滴滴，酸甜苦辣~【发帖】~<i
                        class="heart"></i></a>
            </c:if>
            <c:if test="${sessionScope.sessionKeyUserInfo.isTourGuide == 1}">
                <a target="_blank" href="/portal/services/guideCenter/writeBlog.html" class="share pie">记录导游生涯点点滴滴，酸甜苦辣~【发帖】<i
                        class="heart"></i></a>
            </c:if>
        </c:if>
        <c:if test="${sessionScope.sessionKeyUserInfo == null}">
            <a href="/portal/portlet/login_register.html" class="share pie login_in">记录导游生涯点点滴滴，酸甜苦辣~【发帖】<i
                    class="heart"></i></a>
        </c:if>
    </div>
    <div class="home_in_wrap">
        <div class="home_imgnews_1">
            <div class="img"><img src="/images/home_news_img_080901.jpg" alt=""></div>
            <div class="con">
                <c:forEach items="${hotArticles}" var="e" varStatus="s">
                    <c:if test="${s.index == 0}">
                        <div class="top_news">
                            <h2><a target="_blank"
                                   href="/portal/guideHome/blogList.html?type=${e.label.type}&lable=${e.label.code}"
                                   class="type">${e.label.message}</a>
                                <a target="_blank" href="/portal/guideHome/getArticleInfo_${e.id}.html"
                                   class="news_t">${e.title}</a>
                                <span class="time">${e.createTimeStr}</span>
                            </h2>
                            <p class="summary">
                                    ${e.content}</p>
                            <div class="f">
                                <span class="author"><img src="${e.headImg}" alt=""
                                                          class="pie">${e.userName}</span>
                                <span class="view"><i></i>${e.readCount}</span>
                                <span class="msg"><i></i>${e.praiseCount}</span>
                            </div>
                        </div>
                    </c:if>
                    <ul class="home_news_list_1">
                        <c:if test="${s.index > 0&&s.index<7}">
                            <li><a target="_blank"
                                   href="/portal/guideHome/blogList.html?type=${e.label.type}&lable=${e.label.code}"
                                   class="type">${e.label.message}</a>
                                <a target="_blank" href="/portal/guideHome/getArticleInfo_${e.id}.html"
                                   class="news_t">${e.title}</a>
                                <span class="date">${e.createTimeStr}</span>
                            </li>
                        </c:if>
                    </ul>
                </c:forEach>
            </div>
        </div>

        <ul class="home_news_list_2">
            <c:forEach items="${hotArticles}" var="e" varStatus="s">
                <c:if test="${s.index >= 7}">
                    <li><a href="javascript:void(0);" class="img"><img src="${e.headImg}" alt=""></a>
                        <a href="/portal/guideHome/getArticleInfo_${e.id}.html" class="t">${e.title}</a><span
                                class="info">
                        <i class="tag">
                        <c:if test="${not empty e.stampCode}">
                            <img src="/images/stamp/${e.stampCode}_small.png" alt="${e.stampCode}">
                        </c:if>
                        </i>
                        <span class="view"><i></i>${e.readCount}</span>
                        <span class="msg"><i></i>${e.praiseCount}</span>
                    </span>
                    </li>
                </c:if>
            </c:forEach>
        </ul>

    </div>
</div>

<!-- 旅情播报 -->
<div class="home_box">
    <div class="home_title0809">
        <span class="name pie">旅情播报</span>
        <a target="_blank" href="/portal/news/newsinfoList.html?type=news" class="share pie">把握旅游业脉络，分享第一手资讯~【详情】<i
                class="heart"></i></a>
    </div>
    <div class="home_in_wrap mb40">
        <div class="home_imgnews_2">
            <div class="img"><img src="/images/home_news_img_080902.jpg" alt=""></div>
            <div class="con">
                <c:forEach items="${tradeNewsList}" var="e" varStatus="s">
                    <c:if test="${s.index == 0}">
                        <div class="top_news">
                            <h2><a href="/portal/news/newsInfo.html?id=${e.id}&type=news">${e.title}</a></h2>
                            <p class="summary">${e.subject}</p>
                            <a href="/portal/news/newsInfo.html?id=${e.id}&type=news" class="detail">全文》</a>
                        </div>
                    </c:if>
                    <ul class="home_news_list_3">
                        <c:if test="${s.index > 0}">
                            <li><a href="/portal/news/newsInfo.html?id=${e.id}&type=news">${e.title}</a></li>
                        </c:if>
                    </ul>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<!-- 导游论坛新闻 end -->

<%--<!-- 友情链接 -->--%>
<%--<jsp:include page="/portal/portlet/friend.html?count=10"/>--%>

<script type="text/javascript">
    function goWriteBlog() {
        var guide =
        ${sessionScope.sessionKeyUserInfo.isTourGuide != 1}
    }
</script>