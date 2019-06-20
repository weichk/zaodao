<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th width="25%">课程名称:</th>
		<td>${course.courseTitle}</td>
	</tr>
	<tr>
		<th>课程简介:</th>
		<td>${course.courseIntro}</td>
	</tr>
	<tr>
		<th>讲师:</th>
		<td>${course.realName}</td>
	</tr>
	<tr>
		<th>报名费(钙片):</th>
		<td>${course.coursePrice}</td>
	</tr>
	<tr>
		<th>课程时间:</th>
		<td><fmt:formatDate value="${course.courseTime}" pattern="yyyy-MM-dd"/></td>
	</tr>
	<tr>
		<th>预计时长(秒):</th>
		<td>${course.sumRecordTime}</td>
	</tr>
	<tr>
		<th>报名截止时间:</th>
		<td><fmt:formatDate value="${course.endlineTime}" pattern="yyyy-MM-dd"/></td>
	</tr>
	<tr>
		<th>发布时间:</th>
		<td><fmt:formatDate value="${course.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>修改时间:</th>
		<td><fmt:formatDate value="${course.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
</table>
</div>
