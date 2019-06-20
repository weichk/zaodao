<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th width="25%">父ID:</th>
		<td>${shopGoodsType.parentId}</td>
	</tr>					
	<tr>
		<th>编码:</th>
		<td>${shopGoodsType.code}</td>
	</tr>					
	<tr>
		<th>名称:</th>
		<td>${shopGoodsType.name}</td>
	</tr>					
	<tr>
		<th>备注:</th>
		<td>${shopGoodsType.comments}</td>
	</tr>					
</table>
</div>
