<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th width="25%">编码:</th>
		<td>${shopGoods.code}</td>
	</tr>					
	<tr>
		<th>名称:</th>
		<td>${shopGoods.name}</td>
	</tr>					
	<tr>
		<th>品牌:</th>
		<td>${shopGoods.brand}</td>
	</tr>					
	<tr>
		<th>简介:</th>
		<td>${shopGoods.subject}</td>
	</tr>					
	<tr>
		<th>商品LOGO:</th>
		<td>${shopGoods.logo}</td>
	</tr>					
	<tr>
		<th>分类:</th>
		<td>${shopGoods.typeId}</td>
	</tr>					
	<tr>
		<th>供应商ID:</th>
		<td>${shopGoods.supplierId}</td>
	</tr>					
	<tr>
		<th>送货方式:</th>
		<td>${allDeliveryTypes[shopGoods.deliveryType]}</td>
	</tr>					
	<tr>
		<th>积分定价:</th>
		<td>${shopGoods.credits}</td>
	</tr>					
	<tr>
		<th>积分折扣价格:</th>
		<td>${shopGoods.discountCredits}</td>
	</tr>					
	<tr>
		<th>上架数量:</th>
		<td>${shopGoods.totalQuantity}</td>
	</tr>					
	<tr>
		<th>销售数量:</th>
		<td>${shopGoods.sellQuantity}</td>
	</tr>					
	<tr>
		<th>商品介绍内容ID:</th>
		<td>${shopGoods.contentId}</td>
	</tr>					
	<tr>
		<th>状态:</th>
		<td>${allStatuss[shopGoods.status]}</td>
	</tr>					
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${shopGoods.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>修改时间:</th>
		<td><fmt:formatDate value="${shopGoods.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>备注:</th>
		<td>${shopGoods.comments}</td>
	</tr>					
</table>
</div>
