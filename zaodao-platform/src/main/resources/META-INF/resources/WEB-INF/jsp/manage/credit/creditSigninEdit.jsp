<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_creditSignin_editform" action="${pageContext.request.contextPath}/manage/credit/creditSignin/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="creditSignin" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">用户名：</th>
				<td><input type="text" name="username" class="easyui-validatebox" data-options="required:true" validType="byteLength[1,32]"/></td>
			</tr>					
			<tr>
				<th>签到时间：</th>
				<td><input type="text" name="signTime" size="15" value="<fmt:formatDate value="${creditSignin.signTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  /></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
