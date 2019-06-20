<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<div class="banner">
    <div class="slider_wrap">
        <div class="slider_banner">
            <c:forEach items="${pointShopBanner}" var="e" varStatus="s">
                <div class="slide"><a target="_blank" href="${e.link}"><img src="${mediaRoot}/${e.cover}" alt=""></a>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- slider_banner end -->
    <!--早导MALL导航-->
    <div class="mall-nav-con">
        <div class="mall-l fl">
            <p class="ul-top"></p>
            <ul class="mall-ul">
             <c:if test="${shopGoodsTypes != null}">
                <li>
                    <p class="tl"><a href="/portal/shop/goodsList.html">全部商品</a></p>
                    <ul class="clearfix">
                    <c:forEach items="${shopGoodsTypes}" var="t">
                        <li><a href="/portal/shop/goodsList.html?mainCode=${t.code}&search_RLIKE_shopGoodsType.code=${t.code}">${t.name}</a></li>
                    </c:forEach>
                    </ul>
                </li>
                <c:forEach items="${shopGoodsTypes}" var="t">
                    <li>
                        <p class="tl">
                            <a href="/portal/shop/goodsList.html?mainCode=${t.code}&search_RLIKE_shopGoodsType.code=${t.code}">${t.name}</a>
                        </p>
                        <ul class="clearfix">
                        <c:forEach items="${t.children}" var="e">
                            <li><a href="/portal/shop/goodsList.html?mainCode=${t.code}&search_LLIKE_shopGoodsType.code=${e.code}">${e.name}</a></li>
                        </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
             </c:if>
                <li>
                    <p class="tl"><a href="#" target="_blank" class="yellow"> 去赚积分</a></p>
                </li>
            </ul>
        </div>
    </div>
</div>
<!-- banner end -->