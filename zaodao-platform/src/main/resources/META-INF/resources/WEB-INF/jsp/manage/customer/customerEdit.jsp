<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp" %>
<div>
    <form id="manage_customer_editform" action="${pageContext.request.contextPath}/manage/customer/customer/${action=='create'?'saveJson':'updateJson'}.html" method="post">
        <jodd:form bean="customer" scope="request">
            <input name="id" type="hidden"/>
            <table class="tableForm" width="100%">
                <tr>
                    <th width="25%">会员ID：</th>
                    <td><input type="text" disabled name="userId" size="48" class="easyui-validatebox text" data-options="required:true" validType="byteLength[1,64]"/></td>
                </tr>
                <tr>
                    <th>电话号码：</th>
                    <td><input type="text" name="mobileNo" size="48" class="easyui-validatebox text" data-options="required:true" validType="byteLength[1,32]"/></td>
                </tr>
                <tr>
                    <th>账户名：</th>
                    <td><input type="text" disabled name="userName" size="48" class="easyui-validatebox text" validType="byteLength[1,32]"/></td>
                </tr>
                <tr>
                    <th>真实姓名：</th>
                    <td><input type="text" disabled name="realName" size="48" class="easyui-validatebox text"  validType="byteLength[1,32]"/></td>
                </tr>
                <tr>
                    <th>身份证号：</th>
                    <td><input type="text" disabled name="idNumber" size="48" class="easyui-validatebox text" validType="byteLength[1,18]"/></td>
                </tr>
                <tr>
                    <th>是否实名：</th>
                    <td>
                        <select disabled name="isCertification" editable="false" style="height:27px; width: 120px;" panelHeight="100" class="easyui-combobox">
                            <c:forEach items="${allIsCertifications}" var="e">
                                <option value="${e.key}">${e.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>是否为导游：</th>
                    <td>
                        <select name="isTourGuide" editable="false" style="height:27px; width: 120px;" panelHeight="100" class="easyui-combobox">
                            <c:forEach items="${allIsTourGuides}" var="e">
                                <option value="${e.key}">${e.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>审核意见：</th>
                    <td>
                        <input type="text" name="auditDesc" size="48" class="easyui-validatebox text" validType="byteLength[1,255]"/>
                    </td>
                </tr>
                <tr>
                    <th>是否为讲师：</th>
                    <td>
                        <select name="isLector" editable="false" style="height:27px; width: 120px;" panelHeight="100" class="easyui-combobox">
                            <c:forEach items="${allIsLectors}" var="e">
                                <option value="${e.key}">${e.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>是否为版主：</th>
                    <td>
                        <select name="isModerator" editable="false" style="height:27px; width: 120px;" panelHeight="100" class="easyui-combobox">
                            <c:forEach items="${allIsModerator}" var="e">
                                <option value="${e.key}">${e.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>版主权限：</th>
                    <td>
                        <select name="moderatorPermission" editable="false" style="height:27px; width: 120px;" panelHeight="100" class="easyui-combobox">
                            <c:forEach items="${allModeratorPermissions}" var="e">
                                <option value="${e.key}">${e.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>是否禁言：</th>
                    <td><select name="isShutup" editable="false" style="height:27px; width: 120px;" panelHeight="100" class="easyui-combobox">
                        <c:forEach items="${allIsShutup}" var="e">
                            <option value="${e.key}">${e.value}</option>
                        </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <th>电子邮件：</th>
                    <td><input disabled type="text" name="email" size="48" class="easyui-validatebox text" validType="byteLength[1,64]"/></td>
                </tr>
                <tr>
                    <th>性别：</th>
                    <td><select disabled name="sex" editable="false" style="height:27px; width: 120px;" panelHeight="100" class="easyui-combobox">
                        <c:forEach items="${allSexs}" var="e">
                            <option value="${e.key}">${e.value}</option>
                        </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <th>年龄：</th>
                    <td><input disabled type="text" name="age" size="48" class="easyui-numberbox text" validType="byteLength[1,10]"/></td>
                </tr>
                <tr>
                    <th>联系地址：</th>
                    <td><input type="text" name="contactAddress" size="48" class="easyui-validatebox text" validType="byteLength[1,128]"/></td>
                </tr>
                <tr>
                    <th>勋章：</th>
                    <td>
                        <c:forEach items="${allMedalEnums}" var="e">
                            <input type="checkbox" value="${e.key}" name="medalCodes" class="medalCodes"/>${e.value}
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <th>是否开通商务接待：</th>
                    <td>
                        <select name="isBusRecept" editable="false" style="height:27px; width: 120px;" panelHeight="100" class="easyui-combobox">
                            <c:forEach items="${allIsOpenables}" var="e">
                                <option value="${e.key}">${e.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>是否开通政务接待：</th>
                    <td>
                        <select name="isGovRecept" editable="false" style="height:27px;  width: 120px;" panelHeight="100" class="easyui-combobox">
                            <c:forEach items="${allIsOpenables}" var="e">
                                <option value="${e.key}">${e.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>是否挂靠旅行社：</th>
                    <td>
                        <select name="isLinkAngency" editable="false" style="height:27px; width: 120px;" panelHeight="100" class="easyui-combobox">
                            <c:forEach items="${allIsOpenables}" var="e">
                                <option value="${e.key}">${e.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>是否具备领队资质：</th>
                    <td>
                        <select name="isLeader" editable="false" style="height:27px;  width: 120px;" panelHeight="100" class="easyui-combobox">
                            <c:forEach items="${allIsOpenables}" var="e">
                                <option value="${e.key}">${e.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
        </jodd:form>
    </form>
</div>
<script type="text/javascript">
    $(function () {
        $('.medalCodes').each(function () {
            <c:forEach items="${customer.medalEnums}" var="e">
            if ($(this).val() == '${e.code}') {
                $(this).attr("checked", true);
            }
            </c:forEach>
        })
    });
</script>
