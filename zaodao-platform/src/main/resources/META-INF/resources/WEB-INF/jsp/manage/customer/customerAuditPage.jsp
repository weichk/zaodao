<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp" %>
<div style="padding: 5px;font-family:微软雅黑;">
    <form id="customerAudit" action="">
        <table class="tableForm" width="100%">
            <tr>
                <th>用户头像:</th>
                <td><img src="${customer.headImg}" width="32" height="32" title="" onclick="window.open('${customer.headImg}')"></td>
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
                <th>真实姓名:</th>
                <td>${customer.realName}</td>
            </tr>
            <tr>
                <th>昵称:</th>
                <td>${customer.userName}</td>
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
                <th>导游封面图:</th>
                <td><img src="${tourGuide.guideCoverImg}" width="32" height="32" title="" onclick="window.open('${tourGuide.guideCoverImg}')"></td>
            </tr>
            <tr>
                <th>导游资格证:</th>
                <td><img src="${tourGuide.guideCertificateImg}" width="32" height="32" title="" onclick="window.open('${tourGuide.guideCertificateImg}')"></td>
            </tr>
            <tr>
                <th>审核意见：</th>
                <td>
                    <textarea id="auditOpinion" name="auditOpinion" size="200" class="easyui-validatebox" style="margin: 0px; width: 280px; height: 70px;"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
