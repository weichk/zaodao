<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<c:if test="${initParam['ssoEnable']=='true'}">
    <%@ include file="/WEB-INF/jsp/manage/common/ssoInclude.jsp" %>
</c:if>
<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_course_list_searchform','manage_course_list_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
  <!-- 报名课程查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_course_list_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
                讲师: <input type="text" name="userName" class="text" size="15"/>
                课程名称: <input type="text" name="courseTitle" class="text" size="15"/>
                发布时间: <input name="publishTimeStart" size="15" class="text" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                至<input name="publishTimeEnd" size="15" class="text" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
          	    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_course_list_searchform','manage_course_list_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_course_list_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/customer/course/getCourseList.html?courseType=enrolable" toolbar="#manage_course_list_toolbar" fit="true" border="false" fitColumns="false" pagination="true" idField="id" pageSize="20" pageList="[ 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
			<th field="id" >id</th>
			<th field="courseTitle">课程名称</th>
            <th field="realName">讲师</th>
			<th field="coursePrice">报名费(钙片)</th>
			<th field="courseTime" formatter="dateFormatter">课程时间</th>
			<th field="sumRecordTime" data-options="formatter:formatSumRecordTime">预计时长</th>
            <th field="endlineTime" formatter="dateFormatter">报名截止时间</th>
		    <th field="userCount">已报名人数</th>
		    <th field="courseStatus" data-options="formatter:formatCourseStatus">状态</th>
          	<th field="rowActions" data-options="formatter:formatCourseAction">操作</th>
        </tr>
      </thead>
    </table>

      <!-- 报名中状态 -->
      <div id="manage_course_list_action" style="display: none;">
          <a onclick="$.acooly.framework.show('/manage/customer/course/courseDetail.html?id={0}',500,400);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>

          <a onclick="$.acooly.framework.edit({url:'/manage/customer/course/courseEdit.html',id:'{0}',entity:'courseCreate',width:500,height:400, onSuccess:function(){
              $.acooly.framework.search('manage_course_list_searchform','manage_course_list_datagrid');
          }});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>

          <a onclick="$.acooly.framework.remove('/manage/customer/course/deleteJson.html','{0}','manage_course_list_datagrid','提示','是否确定删除?',function(){
              $.acooly.framework.search('manage_course_list_searchform','manage_course_list_datagrid');
          });" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
      </div>

      <!-- 完成状态 -->
      <div id="manage_course_list_finish">
          <a onclick="$.acooly.framework.show('/manage/customer/course/courseDetail.html?id={0}',500,400);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      </div>

      <!-- 报名截止状态 -->
      <div id="manage_course_list_endline">
          <a onclick="$.acooly.framework.show('/manage/customer/course/courseDetail.html?id={0}',500,400);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
          <a onclick="$.acooly.framework.edit({url:'/manage/customer/course/courseMake.html',id:'{0}',entity:'courseMake',width:500,height:400, onSuccess:function(){
              $.acooly.framework.search('manage_course_list_searchform','manage_course_list_datagrid');

          }});" href="#" title="制作为课程"><i class="fa fa-play fa-lg fa-fw fa-col"></i></a>
      </div>

      <!-- 新增报名课程 -->
      <div id="manage_course_list_toolbar">
          <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/customer/course/courseCreate.html',entity:'courseCreate',width:500,height:400, onSuccess:function(){
              $.acooly.framework.search('manage_course_list_searchform','manage_course_list_datagrid');
          }})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>新增</a>
      </div>
  </div>
</div>

<script type="text/javascript">
    //课程时长秒->分
    function formatSumRecordTime(value){
        var mod = 0, min = 0;
        if(value > 0){
            mod = value % 60;
            min = parseInt(value / 60);
        }
        return min + '分' + mod + '秒';
    }

    //课程状态
    function formatCourseStatus(value, row, index, data, field){
        //这两个状态的报名课程在此页面显示为已完成状态
        if(value == 'draft' || value == 'published'){ value = 'finish';}

        try {
            var mapping = "all" + field.substring(0, 1).toUpperCase() + field.substring(1, field.length) + "s";
            return data["data"][mapping][value];
        } catch (e) {
            return value;
        }
    }

    //报名课程列表格式化操作列
    function formatCourseAction(value, row, index){
        var courseStatus = row.courseStatus;
        //console.log("courseStatus = " + courseStatus);
        if(courseStatus == 'enroll') {
            return formatAction('manage_course_list_action', value, row);
        }else if(courseStatus == 'endline'){
            return formatAction('manage_course_list_endline', value, row);
        }else if(courseStatus == 'finish'){
            return formatAction('manage_course_list_finish', value, row);
        }else {
            return formatAction('manage_course_list_finish', value, row);
        }
    }
</script>