<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_courseCreate_editform" action="${pageContext.request.contextPath}/manage/customer/course/${action=='edit'?'updateCourse':'saveCourse'}.html" method="post">
      <jodd:form bean="course" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th>课程名称：</th>
				<td>
					<input type="text" name="courseTitle" size="46" placeholder="请输入课程简介..." class="easyui-validatebox text" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>课程简介：</th>
				<td>
					<textarea name="courseIntro" rows="3" cols="40" placeholder="请输入课程名称..." style="width:300px;" class="easyui-validatebox" data-options="required:true"></textarea>
				</td>
			</tr>
			<tr>
				<th>讲师：</th>
				<td>
					<select id="course_create_courseTeacher" name="userId" class="easyui-combobox" data-options="editable:true,panelHeight:200" style="width:210px;">
						<c:forEach items="${lectors}" var="e">
							<c:choose>
								<c:when test="${course.userId == e.userId}">
									<option value="${e.userId}" selected="selected">${e.realName}</option>
								</c:when>
								<c:otherwise>
									<option value="${e.userId}">${e.realName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>报名费(钙片)：</th>
				<td>
					<input type="text" name="coursePrice" style="width:205px;" placeholder="请输入钙片..." class="easyui-numberbox text" data-options="required:true,min:0"/>
				</td>
			</tr>
			<tr>
				<th>课程时间：</th>
				<td>
					<input type="text" name="courseTime"  style="width:200px;" class="easyui-validatebox text" data-options="required:true" value="<fmt:formatDate value="${course.courseTime}" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
				</td>
			</tr>
			<tr>
				<th>预计时长(秒)：</th>
				<td>
					<input type="text" name="sumRecordTime" style="width:205px;" placeholder="请输入课程时长..." class="easyui-numberbox text" data-options="required:true,min:1,max:10000"/>
				</td>
			</tr>
			<tr>
				<th>截止报名时间：</th>
				<td>
					<input type="text" name="endlineTime" style="width:200px;"  class="easyui-validatebox text" data-options="required:true" value="<fmt:formatDate value="${course.endlineTime}" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
				</td>
			</tr>
			<c:if test="${action=='edit'}">
				<tr>
					<th>报名人数</th>
					<td>
						<input type="text" name="userCount" style="width:200px;" placeholder="请输入报名人数..." class="easyui-numberbox text" data-options="required:true,min:0,max:1000"/>
					</td>
				</tr>
			</c:if>
        </table>
      </jodd:form>
    </form>
</div>

<style type="text/css">

</style>

<script type="text/javascript">
	$(function () {

    });
</script>
