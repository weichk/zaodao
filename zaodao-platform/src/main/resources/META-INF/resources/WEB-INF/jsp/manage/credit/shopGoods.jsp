<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>


<script type="text/javascript">
	function manage_shopGoodsType_loadTree(defaultNode, clear) {
		$.ajax({
			url : '/manage/credit/shopGoodsType/loadTree.html',
			success : function(data, status) {
				if (typeof (data) == 'string') {
					data = eval('(' + data + ')');
				}
				if (!data.success) {
					return;
				}
				$.fn.zTree.init($("#manage_shopGoodsType_tree"), {
					view : {
						showLine : true,
						showIcon : true,
						showTitle : true,
						addHoverDom : manage_shopGoodsType_tree_addHoverDom,
						removeHoverDom : manage_shopGoodsType_tree_removeHoverDom,
					},
					edit : {
						enable : true,
						showRemoveBtn : false,
						showRenameBtn : false
					},
					callback : {
						onClick : function(event, treeId, treeNode, clickFlag) {
							manage_shopGoods_search(treeNode)
						},
						onDrop : function(event, treeId, treeNodes, targetNode, moveType) {
							manage_shopGoodsType_form_moveup(treeNodes[0], targetNode, moveType);
						}
					}
				}, data.rows);
				var zTree = $.fn.zTree.getZTreeObj("manage_shopGoodsType_tree");
				zTree.expandAll(true);
				if (defaultNode)
					zTree.selectNode(defaultNode, true);
				if (clear)
					manage_shopGoodsType_form_add();
			}
		});
	}

	function manage_shopGoods_search(shopGoodsType) {
		$('#manage_shopGoods_typeCode').val(shopGoodsType.code);
		$('#manage_shopGoods_typeId').val(shopGoodsType.id);
		$.acooly.framework.search('manage_shopGoods_searchform', 'manage_shopGoods_datagrid');
	}

	/**
	 * 鼠标移动到节点动态添加添加和删除链接。
	 */
	function manage_shopGoodsType_tree_addHoverDom(treeId, treeNode) {
		var aObj = $("#" + treeNode.tId + "_a");
		// 添加子
		if ($("#manage_shopGoodsType_add_Btn_" + treeNode.id).length == 0) {
			var html = "<a id='manage_shopGoodsType_add_Btn_" + treeNode.id + "' href='javascript:;' onclick='manage_shopGoodsType_tree_add(" + treeNode.id + ");return false;' style='margin:0 0 0 5px;'>添加</a>"
			aObj.append(html)
		}
		// 编辑
		if ($("#manage_shopGoodsType_edit_Btn_" + treeNode.id).length == 0) {
			var html = "<a id='manage_shopGoodsType_edit_Btn_" + treeNode.id + "' href='javascript:;' onclick='manage_shopGoodsType_tree_edit(" + treeNode.id + ");return false;' style='margin:0 0 0 5px;'>修改</a>"
			aObj.append(html)
		}
		// 删除
		if (treeNode.children && treeNode.children.length == 0) {
			if ($("#manage_shopGoodsType_delete_Btn_" + treeNode.id).length > 0)
				return;
			var html = "<a id='manage_shopGoodsType_delete_Btn_" + treeNode.id + "' href='javascript:;' onclick='manage_shopGoodsType_tree_delete(" + treeNode.id + ");return false;' style='margin:0 0 0 5px;'>删除</a>"
			aObj.append(html)
		}
	}
	/**
	 * 鼠标移出节点处理
	 */
	function manage_shopGoodsType_tree_removeHoverDom(treeId, treeNode) {
		if ($("#manage_shopGoodsType_add_Btn_" + treeNode.id).length > 0)
			$("#manage_shopGoodsType_add_Btn_" + treeNode.id).unbind().remove();
		if ($("#manage_shopGoodsType_edit_Btn_" + treeNode.id).length > 0)
			$("#manage_shopGoodsType_edit_Btn_" + treeNode.id).unbind().remove();
		if (treeNode.children.length == 0)
			$("#manage_shopGoodsType_delete_Btn_" + treeNode.id).unbind().remove();
	}

	// 添加子节点
	function manage_shopGoodsType_tree_add(parentId) {
		var url = '/manage/credit/shopGoodsType/create.html';
		if (parentId) {
			url += '?parentId=' + parentId;
		}
		$.acooly.framework.create({
			url : url,
			entity : 'shopGoodsType',
			width : 500,
			height : 200,
			onSuccess : function(result) {
				var zTree = $.fn.zTree.getZTreeObj("manage_shopGoodsType_tree");
				if (parentId) {
					var parentNode = zTree.getNodeByParam("id", parentId, null);
					zTree.addNodes(parentNode, result.entity, false);
				} else {
					zTree.addNodes(null, result.entity, false);
				}
			}
		})
	}

	// 修改节点
	function manage_shopGoodsType_tree_edit(id) {
		$.acooly.framework.edit({
			url : '/manage/credit/shopGoodsType/edit.html',
			id : id,
			entity : 'shopGoodsType',
			width : 500,
			height : 200,
			reload : false,
			onSuccess : function(d, result) {
				var zTree = $.fn.zTree.getZTreeObj("manage_shopGoodsType_tree");
				var node = zTree.getNodeByParam("id", id, null);
				node.name = result.entity.name;
				zTree.updateNode(node, true);
			}
		})
	}

	//删除子节点
	function manage_shopGoodsType_tree_delete(id) {
		$.messager.confirm("确认", "你确认删除该类型？	", function(r) {
			if (!r)
				return;
			$.ajax({
				url : '/manage/credit/shopGoodsType/deleteJson.html?id=' + id,
				success : function(data, status) {
					if (data.success) {
						var zTree = $.fn.zTree.getZTreeObj("manage_shopGoodsType_tree");
						var node = zTree.getNodeByParam("id", id, null);
						zTree.removeNode(node);
					}
					if (data.message)
						$.messager.show({
							title : '提示',
							msg : data.message
						});
				}
			});
		})
	}

	//移动
	function manage_shopGoodsType_form_moveup(sourceNode, targetNode, moveType) {
		if (!moveType || moveType == null)
			return;
		console.info("sourceNode:" + sourceNode.name + ", targetNode:" + targetNode.name + ",moveType:" + moveType);
		$.ajax({
			url : '/manage/credit/shopGoodsType/move.html',
			data : {
				sourceId : sourceNode.id,
				targetId : targetNode.id,
				moveType : moveType
			},
			success : function(data, status) {
				if (data.success) {
					console.info("save move success!");
				}
			}
		});
	}

	//展开/收缩
	var manage_shopGoodsType_tree_expand_state = true;
	function manage_resource_tree_toggle() {
		var zTree = $.fn.zTree.getZTreeObj("manage_shopGoodsType_tree");
		zTree.expandAll(!manage_shopGoodsType_tree_expand_state);
		manage_shopGoodsType_tree_expand_state = !manage_shopGoodsType_tree_expand_state;
	}

	function manage_shopGoods_create() {
		var typeId = $('#manage_shopGoods_typeId').val();
		if (!typeId) {
			alert("请先选择分类");
			return;
		}
		$.acooly.framework.create({
			url : '/manage/credit/shopGoods/create.html?typeId=' + typeId,
			entity : 'shopGoods',
			width : 800,
			height : 600,
			maximizable : true
		});
	}

	$(function() {
		manage_shopGoodsType_loadTree();
		$.acooly.framework.registerKeydown('manage_shopGoods_searchform', 'manage_shopGoods_datagrid');
	});
</script>
<div class="easyui-layout" data-options="fit : true,border : false">

	<!-- 菜单树 -->
	<div data-options="region:'west',border:true,split:true" style="width: 200px;" align="left">
		<div class="easyui-panel" style="padding: 5px;">
			<a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="manage_resource_tree_toggle()">[+/-]</a> <a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="manage_shopGoodsType_tree_add()">添加根</a>
		</div>
		<div id="manage_shopGoodsType_tree" class="ztree"></div>
	</div>
	<!-- 列表和工具栏 -->
	<div data-options="region:'center',border:false">
		<form id="manage_shopGoods_searchform" onsubmit="return false">
			<table class="tableForm" width="100%">
				<tr>
					<td align="left">编码:<input type="text" size="15" name="search_LIKE_code" value="${param.search_LIKE_code}" /> 名称:<input type="text" size="15" name="search_LIKE_name" value="${param.search_LIKE_name}" /> 供应商:<select name="search_EQ_shopSupplier.id" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option>
							<c:forEach items="${allSuppliers}" var="e">
								<option value="${e.id}">${e.name}</option>
							</c:forEach></select> 状态:<select style="width: 80px;" name="search_EQ_status" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option>
							<c:forEach var="e" items="${allStatuss}">
								<option value="${e.key}" ${param.search_EQ_status == e.key?'selected':''}>${e.value}</option>
							</c:forEach></select> <input id="manage_shopGoods_typeCode" name="search_RLIKE_shopGoodsType.code" type="hidden"> <input id="manage_shopGoods_typeId" name="goodsTypeId" type="hidden"> <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:false" onclick="$.acooly.framework.search('manage_shopGoods_searchform','manage_shopGoods_datagrid');">查询</a>
					</td>
				</tr>
			</table>
		</form>
		<table id="manage_shopGoods_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/credit/shopGoods/listJson.html" toolbar="#manage_shopGoods_toolbar" fit="true" border="false" fitColumns="false" pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
					<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
					<th field="id">id</th>
					<th field="code">编码</th>
					<th field="name" data-options="formatter:function(value){return formatContent(value,15);}">名称</th>
					<th field="brand">品牌</th>
					<th field="shopSupplier" data-options="formatter:function(value){ return value.name;}">供应商</th>
					<th field="deliveryType" data-options="formatter:function(value){ return formatRefrence('manage_shopGoods_datagrid','allDeliveryTypes',value);} ">商品类型</th>
					<th field="credits">积分定价</th>
					<%--<th field="sellAmount">销售价格(元)</th>--%>
					<th field="unit">单位</th>
					<th field="totalQuantity">上架数量</th>
					<th field="sellQuantity">销售数量</th>
					<th field="status" data-options="formatter:function(value){ return formatRefrence('manage_shopGoods_datagrid','allStatuss',value);} ">状态</th>
					<th field="createTime" formatter="formatDate">修改时间</th>
					<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_shopGoods_action',value,row)}">动作</th>
				</tr>
			</thead>
		</table>

		<!-- 每行的Action动作模板 -->
		<div id="manage_shopGoods_action" style="display: none;">
			<a class="line-action icon-edit" onclick="$.acooly.framework.edit({url:'/manage/credit/shopGoods/edit.html',id:'{0}',entity:'shopGoods',width:800,height:500,maximizable:false});" href="#" title="编辑"></a> <a class="line-action icon-edit" onclick="$.acooly.framework.edit({url:'/manage/credit/shopGoodsDetail/modify.html?goodsId={0}',id:'{0}',entity:'shopGoodsDetail',width:800,height:550,maximizable:false,title:'商品介绍',editButton:'保存'});" href="#" title="商品介绍"></a> <a
				class="line-action icon-delete" onclick="$.acooly.framework.remove('/manage/credit/shopGoods/deleteJson.html','{0}','manage_shopGoods_datagrid');" href="#" title="删除"></a>
		</div>

		<!-- 表格的工具栏 -->
		<div id="manage_shopGoods_toolbar">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="manage_shopGoods_create()">添加</a> <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="$.acooly.framework.removes('/manage/credit/shopGoods/deleteJson.html','manage_shopGoods_datagrid')">批量删除</a>
		</div>
	</div>

</div>
