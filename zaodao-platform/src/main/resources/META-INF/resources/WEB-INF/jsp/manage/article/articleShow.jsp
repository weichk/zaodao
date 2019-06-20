<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>id:</th>
		<td>${article.id}</td>
	</tr>					
	<tr>
		<th width="25%">发表人ID:</th>
		<td>${article.userId}</td>
	</tr>					
	<tr>
		<th>发表人手机号码:</th>
		<td>${article.mobileNo}</td>
	</tr>					
	<tr>
		<th>文章标题:</th>
		<td>${article.title}</td>
	</tr>					
	<tr>
		<th>文章内容:</th>
		<td>${article.content}</td>
	</tr>					
	<%--<tr>--%>
		<%--<th>文章封面:</th>--%>
		<%--<td>${article.cover}</td>--%>
	<%--</tr>					--%>
	<tr>
		<th>地区:</th>
		<td>${article.area}</td>
	</tr>					
	<tr>
		<th>景区:</th>
		<td>${article.scenic}</td>
	</tr>					
	<tr>
		<th>标签:</th>
		<td>${article.label}</td>
	</tr>					
	<tr>
		<th>阅览数:</th>
		<td>${article.readCount}</td>
	</tr>					
	<tr>
		<th>点赞数:</th>
		<td>${article.praiseCount}</td>
	</tr>					
	<tr>
		<th>帖子类型:</th>
		<td>${article.articleType.message}</td>
	</tr>
	<tr>
		<th>帖子状态:</th>
		<td>${allArticleStatuss[article.articleStatus]}</td>
	</tr>					
	<tr>
		<th>是否加精:</th>
		<td>${allEssenceStatuss[article.essenceStatus]}</td>
	</tr>					
	<tr>
		<th>是否置顶:</th>
		<td>${allUpStatuss[article.upStatus]}</td>
	</tr>
	<tr>
		<th>帖子展示类型:</th>
		<td>${article.articleHotType.message}</td>
	</tr>
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
	<tr>
		<th>修改时间:</th>
		<td><fmt:formatDate value="${article.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
</table>
</div>
