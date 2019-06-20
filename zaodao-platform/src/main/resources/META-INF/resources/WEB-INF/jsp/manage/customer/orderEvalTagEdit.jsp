<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_orderEvalTag_editform" action="${pageContext.request.contextPath}/manage/customer/orderEvalTag/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="orderEvalTag" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th>标签内容：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入标签内容..." style="width:300px;" name="tagContent" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
