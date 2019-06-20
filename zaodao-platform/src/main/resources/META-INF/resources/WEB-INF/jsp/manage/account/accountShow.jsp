<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>id:</th>
		<td>${account.id}</td>
	</tr>
	<tr>
		<th>会员ID:</th>
		<td>${account.userId}</td>
	</tr>					
	<tr>
		<th>会员名称:</th>
		<td>${account.userName}</td>
	</tr>					
	<tr>
		<th>账户号:</th>
		<td>${account.accountNo}</td>
	</tr>					
	<tr>
		<th>账户类型:</th>
		<td>${account.accountType}</td>
	</tr>					
	<tr>
		<th>账户余额:</th>
		<td><fmt:formatNumber type="number" value="${account.balance/100}" pattern="0.00" maxFractionDigits="2"/></td>
	</tr>					

	<tr>
		<th>冻结金额:</th>
		<td><fmt:formatNumber type="number" value="${account.freeze/100}" pattern="0.00" maxFractionDigits="2"/></td>
	</tr>					
	<tr>
		<th>最后变更记录ID:</th>
		<td>${account.lastChangeId}</td>
	</tr>
	<tr>
		<th>状态:</th>
		<td>${account.status.message}</td>
	</tr>
	<tr>
		<th>备注:</th>
		<td>${account.comments}</td>
	</tr>
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${account.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>修改时间:</th>
		<td><fmt:formatDate value="${account.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
</table>
</div>
