<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<c:if test="${initParam['ssoEnable']=='true'}">
    <%@ include file="/WEB-INF/jsp/manage/common/ssoInclude.jsp" %>
</c:if>
<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_tourGuide_searchform','manage_tourGuide_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_tourGuide_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
					用户ID: <input type="text" class="text" size="15" name="search_LIKE_userId"/>
					导游封面图: <input type="text" class="text" size="15" name="search_LIKE_guideCoverImg"/>
					导游证号: <input type="text" class="text" size="15" name="search_LIKE_guideNo"/>
					导游资格证号: <input type="text" class="text" size="15" name="search_LIKE_guideCertificateNo"/>
					带团时间: <input type="text" class="text" size="15" name="search_EQ_tourTime"/>
				导游等级: <select style="width:80px;height:27px;" name="search_EQ_tourRank" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allTourRanks}"><option value="${e.key}" ${param.search_EQ_tourRank == e.key?'selected':''}>${e.value}</option></c:forEach></select>
					常驻城市: <input type="text" class="text" size="15" name="search_LIKE_permanentCity"/>
					价格: <input type="text" class="text" size="15" name="search_EQ_pricePerDay"/>
					创建时间: <input size="15" class="text" id="search_GTE_createTime" name="search_GTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					至<input size="15" class="text" id="search_LTE_createTime" name="search_LTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					修改时间: <input size="15" class="text" id="search_GTE_updateTime" name="search_GTE_updateTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					至<input size="15" class="text" id="search_LTE_updateTime" name="search_LTE_updateTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					hot_guide: <input type="text" class="text" size="15" name="search_EQ_hotGuide"/>
					star_level: <input type="text" class="text" size="15" name="search_EQ_starLevel"/>
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_tourGuide_searchform','manage_tourGuide_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_tourGuide_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/customer/tourGuide/listJson.html" toolbar="#manage_tourGuide_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sum="true">ID</th>
			<th field="userId">用户ID</th>
			<th field="guideCoverImg">导游封面图</th>
			<th field="guideNo">导游证号</th>
			<th field="guideCertificateNo">导游资格证号</th>
			<th field="tourTime" >带团时间</th>
			<th field="tourRank" data-options="formatter:function(value){ return formatRefrence('manage_tourGuide_datagrid','allTourRanks',value);} ">导游等级</th>
			<th field="permanentCity">常驻城市</th>
			<th field="province">所在省市</th>
			<th field="goodRoute">擅长路线</th>
			<th field="introduceMyself">自我介绍</th>
			<th field="speciality">导游专长</th>
			<th field="pricePerDay" sum="true">价格</th>
		    <th field="createTime" formatter="formatDate">创建时间</th>
		    <th field="updateTime" formatter="formatDate">修改时间</th>
			<th field="language">language</th>
			<th field="hotGuide" >hot_guide</th>
			<th field="starLevel" >star_level</th>
			<th field="restDays">rest_days</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_tourGuide_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_tourGuide_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/customer/tourGuide/edit.html',id:'{0}',entity:'tourGuide',width:500,height:400});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/customer/tourGuide/show.html?id={0}',500,400);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/customer/tourGuide/deleteJson.html','{0}','manage_tourGuide_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_tourGuide_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/customer/tourGuide/create.html',entity:'tourGuide',width:500,height:400})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/customer/tourGuide/deleteJson.html','manage_tourGuide_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_tourGuide_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_tourGuide_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/customer/tourGuide/exportXls.html','manage_tourGuide_searchform','导游信息表')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/customer/tourGuide/exportCsv.html','manage_tourGuide_searchform','导游信息表')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/customer/tourGuide/importView.html',uploader:'manage_tourGuide_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>

</div>
