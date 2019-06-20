<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
</head>

<body class="grey_bg">
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=customerCenter"/>

<div id="vedio_play_div_id"></div>

<script type="text/javascript" src="/plugins/ckplayer6.8/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript">
    var watchTime=0;
    var setT=null;
    function loadedHandler(){
        if(CKobject.getObjectById('ckplayer_test_viedo_id').getType()){
            CKobject.getObjectById('ckplayer_test_viedo_id').addListener('paused',pausedHandler);
        }
        else{
            CKobject.getObjectById('ckplayer_test_viedo_id').addListener('paused','pausedHandler');
        }
    }
    function pausedHandler(b){
        if(setT){
            window.clearInterval(setT);
        }
        if(!b){
            setT=window.setInterval(setFunction,1000);
        }
    }
    //    function setFunction(){
    //        watchTime+=1;
    //        CKobject._K_('nowTime').innerHTML='当前观看时间：'+watchTime;
    //    }

    var flashvars={
        f:'/media/6-3.flv',
        c:0,
        p:2,
        b:0,
        i:'/static/images/letitgo.jpg',
        loaded:'loadedHandler',
        my_url:encodeURIComponent(window.location.href)
    };
    var video=['/media/6-3.flv->video/flv'];
    CKobject.embed('/plugins/ckplayer6.8/ckplayer.swf','vedio_play_div_id','ckplayer_vedio_play_div_id','600','400',false,flashvars,video);
</script>
</body>
</html>