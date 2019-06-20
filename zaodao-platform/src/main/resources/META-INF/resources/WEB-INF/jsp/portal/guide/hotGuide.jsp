<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
</head>

<body class="grey_bg">
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=hotGuide"/>
<!-- banner -->
<jsp:include page="/portal/portlet/innerBanner.html?bannerType=hotGuideBanner"/>

<div class="wrap">
    <!-- 搜索 -->
    <jsp:include page="/portal/portlet/search.html" />
    <%--<div class="commen_title"><span class="bold">人气导游—重庆</span><a href="#" class="more">更多 》</a></div>--%>

    <%--<ul class="col4_list">--%>
    <%--<li>--%>
    <%--<a href="guide_detail.html" class="list_guide_img"><img src="/images/guide_photo_01.jpg" alt=""><span--%>
    <%--class="stars"><span class="star5"></span></span></a>--%>
    <%--<div class="list_guide_info">--%>
    <%--<div class="top">--%>
    <%--<a href="guide_detail.html" class="name">可可</a><span class="lang">中文导游</span>--%>
    <%--</div>--%>
    <%--<div class="bottom">--%>
    <%--<span class="location"><i class="sprite icon_location_bl"></i>云南</span>--%>
    <%--<span class="price">￥<span class="num">800</span>/天</span>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</li>--%>
    <%--</ul>--%>
    <div id="hotGuideList"></div>
    <div class="more_list_item last ac"><a href="javascript:getDataList(false)">查看更多<i class="arrow"></i></a></div>
</div>
<!-- main content end -->
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>
<!-- 回到顶部 -->
<div id="gotop" class="sprite icon_top"></div>
</body>
</html>
<!--人气导游列表模版（按城市分类）-->
<script id="hotGuideListCity-template" type="text/html">
    {{#each data.dataList}}
    <div class="commen_title"><span class="bold">名导之颜—{{city}}</span><a href="/portal/guide/hotGuide/{{cityCode}}"
                                                                        class="more">更多 》</a></div>

    <ul class="col4_list">
        {{#each guides}}
        <li>
            <a target="_blank"  href="/portal/guide/detail/{{userId}}" class="list_guide_img"><img src="{{guideCoverImg}}" alt=""><span
                    class="stars"><span class="star{{starLevel}}"></span></span></a>
            <div class="list_guide_info">
                <div class="top">
                    <a target="_blank"  href="/portal/guide/detail/{{userId}}" class="name">{{realName}}</a><span class="lang">
                    {{language}}</span>
                </div>
                <div class="bottom">
                    <span class="location"><i class="sprite icon_location_bl"></i>{{realName}}</span>
                    <span class="price">￥<span class="num">{{amount}}</span>/天</span>
                </div>
            </div>
        </li>
        {{/each}}
    </ul>
    {{/each}}
</script>
<!--人气导游列表模版（固定城市）-->
<script id="hotGuideList-template" type="text/html">
    {{#each data.dataList}}
    {{#each guides}}
    <li>
        <a href="/portal/guide/detail/{{userId}}" class="list_guide_img"><img src="{{guideCoverImg}}" alt=""><span
                class="stars"><span class="star{{starLevel}}"></span></span></a>
        <div class="list_guide_info">
            <div class="top">
                <a href="/portal/guide/detail/{{userId}}" class="name">{{realName}}</a><span
                    class="lang">{{language}}</span>
            </div>
            <div class="bottom">
                <span class="location"><i class="sprite icon_location_bl"></i>{{realName}}</span>
                <span class="price">￥<span class="num">{{amount}}</span>/天</span>
            </div>
        </div>
    </li>
    {{/each}}
    {{/each}}
</script>
<script type="text/javascript">
    var pageNo = 1;
    var hasNext = true;

    $(document).ready(function () {
        getDataList(true);
    })

    function getDataList(flag) {
        var city = '${city}';
        if (flag) {
            //加载分城市人气导游
            var tmpSource = $("#hotGuideListCity-template").html();
            var template = Handlebars.compile(tmpSource);
        } else {
            //加载指定城市人气导游
            var tmpSource = $("#hotGuideList-template").html();
            var template = Handlebars.compile(tmpSource);
        }
        if (!hasNext) {
            top.layer.msg('没有更多了', {icon: 1, time: 2000});
            return;
        }
        $.ajax({
            type: "get",
            url: "/portal/guide/hotGuideList.html",
            dataType: 'json',
            async: true,
            data: {
                "city": city,
                "pageNo": pageNo
            },
            beforeSend: function (curform) {
                loading = top.layer.load(2, {
                    shade: [0.3, '#eee'] //0.1透明度的白色背景
                });
            },
            complete: function () {
                layer.closeAll();
            },
            error: function (data) {
                alert("Connection error");
                return false;
            },
            success: function (data) {
                var listHtml = template(data);
                if (flag) {
                    $('#hotGuideList').html(listHtml);
                } else {
                    $('#hotGuideList').find("ul.col4_list").append(listHtml);
                }
                pageNo = pageNo + 1;
                hasNext = data.data.hasNext;
            },
        });
    }
</script>