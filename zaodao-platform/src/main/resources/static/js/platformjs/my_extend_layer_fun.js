/**
 * 前端界面统一UI组件或公共函数
 *
 * @author zhangpu
 * @date 2014-04-06
 */
$.namespace = function() {
    var a = arguments, o = null, i, j, d;
    for (i = 0; i < a.length; i = i + 1) {
        d = a[i].split(".");
        o = jQuery;
        for (j = (d[0] == "jQuery") ? 1 : 0; j < d.length; j = j + 1) {
            o[d[j]] = o[d[j]] || {};
            o = o[d[j]];
        }
    }
    return o;
};

/** Acooly Portal 名字空间 */
$.namespace('jQuery.platform');


$.platform.msg = function(msg,time,icon,skin,closeBtn,url){
    if (!msg)
        msg = '加载中…';
    if(!time) time = 2000;
    if(!icon) icon = 1;
    if(skin == null) skin = 'layui-layer-rim';
    if(closeBtn != 0) closeBtn = 1;
    return layer.msg(msg,
        {
            time:time,
            icon:icon,
            skin:skin,
            closeBtn:closeBtn
        },function () {
        if(url != null && url != '') {
            window.location.href = url;
        }

        layer.closeAll();
    });
};

$.platform.simplemsg = function(msg,time,icon,skin,closeBtn){
    if (!msg)
        msg = '加载中…';
    if(!time) time = 2000;
    if(!icon) icon = 1;
    if(skin == null) skin = 'layui-layer-rim';
    if(closeBtn != 0) closeBtn = 1;
    return layer.msg(msg,
        {
            time:time,
            icon:icon,
            skin:skin,
            closeBtn:closeBtn
        });
};