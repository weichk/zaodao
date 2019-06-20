<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<c:if test="${initParam['ssoEnable']=='true'}">
    <%@ include file="/WEB-INF/jsp/manage/common/ssoInclude.jsp" %>
</c:if>
<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_orderServiceFee_searchform','manage_orderServiceFee_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_orderServiceFee_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
                收费项目:
                <select style="width:80px;height:27px;" name="feeName" editable="false" panelHeight="100" class="easyui-combobox">
                    <option value="">所有</option>
                    <c:forEach var="e" items="${allServiceFeeNameTypes}">
                        <option value="${e.key}">${e.value}</option>
                    </c:forEach>
                </select>
                状态:
                <select style="width:80px;height:27px;" name="feeStatus" editable="false" panelHeight="100" class="easyui-combobox">
                    <option value="">所有</option>
                    <c:forEach var="e" items="${allServiceFeeStatuss}">
                        <option value="${e.key}">${e.value}</option>
                    </c:forEach>
                </select>
                <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_orderServiceFee_searchform','manage_orderServiceFee_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_orderServiceFee_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/orderServiceFee/orderServiceFee/getServiceFeeList.html" toolbar="#manage_orderServiceFee_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sum="true">id</th>
			<th field="feeName" formatter="mappingFormatter">收费项目</th>
            <th field="conditionTxt">收费条件</th>
			<th field="feeScale" formatter="mappingFormatter">收费标准</th>
			<th field="feeAmount" data-options="formatter:formatFeeAmount">收费额度</th>
            <th field="feeSort">排序</th>
			<th field="feeStatus" formatter="mappingFormatter">状态</th>
		    <th field="createTime" formatter="dateTimeFormatter">创建时间</th>
		    <th field="updateTime" formatter="dateTimeFormatter">更新时间</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_orderServiceFee_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_orderServiceFee_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/orderServiceFee/orderServiceFee/editOrderServiceFee.html',id:'{0}',entity:'orderServiceFee',width:500,height:400, onSuccess:function(){
              $.acooly.framework.search('manage_orderServiceFee_searchform','manage_orderServiceFee_datagrid');
          }});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_orderServiceFee_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/orderServiceFee/orderServiceFee/createOrderServiceFee.html',entity:'orderServiceFee',width:550,height:400, onSuccess:function(){
              $.acooly.framework.search('manage_orderServiceFee_searchform','manage_orderServiceFee_datagrid');
          }})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
    </div>
  </div>
</div>

<script type="text/javascript">


    function formatFeeAmount(value, row, index) {
        if(row.feeScale == 'percent_fee'){
            return (value / 100).toFixed(2) + " %";
        }else if(row.feeScale == 'fixed_fee'){
            return (value / 100).toFixed(2) + " 元";
        }
        return value;
    }
</script>
