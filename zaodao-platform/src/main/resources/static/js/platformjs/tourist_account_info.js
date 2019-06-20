$(function() {

    // tab slide function
    function tabSlide(nav, idc, item, a) {
        var indicator = $(idc),
            indicatorHalfWidth = a ? indicator.width() / 2 : indicator.height() / 2,
            lis = $(nav).children('li');

        $(nav).tabs(item, {
            effect: 'fade',
            fadeOutSpeed: 0,
            fadeInSpeed: 400,
            onBeforeClick: function(event, index) {
                var li = lis.eq(index),
                    newPos = (a ? li.position().left : li.position().top) + (a ? (li.width() / 2) : (li.height() / 2)) - indicatorHalfWidth;
                if (a == 1) {
                    indicator.stop(true).animate({
                        left: newPos
                    }, 600, 'easeInOutExpo');
                }
                if (a == 0) {
                    indicator.stop(true).animate({
                        top: newPos
                    }, 600, 'easeInOutExpo');
                }
            },
            onClick: function(event, index) {
                if (a == 1) {
                    if(index == 0) {
                        $('.gd_content_1 > .item').show();
                        showGallery();
                        if($('.bx-viewport .bx-controls')) {
                            $('.bx-viewport .bx-controls').remove();
                        }
                    }
                }
            }
        });
    }
    tabSlide(".gd_tab_2 .nav1", ".gd_idc_2", ".gd_content_2 .swiper-slide", 0); //参数为0，nav纵向
});

function intoBindingCard(isCertification) {
    window.location.href = '/portal/services/customer/childpage.html?childIndex=bindingCard'
}