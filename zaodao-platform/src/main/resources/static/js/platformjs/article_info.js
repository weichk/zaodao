$(document).ready(function () {
    var havaRedBag = $('#hava_red_bag_id').val();
    var getPoint = $('#get_point_id').val();
    if (havaRedBag == 1) {
        openRedpaket(getPoint);
    }
});
$(function () {
    // 定位右侧浮动
    var docWidth = $(document).width();
    $(".quik_btns").css("right", (docWidth - 1000) / 2 - 90);
    docWidth = null;

    //回复提交
    $("#reply_form_id").Validform({
        tiptype: function (msg, o, cssctl) {
            if (!o.obj.is("form")) {
                var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                // alert(o.obj[0].tagName);
                cssctl(objtip, o.type);
                objtip.text(msg);
            }
        },
        ajaxPost: true,
        showAllError: true,
        beforeCheck: function (curform) {
            var isLogin = $('#is_login_id').val();
            if (isLogin == 0) {
                $.platform.msg('请先登录', 1000, 5);
                return false;
            }
            var isShutup = $('#is_shutup_id').val();
            if (isShutup != 0) {
                $.platform.msg('你已被禁言', 1000, 5);
                return false;
            }
        },
        callback: function (data) {
            if (data.success) {
                // $.platform.msg('回复成功', 2000, 6);
                //获取帖子评论
                var id = $('#articleId').val();
                getAtricleReview(id, 1, 1);
                if (data.data.haveRedPacket == 1) {
                    openRedpaket(1);
                }
            } else {
                $.platform.msg(data.message, 1000, 5);
            }
        }
    });
});

$(document).ready(function () {
    var id = $('#articleId').val();
    //获取帖子信息
    getArticleInfo(id);
    //获取帖子评论
    getAtricleReview(1, 1);
})


/**
 * 获取帖子信息
 * @type {*}
 */
var articleInfoSource = $("#atricleInfo-template").html();
var articleInfoTemplate = Handlebars.compile(articleInfoSource);
var articleInfoContext;

function getArticleInfo(id) {
    $.ajax({
        type: "post",
        url: "/portal/articleInfo/getArticleDetails.html",
        dataType: 'json',
        async: true,
        data: {"id": id},
        beforeSend: function (XHR) {
            loading = top.layer.load(2, {
                shade: [0.3, '#eee'] //0.1透明度的白色背景
            });
        },
        error: function (data) {
            console.info("Connection error");
            return false;
        },
        success: function (data) {
            articleInfoContext = data.data.articleDetails;
            var articleInfotHtml = articleInfoTemplate(articleInfoContext);
            $('#article_info_item_id').html(articleInfotHtml);
            //弹出管理菜单
            getGuanli();
        },
    });
}
;

function getGuanli() {
    // 点击管理弹出管理菜单
    $('.manage_blogs').click(function (e) {
        e.preventDefault();
        e.stopPropagation();
        $(this).siblings('.manage_list').slideToggle();
    });

    // 点击设置图章弹出图章列表
    $('.set_stamp_btn').click(function(e) {
        e.preventDefault();
        e.stopPropagation();
        $('.manage_list,.set_stamp_list').fadeOut();
        $(this).siblings('.set_stamp_list').slideToggle();
    });
    // 点击管理及其菜单区域阻止事件传播
    $('.manage_list,.manage_list a,.set_stamp_list,.set_stamp_list a').click(function (e) {
        e.stopPropagation();
    });

    // 点击其它区域关闭管理菜单
    $(document).click(function (e) {
        $('.manage_list,.set_stamp_list').fadeOut();
    });
}

// 删除帖子
function deleteArticle(articleId) {
    layer.open({
        type: 1,
        title: false,
        area: ['400px', '300px'],
        closeBtn: 0,
        shadeClose: true,
        skin: 'popup_manage_blog',
        content: '<div class="content_wrap"><div class="inner_wrap">' +
        '<a href="#" class="close_popup_blog"></a>' +
        '<h2 class="popup_blog_title">删除帖子</h2>' +
        '<form action="/portal/services/moderator/deleteArticle.html" method="post" name="delete_blog_reason" class="delete_blog_reason">' +
        '<input type="hidden" value=' + articleId + ' name="articleId"/>' +
        '<input type="text" name="deleteReason" placeholder="请输入删除原因" class="text pie" />' +
        '<input type="submit" value="确认删除" name="delete_blog_btn" class="delete_blog_btn uni_btn pie" />' +
        '</form></div></div>',
        success: function (layero, index) {
            $('.close_popup_blog').click(function (e) {
                e.preventDefault();
                layer.close(index);
            });
            //监控form表单
            $(".delete_blog_reason").Validform({
                tiptype: function (msg, o, cssctl) {
                    if (!o.obj.is("form")) {
                        var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                        // alert(o.obj[0].tagName);
                        cssctl(objtip, o.type);
                        objtip.text(msg);
                    }
                },
                ajaxPost: true,
                showAllError: true,
                usePlugin: {
                    jqtransform: {
                        //会在当前表单下查找这些元素;
                        selector: ":checkbox,:radio,.decorate"
                    }
                },
                beforeSubmit: function (curform) {
                    loading = top.layer.load(2, {
                        shade: [0.3, '#eee'] //0.1透明度的白色背景
                    });
                },
                callback: function (data) {
                    if (data.success) {
                        location.reload();
                    } else {
                        $.platform.msg(data.message, 1000, 5);
                    }
                }
            });
        }
    });
}

// 移动帖子
function moveArticle(articleId) {
    layer.open({
        type: 1,
        title: false,
        area: ['400px', '300px'],
        closeBtn: 0,
        shadeClose: true,
        skin: 'popup_manage_blog',
        content: '<div class="content_wrap">' +
        '<div class="inner_wrap"><a href="#" class="close_popup_blog"></a>' +
        '<h2 class="popup_blog_title">移动帖子</h2>' +
        '<form action="/portal/services/moderator/moverArticle.html" method="post" name="popup_move_blog" class="popup_move_blog">' +
        '<p>将帖子移动到</p>' +
        '<input type="hidden" value=' + articleId + ' name="articleId"/>' +
        '<select name="label" id="select_moveto" class="select_moveto">' +
        '<option value="guideWord">导游词</option> ' +
        '<option value="skillsTour">带团技巧</option> ' +
        '<option value="guideTraining">导游培训</option>' +
        ' <option value="domesticRoutes">国内路线</option> ' +
        '<option value="exitRoutes">境外路线</option>' +
        ' <option value="customRoute">定制路线</option>' +
        ' <option value="guideTreeHole">导游树洞</option>' +
        ' <option value="caseTour">带团案例</option>' +
        '<option value="complaintCase">投诉案例</option>' +
        '<option value="problemSolving">问题处理</option>' +
        ' </select>' +
        '<input type="submit" value="确认移动" name="delete_blog_btn" class="move_blog_btn uni_btn pie" />' +
        '</form></div></div>',
        success: function (layero, index) {
            $('.close_popup_blog').click(function (e) {
                e.preventDefault();
                layer.close(index);
            });
            //监控form表单
            $(".popup_move_blog").Validform({
                tiptype: function (msg, o, cssctl) {
                    if (!o.obj.is("form")) {
                        var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                        // alert(o.obj[0].tagName);
                        cssctl(objtip, o.type);
                        objtip.text(msg);
                    }
                },
                ajaxPost: true,
                showAllError: true,
                usePlugin: {
                    jqtransform: {
                        //会在当前表单下查找这些元素;
                        selector: ":checkbox,:radio,.decorate"
                    }
                },
                beforeSubmit: function (curform) {
                    loading = top.layer.load(2, {
                        shade: [0.3, '#eee'] //0.1透明度的白色背景
                    });
                },
                callback: function (data) {
                    if (data.success) {
                        location.reload();
                        // $.platform.msg('移动成功！', 1000, 6);
                    } else {
                        $.platform.msg(data.message, 1000, 5);
                    }
                }
            });
        }
    });
}

// 加精
function essence(articleId, status) {
    $.ajax({
        type: 'post',
        url: "/portal/services/moderator/essence.html",// 请求的action路径
        dataType: "json",
        async: true,
        data: {"articleId": articleId, "status": status},
        error: function (data) {
            $.platform.msg('操作失败', 2000, 2);
        },
        success: function (data) {
            location.reload();
        }
    });
}

// 置顶
function up(articleId, status) {
    $.ajax({
        type: 'post',
        url: "/portal/services/moderator/up.html",// 请求的action路径
        dataType: "json",
        async: true,
        data: {"articleId": articleId, "status": status},
        error: function (data) {
            $.platform.msg('操作失败', 2000, 2);
        },
        success: function (data) {
            location.reload();
        }
    });
}

// 设置图章
function setStampCode(articleId, code) {
    $.ajax({
        type: 'post',
        url: "/portal/services/moderator/setStamp.html",// 请求的action路径
        dataType: "json",
        async: true,
        data: {"articleId": articleId, "stampCode": code},
        error: function (data) {
            $.platform.msg('操作失败', 2000, 2);
        },
        success: function (data) {
            location.reload();
        }
    });
}
/**
 * 获取帖子评论列表
 * @type {*}
 */
var atricleReviewSource = $("#atricleReview-template").html();
var atricleReviewTemplate = Handlebars.compile(atricleReviewSource);
var atricleReviewContext;

function getAtricleReview(totalPageNo, pageNo) {
    var id = $('#articleId').val();
    var countOfCurrentPage = 10;
    if (totalPageNo < pageNo || pageNo < 1) {
        return;
    }
    $.ajax({
        type: "post",
        url: "/portal/articleInfo/getArticleReviews.html",
        dataType: 'json',
        async: true,
        data: {"id": id, "currentPageNo": pageNo, "countOfCurrentPage": countOfCurrentPage},
        beforeSend: function (XHR) {
            loading = top.layer.load(2, {
                shade: [0.3, '#eee'] //0.1透明度的白色背景
            });
        },
        error: function (data) {
            console.info("Connection error");
            return false;
        },
        success: function (data) {
            atricleReviewContext = data;
            var atricleReviewHtml = atricleReviewTemplate(atricleReviewContext);
            $('#article_review_item_id').html(atricleReviewHtml);
            //分页
            var totalPage = data.total % countOfCurrentPage == 0 ? data.total / countOfCurrentPage : Math.ceil(data.total / countOfCurrentPage);
            if (totalPage > 1) {
                pageFunction(totalPage, pageNo);
            }
            layer.close(loading);
        },
    });
}

//分页
function pageFunction(totalPages, currentPage) {
    $("#reviewsPageId").jqPaginator({
        totalPages: totalPages,
        visiblePages: 10,
        currentPage: currentPage,
        first: '<a href="javascript:void(0);" onclick="getAtricleReview(1,1)">首页<\/a>',
        prev: '<a href="javascript:void(0);" onclick="getAtricleReview({{totalPages}},{{page}})">上一页<\/a>',
        next: '<a href="javascript:void(0);" onclick="getAtricleReview({{totalPages}},{{page}})">下一页<\/a>',
        last: '<a href="javascript:void(0);" onclick="getAtricleReview({{totalPages}},{{totalPages}})">末页<\/a>',
        page: '<a href="javascript:void(0);" onclick="getAtricleReview({{totalPages}},{{page}})">{{page}}<\/a>',
        onPageChange: function (n) {
            $("#demo1-text").html("当前第" + n + "页");
        }
    });
}
;

/**
 * 点赞
 */
function likeHeart(id) {
    var isLogin = $('#is_login_id').val();
    if (isLogin == 0) {
        $.platform.msg('请先登录', 2000, 5);
        return false;
    }
    if ($("#like_heart_id").hasClass("on")) {
        return false;
    }
    $.ajax({
        type: "post",
        url: "/portal/articleInfo/likeHeart.html",
        dataType: 'json',
        async: true,
        data: {"id": id},
        error: function (data) {
            return false;
        },
        success: function (data) {
            if (data.success) {
                $("#like_heart_id").addClass("on");
                var currentCount = parseInt($("#like_heart_num_id").html());
                $("#like_heart_num_id").html(currentCount+1);
            }
        },
    });
}

/**
 * 收藏
 */
function enshrine(id) {
    var isLogin = $('#is_login_id').val();
    if (isLogin == 0) {
        $.platform.msg('请先登录', 2000, 5);
        return false;
    }
    var url = "";
    if ($("#enshrine_id").hasClass("on")) {
        url = "/portal/articleInfo/cancelEnshrine.html";
    }else {
        url = "/portal/articleInfo/enshrine.html";
    }
    $.ajax({
        type: "post",
        url: url,
        dataType: 'json',
        async: true,
        data: {"id": id},
        error: function (data) {
            return false;
        },
        success: function (data) {
            if (data.success) {
                if ($("#enshrine_id").hasClass("on")) {
                    $("#enshrine_id").removeClass("on");
                    var currentCount = parseInt($("#enshrine_num_id").html());
                    $("#enshrine_num_id").html(currentCount-1);
                }else {
                    $("#enshrine_id").addClass("on");
                    var currentCount = parseInt($("#enshrine_num_id").html());
                    $("#enshrine_num_id").html(currentCount+1);
                }
            }
        },
    });
}

/**
 * 回复评论
 */
function replyReview(articleId, reviewId) {
    var content = $("#reply_review_content_id_" + reviewId).val();
    $.ajax({
        type: "post",
        url: "/portal/articleInfo/replyReview.html",
        dataType: 'json',
        async: true,
        data: {"articleId": articleId, "content": content, "reviewId": reviewId},
        beforeSend: function (XHR) {
            var isLogin = $('#is_login_id').val();
            if (isLogin == 0) {
                $.platform.msg('请先登录', 2000, 5);
                return false;
            }
            var isShutup = $('#is_shutup_id').val();
            if (isShutup != 0) {
                $.platform.msg('你已被禁言', 1000, 5);
                return false;
            }
        },
        error: function (data) {
            return false;
        },
        success: function (data) {
            if (data.success) {
                getAtricleReview(1, 1);
            } else {
                $.platform.msg(data.message, 1000, 5);
            }
        },
    });
}

//展开回复
function showReplyContent(id) {
    $('#reply_content_id_' + id).show(400);
}

//关闭回复
function hideReplyContent(id) {
    $('#reply_content_id_' + id).hide(200);
}

// 点击右侧回复跳转到回复区
function redirectReply() {
    $("html,body").stop(true);
    $("html,body").animate({scrollTop: $("#reply_left_div_id").offset().top - 100}, 500);
}

//屏蔽回复
function setShield(reviewId, status) {
    $.ajax({
        type: 'post',
        url: "/portal/articleInfo/setShield.html",// 请求的action路径
        dataType: "json",
        async: true,
        data: {"reviewId": reviewId, "status": status},
        error: function (data) {
            $.platform.msg('操作失败', 2000, 2);
        },
        success: function (data) {
            getAtricleReview(1, 1);
        }
    });
}

// 打赏
function onlyReward(userId,id,type) {
    var isLogin = $('#is_login_id').val();
    if (isLogin == 0) {
        $.platform.simplemsg('请先登录', 1000, 5);
        return false;
    }
    layer.open({
        type: 1,
        title: false,
        area: ['400px', '300px'],
        closeBtn: 0,
        shadeClose: true,
        skin: 'popup_manage_blog',
        content: '<div class="content_wrap">' +
        '<div class="inner_wrap"><a href="#" class="close_popup_blog"></a>' +
        '<h2 class="popup_blog_title">打赏</h2>' +
        '<form action="/portal/articleInfo/onlyReward.html" method="post" id="only_reward_form_id" name="form_award" class="form_award">' +
        '<input type="hidden" value=' + userId + ' name="userId"/>' +
        '<input type="hidden" value=' + id + ' name="id"/>' +
        '<input type="hidden" value=' + type + ' name="type"/>' +
        '<div class="form_item">' +
        '<input type="text" datatype="n" name="point" nullmsg="请输入积分数！" errormsg="积分数只能是数字！" placeholder="打赏积分" class="text pie" />' +
        '<div class="Validform_checktip"></div></div>' +
        '<input type="submit" value="打赏" name="btn_award" class="btn_award uni_btn pie" /></form></div></div>',
        success: function (layero, index) {
            $('.close_popup_blog').click(function (e) {
                e.preventDefault();
                layer.close(index);
            });

            $("#only_reward_form_id").Validform({
                tiptype: function (msg, o, cssctl) {
                    if (!o.obj.is("form")) {
                        var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                        // alert(o.obj[0].tagName);
                        cssctl(objtip, o.type);
                        objtip.text(msg);
                    }
                },
                ajaxPost: true,
                showAllError: true,
                beforeSubmit: function (curform) {
                    loading = top.layer.load(2, {
                        shade: [0.3, '#eee'] //0.1透明度的白色背景
                    });
                },
                callback: function (data) {
                    if (data.success) {
                        $.platform.simplemsg("打赏成功", 1000, 6);
                        location.reload();
                    } else {
                        $.platform.simplemsg(data.message, 1000, 5);
                    }
                    layer.close(loading);
                }
            });
        }
    });
}