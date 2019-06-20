$(function(){
    $(".find_pwd_form").Validform({
        tiptype:function(msg,o,cssctl){
            if(!o.obj.is("form")){
                var objtip=o.obj.parents(".form_item").find(".Validform_checktip");
                // alert(o.obj[0].tagName);
                cssctl(objtip,o.type);
                objtip.text(msg);
            }
        },
        ajaxPost:true,
        showAllError:true,
        usePlugin:{
            jqtransform:{
                //会在当前表单下查找这些元素;
                selector:":checkbox,:radio,.decorate"
            }
        },
        beforeSubmit:function (curform) {
            loading = top.layer.load(2, {
                shade: [0.3,'#eee'] //0.1透明度的白色背景
            });
        },
        callback:function(data){
            if (data.success) {
                $.platform.msg('修改成功！', 1000, 6,null,null,'/portal/portlet/login_register.html');
            } else {
                $.platform.msg(data.message, 1000, 5);
            }
        }
    });
});

//短信验证码
function clickSendCode() {
    if ($('#find_pwd_mobileNo_id').val() == '' || $('#find_pwd_mobileNo_id').val() == null) {
        $.platform.msg('手机号码格不能为空', 1000, 2);
    } else if (!$("#mobileNo_img_id").hasClass('hidden')) {
        sendCode();
    } else {
        $.platform.msg('手机号码格式错误', 1000, 2);
    }
}

//发送验证码
function sendCode() {
    var result = isPhoneNum();
    if (result) {
        settime();//开始倒计时
        //发送短息
        doPostBack();
    }
}

//将手机利用ajax提交到后台的发短信接口
function doPostBack() {
    var mobileNo = $("#find_pwd_mobileNo_id").val();
    $.ajax({
        type: 'get',
        url: "/portal/services/sendSms.html",// 请求的action路径
        dataType: "json",
        async: true,
        data: {"mobileNo": mobileNo,"sendSmsType":"findPassword"},
        error: function (data) {
            layer.alert('发送失败', {icon: 11, offset: '280px'});
        },
        success: function (data) {

        }
    });
}
/**
 * 短息验证码倒计时
 * @type {number}
 */
var countdown = 60;
function settime() {
    if (countdown == 0) {
        countdown = 60;
        $("#click_send_sms_id").removeAttr("disabled");
        $("#click_send_sms_id").attr("onclick","clickSendCode()");
        $("#click_send_sms_id").html("获取验证码");
        return;
    } else {
        $("#click_send_sms_id").attr("disabled", true);
        $("#click_send_sms_id").removeAttr("onclick");
        $("#click_send_sms_id").html("重新发送(" + countdown + ")");
        countdown--;
    }
    setTimeout(function () {
        settime()
    }, 1000);
}

//校验手机号是否合法
function isPhoneNum() {
    var phonenum = $("#find_pwd_mobileNo_id").val();
    var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    if (!myreg.test(phonenum)) {
        layer.alert('请输入有效的手机号码！', {icon: 11, offset: '280px'});
        return false;
    } else {
        return true;
    }
}
