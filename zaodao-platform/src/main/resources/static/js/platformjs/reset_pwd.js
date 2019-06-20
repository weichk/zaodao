$(function(){
    $("#reset_pws_form_id").Validform({
        tiptype:function(msg,o,cssctl){
            if(!o.obj.is("form")){
                var objtip=o.obj.parents(".form_item").find(".Validform_checktip");
                // alert(o.obj[0].tagName);
                cssctl(objtip,o.type);
                objtip.text(msg);
            }
        },
        ajaxPost:true,
        showAllError:true,
        usePlugin:{
            jqtransform:{
                //会在当前表单下查找这些元素;
                selector:"select,:checkbox,:radio,.decorate"
            }
        },
        beforeSubmit:function (curform) {
            loading = top.layer.load(2, {
                shade: [0.3,'#eee'] //0.1透明度的白色背景
            });
        },
        callback:function(data){
            if (data.success) {
                layer.alert('修改成功！', {
                    closeBtn: 0,
                }, function () {
                    layer.closeAll();
                    window.location.href = '/portal/services/logout.html'
                });
            } else {
                layer.alert(data.message, function () {
                    layer.closeAll();
                });
            }
        }
    });
});
