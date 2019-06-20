<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<div class="my_article page_wrap">
    <div class="mslide_tabs">
        <ul class="nav">
            <li><a class="current" href="#">我的文章</a></li>
            <li><a href="#">我的草稿</a></li>
            <li><a href="#">我的收藏</a></li>
        </ul>
        <span class="m_indicator"></span>
    </div>
    <div class="left">
        <div class="mslide_content mt30">
            <div class="item">
                <div class="tools">
                    <a href="javascript:void(0);" onclick="goWritBlog()" class="write_blog uni_btn pie"><i
                            class="sprite icon_pencil_w"></i>写帖子</a>
                </div>

                <div id="myArticleContent"></div>
            </div>
            <!-- 我的文章结束 -->
            <div class="item">
                <div id="myDraftContent"></div>
            </div>
            <!-- 我的草稿结束 -->
            <div class="item">
                <div id="myEnshrineContent">
                </div>
            </div>
            <!-- 我的收藏结束 -->
        </div>
    </div>
    <!-- left end -->

    <div class="right">
        <div class="t"><i></i>帖子分类</div>
        <div id="articleCount"></div>
        <form action="#" method="post" name="article_sch" class="article_sch mt40">
            <input type="text" name="article_key" placeholder="请输入关键字" class="input_txt" id="articleKey"/>
            <input type="button" name="article_sch_btn" class="article_sch_btn" id="searchArticle">
        </form>
    </div>
    <!-- right end -->
</div>
<!-- list: 文章统计 -->
<script id="articleCount-template" type="text/html">
    <ul class="type_nav">
        <li><a href="#">全部帖子</a><span class="num">{{data.totalCount}}</span></li>

        {{#each rows}}
        <li><a href="#">{{articleTypeMsg}}</a><span class="num">{{count}}</span></li>
        {{/each}}
    </ul>
</script>
<!-- list: 我的文章 -->
<script id="articleList-template" type="text/html">
    <ul class="list">
        {{#each rows}}
        <li>
            <a href="/portal/guideHome/getArticleInfo_{{id}}.html" class="link">{{title}}</a>
            <div class="info">
                <div class="date">{{createTimeStr}}</div>
                <div class="mt15">
                    <span class="reply_num"><i class="sprite icon_dial_bl"></i>{{praiseCount}}</span>
                    <span class="view_num"><i class="sprite icon_eye_bl"></i>{{readCount}}</span>
                </div>
            </div>
            <a href="/portal/guideHome/getArticleInfo_{{id}}.html" class="edit">查看</a>
        </li>
        {{/each}}
    </ul>

    <div class="pages mt40 ac" id="pageInfoDiv">
        <%--<span class="first">导游论坛</span>--%>
        <%--<span class="active">1</span>--%>
        <%--<a href="#">2</a>--%>
        <%--<a href="#" class="next">下一页</a>--%>
        <%--<a href="#" class="last">尾页</a>--%>
    </div>
</script>

<!-- list: 我的草稿 -->
<script id="draftList-template" type="text/html">
    <ul class="list draft_list">
        {{#each rows}}
        <li>
            <a href="/portal/services/guideCenter/writeBlog.html?articleId={{id}}" class="link">{{title}}</a>
            <div class="date">保存于：{{createTime}}</div>
            <div class="manage_draft">
                <a href="/portal/services/guideCenter/writeBlog.html?articleId={{id}}" class="edit_draft">编辑</a>
                <a href="#" onclick="deleteDraft({{id}})" class="delete_draft">删除</a>
            </div>
        </li>
        {{/each}}
    </ul>
    <div class="pages mt40 ac" id="draftPageInfoDiv"></div>
</script>

<!-- list: 我的收藏 -->
<script id="enshrineList-template" type="text/html">
    <ul class="list">
        {{#each rows}}
        <li>
            <a href="/portal/guideHome/getArticleInfo_{{id}}.html" class="link">{{title}}</a>
            <div class="info">
                <div class="date">{{createTimeStr}}</div>
                <div class="mt15">
                    <span class="reply_num"><i class="sprite icon_dial_bl"></i>{{praiseCount}}</span>
                    <span class="view_num"><i class="sprite icon_eye_bl"></i>{{readCount}}</span>
                </div>
            </div>
            <a href="#" onclick="deleteEnshrine({{id}})"  class="edit">删除</a>
        </li>
        {{/each}}
    </ul>

    <div class="pages mt40 ac" id="enshrinePageInfoDiv"></div>
</script>

<script type="text/javascript">

    function goWritBlog() {
        if (${sessionScope.sessionKeyUserInfo.isShutup==1}) {
            $.platform.simplemsg("您已被禁言，请联系管理员！", 1000, 5);
            return;
        }
        window.open("/portal/services/guideCenter/writeBlog.html");
    }

    $("#searchArticle").click(function (e) {
        e.preventDefault();
        if ($("#articleKey").val() == "") {
            return;
        }
        initArticleList(1, 1, $("#articleKey").val());
        initDraftList(1, 1, $("#articleKey").val());
        initEnshrineList(1, 1, $("#articleKey").val());
        initArticleCount();
    })
    function deleteDraft(id) {
        layer.msg('确定删除草稿么？', {
            time: 0 //不自动关闭
            , skin: 'alarm__popup'
            , shade: .5
            , scrollbar: false
            , shadeClose: true
            , btn: ['删除', '取消']
            , yes: function (index) {
                $.ajax({
                    type: "POST",
                    url: "/portal/services/guideCenter/deleteArticle.html",
                    data: {"articleId": id},// 要提交的表单
                    success: function (data) {
                        if (data.success) {
                            layer.close(index);
                            layer.msg('草稿已删除');
                            initDraftList(1, 1, "");
                        } else {
                            layer.msg(data.message);
                        }
                    }
                });
            }
        });
    }

    function deleteEnshrine(id) {
        layer.msg('确定取消收藏？', {
            time: 0 //不自动关闭
            , skin: 'alarm__popup'
            , shade: .5
            , scrollbar: false
            , shadeClose: true
            , btn: ['删除', '取消']
            , yes: function (index) {
                $.ajax({
                    type: "POST",
                    url: "/portal/articleInfo/cancelEnshrine.html",
                    data: {"id": id},// 要提交的表单
                    success: function (data) {
                        if (data.success) {
                            layer.close(index);
                            layer.msg('已取消收藏');
                            initEnshrineList(1, 1, "");
                        } else {
                            layer.msg(data.message);
                        }
                    }
                });
            }
        });
    }
    $(document).ready(function () {
        initArticleList(1, 1, "");
        initDraftList(1, 1, "");
        initEnshrineList(1, 1, "");
        initArticleCount();
    })

    function initArticleCount() {
        var articleCountSource = $("#articleCount-template").html();
        var articleCountTemplate = Handlebars.compile(articleCountSource);
        $.ajax({
            type: "get",
            url: "/portal/services/guideCenter/articleCount.html",
            dataType: 'json',
            async: true,
            data: {},
            beforeSend: function (curform) {
                loading = top.layer.load(2, {
                    shade: [0.3, '#eee'] //0.1透明度的白色背景
                });
            },
            complete: function () {
                layer.closeAll();
            },
            error: function (data) {
                return false;
            },
            success: function (data) {
                var listHtml = articleCountTemplate(data);
                $('#articleCount').html(listHtml);
            },
        });
    }
    /**
     * 获取页面数据（文章）
     * @type {any}
     */
    var articleListSource = $("#articleList-template").html();
    var listTemplate = Handlebars.compile(articleListSource);
    function initArticleList(totalPageNo, pageNo, keyWords) {
        var pageSize = 5;
        if (totalPageNo < pageNo || pageNo < 1) {
            return;
        }
        $.ajax({
            type: "post",
            url: "/portal/services/guideCenter/articleList.html?articleStatus=published",
            dataType: 'json',
            async: true,
            data: {"pageNo": pageNo, "pageSize": pageSize, "title": keyWords},
            beforeSend: function (XHR) {
                loading = top.layer.load(2, {
                    shade: [0.3, '#eee'] //0.1透明度的白色背景
                });
            },
            error: function (data) {
                alert("Connection error");
                return false;
            },
            success: function (data) {
                console.info(data);
                var articleListHtml = listTemplate(data);
                $('#myArticleContent').html(articleListHtml);
                //分页
                var totalPage = data.total % pageSize == 0 ? data.total / pageSize : Math.ceil(data.total / pageSize);
                if (totalPage > 1) {
                    pageFunction(totalPage, pageNo);
                }

                layer.closeAll();
            },
        });
    }

    //分页
    function pageFunction(totalPages, currentPage) {
        $("#pageInfoDiv").jqPaginator({
            totalPages: totalPages,
            visiblePages: 10,
            currentPage: currentPage,
            first: '<a href="javascript:void(0);" onclick="initArticleList(1,1)">导游论坛<\/a>',
            prev: '<a href="javascript:void(0);" onclick="initArticleList({{totalPages}},{{page}})">上一页<\/a>',
            next: '<a href="javascript:void(0);" onclick="initArticleList({{totalPages}},{{page}})">下一页<\/a>',
            last: '<a href="javascript:void(0);" onclick="initArticleList({{totalPages}},{{totalPages}})">末页<\/a>',
            page: '<a href="javascript:void(0);" onclick="initArticleList({{totalPages}},{{page}})">{{page}}<\/a>',
            onPageChange: function (n) {
                $("#demo1-text").html("当前第" + n + "页");
            }
        });
    }

    /**
     * 获取页面数据（草稿）
     * @type {any}
     */
    var draftListSource = $("#draftList-template").html();
    var draftListTemplate = Handlebars.compile(draftListSource);
    function initDraftList(totalPageNo, pageNo, keyWords) {
        var pageSize = 5;
        if (totalPageNo < pageNo || pageNo < 1) {
            return;
        }
        $.ajax({
            type: "post",
            url: "/portal/services/guideCenter/articleList.html?articleStatus=draft",
            dataType: 'json',
            async: true,
            data: {"pageNo": pageNo, "pageSize": pageSize, "title": keyWords},
            beforeSend: function (XHR) {
                loading = top.layer.load(2, {
                    shade: [0.3, '#eee'] //0.1透明度的白色背景
                });
            },
            error: function (data) {
                alert("Connection error");
                return false;
            },
            success: function (data) {
                var draftListHtml = draftListTemplate(data);
                $('#myDraftContent').html(draftListHtml);
                //分页
                var totalPage = data.total % pageSize == 0 ? data.total / pageSize : Math.ceil(data.total / pageSize);
                if (totalPage > 1) {
                    draftPageFunction(totalPage, pageNo);
                }

                layer.closeAll();
            },
        });
    }

    //分页
    function draftPageFunction(totalPages, currentPage) {
        $("#draftPageInfoDiv").jqPaginator({
            totalPages: totalPages,
            visiblePages: 10,
            currentPage: currentPage,
            first: '<a href="javascript:void(0);" onclick="initDraftList(1,1)">导游论坛<\/a>',
            prev: '<a href="javascript:void(0);" onclick="initDraftList({{totalPages}},{{page}})">上一页<\/a>',
            next: '<a href="javascript:void(0);" onclick="initDraftList({{totalPages}},{{page}})">下一页<\/a>',
            last: '<a href="javascript:void(0);" onclick="initDraftList({{totalPages}},{{totalPages}})">末页<\/a>',
            page: '<a href="javascript:void(0);" onclick="initDraftList({{totalPages}},{{page}})">{{page}}<\/a>',
            onPageChange: function (n) {
                $("#demo1-text").html("当前第" + n + "页");
            }
        });
    }

    /**
     * 获取页面数据（收藏）
     * @type {any}
     */
    var enshrineListSource = $("#enshrineList-template").html();
    var enshrineListTemplate = Handlebars.compile(enshrineListSource);
    function initEnshrineList(totalPageNo, pageNo, keyWords) {
        var pageSize = 5;
        if (totalPageNo < pageNo || pageNo < 1) {
            return;
        }
        $.ajax({
            type: "post",
            url: "/portal/services/guideCenter/enshrineList.html",
            dataType: 'json',
            async: true,
            data: {"pageNo": pageNo, "pageSize": pageSize, "title": keyWords},
            beforeSend: function (XHR) {
                loading = top.layer.load(2, {
                    shade: [0.3, '#eee'] //0.1透明度的白色背景
                });
            },
            error: function (data) {
                alert("Connection error");
                return false;
            },
            success: function (data) {
                var enshrineListHtml = enshrineListTemplate(data);
                $('#myEnshrineContent').html(enshrineListHtml);
                //分页
                var totalPage = data.total % pageSize == 0 ? data.total / pageSize : Math.ceil(data.total / pageSize);
                if (totalPage > 1) {
                    enshrinePageFunction(totalPage, pageNo);
                }

                layer.closeAll();
            },
        });
    }

    //分页
    function enshrinePageFunction(totalPages, currentPage) {
        $("#enshrinePageInfoDiv").jqPaginator({
            totalPages: totalPages,
            visiblePages: 10,
            currentPage: currentPage,
            first: '<a href="javascript:void(0);" onclick="initEnshrineList(1,1)">导游论坛<\/a>',
            prev: '<a href="javascript:void(0);" onclick="initEnshrineList({{totalPages}},{{page}})">上一页<\/a>',
            next: '<a href="javascript:void(0);" onclick="initEnshrineList({{totalPages}},{{page}})">下一页<\/a>',
            last: '<a href="javascript:void(0);" onclick="initEnshrineList({{totalPages}},{{totalPages}})">末页<\/a>',
            page: '<a href="javascript:void(0);" onclick="initEnshrineList({{totalPages}},{{page}})">{{page}}<\/a>',
            onPageChange: function (n) {
                $("#demo1-text").html("当前第" + n + "页");
            }
        });
    }
</script>
<script type="text/javascript">
    $(function () {

        // tab slide function
        function tabSlide(nav, idc, item) {
            var indicator = $(idc),
                indicatorHalfWidth = indicator.width() / 2,
                lis = $(nav).children('li');

            $(nav).tabs(item, {
                effect: 'fade',
                fadeOutSpeed: 0,
                fadeInSpeed: 400,
                onBeforeClick: function (event, index) {
                    var li = lis.eq(index),
                        newPos = li.position().left + (li.width() / 2) - indicatorHalfWidth;
                    indicator.stop(true).animate({
                        left: newPos
                    }, 600, 'easeInOutExpo');
                }
            });
        }

        tabSlide(".mslide_tabs .nav", ".m_indicator", ".mslide_content .item");
    });
</script>
