/**
 * 基础acooly框架功能封装(流程审核)
 *
 * @author cuifuq
 * @date 2017-3-30
 * @version: 1.0.0
 * @required JQuery EasyUI
 */
(function ($) {
    $.extend({
        flowAudit: {
            selectedMenu: null,
            westTree: null
        }
    });
    $.extend($.flowAudit, {
        framework: {

            //页面显示
            _getCanonicalUrl: function (url, id) {
                url = contextPath + url;
                url += (url.indexOf('?') != -1 ? '&' : '?');
                url += 'id=' + id
                return url;
            },
            
            //表单序列化
            _formSerializeArray: function (form) {
            	var formDatas=[];
                var formDataArray=JSON.stringify($("#"+form).serializeArray());
                var jsonObj = eval("(" + formDataArray + ")");
                for(var data in jsonObj){  
               	 formDatas.push('"'+jsonObj[data].name+'":"'+jsonObj[data].value+'"');
                }
                return "{"+formDatas+"}";
            },
            
            /**
             * 公共流程审核
             */
            commonFlowAudit: function (opts) {
                if (opts.entity) {
                    $.flowAudit.framework.doCommonFlowAudit(opts.auditPassUrl,opts.auditPassButton,opts.auditRejectUrl,opts.auditRejectButton,opts.auditEndUrl,opts.auditEndButton,opts.auditPagesUrl,
                    	opts.id, opts.jsonData, opts.form, "manage_" + opts.entity + "_datagrid",
                        opts.auditPages, opts.top, opts.width, opts.height, opts.reload, opts.maximizable, opts.onSubmit, opts.onSuccess, opts.onCloseWindow);
                } else {
                    $.flowAudit.framework.doCommonFlowAudit(opts.auditPassUrl,opts.auditPassButton,opts.auditRejectUrl,opts.auditRejectButton,opts.auditEndUrl,opts.auditEndButton,opts.auditPagesUrl,
                    	opts.id, opts.jsonData, opts.form, opts.datagrid,
                        opts.auditPages, opts.top, opts.width, opts.height, opts.reload, opts.maximizable, opts.onSubmit, opts.onSuccess, opts.onCloseWindow);
                }
            },

            /**
             * 执行公共流程审核
             */
            doCommonFlowAudit: function (auditPassUrl,auditPassButton,auditRejectUrl,auditRejectButton, auditEndUrl, auditEndButton,auditPagesUrl, id, jsonData, form, datagrid, auditPages, top, width, height, reload, maximizable, onSubmitCallback, successCallback, onCloseWindow) {
                var t = top ? top : null;
                var w = width ? width : 500;
                var h = height ? height : 'auto';
                var tt = auditPages ? auditPages : '业务审核';
                
                var auditPassButtonName = auditPassButton ? auditPassButton : '审核通过';
                var auditRejectButtonName = auditRejectButton ? auditRejectButton : '审核驳回';
                var auditEndButtonName = auditEndButton ? auditEndButton : '结束流程';

                
                var max = maximizable ? maximizable : false;
                if (!id || id == '') {
                    var selectedRow = $.acooly.framework.getSelectedRow(datagrid);
                    if (selectedRow && selectedRow != null) id = selectedRow.id;
                }
                if (!id || id == '') {
                    $.messager.alert('提示', '请选择需要审核的数据');
                    return;
                }
                var href = $.flowAudit.framework._getCanonicalUrl(auditPagesUrl, id);
                $('<div/>').dialog({
                    href: href,
                    title: tt, top: t, width: w, height: h, modal: true, iconCls: 'icon-save', maximizable: max,
                    buttons: [
                        {
                            id: 'auditPassUrl',
                            text: auditPassButtonName,
                            iconCls: 'icon-yes',
                            handler: function () {
                                var d = $(this).closest('.window-body');
                                //审核意见
                                var auditOpinion = $("#auditOpinion").val();
                                //注释审核通过意见必填
                                // if (auditOpinion == '') {
                                //     $.messager.show({
                                //         title: '提示',
                                //         msg: '请填写审核意见!'
                                //     });
                                //     return;
                                // }
                                
                              //审核意见
                              var auditOpinion = $("#auditOpinion").val();
                              jsonData.auditOpinion = auditOpinion;
                              
                              //表单序列化
                              var formDatas = $.flowAudit.framework._formSerializeArray(form);
                              jsonData.formDatas = formDatas;
                              //console.log(jsonData.formDatas);

                              jQuery.ajax({
                                    url: auditPassUrl,
                                    cache: false,
                                    data: jsonData,
                                    success: function (data) {
                                        d.dialog('destroy');
                                        $('#' + datagrid).datagrid('load');
                                        $.messager.show({
                                            title: '审核提示',
                                            msg: data.message
                                        });
                                    }
                                });
                                $('#' + datagrid).datagrid('reload');

                            }
                        }, {
                            id: 'auditRejectUrl',
                            text: auditRejectButtonName, 
                            iconCls: 'icon-no',
                            handler: function () {
                                var d = $(this).closest('.window-body');
                                //审核意见
                                var auditOpinion = $("#auditOpinion").val();
                                if (auditOpinion == '') {
                                    $.messager.show({
                                        title: '提示',
                                        msg: '请填写审核意见!'
                                    });
                                    return;
                                }
                                jsonData.auditOpinion = auditOpinion;
                                
                                //表单序列化
                                var formDatas = $.flowAudit.framework._formSerializeArray(form);
                                jsonData.formDatas = formDatas;
                                
                                jQuery.ajax({
                                    url: auditRejectUrl,
                                    cache: false,
                                    data: jsonData,
                                    success: function (data) {
                                        d.dialog('destroy');
                                        $('#' + datagrid).datagrid('load');
                                        $.messager.show({
                                            title: '审核提示',
                                            msg: data.message
                                        });
                                    }
                                });
                                $('#' + datagrid).datagrid('reload');
                            }
                        },
                        {
                            id: 'auditEndUrl',
                            text: auditEndButtonName, 
                            iconCls: 'icons-resource-control-stop-blue',
                            handler: function () {
                                var d = $(this).closest('.window-body');
                                //审核意见
                                var auditOpinion = $("#auditOpinion").val();
                                if (auditOpinion == '') {
                                    $.messager.show({
                                        title: '提示',
                                        msg: '请填写审核意见!'
                                    });
                                    return;
                                }
                                jsonData.auditOpinion = auditOpinion;
                                
                                //表单序列化
                                var formDatas = $.flowAudit.framework._formSerializeArray(form);
                                jsonData.formDatas = formDatas;

                                jQuery.ajax({
                                    url: auditEndUrl,
                                    cache: false,
                                    data: jsonData,
                                    success: function (data) {
                                        d.dialog('destroy');
                                        $('#' + datagrid).datagrid('load');
                                        $.messager.show({
                                            title: '审核提示',
                                            msg: data.message
                                        });
                                    }
                                });
                                $('#' + datagrid).datagrid('reload');
                            }
                        }, {
                            text: '关闭', iconCls: 'icon-cancel',
                            handler: function () {
                                var d = $(this).closest('.window-body');
                                d.dialog('close');
                            }
                        }
                    ],
                    onClose: function () {
                        if (onCloseWindow) {
                            onCloseWindow();
                        }
                        $(this).dialog('destroy');
                    }
                });
                if(!auditPassUrl){
                    $("#auditPassUrl").hide();
                }
                if(!auditRejectUrl){
                    $("#auditRejectUrl").hide();
                }
                if(!auditEndUrl){
                    $("#auditEndUrl").hide();
                }
            },


            /**
             * 公共流程审核不通过
             */
            commonContenderAuditNotPassed: function (opts) {
                if (opts.entity) {
                    $.flowAudit.framework.doCommonFlowAuditNotPassed(opts.auditReject, opts.url, opts.id, "manage_" + opts.entity + "_editform", "manage_" + opts.entity + "_datagrid",
                        opts.title, opts.top, opts.width, opts.height, opts.reload, opts.maximizable, opts.onSubmit, opts.onSuccess, opts.onCloseWindow);
                } else {
                    $.flowAudit.framework.doCommonFlowAuditNotPassed(opts.auditReject, opts.url, opts.id, opts.form, opts.datagrid,
                        opts.title, opts.top, opts.width, opts.height, opts.reload, opts.maximizable, opts.onSubmit, opts.onSuccess, opts.onCloseWindow);
                }
            },


            /**
             * 执行公共流程审核不通过
             */
            doCommonFlowAuditNotPassed: function (auditReject, url, id, form, datagrid, title, top, width, height, reload, maximizable, onSubmitCallback, successCallback, onCloseWindow) {
                var t = top ? top : null;
                var w = width ? width : 500;
                var h = height ? height : 'auto';
                var tt = title ? title : '审核';
                var max = maximizable ? maximizable : false;
                if (!id || id == '') {
                    var selectedRow = $.tender.framework.getSelectedRow(datagrid);
                    if (selectedRow && selectedRow != null) id = selectedRow.id;
                }
                if (!id || id == '') {
                    $.messager.alert('提示', '请选择需要审核的数据');
                    return;
                }
                var href = $.flowAudit.framework._getCanonicalUrl(url, id);
                $('<div/>').dialog({
                    href: href,
                    title: tt, top: t, width: w, height: h, modal: true, iconCls: 'icon-save', maximizable: max,
                    buttons: [
                        {
                            text: '审核不通过', iconCls: 'icon-cancel',
                            handler: function () {
                                var d = $(this).closest('.window-body');
                                //审核意见
                                var auditOpinion = $("#auditOpinion").val();
                                if (auditOpinion == '') {
                                    $.messager.show({
                                        title: '提示',
                                        msg: '请填写审核意见!'
                                    });
                                    return;
                                }

                                jQuery.ajax({
                                    url: auditReject,
                                    cache: false,
                                    data: {"id": id, "auditOpinion": auditOpinion},
                                    success: function (data) {
                                        d.dialog('destroy');
                                        $('#' + datagrid).datagrid('load');
                                        manage_loading_datagrid_onClickRow();
                                        if (data.length > 0) {
                                            $.messager.show({
                                                title: '审核提示',
                                                msg: data
                                            });
                                        }
                                    }
                                });
                            }
                        }, {
                            text: '关闭', iconCls: 'icon-cancel',
                            handler: function () {
                                var d = $(this).closest('.window-body');
                                d.dialog('close');
                            }
                        }],
                    onClose: function () {
                        if (onCloseWindow) {
                            onCloseWindow();
                        }
                        $(this).dialog('destroy');
                    }
                });
            },


            //------end
        }
    });
})(jQuery);
