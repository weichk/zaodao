<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp" %>
<div>
    <form id="manage_article_editform"
          action="${pageContext.request.contextPath}/manage/article/article/${action=='create'?'saveJson':'updateJson'}.html"
          method="post">
        <jodd:form bean="article" scope="request">
            <input name="id" type="hidden"/>
            <table class="tableForm" width="100%">
                <tr>
                    <th width="10%">发表人ID：</th>
                    <td style="width: 100px;"><input type="text" name="userId" size="48" class="easyui-validatebox text"
                                                     data-options="required:true" validType="byteLength[1,64]"/></td>
                    <th width="10%">帖子类型：</th>
                    <td><select name="articleType" editable="false" style="height:27px;" panelHeight="auto"
                                class="easyui-combobox" data-options="required:true">
                        <c:forEach items="${allArticleTypes}" var="e">
                            <option value="${e.key}">${e.value}</option>
                        </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <th>文章标题：</th>
                    <td><input type="text" name="title" size="48" class="easyui-validatebox text"
                               data-options="required:true" validType="byteLength[1,64]"/></td>
                    <th>帖子状态：</th>
                    <td><select name="articleStatus" editable="false" style="height:27px;" panelHeight="auto"
                                class="easyui-combobox" data-options="required:true">
                        <c:forEach items="${allArticleStatuss}" var="e">
                            <option value="${e.key}">${e.value}</option>
                        </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <th>手机号码：</th>
                    <td><input type="text" name="mobileNo" size="48" class="easyui-validatebox text"
                               data-options="required:true" validType="byteLength[1,32]"/></td>
                    <th>帖子类型：</th>
                    <td><select name="articleHotType" editable="false" style="height:27px;" panelHeight="auto"
                                class="easyui-combobox">
                        <c:forEach items="${allArticleHotTypes}" var="e">
                            <option value="${e.key}">${e.value}</option>
                        </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <th>地区：</th>
                    <td><input type="text" name="area" size="48" class="easyui-validatebox text"
                               data-options="required:true" validType="byteLength[1,64]"/></td>
                    </select></td>
                    <th>是否置顶：</th>
                    <td>
                        <select name="upStatus" editable="false" style="height:27px;" panelHeight="auto"
                                class="easyui-combobox">
                            <c:forEach items="${allUpStatuss}" var="e">
                                <option value="${e.key}">${e.value}</option>
                            </c:forEach>
                        </select>
                        是否加精：
                        <select name="essenceStatus" editable="false" style="height:27px;" panelHeight="auto"
                                class="easyui-combobox">
                            <c:forEach items="${allEssenceStatuss}" var="e">
                                <option value="${e.key}">${e.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>景区：</th>
                    <td><input type="text" name="scenic" size="48" class="easyui-validatebox text"
                               data-options="required:true" validType="byteLength[1,64]"/></td>
                    <th>图章：</th>
                    <td>
                        <select name="stampCode" editable="false" style="height:27px;" panelHeight="auto"
                                class="easyui-combobox"
                                data-options="required:true">
                            <option value="">请选择</option>
                            <c:forEach items="${allStampEnums}" var="e">
                                <option value="${e.key}">${e.value}</option>
                            </c:forEach>
                        </select></td>
                </tr>
                    <%--<tr>--%>
                    <%--<th>文章内容：</th>--%>
                    <%--<td><input type="text" name="content" size="48" class="easyui-validatebox text" data-options="required:true" /></td>--%>
                    <%--</tr>					--%>
                    <%--<tr>--%>
                    <%--<th>文章封面：</th>--%>
                    <%--<td><input type="text" name="cover" size="48" class="easyui-validatebox text" data-options="required:true" validType="byteLength[1,128]"/></td>--%>
                    <%--</tr>					--%>
                <tr>
                    <th>阅览数：</th>
                    <td><input type="text" name="readCount" size="48" class="easyui-numberbox text"
                               validType="byteLength[1,19]"/></td>
                    <th>标签：</th>
                    <td><select name="label" editable="false" style="height:27px;" panelHeight="auto"
                                class="easyui-combobox"
                                data-options="required:true">
                        <c:forEach items="${allArticleLabels}" var="e">
                            <option value="${e.key}">${e.value}</option>
                        </c:forEach>
                    </select>
                    </td>
                </tr>

                <tr>
                    <th>点赞数：</th>
                    <td><input type="text" name="praiseCount" size="48" class="easyui-numberbox text"
                               validType="byteLength[1,19]"/></td>
                    <th>文章封面：</th>
                    <td>
                        <input type="hidden" name="cover" size="48" class="easyui-validatebox text"
                               data-options="required:true" validType="byteLength[1,128]"/>
                        <img src="${article.cover}" width="16" height="16" onclick="window.open('${article.cover}')"/>
                        <input type="file" name="coverUpload" id="coverUpload" class="easyui-validatebox"
                               validtype="validImg['jpg,gif,png','只能上传jpg,gif,png格式的图片']"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <textarea id="contentId" name="content" data-options="required:true"
                                  style="width:100%;height:430px;"></textarea>
                    </td>
                </tr>
            </table>
            <script type="text/javascript">
                $(function () {
                    var token = $("meta[name='X-CSRF-TOKEN']").attr("content");// 从meta中获取token
                    var ke = $.acooly.framework.kingEditor({
                        uploadUrl: '/ofile/kindEditor.html?_csrf=' + token,
                        minHeight: '310',
                        textareaId: 'contentId'
                    });
                });
            </script>
        </jodd:form>
    </form>
</div>

<script type="text/javascript">
    $("#cover").change(function () {
        console.info($(this).val());
    });
</script>
