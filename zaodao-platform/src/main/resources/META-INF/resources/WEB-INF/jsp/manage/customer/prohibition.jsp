<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp" %>
<div style="padding: 5px;font-family:微软雅黑;">
    <form id="customerAudit" action="/manage/customer/customer/prohibitionMsg.html">
        <input type="hidden" value="${customer.id}" name="id"/>
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
                <th>昵称:</th>
                <td>${customer.userName}</td>
            </tr>
            <tr>
                <th>禁言意见：</th>
                <td>
                    <textarea id="prohibitionMsg" name="prohibitionMsg" size="200" class="easyui-validatebox"
                              style="margin: 0px; width: 280px; height: 70px;"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
