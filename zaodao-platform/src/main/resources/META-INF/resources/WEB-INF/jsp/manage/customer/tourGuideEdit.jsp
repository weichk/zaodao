<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_tourGuide_editform" action="${pageContext.request.contextPath}/manage/customer/tourGuide/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="tourGuide" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">用户ID：</th>
				<td>${tourGuide.userId}</td>
				<td colspan="2">导游资格证：</td>
			</tr>					
			<tr>
				<th>导游封面图：</th>
				<td><img src="${tourGuide.guideCoverImg}" width="32" height="32" title=""></td>
				<td rowspan="5" valign="bottom" colspan="2"><c:if test="${tourGuide.guideCertificateImg != null}">
					<img src="${tourGuide.guideCertificateImg}" width="90" height="160">
				</c:if></td>
			</tr>
			<tr>
				<th>导游证号：</th>
				<td>${tourGuide.guideNo}</td>
			</tr>					
			<tr>
				<th>导游资格证号：</th>
				<td>${tourGuide.guideCertificateNo}</td>
			</tr>					
			<tr>
				<th>带团时间：</th>
				<td>${tourGuide.tourTime}年</td>
			</tr>
			<tr>
				<th>导游等级：</th>
				<td>
					<select name="guideLevel" editable="false" style="width:210px;" panelHeight="100" class="easyui-combobox" >
						<c:forEach items="${allGuideLevels}" var="e">
							<option value="${e.key}">${e.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>常驻城市：</th>
				<td>${tourGuide.permanentCity}</td>
			</tr>
			<tr>
				<th>擅长路线：</th>
				<td>${tourGuide.goodRoute}</td>
			</tr>					
			<tr>
				<th>自我介绍：</th>
				<td>${tourGuide.introduceMyself}</td>
			</tr>					
			<tr>
				<th>导游专长：</th>
				<td>${tourGuide.speciality}</td>
			</tr>					
			<tr>
				<th>带团价格：</th>
				<td>${tourGuide.pricePerDay/100}元/天</td>
			</tr>					
			<tr>
				<th>带团语言：</th>
				<td>${tourGuide.language}</td>
			</tr>					
			<tr>
				<th>是否人气：</th>
				<td>
					<select name="hotGuide" editable="false" style="width:210px;" panelHeight="100" class="easyui-combobox">
						<c:forEach items="${allIsHotStatus}" var="e">
							<option value="${e.key}">${e.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>导游星级：</th>
				<td>
					<select name="starLevel" editable="false" style="width:210px;" panelHeight="100" class="easyui-combobox" >
						<c:forEach items="${starLevel}" var="e">
							<option value="${e.key}">${e.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>休息时间：</th>
				<td>${tourGuide.restDays}</td>
			</tr>
			<tr>
				<th>商务接待：</th>
				<td>
					<select name="busReceptCount" editable="false" style="width:210px;" panelHeight="100" class="easyui-combobox" >
						<c:forEach items="${allBusReceptCounts}" var="e">
							<option value="${e.key}">${e.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>政务接待：</th>
				<td>
					<select name="govReceptCount" editable="false" style="width:210px;" panelHeight="100" class="easyui-combobox" >
						<c:forEach items="${allGovReceptCounts}" var="e">
							<option value="${e.key}">${e.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
        </table>
      </jodd:form>
    </form>
</div>
