<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_customerFeedback_editform" action="${pageContext.request.contextPath}/manage/customer/customerFeedback/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="customerFeedback" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">发布用户ID：</th>
				<td><input type="text" name="userId" size="48" placeholder="请输入发布用户ID..." class="easyui-validatebox text" data-options="validType:['length[1,64]']"/></td>
			</tr>					
			<tr>
				<th>标题：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入标题..." style="width:300px;" name="title" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>反馈内容：</th>
				<td><input type="text" name="content" size="48" placeholder="请输入反馈内容..." class="easyui-validatebox text" data-options="validType:['length[1,0]']"/></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
