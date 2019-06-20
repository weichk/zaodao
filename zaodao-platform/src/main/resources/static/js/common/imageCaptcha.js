function captchaImageRefresh() {
	$('#captchaImage').attr("src", "/jcaptcha.jpg?" + new Date());
	$('#captcha').val('');
}