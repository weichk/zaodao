<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_travelVoice_editform" action="${pageContext.request.contextPath}/manage/customer/travelVoice/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="travelVoice" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">发布用户ID：</th>
				<td><input type="text" name="userId" size="48" placeholder="请输入发布用户ID..." class="easyui-validatebox text" data-options="validType:['length[1,64]']"/></td>
			</tr>					
			<tr>
				<th>标题：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入标题..." style="width:300px;" name="title" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>文章内容：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入文章内容..." style="width:300px;" name="content" class="easyui-validatebox" data-options="required:true"></textarea></td>
			</tr>					
			<tr>
				<th>收藏数：</th>
				<td><input type="text" name="praiseCount" size="48" placeholder="请输入收藏数..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]']"/></td>
			</tr>					
			<tr>
				<th>评论数：</th>
				<td><input type="text" name="reviewCount" size="48" placeholder="请输入评论数..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]']"/></td>
			</tr>					
			<tr>
				<th>备注：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入备注..." style="width:300px;" name="remark" class="easyui-validatebox" data-options="validType:['length[1,1000]']"></textarea></td>
			</tr>					
			<tr>
				<th>发布位置名称：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入发布位置名称..." style="width:300px;" name="positionName" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>发布位置纬度：</th>
				<td><input type="text" name="positionLat" size="48" placeholder="请输入发布位置纬度..." class="easyui-validatebox text" data-options="validType:['length[1,64]']"/></td>
			</tr>					
			<tr>
				<th>发布位置经度：</th>
				<td><input type="text" name="positionLng" size="48" placeholder="请输入发布位置经度..." class="easyui-validatebox text" data-options="validType:['length[1,64]']"/></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
