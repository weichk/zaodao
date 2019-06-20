$(document).ready(function(){

    // $('.slider_banner').bxSlider({
    //     controls: false,
    //     minSlides: 1,
    //     maxSlides: 1,
    //     moveSlides: 1,
    //     auto:true,
    //     slideMargin: 0
    // });

});

$(function() {

    var slider01 = $('.club_slider_01').bxSlider({
        controls: false,
        minSlides: 1,
        maxSlides: 1,
        moveSlides: 1,
        auto: true,
        slideMargin: 0
    });
    var slider02 = $('.club_slider_02').bxSlider({
        controls: false,
        minSlides: 1,
        maxSlides: 1,
        moveSlides: 1,
        auto: true,
        slideMargin: 0
    });
    var slider03 = $('.club_slider_03').bxSlider({
        controls: false,
        minSlides: 1,
        maxSlides: 1,
        moveSlides: 1,
        auto: true,
        slideMargin: 0
    });
    var slider04 = $('.club_slider_04').bxSlider({
        controls: false,
        minSlides: 1,
        maxSlides: 1,
        moveSlides: 1,
        auto: true,
        slideMargin: 0
    });
    var slider05 = $('.club_slider_05').bxSlider({
        controls: false,
        minSlides: 1,
        maxSlides: 1,
        moveSlides: 1,
        auto: true,
        slideMargin: 0
    });
    var slider06 = $('.club_slider_06').bxSlider({
        controls: false,
        minSlides: 1,
        maxSlides: 1,
        moveSlides: 1,
        auto: true,
        slideMargin: 0
    });
    var slider07 = $('.club_slider_07').bxSlider({
        controls: false,
        minSlides: 1,
        maxSlides: 1,
        moveSlides: 1,
        auto: true,
        slideMargin: 0
    });
    var slider08 = $('.club_slider_08').bxSlider({
        controls: false,
        minSlides: 1,
        maxSlides: 1,
        moveSlides: 1,
        auto: true,
        slideMargin: 0
    });
    var slider09 = $('.club_slider_09').bxSlider({
        controls: false,
        minSlides: 1,
        maxSlides: 1,
        moveSlides: 1,
        auto: true,
        slideMargin: 0
    });
    var slider10 = $('.club_slider_10').bxSlider({
        controls: false,
        minSlides: 1,
        maxSlides: 1,
        moveSlides: 1,
        auto: true,
        slideMargin: 0
    });

    // tab slide function
    function tabSlide(nav, idc, item) {
        var indicator = $(idc),
            indicatorHalfWidth = indicator.width() / 2,
            lis = $(nav).children('li');

        $(nav).tabs(item, {
            effect: 'fade',
            fadeOutSpeed: 0,
            fadeInSpeed: 400,
            onBeforeClick: function(event, index) {
                var li = lis.eq(index),
                    newPos = li.position().left + (li.width() / 2) - indicatorHalfWidth;
                indicator.stop(true).animate({
                    left: newPos
                }, 600, 'easeInOutExpo');
            },
            onClick: function(event,index) {
                var num = parseInt(idc.charAt(idc.length - 1));
                switch (num) {
                    case 1:
                        slider01.reloadSlider();
                        slider02.reloadSlider();
                        slider03.reloadSlider();
                        break;
                    case 2:
                        slider04.reloadSlider();
                        slider05.reloadSlider();
                        slider06.reloadSlider();
                        break;
                    case 3:
                        slider08.reloadSlider();
                        slider09.reloadSlider();
                        slider10.reloadSlider();
                        break;
                }
            }
        });
    }
    // 导游秘籍 tab
    tabSlide(".gch_tab_1 .nav", ".gch_idc_1", ".gch_content_1 .item");
    // 带团日志 tab
    tabSlide(".gch_tab_2 .nav", ".gch_idc_2", ".gch_content_2 .item");
    // 案例分析 tab
    tabSlide(".gch_tab_3 .nav", ".gch_idc_3", ".gch_content_3 .item");
    // 话题排行 tab
    tabSlide(".gch_tab_4 .nav", ".gch_idc_4", ".gch_content_4 .item");

});

function readArticleInfo(articleId,isGuide,loginInfo) {
    if(loginInfo == '') {
        popupLogin();
    }else if(isGuide != 1) {
        $.platform.msg("请上传导游证", 2000, 5);
    }else {
        window.location.href = "/portal/guideHome/getArticleInfo_"+articleId+".html";
    }
}

function readNewsInfo(newsId,isGuide,type,loginInfo) {
    if(loginInfo == '') {
        popupLogin();
    }else if(isGuide != 1) {
        $.platform.msg("请上传导游证", 2000, 5);
    }else {
        window.location.href = "/portal/news/newsInfo.html?id="+newsId+"&type="+type;
    }
}