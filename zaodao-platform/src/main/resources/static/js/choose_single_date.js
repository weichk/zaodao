$(function(){
    var date = new Date(),
        today = date.getFullYear() + '.' + (date.getMonth() + 1)+ '.' + date.getDate();
    if ($('#choose_single_date').val() == '') {
        $('#choose_single_date').val(today);
    }

    date = null;
    today = null;

    $('.date_input').click(function(){
        if(!$("#calendar-box") || !$('.date_swiper')) return false;
        $("#calendar-box").attr('style',''); // 清空样式
        $('.date_swiper').toggle();
        $(this).toggleClass('active');

        var box = $("#calendar-box").find(".checkedday").first();
        var itemIndex = box.length == 0 ? 0 : box.parents(".swiper-slide").prevAll().length;
        var itemLength = $("#calendar-box .swiper-slide").length;
        if(itemIndex == 0) {
            $('.swiper-button-prev').addClass('disable');
        }
        if (itemIndex == itemLength) {
            $('.swiper-button-next').addClass('disable');
        }

        // 日历切换
        var mySwiper = new Swiper('.date_swiper', {
            initialSlide: itemIndex,
            calculateHeight: true,
            onSlideChangeStart: function(swiper){
                if(mySwiper.activeIndex == 0){
                    $('.swiper-button-prev').addClass('disable');
                }else{
                    $('.swiper-button-prev').removeClass('disable');
                }
                if(mySwiper.activeIndex == mySwiper.slides.length - 1) {
                    $('.swiper-button-next').addClass('disable');
                } else {
                    $('.swiper-button-next').removeClass('disable');
                }
            }
        });
        $('.swiper-button-prev').on('click', function(e) {
            e.preventDefault();
            mySwiper.swipePrev();
        });
        $('.swiper-button-next').on('click', function(e) {
            e.preventDefault();
            mySwiper.swipeNext();
        });
    });

    $(document).bind("click", function(event) {
        if($(event.target).parents(".date_container").length==0) {
            $('.date_swiper').hide();
            $('.date_input').removeClass('active');
        }
    });

    Date.prototype.format = function(formatStr) {
        var str = formatStr;
        var Week = ['日','一','二','三','四','五','六'];

        str = str.replace(/yyyy|YYYY/,this.getFullYear());
        str = str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));

        str = str.replace(/MM/,this.getMonth()>8?(this.getMonth()+1).toString():'0' + (this.getMonth()+1));
        str = str.replace(/M/g,this.getMonth()+1);

        str = str.replace(/w|W/g,Week[this.getDay()]);

        str = str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());
        str = str.replace(/d|D/g,this.getDate());

        str = str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());
        str = str.replace(/h|H/g,this.getHours());
        str = str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());
        str = str.replace(/m/g,this.getMinutes());

        str = str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());
        str = str.replace(/s|S/g,this.getSeconds());

        return str;
    }

    $('#calendar-box').calendar({
        checkIn : null,
        totalMohth: 7,
        singleDate: true,
        dayText: ['已选'],
        checkDayChange: function(){
            var date = new Date(),
                today = date.getFullYear() + '.' + date.getMonth() + '.' + date.getDate();
            if (this.checkIn == null) {
                $('#choose_single_date').val(today);
            }
            if (this.checkIn) {
                $('#choose_single_date').val(this.checkIn.format('yyyy.MM.dd'));
            }
        }
    });
});
