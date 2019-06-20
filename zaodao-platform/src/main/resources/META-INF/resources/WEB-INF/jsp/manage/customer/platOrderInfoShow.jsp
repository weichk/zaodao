<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>id:</th>
		<td>${platOrderInfo.id}</td>
	</tr>					
	<tr>
		<th width="25%">游客ID:</th>
		<td>${platOrderInfo.touristId}</td>
	</tr>					
	<tr>
		<th>导游ID:</th>
		<td>${platOrderInfo.tourGuideId}</td>
	</tr>					
	<tr>
		<th>订单号:</th>
		<td>${platOrderInfo.orderNo}</td>
	</tr>					
	<tr>
		<th>游玩天数:</th>
		<td>${platOrderInfo.dayCount}</td>
	</tr>					
	<tr>
		<th>成人数:</th>
		<td>${platOrderInfo.adultCount}</td>
	</tr>					
	<tr>
		<th>小孩数:</th>
		<td>${platOrderInfo.childCount}</td>
	</tr>					
	<tr>
		<th>订单金额:</th>
		<td>${platOrderInfo.orderAmount/100}</td>
	</tr>					
	<tr>
		<th>订单状态:</th>
		<td>${platOrderInfo.orderStatus.message}</td>
	</tr>					
	<tr>
		<th>游玩开始时间:</th>
		<td><fmt:formatDate value="${platOrderInfo.startPlayTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>游玩结束时间:</th>
		<td><fmt:formatDate value="${platOrderInfo.endPlayTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>联系人姓名:</th>
		<td>${platOrderInfo.contactName}</td>
	</tr>					
	<tr>
		<th>联系人手机:</th>
		<td>${platOrderInfo.contactPhone}</td>
	</tr>					
	<tr>
		<th>联系人备注:</th>
		<td>${platOrderInfo.contactMemo}</td>
	</tr>					
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${platOrderInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>修改时间:</th>
		<td><fmt:formatDate value="${platOrderInfo.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>每日单价:</th>
		<td>${platOrderInfo.pricePerDay/100}</td>
	</tr>					
</table>
</div>
