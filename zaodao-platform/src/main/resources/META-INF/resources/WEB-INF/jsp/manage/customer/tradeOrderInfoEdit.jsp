<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_tradeOrderInfo_editform" action="${pageContext.request.contextPath}/manage/customer/tradeOrderInfo/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="tradeOrderInfo" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">会员ID：</th>
				<td><input type="text" name="userId" size="48" class="easyui-validatebox text" data-options="required:true" validType="byteLength[1,64]"/></td>
			</tr>					
			<tr>
				<th>银行会员ID：</th>
				<td><input type="text" name="outUserId" size="48" class="easyui-validatebox text" data-options="required:true" validType="byteLength[1,64]"/></td>
			</tr>					
			<tr>
				<th>订单号：</th>
				<td><input type="text" name="orderNo" size="48" class="easyui-validatebox text" data-options="required:true" validType="byteLength[1,64]"/></td>
			</tr>					
			<tr>
				<th>银行订单号：</th>
				<td><input type="text" name="bankOrderNo" size="48" class="easyui-validatebox text"  validType="byteLength[1,64]"/></td>
			</tr>					
			<tr>
				<th>交易名称：</th>
				<td><input type="text" name="tradeName" size="48" class="easyui-validatebox text"  validType="byteLength[1,64]"/></td>
			</tr>					
			<tr>
				<th>交易时间：</th>
				<td><input type="text" name="tradeTime" size="20" class="easyui-validatebox text" value="<fmt:formatDate value="${tradeOrderInfo.tradeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" data-options="required:true" /></td>
			</tr>					
			<tr>
				<th>交易完成时间：</th>
				<td><input type="text" name="finishTime" size="20" class="easyui-validatebox text" value="<fmt:formatDate value="${tradeOrderInfo.finishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  /></td>
			</tr>					
			<tr>
				<th>订单金额：</th>
				<td><input type="text" name="orderAmount" size="48" class="easyui-numberbox text" data-options="required:true" validType="byteLength[1,19]"/></td>
			</tr>					
			<tr>
				<th>订单交易类型：</th>
				<td><select name="orderTradeType" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" data-options="required:true">
					<c:forEach items="${allOrderTradeTypes}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>					
			<tr>
				<th>订单子交易类型：</th>
				<td><select name="orderSubTradeType" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" data-options="required:true">
					<c:forEach items="${allOrderSubTradeTypes}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>					
			<tr>
				<th>订单状态：</th>
				<td><select name="orderStatus" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" data-options="required:true">
					<c:forEach items="${allOrderStatuss}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
