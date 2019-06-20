<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>id:</th>
		<td>${customerCard.id}</td>
	</tr>					
	<tr>
		<th width="25%">用户ID:</th>
		<td>${customerCard.userId}</td>
	</tr>					
	<tr>
		<th>用户手机号码:</th>
		<td>${customerCard.mobileNo}</td>
	</tr>					
	<tr>
		<th>绑卡号:</th>
		<td>${customerCard.cardNo}</td>
	</tr>					
	<tr>
		<th>绑卡中文名称:</th>
		<td>${customerCard.cardName}</td>
	</tr>					
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${customerCard.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>更新时间:</th>
		<td><fmt:formatDate value="${customerCard.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
</table>
</div>
