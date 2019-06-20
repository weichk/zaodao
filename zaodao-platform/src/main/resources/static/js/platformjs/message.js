$(function () {

    // tab slide function
    function tabSlide(nav, idc, item) {
        var indicator = $(idc),
            indicatorHalfWidth = indicator.width() / 2,
            lis = $(nav).children('li');

        $(nav).tabs(item, {
            effect: 'fade',
            fadeOutSpeed: 0,
            fadeInSpeed: 400,
            onBeforeClick: function (event, index) {
                var li = lis.eq(index),
                    newPos = li.position().left + (li.width() / 2) - indicatorHalfWidth;
                indicator.stop(true).animate({
                    left: newPos
                }, 600, 'easeInOutExpo');
            }
        });
    }

    tabSlide(".mslide_tabs .nav", ".m_indicator", ".mslide_content .item");
});

/**
 * 回复
 */
function btnReplay(obj) {
    $(obj).parents('.my_words').siblings('.leave_word_form').fadeIn();
}

/**
 * 取消回复
 */
function cancelLeaveWord(obj) {
    $(obj).parents('.leave_word_form').fadeOut();
}
$(document).ready(function () {
    //分页获取系统消息列表
    getSysMessageList(1, 1);
    // //分页获取回复消息类别
    // getReplyMessageList(1, 1);
    // //分页获取留言消息
    // getLeaveMessageList(1,1);
})

/**
 * 分页获取系统消息
 * @type {*}
 */
var sysMessageListSource = $("#sysMessage-template").html();
var sysMessageListTemplate = Handlebars.compile(sysMessageListSource);
var sysMessageListContext;
function getSysMessageList(totalPageNo, pageNo) {
    var countOfCurrentPage = 10;
    if (totalPageNo < pageNo || pageNo < 1) {
        return;
    }
    $.ajax({
        type: "post",
        url: "/portal/services/customerMessage/getSysMessages.html",
        dataType: 'json',
        async: true,
        data: {"currentPageNo": pageNo, "countOfCurrentPage": countOfCurrentPage},
        beforeSend: function (XHR) {
            loading = top.layer.load(2, {
                shade: [0.3, '#eee'] //0.1透明度的白色背景
            });
        },
        error: function (data) {
            layer.alert('获取系统消息失败', {icon: 2, offset: '280px'});
            return false;
        },
        success: function (data) {
            sysMessageListContext = data;
            var sysMessageListHtml = sysMessageListTemplate(sysMessageListContext);
            $('#sys_message_list_id').html(sysMessageListHtml);
            //分页
            var totalPage = data.total % countOfCurrentPage == 0 ? data.total / countOfCurrentPage : Math.ceil(data.total / countOfCurrentPage);
            if(totalPage > 1) {
                pageFunction(totalPage, pageNo);
            }
            layer.closeAll();
        },
    });
}
;

/**
 * 分页获取回复消息
 * @type {*}
 */
var replyMessageListSource = $("#replyMessage-template").html();
var replyMessageListTemplate = Handlebars.compile(replyMessageListSource);
var replyMessageListContext;
function getReplyMessageList(totalPageNo, pageNo) {
    var countOfCurrentPage = 10;
    if (totalPageNo < pageNo || pageNo < 1) {
        return;
    }
    $.ajax({
        type: "post",
        url: "/portal/services/customerMessage/getReplyMessage.html",
        dataType: 'json',
        async: true,
        data: {"currentPageNo": pageNo, "countOfCurrentPage": countOfCurrentPage},
        beforeSend: function (XHR) {
            loading = top.layer.load(2, {
                shade: [0.3, '#eee'] //0.1透明度的白色背景
            });
        },
        error: function (data) {
            $.platform.msg('获取回复消息失败', 1000, 2);
            return false;
        },
        success: function (data) {
            replyMessageListContext = data;
            var replyMessageListHtml = replyMessageListTemplate(replyMessageListContext);
            $('#reply_message_list_id').html(replyMessageListHtml);
            //分页
            var totalPage = data.total % countOfCurrentPage == 0 ? data.total / countOfCurrentPage : Math.ceil(data.total / countOfCurrentPage);
            if(totalPage > 1) {
                pageReplyMessageFunction(totalPage, pageNo);
            }
            layer.closeAll();
        },
    });
}
;

/**
 * 分页获取留言消息
 * @type {*}
 */
var leaveMessageListSource = $("#leaveMessage-template").html();
var leaveMessageListTemplate = Handlebars.compile(leaveMessageListSource);
var leaveMessageListContext;
function getLeaveMessageList(totalPageNo, pageNo) {
    var countOfCurrentPage = 5;
    if (totalPageNo < pageNo || pageNo < 1) {
        return;
    }
    $.ajax({
        type: "post",
        url: "/portal/services/customerMessage/getLeaveMessage.html",
        dataType: 'json',
        async: true,
        data: {"currentPageNo": pageNo, "countOfCurrentPage": countOfCurrentPage},
        beforeSend: function (XHR) {
            loading = top.layer.load(2, {
                shade: [0.3, '#eee'] //0.1透明度的白色背景
            });
        },
        error: function (data) {
            $.platform.msg('获取留言消息失败', 1000, 2);
            return false;
        },
        success: function (data) {
            leaveMessageListContext = data;
            var leaveMessageListHtml = leaveMessageListTemplate(leaveMessageListContext);
            $('#leave_message_list_id').html(leaveMessageListHtml);
            //分页
            var totalPage = data.total % countOfCurrentPage == 0 ? data.total / countOfCurrentPage : Math.ceil(data.total / countOfCurrentPage);
            if (totalPage > 1) {
                pageLeaveMessageFunction(totalPage, pageNo);
            }
            layer.closeAll();
        },
    });
}
;

//分页
function pageFunction(totalPages, currentPage) {
    $("#sysMessagePageId").jqPaginator({
        totalPages: totalPages,
        visiblePages: 10,
        currentPage: currentPage,
        first: '<a href="javascript:void(0);" onclick="getSysMessageList(1,1)">首页<\/a>',
        prev: '<a href="javascript:void(0);" onclick="getSysMessageList({{totalPages}},{{page}})">上一页<\/a>',
        next: '<a href="javascript:void(0);" onclick="getSysMessageList({{totalPages}},{{page}})">下一页<\/a>',
        last: '<a href="javascript:void(0);" onclick="getSysMessageList({{totalPages}},{{totalPages}})">末页<\/a>',
        page: '<a href="javascript:void(0);" onclick="getSysMessageList({{totalPages}},{{page}})">{{page}}<\/a>',
        onPageChange: function (n) {
            $("#demo1-text").html("当前第" + n + "页");
        }
    });
}
;

//分页
function pageReplyMessageFunction(totalPages, currentPage) {
    $("#replyMessagePageId").jqPaginator({
        totalPages: totalPages,
        visiblePages: 10,
        currentPage: currentPage,
        first: '<a href="javascript:void(0);" onclick="getReplyMessageList(1,1)">首页<\/a>',
        prev: '<a href="javascript:void(0);" onclick="getReplyMessageList({{totalPages}},{{page}})">上一页<\/a>',
        next: '<a href="javascript:void(0);" onclick="getReplyMessageList({{totalPages}},{{page}})">下一页<\/a>',
        last: '<a href="javascript:void(0);" onclick="getReplyMessageList({{totalPages}},{{totalPages}})">末页<\/a>',
        page: '<a href="javascript:void(0);" onclick="getReplyMessageList({{totalPages}},{{page}})">{{page}}<\/a>',
        onPageChange: function (n) {
            $("#demo1-text").html("当前第" + n + "页");
        }
    });
}
;

//分页
function pageLeaveMessageFunction(totalPages, currentPage) {
    $("#leaveMessagePageId").jqPaginator({
        totalPages: totalPages,
        visiblePages: 5,
        currentPage: currentPage,
        first: '<a href="javascript:void(0);" onclick="getLeaveMessageList(1,1)">首页<\/a>',
        prev: '<a href="javascript:void(0);" onclick="getLeaveMessageList({{totalPages}},{{page}})">上一页<\/a>',
        next: '<a href="javascript:void(0);" onclick="getLeaveMessageList({{totalPages}},{{page}})">下一页<\/a>',
        last: '<a href="javascript:void(0);" onclick="getLeaveMessageList({{totalPages}},{{totalPages}})">末页<\/a>',
        page: '<a href="javascript:void(0);" onclick="getLeaveMessageList({{totalPages}},{{page}})">{{page}}<\/a>',
        onPageChange: function (n) {
            $("#demo1-text").html("当前第" + n + "页");
        }
    });
}
;

function isDeleteMessage(type, id){
    layer.confirm('确认要删除吗？',{
        icon: 3,
        offset: '280px',
        skin:'layui-layer-rim'
    },function(index){
        deleteMessage(type, id);
        layer.close(index);
    });
}
/**
 * 删除消息
 * @param type
 * @param id
 */
function deleteMessage(type, id) {
    $.ajax({
        type: "post",
        url: "/portal/services/customerMessage/deleteMessage.html",
        dataType: 'json',
        async: true,
        data: {"type": type, "id": id},
        error: function (data) {
            alert("Connection error");
            return false;
        },
        success: function (data) {
            if (data.success) {
                //分页获取系统消息列表
                getSysMessageList(1, 1);
                //分页获取回复消息类别
                getReplyMessageList(1, 1);
            } else {
                $.platform.msg(data.message, 1000, 5);
            }
        }
    });
}

/**
 * 回复留言消息
 */
function replyLeaveMessage(messageId,leaveCusUserId) {
    var content = $("#repay_content_"+messageId).val();
    $.ajax({
        type: "post",
        url: "/portal/services/customerMessage/replyLeaveMessage.html",
        dataType: 'json',
        async: true,
        data: {"messageId": messageId,"content":content,"leaveCusUserId":leaveCusUserId},
        beforeSend: function (XHR) {
            loading = top.layer.load(2, {
                shade: [0.3, '#eee'] //0.1透明度的白色背景
            });
        },
        error: function (data) {
            return false;
        },
        success: function (data) {
            if(data.success) {
                $.platform.msg("回复成功", 1000, 6);
                getLeaveMessageList(1,1);
            }else {
                $.platform.msg(data.message, 1000, 5);
            }
        },
    });
}