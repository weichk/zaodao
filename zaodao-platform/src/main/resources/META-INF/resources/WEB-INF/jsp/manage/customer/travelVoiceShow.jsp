<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>id:</th>
		<td>${travelVoice.id}</td>
	</tr>					
	<tr>
		<th width="25%">发布用户ID:</th>
		<td>${travelVoice.userId}</td>
	</tr>					
	<tr>
		<th>标题:</th>
		<td>${travelVoice.title}</td>
	</tr>					
	<tr>
		<th>文章内容:</th>
		<td>${travelVoice.content}</td>
	</tr>					
	<tr>
		<th>收藏数:</th>
		<td>${travelVoice.praiseCount}</td>
	</tr>					
	<tr>
		<th>评论数:</th>
		<td>${travelVoice.reviewCount}</td>
	</tr>					
	<tr>
		<th>备注:</th>
		<td>${travelVoice.remark}</td>
	</tr>					
	<tr>
		<th>发布位置名称:</th>
		<td>${travelVoice.positionName}</td>
	</tr>					
	<tr>
		<th>发布位置纬度:</th>
		<td>${travelVoice.positionLat}</td>
	</tr>					
	<tr>
		<th>发布位置经度:</th>
		<td>${travelVoice.positionLng}</td>
	</tr>					
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${travelVoice.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>修改时间:</th>
		<td><fmt:formatDate value="${travelVoice.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
</table>
</div>
