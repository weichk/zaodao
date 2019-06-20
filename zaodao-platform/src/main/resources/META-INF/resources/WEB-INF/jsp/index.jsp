<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">

    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <!-- jquery -->
    <script type="text/javascript" src="/manage/assert/plugin/jquery/jquery-1.9.1.min.js" charset="utf-8"></script>
    <!-- bootstrap -->
    <link rel="stylesheet" href="/manage/assert/plugin/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/manage/assert/plugin/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/images/app/bootstrap-theme-zaodao.css">
    <script src="/manage/assert/plugin/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="/js/aportal.js"></script>
    <script src="/plugins/layer/layer.js"></script>
    <style>
        .weixin-tip {
            position: fixed;
            left: 0;
            top: 0;
            bottom: 0;
            background: rgba(0, 0, 0, 0.85);
            filter: alpha(opacity=30);
            height: 100%;
            width: 100%;
            z-index: 1000;
        }

        .weixin-tip p {
            text-align: center;
            margin-top: 10%;
            padding: 0 5%;
        }
    </style>
</head>
<body>
<div style="text-align: center; margin-left: auto; margin-right: auto; padding: 5px;">
    <div class="page-header">
        <h3>
            早导网
            <small>App下载安装</small>
        </h3>
    </div>
    <div style="color: #fe8b0c;">
        <button type="button" onclick="location.href='${android.url}'" class="btn btn-warning" aria-label="Left Align"
                style="width: 100%;">
            <span style="display: inline-block;"><img alt="android图标" src="/images/app/iconfont-android.png"
                                                      style="width:24px;padding-bottom: 5px;"> 安卓客户端</span>
        </button>
    </div>
    <div style="margin-top: 5px;">
        <!--<button type="button" onclick="alertMessage('苹 果 应 用 正 在 审 核 中 , 敬 请 期 待...')" class="btn btn-success" style="width: 100%;"> -->
        <button type="button" onclick="alertMessage('苹 果 应 用 正 在 审 核 中 , 敬 请 期 待...')" class="btn btn-warning"
                style="width: 100%;">
            <span style="display: inline-block;"><img alt="iphone图标" src="/images/app/iconfont-apple.png"
                                                      style="width:24px;padding-bottom: 7px;"> 苹果客户端</span>
        </button>
    </div>
</div>


<div id="weixin" class="weixin-tip"
     style="display: none; position:absolute; left:0px; top:0px; width:100%; height:100%">
    <p>
        <img src="/images/app/live_weixin.png" width="100%" height="100%" alt="微信打开"/>
    </p>
</div>


</body>
<script type="text/javascript">
    var ua = navigator.userAgent.toLowerCase();
    var isWeixin = ua.indexOf('micromessenger') != -1;
    if (isWeixin) {
        $("#weixin").show();
    }

    function alertMessage(message) {
        $.aportal.alert("温馨提示: <div style='color: red;'> " + message + "</div>");
    }
</script>
</html>