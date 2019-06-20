<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>ID:</th>
		<td>${customer.id}</td>
	</tr>
	<tr>
		<th>用户头像:</th>
		<td><img src="${customer.headImg}" width="32" height="32" title=""></td>
	</tr>
	<tr>
		<th width="25%">会员唯一标识:</th>
		<td>${customer.userId}</td>
	</tr>					
	<tr>
		<th>电话号码:</th>
		<td>${customer.mobileNo}</td>
	</tr>					
	<tr>
		<th>账户名:</th>
		<td>${customer.userName}</td>
	</tr>					
	<tr>
		<th>真实姓名:</th>
		<td>${customer.realName}</td>
	</tr>					
	<tr>
		<th>身份证号:</th>
		<td>${customer.idNumber}</td>
	</tr>					
	<tr>
		<th>是否实名:</th>
		<td>${allIsCertifications[customer.isCertification]}</td>
	</tr>
	<tr>
		<th>是否为导游:</th>
		<td>${allIsTourGuides[customer.isTourGuide]}</td>
	</tr>
	<tr>
		<th>是否为版主:</th>
		<td>${allIsModerator[customer.isModerator]}</td>
	</tr>
	<tr>
		<th>版主权限:</th>
		<td>${allModeratorPermissions[customer.moderatorPermission]}</td>
	</tr>
	<tr>
		<th>是否禁言:</th>
		<td>${allIsShutup[customer.isShutup]}</td>
	</tr>
	<tr>
		<th>电子邮件:</th>
		<td>${customer.email}</td>
	</tr>					
	<tr>
		<th>性别:</th>
		<td>${allSexs[customer.sex]}</td>
	</tr>					
	<tr>
		<th>年龄:</th>
		<td>${customer.age}</td>
	</tr>					
	<tr>
		<th>联系地址:</th>
		<td>${customer.contactAddress}</td>
	</tr>
	<tr>
		<th>勋章:</th>
		<td>${customer.medalName}</td>
	</tr>
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${customer.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>修改时间:</th>
		<td><fmt:formatDate value="${customer.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
</table>
</div>
