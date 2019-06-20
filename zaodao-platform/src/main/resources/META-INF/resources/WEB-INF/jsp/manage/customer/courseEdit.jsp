<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_course_editform" action="${pageContext.request.contextPath}/manage/customer/course/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="course" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">会员唯一标识：</th>
				<td><input type="text"  name="userId" size="48" placeholder="请输入会员唯一标识..." class="easyui-validatebox text" data-options="required:true"/></td>
			</tr>
			<tr>
				<th>所需积分：</th>
				<td>
					<input type="text" name="coursePrice" size="48" placeholder="请输入课程价格..." class="easyui-numberbox text" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>发布日期：</th>
				<td>
					<input type="text" name="publishTime" size="20" class="easyui-validatebox text" data-options="required:true" value="<fmt:formatDate value="${course.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
				</td>
			</tr>
			<tr>
				<th>课程名称：</th>
				<td>
					<input type="text" name="courseTitle" size="48" placeholder="请输入课程名称..." class="easyui-validatebox text" data-options="required:true"/>
				</td>
			</tr>					
			<tr>
				<th>课程简介：</th>
				<td>
					<textarea rows="1" cols="40" placeholder="请输入课程简介..." style="width:300px;" name="courseIntro" class="easyui-validatebox" data-options="required:true"></textarea>
				</td>
			</tr>
			<tr>
				<th>课程状态：</th>
				<td><select name="courseStatus" editable="false" style="height:27px;" panelHeight="100" class="easyui-combobox" data-options="required:true">
					<c:forEach items="${allCourseStatuss}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>课程封面：</th>
				<td>
					<input type="hidden" name="courseImg" size="48" class="easyui-validatebox text"
						    validType="byteLength[1,128]"/>
					<img src="${course.courseImg}" width="16" height="16" onclick="window.open('${course.courseImg}')"/>
					<input type="file" name="courseImgUpload" id="courseImgUpload" class="easyui-validatebox"
						   validtype="validImg['jpg,gif,png','只能上传jpg,gif,png格式的图片']"/>
				</td>
			</tr>
			<c:if test="${action=='edit'}">
				<tr>
					<th>报名人数</th>
					<td>
						<input type="text" name="userCount" style="width:200px;" placeholder="请输入报名人数..." class="easyui-numberbox text" data-options="required:true,min:0,max:1000"/>
					</td>
				</tr>
			</c:if>
			<tr>
				<th>课程标签：</th>
				<td style="height: 30px;">
					<!-- 展示该课程标签-->
					<div id="edit_course_tag_show" style="float:left;"></div>
					<span id="edit_course_tag_span" class="tag_new_add">添加标签</span>
					<input id='edit_course_tag_new' class="tag_new_input" type='text' maxlength="20" />
					<input id="edit_course_tag_text" name="tagContent" type="hidden" />
				</td>
			</tr>
        </table>
      </jodd:form>
    </form>
</div>
<style type="text/css">
	.tag_new_add{
		width:80px; border:solid 1px #0E2D5F; padding:2px 5px 2px 5px; border-radius:5px; box-shadow:1px 1px 1px #2D93CA;
	}
	.tag_new_input{
		width:60px; border:solid 1px #1a6dbb;  padding:2px 5px 2px 5px; border-radius:5px; box-shadow:1px 1px 1px #2D93CA;
	}
	.tag-span{
		float:left;padding:2px 5px 2px 5px;border-radius:5px;background-color:#B4CDCD !important; margin-right:3px;
	}
	.tag-x{
		color:white;padding:0px 4px 0px 4px;margin:0px 4px 0px 4px;cursor:pointer;border-radius:8px 8px 8px 8px; background-color:red;top:5px;width:13px
	}
</style>
<script type="text/javascript">
	$(function () {
        setTagSpan('${course.tagContent}');

	    $("#edit_course_tag_new").hide();

        $("#edit_course_tag_span").dblclick(function(){
            $(this).hide();
            $("#edit_course_tag_new").show().focus();
		});

        $("#edit_course_tag_new").bind('keypress',function(event){
            //回车事件
            if(event.keyCode == '13'){
                $(this).focusout();
			}
		}).focusout(function(){
            var tagText = $(this).val() || false;
            $(this).val("");
            $(this).hide();
            $("#edit_course_tag_span").show();
            if(tagText) {
                var html = $("#edit_course_tag_show").html();
                var span = "<span class='tag-span'>";
                span += "<span class='tag-text'>" + tagText + "</span>";
                span += "<span class='tag-x' onclick='removeTag(this)'>X</span></span>";

                $("#edit_course_tag_show").html(html + span);

                setTagContent();
            }
		});
    });
	//移除课程标签
	function removeTag(obj){
		$.messager.confirm('提示', '确认移除?',function(r){
			if(r){
				$(obj).parent('.tag-span').remove();
                setTagContent();
			}
		});
	}

    /**
	 * 根据标签内容展示标签
     */
	function setTagSpan(content){
		content = content || false;
	    if(content) {
            var html = "";
            var tags = content.split(",");

            for(var i = 0; i < tags.length; i++){
                html += "<span class='tag-span'>";
                html += "<span class='tag-text'>" + tags[i] + "</span>";
                html += "<span class='tag-x' onclick='removeTag(this)'>X</span></span>";
			}
            $("#edit_course_tag_show").html(html);
        }
	}
	//重新获取tag标签
	function setTagContent(){
	    var html = "";
	    var index = -1;
	    var tags = $('#edit_course_tag_show .tag-text');
	    tags.each(function(){
            html += $(this).text();
            if(++index < tags.length - 1){
                html += ',';
			}
		});
	    $('#edit_course_tag_text').val(html);
	}
</script>
