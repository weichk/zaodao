<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
	<form id="manage_shopGoods_editform" action="${pageContext.request.contextPath}/manage/credit/shopGoods/${action=='create'?'saveJson':'updateJson'}.html" method="post" enctype="multipart/form-data">
		<jodd:form bean="shopGoods" scope="request">
			<input name="id" type="hidden" />
			<table class="tableForm" width="100%">
				<tr>
					<th width="15%">分类：</th>
					<td>${typeName}<input type="hidden" name="shopGoodsType.id" value="${typeId}" /></td>
					<td colspan="2">商品LOGO：</td>
				</tr>
				<tr>
					<th width="15%">名称：</th>
					<td><input type="text" name="name" class="easyui-validatebox" data-options="required:true" validType="byteLength[1,64]" /></td>
					<td rowspan="5" valign="bottom" colspan="2"><c:if test="${shopGoods.logo != null}">
							<img src="${shopGoods.logo}" width="320" height="160">
						</c:if> <input type="file" name="logoFile"></td>
				</tr>
				<tr>
					<th>品牌：</th>
					<td><input type="text" name="brand" class="easyui-validatebox" validType="byteLength[1,32]" /></td>
				</tr>
				<tr>
					<th>供应商：</th>
					<td><select name="shopSupplier.id" editable="false" panelHeight="auto" class="easyui-combobox"><c:forEach items="${allSuppliers}" var="e">
								<option value="${e.id}">${e.name}</option>
							</c:forEach></select></td>
				</tr>
				<tr>
					<th>商品类型：</th>
					<td><select name="deliveryType" editable="false" panelHeight="auto" class="easyui-combobox" data-options="required:true">
							<c:forEach items="${allDeliveryTypes}" var="e">
								<option value="${e.key}">${e.value}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>简介：</th>
					<td><textarea rows="2" cols="40" name="subject" class="easyui-validatebox" validType="byteLength[1,128]"></textarea></td>
				</tr>
				<tr>
					<th>积分定价：</th>
					<td><input type="text" name="credits" class="easyui-numberbox" data-options="required:true" validType="byteLength[1,19]" /></td>
					<th>市场定价：</th>
					<td><input type="text" name="marketCredits" class="easyui-numberbox" data-options="required:true" validType="byteLength[1,19]" />元
						</td>
				</tr>
				<tr>
					<th>单人限购数量：</th>
					<td><input type="text" name="maxBuyNum" class="easyui-numberbox" data-options="required:true" validType="byteLength[1,19]" /></td>
					<th>商品单位：</th>
					<td><input type="text" name="unit" class="easyui-validatebox" validType="byteLength[1,16]" /> 如：个，只，套</td>

				</tr>
				<tr>
					<th>上架数量：</th>
					<td><input type="text" name="totalQuantity" class="easyui-numberbox" data-options="required:true" validType="byteLength[1,10]" /></td>
					<th>状态：</th>
					<td><select name="status" editable="false" panelHeight="auto" class="easyui-combobox" data-options="required:true">
							<c:forEach items="${allStatuss}" var="e">
								<option value="${e.key}">${e.value}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>有效期：</th>
					<td colspan="3">
					<input size="15" id="goodsValidityDate" data-options="required:true" name="validityDate" value="<fmt:formatDate value="${shopGoods.validityDate}" pattern="yyyy-MM-dd"/>" size="15" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					 用于代金券有效期定义。 </td>
				</tr>				
				<tr>
					<th>备注：</th>
					<td colspan="3"><textarea rows="4" cols="80" name="comments" class="easyui-validatebox" validType="byteLength[1,128]"></textarea></td>
				</tr>
				<tr>
					<th>推荐热门：</th>
					<td><select name="isHot" editable="false" panelHeight="auto" class="easyui-combobox" data-options="required:true">
						<c:forEach items="${hotType}" var="e">
							<option value="${e.key}">${e.value}</option>
						</c:forEach>
					</select></td>
				</tr>
			</table>
		</jodd:form>
	</form>
</div>
