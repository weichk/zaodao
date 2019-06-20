$(document).ready(function () {
    getArticleList(1, 1);

});

/**
 * 分页获取文章消息
 * @type {*}
 */
var articleListSource = $("#articleList-template").html();
var articleListTemplate = Handlebars.compile(articleListSource);
var articleListContext;
function getArticleList(totalPageNo, pageNo) {
    var lable = $('#article_label_id').val();
    var searchContent = $('#search_content_id').val();
    var countOfCurrentPage = 10;
    if (totalPageNo < pageNo || pageNo < 1) {
        return;
    }
    $.ajax({
        type: "post",
        url: "/portal/services/moderator/getModeratorArticleList.html",
        dataType: 'json',
        async: true,
        data: {"currentPageNo": pageNo, "lable": lable, searchContent: searchContent},
        beforeSend: function (XHR) {
            loading = top.layer.load(2, {
                shade: [0.3, '#eee'] //0.1透明度的白色背景
            });
        },
        error: function (data) {
            $.platform.msg('获取文章列表失败', 1000, 2);
            return false;
        },
        success: function (data) {
            articleListContext = data;
            var articleListHtml = articleListTemplate(articleListContext);
            $('#article_list_id').html(articleListHtml);
            //分页
            var totalPage = data.total % countOfCurrentPage == 0 ? data.total / countOfCurrentPage : Math.ceil(data.total / countOfCurrentPage);
            if (totalPage > 1) {
                pageArticleFunction(totalPage, pageNo);
            }
            layer.closeAll();
            // 点击其它区域关闭管理菜单
            $(document).click(function (e) {
                $('.manage_list,.set_stamp_list').fadeOut();
            });

            // 点击管理及其菜单区域阻止事件传播
            $('.manage_list,.manage_list a,.set_stamp_list,.set_stamp_list a').click(function (e) {
                e.stopPropagation();
            });

            // 点击设置图章弹出图章列表
            $('.set_stamp_btn').click(function (e) {
                e.preventDefault();
                e.stopPropagation();
                $('.manage_list,.set_stamp_list').fadeOut();
                $(this).siblings('.set_stamp_list').slideToggle();
            });

            // 点击管理弹出管理菜单
            $('.manage_blogs').click(function (e) {
                e.preventDefault();
                e.stopPropagation();
                $('.manage_list,.set_stamp_list').fadeOut();
                $(this).siblings('.manage_list').slideToggle();
            });
        },
    });
}
;

//分页
function pageArticleFunction(totalPages, currentPage) {
    $("#moderator_list_page_id").jqPaginator({
        totalPages: totalPages,
        visiblePages: 10,
        currentPage: currentPage,
        first: '<a href="javascript:void(0);" onclick="getArticleList(1,1)">首页<\/a>',
        prev: '<a href="javascript:void(0);" onclick="getArticleList({{totalPages}},{{page}})">上一页<\/a>',
        next: '<a href="javascript:void(0);" onclick="getArticleList({{totalPages}},{{page}})">下一页<\/a>',
        last: '<a href="javascript:void(0);" onclick="getArticleList({{totalPages}},{{totalPages}})">末页<\/a>',
        page: '<a href="javascript:void(0);" onclick="getArticleList({{totalPages}},{{page}})">{{page}}<\/a>',
        onPageChange: function (n) {
            $("#demo1-text").html("当前第" + n + "页");
        }
    });
}
;

// function guanli(obj) {
//     $('.manage_list').fadeOut();
//     $(obj).siblings('.manage_list').slideToggle();
// }

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
                        getArticleList(1, 1);
                        // $.platform.msg('删除成功！', 2000, 6);
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
                        getArticleList(1, 1);
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
            getArticleList(1, 1);
        }
    });
}

// 设置图章
function setStamp(articleId, stampCode, message) {
    $.ajax({
        type: 'post',
        url: "/portal/services/moderator/setStamp.html",// 请求的action路径
        dataType: "json",
        async: true,
        data: {"articleId": articleId, "stampCode": stampCode},
        error: function (data) {
            $.platform.msg('操作失败', 2000, 2);
        },
        success: function (data) {
            getArticleList(1, 1);
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
            getArticleList(1, 1);
        }
    });
}

// 移动
function move() {
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
            getArticleList(1, 1);
        }
    });
}

// 删除
function deleteAr() {
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
            getArticleList(1, 1);
        }
    });
}

function readArticleInfo(articleId, isGuide) {
    if (isGuide != 1) {
        $.platform.msg("请上传导游证", 2000, 5);
    } else {
        window.location.href = "/portal/guideHome/getArticleInfo_" + articleId + ".html";
    }
}