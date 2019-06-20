<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>

<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_creditSignin_searchform','manage_creditSignin_datagrid');
});
</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_creditSignin_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
					用户名:<input type="text" size="15" name="search_EQ_userName" value="${param.search_EQ_userName}"  />
					签到时间:<input size="15" id="search_GTE_signTime" name="search_GTE_signTime" size="15" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					至<input size="15" id="search_LTE_signTime" name="search_LTE_signTime" size="15" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 			
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:false" onclick="$.acooly.framework.search('manage_creditSignin_searchform','manage_creditSignin_datagrid');">查询</a> 
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_creditSignin_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/credit/creditSignin/listJson.html" toolbar="#manage_creditSignin_toolbar" fit="true" border="false" fitColumns="true"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id">ID</th>
			<th field="username">用户名</th>
		    <th field="signTime" formatter="formatDate">签到时间</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_creditSignin_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>
    
    <!-- 每行的Action动作模板 -->
    <div id="manage_creditSignin_action" style="display: none;">
      <a class="line-action icon-edit" onclick="$.acooly.framework.edit({url:'/manage/credit/creditSignin/edit.html',id:'{0}',entity:'creditSignin',width:500,height:400});" href="#" title="编辑"></a>&nbsp
      <a class="line-action icon-show" onclick="$.acooly.framework.show('/manage/credit/creditSignin/show.html?id={0}',500,400);" href="#" title="查看"></a>&nbsp
      <a class="line-action icon-delete" onclick="$.acooly.framework.remove('/manage/credit/creditSignin/deleteJson.html','{0}','manage_creditSignin_datagrid');" href="#" title="删除"></a>
    </div>
    
    <!-- 表格的工具栏 -->
    <div id="manage_creditSignin_toolbar">
      <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$.acooly.framework.create({url:'/manage/credit/creditSignin/create.html',entity:'creditSignin',width:500,height:400})">添加</a>
      <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="$.acooly.framework.removes('/manage/credit/creditSignin/deleteJson.html','manage_creditSignin_datagrid')">批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_creditSignin_exports_menu',iconCls:'icon-export'">批量导出</a>
      <div id="manage_creditSignin_exports_menu" style="width:150px;">
        <div data-options="iconCls:'icon-excel'" onclick="$.acooly.framework.exports('/manage/credit/creditSignin/exportXls.html','manage_creditSignin_searchform','积分签到记录')">Excel</div>
        <div data-options="iconCls:'icon-csv'" onclick="$.acooly.framework.exports('/manage/credit/creditSignin/exportCsv.html','manage_creditSignin_searchform','积分签到记录')">CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" iconCls="icon-import" plain="true" onclick="$.acooly.framework.imports({url:'/manage/credit/creditSignin/importView.html',uploader:'manage_creditSignin_import_uploader_file'});">批量导入</a>
    </div>
  </div>

</div>
