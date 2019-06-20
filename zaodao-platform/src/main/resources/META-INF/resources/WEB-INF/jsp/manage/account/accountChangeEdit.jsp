<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_accountChange_editform" action="${pageContext.request.contextPath}/manage/account/accountChange/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="accountChange" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th>account_no：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入account_no..." style="width:300px;" name="accountNo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>account_type：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入account_type..." style="width:300px;" name="accountType" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>amount：</th>
				<td><input type="text" name="amount" size="48" placeholder="请输入amount..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]']"/></td>
			</tr>					
			<tr>
				<th>balance：</th>
				<td><input type="text" name="balance" size="48" placeholder="请输入balance..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]']"/></td>
			</tr>					
			<tr>
				<th>business_id：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入business_id..." style="width:300px;" name="businessId" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>change_type：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入change_type..." style="width:300px;" name="changeType" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>comments：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入comments..." style="width:300px;" name="comments" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>freeze：</th>
				<td><input type="text" name="freeze" size="48" placeholder="请输入freeze..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]']"/></td>
			</tr>					
			<tr>
				<th>freeze_balance：</th>
				<td><input type="text" name="freezeBalance" size="48" placeholder="请输入freeze_balance..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]']"/></td>
			</tr>					
			<tr>
				<th>memo：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入memo..." style="width:300px;" name="memo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>order_no：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入order_no..." style="width:300px;" name="orderNo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>partner_id：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入partner_id..." style="width:300px;" name="partnerId" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>previou_change_id：</th>
				<td><input type="text" name="previouChangeId" size="48" placeholder="请输入previou_change_id..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]']"/></td>
			</tr>					
			<tr>
				<th>ref_account_no：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入ref_account_no..." style="width:300px;" name="refAccountNo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>ref_user_id：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入ref_user_id..." style="width:300px;" name="refUserId" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>ref_user_name：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入ref_user_name..." style="width:300px;" name="refUserName" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>status：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入status..." style="width:300px;" name="status" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>trade_type：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入trade_type..." style="width:300px;" name="tradeType" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>trans_date：</th>
				<td><input type="text" name="transDate" size="20" placeholder="请输入trans_date..." class="easyui-validatebox text" value="<fmt:formatDate value="${accountChange.transDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  /></td>
			</tr>					
			<tr>
				<th>transfer_type：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入transfer_type..." style="width:300px;" name="transferType" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>user_id：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入user_id..." style="width:300px;" name="userId" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>user_name：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入user_name..." style="width:300px;" name="userName" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
