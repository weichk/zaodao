<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<c:if test="${initParam['ssoEnable']=='true'}">
    <%@ include file="/WEB-INF/jsp/manage/common/ssoInclude.jsp" %>
</c:if>
<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_article_searchform','manage_article_datagrid');
});

function formatCoverImagUrl(value,row){
    return '<a class="manage_article_datagrid_cover" target="_blank" href="'+row.cover+'" title="'+row.title+'"><img src="'+value+'" width="16" height="16" /></a>';
}
</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_article_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
					发表人ID: <input type="text" class="text" size="15" name="search_LIKE_userId"/>
					文章标题: <input type="text" class="text" size="15" name="search_LIKE_title"/>
					地区: <input type="text" class="text" size="15" name="search_LIKE_area"/>
					标签: <input type="text" class="text" size="15" name="search_LIKE_label"/>
					文章类型: <select style="width:80px;height:27px;" name="search_EQ_articleType" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allArticleTypes}"><option value="${e.key}" ${param.search_EQ_articleType == e.key?'selected':''}>${e.value}</option></c:forEach></select>
					<%--创建时间: <input size="15" class="text" id="search_GTE_createTime" name="search_GTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />--%>
					<%--至<input size="15" class="text" id="search_LTE_createTime" name="search_LTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />--%>
					<%--修改时间: <input size="15" class="text" id="search_GTE_updateTime" name="search_GTE_updateTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />--%>
					<%--至<input size="15" class="text" id="search_LTE_updateTime" name="search_LTE_updateTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />--%>
					是否加精: <select style="width:80px;height:27px;" name="search_EQ_essenceStatus" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allEssenceStatuss}"><option value="${e.key}" ${param.search_EQ_essenceStatus == e.key?'selected':''}>${e.value}</option></c:forEach></select>
					是否置顶: <select style="width:80px;height:27px;" name="search_EQ_upStatus" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allUpStatuss}"><option value="${e.key}" ${param.search_EQ_upStatus == e.key?'selected':''}>${e.value}</option></c:forEach></select>
					帖子状态: <select style="width:80px;height:27px;" name="search_EQ_articleStatus" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allArticleStatuss}"><option value="${e.key}" ${param.search_EQ_articleStatus == e.key?'selected':''}>${e.value}</option></c:forEach></select>
					帖子展示类型: <select style="width:80px;height:27px;" name="search_EQ_articleHotType" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allArticleHotTypes}"><option value="${e.key}" ${param.search_EQ_articleHotType == e.key?'selected':''}>${e.value}</option></c:forEach></select>
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_article_searchform','manage_article_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_article_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/article/article/listJson.html" toolbar="#manage_article_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sum="true">id</th>
			<th field="userId">发表人ID</th>
			<th field="cover" data-options="formatter:function(value,row,index){ return formatCoverImagUrl(value,row);}">文章封面</th>
			<th field="title">文章标题</th>
			<%--<th field="content">文章内容</th>--%>
			<th field="area">地区</th>
			<th field="scenic">景区</th>
			<th field="label" data-options="formatter:function(value){ return formatRefrence('manage_article_datagrid','allArticleLabels',value);} ">标签</th>
			<th field="readCount" sum="true">阅览数</th>
			<th field="praiseCount" sum="true">点赞数</th>
			<th field="articleType" data-options="formatter:function(value){ return formatRefrence('manage_article_datagrid','allArticleTypes',value);} ">帖子类型</th>
			<th field="articleStatus" data-options="formatter:function(value){ return formatRefrence('manage_article_datagrid','allArticleStatuss',value);} ">帖子状态</th>
			<th field="essenceStatus" data-options="formatter:function(value){ return formatRefrence('manage_article_datagrid','allEssenceStatuss',value);} ">是否加精</th>
			<th field="upStatus" data-options="formatter:function(value){ return formatRefrence('manage_article_datagrid','allUpStatuss',value);} ">是否置顶</th>
			<th field="articleHotType" data-options="formatter:function(value){ return formatRefrence('manage_article_datagrid','allArticleHotTypes',value);} ">帖子展示类型</th>
		    <th field="createTime" formatter="formatDate">创建时间</th>
		    <th field="updateTime" formatter="formatDate">修改时间</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_article_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_article_action" style="display: none;">
      <a
              onclick="$.acooly.framework.edit({url:'/manage/article/article/edit.html',id:'{0}',entity:'article',width:1000,height:600});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/article/article/show.html?id={0}',500,400);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/article/article/deleteJson.html','{0}','manage_article_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_article_toolbar">
      <%--<a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/article/article/create.html',entity:'article',width:500,height:400})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>--%>
      <%--<a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/article/article/deleteJson.html','manage_article_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>--%>
      <%--<a href="#" class="easyui-menubutton" data-options="menu:'#manage_article_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>--%>
      <%--<div id="manage_article_exports_menu" style="width:150px;">--%>
        <%--<div onclick="$.acooly.framework.exports('/manage/article/article/exportXls.html','manage_article_searchform','文章表')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>--%>
        <%--<div onclick="$.acooly.framework.exports('/manage/article/article/exportCsv.html','manage_article_searchform','文章表')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>--%>
      <%--</div>--%>
      <%--<a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/article/article/importView.html',uploader:'manage_article_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>--%>
    </div>
  </div>

</div>
