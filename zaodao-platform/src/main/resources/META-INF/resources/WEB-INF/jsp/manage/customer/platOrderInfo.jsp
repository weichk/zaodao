<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<c:if test="${initParam['ssoEnable']=='true'}">
    <%@ include file="/WEB-INF/jsp/manage/common/ssoInclude.jsp" %>
</c:if>
<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_platOrderInfo_searchform','manage_platOrderInfo_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_platOrderInfo_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
					游客ID: <input type="text" class="text" size="15" name="search_LIKE_touristId"/>
					导游ID: <input type="text" class="text" size="15" name="search_LIKE_tourGuideId"/>
					订单号: <input type="text" class="text" size="15" name="search_LIKE_orderNo"/>
				订单状态: <select style="width:80px;height:27px;" name="search_EQ_orderStatus" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allOrderStatuss}"><option value="${e.key}" ${param.search_EQ_orderStatus == e.key?'selected':''}>${e.value}</option></c:forEach></select>
					游玩开始时间: <input size="15" class="text" id="search_GTE_startPlayTime" name="search_GTE_startPlayTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					至<input size="15" class="text" id="search_LTE_startPlayTime" name="search_LTE_startPlayTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					游玩结束时间: <input size="15" class="text" id="search_GTE_endPlayTime" name="search_GTE_endPlayTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					至<input size="15" class="text" id="search_LTE_endPlayTime" name="search_LTE_endPlayTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					联系人手机: <input type="text" class="text" size="15" name="search_LIKE_contactPhone"/>
					每日单价（元）: <input type="text" class="text" size="15" name="search_EQ_pricePerDay"/>
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_platOrderInfo_searchform','manage_platOrderInfo_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_platOrderInfo_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/customer/platOrderInfo/listJson.html" toolbar="#manage_platOrderInfo_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sum="true">id</th>
			<th field="touristId">游客ID</th>
			<th field="tourGuideId">导游ID</th>
			<th field="orderNo">订单号</th>
			<th field="dayCount" >游玩天数</th>
			<th field="adultCount" >成人数</th>
			<th field="childCount" >小孩数</th>
			<th field="orderAmount" formatter="centMoneyFormatter" sum="true">订单金额</th>
			<th field="orderStatus" formatter="mappingFormatter">订单状态</th>
		    <th field="startPlayTime">游玩开始时间</th>
		    <th field="endPlayTime">游玩结束时间</th>
			<th field="contactName">联系人姓名</th>
			<th field="contactPhone">联系人手机</th>
			<th field="contactMemo">联系人备注</th>
		    <th field="createTime">创建时间</th>
		    <th field="updateTime">修改时间</th>
			<th field="pricePerDay" formatter="centMoneyFormatter" sum="true">每日单价（元）</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_platOrderInfo_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_platOrderInfo_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/customer/platOrderInfo/edit.html',id:'{0}',entity:'platOrderInfo',width:500,height:400});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/customer/platOrderInfo/show.html?id={0}',500,400);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <%--<a onclick="$.acooly.framework.remove('/manage/customer/platOrderInfo/deleteJson.html','{0}','manage_platOrderInfo_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>--%>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_platOrderInfo_toolbar">
      <%--<a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/customer/platOrderInfo/create.html',entity:'platOrderInfo',width:500,height:400})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>--%>
      <%--<a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/customer/platOrderInfo/deleteJson.html','manage_platOrderInfo_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>--%>
      <%--<a href="#" class="easyui-menubutton" data-options="menu:'#manage_platOrderInfo_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>--%>
      <%--<div id="manage_platOrderInfo_exports_menu" style="width:150px;">--%>
        <%--<div onclick="$.acooly.framework.exports('/manage/customer/platOrderInfo/exportXls.html','manage_platOrderInfo_searchform','订单表')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>--%>
        <%--<div onclick="$.acooly.framework.exports('/manage/customer/platOrderInfo/exportCsv.html','manage_platOrderInfo_searchform','订单表')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>--%>
      <%--</div>--%>
      <%--<a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/customer/platOrderInfo/importView.html',uploader:'manage_platOrderInfo_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>--%>
    </div>
  </div>

</div>
