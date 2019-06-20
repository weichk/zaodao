<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>id:</th>
		<td>${courseRecord.id}</td>
	</tr>					
	<tr>
		<th width="25%">会员唯一标识:</th>
		<td>${courseRecord.userId}</td>
	</tr>					
	<tr>
		<th>course_id:</th>
		<td>${courseRecord.courseId}</td>
	</tr>					
	<tr>
		<th>音频状态::</th>
		<td>${courseRecord.recordStatus.message}</td>
	</tr>					
	<tr>
		<th>音频标题:</th>
		<td>${courseRecord.recordTitle}</td>
	</tr>					
	<tr>
		<th>音频地址:</th>
		<td>${courseRecord.recordUrl}</td>
	</tr>					
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${courseRecord.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>修改时间:</th>
		<td><fmt:formatDate value="${courseRecord.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
</table>
</div>
