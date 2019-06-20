<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_account_editform" action="${pageContext.request.contextPath}/manage/account/account/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="account" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">partner_id：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入partner_id..." style="width:300px;" name="partnerId" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>user_id：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入user_id..." style="width:300px;" name="userId" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>user_name：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入user_name..." style="width:300px;" name="userName" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>account_no：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入account_no..." style="width:300px;" name="accountNo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>account_type：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入account_type..." style="width:300px;" name="accountType" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>balance：</th>
				<td><input type="text" name="balance" size="48" placeholder="请输入balance..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]']"/></td>
			</tr>					
			<tr>
				<th>comments：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入comments..." style="width:300px;" name="comments" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>ext_context_json：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入ext_context_json..." style="width:300px;" name="extContextJson" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>freeze：</th>
				<td><input type="text" name="freeze" size="48" placeholder="请输入freeze..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]']"/></td>
			</tr>					
			<tr>
				<th>last_change_id：</th>
				<td><input type="text" name="lastChangeId" size="48" placeholder="请输入last_change_id..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]']"/></td>
			</tr>					
			<tr>
				<th>memo：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入memo..." style="width:300px;" name="memo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>parent_user_id：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入parent_user_id..." style="width:300px;" name="parentUserId" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>search_path：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入search_path..." style="width:300px;" name="searchPath" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>status：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入status..." style="width:300px;" name="status" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
