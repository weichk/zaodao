$(function() {

    // // 注册弹窗
    // function popupRegist() {
    //     layer.open({
    //         type: 1,
    //         title: false,
    //         area: ['430px', '480px'],
    //         closeBtn: 1,
    //         shadeClose: true,
    //         skin: 'popup_login',
    //         content: '<div class="content_wrap login_con">' +
    //         '<h2 class="title">用户注册</h2>' +
    //         '<form action="" name="regist_form" class="regist_form">' +
    //         '<div class="form_item"> ' +
    //         '<input type="text" name="mobileNo" id="register_mobile_no_id" datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码！" ajaxurl="/portal/services/verifyMobileRepeat.html" placeholder="请输入手机号码"> ' +
    //         '<div class="Validform_checktip"></div> </div>' +
    //         '<div class="form_item">' +
    //         '<input type="text" name="mobileCaptcha" datatype="*" nullmsg="请输入短信验证码！" errormsg="短信验证码错误！" placeholder="短信验证码">' +
    //         '<a href="javascript:void(0);" id="click_send_sms_id" class="get_sms" onclick="clickRegistSendCode()">获取验证码</a> ' +
    //         '<div class="Validform_checktip"></div> </div>' +
    //         ' <div class="form_item"> <input type="password" name="password" datatype="*6-16" plugin="passwordStrength" nullmsg="请输入密码" errormsg="请输入6-16位的密码！" placeholder="请输入密码"> ' +
    //         '<div class="Validform_checktip"></div> </div> ' +
    //         '<div class="form_item"> ' +
    //         '<input type="password" name="rePassword" recheck="password" datatype="*" placeholder="请再次输入密码" errormsg="您两次输入的账号密码不一致！"> ' +
    //         '<div class="Validform_checktip"></div> </div> ' +
    //         '<div class="form_item agree"> ' +
    //         '<input type="radio" name="protocolRadio"> 我同意<a href="javascript:void(0);" class="service_rules yellow">《早稻网用户服务协议》</a> </div>' +
    //         ' <input type="submit" name="regist" value="立即注册" id="regist_btn" class="btn pie"> </form></div>',
    //         success: function(layero, index) {
    //             // 弹出服务协议
    //             $(".service_rules").on("click", function() {
    //                 layer.open({
    //                     type: 1,
    //                     title: '《早稻网用户服务协议》',
    //                     area: ['800px', '500px'],
    //                     closeBtn: 1,
    //                     shadeClose: true,
    //                     scrollbar: false,
    //                     skin: 'popup_service_rule',
    //                     content: '<div class="content_wrap"> <div class="con"><p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas dignissimos quia molestiae illo enim ratione, minus quod ea architecto recusandae at maiores, ullam omnis debitis nihil! Recusandae facere a placeat?</p><p>Possimus ut quos dolores similique, inventore libero sit nihil impedit nulla iusto quas velit temporibus veritatis voluptas eligendi nesciunt, beatae reiciendis. Est aliquid, suscipit, quasi modi natus corporis sequi facilis!</p><p>Ex expedita obcaecati, culpa! Quaerat possimus expedita accusamus, minima, aliquam odio doloribus. Voluptates reprehenderit facere natus, qui obcaecati velit magni, ipsum dolore necessitatibus quasi ullam sint, tempora reiciendis deleniti expedita.</p><p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Delectus illum quisquam doloremque atque cum doloribus veniam itaque, repellendus quos quia deleniti facilis rem asperiores laboriosam id similique accusantium alias temporibus!</p><p>Hic enim facere minima iure ullam, dolore eligendi perferendis eveniet illo, doloribus quos quis labore minus omnis. Cum ducimus inventore qui vel impedit, eligendi repellendus eaque natus, nemo molestias. Similique.</p><p>Iusto quis distinctio accusamus impedit eaque deleniti quae, maxime, quibusdam, non error quidem qui sunt eum illo quia sequi! Quos voluptatibus et magni excepturi, non ut vel. Voluptatem, cum possimus?</p></div> </div>',
    //                     success: function(index) {
    //                         $(".close_popup").on("click", function() {
    //                             layer.close(index);
    //                         });
    //                     }
    //                 });
    //             });
    //             regist();
    //         }
    //     });
    // }

    // 登录
    $('.login_in').click(function(e) {
        e.preventDefault();
        popupLogin();
    });

    // 注册
    $('.regist_account').click(function(e) {
        window.location.href='/portal/portlet/login_register.html?type=register';
    });

});

// 登录弹窗
function popupLogin() {
    layer.open({
        type: 1,
        title: false,
        area: ['430px', '480px'],
        closeBtn: 1,
        shadeClose: true,
        skin: 'popup_login',
        content: '<div class="content_wrap login_con">' +
        '<h2 class="title">用户登录</h2>' +
        '<form action="/portal/services/login.html?type=" id="login_from_id" method="post" name="login_form" class="login_form">' +
        '<div class="form_item">' +
        ' <input type="text" id="login_mobile_no_id" name="mobileNo" autocomplete="off" datatype="m" nullmsg="请输入手机号码！"' +
        ' errormsg="请输入正确的手机号码！" placeholder="请输入手机号码" class="phone_num">' +
        '<i class="login_phone"></i><div class="Validform_checktip">' +
        '</div></div> ' +
        '<div class="form_item"> ' +
        '<input type="password" name="password" datatype="*6-16" autocomplete="off" nullmsg="请输入密码" errormsg="密码错误！"' +
        ' placeholder="请输入登录密码" class="pwd">' +
        '<i class="login_key"></i><div class="Validform_checktip">' +
        '</div></div>' +
        // '<div class="form_item"> <input type="text" name="login_valid" datatype="*4-4" nullmsg="验证码" errormsg="验证码错误！" placeholder="验证码" class="text">' +
        // '<span class="valid_box"><span class="valid_img"><img src="/images/code_num.png" alt="" /></span>' +
        // '<a href="#" class="valid_refresh">看不清，换一个</a></span>' +
        // '<div class="Validform_checktip">' +
        // '</div> </div> ' +
        '<div class="setting"><a href="/portal/findPassword/intoFindPassword.html" class="forget">忘记密码?</a></div> ' +
        '<input type="submit" name="login" value="立即登录" id="login_btn" class="btn pie"> ' +
        '<div class="no_account">还没有账号？<a href="/portal/portlet/login_register.html?type=register" class="regist_rightnow">立即注册！</a>' +
        '</div></form></div>',
        success: function(layero, index) {
            login(index);
            // 注册账号
            $(".regist_rightnow").on("click", function() {
                layer.close(index);
                window.location.href='/portal/portlet/login_register.html?type=register';
            });
        }
    });
}

function login(index) {
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
                layer.close(index);
                //登录成功弹出效果
                loginSuccessAlert();
                layer.close(loading);
            } else {
                $.platform.simplemsg(data.message, 1000, 5);
                layer.close(loading);
            }
        }
    });
}

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
        },
        end: function () {
            location.reload();
        }
    });
}