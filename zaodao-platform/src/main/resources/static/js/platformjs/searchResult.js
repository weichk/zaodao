<!-- Initialize Swiper -->
// var swiper = new Swiper('.swiper-container', {
//     pagination: '.swiper-pagination',
//     paginationClickable: true,
//     loop: true
// });

$(function(){
    // $(".registerform").Validform();  //就这一行代码！;

    $(".filter_form").Validform({
        tiptype:function(msg,o,cssctl){
            if(!o.obj.is("form")){
                var objtip=o.obj.parents("td").next().find(".Validform_checktip");
                // alert(o.obj[0].tagName);
                cssctl(objtip,o.type);
                objtip.text(msg);
            }
        },
        showAllError:true,
        usePlugin:{
            jqtransform:{
                //会在当前表单下查找这些元素;
                selector:":checkbox,:radio,.decorate"
            }
        }
    });
})

// var pcas = new PCAS("province,省份", "city,城市");