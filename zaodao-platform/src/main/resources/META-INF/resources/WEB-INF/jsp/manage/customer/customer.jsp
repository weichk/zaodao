<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp" %>
<c:if test="${initParam['ssoEnable']=='true'}">
    <%@ include file="/WEB-INF/jsp/manage/common/ssoInclude.jsp" %>
</c:if>
<script src="/js/common/flowAudit.framework.js"></script>
<script type="text/javascript">
    $(function () {
        $.acooly.framework.registerKeydown('manage_customer_searchform', 'manage_customer_datagrid');
    });

    function formatHeadImagUrl(value, row) {
        if (row == null || row.headImg == null || row.headImg == "") {
            return;
        }
        return '<a class="manage_customer_datagrid_HeadImagUrl" target="_blank" href="' + row.headImg + '" title="' + row.userName + '"><img src="' + value + '" width="16" height="16" /></a>';
    }

    function formatGuideCoverUrl(value, row) {
        if (row == null || row.guideCoverImg == null || row.guideCoverImg == "") {
            return;
        }
        return '<a class="manage_tourGuide_datagrid_guideCoverImg" target="_blank" href="' + row.guideCoverImg + '" title="封面图"><img src="' + value + '" width="16" height="16" /></a>';
    }

    function formatGuideCertificateImg(value, row) {
        if (row == null || row.guideCertificateImg == null || row.guideCertificateImg == "") {
            return;
        }
        return '<a class="manage_tourGuide_datagrid_guideCertificateImg" target="_blank" href="' + row.guideCertificateImg + '" title="导游资格证"><img src="' + value + '" width="16" height="16" /></a>';
    }
</script>
<div class="easyui-layout" data-options="fit : true,border : false">
    <!-- 查询条件 -->
    <form id="manage_customer_searchform" onsubmit="return false">
        <table class="tableForm" width="100%">
            <tr>
                <td align="left">
                    <div>
                        电话号码: <input type="text" class="text" size="15" name="search_LIKE_mobileNo"/>
                        昵称: <input type="text" class="text" size="15" name="search_LIKE_userName"/>
                        姓名: <input type="text" class="text" size="15" name="search_LIKE_realName"/>
                        是否实名: <select style="width:80px;height:27px;" name="search_EQ_isCertification" editable="false"  panelHeight="auto" class="easyui-combobox">
                        <option value="">所有</option>
                        <c:forEach var="e" items="${allIsCertifications}">
                            <option value="${e.key}" ${param.search_EQ_isCertification == e.key?'selected':''}>${e.value}</option>
                        </c:forEach></select>
                        是否导游: <select style="width:80px;height:27px;" name="search_EQ_isTourGuide" editable="false" panelHeight="auto" class="easyui-combobox">
                        <option value="">所有</option>
                        <c:forEach var="e" items="${allIsTourGuides}">
                            <option value="${e.key}" ${param.search_EQ_isTourGuide == e.key?'selected':''}>${e.value}</option>
                        </c:forEach></select>
                        是否讲师: <select style="width:80px;height:27px;" name="search_EQ_isLector" editable="false" panelHeight="auto" class="easyui-combobox">
                        <option value="">所有</option>
                        <c:forEach var="e" items="${allIsLectors}">
                            <option value="${e.key}" ${param.search_EQ_isLector == e.key?'selected':''}>${e.value}</option>
                        </c:forEach></select>
                        是否版主: <select style="width:80px;height:27px;" name="search_EQ_isModerator" editable="false" panelHeight="auto" class="easyui-combobox">
                        <option value="">所有</option>
                        <c:forEach var="e" items="${allIsModerator}">
                            <option value="${e.key}" ${param.search_EQ_isModerator == e.key?'selected':''}>${e.value}</option>
                        </c:forEach></select>
                        是否禁言: <select style="width:80px;height:27px;" name="search_EQ_isShutup" editable="false" panelHeight="auto" class="easyui-combobox">
                        <option value="">所有</option>
                        <c:forEach var="e" items="${allIsShutup}">
                            <option value="${e.key}" ${param.search_EQ_isShutup == e.key?'selected':''}>${e.value}</option>
                        </c:forEach></select>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_customer_searchform','manage_customer_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
                    </div>
                </td>
            </tr>
        </table>
    </form>

    <!-- 列表和工具栏 -->
    <div data-options="region:'center',border:false" style="overflow: hidden;height:50%">
        <table id="manage_customer_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/customer/customer/listJson.html" toolbar="#manage_customer_toolbar" fit="true" border="false" fitColumns="false" pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true" data-options="onClickRow:manage_customerInfo_loadSubTabs">
            <thead>
            <tr>
                <th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号
                </th>
                <th field="headImg" data-options="formatter:function(value,row,index){ return formatHeadImagUrl(value,row);}">用户头像
                </th>
                <th field="userId">会员ID</th>
                <th field="mobileNo">电话号码</th>
                <th field="userName">昵称</th>
                <th field="realName">真实姓名</th>
                <th field="idNumber">身份证号</th>
                <th field="isCertification" data-options="formatter:function(value){ return formatRefrence('manage_customer_datagrid','allIsCertifications',value);} ">是否实名</th>
                <th field="isTourGuide" data-options="formatter:function(value){ return formatRefrence('manage_customer_datagrid','allIsTourGuides',value);} ">是否为导游</th>
                <th field="isLector" data-options="formatter:function(value){ return formatRefrence('manage_customer_datagrid','allIsLectors',value);} ">是否讲师</th>
                <th field="isModerator" data-options="formatter:function(value){ return formatRefrence('manage_customer_datagrid','allIsModerator',value);} ">是否为版主</th>
                <th field="moderatorPermission" data-options="formatter:function(value){ return formatRefrence('manage_customer_datagrid','allModeratorPermissions',value);} ">版主权限</th>
                <th field="isShutup" data-options="formatter:function(value){ return formatRefrence('manage_customer_datagrid','allIsShutup',value);} ">是否禁言</th>
                <th field="sex" data-options="formatter:function(value){ return formatRefrence('manage_customer_datagrid','allSexs',value);} ">性别</th>
                <th field="age">年龄</th>
                <th field="contactAddress">联系地址</th>
                <th field="medalCode" data-options="formatter:function(v){
                    if(v==null||v=='')
                    return;
                    v = v.split(',');
                    var names = [];
                    $.each(v,function(i,e){
                        names.push(formatRefrence('manage_customer_datagrid','allMedalEnums',e));
                    });
                    return names.join();
                }">勋章</th>
                <th field="createTime" formatter="formatDate">创建时间</th>
                <th field="updateTime" formatter="formatDate">修改时间</th>
                <th field="rowActions" formatter="manage_customer_datagrid_rowActionFormatter">动作</th>
            </tr>
            </thead>
        </table>

        <!-- 每行的Action动作模板 -->
        <div id="manage_customer_action" style="display: none;">
            <a onclick="$.acooly.framework.edit({url:'/manage/customer/customer/edit.html',id:'{0}',entity:'customer',width:600,height:500});"
               href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
            <a onclick="$.acooly.framework.show('/manage/customer/customer/show.html?id={0}',500,400);" href="#"
               title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
        </div>
        <!-- 每行的Action动作模板 -->
        <div id="manage_customer_action_audit" style="display: none;">
            <a onclick="manage_customer_audit_action('{0}')" href="javascript:void(0);" title="审核"><i
                    class="fa fa-question-circle fa-lg fa-fw fa-col"></i></a>
        </div>

        <!-- 每行的Action动作模板 -->
        <div id="manage_prohibition_user" style="display: none;">
            <a title="禁言" onclick="manage_prohibition_user('{0}');" href="#"><i class="fa fa-ban fa-lg fa-fw fa-col"></i></a>
        </div>
    </div>

    <div data-options="region:'south',border:false" style="height:45%;">
        <div id="manage_customer_sub_tab" class="easyui-tabs" fit="true">
            <%--导游信息--%>
            <div title="导游信息" style="margin-left: 0px;">
                <table id="manage_tourGuide_datagrid" class="easyui-datagrid" toolbar="#manage_tourGuide_toolbar"
                       fit="true" border="false" fitColumns="false"
                       pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id"
                       sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
                    <thead>
                    <tr>
                        <th field="showCheckboxWithId" checkbox="true"
                            data-options="formatter:function(value, row, index){ return row.id }">编号
                        </th>
                        <th field="guideCoverImg"
                            data-options="formatter:function(value,row,index){ return formatGuideCoverUrl(value,row);}">导游封面图</th>
                        <th field="guideCertificateImg"
                            data-options="formatter:function(value,row,index){ return formatGuideCertificateImg(value,row);}">导游资格证</th>
                        <th field="guideNo">导游证号</th>
                        <th field="guideCertificateNo">导游资格证号</th>
                        <th field="tourTime">带团时间/年</th>
                        <th field="guideLevel" data-options="formatter:function(value){ return formatRefrence('manage_tourGuide_datagrid','allGuideLevels',value);} ">导游等级</th>
                        <th field="permanentCity">常驻城市</th>
                        <th field="goodRoute">擅长路线</th>
                        <th field="introduceMyself">自我介绍</th>
                        <th field="speciality">导游专长</th>
                        <th field="pricePerDay" sum="true" formatter="centMoneyFormatter">带团价格(元/天)</th>
                        <th field="language">语言</th>
                        <th field="hotGuide" data-options="formatter:function(value){ return formatRefrence('manage_tourGuide_datagrid','allIsHotStatus',value);} ">是否人气</th>
                        <th field="starLevel" data-options="formatter:function(value){ return formatRefrence('manage_tourGuide_datagrid','starLevel',value);} ">星级</th>
                        <th field="restDays">休息时间</th>
                        <th field="createTime" formatter="formatDate">创建时间</th>
                        <th field="updateTime" formatter="formatDate">修改时间</th>
                        <th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_tourGuide_action',value,row)}">动作</th>
                    </tr>
                    </thead>
                </table>
                <!-- 每行的Action动作模板 -->
                <div id="manage_tourGuide_action" style="display: none;">
                    <a onclick="$.acooly.framework.edit({url:'/manage/customer/tourGuide/edit.html',id:'{0}',entity:'tourGuide',width:500,height:400});"
                       href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
                </div>
            </div>
            <%--会员绑卡信息--%>
            <div title="会员绑卡信息" style="margin-left: 0px;">
                <table id="manage_customerCard_datagrid" class="easyui-datagrid" toolbar="#manage_customerCard_toolbar"
                       fit="true" border="false" fitColumns="false"
                       pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id"
                       sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
                    <thead>
                    <tr>
                        <th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号
                        </th>
                        <th field="id" sum="true">id</th>
                        <th field="userId">用户ID</th>
                        <th field="mobileNo">用户手机号码</th>
                        <th field="cardNo">绑卡号</th>
                        <th field="cardName">绑卡中文名称</th>
                        <th field="createTime" formatter="formatDate">创建时间</th>
                        <th field="updateTime" formatter="formatDate">更新时间</th>
                        <th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_customerCard_action',value,row)}">动作</th>
                    </tr>
                    </thead>
                </table>

                <!-- 每行的Action动作模板 -->
                <div id="manage_customerCard_action" style="display: none;">
                    <a onclick="$.acooly.framework.show('/manage/customer/customerCard/show.html?id={0}',500,400);"
                       href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    /**
     * 点击客户主表格行，加载显示所有关联只tab数据
     */
    function manage_customerInfo_loadSubTabs() {
        load_manage_customerCard_datagrid();
        load_manage_tourGuide_datagrid();
    }

    //使用ajax查询和加载:会员绑卡信息
    function load_manage_customerCard_datagrid() {
        $.acooly.framework.fireSelectRow('manage_customer_datagrid', function (row) {
            $.acooly.framework.loadGrid({
                gridId: "manage_customerCard_datagrid",
                url: '/manage/customer/customerCard/listJson.html',
                ajaxData: {"search_EQ_userId": row.userId}
            });
        }, '请先选择操作会员数据行');
    }

    //使用ajax查询和加载:导游信息
    function load_manage_tourGuide_datagrid() {
        $.acooly.framework.fireSelectRow('manage_customer_datagrid', function (row) {
            $.acooly.framework.loadGrid({
                gridId: "manage_tourGuide_datagrid",
                url: '/manage/customer/tourGuide/listJson.html',
                ajaxData: {"search_EQ_userId": row.userId}
            });
        }, '请先选择操作会员数据行');
    }

    function manage_customer_datagrid_rowActionFormatter(value, row, index) {
        var actionHtml = "";
        if (row.isTourGuide == -1) {
            //actionHtml += formatString($('#manage_customer_action_audit').html(), row.id);
        }
        if (row.isShutup == '0') {
            actionHtml += formatString($('#manage_prohibition_user').html(), row.id);
        }
        actionHtml += formatString($('#manage_customer_action').html(), row.id);
        return actionHtml;
    }

    function manage_customer_audit_action(id) {
        var jsonDatas = {
            "id": id,
        };
        var passButtonTxt = "审核通过";
        $.flowAudit.framework.commonFlowAudit({
            auditPassUrl: "/manage/customer/customer/auditPass.html",
            auditPassButton: passButtonTxt,
            auditRejectUrl: "/manage/customer/customer/auditReject.html",
            auditEndUrl: "",
            auditPagesUrl: "/manage/customer/customer/auditPage.html",
            form: 'customerAudit',
            id: id,
            jsonData: jsonDatas,
            entity: 'customer',
            width: 600,
            height: 500,
            reload: true
        });
    }

    function manage_prohibition_user(id) {
        $('<div/>').dialog({
            href: '/manage/customer/customer/prohibition.html?id=' + id,
            width: 600,
            height: 400,
            modal: true,
            title: '禁言用户',
            buttons: [{
                text: '禁言',
                iconCls: 'icon-edit',
                handler: function () {
                    var d = $(this).closest('.window-body');
                    $('#customerAudit').form('submit', {
                        onSubmit: function () {
                            var isValid = $('#customerAudit').form('validate');
                            if (!isValid) {
                                return false;
                            }
                            return true;
                        },
                        success: function (data) {
                            try {
                                var result = $.parseJSON(data);
                                if (result.success) {
                                    d.dialog('destroy');
                                }
                                $('#manage_customer_datagrid').datagrid('reload');
                                $.messager.show({
                                    title: '提示',
                                    msg: result.message
                                });
                            } catch (e) {
                                $.messager.alert('提示', result);
                            }
                        }
                    });
                }
            }, {
                text: '<i class="fa fa-times-circle fa-lg fa-fw fa-col" ></i>关闭',
                /*iconCls : 'icon-cancel',*/
                handler: function () {
                    var d = $(this).closest('.window-body');
                    d.dialog('close');
                }
            }],
            onClose: function () {
                $(this).dialog('destroy');
            }
        });
    }
</script>