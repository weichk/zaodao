<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>id:</th>
		<td>${course.id}</td>
	</tr>					
	<tr>
		<th width="25%">会员唯一标识:</th>
		<td>${course.userId}</td>
	</tr>
	<tr>
		<th>所需积分:</th>
		<td>${course.coursePrice}</td>
	</tr>
	<tr>
		<th>课程名称:</th>
		<td>${course.courseTitle}</td>
	</tr>					
	<tr>
		<th>课程简介:</th>
		<td>${course.courseIntro}</td>
	</tr>					
	<tr>
		<th>课程状态::</th>
		<td>${course.courseStatus.message}</td>
	</tr>					
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${course.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>修改时间:</th>
		<td><fmt:formatDate value="${course.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
</table>
</div>
