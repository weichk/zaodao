<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>导游封面图:</th>
		<td><img src="${tourGuide.guideCoverImg}" width="32" height="32" title=""></td>
	</tr>
	<tr>
		<th>导游证号:</th>
		<td>${tourGuide.guideNo}</td>
	</tr>					
	<tr>
		<th>导游资格证号:</th>
		<td>${tourGuide.guideCertificateNo}</td>
	</tr>					
	<tr>
		<th>带团时间:</th>
		<td>${tourGuide.tourTime}</td>
	</tr>					
	<tr>
		<th>导游等级:</th>
		<td>${tourGuide.tourRank.message}</td>
	</tr>					
	<tr>
		<th>常驻城市:</th>
		<td>${tourGuide.permanentCity}</td>
	</tr>					
	<tr>
		<th>所在省市:</th>
		<td>${tourGuide.province}</td>
	</tr>					
	<tr>
		<th>擅长路线:</th>
		<td>${tourGuide.goodRoute}</td>
	</tr>					
	<tr>
		<th>自我介绍:</th>
		<td>${tourGuide.introduceMyself}</td>
	</tr>					
	<tr>
		<th>导游专长:</th>
		<td>${tourGuide.speciality}</td>
	</tr>					
	<tr>
		<th>价格:</th>
		<td>${tourGuide.pricePerDay/100}元/天</td>
	</tr>
	<tr>
		<th>带团语言:</th>
		<td>${tourGuide.language}</td>
	</tr>					
	<tr>
		<th>是否人气导游:</th>
		<td>${allIsHotStatus[tourGuide.hotGuide]}</td>
	</tr>					
	<tr>
		<th>导游星级:</th>
		<td>${starLevel[tourGuide.starLevel]}</td>
	</tr>
	<tr>
		<th>休息时间:</th>
		<td>${tourGuide.restDays}</td>
	</tr>
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${tourGuide.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
	<tr>
		<th>修改时间:</th>
		<td><fmt:formatDate value="${tourGuide.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
</table>
</div>
