<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th width="25%">名称:</th>
		<td>${shopSupplier.name}</td>
	</tr>					
	<tr>
		<th>手机号码:</th>
		<td>${shopSupplier.mobileNo}</td>
	</tr>					
	<tr>
		<th>地址:</th>
		<td>${shopSupplier.address}</td>
	</tr>					
	<tr>
		<th>操作员:</th>
		<td>${shopSupplier.optUser}</td>
	</tr>					
	<tr>
		<th>状态:</th>
		<td>${allStatuss[shopSupplier.status]}</td>
	</tr>					
	<tr>
		<th>备注:</th>
		<td>${shopSupplier.comments}</td>
	</tr>					
</table>
</div>
