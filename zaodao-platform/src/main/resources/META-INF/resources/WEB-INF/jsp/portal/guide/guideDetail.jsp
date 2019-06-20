<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <link rel="stylesheet" href="/css/normalize-min.css"/>
    <link rel="stylesheet" href="/css/base.css"/>
    <link rel="stylesheet" href="/css/idangerous.swiper.css">
    <link rel="stylesheet" href="/css/public.css"/>
    <link rel="stylesheet" href="/css/detail.css"/>
    <link rel="stylesheet" href="/css/calendar.css">
    <script src="/js/modernizr.min.js"></script>
</head>

<body class="grey_bg">
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=firstPage"/>

<div class="banner">
    <div class="inner_banner"><img src="/images/banner_02.jpg" alt=""></div>
</div>
<!-- banner end -->
<div class="guide_detail wrap cf">
    <div class="block w584">
        <div class="breadcrum"><a href="index.html">导游论坛</a> &gt; <a href="/portal/guide/hotGuide/all">名导之颜</a> &gt; 导游详情
        </div>
        <div class="guide_detail_nav gd_tab_1">
            <ul class="nav">
                <li><a href="#" class="current">导游风采</a></li>
                <li><a href="#">导游相册</a></li>
                <li><a href="#">推荐路线</a></li>
                <li><a href="#">预定须知</a></li>
                <li><a href="#">留言咨询</a></li>
            </ul>
            <span class="gd_indicator gd_idc_1"></span>
        </div>
        <div class="gd_content_1">
            <div class="item">
                <div class="detail_sub_title"><span class="t">导游风采</span></div>
                <%--<video>--%>
                <%--<source src="1.mp4" type="video/mp4">--%>
                <%--</video>--%>
                <div id="videoPlayer"></div>
                <script type="text/javascript" src="/plugins/ckplayer6.8/ckplayer.js" charset="utf-8"></script>
                <div class="detail_l2_title">导游日历</div>
                <div class="guide_calendar">
                    <div class="guide_calendar_nav gd_tab_2">
                        <ul class="ca_nav nav1">
                            <li><a href="#" class="current"></a></li>
                            <li><a href="#"></a></li>
                            <li><a href="#"></a></li>
                        </ul>
                        <span class="gd_indicator gd_idc_2"></span>
                    </div>
                    <!-- 导游日历插件 -->
                    <div id="guide_calendar" class="guide_calendar_content gd_content_2">
                    </div>
                </div>

                <div class="detail_l2_title">导游自述</div>
                <div class="guide_intro">
                    <p>
                        ${guide.introduceMyself}
                    </p>
                </div>
                <!-- 导游风采 end -->
            </div>

            <div class="item">
                <div class="detail_sub_title"><span class="t">导游相册</span></div>
                <div class="guide_gallery">
                    <div class="gallery_con">
                        <c:forEach var="img" items="${imgList}" varStatus="st">
                            <div class="slide"><img src="${img.cusPicture}" alt=""></div>
                        </c:forEach>
                        <%--<div class="slide"><img src="/images/guide_gallery_01.jpg" alt=""></div>--%>
                        <%--<div class="slide"><img src="/images/guide_detail_02.jpg" alt=""></div>--%>
                        <%--<div class="slide"><img src="/images/guide_detail_03.jpg" alt=""></div>--%>
                        <%--<div class="slide"><img src="/images/guide_detail_04.jpg" alt=""></div>--%>
                        <%--<div class="slide"><img src="/images/guide_detail_05.jpg" alt=""></div>--%>
                        <%--<div class="slide"><img src="/images/guide_detail_01.jpg" alt=""></div>--%>
                        <%--<div class="slide"><img src="/images/guide_detail_02.jpg" alt=""></div>--%>
                    </div>

                    <div class="page_box">
                        <div class="slider_page cf">
                            <c:forEach var="img" items="${imgList}" varStatus="st">
                                <a data-slide-index="${st.index}" href=""
                                   <c:if test="${st.index==0}">class="active"</c:if>>
                                    <img src="${img.thumbPic}" alt="">
                                </a>
                            </c:forEach>
                            <%--<a data-slide-index="0" href="" class="active"><img src="/images/guide_gallery_thumb_01.jpg"--%>
                            <%--alt=""></a>--%>
                            <%--<a data-slide-index="1" href=""><img src="/images/guide_gallery_thumb_02.jpg" alt=""></a>--%>
                            <%--<a data-slide-index="2" href=""><img src="/images/guide_gallery_thumb_03.jpg" alt=""></a>--%>
                            <%--<a data-slide-index="3" href=""><img src="/images/guide_gallery_thumb_04.jpg" alt=""></a>--%>
                            <%--<a data-slide-index="4" href=""><img src="/images/guide_gallery_thumb_01.jpg" alt=""></a>--%>
                            <%--<a data-slide-index="5" href=""><img src="/images/guide_gallery_thumb_02.jpg" alt=""></a>--%>
                            <%--<a data-slide-index="6" href=""><img src="/images/guide_gallery_thumb_03.jpg" alt=""></a>--%>
                        </div>
                    </div>
                </div>
                <!-- 旅游照片 end -->
            </div>

            <div class="item">
                <div
                        class="detail_sub_title"><span class="t">推荐线路</span><span
                        class="info" id="totalArticles">（共条）</span></div>
                <ul class="hot_line_item">
                    <div id="articleListDiv">
                        <!--推荐路线-->
                    </div>
                </ul>
                <div class="more_list_item ac"><a href="javascript:getArticleDataList(false)">查看更多
                    <i class="arrow"></i></a></div>
                <!-- 推荐路线 end -->
            </div>

            <div class="item">
                <div class="detail_sub_title"><span class="t">预定须知</span></div>
                <ul class="booking_tips">
                    <li>请在预定并下单支付导游服务费之前与导游取得联系，并与导游确认旅行日期；</li>
                    <li>导游服务费标价以天为单位，工作时间为<strong>8小时/天</strong>，若需延长导游服务时间，游客需支付导游加班费<strong>50/小时</strong>；</li>
                    <li>除特殊声明，请另支付导游工作期间的用餐费用（参考费用20~30元/餐）。若需到旅游目的地周边县城住宿费，具体费用可与导游协商；</li>
                    <li>为保证您的行程，请至少提前一天预订。</li>
                </ul>
                <!-- 预定须知 end -->
            </div>

            <div class="item comment">
                <div class="detail_sub_title"><span class="t">游客留言</span></div>

                <form id="reply_to_guide" action="/portal/guide/leaveMsg.html" name="reply_to_guide"
                      class="reply_to_guide">
                    <div class="top">
                        <div class="img pie"><img src="/images/diary_head_03.jpg" alt="" class="pie"></div>
                        <textarea name="content" id="reply_textarea" cols="30" rows="10" placeholder="我要留言咨询"
                                  class="reply_textarea" datatype="*" nullmsg="请输入留言内容！"></textarea>
                    </div>
                    <div class="form_item foot">
                        <label for="captcha">验证码</label>
                        <div class="valid_code">
                            <input type="text" id="captcha" name="param" placeholder="验证码" class="valid_text"
                                   datatype="*" nullmsg="请输入验证码！" errormsg="验证码错误！" placeholder="验证码"
                                   ajaxurl="/portal/findPassword/checkCaptcha.html">
                            <span class="code">
                                <img src="/jcaptcha.jpg" id="jcaptchaImage" onclick="refreshCaptcha()" alt="">
                            </span>
                        </div>
                        <a href="javascript:refreshCaptcha()" class="refresh_code">看不清楚，换一个 </a>

                        <div class="Validform_checktip"></div>
                        <input type="hidden" name="guideUserId" value="${guide.userId}">
                        <input id="reply_btn" type="submit" name="reply_btn" value="提交" class="reply_btn pie">
                    </div>
                </form>

                <ul>
                    <div id="leaveMsgDiv">
                        <!--留言列表-->
                    </div>
                </ul>
                <div class="more_list_item last ac"><a href="javascript:getMsgDataList(false)">查看更多
                    <i class="arrow"></i></a></div>
                <!-- 留言 end -->
            </div>
        </div>

    </div>
    <!-- w584 end -->
    <div class="block w380">
        <div class="side_guide_info">
            <div class="guide_price">￥<strong>${guide.amount}</strong><span>/天</span></div>
            <div class="col1">
                <div class="guide_header pie">
                    <c:if test="${not empty guide.headImg}"><img src="${guide.headImg} " class="pie"
                                                              alt=""></c:if>
                    <c:if test="${empty guide.headImg}"><img src="/images/default_header_big.png" class="pie"
                                                             alt=""></c:if>
                </div>
                <div class="name">${guide.realName}<span class="age">${guide.age}岁</span></div>
                <div class="addr"><i class="sprite icon_location_bl"></i>${guide.permanentCity}</div>
                <p><span class="null_stars"><span class="stars"><span class="star${guide.starLevel}"></span></span>
                    ${fn:length(tourGrades)}条评论</p>
            </div>
            <div class="col2">
                <ul>
                    <li><i class="sprite icon_up"></i>${guide.speciality}</li>
                    <li><i class="sprite icon_dial"></i>${guide.language}</li>
                    <li><i class="sprite icon_tel"></i>${guide.maskMobileNo}</li>
                    <li><i class="sprite icon_flag"></i>${guide.tourTime}年</li>
                </ul>
            </div>
            <form action="/portal/services/orderInfo/createOrder.html" method="post" id="booking_form"
                  name="booking_form"
                  class="booking_form">
                <div class="t">预约日期</div>
                <div class="date_label"><span>开始</span><span>结束</span></div>

                <div class="date_container book_date mt10">
                    <div class="date_input pie">
                        <div class="room_num">
                            <span id="checkin-date" class="date_txt">年-月-日</span>
                            <span id="checkout-date" class="date_txt">年-月-日</span>
                        </div>
                    </div>
                    <span class="total none">共<em id="total-days"></em>天</span>
                    <!-- Swiper -->
                    <div class="swiper-container date_swiper pie">
                        <div id="calendar-box" class="calendar swiper-wrapper"></div>
                        <!-- Add Navigation -->
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-button-next"></div>
                    </div>
                </div>

                <div class="person_num_label">人数</div>
                <div class="person_num mt10">
                    <div class="person_num_head pie"><span id="person_num_1">0</span>个成人，<span
                            id="person_num_2">0</span>个儿童<i class="arrow"></i></div>
                    <div class="person_selection pie">
                        <div class="item set_num_1"><span class="info">成人</span><a href="javascript:;"
                                                                                   class="J_minus_1 pie">-</a>
                            <input type="text" value="0" class="J_input_1"/><a href="javascript:;"
                                                                               class="J_add_1 pie">+</a></div>
                        <div class="item set_num_2 mt30"><span class="info">儿童</span><a href="javascript:;"
                                                                                        class="J_minus_2 pie">-</a>
                            <input type="text" value="0" class="J_input_2"/><a href="javascript:;"
                                                                               class="J_add_2 pie">+</a></div>
                    </div>
                </div>
                <div class="price">费用合计<strong class="num" id="totalPrice"><i>￥</i>${guide.amount}</strong></div>
                <input type="hidden" id="amountPerDay" value="${guide.amount}">
                <input type="hidden" id="dateStart" name="dateStart">
                <input type="hidden" id="dateEnd" name="dateEnd">
                <input type="hidden" id="totalDays" name="totalDays">
                <input type="hidden" id="childrenNum" name="childrenNum">
                <input type="hidden" id="adultNum" name="adultNum">
                <input type="hidden" id="guideUserId" name="guideUserId" value="${guide.userId}">
                <input type="button" id="booking_btn" name="booking_btn" value="立即预定" class="booking_btn pie">
            </form>
        </div>
    </div>
    <!-- w380 end -->
</div>
<!-- main content end -->
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>

<!-- 加减人数 -->
<script src="/js/num_plus.js"></script>
<script type="text/javascript" src="/js/choose_date.js"></script>
<script type="text/javascript" src="/js/kalendae.standalone.js"></script>
<!--初始化锁定日期-->
<script type="text/javascript">
    $(function () {
        if (!document.getElementById('guide_calendar')) return false;
        var calendarBox = document.getElementById('guide_calendar');
        /*使用方式：
         new Kalendae(指定容器,配置);
         配置属性注解：
         months属性表示日历显示几个月，值：1、2、3.....；默认值：1
         mode属性表示显示的是单选、多选还是范围，值：'single'、'multiple'、'range'；默认值：'single'
         subscribe属性表示绑定kalendea指定的事件，支持的事件有change、date-clicked、view-changed
         */

        var classMap = ${lockedDays};
        for (var lockedDay = 0; lockedDay < classMap.length; lockedDay++) {
            classMap[Kalendae.moment(classMap[lockedDay], 'YYYY-MM-DD').format('YYYY-MM-DD')] = 'k-blackout';
        }
        var calendar = new Kalendae({
            attachTo: calendarBox,
            months: 3,
            mode: 'multiple',
            direction: 'today-future',
            dateClassMap: classMap,
            blackout: function (date) {
                var j = classMap.length;
                while (j--) {
                    if (date.format('YYYY-MM-DD') == classMap[j]) {
                        return classMap[j];
                    }
                }
            },
            subscribe: {
                'date-clicked': function (date) {
                    return false;
                }
            }
            // 设定默认选中的开始和结束日期(multiple模式下，多个日期以逗号分隔)
            // selected:'2017-06-02,2017-06-06,2017-06-07,2017-06-08,2017-06-21'
        });

        // 设置导航月份
        var today = new Date();
        thisMonth = today.getMonth() + 1;
        $('.ca_nav a').each(function (i) {
            $('.ca_nav a').eq(i).html(thisMonth + i + '月');
        });

    });
</script>
<!--tab切换-->
<script type="text/javascript">
    $(function () {
        // 导游相册
        function showGallery() {
            var slider = $('.gallery_con').bxSlider({
                startSlide: 0,
                pagerCustom: '.slider_page',
                slideWidth: 580,
                minSlides: 1,
                maxSlides: 1,
                moveSlides: 1,
                auto: false,
                slideMargin: 0
            });

            // 缩略图
            $('.guide_gallery .bx-next,.guide_gallery .bx-prev').click(function () {
                var count = slider.getSlideCount(),
                    num = +$('.slider_page .active').attr('data-slide-index');

                $('.slider_page').css({
                    'width': count * 102 + 'px'
                });

                if (num > 4) {
                    $('.slider_page').css({
                        'margin-left': -(num - 4) * 102
                    });
                } else {
                    $('.slider_page').css({
                        'margin-left': 0
                    });
                }
            });
        }

        // showGallery();

        // tab slide function
        function tabSlide(nav, idc, item, a) {
            var indicator = $(idc),
                indicatorHalfWidth = a ? indicator.width() / 2 : indicator.height() / 2,
                lis = $(nav).children('li');

            $(nav).tabs(item, {
                effect: 'fade',
                fadeOutSpeed: 0,
                fadeInSpeed: 400,
                onBeforeClick: function (event, index) {
                    var li = lis.eq(index),
                        newPos = (a ? li.position().left : li.position().top) + (a ? (li.width() / 2) : (li.height() / 2)) - indicatorHalfWidth;
                    if (a == 1) {
                        indicator.stop(true).animate({
                            left: newPos
                        }, 600, 'easeInOutExpo');
                    }
                    if (a == 0) {
                        indicator.stop(true).animate({
                            top: newPos
                        }, 600, 'easeInOutExpo');
                    }
                },
                onClick: function (event, index) {
                    if (a == 1) {
                        if (index == 0) {
                            $('.gd_content_1 > .item').show();
                            showGallery();
                            if ($('.bx-viewport .bx-controls')) {
                                $('.bx-viewport .bx-controls').remove();
                            }
                        }
                    }
                }
            });
        }

        // 导游详情 tab
        tabSlide(".gd_tab_1 .nav", ".gd_idc_1", ".gd_content_1 .item", 1); //参数为1，nav横向
        tabSlide(".gd_tab_2 .nav1", ".gd_idc_2", ".gd_content_2 .k-calendar", 0); //参数为0，nav纵向
    });
</script>
<!--业务实现-->
<script type="text/javascript">
    $(function () {
        $("#reply_to_guide").Validform({
            ajaxPost: true,
            tiptype: function (msg, o, cssctl) {
                if (!o.obj.is("form")) {
                    var objtip = o.obj.parents(".form_item").find(".Validform_checktip");
                    cssctl(objtip, o.type);
                    objtip.text(msg);
                }
            },
            showAllError: true,
            beforeCheck: function (curform) {
                //在表单提交执行验证之前执行的函数，curform参数是当前表单对象。
                //这里明确return false的话将不会继续执行验证操作;
                <c:if test="${sessionScope.sessionKeyUserInfo==null}">
                layer.msg("请先登录！");
                return false;
                </c:if>
            },
            usePlugin: {
                jqtransform: {
                    //会在当前表单下查找这些元素;
                    selector: ":checkbox,:radio,.decorate"
                }
            }, callback: function (data) {
                if (data.status == "y") {
                    pageNoMsg = 1;
                    hasNextMsg = true;
                    getMsgDataList(true);
                    refreshCaptcha();
                } else {
                    layer.msg(data.info);
                }
            }
        });

        refreshCaptcha();
    });

    $("#booking_btn").click(function () {
        <c:if test="${sessionScope.sessionKeyUserInfo==null}">
        $(".login_in").click();
        return;
        </c:if>
        if ($("#total-days").text() == "") {
            layer.msg("请选择出行日期！");
            return;
        }
        if ($("#person_num_1").text() == 0 && $("#person_num_1").text() == 0) {
            layer.msg("请填写出行人数！");
            return;
        }
        $.ajax({
            type: "post",
            url: "/portal/services/orderInfo/createOrder.html",
            dataType: 'json',
            async: true,
            data: {
                "dateStart": $("#checkin-date").text(),
                "dateEnd": $("#checkout-date").text(),
                "childrenNum": $("#person_num_2").text(),
                "adultNum": $("#person_num_1").text(),
                "guideUserId": '${guide.userId}'
            },
            beforeSend: function (curform) {
                loading = top.layer.load(2, {
                    shade: [0.3, '#eee'] //0.1透明度的白色背景
                });
            },
            error: function (data) {
//                alert("Connection error");
                return false;
            },
            success: function (data) {
                if (data.success) {
                    window.location.href = "/portal/services/orderInfo/orderDetail/" + data.data.orderNo;
                } else {
                    layer.closeAll();
                    layer.msg(data.message);
                }
            },
        });
    })

    /**
     * 刷新验证码
     */
    function refreshCaptcha() {
        $('#jcaptchaImage').attr("src", "/jcaptcha.jpg?" + new Date());
        $('#captcha').val('');
    }
</script>
<script id="levaeMsgList-template" type="text/html">
    {{#each rows}}
    <li>
        <div class="host">
            <img src="{{headImg}}" alt="" class="img pie">
            <div class="r">
                <div class="name">{{realName}}</div>
                <div class="date">{{createTime}}</div>
                <p class="con">{{content}}</p>
            </div>
        </div>
        {{#each leaveMessageReplys}}
        <div class="reply">
            <div class="name">【导游回复】</div>
            <p class="con">{{replyContent}}</p>
        </div>
        {{/each}}
    </li>
    {{/each}}
</script>
<script type="text/javascript">
    var pageNoMsg = 1;
    var hasNextMsg = true;

    $(document).ready(function () {
        getMsgDataList(true);
    })

    function getMsgDataList(flag) {
        if (!hasNextMsg) {
            top.layer.msg('没有更多了', {icon: 1, time: 2000});
            return;
        }
        var tmpSource = $("#levaeMsgList-template").html();
        var template = Handlebars.compile(tmpSource);
        $.ajax({
            type: "get",
            url: "/portal/guide/leaveMsgList.html",
            dataType: 'json',
            async: true,
            data: {
                "guideUserId": '${guide.userId}',
                "pageNo": pageNoMsg
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
//                alert("Connection error");
                return false;
            },
            success: function (data) {
                console.info(data);
                var listHtml = template(data);
                if (flag) {
                    $('#leaveMsgDiv').html(listHtml);
                } else {
                    $('#leaveMsgDiv').append(listHtml);
                }

                pageNoMsg = pageNoMsg + 1;
                hasNextMsg = data.data.hasNext;
            },
        });
    }
</script>

<script id="articleList-template" type="text/html">
    {{#each rows}}
    <li>
        <a href="/portal/guideHome/getArticleInfo_{{id}}.html" class="img"><img
                src="{{cover}}" alt=""></a>
        <div class="con">
            <div class="t"><a
                    href="/portal/guideHome/getArticleInfo_{{id}}.html">{{title}}</a>
            </div>
            <div class="info"><span><i
                    class="sprite icon_eye_bl"></i>{{readCount}}</span><span><i
                    class="sprite icon_eye_bl"></i>{{praiseCount}}</span></div>
        </div>
    </li>
    {{/each}}
</script>
<script type="text/javascript">
    var pageNoArticle = 1;
    var hasNextArticle = true;

    $(document).ready(function () {
        getArticleDataList(true);
    })

    function getArticleDataList(flag) {
        if (!hasNextArticle) {
            top.layer.msg('没有更多了', {icon: 1, time: 2000});
            return;
        }
        var tmpSource = $("#articleList-template").html();
        var template = Handlebars.compile(tmpSource);
        $.ajax({
            type: "get",
            url: "/portal/guide/guideArticleList.html",
            dataType: 'json',
            async: true,
            data: {
                "guideUserId": '${guide.userId}',
                "pageNo": pageNoArticle
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
//                alert("Connection error");
                return false;
            },
            success: function (data) {
                console.info(data);
                var listHtml = template(data);
                if (flag) {
                    $('#articleListDiv').html(listHtml);
                } else {
                    $('#articleListDiv').append(listHtml);
                }
                $("#totalArticles").text("（共" + data.total + "条）")
                pageNoArticle = pageNoArticle + 1;
                hasNextArticle = data.data.hasNext;
            },
        });
    }
</script>
<script type="text/javascript">
    var flashvars = {
        p: 0,
        e: 1,
        i: '${video.cover}'
    };
    var params = {
        bgcolor: '#FFF',
        allowFullScreen: true,
        allowScriptAccess: 'always',
        wmode: 'transparent'
    };
    var video = ['${video.cusVideo}->video/mp4'];
    CKobject.embed('/plugins/ckplayer6.8/ckplayer.swf', 'videoPlayer', 'ckplayer_videoPlayer', 580, 435, true, flashvars, video, params);
</script>
</body>
</html>
