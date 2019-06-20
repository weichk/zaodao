$(function(){
    $("#find_pwd_phone_form_id").Validform({
        tiptype:function(msg,o,cssctl){
            if(!o.obj.is("form")){
                var objtip=o.obj.parents(".form_item").find(".Validform_checktip");
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
});

/**
 * 刷新验证码
 */
function refreshCaptcha() {
    $('#captchaPanel').show();
    $('#jcaptchaImage').attr("src", "/jcaptcha.jpg?" + new Date());
    $('#captcha').val('');
}