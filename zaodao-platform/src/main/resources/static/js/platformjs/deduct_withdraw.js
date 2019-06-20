$(function () {
    $("#deposit_amount_form_id").Validform({
        tiptype: function (msg, o, cssctl) {
            if (!o.obj.is("form")) {
                var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                // alert(o.obj[0].tagName);
                cssctl(objtip, o.type);
                objtip.text(msg);
            }
        },
        ajaxPost: true,
        showAllError: true,
        usePlugin: {
            jqtransform: {
                //会在当前表单下查找这些元素;
                selector: ":checkbox,:radio,.decorate"
            }
        },
        datatype: {//传入自定义datatype类型，可以是正则，也可以是函数（函数内会传入一个参数）;
            amount: function (gets, obj, curform, regxp) {
                var reg1 = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
                if (reg1.test(gets)) {
                    return true;
                }
                return false;
            },
        },
        callback: function (data) {
            if (data.success) {
                window.location.href = '/portal/services/deductWithdraw/deposit_' + data.data.orderNo + '.html';
            } else {
                $.platform.msg(data.message, 1000, 5);
            }
        }
    });

    $("#withdraw_amount_from_id").Validform({
        tiptype: function (msg, o, cssctl) {
            if (!o.obj.is("form")) {
                var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                // alert(o.obj[0].tagName);
                cssctl(objtip, o.type);
                objtip.text(msg);
            }
        },
        ajaxPost: true,
        showAllError: true,
        usePlugin: {
            jqtransform: {
                //会在当前表单下查找这些元素;
                selector: ":checkbox,:radio,.decorate"
            }
        },
        datatype: {//传入自定义datatype类型，可以是正则，也可以是函数（函数内会传入一个参数）;
            amount: function (gets, obj, curform, regxp) {
                var reg1 = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
                if (reg1.test(gets)) {
                    return true;
                }
                return false;
            },
        },
        beforeSubmit: function (curform) {
            var isBindCard = $("#is_bind_card_id").val();
            if (isBindCard == 0) {
                $.platform.msg("还没有绑定银行卡", 1000, 5);
                return false;
            }
            var balance = parseFloat($("#customer_balance_id").val());
            var withdrawAmount = parseFloat($("#withdraw_amount_id").val());
            if (withdrawAmount > balance) {
                $.platform.msg("提现金额不能大余额", 1000, 5);
                return false;
            }
        },
        callback: function (data) {
            if (data.success) {
                layer.msg("提现成功，24小时内到账",
                    {
                        time: 2000,
                        icon: 6,
                        skin: 'layui-layer-rim',
                        closeBtn: 1
                    }, function () {
                        location.reload();
                    })
            } else {
                $.platform.msg(data.message, 1000, 5);
            }
        }
    });
});

//短信验证码
function clickSendCode() {
    var amount = $("#withdraw_amount_id").val();
    if (amount == '' || amount == null) {
        $.platform.msg("金额不能为空", 1000, 5);
        return false;
    }
    //发送短息
    doPostBack();
}

//将手机利用ajax提交到后台的发短信接口
function doPostBack() {
    var amount = $("#withdraw_amount_id").val();
    $.ajax({
        type: 'post',
        url: "/portal/services/deductWithdraw/sendCaptchaSms.html",// 请求的action路径
        dataType: "json",
        async: true,
        data: {"type": "withdraw", "amount": amount},
        error: function (data) {
            layer.alert('发送失败', {icon: 11, offset: '280px'});
        },
        success: function (data) {
            if (!data.success) {
                $.platform.msg(data.message, 1000, 5);
            } else {
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
        $("#click_send_sms_id").attr("onclick", "clickSendCode()");
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