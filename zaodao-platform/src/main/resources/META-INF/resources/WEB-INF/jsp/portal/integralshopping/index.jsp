<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/metaScore.jsp"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <link href="/css/eshop.css" rel="stylesheet" />
</head>

<body>
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=integralShopping"/>

<!-- banner -->
<jsp:include page="/portal/shop/portlet/integralBanner.html"/>

<!-- 右侧工具栏-->
<jsp:include page="/portal/shop/portlet/rigthTool.html"/>

<div class="mall-hot">
    <div class="mall-hot-list wrapper1 clearfix">
        <div class="hot-select-l">
            <img src="/picture/hot-select-l.png">
        </div>
        <div class="hot-select-r">
            <ul class="mall-hot-goods clearfix">
            <c:forEach items="${hotShopGoods}" var="h">
                <li>
                    <a href="/portal/shop/detail_${h.id}.html">
                        <dl class="hot-item active">
                            <p>限时抢购</p>
                            <dt>
                                <img alt="商品图片" src="${h.logo}" style="width: 180px;height: 120px;">
                            </dt>
                            <dd class="hot-name">
                                ${h.name}
                            </dd>
                            <dd class="hot-rPrice">
                                市场参考价：<span>${h.marketCredits}</span>元
                            </dd>
                            <dd class="hot-dPrice">
                                <span class="pull-left">${h.credits} 积分</span> <span class="pull-right"> <b
                                    class="glyphicon glyphicon-time"></b> <b class="countdown"></b>
                                        </span>
                            </dd>
                        </dl>
                    </a>
                </li>
            </c:forEach>
            </ul>
        </div>
    </div>
</div>
<!--实物商品-->
<c:forEach items="${shopGoodsTypes}" var="t">
    <div class="mall-exclusive">
    <div class="mall-virtual-list wrapper1 clearfix">
        <div class="mall-goods-title">
            <img src="/picture/mall-bg.png" title="实物商品">
            <h4>${t.name}</h4>
            <div style="position: absolute;right:20px">
                <a target="_blank" href="/portal/shop/goodsList.html?mainCode=${t.code}&search_RLIKE_shopGoodsType.code=${t.code}">更多</a>
            </div>
        </div>
        <!--专属商品-->
        <ul class="mall-exclusive-goods clearfix">
        <c:forEach items="${t.shopGoodsList}" var="e">
            <li>
                <a target="_blank" href="/portal/shop/detail_${e.id}.html">
                    <dl class="exclusive-item">
                        <dt>
                            <img alt="商品图片" src="${e.logo}" style="width: 180px;height: 120px;">
                        </dt>
                        <dd class="goods-name ml30">${e.name}</dd>
                        <dd class="goods-rPrice ml30">市场参考价：<span>${e.marketCredits}</span>元</dd>
                        <dd class="goods-dPrice ml30">尊享价：<span>${e.credits}</span> 积分</dd>
                    </dl>
                </a>
            </li>
        </c:forEach>
        </ul>
    </div>
</div>
</c:forEach>

<!-- main content end -->

<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>

<!-- slider -->
<script src="/js/jquery.bxslider.min.js"></script>
<script src="/js/shop_menu.js"></script>
<script>
    $(document).ready(function() {

        $('.slider_banner').bxSlider({
            controls: false,
            minSlides: 1,
            maxSlides: 1,
            moveSlides: 1,
            auto: true,
            slideMargin: 0
        });

    });
</script>
</body>
</html>
