<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!doctype html>
<html>

<head>
    <meta charset="utf-8" />
    <title>早导网</title>
    <link rel="stylesheet" href="/css/normalize-min.css" />
    <link href="/js/jqtransformplugin/jqtransform.css" rel="stylesheet" />
    <link rel="stylesheet" href="/css/base.css" />
    <link rel="stylesheet" href="/css/public.css" />
    <link rel="stylesheet" href="/css/idangerous.swiper.css">
    <link rel="stylesheet" href="/css/calendar.css">
    <link rel="stylesheet" href="/css/bxslider.css" />
    <link rel="stylesheet" href="/css/home.css" />
    <link rel="stylesheet" href="/css/login_regist.css" />
    <script src="/js/modernizr.min.js"></script>
</head>

<body class="grey_bg">
<div class="main_nav">
    <div class="main_nav_box">
        <div class="logo"><img src="/images/logo_0809.png" alt=""></div>
        <ul>
            <li><a href="#" class="active">导游论坛</a></li>
            <li><a href="#">导行天下</a></li>
            <li><a href="#">游者无疆</a></li>
            <li><a href="#">早导MALL</a></li>
        </ul>
        <div class="top_login_info">
            <!-- 未登录 -->
            <div class="top_loginout">
                <a href="#" class="login_in">登录</a>|<a href="#" class="regist_account">注册</a>
            </div>
            <!-- 已登录 -->
            <!-- <div class="top_login_in">
                <a href="#" class="loginin_nav"><i class="icon_msg"></i><i class="reddot"></i></a>
                <a href="#" class="loginin_header pie"><img src="/images/guide_photo_01.jpg" alt=""></a>
                <dl class="login_nav_list">
                    <dd><a href="#">我的文章</a></dd>
                    <dt><a href="#">我的媒体库</a></dt>
                    <dd><a href="#">账户信息</a></dd>
                    <dt><a href="#">账户设置</a></dt>
                    <dd><a href="#">我的订单</a></dd>
                    <dd><a href="#">充值提现</a></dd>
                    <dt><a href="#">收支明细</a></dt>
                    <dd><a href="#">退出</a></dd>
                </dl>
            </div> -->
        </div>
    </div>
</div>
<!-- header end -->
<div class="banner">
    <div class="slider_wrap">
        <div id="slider_banner" class="slider_banner">
            <div class="slide"><img src="/images/banner_05.jpg" title="金佛山属典型的喀斯特地质地貌，由于特殊的地理位置和气候条件，在古老的时代，缓冲了第四纪冰川的袭击。" alt=""></div>
            <div class="slide"><img src="/images/banner_080902.jpg" title="金佛山属典型的喀斯特地质地貌，由于特殊的地理位置和气候条件，在古老的时代，缓冲了第四纪冰川的袭击。" alt=""></div>
            <div class="slide"><img src="/images/banner.jpg" title="金佛山属典型的喀斯特地质地貌，由于特殊的地理位置和气候条件，在古老的时代，缓冲了第四纪冰川的袭击。" alt=""></div>
        </div>
        <div id="banner_thumb" class="banner_thumb">
            <div class="banner_thumb_box">
                <a data-slide-index="0" href=""><img src="/images/banner_thumb_01.jpg" /></a>
                <a data-slide-index="1" href=""><img src="/images/banner_thumb_02.jpg" /></a>
                <a data-slide-index="2" href=""><img src="/images/banner_thumb_03.jpg" /></a>
            </div>
        </div>
    </div>
    <!-- slider_banner end -->
</div>
<script>
    var banner = document.getElementById('slider_banner');
    var thumb = document.getElementById('banner_thumb');
    var slide = banner.childNodes;
    for (var i = 0; i < slide.length; i++) {
        if (slide[i].nodeType == 1) {
            slideItem = slide[i];
            break;
        }
    }
    banner.style.height = slideItem.clientHeight + 'px';
    // 缩略图垂直居中
    thumb.style.top = (slideItem.clientHeight - 399) / 2 + 'px';
</script>
<!-- banner end -->
<div class="home_box">
    <div class="home_title0809"><span class="name pie">名导之颜</span><a href="#" class="share pie">分享导游生涯的点点滴滴，你也可以出现在这里~<i></i></a></div>
    <ul class="home_guider_list">
        <li><a href="#"><img src="/images/home_guide_header.jpg" alt=""><span class="name">七七</span></a></li>
        <li><a href="#"><img src="/images/home_guide_header.jpg" alt=""><span class="name">七七</span></a></li>
        <li><a href="#"><img src="/images/home_guide_header.jpg" alt=""><span class="name">七七</span></a></li>
        <li><a href="#"><img src="/images/home_guide_header.jpg" alt=""><span class="name">七七</span></a></li>
        <li><a href="#"><img src="/images/home_guide_header.jpg" alt=""><span class="name">七七</span></a></li>
        <li><a href="#"><img src="/images/home_guide_header.jpg" alt=""><span class="name">七七</span></a></li>
        <li><a href="#"><img src="/images/home_guide_header.jpg" alt=""><span class="name">七七</span></a></li>
        <li><a href="#"><img src="/images/home_guide_header.jpg" alt=""><span class="name">七七</span></a></li>
    </ul>
</div>
<!-- 名导之颜 -->
<div class="home_box">
    <div class="home_title0809"><span class="name pie">名导之颜</span><a href="#" class="share pie">分享导游生涯的点点滴滴，你也可以出现在这里~<i class="heart"></i></a></div>
    <div class="home_in_wrap">

        <div class="home_imgnews_1">
            <div class="img"><img src="/images/home_news_img_080901.jpg" alt=""></div>
            <div class="con">
                <div class="top_news">
                    <h2><a href="#"><span class="type">带团技巧</span>新导游上路有这几点就够了</a><span class="time">2017-08-01   14:52</span></h2>
                    <p class="summary">向旅游者提供令其满意的心理服务，是对旅游者的尊重，也是导游员完美带团的基本条件，所以新导游上路必然要从以下几点开始。自尊心人人都有，自尊心（包括虚荣心）是人的最为敏感的神经向旅游者提供令其满意的心理服务，是对旅游者的尊重，也是导游员完美带团的基本条件，所以新导游上路必然要从以下几点开始。 自尊心人人都有，自尊心（包括虚荣心）......</p>
                    <div class="f">
                        <span class="author"><img src="/images/home_guide_header.jpg" alt="" class="pie">谢春花</span>
                        <span class="view"><i></i>221</span>
                        <span class="msg"><i></i>221</span>
                    </div>
                </div>

                <ul class="home_news_list_1">
                    <li><a href="#"><span class="type">带团日志</span>因为带钱少，游客被遣返，导游看不过去了。</a><span class="date">2017-08-01   14:52</span></li>
                    <li><a href="#"><span class="type">导游培训</span>违规安排购物，丽江127名导游被查。</a><span class="date">2017-08-01   14:52</span></li>
                    <li><a href="#"><span class="type">国内路线</span>黄海之滨的明珠，万国建筑的经典，啤酒飘香的名城。</a><span class="date">2017-08-01   14:52</span></li>
                    <li><a href="#"><span class="type">国际线路</span>迪拜4晚6日自由行，五星酒店任你选。</a><span class="date">2017-08-01   14:52</span></li>
                    <li><a href="#"><span class="type">国际线路</span>以色列-约旦12日游   海航直飞部分城市免费联运。</a><span class="date">2017-08-01   14:52</span></li>
                    <li><a href="#"><span class="type">国际线路</span>卡塔尔航空有望于8月初获得三条国际应急航空线路。</a><span class="date">2017-08-01   14:52</span></li>
                </ul>
            </div>
        </div>

        <ul class="home_news_list_2">
            <li><a href="#">【2017年中总结大会】导游相关的那些事。</a><span class="info">
                        <i class="tag"><img src="/images/home_tag_hot.png" alt="hot"></i>
                        <span class="view"><i></i>221</span>
                        <span class="msg"><i></i>221</span>
                    </span></li>
            <li><a href="#">【2017年中总结大会】导游相关的那些事。</a><span class="info">
                        <i class="tag"><img src="/images/home_tag_new.png" alt="new"></i>
                        <span class="view"><i></i>221</span>
                        <span class="msg"><i></i>221</span>
                    </span></li>
            <li><a href="#">【2017年中总结大会】导游相关的那些事。</a><span class="info">
                        <i class="tag"><img src="/images/home_tag_hot.png" alt="hot"></i>
                        <span class="view"><i></i>221</span>
                        <span class="msg"><i></i>221</span>
                    </span></li>
            <li><a href="#">【2017年中总结大会】导游相关的那些事。</a><span class="info">
                        <i class="tag"><img src="/images/home_tag_new.png" alt="new"></i>
                        <span class="view"><i></i>221</span>
                        <span class="msg"><i></i>221</span>
                    </span></li>
            <li><a href="#">【2017年中总结大会】导游相关的那些事。</a><span class="info">
                        <i class="tag"><img src="/images/home_tag_hot.png" alt="hot"></i>
                        <span class="view"><i></i>221</span>
                        <span class="msg"><i></i>221</span>
                    </span></li>
            <li><a href="#">【2017年中总结大会】导游相关的那些事。</a><span class="info">
                        <i class="tag"><img src="/images/home_tag_new.png" alt="new"></i>
                        <span class="view"><i></i>221</span>
                        <span class="msg"><i></i>221</span>
                    </span></li>
            <li><a href="#">【2017年中总结大会】导游相关的那些事。</a><span class="info">
                        <i class="tag"><img src="/images/home_tag_hot.png" alt="hot"></i>
                        <span class="view"><i></i>221</span>
                        <span class="msg"><i></i>221</span>
                    </span></li>
            <li><a href="#">【2017年中总结大会】导游相关的那些事。</a><span class="info">
                        <i class="tag"><img src="/images/home_tag_new.png" alt="new"></i>
                        <span class="view"><i></i>221</span>
                        <span class="msg"><i></i>221</span>
                    </span></li>
            <li><a href="#">【2017年中总结大会】导游相关的那些事。</a><span class="info">
                        <i class="tag"><img src="/images/home_tag_hot.png" alt="hot"></i>
                        <span class="view"><i></i>221</span>
                        <span class="msg"><i></i>221</span>
                    </span></li>
            <li><a href="#">【2017年中总结大会】导游相关的那些事。</a><span class="info">
                        <i class="tag"><img src="/images/home_tag_new.png" alt="new"></i>
                        <span class="view"><i></i>221</span>
                        <span class="msg"><i></i>221</span>
                    </span></li>
        </ul>

    </div>
</div>

<!-- 旅情播报 -->
<div class="home_box">
    <div class="home_title0809"><span class="name pie">旅情播报</span></div>
    <div class="home_in_wrap">
        <div class="home_imgnews_2">
            <div class="img"><img src="/images/home_news_img_080902.jpg" alt=""></div>
            <div class="con">
                <div class="top_news">
                    <h2><a href="#">导游界“华山论剑”播出详情</a></h2>
                    <p class="summary">各位兄弟姐妹：接重庆市旅游局通知，第三届全国导游大赛已圆满结束即将通过旅游卫视和旅游网视频转播各位兄弟姐妹：接重庆市旅游局通知，第三届全国导游大赛已圆满结束即将通过旅游卫视和旅游网视频转播......</p>
                    <a href="#" class="detail">全文》</a>
                </div>
                <ul class="home_news_list_3">
                    <li><a href="#">山东游客泰曼谷机场被抽查，现金没带够，当即被遣返....</a></li>
                    <li><a href="#">旅游大巴车急刹，导游头撞碎挡风玻璃！同行们，带团注意安全！！旅游大巴车急刹，导游头撞碎挡风玻璃！同行们，带团注意安全！！</a></li>
                    <li><a href="#">第三届全国导游大赛圆满落幕 30位选手荣膺金银铜奖 | 附全名单</a></li>
                    <li><a href="#">自由行碰上带车导游，会擦出怎样的旅行火花？</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<!-- footer -->
<div class="footer">
    <div class="footer_wrap">
        <ul>
            <li>
                <a href="#" class="t"><img src="/images/icon_footer_080901.png" alt=""><span class="name">关于我们</span></a>
                <div class="con">向旅游者提供令其满意的心理服务，是对旅游者的尊重也是导游员完美带团的基本条件，</div>
            </li>
            <li>
                <a href="#" class="t"><img src="/images/icon_footer_080902.png" alt=""><span class="name">服务保障</span></a>
                <div class="con">向旅游者提供令其满意的心理服务，是对旅游者的尊重也是导游员完美带团的基本条件，</div>
            </li>
            <li>
                <a href="#" class="t"><img src="/images/icon_footer_080903.png" alt=""><span class="name">支持机构</span></a>
                <div class="con">向旅游者提供令其满意的心理服务，是对旅游者的尊重也是导游员完美带团的基本条件，</div>
            </li>
            <li>
                <a href="#" class="t"><img src="/images/icon_footer_080904.png" alt=""><span class="name">常见问题</span></a>
                <div class="con">向旅游者提供令其满意的心理服务，是对旅游者的尊重也是导游员完美带团的基本条件，</div>
            </li>
            <li>
                <a href="#" class="t"><img src="/images/icon_footer_080905.png" alt=""><span class="name">常见问题</span></a>
                <div class="con">向旅游者提供令其满意的心理服务，是对旅游者的尊重也是导游员完美带团的基本条件，</div>
            </li>
        </ul>
        <div class="copyright">© 2017 Mafengwo.cn 京ICP备11015476号 京公网安备110105013401号 京ICP证110318号</div>
    </div>
</div>
<!-- footer end -->
<!-- 回到顶部 -->
<div id="gotop" class="sprite icon_top"></div>
<!-- 右侧固定签到 -->
<a class="fixed_signin"></a>
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<!--[if (gte IE 6)&(lte IE 8)]>
<script src="/js/selectivizr.js"></script>
<![endif]-->
<script type="text/javascript" src="/js/global.js"></script>
<!-- slider -->
<script src="/js/jquery.bxslider.min.js"></script>
<script>
    $(document).ready(function() {

        $('.slider_banner').bxSlider({
            controls: false,
            minSlides: 1,
            maxSlides: 1,
            moveSlides: 1,
            auto: true,
            pagerCustom: '.banner_thumb_box',
            slideMargin: 0,
            autoHover: true,
            captions: true
        });

    });
</script>
<!-- 表单美化 -->
<script type="text/javascript" src="/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/js/jquery.jqtransform.js"></script>

<script src="/js/layer/layer.js"></script>
<!-- 登录、注册弹窗 -->
<script src="/js/login_regist.js"></script>
<script>
    // 欢迎回来
    $(function() {
        layer.open({
            type: 1,
            title: false,
            area: ['590px', '398px'],
            closeBtn: 0,
            shadeClose: true,
            skin: 'popup_welcomeback',
            content: '<div class="box"><img src="/images/welcome_back.png" alt="欢迎回来" /></div>',
            success: function(layero, index) {
                setTimeout(function() {
                    layer.close(index);
                }, 2000);
            }
        });
    });
</script>
</body>

</html>
