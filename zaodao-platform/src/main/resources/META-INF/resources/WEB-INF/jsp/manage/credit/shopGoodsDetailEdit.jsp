<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<script type="text/javascript">

$(function () {
    var token = $("meta[name='X-CSRF-TOKEN']").attr("content");// 从meta中获取token
    $.acooly.framework.kingEditor({
        uploadUrl : '/ofile/kindEditor.html?_csrf=' + token,
        minHeight : '400',
        textareaId : 'contentId'
    });
});
</script>
<div>
    <form id="manage_shopGoodsDetail_editform" action="${pageContext.request.contextPath}/manage/credit/shopGoodsDetail/saveJson.html" method="post" enctype="multipart/form-data">
      <jodd:form bean="shopGoodsDetail" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<td>${goods.name}<input type="hidden" name="goodsId" value="${goods.id}"/></td>
			</tr>
            <td>
                    <textarea id="contentId" name="body" data-options="required:true"
                              style="width:100%;height:430px;"></textarea>
            </td>
        </table>
      </jodd:form>
    </form>
</div>
