<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>id:</th>
		<td>${orderEvalTag.id}</td>
	</tr>					
	<tr>
		<th width="25%">创建时间:</th>
		<td><fmt:formatDate value="${orderEvalTag.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>修改时间:</th>
		<td><fmt:formatDate value="${orderEvalTag.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>标签内容:</th>
		<td>${orderEvalTag.tagContent}</td>
	</tr>					
</table>
</div>
