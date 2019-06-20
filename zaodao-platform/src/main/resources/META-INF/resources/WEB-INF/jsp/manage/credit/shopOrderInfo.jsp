<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>


<script type="text/javascript">
	$(function() {
		$.acooly.framework.registerKeydown('manage_shopOrderInfo_searchform', 'manage_shopOrderInfo_datagrid');
	});

	function getShopOrderInfoRow() {
		var row = $.acooly.framework.getSelectedRow("manage_shopOrderInfo_datagrid");
		if (!row) {
			$.messager.alert('提示', "请选择操作的行");
			return false;
		}
		return row;
	}

	function manage_shopOrderInfo_deliver() {
		var row = getShopOrderInfoRow();
		if (!row)
			return;
		if (row.status >= 15) {
			$.messager.alert('提示', "订单已发货或已收货。");
			return;
		}
		$.acooly.framework.confirmSubmit('/manage/credit/shopOrderInfo/deliver.html', row.id, 'manage_shopOrderInfo_datagrid');
	}

	function manage_shopOrderInfo_receive() {
		var row = getShopOrderInfoRow();
		if (!row) return;
		if (row.status == 20) {
			$.messager.alert('提示', "订单已确定收货。");
			return;
		}
		if (row.status != 15) {
			$.messager.alert('提示', "订单需要先发货后才能确定收货。");
			return;
		}

		$.acooly.framework.confirmSubmit('/manage/credit/shopOrderInfo/receive.html', row.id, 'manage_shopOrderInfo_datagrid');
	}
	function manage_shopOrderInfo_show() {
		var row = getShopOrderInfoRow();
		if (!row)
			return;
		$.acooly.framework.show('/manage/credit/shopOrderInfo/show.html?id=' + row.id, 500, 480);
	}
</script>
<div class="easyui-layout" data-options="fit : true,border : false">
	<!-- 查询条件 -->
	<div data-options="region:'north',border:false" style="padding: 5px; overflow: hidden;" align="left">
		<form id="manage_shopOrderInfo_searchform" onsubmit="return false">
			<table class="tableForm" width="100%">
				<tr>
					<td align="left">订单号:<input type="text" size="15" name="search_LIKE_orderNo" value="${param.search_LIKE_orderNo}" /> 商品名称:<input type="text" size="15" name="search_LIKE_goodsName" value="${param.search_LIKE_goodsName}" /> 下订时间:<input size="15" id="search_GTE_createTime" name="search_GTE_createTime" size="15" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 至 <input size="15" id="search_LTE_createTime" name="search_LTE_createTime" size="15"
						onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 状态:<select style="width: 80px;" name="search_EQ_status" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option>
							<c:forEach var="e" items="${allStatuss}">
								<option value="${e.key}" ${param.search_EQ_status == e.key?'selected':''}>${e.value}</option>
							</c:forEach></select> <c:if test="${userType != 20}">
					供货商:<select name="search_EQ_supplierId" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option>
								<c:forEach items="${allSuppliers}" var="e">
									<option value="${e.id}">${e.name}</option>
								</c:forEach></select>
						</c:if> <br> 用户名:<input type="text" size="15" name="search_EQ_userName" value="${param.search_EQ_userName}" /> 客户姓名:<input type="text" size="15" name="search_LIKE_realName" value="${param.search_LIKE_realName}" /> 手机号码:<input type="text" size="15" name="search_LIKE_mobileNo" value="${param.search_LIKE_mobileNo}" /> <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:false"
						onclick="$.acooly.framework.search('manage_shopOrderInfo_searchform','manage_shopOrderInfo_datagrid');">查询</a>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<!-- 列表和工具栏 -->
	<div data-options="region:'center',border:false">
		<table id="manage_shopOrderInfo_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/credit/shopOrderInfo/listJson.html" toolbar="#manage_shopOrderInfo_toolbar" fit="true" border="false" fitColumns="false" pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
			<thead>
				<tr>
					<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
					<th field="id">id</th>
					<th field="orderNo">订单号</th>
					<th field="deliveryType" data-options="formatter:function(value){ return formatRefrence('manage_shopOrderInfo_datagrid','allDeliveryTypes',value);}">类型</th>
					<th field="goodsName">商品名称</th>
					<th field="quantity">商品数量</th>
					<th field="amount">订单积分</th>
					<th field="sellAmount">使用余额(元)</th>
					<th field="supplier">供货商</th>
					<th field="userName">用户名</th>
					<th field="realName">姓名</th>
					<th field="mobileNo">手机号码</th>
					<th field="address" data-options="formatter:function(value,row){ return row.provName + row.cityName + row.distName + value}">送货地址</th>
					<th field="createTime" formatter="formatDate">下订时间</th>
					<th field="updateTime" formatter="formatDate">交易时间</th>
					<th field="status" data-options="formatter:function(value){ return formatRefrence('manage_shopOrderInfo_datagrid','allStatuss',value);} ">状态</th>
				</tr>
			</thead>
		</table>

		<!-- 每行的Action动作模板 
    <div id="manage_shopOrderInfo_action" style="display: none;">
      <a class="line-action icon-edit" onclick="$.acooly.framework.edit({url:'/manage/credit/shopOrderInfo/edit.html',id:'{0}',entity:'shopOrderInfo',width:500,height:400});" href="#" title="编辑"></a>&nbsp
      <a class="line-action icon-show" onclick="$.acooly.framework.show('/manage/credit/shopOrderInfo/show.html?id={0}',500,480);" href="#" title="查看"></a>
    </div>
    -->
		<div id="manage_shopOrderInfo_toolbar">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="manage_shopOrderInfo_deliver()">标记已发货</a> <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="manage_shopOrderInfo_receive()">标记已收货</a> <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="manage_shopOrderInfo_show()">查看订单</a>
			<!-- 
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_shopOrderInfo_exports_menu',iconCls:'icon-export'">批量导出</a>
      <div id="manage_shopOrderInfo_exports_menu" style="width:150px;">
        <div data-options="iconCls:'icon-excel'" onclick="$.acooly.framework.exports('/manage/credit/shopOrderInfo/exportXls.html','manage_shopOrderInfo_searchform','商城订单信息')">Excel</div>  
        <div data-options="iconCls:'icon-csv'" onclick="$.acooly.framework.exports('/manage/credit/shopOrderInfo/exportCsv.html','manage_shopOrderInfo_searchform','商城订单信息')">CSV</div> 
      </div>
       -->
		</div>
	</div>

</div>
