<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>id:</th>
		<td>${accountChange.id}</td>
	</tr>
	<tr>
		<th>会员ID:</th>
		<td>${accountChange.userId}</td>
	</tr>
	<tr>
		<th>会员名称:</th>
		<td>${accountChange.userName}</td>
	</tr>
	<tr>
		<th>账户号:</th>
		<td>${accountChange.accountNo}</td>
	</tr>					
	<tr>
		<th>账户类型:</th>
		<td>${accountChange.accountType}</td>
	</tr>					
	<tr>
		<th>交易金额:</th>
		<td>${accountChange.amount}</td>
	</tr>					
	<tr>
		<th>交易后余额:</th>
		<td>${accountChange.balance}</td>
	</tr>					
	<tr>
		<th>订单ID:</th>
		<td>${accountChange.businessId}</td>
	</tr>					
	<tr>
		<th>变更类型:</th>
		<td>${accountChange.changeType.message}</td>
	</tr>
	<tr>
		<th>订单编号:</th>
		<td>${accountChange.orderNo}</td>
	</tr>
	<tr>
		<th>交易会员ID:</th>
		<td>${accountChange.refUserId}</td>
	</tr>					
	<tr>
		<th>交易会员名称:</th>
		<td>${accountChange.refUserName}</td>
	</tr>					
	<tr>
		<th>状态:</th>
		<td>${accountChange.status.message}</td>
	</tr>
	<tr>
		<th>备注:</th>
		<td>${accountChange.comments}</td>
	</tr>
	<tr>
		<th width="25%">创建时间:</th>
		<td><fmt:formatDate value="${accountChange.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
	<tr>
		<th>修改时间:</th>
		<td><fmt:formatDate value="${accountChange.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
</table>
</div>
