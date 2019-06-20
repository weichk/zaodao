$(function () {
    $("#binding_card_from_id").Validform({
        tiptype: function (msg, o, cssctl) {
            if (!o.obj.is("form")) {
                var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                cssctl(objtip, o.type);
                objtip.text(msg);
            }
        },
        ajaxPost:true,
        showAllError: true,
        usePlugin: {
            jqtransform: {
                //会在当前表单下查找这些元素;
                selector: "select,:checkbox,:radio,.decorate"
            }
        },
        datatype: {//传入自定义datatype类型，可以是正则，也可以是函数（函数内会传入一个参数）;
            "idcard":function(gets,obj,curform,datatype){
                //该方法由佚名网友提供;

                var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子;
                var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值，10代表X;

                if (gets.length == 15) {
                    return isValidityBrithBy15IdCard(gets);
                }else if (gets.length == 18){
                    var a_idCard = gets.split("");// 得到身份证数组
                    if (isValidityBrithBy18IdCard(gets)&&isTrueValidateCodeBy18IdCard(a_idCard)) {
                        return true;
                    }
                    return false;
                }
                return false;

                function isTrueValidateCodeBy18IdCard(a_idCard) {
                    var sum = 0; // 声明加权求和变量
                    if (a_idCard[17].toLowerCase() == 'x') {
                        a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作
                    }
                    for ( var i = 0; i < 17; i++) {
                        sum += Wi[i] * a_idCard[i];// 加权求和
                    }
                    valCodePosition = sum % 11;// 得到验证码所位置
                    if (a_idCard[17] == ValideCode[valCodePosition]) {
                        return true;
                    }
                    return false;
                }

                function isValidityBrithBy18IdCard(idCard18){
                    var year = idCard18.substring(6,10);
                    var month = idCard18.substring(10,12);
                    var day = idCard18.substring(12,14);
                    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
                    // 这里用getFullYear()获取年份，避免千年虫问题
                    if(temp_date.getFullYear()!=parseFloat(year) || temp_date.getMonth()!=parseFloat(month)-1 || temp_date.getDate()!=parseFloat(day)){
                        return false;
                    }
                    return true;
                }

                function isValidityBrithBy15IdCard(idCard15){
                    var year =  idCard15.substring(6,8);
                    var month = idCard15.substring(8,10);
                    var day = idCard15.substring(10,12);
                    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
                    // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
                    if(temp_date.getYear()!=parseFloat(year) || temp_date.getMonth()!=parseFloat(month)-1 || temp_date.getDate()!=parseFloat(day)){
                        return false;
                    }
                    return true;
                }
            }
        },
        beforeSubmit:function (curform) {
            loading = top.layer.load(2, {
                shade: [0.3,'#eee'] //0.1透明度的白色背景
            });
        },
        callback:function(data){
            if (data.success) {
                $.platform.msg('绑卡成功！', 1000, 6,null,null,'/portal/services/customer/childpage.html?childIndex=accountConfigue');
            } else {
                $.platform.msg(data.message, 1000, 5);
            }
        }
    });
});

///短信验证码
function clickSendCode() {
    // if ($('#binding_card_mobile_no_id').val() == '' || $('#binding_card_mobile_no_id').val() == null) {
    //     layer.alert('手机号不能为空', {icon: 11, offset: '280px'});
    //     return false;
    // }
    //发送短息
    doPostBack();
}

//将手机利用ajax提交到后台的发短信接口
function doPostBack() {
    var cardNo = $("#card_no_id").val();
    $.ajax({
        type: 'post',
        url: "/portal/services/deductWithdraw/sendCaptchaSms.html",// 请求的action路径
        dataType: "json",
        async: true,
        data: {"type": "signCard","cardNo":cardNo},
        error: function (data) {
            layer.alert('发送失败', {icon: 11, offset: '280px'});
        },
        success: function (data) {
            if(!data.success) {
                $.platform.msg(data.message, 1000, 5);
            }else {
                settime();//开始倒计时
            }
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