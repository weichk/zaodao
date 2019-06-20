<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>id:</th>
		<td>${tradeOrderInfo.id}</td>
	</tr>					
	<tr>
		<th width="25%">会员ID:</th>
		<td>${tradeOrderInfo.userId}</td>
	</tr>					
	<tr>
		<th>银行会员ID:</th>
		<td>${tradeOrderInfo.outUserId}</td>
	</tr>					
	<tr>
		<th>订单号:</th>
		<td>${tradeOrderInfo.orderNo}</td>
	</tr>					
	<tr>
		<th>银行订单号:</th>
		<td>${tradeOrderInfo.bankOrderNo}</td>
	</tr>					
	<tr>
		<th>交易名称:</th>
		<td>${tradeOrderInfo.tradeName}</td>
	</tr>					
	<tr>
		<th>交易时间:</th>
		<td><fmt:formatDate value="${tradeOrderInfo.tradeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>交易完成时间:</th>
		<td><fmt:formatDate value="${tradeOrderInfo.finishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>订单金额:</th>
		<td>${tradeOrderInfo.orderAmount}</td>
	</tr>					
	<tr>
		<th>订单交易类型:</th>
		<td>${tradeOrderInfo.orderTradeType.message}</td>
	</tr>					
	<tr>
		<th>订单子交易类型:</th>
		<td>${tradeOrderInfo.orderSubTradeType.message}</td>
	</tr>					
	<tr>
		<th>订单状态:</th>
		<td>${tradeOrderInfo.orderStatus.message}</td>
	</tr>					
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${tradeOrderInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>修改时间:</th>
		<td><fmt:formatDate value="${tradeOrderInfo.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
</table>
</div>
