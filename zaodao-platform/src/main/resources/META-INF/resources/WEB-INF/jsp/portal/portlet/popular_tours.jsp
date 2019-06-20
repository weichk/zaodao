<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<div class="home_section1">
    <div class="wrap">
        <!-- 搜索 -->
        <jsp:include page="/portal/portlet/search.html"/>

        <div class="commen_title">
            <span class="bold yellow">名导之颜</span>
            <%--<ul class="city_nav">--%>
            <%--<c:forEach var="area" items="${areaList}" varStatus="st">--%>
            <%--<li>--%>
            <%--<a target="_blank"--%>
            <%--<c:if test="${st.index==0}">class="current"</c:if>--%>
            <%--href="/portal/guide/hotGuide/${area.code}">${area.name}</a>--%>
            <%--</li>--%>
            <%--</c:forEach>--%>
            <%--</ul>--%>
            <a href="#" class="show_search more">更多<i class="icon_arrow_down"></i></a>
        </div>

        <form action="/portal/search.html" method="post" name="filter_form" class="filter_form">
            <div class="form_item form_head">
                <label for="province" class="label">目&nbsp;&nbsp;的&nbsp;&nbsp;地：</label>
                <select class="select" id="province" name="province" tabindex="3"></select>
                <select class="select" id="city" name="city" tabindex="4"></select>
                <label class="label out_date">出行时间：</label>
                <div class="date_container inline">
                    <div class="date_input pie">
                        <div class="room_num">
                            <input type="text" name="date" id="choose_single_date" class="date">
                        </div>
                    </div>
                    <span class="total none">共<em id="total-days">_</em>晚</span>
                    <!-- Swiper -->
                    <div class="swiper-container date_swiper pie">
                        <div id="calendar-box" class="calendar swiper-wrapper">
                            <!-- <div class="calendar pie"></div> -->
                        </div>
                        <!-- Add Navigation -->
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-button-next"></div>
                    </div>
                </div>
            </div>
            <div class="form_item">
                <label class="label">语&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;言：</label>
                <input type="radio" name="lang" value="" checked><label>全部</label>
                <input type="radio" name="lang" value="中文"><label>中文</label>
                <input type="radio" name="lang" value="英文"><label>英文</label>
                <input type="radio" name="lang" value="日文"><label>日文</label>
            </div>
            <%--<div class="form_item">--%>
            <%--<label class="label">导游级别：</label>--%>
            <%--<input type="radio" name="level" value="" checked><label>全部</label>--%>
            <%--<input type="radio" name="level" value="goldMedal"><label>金牌</label>--%>
            <%--<input type="radio" name="level" value="silverMedal"><label>银牌</label>--%>
            <%--<input type="radio" name="level" value="copperMedal"><label>铜牌</label>--%>
            <%--</div>--%>
            <div class="form_item last">
                <label class="label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
                <input type="radio" name="sex" value="" checked><label>全部</label>
                <input type="radio" name="sex" value="0"><label>女</label>
                <input type="radio" name="sex" value="1"><label>男</label>
            </div>
            <input type="submit" value="我要约导" id="filter_btn" class="filter_btn pie">
        </form>

        <div class="city_guider">
            <div class="item">
                <ul class="col4_list">
                    <c:forEach var="guide" items="${dataList}">
                        <li>
                            <a target="_blank" href="/portal/guide/detail/${guide.userId}"
                               class="list_guide_img"><img
                                    src="${guide.guideCoverImg}" alt=""><span
                                    class="stars"><span class="star${guide.starLevel}"></span></span></a>
                            <div class="list_guide_info">
                                <div class="top">
                                    <a target="_blank" href="/portal/guide/detail/${guide.userId}"
                                       class="name">${guide.realName}</a><span
                                        class="lang">${guide.language}</span>
                                </div>
                                <div class="bottom">
                                        <span class="location"><i
                                                class="sprite icon_location_bl"></i>${guide.permanentCity}</span>
                                    <span class="price">￥<span class="num">${guide.amount}</span>/天</span>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <!--人气导游结束-->
        </div>

        <div class="more_guide_item ac"><a target="_blank" href="/portal/search.html" class="pie">查看更多 》</a></div>
    </div>
</div>
<script type="text/javascript">
    $(function () {

        // tab slide function
        function tabSlide(nav, item) {

            $(nav).tabs(item, {
                effect: 'fade',
                fadeOutSpeed: 0,
                fadeInSpeed: 400
            });
        }

        // 人气导游 tab
        tabSlide(".city_nav", ".city_guider .item");
    });

    //    function hotGuide() {
    //        window.location.href = $("ul.city_nav").find("a.current").attr("href");
    //    }

    $(function () {

        // 滚动显/隐【我要约导】
        $(".show_search").click(function (e) {
            e.preventDefault();
            $(".filter_form").slideToggle();
        });

        // tab slide function
        function tabSlide(nav, item) {

            $(nav).tabs(item, {
                effect: 'fade',
                fadeOutSpeed: 0,
                fadeInSpeed: 400
            });
        }

        // 人气导游 tab
        tabSlide(".city_nav", ".city_guider .item");
    });
</script>
<!-- 导游论坛人气导游 end -->