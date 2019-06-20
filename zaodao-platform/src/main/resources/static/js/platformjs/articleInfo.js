/**
 * Created by xiaohong on 2017/9/28.
 */

$(function(){
    //处理图片自适应
    $(".article-content img").addClass("img-responsive center-block");

    $(window).load(function(){
        var imgs = $("img");
        var len = imgs.length;
        for(var i = 0 ; i < len; i++) {
            if ((typeof(imgs[i].src) == 'undefined' || imgs[i].src == '')
                && imgs[i].getAttribute('nsrc') != null) {
                imgs[i].src = imgs[i].getAttribute('nsrc');
                imgs[i].removeAttribute('nsrc');
            }
        }
    });

    var id = $('#articleId').val();
    getAtricleReview(1, 1);
    //回复帖子
    $("#article_review_content_submit").unbind("click").bind("click", submitReview);


});

/*获取帖子评论列表*/
function getAtricleReview(totalPageNo, pageNo) {
    var id = $('#articleId').val();
    var reviewTemp = Handlebars.compile($("#atricleReview-template").html());
    var countOfCurrentPage = 10;

    if (totalPageNo < pageNo || pageNo < 1) {  return; }

    $.ajax({
        type: "post",
        url: "/portal/articleInfo/getArticleReviews.html",
        dataType: 'json',
        async: true,
        data: {"id": id, "currentPageNo": pageNo, "countOfCurrentPage": countOfCurrentPage},
        beforeSend: function (xhr) { },
        error: function (data) {
            alert("Connection error");
            return false;
        },
        success: function (data) {
            $('#article_review_item_id').html(reviewTemp(data));
            var totalPage = data.total % countOfCurrentPage == 0 ? data.total / countOfCurrentPage : Math.ceil(data.total / countOfCurrentPage);

            if (totalPage > 1) {
                pageFunction(totalPage, pageNo);
            }
        }
    });
}
/* 分页 */
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
/*回复帖子*/
function submitReview(){
    //提交参数
    var data = {
        articleId: $("#articleId").val(),
        reviewContent:$("#article_review_content_text").val()
    };
    function tips(msg){ layer.open({ content: msg, skin: 'msg', time: 2 }); }
    var index = -1;
    //提交回复内容
    $.ajax({
        type: "POST",
        async:true,
        url: "/portal/articleInfo/articleReview",
        dataType: "JSON",
        data: data,
        beforeSend:function(xhr){
            index = layer.open({ type: 2, content: '提交中' });
        },
        success: function (data) {
            var success = data.success || 0;
            var message = data.message || "操作失败";
            if (success) {
                tips("回复成功");
                getAtricleReview(1, 1);
                $("#article_review_content_text").val("");
                $("#article_review_back").click();
            } else {
                tips(message);
            }
        },
        complete:function(xhr, ts){
            if(index > -1){
                layer.close(index)
            }
        },
        error:function(xhr, textStatus, errorThrown){
            tips("发生错误 = " + xhr.status);
        }
    });
}