<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>


<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_shopSupplier_searchform','manage_shopSupplier_datagrid');
});
</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_shopSupplier_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
					名称:<input type="text" size="15" name="search_LIKE_name" value="${param.search_LIKE_name}"  />
					手机号码:<input type="text" size="15" name="search_LIKE_mobileNo" value="${param.search_LIKE_mobileNo}"  />
					状态:<select style="width:80px;" name="search_EQ_status" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allStatuss}"><option value="${e.key}" ${param.search_EQ_status == e.key?'selected':''}>${e.value}</option></c:forEach></select>
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:false" onclick="$.acooly.framework.search('manage_shopSupplier_searchform','manage_shopSupplier_datagrid');">查询</a> 
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_shopSupplier_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/credit/shopSupplier/listJson.html" toolbar="#manage_shopSupplier_toolbar" fit="true" border="false" fitColumns="true"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id">id</th>
			<th field="name">名称</th>
			<th field="mobileNo">手机号码</th>
			<th field="address">地址</th>
			<th field="optUser">操作员</th>
			<th field="status" data-options="formatter:function(value){ return formatRefrence('manage_shopSupplier_datagrid','allStatuss',value);} ">状态</th>
			<th field="comments">备注</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_shopSupplier_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>
    
    <!-- 每行的Action动作模板 -->
    <div id="manage_shopSupplier_action" style="display: none;">
      <a class="line-action icon-edit" onclick="$.acooly.framework.edit({url:'/manage/credit/shopSupplier/edit.html',id:'{0}',entity:'shopSupplier',width:500,height:400});" href="#" title="编辑"></a>&nbsp
      <a class="line-action icon-show" onclick="$.acooly.framework.show('/manage/credit/shopSupplier/show.html?id={0}',500,400);" href="#" title="查看"></a>&nbsp
      <a class="line-action icon-delete" onclick="$.acooly.framework.remove('/manage/credit/shopSupplier/deleteJson.html','{0}','manage_shopSupplier_datagrid');" href="#" title="删除"></a>
    </div>
    
    <!-- 表格的工具栏 -->
    <div id="manage_shopSupplier_toolbar">
      <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$.acooly.framework.create({url:'/manage/credit/shopSupplier/create.html',entity:'shopSupplier',width:500,height:400})">添加</a> 
      <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="$.acooly.framework.removes('/manage/credit/shopSupplier/deleteJson.html','manage_shopSupplier_datagrid')">批量删除</a>
      <!-- 
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_shopSupplier_exports_menu',iconCls:'icon-export'">批量导出</a>
      <div id="manage_shopSupplier_exports_menu" style="width:150px;">
        <div data-options="iconCls:'icon-excel'" onclick="$.acooly.framework.exports('/manage/credit/shopSupplier/exportXls.html','manage_shopSupplier_searchform','商城供货商')">Excel</div>  
        <div data-options="iconCls:'icon-csv'" onclick="$.acooly.framework.exports('/manage/credit/shopSupplier/exportCsv.html','manage_shopSupplier_searchform','商城供货商')">CSV</div> 
      </div>
      <a href="#" class="easyui-linkbutton" iconCls="icon-import" plain="true" onclick="$.acooly.framework.imports({url:'/manage/credit/shopSupplier/importView.html',uploader:'manage_shopSupplier_import_uploader_file'});">批量导入</a> 
       -->
    </div>
  </div>

</div>
