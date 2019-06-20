<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_shopOrderInfo_editform" action="${pageContext.request.contextPath}/manage/credit/shopOrderInfo/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="shopOrderInfo" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">订单号:</th>
				<td>${shopOrderInfo.orderNo}</td>
			</tr>					
			<tr>
				<th>商品名称:</th>
				<td>${shopOrderInfo.goodsName}</td>
			</tr>
			<tr>
				<th>商品数量:</th>
				<td>${shopOrderInfo.quantity}</td>
			</tr>							
			<tr>
				<th>订单积分:</th>
				<td>${shopOrderInfo.amount}</td>
			</tr>					
			<tr>
				<th>供货商:</th>
				<td>${shopOrderInfo.supplier}</td>
			</tr>					
			<tr>
				<th>用户名:</th>
				<td>${shopOrderInfo.userName}</td>
			</tr>					
			<tr>
				<th>姓名:</th>
				<td>${shopOrderInfo.realName}</td>
			</tr>
			<tr>
				<th>手机号码:</th>
				<td>${shopOrderInfo.mobileNo}</td>
			</tr>							
			<tr>
				<th>送货地址:</th>
				<td>${shopOrderInfo.provName}-${shopOrderInfo.cityName}- ${shopOrderInfo.distName} ${shopOrderInfo.address}</td>
			</tr>					
			<tr>
				<th>下订时间:</th>
				<td><fmt:formatDate value="${shopOrderInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>					
			<tr>
				<th>最后修改时间:</th>
				<td><fmt:formatDate value="${shopOrderInfo.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<th>备注:</th>
				<td>${shopOrderInfo.comments}</td>
			</tr>							
			<tr>
				<th>状态：</th>
				<td><select name="status" editable="false" panelHeight="auto" class="easyui-combobox" >
					<c:forEach items="${allStatuss}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
