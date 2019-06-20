<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<c:if test="${initParam['ssoEnable']=='true'}">
    <%@ include file="/WEB-INF/jsp/manage/common/ssoInclude.jsp" %>
</c:if>
<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_accountChange_searchform','manage_accountChange_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_accountChange_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
				会员名: <input type="text" class="text" size="15" name="search_LIKE_userName"/>
				会员ID: <input type="text" class="text" size="15" name="search_LIKE_userId"/>
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_accountChange_searchform','manage_accountChange_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_accountChange_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/account/accountChange/listJson.html" toolbar="#manage_accountChange_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sum="true">id</th>
			<th field="userId">会员ID</th>
			<th field="userName">会员名称</th>
			<th field="accountNo">账户号</th>
			<th field="accountType" data-options="formatter:function(value){ return formatRefrence('manage_accountChange_datagrid','allAccountTypes',value);} ">账户类型</th>
			<th field="amount" sum="true" data-options="formatter:function(value){return '￥'+formatCurrency(value);}">交易金额</th>
			<th field="balance" sum="true" data-options="formatter:function(value){return '￥'+formatCurrency(value);}">交易后余额</th>
			<th field="businessId">订单编号</th>
			<th field="orderNo">流水号</th>
			<th field="changeType" data-options="formatter:function(value){ return formatRefrence('manage_accountChange_datagrid','allChangeTypes',value);} ">变更类型</th>
			<%--<th field="transferType">操作类型</th>--%>
			<th field="refUserId">交易会员ID</th>
			<th field="refUserName">交易用户名</th>
			<th field="status" data-options="formatter:function(value){ return formatRefrence('manage_accountChange_datagrid','allAccountOrderStatus',value);} ">状态</th>
			<th field="comments">备注</th>
			<th field="createTime" formatter="dateTimeFormatter">创建时间</th>
			<th field="updateTime" formatter="dateTimeFormatter">修改时间</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_accountChange_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_accountChange_action" style="display: none;">
      <%--<a onclick="$.acooly.framework.edit({url:'/manage/account/accountChange/edit.html',id:'{0}',entity:'accountChange',width:500,height:400});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>--%>
      <a onclick="$.acooly.framework.show('/manage/account/accountChange/show.html?id={0}',500,400);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
     <%-- <a onclick="$.acooly.framework.remove('/manage/account/accountChange/deleteJson.html','{0}','manage_accountChange_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>--%>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_accountChange_toolbar">
        <a href="#" class="easyui-menubutton" data-options="menu:'#manage_accountChange_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
        <div id="manage_accountChange_exports_menu" style="width:150px;">
            <div onclick="$.acooly.framework.exports('/manage/account/accountChange/exportXls.html','manage_accountChange_searchform','act_account_change')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
            <div onclick="$.acooly.framework.exports('/manage/account/accountChange/exportCsv.html','manage_accountChange_searchform','act_account_change')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
        </div>
      <%--<a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/account/accountChange/create.html',entity:'accountChange',width:500,height:400})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/account/accountChange/deleteJson.html','manage_accountChange_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/account/accountChange/importView.html',uploader:'manage_accountChange_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>--%>
    </div>
  </div>

</div>
