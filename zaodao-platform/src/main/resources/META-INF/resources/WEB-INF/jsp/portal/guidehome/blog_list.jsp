<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <link rel="stylesheet" href="/css/news.css"/>
</head>

<body class="grey_bg">
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=guideHome"/>

<div class="news_content wrap">
    <div class="breadcrum"><a href="/portal/guideHome/guideHomeIndex.html">导游之家</a> >
        <c:if test="${type == 'guideSecret'}">
            导游秘籍
        </c:if>
        <c:if test="${type == 'experienceTour'}">
            带团日志
        </c:if>
        <c:if test="${type == 'guideTreeHole'}">
            导游树洞
        </c:if>
        <c:if test="${type == 'caseAnalysis'}">
            案例分析
        </c:if>
    </div>

    <div class="blog_header">
        <div class="img"><img src="/images/blog_header.jpg" alt=""></div>
        <div class="info">
            <c:if test="${type == 'guideSecret'}">
                <div class="name">导游秘籍</div>
                <p>全国优秀导游员、资深导游员导游词、带团技巧在此</p>
            </c:if>
            <c:if test="${type == 'experienceTour'}">
                <div class="name">带团日志</div>
                <p>每次带团的心得、体会、感悟和大家分享</p>
            </c:if>
            <c:if test="${type == 'guideTreeHole'}">
                <div class="name">导游树洞</div>
                <p>吐糟导游员带团时的委屈、心酸、不文明行为</p>
            </c:if>
            <c:if test="${type == 'caseAnalysis'}">
                <div class="name">案例分析</div>
                <p>导游带团案例分析，与投诉有关的话题</p>
            </c:if>
        </div>
        <div class="r">
            <a href="javascript:void(0)" onclick="goWritBlog()" class="uni_btn pie">发表新帖</a>
            <%--<p class="belong">所属：--%>
            <%--<c:if test="${type == 'guideSecret'}">--%>
            <%--导游秘籍--%>
            <%--</c:if>--%>
            <%--<c:if test="${type == 'experienceTour'}">--%>
            <%--带团日志--%>
            <%--</c:if>--%>
            <%--<c:if test="${type == 'experienceTour'}">--%>
            <%--导游树洞--%>
            <%--</c:if>--%>
            <%--<c:if test="${type == 'caseAnalysis'}">--%>
            <%--案例分析--%>
            <%--</c:if>--%>
            <%--</p>--%>
        </div>
    </div>
    <!-- header end -->

    <div class="w730">
        <div class="news_wrap">
            <c:if test="${type == 'guideSecret'}">
                <div class="commen_title">
                    <a href="javascript:void(0);" onclick="getArticleList(this,'guideWord')" name="guideWord"
                       class="yellow">导游词</a>
                    <a href="javascript:void(0);" onclick="getArticleList(this,'skillsTour')" name="skillsTour">带团技巧</a><a
                        href="javascript:void(0);" onclick="getArticleList(this,'guideTraining')"
                        name="guideTraining">导游培训</a></div>
            </c:if>
            <c:if test="${type == 'experienceTour'}">
                <div class="commen_title">
                    <a href="javascript:void(0);" onclick="getArticleList(this,'domesticRoutes')" name="domesticRoutes"
                       class="yellow">国内路线</a>
                    <a href="javascript:void(0);" onclick="getArticleList(this,'exitRoutes')" name="exitRoutes">出境路线</a><a
                        href="javascript:void(0);" onclick="getArticleList(this,'customRoute')"
                        name="customRoute">定制路线</a></div>
            </c:if>
            <c:if test="${type == 'caseAnalysis'}">
                <div class="commen_title">
                    <a href="javascript:void(0);" onclick="getArticleList(this,'caseTour')" name="caseTour"
                       class="yellow">带团案例</a>
                    <a href="javascript:void(0);" onclick="getArticleList(this,'complaintCase')"
                       name="complaintCase">投诉案例</a>
                    <a href="javascript:void(0);" onclick="getArticleList(this,'problemSolving')"
                       name="problemSolving">问题处理</a></div>
            </c:if>
            <input type="hidden" id="lable_input_id">
            <%--<form action="#" name="news_filter" class="news_sort mt20">--%>
            <%--<select name="topic" id="topic" class="topic">--%>
            <%--<option value="1">全部主题</option>--%>
            <%--<option value="2">主题1</option>--%>
            <%--<option value="3">主题2</option>--%>
            <%--</select>--%>
            <%--<select name="date" id="date" class="date">--%>
            <%--<option value="1">全部时间</option>--%>
            <%--<option value="2">今天</option>--%>
            <%--<option value="3">昨天</option>--%>
            <%--</select>--%>
            <%--<select name="sort" id="sort" class="sort">--%>
            <%--<option value="1">默认排序</option>--%>
            <%--<option value="2">升序</option>--%>
            <%--<option value="3">降序</option>--%>
            <%--</select>--%>
            <%--</form>--%>
            <div id="article_list_id">

            </div>
        </div>

    </div>
    <!-- news left list end -->

    <div class="w260">
        <div class="news_side_wrap">
            <div class="news_side_t">公告</div>
            <ul class="news_side_list">
                <c:forEach items="${noticeList}" var="e" varStatus="s">
                    <li><a href="javascript:void(0);"
                           onclick="readNewsInfo('${e.id}','${sessionScope.sessionKeyUserInfo.isTourGuide}','notice')">${e.title}</a>
                    </li>
                </c:forEach>
            </ul>
            <div class="ar"><a href="/portal/news/newsinfoList.html?type=notice">更多 》</a></div>
        </div>

        <div class="news_side_wrap nav_index">
            <div class="news_side_t">版块导航</div>

            <ul class="nav_index_list">
                <li><a href="/portal/guideHome/blogList.html?type=guideSecret">导游秘籍</a></li>
                <li><a href="/portal/guideHome/blogList.html?type=experienceTour">带团日志</a></li>
                <li><a href="/portal/guideHome/blogList.html?type=guideTreeHole">导游树洞</a></li>
                <li><a href="/portal/guideHome/blogList.html?type=caseAnalysis">案例分析</a></li>
            </ul>
        </div>
    </div>
    <!-- news side end -->

</div>
<!-- main content end -->
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>

<!-- 系统消息模板-->
<script id="articleList-template" type="text/html">
    <ul class="blog_list mt10">
        {{#each rows}}
        <li>
            <a href="#" class="img"><img src="{{headImg}}" alt=""></a>
            <div class="con">
                <a target="_blank" href="javascript:void(0);"
                   onclick="readArticleInfo('{{articleId}}','${sessionScope.sessionKeyUserInfo.isTourGuide}','${sessionScope.sessionKeyUserInfo.userId}')"
                   class="t">
                    {{#myif essenceStatus '==' '1' }}
                    <span class="stick_great">精</span>
                    {{/myif}}
                    {{#myif upStatus '==' '1' }}
                    <span class="stick_top">置顶</span>
                    {{/myif}}
                    {{title}}
                    {{#if havaRedPacket}}
                    <i class="redpacket"></i>
                    {{/if}}
                </a>
                <div class="info">
                    <span class="name">{{userName}}<i class="medal"></i></span>
                    <span class="date">{{createTime}}</span>
                    <div class="r"><span class="reply"><i class="sprite icon_dial_bl"></i>{{reviewCount}}</span><span
                            class="view"><i
                            class="sprite icon_eye_bl"></i>{{readCount}}</span></div>
                </div>
            </div>
        </li>
        {{/each}}
    </ul>
    <div class="pages" id="blogArticlePageId">

    </div>
</script>
<script type="text/javascript">
    function goWritBlog() {
        if (${sessionScope.sessionKeyUserInfo.isShutup==1}) {
            $.platform.simplemsg("您已被禁言，请联系管理员！", 1000, 5);
            return;
        }
        window.open("/portal/services/guideCenter/writeBlog.html");
    }
    $(document).ready(function () {
        var type = '${type}';
        var lable = '${lable}';
        console.info(type);
        console.info(lable);
        if (type == 'guideSecret') {
            $('#lable_input_id').val('guideWord');
        } else if (type == 'experienceTour') {
            $('#lable_input_id').val('domesticRoutes');
        } else if (type == 'guideTreeHole') {
            $('#lable_input_id').val('');
        } else {
            $('#lable_input_id').val('caseTour');
        }
        if (lable != null && lable != "") {
            $('#lable_input_id').val(lable);
            $('.commen_title').children('a').removeClass("yellow");
            $('.commen_title').children("a[name='" + lable + "']").addClass("yellow");
        }
        getBlogArticleList(1, 1);
    });

    $(function () {
        $('.news_sort').jqTransform({imgPath: 'js/jqtransformplugin/img/'});
    });


    function readNewsInfo(newsId, isGuide, type) {
        if (isGuide != 1) {
            $.platform.msg("请上传导游证", 2000, 5);
        } else {
            window.location.href = "/portal/news/newsInfo.html?id=" + newsId + "&type=" + type;
        }
    }


    function getArticleList(obj, lable) {
        $('#lable_input_id').val(lable);
        $('.commen_title').children('a').removeClass("yellow");
        $(obj).addClass("yellow");
        getBlogArticleList(1, 1);
    }
    /**
     * 分页获取回复消息
     * @type {*}
     */
    var articleListSource = $("#articleList-template").html();
    var articleListTemplate = Handlebars.compile(articleListSource);
    var articleListContext;
    function getBlogArticleList(totalPageNo, pageNo) {
        var articleType = '${type}';
        var lable = $('#lable_input_id').val();
        var countOfCurrentPage = 10;
        if (totalPageNo < pageNo || pageNo < 1) {
            return;
        }
        $.ajax({
            type: "post",
            url: "/portal/guideHome/getBlogArticleList.html",
            dataType: 'json',
            async: true,
            data: {"currentPageNo": pageNo, "articleType": articleType, "lable": lable},
            beforeSend: function (XHR) {
                loading = top.layer.load(2, {
                    shade: [0.3, '#eee'] //0.1透明度的白色背景
                });
            },
            error: function (data) {
                $.platform.msg('获取文章列表失败', 1000, 2);
                return false;
            },
            success: function (data) {
                articleListContext = data;
                var articleListHtml = articleListTemplate(articleListContext);
                $('#article_list_id').html(articleListHtml);
                //分页
                var totalPage = data.total % countOfCurrentPage == 0 ? data.total / countOfCurrentPage : Math.ceil(data.total / countOfCurrentPage);
                if (totalPage > 1) {
                    pageReplyMessageFunction(totalPage, pageNo);
                }
                layer.closeAll();
            },
        });
    }
    ;

    //分页
    function pageReplyMessageFunction(totalPages, currentPage) {
        $("#blogArticlePageId").jqPaginator({
            totalPages: totalPages,
            visiblePages: 10,
            currentPage: currentPage,
            first: '<a href="javascript:void(0);" onclick="getBlogArticleList(1,1)">导游论坛<\/a>',
            prev: '<a href="javascript:void(0);" onclick="getBlogArticleList({{totalPages}},{{page}})">上一页<\/a>',
            next: '<a href="javascript:void(0);" onclick="getBlogArticleList({{totalPages}},{{page}})">下一页<\/a>',
            last: '<a href="javascript:void(0);" onclick="getBlogArticleList({{totalPages}},{{totalPages}})">末页<\/a>',
            page: '<a href="javascript:void(0);" onclick="getBlogArticleList({{totalPages}},{{page}})">{{page}}<\/a>',
            onPageChange: function (n) {
                $("#demo1-text").html("当前第" + n + "页");
            }
        });
    }
    ;

    function readArticleInfo(articleId, isGuide, loginInfo) {
        if (loginInfo == '') {
            $.platform.msg("请登录", 2000, 5);
        } else if (isGuide != 1) {
            $.platform.msg("请上传导游证", 2000, 5);
        } else {
            window.location.href = "/portal/guideHome/getArticleInfo_" + articleId + ".html";
        }
    }
</script>
</body>
</html>

