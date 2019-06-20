<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!--右侧边栏-->
<div class="side-bar">
    <ul>
        <c:if test="${serviceQQs != null}">
            <li class="side-bar-sev">
                <span class="i1"></span>
                <a href="#">在线客服</a>
                <div class="side-bar-content">
                    <p>客服qq</p>
                    <dl>
                        <c:forEach items="${serviceQQs}" var="e">
                            <dd><a href="tencent://message/?uin=${e}"><img alt="点击这里给我发消息" src="/picture/qq_online.gif"></a></dd>
                        </c:forEach>
                    </dl>
                    <p>早导qq群</p>
                    <dl>
                        <dd>
                            <a target="_blank" href="${qqGroup}"><img src="/picture/group.png" alt="点击加入qq群"></a>
                        </dd>
                    </dl>
                </div>
                <div class="side-bar-trg"></div>
            </li>
        </c:if>
        <li>
            <span class="i2"></span>
            <a href="/portal/about.html?childIndex=guideProblem">帮助中心</a>
        </li>
        <li class="totop">
            <span class="i4"></span>
            <a href="#">返回顶部</a>
        </li>
    </ul>
</div>

<!--右侧边栏结束-->
<script>
    $(function() {
        // 导航
        function changePos(obj, height) {
            var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
            if (scrollTop < height) {
                obj.style.position = 'absolute';
                obj.style.top = '38px';
            } else {
                obj.style.position = 'fixed';
                obj.style.top = '0px';
            }
        }
        //边栏
        $('.side-bar-sev,.side-bar-cal').hover(function() { //客服展示
            $(this).find('.side-bar-content').show('fast');
            $(this).find('.side-bar-trg').show('fast');
        }, function() {
            $(this).find('.side-bar-content').hide();
            $(this).find('.side-bar-trg').hide();
        });
        var obj = document.getElementById("main_nav");
        if (!obj) return false;
        var _getHeight = obj.offsetTop;
        window.onscroll = function() { //当滚动到大于450显示返回顶部
            var disTop = document.documentElement.scrollTop || document.body.scrollTop;
            console.log();
            disTop > 450 ?
                $('.totop').slideDown() :
                $('.totop').slideUp();

            changePos(obj, _getHeight);
        };
        $('.totop').click(function() { //返回顶部
            $("html, body").animate({
                scrollTop: 0
            }, 300);
            return false;
        });
        $('.nav-tabs li').hover(function() {
            $(this).find('.indexTab').animate({
                left: "-6px"
            });
        }, function() {
            $(this).find('.indexTab').animate({
                left: "0px"
            });
        });
        $(".nav .word").hover(function() {
            $(this).find(".word-menu").show();
        }, function() {
            $(this).find(".word-menu").hide();
        });
    });
</script>