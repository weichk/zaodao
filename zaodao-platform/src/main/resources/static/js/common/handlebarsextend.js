(function () {
    //将金额分格式话为元
    Handlebars.registerHelper("fmoney", function (s, n) {
        s = s / 100;
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
        var l = s.split(".")[0].split("").reverse(),
            r = s.split(".")[1];
        t = "";
        for (i = 0; i < l.length; i++) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
        }
        return t.split("").reverse().join("") + "." + r;
    });

    //订单状态
    Handlebars.registerHelper("orderStatus", function (code) {
        return ({
            pay: '已支付',
            noPay: '待付款',
            close: '已取消',
            processing: '支付中',
            confirm:'已确认',
            delete: '已删除'
        })[code];
    });

    //交易类型
    Handlebars.registerHelper("hTradingType", function (code) {
        return ({
            travel: '旅游',
            deposit: '充值',
            withdraw: '提现'
        })[code];
    });

    //格式化时间为YYYY-MM-DD
    Handlebars.registerHelper({
        'prettifyDate': function (timestamp) {//格式化时间
            return timestamp.substring(0, 10);
        }
    });

    //积分订单类型
    Handlebars.registerHelper("hShopOrderStatus", function (code) {
        return ({
            1: '已下单',
            5: '兑换中',
            10: '已兑换',
            15: '已发货',
            20: '已收货',
            0: '订单异常'
        })[code];
    });

    //文章所属标签
    Handlebars.registerHelper("articleLabel", function (code) {
        return ({
            guideWord: '导游词',
            skillsTour: '带团技巧',
            guideTraining: '导游培训',
            domesticRoutes: '国内路线',
            exitRoutes: '境外路线',
            customRoute: '定制路线',
            guideTreeHole: '导游树洞',
            caseTour: '带团案例',
            complaintCase: '投诉案例',
            problemSolving: '问题处理'
        })[code];
    });
})()

