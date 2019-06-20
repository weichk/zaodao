function getMoreArticles() {
    var per = $("#current_page_no_index_id").val();
    $("#current_page_no_index_id").val(parseInt(per) + 1);
    var area = $('#current_area_value_id').val();
    var searchContent = $('#search_content_id').val();
    $.ajax({
        type: 'get',
        url: '/portal/travelStrategy/getMoreArticlesList.html',
        dataType: 'json',
        async: true,
        data: {currentPageNo: parseInt(per) + 1, area: area, searchContent: searchContent},
        beforeSend: function (XHR) {
            loading = top.layer.load(2, {
                shade: [0.3, '#eee'] //0.1透明度的白色背景
            });
        },
        success: function (data) {
            if (data.data.articleList.length > 0) {
                var articleList = data.data.articleList;
                var html = "";
                for (var i = 0; i < articleList.length; i++) {
                    html += "<p class='summary'><li>";
                    html += "<a href='/portal/guideHome/getArticleInfo_" + articleList[i]['id'] + ".html' class='img'><img src='" + articleList[i]['cover'] + "' alt=''></a>";
                    html += "<div class='con'>";
                    html += "<a href='/portal/guideHome/getArticleInfo_" + articleList[i]['id'] + ".html' class='t'>" + articleList[i]['title'] + "</a>" + articleList[i]['content'];
                    html += "<div class='f'><span class='view'><i class='sprite icon_eye_bl'></i>" + articleList[i]['readCount'] + "</span><span class='like'><i class='sprite icon_heart_bl'></i>" + articleList[i]['praiseCount'] + "</span><span class='date'>2017.05.04</span></div> ";
                    html += "</div>";
                    html += "</li></p>";
                }
                ;
                $("#travel_strategy_list_div_id").append(html);
                layer.closeAll();
            } else {
                $.platform.msg('没有更多了', 1000, 5, '', 0);
            }
        }
    });
}