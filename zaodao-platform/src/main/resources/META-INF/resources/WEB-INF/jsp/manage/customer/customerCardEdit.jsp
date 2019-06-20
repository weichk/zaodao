<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_customerCard_editform" action="${pageContext.request.contextPath}/manage/customer/customerCard/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="customerCard" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">用户ID：</th>
				<td><input type="text" name="userId" size="48" class="easyui-validatebox text" data-options="required:true" validType="byteLength[1,64]"/></td>
			</tr>					
			<tr>
				<th>用户手机号码：</th>
				<td><input type="text" name="mobileNo" size="48" class="easyui-validatebox text" data-options="required:true" validType="byteLength[1,32]"/></td>
			</tr>					
			<tr>
				<th>绑卡号：</th>
				<td><input type="text" name="cardNo" size="48" class="easyui-validatebox text" data-options="required:true" validType="byteLength[1,128]"/></td>
			</tr>					
			<tr>
				<th>绑卡中文名称：</th>
				<td><input type="text" name="cardName" size="48" class="easyui-validatebox text" data-options="required:true" validType="byteLength[1,128]"/></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
