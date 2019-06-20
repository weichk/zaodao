$(function() {
    $(".apply_guide_info").Validform({
        tiptype: function(msg, o, cssctl) {
            if (!o.obj.is("form")) {
                var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                cssctl(objtip, o.type);
                objtip.text(msg);
            }
        },
        showAllError: true,
        usePlugin: {
            jqtransform: {
                //会在当前表单下查找这些元素;
                selector: "select,:checkbox,:radio,.decorate"
            }
        },
        beforeSubmit: function (curform) {
            loading = top.layer.load(2, {
                shade: [0.3, '#eee'] //0.1透明度的白色背景
            });
        },
        callback: function (data) {
            $.platform.msg('设置密保成功！', 1000, 6);
        }
    });

});
