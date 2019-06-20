$(function() {
    // 导航
    // addClass
    function addClass(element,value) {
        if(!element.className) {
            element.className = value;
        } else {
            var newClassName = element.className;
            newClassName += ' ';
            newClassName += value;
            element.className = newClassName;
        }
    }

    // removeClass
    function removeClass(element,value) {
        var regExp = /\s+$/;
        if(element.className.indexOf(value) != -1) {
            var newClassName = element.className.replace(value, '');
            element.className = newClassName.replace(regExp, '');
        } else {
            return;
        }
    }
    function changePos(obj,height){
        var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
        var classValue = obj.attributes['class'].value;
        if(scrollTop < height){
            if(classValue.indexOf('fixed')!= -1) {
                removeClass(obj,'fixed');
            }
        }else{
            if( classValue.indexOf('fixed')== -1) {
                addClass(obj,'fixed');
            }
        }
    }
    //边栏
    $('.side-bar-sev,.side-bar-cal').hover(function() { //客服展示
        $(this).find('.side-bar-content').show('fast');
        $(this).find('.side-bar-trg').show('fast');
    }, function() {
        $(this).find('.side-bar-content').hide();
        $(this).find('.side-bar-trg').hide();
    });
    var obj = document.getElementById("main_nav");
    if (!obj) return false;
    var _getHeight = obj.offsetTop;
    window.onscroll = function() { //当滚动到大于450显示返回顶部
        var disTop = document.documentElement.scrollTop || document.body.scrollTop;
        console.log();
        disTop > 450 ?
            $('.totop').slideDown() :
            $('.totop').slideUp();

        changePos(obj, _getHeight);
    };
    $('.totop').click(function() { //返回顶部
        $("html, body").animate({
            scrollTop: 0
        }, 300);
        return false;
    });
    $('.nav-tabs li').hover(function() {
        $(this).find('.indexTab').animate({
            left: "-6px"
        });
    }, function() {
        $(this).find('.indexTab').animate({
            left: "0px"
        });
    });
    $(".nav .word").hover(function() {
        $(this).find(".word-menu").show();
    }, function() {
        $(this).find(".word-menu").hide();
    });
});