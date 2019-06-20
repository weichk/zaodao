// addLoadEvent
function addLoadEvent(func) {
    var oldonload = window.onload;
    if(typeof window.onload != 'function') {
        // window.onload = func;
    } else {
        window.onload = function() {
            oldonload();
            func();
        };
    }
}

// insertAfter
function insertAfter(newElement,targetElement) {
    var parent = targetElement.parentNode;
    if(parent.lastChild == targetElement) {
        parent.appendChild(newElement);
    } else {
        parent.insertBefore(newElement,targetElement.nextsibling);
    }
}

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

/*菜单浮动到顶部时固定*/
function menuFixed(id){
    var obj = document.getElementById(id);
    if(!obj) return false;
    var _getHeight = obj.offsetTop;

    window.onscroll = function(){
        changePos(id,_getHeight);
    };
}
function changePos(id,height){
    var obj = document.getElementById(id);
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
addLoadEvent(menuFixed('main_nav'));

// 顶部登录状态下的菜单显示/隐藏
$('.loginin_header').click(function(e){
    e.preventDefault();
    $(this).siblings('.login_nav_list').css('visibility','visible').slideToggle();
    return false;
});
// 点击页面其它区域菜单收起
$(document).bind("click", function(e) {
    var side_win = $(".login_nav_list").css("visibility");

    if($(e.target).parents(".login_nav_list,.loginin_header").length==0 &&
         side_win==="visible") {
        $('.login_nav_list').hide().css('visibility','hidden');
    }
});

// // 文本框默认文字
// function resetFields(whichform) {
//     if(Modernizr.input.placeholder) return;
//     for(var i=0; i<whichform.elements.length; i++) {
//         var element = whichform.elements[i];
//         if(element.type == 'submit') continue;
//         var check = element.placeholder || element.getAttribute('placeholder');
//         if(!check) continue;
//         element.onfocus = function() {
//             var text = this.placeholder || this.getAttribute('placeholder');
//             if(this.value == text) {
//                 // this.className = '';
//                 removeClass(this,'placeholder');
//                 this.value = '';
//             }
//         };
//         element.onblur = function() {
//             if(this.value == '') {
//                 addClass(this,'placeholder');
//                 // this.className = 'placeholder';
//                 this.value = this.placeholder || this.getAttribute('placeholder');
//             }
//         };
//         element.onblur();
//     }
// }
// // prepareForms
// function prepareForms() {
//     for(var i=0; i<document.forms.length; i++) {
//         var thisform = document.forms[i];
//         resetFields(thisform);
//     }
// }
// addLoadEvent(prepareForms());

// 返回顶部
function goTop() {
    $(window).scroll(function(e) {
        //若滚动条离顶部大于100元素
        if($(window).scrollTop()>100)
            $("#gotop").fadeIn(1000);//以1秒的间隔渐显id=gotop的元素
        else
            $("#gotop").fadeOut(1000);//以1秒的间隔渐隐id=gotop的元素
    });
}
$(function(){
    var docWidth = $(document).width();
    $("#gotop,.fixed_signin").css("right",(docWidth - 1200)/2 - 73);
    docWidth = null;
    //点击回到顶部的元素
    $("#gotop").click(function(e) {
            //以1秒的间隔返回顶部
            $('body,html').animate({scrollTop:0},1000);
    });
    goTop();//实现回到顶部元素的渐显与渐隐
});

$(function() {
    // 图片上传弹窗
    $('.upload_header_btn').click(function(e) {
        e.preventDefault();
        $('body').addClass('modal_open');
        $('.modal_upload').addClass('in').show(200);
    });

    $('.modal_upload .icon_close').click(function(e) {
        e.preventDefault();
        $('body').removeClass('modal_open');

        $('.modal_upload').removeClass('in').hide();
    });
});

//签到
function signIn(){
    $.ajax({
        url : '/portal/services/customer/creditSignin.html',
        cache : false,
        type: 'POST',
        success : function(result) {
            if (result.success) {
//                    layer.msg("每日签到恭喜你! "+result.message,{icon: 6,time: 1000}, function() {
//                            location.reload();
//                        },
                var point = 1;
                if(result.data.times >= 20) {
                    point = 2;
                }
                openRedpaket(point,"cerditSignin");
            } else {
                layer.msg(result.message, {icon: 5,time: 1000});
            }
        }
    });
}

