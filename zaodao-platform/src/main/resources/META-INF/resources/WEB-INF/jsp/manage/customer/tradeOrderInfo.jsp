<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<c:if test="${initParam['ssoEnable']=='true'}">
    <%@ include file="/WEB-INF/jsp/manage/common/ssoInclude.jsp" %>
</c:if>
<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_tradeOrderInfo_searchform','manage_tradeOrderInfo_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_tradeOrderInfo_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
					会员ID: <input type="text" class="text" size="15" name="search_LIKE_userId"/>
					银行会员ID: <input type="text" class="text" size="15" name="search_LIKE_outUserId"/>
					订单号: <input type="text" class="text" size="15" name="search_LIKE_orderNo"/>
					银行订单号: <input type="text" class="text" size="15" name="search_LIKE_bankOrderNo"/>
					交易名称: <input type="text" class="text" size="15" name="search_LIKE_tradeName"/>
					交易时间: <input size="15" class="text" id="search_GTE_tradeTime" name="search_GTE_tradeTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					至<input size="15" class="text" id="search_LTE_tradeTime" name="search_LTE_tradeTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					交易完成时间: <input size="15" class="text" id="search_GTE_finishTime" name="search_GTE_finishTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					至<input size="15" class="text" id="search_LTE_finishTime" name="search_LTE_finishTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					订单金额: <input type="text" class="text" size="15" name="search_EQ_orderAmount"/>
				订单交易类型: <select style="width:80px;height:27px;" name="search_EQ_orderTradeType" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allOrderTradeTypes}"><option value="${e.key}" ${param.search_EQ_orderTradeType == e.key?'selected':''}>${e.value}</option></c:forEach></select>
				订单子交易类型: <select style="width:80px;height:27px;" name="search_EQ_orderSubTradeType" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allOrderSubTradeTypes}"><option value="${e.key}" ${param.search_EQ_orderSubTradeType == e.key?'selected':''}>${e.value}</option></c:forEach></select>
				订单状态: <select style="width:80px;height:27px;" name="search_EQ_orderStatus" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allOrderStatuss}"><option value="${e.key}" ${param.search_EQ_orderStatus == e.key?'selected':''}>${e.value}</option></c:forEach></select>
					创建时间: <input size="15" class="text" id="search_GTE_createTime" name="search_GTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					至<input size="15" class="text" id="search_LTE_createTime" name="search_LTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					修改时间: <input size="15" class="text" id="search_GTE_updateTime" name="search_GTE_updateTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					至<input size="15" class="text" id="search_LTE_updateTime" name="search_LTE_updateTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_tradeOrderInfo_searchform','manage_tradeOrderInfo_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_tradeOrderInfo_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/customer/tradeOrderInfo/listJson.html" toolbar="#manage_tradeOrderInfo_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sum="true">id</th>
			<th field="userId">会员ID</th>
			<th field="outUserId">银行会员ID</th>
			<th field="orderNo">订单号</th>
			<th field="bankOrderNo">银行订单号</th>
			<th field="tradeName">交易名称</th>
		    <th field="tradeTime" formatter="formatDate">交易时间</th>
		    <th field="finishTime" formatter="formatDate">交易完成时间</th>
			<th field="orderAmount" sum="true">订单金额</th>
			<th field="orderTradeType" data-options="formatter:function(value){ return formatRefrence('manage_tradeOrderInfo_datagrid','allOrderTradeTypes',value);} ">订单交易类型</th>
			<th field="orderSubTradeType" data-options="formatter:function(value){ return formatRefrence('manage_tradeOrderInfo_datagrid','allOrderSubTradeTypes',value);} ">订单子交易类型</th>
			<th field="orderStatus" data-options="formatter:function(value){ return formatRefrence('manage_tradeOrderInfo_datagrid','allOrderStatuss',value);} ">订单状态</th>
		    <th field="createTime" formatter="formatDate">创建时间</th>
		    <th field="updateTime" formatter="formatDate">修改时间</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_tradeOrderInfo_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_tradeOrderInfo_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/customer/tradeOrderInfo/edit.html',id:'{0}',entity:'tradeOrderInfo',width:500,height:400});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/customer/tradeOrderInfo/show.html?id={0}',500,400);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/customer/tradeOrderInfo/deleteJson.html','{0}','manage_tradeOrderInfo_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_tradeOrderInfo_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/customer/tradeOrderInfo/create.html',entity:'tradeOrderInfo',width:500,height:400})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/customer/tradeOrderInfo/deleteJson.html','manage_tradeOrderInfo_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_tradeOrderInfo_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_tradeOrderInfo_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/customer/tradeOrderInfo/exportXls.html','manage_tradeOrderInfo_searchform','交易订单表')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/customer/tradeOrderInfo/exportCsv.html','manage_tradeOrderInfo_searchform','交易订单表')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/customer/tradeOrderInfo/importView.html',uploader:'manage_tradeOrderInfo_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>

</div>
