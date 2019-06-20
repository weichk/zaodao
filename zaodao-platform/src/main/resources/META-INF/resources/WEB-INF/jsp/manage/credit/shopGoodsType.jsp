<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>


<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_shopGoodsType_searchform','manage_shopGoodsType_datagrid');
});
</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_shopGoodsType_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
					编码:<input type="text" size="15" name="search_LIKE_code" value="${param.search_LIKE_code}"  />
					名称:<input type="text" size="15" name="search_LIKE_name" value="${param.search_LIKE_name}"  />
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:false" onclick="$.acooly.framework.search('manage_shopGoodsType_searchform','manage_shopGoodsType_datagrid');">查询</a> 
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_shopGoodsType_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/credit/shopGoodsType/listJson.html" toolbar="#manage_shopGoodsType_toolbar" fit="true" border="false" fitColumns="true"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id">id</th>
			<th field="code">编码</th>
			<th field="name">名称</th>
			<th field="comments">备注</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_shopGoodsType_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>
    
    <!-- 每行的Action动作模板 -->
    <div id="manage_shopGoodsType_action" style="display: none;">
      <a class="line-action icon-edit" onclick="$.acooly.framework.edit({url:'/manage/credit/shopGoodsType/edit.html',id:'{0}',entity:'shopGoodsType',width:500,height:400});" href="#" title="编辑"></a>&nbsp
      <a class="line-action icon-show" onclick="$.acooly.framework.show('/manage/credit/shopGoodsType/show.html?id={0}',500,400);" href="#" title="查看"></a>&nbsp
      <a class="line-action icon-delete" onclick="$.acooly.framework.remove('/manage/credit/shopGoodsType/deleteJson.html','{0}','manage_shopGoodsType_datagrid');" href="#" title="删除"></a>
    </div>
    
    <!-- 表格的工具栏 -->
    <div id="manage_shopGoodsType_toolbar">
      <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$.acooly.framework.create({url:'/manage/credit/shopGoodsType/create.html',entity:'shopGoodsType',width:500,height:400})">添加</a> 
      <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="$.acooly.framework.removes('/manage/credit/shopGoodsType/deleteJson.html','manage_shopGoodsType_datagrid')">批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_shopGoodsType_exports_menu',iconCls:'icon-export'">批量导出</a>
      <div id="manage_shopGoodsType_exports_menu" style="width:150px;">
        <div data-options="iconCls:'icon-excel'" onclick="$.acooly.framework.exports('/manage/credit/shopGoodsType/exportXls.html','manage_shopGoodsType_searchform','商品分类')">Excel</div>  
        <div data-options="iconCls:'icon-csv'" onclick="$.acooly.framework.exports('/manage/credit/shopGoodsType/exportCsv.html','manage_shopGoodsType_searchform','商品分类')">CSV</div> 
      </div>
      <a href="#" class="easyui-linkbutton" iconCls="icon-import" plain="true" onclick="$.acooly.framework.imports({url:'/manage/credit/shopGoodsType/importView.html',uploader:'manage_shopGoodsType_import_uploader_file'});">批量导入</a> 
    </div>
  </div>

</div>
