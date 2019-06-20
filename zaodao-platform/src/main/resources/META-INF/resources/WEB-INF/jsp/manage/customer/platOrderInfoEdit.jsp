<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_platOrderInfo_editform" action="${pageContext.request.contextPath}/manage/customer/platOrderInfo/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="platOrderInfo" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">游客ID：</th>
				<td><input type="text" name="touristId" size="48" placeholder="请输入游客ID..." class="easyui-validatebox text" data-options="validType:['length[1,64]'],required:true"/></td>
			</tr>					
			<tr>
				<th>导游ID：</th>
				<td><input type="text" name="tourGuideId" size="48" placeholder="请输入导游ID..." class="easyui-validatebox text" data-options="validType:['length[1,64]'],required:true"/></td>
			</tr>					
			<tr>
				<th>订单号：</th>
				<td><input type="text" name="orderNo" size="48" placeholder="请输入订单号..." class="easyui-validatebox text" data-options="validType:['length[1,64]'],required:true"/></td>
			</tr>					
			<tr>
				<th>游玩天数：</th>
				<td><input type="text" name="dayCount" size="48" placeholder="请输入游玩天数..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,10]'],required:true"/></td>
			</tr>					
			<tr>
				<th>成人数：</th>
				<td><input type="text" name="adultCount" size="48" placeholder="请输入成人数..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,10]'],required:true"/></td>
			</tr>					
			<tr>
				<th>小孩数：</th>
				<td><input type="text" name="childCount" size="48" placeholder="请输入小孩数..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,10]'],required:true"/></td>
			</tr>					
			<tr>
				<th>订单金额：</th>
				<td><input type="text" name="orderAmount" value="${platOrderInfo.orderAmount/100}" size="48" placeholder="请输入订单金额..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]'],required:true"/></td>
			</tr>					
			<tr>
				<th>订单状态：</th>
				<td><select name="orderStatus" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" data-options="required:true">
					<c:forEach items="${allOrderStatuss}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>					
			<tr>
				<th>游玩开始时间：</th>
				<td><input type="text" name="startPlayTime" size="20" placeholder="请输入游玩开始时间..." class="easyui-validatebox text" value="<fmt:formatDate value="${platOrderInfo.startPlayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" data-options="required:true" /></td>
			</tr>					
			<tr>
				<th>游玩结束时间：</th>
				<td><input type="text" name="endPlayTime" size="20" placeholder="请输入游玩结束时间..." class="easyui-validatebox text" value="<fmt:formatDate value="${platOrderInfo.endPlayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" data-options="required:true" /></td>
			</tr>					
			<tr>
				<th>联系人姓名：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入联系人姓名..." style="width:300px;" name="contactName" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>联系人手机：</th>
				<td><input type="text" name="contactPhone" size="48" placeholder="请输入联系人手机..." class="easyui-validatebox text" data-options="validType:['length[1,20]'],required:true"/></td>
			</tr>					
			<tr>
				<th>联系人备注：</th>
				<td><input type="text" name="contactMemo" size="48" placeholder="请输入联系人备注..." class="easyui-validatebox text" data-options="validType:['length[1,0]']"/></td>
			</tr>					
			<tr>
				<th>每日单价（元）：</th>
				<td><input type="text" name="pricePerDay" value="${platOrderInfo.pricePerDay/100}" size="48" placeholder="请输入每日单价..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]']"/></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
