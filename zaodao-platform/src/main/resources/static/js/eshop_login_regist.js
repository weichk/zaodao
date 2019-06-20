$(function () {
    // 登录
    $('.login_in').click(function (e) {
        e.preventDefault();
        popupLogin();
    });

    // 注册
    $('.regist_account').click(function (e) {
        window.location.href = '/portal/portlet/login_register.html?type=register';
    });

});

// 登录弹窗
function popupLogin() {
    var loginLayer = $.layer({
        type: 1,
        shade: [0.5, '#000'],
        shadeClose: true,
        closeBtn: [1, true],
        area: ['430px', '480px'],
        title: false,
        border: [0],
        page: {
            html: '<div class="popup_login"><div class="content_wrap login_con">' +
            '<h2 class="title">用户登录</h2>' +
            '<form action="/portal/services/login.html?type=" id="eshop_login_from_id" method="post" name="login_form" class="login_form">' +
            '<div class="form_item">' +
            ' <input type="text" id="login_mobile_no_id" autocomplete="off" name="mobileNo" datatype="m" nullmsg="请输入手机号码！"' +
            ' errormsg="请输入正确的手机号码！" placeholder="请输入手机号码" class="phone_num">' +
            '<i class="login_phone"></i><div class="Validform_checktip">' +
            '</div></div> ' +
            '<div class="form_item"> ' +
            '<input type="password" name="password" datatype="*6-16" autocomplete="off" nullmsg="请输入密码" errormsg="密码错误！"' +
            ' placeholder="请输入登录密码" class="pwd">' +
            '<i class="login_key"></i><div class="Validform_checktip">' +
            '</div></div>' +
            '<div class="setting"><a href="/portal/findPassword/intoFindPassword.html" class="forget">忘记密码?</a></div> ' +
            '<input type="submit" name="login" value="立即登录" id="login_btn" class="btn pie"> ' +
            '<div class="no_account">还没有账号？<a href="/portal/portlet/login_register.html?type=register" class="regist_rightnow">立即注册！</a>' +
            '</div>' +
            '</form></div></div>'
        },
        success: function (layero) {
            login();
            // 注册账号
            $(".regist_rightnow").on("click", function () {
                layer.close(index);
                window.location.href = '/portal/portlet/login_register.html?type=register';
            });
        }
    });
}

function login() {
    $("#eshop_login_from_id").Validform({
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
                layer.close(1);
                shopLoginWelcomeBack();
                layer.close(loading);
            } else {
                $.platform.simplemsg(data.message, 1000, 5);
                layer.close(loading);
            }
        }
    });
}

function shopLoginWelcomeBack() {
    var welcomeback = $.layer({
        type: 1,
        title: false,
        move:false,
        bgcolor: '',
        shade: [0.5, '#000'],
        shadeClose: true,
        area: ['590px', '398px'],
        closeBtn: 0,
        time: 3,
        page : {
            html : '<div class="popup_welcomeback"><div class="box"><img src="/images/welcome_back.png" alt="欢迎回来" /></div></div>'
        },
        success: function(layero) {
            $('.xubox_border').hide();
        },
        end: function () {
            location.reload();
        }
    });
}
