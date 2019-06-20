<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_courseMake_editform" action="${pageContext.request.contextPath}/manage/customer/course/saveCourseMake.html" method="post">
      <jodd:form bean="course" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th>会员唯一标识：</th>
				<td colspan="2">
					<input id="course_make_user_id" type="text" size="46" readonly="readonly" class="easyui-validatebox text" data-options="required:true" value="${course.userId}"/>
				</td>
			</tr>
			<tr>
				<th>课程名称：</th>
				<td colspan="2">
					<input type="text" name="courseTitle" size="46" placeholder="请输入课程简介..." class="easyui-validatebox text" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>课程简介：</th>
				<td colspan="2">
					<textarea name="courseIntro" rows="1" cols="40" placeholder="请输入课程名称..." style="width:300px;" class="easyui-validatebox" data-options="required:true"></textarea>
				</td>
			</tr>
			<tr>
				<th>讲师：</th>
				<td colspan="2">
					<select id="course_make_user_id_select" name="userId" class="easyui-combobox" data-options="editable:true,panelHeight:200,onChange:onCourseUserChanged" style="width:210px;">
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
				<th>课程价格(钙片)：</th>
				<td colspan="2">
					<input type="text" name="coursePrice" style="width:205px;" placeholder="请输入钙片..." class="easyui-numberbox text" data-options="required:true,min:0"/>
				</td>
			</tr>
			<tr>
				<th>课程时长(秒)：</th>
				<td colspan="2">
					<input type="text" name="sumRecordTime" style="width:205px;" placeholder="请输入课程时长..." class="easyui-numberbox text" data-options="required:true,min:1,max:10000"/>
				</td>
			</tr>

			<tr id="course_make_record_file_tr">
				<th>课程文件：</th>
				<td>
					<span style="color:red">仅支持mp3和wav格式的文件</span><br/>
					<input class='mark-course' type="file" required accept="audio/mpeg,audio/x-wav" name="course_make_record_file0"/>
				</td>
				<td width="80">
					<a href="#" onclick="courseMakeRecordFileAdd(this)">添加</a>
				</td>
			</tr>
			<tr>
				<th>课程封面：</th>
				<td>
					<input type="file" name="course_make_course_img" id="course_make_course_img" accept="image/*" />
				</td>
			</tr>
			<tr>
				<th>课程状态：</th>
				<td>
					<select name="courseStatus" editable="false" class="easyui-combobox" data-options="required:true,panelHeight:100" style="width:210px;">
						<c:forEach items="${partCourseStatuss}" var="e">
							<option value="${e.key}">${e.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
        </table>

      </jodd:form>
    </form>
</div>

<script type="text/javascript">
	//选择讲师事件变更
	function onCourseUserChanged(n, o){
		$('#course_make_user_id').val(n);
	}

	//课程文件全局索引,保证ID不重复
	var index = 0;
	//添加课程文件
	function courseMakeRecordFileAdd(obj){
	    var files = $('.mark-course');
	    if(files.length == 5){
	        $.messager.alert("提示", "一节课程最多5小节(每节不超过15分钟)");
	        return;
		}
	    index = index + 1;
	    var name = "course_make_record_file" + index;
	    var tr = "<tr><th></th><td>";
	    tr += "<input class='mark-course' type='file' required accept='audio/mpeg,audio/x-wav' name='" + name + "'/>";
        tr += "</td><td>";
        tr += "<a href='#' onclick='courseMakeRecordFileAdd(this)'>添加</a>&nbsp;";
        tr += "<a href='#' onclick='courseMakeRecordFileRemove(this)'>移除</a>";
	    tr += "</td></tr>";

        $(obj).parent().parent().after(tr);
	}
    //移除课程文件
	function courseMakeRecordFileRemove(obj){
	    $(obj).parent().parent().remove();
	}
</script>
