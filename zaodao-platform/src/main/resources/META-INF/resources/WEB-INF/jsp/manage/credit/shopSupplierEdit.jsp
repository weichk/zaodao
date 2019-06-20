<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_shopSupplier_editform" action="${pageContext.request.contextPath}/manage/credit/shopSupplier/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="shopSupplier" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">名称：</th>
				<td><input type="text" name="name" class="easyui-validatebox" data-options="required:true" validType="byteLength[1,64]"/></td>
			</tr>
			<tr>
				<th>操作员用户名：</th>
				<td><input type="text" name="optUser" class="easyui-validatebox" data-options="required:true" validType="byteLength[1,12]"/></td>
			</tr>								
			<tr>
				<th>手机号码：</th>
				<td><input type="text" name="mobileNo" class="easyui-validatebox"  validType="byteLength[1,21]"/></td>
			</tr>					
			<tr>
				<th>地址：</th>
				<td><input type="text" name="address" class="easyui-validatebox"  validType="byteLength[1,128]"/></td>
			</tr>					
			<tr>
				<th>状态：</th>
				<td><select name="status" editable="false" panelHeight="auto" class="easyui-combobox" >
					<c:forEach items="${allStatuss}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>					
			<tr>
				<th>备注：</th>
				<td>
				<textarea rows="3" cols="40" name="comments" class="easyui-validatebox"  validType="byteLength[1,128]"></textarea>
				</td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
