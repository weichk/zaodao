<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_area_editform" action="${pageContext.request.contextPath}/manage/area/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="area" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">地区名称：</th>
				<td><input type="text" name="name" size="48" class="easyui-validatebox text" data-options="required:true" validType="byteLength[1,32]"/></td>
			</tr>					
			<tr>
				<th>地区编码：</th>
				<td><input type="text" name="code" size="48" class="easyui-validatebox text"  validType="byteLength[1,20]"/></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
