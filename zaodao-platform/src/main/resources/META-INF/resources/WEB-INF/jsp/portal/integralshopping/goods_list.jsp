<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/eshop.css" rel="stylesheet" />
</head>

<body>
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=integralShopping"/>

<!-- 右侧工具栏-->
<jsp:include page="/portal/shop/portlet/rigthTool.html"/>

<div class="mallProduct">
    <div class="wrapper1">
        <!--商品分类导航-->
        <div class="mallPro-nav">
            <i class="nav-line nav-line-l"></i>
            <ul class="nav-list clearfix">
                <li <c:if test="${mainCode==null||mainCode==''}">class="active"</c:if>><a class="all" href="/portal/shop/goodsList.html"></a></li>
                <li <c:if test="${mainCode=='100'}">class="active"</c:if>><a class="exc" href="/portal/shop/goodsList.html?mainCode=100&search_RLIKE_shopGoodsType.code=100"></a></li>
                <li <c:if test="${mainCode=='102'}">class="active"</c:if>><a class="ent" href="/portal/shop/goodsList.html?mainCode=102&search_RLIKE_shopGoodsType.code=102"></a></li>
                <li <c:if test="${mainCode=='101'}">class="active"</c:if>><a class="vir" href="/portal/shop/goodsList.html?mainCode=101&search_RLIKE_shopGoodsType.code=101"></a></li>
            </ul>
            <i class="nav-line nav-line-r"></i>
        </div>
        <!--商品列表-->
        <form id="shopGoodsPage_search_form" action="/portal/shop/goodsList.html" method="post">
            <input type="hidden" name="mainCode" value="${mainCode}" />
            <input type="hidden" name="search_RLIKE_shopGoodsType.code" value="${rShopGoodsType}" />
            <input type="hidden" name="search_LLIKE_shopGoodsType.code" value="${lShopGoodsType}" />
            <div>
                <!--所有商品-->
                <div id="allGoods" class="mallPro-goods active">
                    <ul class="mallPro-list clearfix">
                        <c:if test="${shopGoodsPage != null}">
                        <c:forEach items="${shopGoodsPage.pageResults}" var="e"
                                   varStatus="s">
                        <li>
                            <a target="_blank" href="/portal/shop/detail_${e.id}.html">
                                <dl class="mallPro-item">
                                    <dt>
                                        <img alt="商品图片" src="<c:if test="${e.logo != null}">${e.logo}</c:if><c:if test="${e.logo == null}">/images/nopic.png</c:if>" style="width: 180px;height: 120px;">
                                    </dt>
                                    <dd class="goods-name ml30"><ac:title value="${e.name}" length="20" /></dd>
                                    <dd class="goods-rPrice ml30">
                                        市场参考价：<span>${e.marketCredits}</span> 元
                                    </dd>
                                    <dd class="goods-dPrice ml30">
                                        尊享价：<span>${e.credits }</span> 积分
                                    </dd>
                                </dl>
                            </a>
                        </li>
                        </c:forEach>
                        </c:if>
                    </ul>
                </div>
                <!--专属-->
            </div>
            <!--分页-->
            <ac:page totalCount="${shopGoodsPage.totalCount}" pageCount="${shopGoodsPage.countOfCurrentPage}" currentPage="${shopGoodsPage.currentPage}" searchForm="shopGoodsPage_search_form"></ac:page>
            <%--<div class="page"><span class="controller"><a href="#" class="current">1</a><a href="#" onclick="pageSubmit(2)">2</a><a href="#" onclick="pageSubmit(3)">3</a><a href="#" onclick="pageSubmit(4)">4</a><a href="#" onclick="pageSubmit(5)">5</a><a href="#" onclick="pageSubmit(2)">下页</a><a href="#" onclick="pageSubmit(5)">末页</a></span><span class="display"><span><label>当前页码:</label>1</span><span><label>总页数:</label>5</span><span><label>总记录数:</label>53</span></span>--%>
                <%--<div>--%>
                    <%--<input type="hidden" id="acoolyPageCurrentPage" name="currentPage" />--%>
                    <%--<input type="hidden" value="12" name="pageCount" />--%>
                <%--</div>--%>
                <%--<script type="text/javascript">--%>
                    <%--function pageSubmit(currentPage) {--%>
                        <%--document.getElementById('acoolyPageCurrentPage').value = currentPage;--%>
                        <%--document.getElementById('shopGoodsPage_search_form').submit();--%>
                    <%--}--%>
                <%--</script>--%>
            <%--</div>--%>
        </form>
    </div>
</div>
<!-- main content end -->

<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>

<!-- slider -->
<script src="/js/jquery.bxslider.min.js"></script>
<script src="/js/shop_menu.js"></script>
<%--<script>--%>
    <%--$(function() {--%>
        <%--$('.mallPro-nav .nav-list li a').click(function(e) {--%>
            <%--$(this).parent().addClass('active').--%>
            <%--siblings().removeClass('active');--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>
</body>
</html>