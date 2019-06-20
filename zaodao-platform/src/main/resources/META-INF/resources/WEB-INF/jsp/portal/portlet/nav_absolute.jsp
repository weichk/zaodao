<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<div class="main_nav_absolute">
    <div class="main_nav_box">
        <div class="logo"><a href="/portal/index.html"><img src="/images/logo_0809.png" alt=""></a></div>
        <ul>
            <li><a href="/portal/index.html" id="first_page_index_id">早导论坛</a></li>
            <li><a href="/portal/guideHome/guideHomeIndex.html" id="guide_home_page_index_id">导行天下</a></li>
            <li><a href="/portal/travelStrategy/travelStrategyIndex.html" id="travel_strategy_page_index_id">游者无疆</a>
            </li>
            <li><a href="/portal/shop/integralShopping.html" id="integral_shop_page_index_id">早导MALL</a></li>
        </ul>
        <div class="top_login_info">
            <c:if test="${sessionScope.sessionKeyUserInfo != null}">
                <div class="top_login_in">
                    <a href="#" class="loginin_header pie">
                        <c:if test="${sessionScope.sessionKeyUserInfo.headImg == ''||sessionScope.sessionKeyUserInfo.headImg==null}">
                            <img src="/images/icon_header.png" alt=""/>
                        </c:if>
                        <c:if test="${sessionScope.sessionKeyUserInfo.headImg != ''}">
                            <img src="${sessionScope.sessionKeyUserInfo.headImg}" alt=""/>
                        </c:if>
                    </a>
                    <c:if test="${sessionScope.sessionKeyUserInfo.isTourGuide != 1}">
                        <a href="/portal/services/customer/childpage.html?childIndex=myTouristMessage"
                           class="loginin_nav"><i class="icon_msg"></i>
                            <c:if test="${not empty msgNum}">
                                <i class="reddot">${msgNum}</i>
                            </c:if>
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.sessionKeyUserInfo.isTourGuide == 1 }">
                        <a href="/portal/services/customer/childpage.html?childIndex=myGuideMessage"
                           class="loginin_nav"><i class="icon_msg"></i>
                            <c:if test="${not empty msgNum}">
                                <i class="reddot">${msgNum}</i>
                            </c:if>
                        </a>
                    </c:if>
                    <dl class="login_nav_list pie">
                        <c:if test="${sessionScope.sessionKeyUserInfo.isTourGuide == 1}">
                            <dd><a href="/portal/services/customer/childpage.html?childIndex=myArticle">我的文章</a></dd>
                            <dt><a href="/portal/services/customer/childpage.html?childIndex=myMediaLib">我的媒体库</a>
                            </dt>
                            <dd><a href="/portal/services/customer/childpage.html?childIndex=accountInfo">小金库</a>
                            </dd>
                            <dd><a href="/portal/services/customer/childpage.html?childIndex=deductWithdraw">账户提现</a>
                            </dd>
                            <dt><a href="/portal/services/customer/childpage.html?childIndex=incomeDetail">收支明细</a></dt>
                        </c:if>
                        <dt><a href="/portal/services/customer/childpage.html?childIndex=accountConfigue">账户设置</a>
                        </dt>
                        <dd><a href="/portal/services/customer/childpage.html?childIndex=tripOrder">我的订单</a></dd>

                        <dd>
                            <c:choose>
                                <c:when test="${pageType == 'shop'}">
                                    <a href="javascript:void(0);"
                                       onclick="$.aportal.confirm('安全退出','确定要注销退出?',function(){location.href='/portal/services/logout.html'},null,null,null,'100px',null);">退出</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="javascript:void(0);" onclick="logout()">退出</a>
                                </c:otherwise>
                            </c:choose>
                        </dd>
                    </dl>
                </div>
            </c:if>
            <c:if test="${sessionScope.sessionKeyUserInfo == null}">
                <div class="top_loginout">
                    <a href="/portal/portlet/login_register.html" class="login_in">登录</a>|<a
                        href="/portal/portlet/login_register.html?type=register" class="regist_account">注册</a>
                </div>
            </c:if>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#nav_ul_id').children('li').children('a').removeClass('active');
        var pageIndex = "${pageIndex}";
        if (pageIndex == 'firstPage') {
            $('#first_page_index_id').addClass("active");
        } else if (pageIndex == 'hotGuide') {
            $('#hot_guide_page_index_id').addClass("active")
        } else if (pageIndex == 'travelStrategy') {
            $('#travel_strategy_page_index_id').addClass("active")
        } else if (pageIndex == 'guideHome') {
            $('#guide_home_page_index_id').addClass("active")
        } else if (pageIndex == 'integralShopping') {
            $('#integral_shop_page_index_id').addClass("active")
        } else if (pageIndex == 'customerCenter') {
            $('#customer_center_page_index_id').addClass("active")
        }
    });

    function logout() {
        layer.confirm('确认要退出登录吗？', {
            icon: 3,
            offset: '280px',
            skin: 'layui-layer-rim'
        }, function (index) {
            location.href = "/portal/services/logout.html";
            layer.close(index);
        });
    }
</script>