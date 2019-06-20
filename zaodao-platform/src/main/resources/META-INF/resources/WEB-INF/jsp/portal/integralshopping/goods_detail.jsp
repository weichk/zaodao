<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/base.css" rel="stylesheet" />
    <link href="/css/public.css" rel="stylesheet" />
    <link href="/css/eshop.css" rel="stylesheet" />
    <link href="/js/jqtransformplugin/jqtransform.css" rel="stylesheet" />
    <link rel="stylesheet" href="/css/login_regist.css"/>
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
</head>

<body>

<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=integralShopping"/>

<!-- 右侧工具栏-->
<jsp:include page="/portal/shop/portlet/rigthTool.html"/>

<div class="mall-detail">
    <!--商品详情介绍-->
    <div class="detail-goods wrapper1 clearfix">
        <!--详情图片-->
        <div class="detail-goods-img fl">
            <div class="small-img">
                <img src="${shopGoods.logo}" style="width: 420px;max-height: 350px;">
                <div class="mask-float"></div>
                <div class="cursor"></div>
            </div>
            <div class="max-img">
                <img src="${shopGoods.logo}">
            </div>
        </div>
        <!--详情文字-->
        <div class="detail-goods-info fl">
            <h3>${shopGoods.name}</h3>
            <p class="goods-desc">[有效期:<fmt:formatDate value="${shopGoods.validityDate}" pattern="yyyy-MM-dd"/>]</p>
            <p class="goods-coding">商品编码：${shopGoods.code}</p>
            <div class="goods-price clearfix">
                <p class="fl">
                    <span>积分单价：</span> <span class="dNum"><b>${shopGoods.credits}</b>积分</span>
                </p>
                <p class="fl ml38">
                    <span>市场参考价：</span> <span class="rNum"><b>${shopGoods.marketCredits}</b>元</span>
                </p>
            </div>
            <div class="sep-line"></div>
            <div class="goods-property clearfix mb15">
                <p class="fl">商品品牌：</p>
                <ul class="clearfix fl">
                    <li>
                        <a href="#"> <b></b> <span class="goods-name"> ${shopGoods.brand}</span>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="goods-num clearfix mb15">
                <p class="fl">数量：</p>
                <div class="goods-cal fl">
                    <div>
                        <div class="je-reduce fl" style="width: 32px;height: 30px;">
                            <button class="glyphicon glyphicon-minus" style="width: 30px;height: 30px;"></button>
                        </div>
                        <input type="text" id="quantity" name="quantity" value="1" maxlength="5">
                        <div class="je-add fr ml5" style="width: 30px;height: 30px;">
                            <button class="glyphicon glyphicon-plus" style="width: 30px;height: 30px;"></button>
                        </div>
                    </div>
                    <p class="cal-text">
                        已兑换 <span>${shopGoods.sellQuantity}</span> 件 &nbsp;&nbsp;剩余 <span class="cal-text-remain">${shopGoods.totalQuantity-shopGoods.sellQuantity}</span>件
                    </p>
                </div>
            </div>
            <div class="goods-tips">一个用户最多只能购买${shopGoods.maxBuyNum}件商品</div>
            <!-- 积分单价 -->
            <input type="hidden" id="shopGoodsCredits" value="${shopGoods.credits}">
            <input type="hidden" id="goodsId" value="${shopGoods.id}">
            <input type="hidden" id="deliveryType" value="${shopGoods.deliveryType}">
            <input type="hidden" id="availablePoint" value="">
            <div class="exchange mt20">
                <c:if test="${sessionScope.sessionKeyUserInfo == null}">
                <!-- 未登录 -->
                    <button id="signinButton" onclick="$.aportal.loginTips(this);" class="btn btn-success" style="font-size: 14px;">立即兑换</button>
                </c:if>
                <c:if test="${sessionScope.sessionKeyUserInfo != null}">
                    <!-- 已登录 -->
                    <button id="signinButton" onclick="pointPaymentShow()" class="btn btn-success" style="font-size: 14px;">立即兑换</button>
                </c:if>
            </div>
        </div>
    </div>
</div>
<!--商品信息-->
<div class="goods-overhead-info">
    <div class="wrapper1">
        <div class="info-title">商品信息</div>
        <div class="info-detail">
           ${shopGoods.shopGoodsDetail.body}
        </div>
    </div>
</div>
<!-- main content end -->

<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>
<!-- jquery -->
<script type="text/javascript" src="/plugins/eshop_layer/layer.min.js"></script>
<script type="text/javascript" src="/js/aportal.js"></script>
<script type="text/javascript" src="/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="/js/validform_datatype.js"></script>
<!-- 登录注册弹出框 -->
<script type="text/javascript" src="/js/eshop_login_regist.js"></script>

<script type="text/javascript" src="/js/point.js"></script>
<script type="text/javascript" src="/js/jsaddress.js"></script>
<script src="/js/shop_menu.js"></script>
<script type="text/javascript">
    function pointPaymentShow() {
        var shopGoodsCredits = $("#shopGoodsCredits").val();
        var availablePoint = parseInt($("#availablePoint").val());
        var quantity = parseInt($("#quantity").val());
        if ((shopGoodsCredits * quantity) > availablePoint) {
            $.aportal.msg("积分不足,无法购买此商品!", 2, 8);
            return;
        }
        $.point.paymentShow();
    }

    $(function() {
        $(".je-reduce").click(function() {
            var quantity = parseInt($("#quantity").val());
            if (!(quantity % 1 === 0)) {
                $.aportal.msg("请填写正确的购买数量", 2, 8);
                return;
            }
            if (quantity > 1) {
                $("#quantity").val(quantity - 1);
            } else {
                $("#quantity").val(1);
            }
        })

        $(".je-add").click(function() {
            var quantity = parseInt($("#quantity").val());
            if (!(quantity % 1 === 0)) {
                $.aportal.msg("请填写正确的购买数量", 2, 8);
                return;
            }
            if (quantity > 0) {
                $("#quantity").val(quantity + 1);
            } else {
                $("#quantity").val(1);
            }
        })
    })

    //点击增加
    $(".cal-plus").click(function() {
        var val = parseFloat($("#amount").val());
        if (val < 10) { //小于10可以加1
            val += 1;
            $("#amount").val(val);
        }
    })
    //点击减少
    $(".cal-cuts").click(function() {
        var val = parseFloat($("#amount").val());
        if (val > 1) { //大于1可以减1
            val -= 1;
            $("#amount").val(val);
        }
    })
    //当输入时
    $("#amount").keyup(function() {
        $(this).val($(this).val().replace(/\D/g, '')); //只允许输入数字（非数字字符替换为空）
        var val = parseFloat($(this).val());
        if (val <= 1) { //输入不小于1
            $(this).val(1);
        } else if (val >= 10) { //不大于10
            $(this).val(10);
        }
    })
    //商品放大镜显示
    $(function() {
        var box = $(".detail-goods-img"); //容器
        var smallImg = box.find(".small-img"); //小图片
        var mask = smallImg.find(".mask-float"); //放大镜
        var moveObj = smallImg.find(".cursor");
        var big = box.find(".max-img");
        var bigImg = big.children("img"); //大图片
        //放大镜和大图片显示与隐藏
        smallImg.hover(function() {
            $(this).children(".mask-float").show();
            $(this).siblings(".max-img").show();
        }, function() {
            $(this).children(".mask-float").hide();
            $(this).siblings(".max-img").hide();
        })
        //放大镜移动
        moveObj.on("mousemove", function(event) { //通过获取鼠标的坐标赋值给放大镜的left和top值
            var event = event || window.event;
            var left = event.pageX - box.offset().left - smallImg.position().left - mask.width() / 2;
            var top = event.pageY - box.offset().top - smallImg.position().top - mask.height() / 2;
            if (left < 0) { //放大镜left的最小值
                left = 0;
            } else if (left > $(this).width() - mask.width()) { //放大镜left的最大值
                left = $(this).width() - mask.width();
            }
            if (top < 0) { //放大镜top的最小值
                top = 0;
            } else if (top > $(this).height() - mask.height()) { //放大镜top的最大值
                top = $(this).height() - mask.height();
            }
            mask.css({
                "left": left + "px",
                "top": top + "px"
            });
            //大图与小图存在下面的比例关系
            var bigX = left / ($(this).width() - mask.width());
            var bigY = top / ($(this).height() - mask.height());
            var bigImgLeft = bigX * (bigImg.width() - big.width());
            var bigImgTop = bigY * (bigImg.height() - big.height());
            //大图放大
            bigImg.css({
                "left": -bigImgLeft + "px",
                "top": -bigImgTop + "px"
            });
        });
    });
</script>
</body>
</html>