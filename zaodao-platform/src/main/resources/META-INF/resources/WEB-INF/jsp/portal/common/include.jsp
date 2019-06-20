<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico" media="screen"/>

<!-- site style -->
<link rel="stylesheet" href="/css/normalize-min.css"/>
<link rel="stylesheet" href="/css/base.css"/>
<link rel="stylesheet" href="/css/swiper.min.css">
<link rel="stylesheet" href="/css/public.css"/>
<link rel="stylesheet" href="/css/calendar.css">
<link rel="stylesheet" href="/css/home.css"/>
<link rel="stylesheet" href="/css/login_regist.css"/>
<link rel="stylesheet" href="/css/search_result.css"/>
<link rel="stylesheet" href="/css/idangerous.swiper.css">
<link rel="stylesheet" href="/js/jqtransformplugin/jqtransform.css"/>
<link rel="stylesheet" href="/css/detail.css">
<link rel="stylesheet" href="/css/calendar.css">
<link rel="stylesheet" href="/css/bxslider.css">
<link rel="stylesheet" href="/css/kalendae.css">
<link rel="stylesheet" href="/css/pay_detail.css">
<link rel="stylesheet" href="/css/find_pwd.css">
<link rel="stylesheet" href="/css/write_blog.css">

<!-- jquery -->
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/jquery.form.js" charset="utf-8"></script>
<script src="/js/swiper.min.js"></script>
<%--<script src="/js/modernizr.min.js"></script>--%>
<%--<script type="text/javascript" src="/js/global.js"></script>--%>
<script src="/js/common/mycommon.js" charset="utf-8"></script>
<!-- 表单美化 -->
<script type="text/javascript" src="/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/js/jquery.jqtransform.js"></script>

<script src="/js/pcasclass.js" type="text/javascript"></script>
<!-- 日历插件 -->
<script type="text/javascript" src="/js/calendar.js"></script>
<script src="/js/idangerous.swiper.min.js"></script>

<!-- layerUi -->
<script src="/plugins/layer/layer.js" charset="utf-8"></script>
<script type="text/javascript" src="/js/platformjs/my_extend_layer_fun.js"></script>
<script src="/plugins/laydate/laydate.js" charset="utf-8"></script>
<!-- slider -->
<script src="/js/jquery.bxslider.min.js"></script>
<!-- handlebars -->
<script src="/js/handlebars-v4.0.5.js" charset="utf-8"></script>
<script src="/js/common/myif.js" charset="utf-8"></script>
<script src="/js/common/handlebarsextend.js" charset="utf-8"></script>
<script src="/js/common/imageCaptcha.js" charset="utf-8"></script>
<!-- 分页插件-->
<script src="/js/jqPaginator.js"></script>
<!-- tab切换 -->
<script type="text/javascript" src="/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="/js/jquery.easing.1.3.js"></script>

<!-- 登录注册弹出框 -->
<script type="text/javascript" src="/js/login_regist.js"></script>

<!-- 红包弹出框 -->
<script type="text/javascript" src="/js/redpaket.js"></script>


<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';

    /**
     * CSRF-Token 客户端支持脚本。
     */
        // CSRF支持ajax表单提交
    var token = $("meta[name='X-CSRF-TOKEN']").attr("content");// 从meta中获取token
    $(document).ajaxSend(function (e, xhr, options) {
        if (!options.crossDomain) {
            xhr.setRequestHeader("X-CSRF-TOKEN", token);// 每次ajax提交请求都会加入此token
        }
    });
    $(function () {
        // CSRF自动为当前页码的所有表单添加hidden(csrfToken)
        $("form").each(function () {
            if ($(this).attr('enctype') == 'multipart/form-data') {
                var action = $(this).attr('action');
                action += (action.indexOf('?') != -1 ? '&' : '?');
                action += "_csrf=${requestScope['org.springframework.security.web.csrf.CsrfToken'].token}";
                $(this).attr('action', action);
            } else {
                $(this).append("<input type=\"hidden\" name=\"_csrf\" value=\"${requestScope['org.springframework.security.web.csrf.CsrfToken'].token}\"/>");
            }
        });
    });

    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?ccc8f58a56be9fd285454c25b270f639";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
