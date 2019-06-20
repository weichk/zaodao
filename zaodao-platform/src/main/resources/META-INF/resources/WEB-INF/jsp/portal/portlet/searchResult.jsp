<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<div class="home_section1">
    <div class="wrap">
        <div class="breadcrum"><a href="index.html">导游论坛</a> > 导游搜索</div>
        <form action="/portal/search.html" method="post" name="filter_form" class="filter_form force_show mt40">
            <div class="form_item form_head">
                <label for="province" class="label">目&nbsp;&nbsp;的&nbsp;&nbsp;地：</label>
                <select class="select" id="province" name="province" tabindex="3"></select>
                <select class="select" id="city" name="city" tabindex="4"></select>
                <label class="label out_date">出行时间：</label>
                <div class="date_container inline">
                    <div class="date_input pie">
                        <div class="room_num">
                            <input type="text" name="date" id="choose_single_date" class="date">
                        </div>
                    </div>
                    <span class="total none">共<em id="total-days">_</em>晚</span>
                    <!-- Swiper -->
                    <div class="swiper-container date_swiper pie">
                        <div id="calendar-box" class="calendar swiper-wrapper"></div>
                        <!-- Add Navigation -->
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-button-next"></div>
                    </div>
                </div>
            </div>
            <div class="form_item">
                <label class="label">语&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;言：</label>
                <input type="radio" name="lang" value="" checked><label>全部</label>
                <input type="radio" name="lang" value="中文"><label>中文</label>
                <input type="radio" name="lang" value="英文"><label>英文</label>
                <input type="radio" name="lang" value="日文"><label>日文</label>
            </div>
            <%--<div class="form_item">--%>
                <%--<label class="label">导游级别：</label>--%>
                <%--<input type="radio" name="level" value="" checked><label>全部</label>--%>
                <%--<input type="radio" name="level" value="goldMedal"><label>金牌</label>--%>
                <%--<input type="radio" name="level" value="silverMedal"><label>银牌</label>--%>
                <%--<input type="radio" name="level" value="copperMedal"><label>铜牌</label>--%>
            <%--</div>--%>
            <div class="form_item last">
                <label class="label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
                <input type="radio" name="sex" value="" checked><label>全部</label>
                <input type="radio" name="sex" value="0"><label>女</label>
                <input type="radio" name="sex" value="1"><label>男</label>
            </div>
            <input type="button" onclick="getTouideList(true)" value="我要约导" id="filter_btn"
                   class="filter_btn pie">
        </form>
        <!-- 导游搜索 end -->

        <div class="result_num">搜索结果：<span id="resultCount"></span>条</div>


        <ul class="search_result_list">

        </ul>
        <!--用于克隆的导游信息-->
        <li class="guideInfo none">
            <a href="#" class="img"><img src="/images/guide_photo_01.jpg" alt=""></a>
            <div class="con">
                <div class="top">
                    <a href="#" class="name">索菲亚</a>
                    <span class="age">27岁</span>
                    <span class="location">
                            <i class="sprite icon_location_bl"></i>
                        </span>
                </div>
                <span class="price">￥300/天</span>
                <a href="#" class="btn pie">立即预定</a>
                <p class="info speciality"><i class="sprite icon_up"></i></p>
                <p class="info language"><i class="sprite icon_dial"></i></p>
                <p class="info maskMobileNo"><i class="sprite icon_tel"></i></p>
                <p class="info tourTime"><i class="sprite icon_flag"></i></p>
            </div>
        </li>
        <div
                class="more_list_item last ac"><a href="javascript:getTouideList(false)" class="pie">查看更多
            <i class="arrow"></i></a></div>
    </div>
</div>
<!-- 搜索结果列表 end -->
<script type="text/javascript" src="/js/choose_single_date.js"></script>
<script type="text/javascript">

    var pageNo = 1;
    var pageSize = 15;
    var hasNext = true;

    $(function () {
        initSearchData();
        getTouideList(true);
    })


    function initSearchData() {
        $(":radio[name='lang'][value='${guideDto.language}']").prop("checked", "checked");
        $(":radio[name='level'][value='${guideDto.level}']").prop("checked", "checked");
        $(":radio[name='sex'][value='${guideDto.sex}']").prop("checked", "checked");
        var province = '${guideDto.province}';
        var city = '${guideDto.city}';
        if (province != "" && city != "") {
            new PCAS("province=${guideDto.province}", "city=${guideDto.city}");
        } else {
            new PCAS("province,省份", "city,城市");
        }

    }
    //flag=true，移除原有查询列表，从新查询，flag=false，不移除原查询列表，追加页面
    function getTouideList(flag) {
        if (flag) {
            pageNo = 1;
            hasNext = true;
        }
        if (!hasNext) {
            top.layer.msg('没有更多了', {icon: 1, time: 2000});
            return;
        }
        var tripTime = $('#choose_single_date').val();
        var province = $("#province").val();
        var city = $("#city").val();
        var keywords = $("#keywords").val();
        var language = $('input:radio[name="lang"]:checked').val();
        var level = $('input:radio[name="level"]:checked').val();
        var gender = $('input:radio[name="sex"]:checked').val();
        $.ajax({
            type: 'get',
            url: "/portal/guide/resultList.html",// 请求的action路径
            dataType: "json",
            async: true,
            data: {
                tripTime: tripTime,
                province: province,
                city: city,
                keywords: keywords,
                language: language,
                level: level,
                gender: gender,
                pageNo: pageNo,
                pageSize: pageSize
            },
            beforeSend: function (curform) {
                loading = top.layer.load(2, {
                    shade: [0.3, '#eee'] //0.1透明度的白色背景
                });
            },
            complete: function () {
                layer.closeAll();
            },
            error: function (data) {
//            layer.alert('发送失败', {icon: 11, offset: '280px'});
            },
            success: function (data) {
                if (data.success) {
                    var resultList = data.data.pageList;
                    if (resultList.currentPage >= resultList.totalPage) {
                        hasNext = false;
                    } else {
                        hasNext = true;
                    }
                    pageNo = pageNo + 1;
                    initResultData(data.data.pageList, flag);
                }
            }
        });
    }

    function initResultData(data, flag) {
        $("#resultCount").text(data.totalCount);
        var ul = $("ul.search_result_list");
        if (flag) {
            ul.children().remove();
        }
        for (var i = 0, j = data.pageResults.length; i < j; i++) {
            var info = $("li.guideInfo.none").clone();
            var guide = data.pageResults[i];
            info.find("a").attr('href', "/portal/guide/detail/" + guide.userId);
            info.find("a").attr('target', "_blank");
            info.find("img").attr('src', guide.guideCoverImg);
            info.find("a.name").text(guide.realName);
            info.find("span.age").text(guide.age + "岁");
            info.find("span.location").append(guide.permanentCity);
            info.find("span.price").text("￥" + guide.pricePerDay / 100 + "/天");
            info.find("p.speciality").append(guide.speciality);
            info.find("p.language").append(guide.language);
            info.find("p.maskMobileNo").append(guide.maskMobileNo);
            info.find("p.tourTime").append(guide.tourTime + "年");
            info.removeClass("none");
            ul.append(info);
        }
    }

</script>