<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th width="25%">商品ID:</th>
		<td>${shopGoodsDetail.goodId}</td>
	</tr>					
	<tr>
		<th>标题:</th>
		<td>${shopGoodsDetail.title}</td>
	</tr>					
	<tr>
		<th>内容:</th>
		<td>${shopGoodsDetail.body}</td>
	</tr>					
</table>
</div>
