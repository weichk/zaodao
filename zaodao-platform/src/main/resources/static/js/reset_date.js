$(function(){
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

    // 将表单中的日期初始化为［入住］与［离开］日期
    var dayIn = new Date($("#checkin-date").html());
    var dayOut = new Date($("#checkout-date").html());

    $('#calendar-box').calendar({
        checkIn : dayIn, // 引入［入住］日期
        checkOut: dayOut, // 引入［离开］日期
        totalMohth: 7,
        dayText: ['开始', '结束'],
        checkDayChange: function(){
            console.log(this.checkIn);
            console.log(this.dayText[0], this.checkIn ? this.checkIn.format('yyyy-MM-dd') : '-');
            console.log(this.dayText[1], this.checkOut ? this.checkOut.format('yyyy-MM-dd') : '-');

            if (this.checkIn == null && this.checkOut == null) {
                $('#checkin-date').html('请选择');
                $('#checkout-date').html('请选择');
                $('#total-days').text('_');
            }
            if (this.checkIn ) {
                $('#checkin-date').html(this.checkIn.format('yyyy-MM-dd'));
                $('#checkout-date').html('请选择');
            }
            if (this.checkOut ) {
                $('#checkout-date').html(this.checkOut.format('yyyy-MM-dd'));
            }
            $('#total-days').text('');

            if (this.checkIn && this.checkOut) {
                var totalDays = parseInt(Math.abs((this.checkOut - this.checkIn) / 1000 / 60 / 60 /24)) + 1;
                $('#total-days').text(' '+totalDays+' ');
            }
        }
    });

    // 清空［入住］与［离开］日期
    dayIn = null;
    dayOut = null;
});
