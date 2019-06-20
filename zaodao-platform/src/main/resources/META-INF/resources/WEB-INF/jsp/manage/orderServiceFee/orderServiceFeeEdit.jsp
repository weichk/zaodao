<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_orderServiceFee_editform" action="${pageContext.request.contextPath}/manage/orderServiceFee/orderServiceFee/${action=='create'?'saveServiceFee':'modifyServiceFee'}.html" method="post">
      <jodd:form bean="orderServiceFee" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="15%">收费项目：</th>
				<td>
					<select name="feeName" editable="false" style="height:27px; width:400px;" panelHeight="100" class="easyui-combobox" data-options="required:true">
						<c:forEach items="${allFeeNames}" var="e">
							<option value="${e.key}">${e.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>					
			<tr>
				<th>收费条件：</th>
				<td>
					<div id="fee_edit_con_container"></div>
				</td>
			</tr>					
			<tr>
				<th>收费标准：</th>
				<td>
					<select id="fee_edit_scale" name="feeScale" editable="false" style="height:27px; width:80px;" panelHeight="100" class="easyui-combobox" data-options="required:true">
						<c:forEach items="${allFeeScales}" var="e">
							<option value="${e.key}">${e.value}</option>
						</c:forEach>
					</select>
					<input id="fee_edit_amount" type="text" name="feeAmount" placeholder="请输入值..." style="height:27px;line-height: 27px; width:120px;" class="easyui-numberbox text" data-options="min:0,max:100,value:0,precision:2"/>
					&nbsp;<label id="fee_edit_scale_unit">${unit}</label>
				</td>
			</tr>					
			<tr>
				<th>状态：</th>
				<td>
					<select name="feeStatus" editable="false" style="height:27px; width:80px;" panelHeight="100" class="easyui-combobox" data-options="required:true">
						<c:forEach items="${allFeeStatuss}" var="e">
							<option value="${e.key}">${e.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>排序：</th>
				<td>
					<input type="text" name="feeSort" placeholder="请输入排序值..." style="height:27px;line-height: 27px; width:120px;" class="easyui-numberbox text" data-options="min:0,max:1000,value:0"/>
				</td>
			</tr>
        </table>
      </jodd:form>
    </form>
</div>

<style type="text/css">
	.fee-edit-condition-name{
		width:140px; height:27px;
	}
	.fee-edit-condition-symbol{
		width:75px; height:27px; margin-left: 3px;
	}
	.fee-edit-condition-value-txt{
		height: 21px; width:100px; margin-left: 3px;
	}
	.fee-edit-condition-value-sel{
		width:80px; margin-left: 3px;
	}
	.fee-edit-condition-add{
		margin-left: 5px;
	}

</style>

<script type="text/javascript">
    var SERVICE_FEE_INDEX = 0;

	$(function(){
	    if(${action=='create'}) {
            $("#fee_edit_con_container").html(getServiceFeeConditionHtml(SERVICE_FEE_INDEX++));
        }else{
            $("#fee_edit_con_container").html(setServiceFeeConditionHtml());
		}

        $("#fee_edit_scale").combobox({
            onSelect:function(record){
                if(record.value == 'fixed_fee'){
                    $("#fee_edit_scale_unit").html("元");
				}else if(record.value == 'percent_fee'){
                    $("#fee_edit_scale_unit").html("%");
				}
			}
		});

	});

	/**
	 * 编辑服务费，渲染收费条件
	 */
	function setServiceFeeConditionHtml(){
	    var html = "";
	    var i = 0;
		<c:forEach items="${conditions}" var="e">
        html += getServiceFeeConditionHtml(i++, '${e.conditionName}', '${e.conditionSymbol}', '${e.conditionValue}');
		</c:forEach>
        SERVICE_FEE_INDEX = i;
		return html;
	}

    /**
	 * 获取收费条件条件Html
     */
	function getServiceFeeConditionHtml(index, conditionName, conditionSymbol, conditionValue){
	    var html = "<div id='fee_edit_con_div_" + index + "' style='margin-top:5px;'>";

	    html += "<select id='fee_edit_con_name_com_" + index +"' name='conditionName_" + index + "' class='fee-edit-condition-name' onchange='onConditionNameChange(this)'>";
        <c:forEach items="${allConditionNames}" var="e">
			if(conditionName != null && '${e.key}' == conditionName){
                html += "<option value='${e.key}' selected>${e.value}</option>";
			}else{
        		html += "<option value='${e.key}'>${e.value}</option>";
			}
        </c:forEach>
        html += "</select>";

		html += "<select id='fee_edit_con_symbol_com_" + index + "' name='conditionSymbol_" + index + "' class='fee-edit-condition-symbol'>";
        <c:forEach items="${allConditionSymbols}" var="e">
			if(conditionSymbol != null && '${e.key}' == conditionSymbol){
				html += "<option value='${e.key}' selected>${e.value}</option>";
			}else{
				html += "<option value='${e.key}'>${e.value}</option>";
			}
        </c:forEach>
		html += "</select>";

		//如果是编辑操作
		if(conditionName != null){
		    var displayEle = 0;
		    var txtValue = "";
		    if(conditionName == 'cancel_days') {
                //显示天数
                displayEle = 0;
                txtValue = conditionValue;
            }else if(conditionName == 'order_status'){
		        //显示订单状态
                displayEle = 1;
			}
			//取消订单提前天数标签和订单状态标签只能显示一个
            html += "<input id='fee_edit_con_value_txt_" + index + "' name='conditionValueTxt_" + index + "' placeholder='请输入天数...'  size='48' class='fee-edit-condition-value-txt' value='" + txtValue + "' style='"+ (displayEle == 1 ? "display:none" : "") +"'/>";
            html += "<select id='fee_edit_con_value_com_" + index +"' name='conditionValueCom_" + index + "' class='fee-edit-condition-value-sel' style='" + (displayEle == 0 ? "display:none" : "") + "'>";
            <c:forEach items="${allOrderStatuss}" var="e">
            if(conditionValue != null && '${e.key}' == conditionValue){
                html += "<option value='${e.key}' selected>${e.value}</option>";
            }else{
                html += "<option value='${e.key}'>${e.value}</option>";
            }
            </c:forEach>
            html += "</select>";
		}else {
            //添加操作默认显示取消订单提前天数标签
            html += "<input id='fee_edit_con_value_txt_" + index + "' name='conditionValueTxt_" + index + "' placeholder='请输入天数...'  size='48' class='fee-edit-condition-value-txt'/>";
            //添加操作默认不显示订单状态标签
            html += "<select id='fee_edit_con_value_com_" + index +"' name='conditionValueCom_" + index + "' class='fee-edit-condition-value-sel' style='display:none;'>";
            <c:forEach items="${allOrderStatuss}" var="e">
                html += "<option value='${e.key}'>${e.value}</option>";
            </c:forEach>
            html += "</select>";
        }

        html += "<a href='#' class='fee-edit-condition-add' onclick='addServiceFee(this)'>添加</a>&nbsp;";
        if(index > 0){
            html += "<a href='#' onclick='removeServiceFee(this)'>移除</a>";
        }
        html += "</div>";

        return html;
	}

    /**
     * 添加条件
     */
    function addServiceFee(obj){
        var html = "";
        html += getServiceFeeConditionHtml(SERVICE_FEE_INDEX++);
        $(obj).parent().parent().append(html);
    }

    /*
    * 移除条件
    */
    function removeServiceFee(obj){
        $(obj).parent().remove();
    }

    /**
	 *条件名选择事件
     */
    function onConditionNameChange(obj){
		var val = $(obj).val();
        var symbol = $(obj).parent().find(".fee-edit-condition-symbol");
        var valueTxt = $(obj).parent().find(".fee-edit-condition-value-txt");
        var valueSel = $(obj).parent().find(".fee-edit-condition-value-sel");

		if(val == 'order_status'){
		    //如果选择的是订单状态，则符号只能等于
            symbol.val('equal_to');
            //symbol.attr("disabled", "disabled");

			valueTxt.hide();
            valueSel.show();
		}else{
			//symbol.removeAttr("disabled");
            valueSel.hide();
            valueTxt.show();
		}
	}
</script>
