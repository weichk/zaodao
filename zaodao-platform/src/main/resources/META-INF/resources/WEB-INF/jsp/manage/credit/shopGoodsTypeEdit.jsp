<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_shopGoodsType_editform" action="${pageContext.request.contextPath}/manage/credit/shopGoodsType/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="shopGoodsType" scope="request">
        <input name="id" type="hidden" />
        <input name="parentId" type="hidden" value="${parentId}"/> 
        <table class="tableForm" width="100%">
			<tr>
				<th>名称：</th>
				<td><input type="text" name="name" class="easyui-validatebox"  validType="byteLength[1,32]"/></td>
			</tr>					
			<tr>
				<th>备注：</th>
				<td><input type="text" name="comments" class="easyui-validatebox"  validType="byteLength[1,128]"/></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
