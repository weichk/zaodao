<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!-- page: 收支明细 -->

<div class="balance_payment page_wrap">
    <div class="mpage_title">收支明细</div>
    <form action="#" method="post" name="form_bp_sch" class="form_bp_sch mt30">
        <span class="date">时间：<input type="text" name="start_date" class="laydate-icon start_date" id="start_time_id"><input type="text" name="end_date" id="end_time_id" class="laydate-icon end_date"></span>
        <span class="city">收支类型：<span class="select"><select name="tradingType" id="trading_type_id">
                <option value="all" selected>全部</option>
                <option value="travel">旅游</option>
                <option value="withdraw">提现</option>
                <option value="deposit">充值</option>
            </select></span></span>
        <input type="button" name="bp_sch_bt" value="查询" class="uni_btn pie" onclick="tradingRecordList(1,1)">
    </form>

    <div id="trading_record_list_id">

    </div>
</div>


<script id="tradingRecordList-template" type="text/html">
    <table width="100%" cellpadding="0" cellspacing="0" border="0" class="table mt30">
        <tr>
            <th width="16%">交易日期</th>
            <th width="13%">出行地点</th>
            <th width="12%">导游</th>
            <th width="13%">收支金额</th>
            <th width="13%">余额</th>
            <th>收支类型</th>
        </tr>
        <tr>
            <td colspan="7" class="space2"></td>
        </tr>
        {{#each rows}}
        <tr>
            <td class="col1">{{prettifyDate createTime}}</td>
            <td>{{touristSite}}</td>
            <td>{{guideName}}</td>
            {{#myif inOutType '==' 'in'}}
                <td class="col5">+{{fmoney tradingAmount 2}}</td>
            {{else}}
                <td class="col4">-{{fmoney tradingAmount 2}}</td>
            {{/myif}}
            <td>{{fmoney balance 2}}</td>
            <td class="col6">{{hTradingType tradingType}}</td>
        </tr>
        {{/each}}
    </table>
    <div class="pages" id="tradingRecordPageId">

    </div>
</script>
<!-- 日历插件 -->
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="/js/platformjs/income_detail.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function() {
        $(".form_bp_sch").Validform({
            tiptype:function(msg,o,cssctl){
                if(!o.obj.is("form")){
                    var objtip=o.obj.parents(".form_item").find(".Validform_checktip");
                    // alert(o.obj[0].tagName);
                    cssctl(objtip,o.type);
                    objtip.text(msg);
                }
            },
            showAllError:true,
            usePlugin:{
                jqtransform:{
                    //会在当前表单下查找这些元素;
                    selector:"select,:checkbox,:radio,.decorate"
                }
            }
        });
    });
</script>

<!-- 美化表格 -->
<script>
    $(function() {
        $('.table tr td:nth-child(1)').addClass('col1');
        $('.table tr td:nth-child(4)').addClass('col4');
        $('.table tr td:nth-child(5)').addClass('col5');
        $('.table tr td:nth-child(6)').addClass('col6');
    })
</script>

