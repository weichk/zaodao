<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_courseRecord_editform" action="${pageContext.request.contextPath}/manage/customer/courseRecord/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="courseRecord" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">会员唯一标识：</th>
				<td><input type="text" name="userId" size="48" placeholder="请输入会员唯一标识..." class="easyui-validatebox text" data-options="validType:['length[1,64]'],required:true"/></td>
			</tr>					
			<tr>
				<th>course_id：</th>
				<td><input type="text" name="courseId" size="48" placeholder="请输入course_id..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]']"/></td>
			</tr>					
			<tr>
				<th>音频状态:：</th>
				<td><select name="recordStatus" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" data-options="required:true">
					<c:forEach items="${allRecordStatuss}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>					
			<tr>
				<th>音频标题：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入音频标题..." style="width:300px;" name="recordTitle" class="easyui-validatebox" data-options="validType:['length[1,255]'],required:true"></textarea></td>
			</tr>					
			<tr>
				<th>音频地址：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入音频地址..." style="width:300px;" name="recordUrl" class="easyui-validatebox" data-options="validType:['length[1,1024]'],required:true"></textarea></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
