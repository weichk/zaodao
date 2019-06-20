<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>


<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_shopGoodsDetail_searchform','manage_shopGoodsDetail_datagrid');
});
</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_shopGoodsDetail_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
					商品ID:<input type="text" size="15" name="search_EQ_goodId" value="${param.search_EQ_goodId}"  />
					标题:<input type="text" size="15" name="search_LIKE_title" value="${param.search_LIKE_title}"  />
					内容:<input type="text" size="15" name="search_LIKE_body" value="${param.search_LIKE_body}"  />
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:false" onclick="$.acooly.framework.search('manage_shopGoodsDetail_searchform','manage_shopGoodsDetail_datagrid');">查询</a> 
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_shopGoodsDetail_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/credit/shopGoodsDetail/listJson.html" toolbar="#manage_shopGoodsDetail_toolbar" fit="true" border="false" fitColumns="true"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id">ID</th>
			<th field="goodId">商品ID</th>
			<th field="title">标题</th>
			<th field="body">内容</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_shopGoodsDetail_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>
    
    <!-- 每行的Action动作模板 -->
    <div id="manage_shopGoodsDetail_action" style="display: none;">
      <a class="line-action icon-edit" onclick="$.acooly.framework.edit({url:'/manage/credit/shopGoodsDetail/edit.html',id:'{0}',entity:'shopGoodsDetail',width:500,height:400});" href="#" title="编辑"></a>&nbsp
      <a class="line-action icon-show" onclick="$.acooly.framework.show('/manage/credit/shopGoodsDetail/show.html?id={0}',500,400);" href="#" title="查看"></a>&nbsp
      <a class="line-action icon-delete" onclick="$.acooly.framework.remove('/manage/credit/shopGoodsDetail/deleteJson.html','{0}','manage_shopGoodsDetail_datagrid');" href="#" title="删除"></a>
    </div>
    
    <!-- 表格的工具栏 -->
    <div id="manage_shopGoodsDetail_toolbar">
      <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$.acooly.framework.create({url:'/manage/credit/shopGoodsDetail/create.html',entity:'shopGoodsDetail',width:500,height:400})">添加</a> 
      <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="$.acooly.framework.removes('/manage/credit/shopGoodsDetail/deleteJson.html','manage_shopGoodsDetail_datagrid')">批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_shopGoodsDetail_exports_menu',iconCls:'icon-export'">批量导出</a>
      <div id="manage_shopGoodsDetail_exports_menu" style="width:150px;">
        <div data-options="iconCls:'icon-excel'" onclick="$.acooly.framework.exports('/manage/credit/shopGoodsDetail/exportXls.html','manage_shopGoodsDetail_searchform','商品详情')">Excel</div>  
        <div data-options="iconCls:'icon-csv'" onclick="$.acooly.framework.exports('/manage/credit/shopGoodsDetail/exportCsv.html','manage_shopGoodsDetail_searchform','商品详情')">CSV</div> 
      </div>
      <a href="#" class="easyui-linkbutton" iconCls="icon-import" plain="true" onclick="$.acooly.framework.imports({url:'/manage/credit/shopGoodsDetail/importView.html',uploader:'manage_shopGoodsDetail_import_uploader_file'});">批量导入</a> 
    </div>
  </div>

</div>
