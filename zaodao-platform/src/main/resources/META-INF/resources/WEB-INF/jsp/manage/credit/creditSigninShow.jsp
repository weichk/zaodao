<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th width="25%">用户名:</th>
		<td>${creditSignin.username}</td>
	</tr>					
	<tr>
		<th>签到时间:</th>
		<td><fmt:formatDate value="${creditSignin.signTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
</table>
</div>
