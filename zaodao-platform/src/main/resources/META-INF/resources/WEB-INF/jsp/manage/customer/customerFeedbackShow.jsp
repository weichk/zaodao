<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>id:</th>
		<td>${customerFeedback.id}</td>
	</tr>					
	<tr>
		<th width="25%">发布用户ID:</th>
		<td>${customerFeedback.userId}</td>
	</tr>					
	<tr>
		<th>标题:</th>
		<td>${customerFeedback.title}</td>
	</tr>					
	<tr>
		<th>反馈内容:</th>
		<td>${customerFeedback.content}</td>
	</tr>					
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${customerFeedback.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>修改时间:</th>
		<td><fmt:formatDate value="${customerFeedback.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
</table>
</div>
