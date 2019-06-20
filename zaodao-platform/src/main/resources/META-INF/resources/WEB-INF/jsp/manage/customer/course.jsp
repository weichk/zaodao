<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<c:if test="${initParam['ssoEnable']=='true'}">
    <%@ include file="/WEB-INF/jsp/manage/common/ssoInclude.jsp" %>
</c:if>
<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_course_searchform','manage_course_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_course_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
                会员唯一标识: <input type="text" class="text" size="15" name="userId"/>
                课程状态:
                <select style="width:80px;height:27px;" name="courseStatus" editable="false" panelHeight="100" class="easyui-combobox">
                    <option value="">所有</option>
                    <c:forEach var="e" items="${allCourseStatuss}">
                        <option value="${e.key}" ${param.search_EQ_courseStatus == e.key?'selected':''}>${e.value}</option>
                    </c:forEach>
                </select>
                创建时间: <input size="15" class="text" name="createTimeStart" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                至<input size="15" class="text" name="createTimeEnd" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                修改时间: <input size="15" class="text" name="updateTimeStart" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                至<input size="15" class="text" name="updateTimeEnd" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
          	    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_course_searchform','manage_course_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_course_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/customer/course/getCourseList.html" toolbar="#manage_course_toolbar" fit="true" border="false" fitColumns="false" pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sum="true">id</th>
			<th field="userId">会员唯一标识</th>
            <th field="coursePrice">所需积分</th>
			<th field="courseTitle" data-options="formatter:formatCourseTitle">课程名称</th>
			<th field="courseIntro" data-options="formatter:formatCourseIntro">课程简介</th>
			<th field="courseStatus" formatter="mappingFormatter">课程状态</th>
            <th field="publishTime" formatter="dateTimeFormatter">发布时间</th>
		    <th field="createTime" formatter="dateTimeFormatter">创建时间</th>
		    <th field="updateTime" formatter="dateTimeFormatter">修改时间</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_course_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_course_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/customer/course/edit.html',id:'{0}',entity:'course',width:500,height:400});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/customer/course/show.html?id={0}',500,400);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/customer/course/deleteJson.html','{0}','manage_course_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_course_toolbar">
      <%--<a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/customer/course/create.html',entity:'course',width:500,height:400})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>--%>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/customer/course/deleteJson.html','manage_course_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_course_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_course_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/customer/course/exportXls.html','manage_course_searchform','zd_course')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/customer/course/exportCsv.html','manage_course_searchform','zd_course')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/customer/course/importView.html',uploader:'manage_course_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>
</div>
<div id="course_record_list" class="easyui-dialog" title="课程文件列表" style="width:600px;height:340px; padding:5px;  overflow-y:hidden;">
</div>

<script type="text/javascript">
    $(function(){
        $("#course_record_list").dialog({
            iconCls: "icon-list",
            closed: true,
            modal: true
        });
    });

    //格式化课程简介
    function formatCourseTitle(value, row, index){
        return '<div style="width: 100px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; " title="' + value + '">' + value + '</div>';
    }

    //格式化课程简介
    function formatCourseIntro(value, row, index){
        return '<div style="width: 200px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; " title="' + value + '">' + value + '</div>';
    }

//    //文件
//    function formatRecord(value, row, index){
//        return '<a href="#" onclick="showLoadRecord(' + row.id + ')">下载</a>'
//    }
//
//    function showLoadRecord(id){
//        $("#course_record_list").window('open');
//    }
</script>