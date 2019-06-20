<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>id:</th>
		<td>${orderServiceFee.id}</td>
	</tr>					
	<tr>
		<th width="25%">收费项:</th>
		<td>${orderServiceFee.feeName}</td>
	</tr>					
	<tr>
		<th>条件名:</th>
		<td>${orderServiceFee.conditionName}</td>
	</tr>					
	<tr>
		<th>条件符号:</th>
		<td>${orderServiceFee.conditionSymbol}</td>
	</tr>					
	<tr>
		<th>条件值:</th>
		<td>${orderServiceFee.conditionValue}</td>
	</tr>					
	<tr>
		<th>收费标准:</th>
		<td>${orderServiceFee.feeScale}</td>
	</tr>					
	<tr>
		<th>收费额度:</th>
		<td>${orderServiceFee.feeAmount}</td>
	</tr>					
	<tr>
		<th>状态:</th>
		<td>${orderServiceFee.feeStatus}</td>
	</tr>					
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${orderServiceFee.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>更新时间:</th>
		<td><fmt:formatDate value="${orderServiceFee.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
</table>
</div>
