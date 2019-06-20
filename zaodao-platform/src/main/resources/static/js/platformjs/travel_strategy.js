function getMore() {
    var per = $("#current_page_no_index_id").val();
    $("#current_page_no_index_id").val(parseInt(per) + 1);
    $.ajax({
        type: 'get',
        url: '/portal/travelStrategy/getMoreArticles.html',
        dataType: 'json',
        async: true,
        data: {currentPageNo: parseInt(per) + 1},
        beforeSend: function (XHR) {
            loading = top.layer.load(2, {
                shade: [0.3, '#eee'] //0.1透明度的白色背景
            });
        },
        success: function (data) {
            if (data.data.areaList.length > 0) {
                var areaArr = data.data.areaList;
                var articleList = data.data.articleList;
                var html = "";
                for (var i = 0; i < areaArr.length; i++) {
                    html +="<div class='commen_title'><span class='bold'>"+areaArr[i]['areaName']+"旅游攻略</span><a href='/portal/travelStrategy/travelStrategyList.html?area="+areaArr[i]['areaName']+"' class='more'>更多 》</a></div>";
                    html += "<ul class='col3_list'>";
                    for(var j = 0;j<articleList.length;j++) {
                        if(areaArr[i]['areaName'] == articleList[j]['area']) {
                            html += "<li>";
                            html += "<a href='/portal/guideHome/getArticleInfo_"+articleList[j]['id']+".html' class='travel_list_img'><img src='"+articleList[j]['cover']+"' alt=''></a>";
                            html += "<div class='travel_list_info'>";
                            html += "<a href='/portal/guideHome/getArticleInfo_"+articleList[j]['id']+".html' class='t'>"+articleList[j]['title']+"</a>";
                            html += "<div class='f'><span class='view'><i class='sprite icon_eye_bl'></i>"+articleList[j]['readCount']+"</span><span class='like'><i class='sprite icon_heart_bl'></i>"+articleList[j]['praiseCount']+"</span></div>";
                            html += "</div></li>";
                        }
                    }
                    html += "</ul>";
                }
                ;
                $("#travel_strategy_div_id").append(html);
                layer.closeAll();
            } else {
                $.platform.msg('没有更多了', 1000, 5,'',0);
            }
        }
    });
}
