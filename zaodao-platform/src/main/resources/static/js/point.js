/** point 名字空间 */
$.namespace('jQuery.point');

/**
 * 请求兑换视图
 */
$.point.paymentShow = function() {
	var quantity = $('#quantity').val();
	var goodsId = $('#goodsId').val();
	var deliveryType = $('#deliveryType').val();
	var heigth="500px";
	if(deliveryType==2){
		heigth="300px";
	}
	$.layer({
		type : 1,
		closeBtn : [ 0, true ],
		shadeClose : false,
		border : [0],
		page : {
			url : "/portal/shop/exchangeGoods.html?quantity=" + quantity
            + "&goodsId=" + goodsId
		},
		title : [
		         "积分换商品",
		         'border:none; background:#ff8900; color:#fff;'
        ],
		area : [ '500px', heigth ],
		end : function() {
		}
	});
}

/**
 * 积分兑换支付
 */
$.point.payment = function(id) {
	var loading = $.aportal.loading("请求中...");
	var password=$('#password').val();
	var deliveryType = $('#deliveryType').val();
	var quantity = $('#quantity').val();
	var realName = $('#realName').val();
	var mobileNo = $('#mobileNo').val();
	var provName = $('#liveProvince').val();
	var cityName = $('#liveCity').val();
	var distName = $('#liveCounty').val();
	var address = $('#address').val();
	var goodsId = $('#goodsId').val();
	var captcha = $('#captcha').val();
	if (!password || password == '') {
		$.aportal.alert("请输入登陆密码");
		return;
	}

	if (!captcha || captcha == '') {
		$.aportal.alert("请输入图像验证码");
		return;
	}

	if (deliveryType == 1
			&& (!mobileNo || !realName || !provName || !cityName || !distName || !address)) {
		$.aportal.alert("请输入收货人信息");
		return;
	}
	if (deliveryType == 2 && (!mobileNo || !realName)) {
		$.aportal.alert("请输入收货人信息(姓名和手机号码)");
		return;
	}

	$.ajax({
		url : "/portal/shop/paymentAction.html?timstap=" + new Date(),
		type : 'post',
		data : {
			"goodsId" : goodsId,
			"quantity" : quantity,
			"realName" : realName,
			"mobileNo" : mobileNo,
			"provName" : provName,
			"cityName" : cityName,
			"distName" : distName,
			"address" : address,
			"password" : password,
			"captcha" : captcha
		},
		cache : false,
		success : function(data) {
			$.aportal.loaded(loading);
			if (data && data.success) {
				$.aportal.confirm("积分兑换", "兑换请求成功，您需要立即查看订单吗？", function() {
					location.href = '/portal/services/customer/childpage.html?childIndex=integralOrder';
				}, function() {
					location.reload(); // 自动关闭后可做一些刷新页面等操作
				}, "立即查看", "关闭");
			} else {
				if (data.message) {
					captchaImageRefresh();
					$.aportal.alert(data.message)
				}
			}
		},
		error : function(e) {
			captchaImageRefresh();
			$.aportal.loaded(loading);
			$.aportal.alert(e)
		}
	});
}

$.point.sendCaptchaButton = function() {
	var pointsAmount=$('#pointsAmount').html();
	var loading = $.aportal.loading("短信发送中...");
	$.ajax({
		url : "/portal/shop/sendCaptcha.html?timstap=" + new Date(),
		data : {
			"pointAmount" : pointsAmount,
			"csrfToken" : $("#csrfToken").val()
		},
		cache : false,
		success : function(data) {
			if (data && data.success) {
				$("#sendCaptchaButton").attr('disabled', true);
				$("#captchaId").val(data.data.captchaId);
				i = 120;
				sendCountdown('sendCaptchaButton');
			}
			$.aportal.loaded(loading);
			if (data.message) {
				$.aportal.msg(data.message)
			}
		}
	});
	return false;
}

// 计数器
function sendCountdown(buttonId) {
	i--;
	if (i < 0) {
		$("#" + buttonId).attr('disabled', false);
		$("#" + buttonId).text("重新下发短信");
	} else {
		$("#" + buttonId).text("倒计时" + i + "秒");
		setTimeout("sendCountdown('" + buttonId + "')", 1000)
	}
}
