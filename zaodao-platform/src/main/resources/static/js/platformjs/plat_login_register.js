$(function () {
    $(".login_form").Validform({
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
        beforeSubmit: function (curform) {
            loading = top.layer.load(2, {
                shade: [0.3, '#eee'] //0.1透明度的白色背景
            });
        },
        callback: function (data) {
            if (data.success) {
                if (data.data.isGuide == 1) {
                    setTimeout("window.location.href = '/portal/services/customer/childpage.html?childIndex=myArticle'", 1000);
                } else {
                    setTimeout("window.location.href = '/portal/services/customer/childpage.html?childIndex=accountConfigue'", 1000);
                }
                loginSuccessAlert();
            } else {
                $.platform.msg(data.message, 1000, 5);
            }
        }
    });

    function loginSuccessAlert() {
        layer.open({
            type: 1,
            title: false,
            area: ['590px', '398px'],
            closeBtn: 0,
            shadeClose: true,
            skin: 'popup_welcomeback',
            content: '<div class="box"><img src="/images/welcome_back.png" alt="欢迎回来" /></div>',
            success: function (layero, index) {
                setTimeout(function () {
                    layer.close(index);
                }, 2000);
            }
        });
    }

    $(".regist_form").Validform({
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
        beforeSubmit: function (curform) {
            if (!$('.jqTransformRadio').is('.jqTransformChecked')) {
                $.platform.msg('请确认您已阅读并同意早导网的服务协议', 2000, 5);
                return false;
            }
        },
        callback: function (data) {
            if (data.success) {
                layer.msg('注册成功',
                    {
                        time: 2000,
                        icon: 1,
                        skin: 'layui-layer-rim',
                        closeBtn: 1
                    }, function () {
                        layer.closeAll();
                        window.location.href = '/portal/portlet/login_register.html'
                    });
            } else {
                $.platform.msg(data.message, 2000, 5);
            }
        }
    });

    // 弹出服务协议
    $(".service_rules").on("click", function () {
        layer.open({
            type: 1,
            title: '《早导网用户服务协议》',
            area: ['800px', '500px'],
            closeBtn: 1,
            shadeClose: true,
            scrollbar: false,
            skin: 'popup_service_rule',
            content: '<div class="content_wrap"> <div class="con"><p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas dignissimos quia molestiae illo enim ratione, minus quod ea architecto recusandae at maiores, ullam omnis debitis nihil! Recusandae facere a placeat?</p><p>Possimus ut quos dolores similique, inventore libero sit nihil impedit nulla iusto quas velit temporibus veritatis voluptas eligendi nesciunt, beatae reiciendis. Est aliquid, suscipit, quasi modi natus corporis sequi facilis!</p><p>Ex expedita obcaecati, culpa! Quaerat possimus expedita accusamus, minima, aliquam odio doloribus. Voluptates reprehenderit facere natus, qui obcaecati velit magni, ipsum dolore necessitatibus quasi ullam sint, tempora reiciendis deleniti expedita.</p><p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Delectus illum quisquam doloremque atque cum doloribus veniam itaque, repellendus quos quia deleniti facilis rem asperiores laboriosam id similique accusantium alias temporibus!</p><p>Hic enim facere minima iure ullam, dolore eligendi perferendis eveniet illo, doloribus quos quis labore minus omnis. Cum ducimus inventore qui vel impedit, eligendi repellendus eaque natus, nemo molestias. Similique.</p><p>Iusto quis distinctio accusamus impedit eaque deleniti quae, maxime, quibusdam, non error quidem qui sunt eum illo quia sequi! Quos voluptatibus et magni excepturi, non ut vel. Voluptatem, cum possimus?</p></div> </div>',
            success: function (index) {
                $(".close_popup").on("click", function () {
                    layer.close(index);
                });
            }
        });
    });
});

// 标签切换
$(function () {
    $(".login_tabnav li:first-child").addClass("hover");
    $(".login_tabnav li").each(function (i) {
        $(".login_tabnav li").eq(i).click(function () {
            $(this).addClass("hover").siblings("li").removeClass("hover");
            $(".login_con .item").eq(i).show().siblings(".login_con .item").hide();
            if (i == 0) {
                $(".login_box").removeAttr("style");
            }else {
                $(".login_box").css("height", "470px");
            }
        });
    });
    var pageType = getUrlValue("type");
    if (pageType == "register") {
        $(".login_box").css("height", "470px");
        $('#register_li_id').addClass("hover").siblings("li").removeClass("hover");
        $("#register_div_id").show().siblings(".login_con .item").hide();
    }
});

//短信验证码
function clickSendCode() {
    if ($('#register_mobile_no_id').val() == '' || $('#register_mobile_no_id').val() == null) {
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
    var mobileNo = $("#register_mobile_no_id").val();
    $.ajax({
        type: 'get',
        url: "/portal/services/sendSms.html",// 请求的action路径
        dataType: "json",
        async: true,
        data: {"mobileNo": mobileNo, "sendSmsType": "register"},
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

//校验手机号是否合法
function isPhoneNum() {
    var phonenum = $("#register_mobile_no_id").val();
    var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    if (!myreg.test(phonenum)) {
        layer.alert('请输入有效的手机号码！', {icon: 11, offset: '280px'});
        return false;
    } else {
        return true;
    }
}