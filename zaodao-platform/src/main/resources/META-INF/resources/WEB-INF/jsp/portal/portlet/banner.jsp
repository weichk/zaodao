<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<div class="banner">
    <div class="slider_wrap">

        <div id="slider_banner" class="slider_banner">
            <c:forEach items="${indexBanners}" var="e" varStatus="s">
                <div class="slide"><img src="${mediaRoot}/${e.cover}"
                                        title="${e.comments}" alt=""
                                        <c:if test="${not empty e.link}">onclick="window.open('${e.link}')"</c:if> />
                </div>
            </c:forEach>
        </div>
    </div>
    <!-- slider_banner end -->
</div>
<script>
    var docWidth = document.body.clientWidth;
    var bannerHeight = null;
    if (docWidth >= 1920) {
        bannerHeight = 586;
    } else {
        bannerHeight = (docWidth / 1920) * 586;
    }
    var banner = document.getElementById('slider_banner');
    // var thumb = document.getElementById('banner_thumb');
    banner.style.height = bannerHeight + 'px';
    // 缩略图垂直居中
    // thumb.style.top = (bannerHeight - 399) / 2 + 'px';
    // if(docWidth > 1220) {
    //     thumb.style.right = (docWidth - 1220) / 2 + 'px';
    // } else {
    //     thumb.style.right  = 0 + 'px';
    // }
</script>
<script>
    $(document).ready(function () {
        $('.slider_banner').bxSlider({
            controls: false,
            minSlides: 1,
            maxSlides: 1,
            moveSlides: 1,
            auto: true,
//            pagerCustom: '.banner_thumb_box',
            slideMargin: 0,
            autoHover: true,
            pause:6000,
            captions: true
        });
    });
</script>
<!-- banner end -->