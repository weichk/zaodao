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
$.namespace('jQuery.aportal');

$.aportal.validform = function(form){
	 return $("#"+form).Validform({
        tiptype:function(msg,o,cssctl){
            if(!o.obj.is("form")){
                var objtip=o.obj.parent().find(".Validform_checktip");
                cssctl(objtip,o.type);
                objtip.text(msg);
            }
        },
        showAllError:true,
        usePlugin:{
            jqtransform:{
                //会在当前表单下查找这些元素;
                selector:"select,:checkbox,:radio"
            }
        },
        beforeSubmit:function(curform){
        	$.aportal.loading('请求中...');
        }
    });
}

$.aportal.loading = function(msg) {
	if (!msg)
		msg = '加载中…';
	return layer.load(msg);
};

$.aportal.msg = function(msg,time,icon){
	if (!msg)
		msg = '加载中…';
	if(!time) time = 3;
	if(!icon) icon = 1;
	return layer.msg(msg,time,icon);
}

$.aportal.alert = function(msg){
	if (!msg)
		msg = '加载中…';
	return layer.alert(msg, 3, !1);
}

$.aportal.alert = function(msg,icon){
	if (!msg)
		msg = '加载中…';
	if(!icon) icon = 1;
	return layer.alert(msg, icon, !1);
}


$.aportal.loaded = function(loadi) {
	layer.close(loadi);
};


$.aportal.sampleConfirm = function(title,msg,yesCallback,noCallback,yesLabel,noLabel){
	if(!title) title = "确定?";
	if(!msg) msg = "您确定该操作吗?";
	if(!yesLabel) yesLabel = "确定";
	if(!noLabel) noLabel = "取消";
	$.layer({
		title : title,
	    shade: [0],
	    area: ['auto','auto'],
	    dialog: {
	        msg: msg,
	        btns: 2,
	        type: 4,
	        btn: [yesLabel,noLabel],
	        yes: function(){
	    		if(yesCallback && typeof(yesCallback)=="function"){
	    			yesCallback.call(this);
	    		}
	        }, no: function(){
	    		if(noCallback && typeof(noCallback)=="function"){
	    			noCallback.call(this);
	    		}
	        }
	    }
	});
}


$.aportal.confirm = function(title,msg,yesCallback,noCallback,yesLabel,noLabel,top,left){
	if(!title) title = "确定?";
	if(!msg) msg = "您确定该操作吗?";
	if(!yesLabel) yesLabel = "确定";
	if(!noLabel) noLabel = "取消";
	if(!top) top='';
	if(!left) left='';
	$.layer({
		closeBtn : [ 0, true ],
		shadeClose : false,
		dialog : {
			type : 4,
			btns : 2,
			btn : [ yesLabel, noLabel ],
			msg : msg,
			yes : function(index) {
				if(yesCallback && typeof(yesCallback)=="function"){
					yesCallback.call(this);
				}
			},
			no : function(index) {
				try{
					if(noCallback && typeof(noCallback)=="function"){
						noCallback.call(this);
					}
				}catch (e){}
				$.aportal.closeLayer(this);
			}
		},
		title : [ title, 'border:none; background:#ff8900; color:#fff;' ],
		border : [ 4, 0.5, '#ff8900' ],
		offset: [top, left],
		area : [ '300px', 'auto' ],
	});
}




$.aportal.iframe = function(url, title, data) {
	if (!title)
		title = false;
	$.layer({
				type : 2,
				closeBtn : [0, true],
				shadeClose : true,
				iframe : {
					src : url
				},
				title : title,
				area : ['600px', '500px']
			});
}

$.aportal.page = function(url, title,width,height,data,endFunc) {
	if (!title) title = false;
	if (!width) width = 600;
	if (!title) height = 500;
	if(!endFunc){
		endFunc = function(){};
	}
	$.layer({
		type : 1,
		closeBtn : [0, true],
		shadeClose : true,
		offset: [($(window).height() - 290)/2+'px', ''],
		page : {
			url : url
		},
		title : title,
		area : [width+'px', height+'px'],
		end : endFunc
	});
}


$.aportal.pageContext = function(contentId, title,width,height,endFunc) {
	if (!title) title = false;
	if (!width) width = 600;
	if (!title) height = 500;
	if(!endFunc){
		endFunc = function(){};
	}
	$.layer({
		type : 1,
		closeBtn : [0, true],
		shadeClose : false,
		offset: [($(window).height() - 290)/2+'px', ''],
		page : {
			html : $("#"+contentId).html()
		},
		title : title,
		area : [width+'px', height+'px'],
		end : endFunc
	});
}

$.aportal.pageHtml = function(context) {
	$.layer({
		type : 1,
		title : false,
		fix : false,
		area : ['600px', '500px'],
		page : {
			html : context
		}
	});
};

$.aportal.welcome = function(context) {
	$.layer({
		type : 1,
		title : false,
		fix : false,
		border: [0],
		//closeBtn:false,
		shadeClose:true,
		offset:['123px',''],
		shade: [0.5, '#000'],
		area : ['800px', 'auto'],
		bgcolor:'',
		move: '.aportal_welcome_content',
		page : {
			html : "<div class='aportal_welcome_content' style='margin:0px auto;width:100%;'>"+context+"</div>"
		},
		close: function(index){
			$.cookie('aportal_welcome','1');
		}
	});
};


$.aportal.closeLayer = function(obj){
	var l = $(obj).closest('.xubox_layer');
	if(l.length == 0) return;
	var i = l.attr('id').substring(11);
	if(!i) return;
	layer.close(i);
};

$.aportal.refreshCaptcha = function(imgId){
	if(!imgId)imgId="jcaptchaImage";
	$('#'+imgId).attr("src", "/jcaptcha.jpg?" + new Date());
};

$.aportal.loginTips=function(obj){
	layer.tips('<div>请先登录或注册!</div><div style="text-align:center;"><a href="/portal/portlet/login_register.html" style="margin-right:25px;margin-left:22px;color: white;">登 录</a><a href="/portal/portlet/login_register.html?type=register" style="color: white;">注 册</a></div>', obj, {
		style: ['background-color:#ff8900; color:#fff', '#ff8900'],
	    maxWidth:185,
	    closeBtn:[0,true] //显示关闭按钮
	});
}


$.aportal.showTips=function(obj,contents){
	layer.tips(contents, obj, {
		style: ['background-color:#ff8900; color:#fff', '#ff8900'],
	    maxWidth:200,
	    closeBtn:[0,true] //显示关闭按钮
	});
}

$.aportal.showBigTips=function(obj,contents){
	layer.tips(contents, obj, {
		style: ['background-color:#fff; color:#fff', '#fff'],
	    maxWidth:500,
	    closeBtn:[0,true] //显示关闭按钮
	});
}

/**
 * 显示图片(类似微信)
 */
$.aportal.showPictureTips=function(obj,contents){
	layer.tips(contents, obj, {
		style: ['background-color:#fff; color:#fff', '#fff'],
	    maxWidth:500,
	    closeBtn:[0,true] //显示关闭按钮
	});
}

$.aportal.logout = function(){
	layer.confirm("确认要退出登录吗？", function(){
		$.SiteIM.disconnect();
		location.href="/portal/services/logout.html";
	});
}
