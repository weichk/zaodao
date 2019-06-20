<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!-- page: 导游账户管理 -->

<div class="account_info">
    <div class="score">
        <div class="score">我的积分：<span class="num">${pointAccount.available}</span>
            <div class="signature">
                <c:if test="${creditSignin.todayCreditSignin == false || creditSignin == null}">
                    <a href="#" class="singin" onclick="signIn()">签到</a>
                    <span class="tips">今日未签到</span>
                </c:if>
                <c:if test="${creditSignin.todayCreditSignin == true}">
                    <span class="singin">已签到</span>
                    <span class="tips">已连续签到${creditSignin.times}天</span>
                </c:if>
            </div>
        </div>
    </div>

    <div class="bills mt20">
        <ul>
            <li class="i1">
                <div class="t">接单数量</div>
                <div class="num">${countInfo.orderCount/100}个</div>
            </li>
            <li class="i2">
                <div class="t">累计金额</div>
                <div class="num">${countInfo.amountCount/100}元</div>
            </li>
            <li class="i3">
                <div class="t">余额</div>
                <div class="num">${balance}元</div>
            </li>
        </ul>
        <div class="handle">
            <a href="javascript:void(0);" onclick="loadWithdraw()" id="checkout" class="checkout uni_btn pie">提现</a>
        </div>
    </div>

    <div class="subtitle">我的带团日历</div>
    <div class="guide_calendar">
        <div class="guide_calendar_nav gd_tab_2">
            <ul class="ca_nav nav1">
                <li><a href="#" class="current">3月</a></li>
                <li><a href="#">4月</a></li>
                <li><a href="#">5月</a></li>
            </ul>
            <span class="gd_indicator gd_idc_2"></span>
        </div>
        <!-- 导游日历插件 -->
        <div id="guide_calendar" class="guide_calendar_content gd_content_2">
        </div>
    </div>

    <div class="calendar_tips">
        <a href="javascript:saveLockDays()" id="save_calendar" class="save_calendar uni_btn pie">保存</a>
        <p class="c1">点击日期设置不可约，再次点击设置可约</p>
        <p class="c2"><span class="circle"></span>已被预约</p>
    </div>

</div>
<script type="text/javascript" src="/js/kalendae.standalone.js"></script>
<script type="text/javascript">
    function loadWithdraw() {
        window.location.href = '/portal/services/customer/childpage.html?childIndex=deductWithdraw';
    }
    var calendar;
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

        var classMap = ${orderDays};
        for (var lockedDay = 0; lockedDay < classMap.length; lockedDay++) {
            classMap[Kalendae.moment(classMap[lockedDay], 'YYYY-MM-DD').format('YYYY-MM-DD')] = 'k-blackout';
        }
        calendar = new Kalendae({
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
            // 设定默认选中的开始和结束日期(multiple模式下，多个日期以逗号分隔)
            selected:${restDays}
        });

        // 设置导航月份
        var today = new Date();
        thisMonth = today.getMonth() + 1;
        $('.ca_nav a').each(function (i) {
            $('.ca_nav a').eq(i).html(thisMonth + i + '月');
        });
    })
</script>
<%--<script type="text/javascript" src="/js/multi_kalendae.js"></script>--%>
<script type="text/javascript">
    $(function () {

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

        tabSlide(".gd_tab_2 .nav1", ".gd_idc_2", ".gd_content_2 .k-calendar", 0); //参数为0，nav纵向
    });
</script>


<!-- 提现、充值跳转 -->
<script>
    $(document).ready(function () {

        $("#checkout,#checkin").click(function (e) {
            e.preventDefault();
            $(".member_nav a").removeClass('active');
            $("#check_io").addClass('active');

            // 点击左侧菜单－动态加载页面
            var url = $(this).attr("href");
            $('#load_doc').load(url);
        });

    });
</script>

<script type="text/javascript">

    function saveLockDays() {

        var restDays = calendar.getSelected();
        console.info(restDays);
        $.ajax({
            type: "get",
            url: "/portal/services/guideCenter/saveLockDays.html",
            dataType: 'json',
            async: true,
            data: {
                "restDays": restDays
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
                alert("Connection error");
                return false;
            },
            success: function (data) {
                if (data.success) {
                    top.layer.msg('保存成功', {icon: 1, time: 2000});
                } else {
                    top.layer.msg(data.message, {icon: 1, time: 2000});
                }
            },
        });
    }
</script>
<script>
    //签到
    function signIn() {
        $.ajax({
            url: '/portal/services/customer/creditSignin.html',
            cache: false,
            type: 'POST',
            success: function (result) {
                if (result.success) {
                    layer.msg("每日签到恭喜你! " + result.message, {icon: 6, time: 1000}, function () {
                            location.reload();
                        },
                        function () {
                            location.reload();
                        });
                } else {
                    layer.msg(result.message, {icon: 5, time: 1000});
                }
            }
        });
    }
</script>



