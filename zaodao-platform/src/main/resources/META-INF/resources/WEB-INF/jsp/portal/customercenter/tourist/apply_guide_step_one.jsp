<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/portal/common/meta.jsp"/>
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <link rel="stylesheet" href="/css/find_pwd.css"/>
</head>

<body>
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=customerCenter"/>
<!-- header end -->
<div class="find_pwd_content apply_guide_content pie">
    <h2 class="find_pwd_t"><a href="/portal/services/customer/customeIndex.html">会员中心</a> > 申请成为导游</h2>
    <ul class="find_pwd_nav apply_guide_nav step1">
        <li class="n1">
            <span class="n pie">1</span>
            <p>基本信息</p>
        </li>
        <li class="n2 disabled">
            <span class="n pie">2</span>
            <p>影像资料</p>
        </li>
        <li class="n3 disabled">
            <span class="n pie">3</span>
            <p>完成</p>
        </li>
    </ul>
    <div class="apply_guide_wrap">
        <form action="/portal/services/customer/setApplyGuideInfo.html" method="post" name="apply_guide_info"
              class="apply_guide_info" id="apply_guide_info_form_one_id">
            <%--<div class="form_item">--%>
            <%--<label class="label">真实姓名</label>--%>
            <%--${sessionScope.sessionKeyUserInfo.realName}--%>

            <%--<div class="Validform_checktip"></div>--%>
            <%--</div>--%>
            <div class="form_item">
                <label class="label">手机号码</label>
                <c:out value="${fn:substring(sessionScope.sessionKeyUserInfo.mobileNo,0,3)}****${fn:substring(sessionScope.sessionKeyUserInfo.mobileNo,7,11)}"></c:out>

                <div class="Validform_checktip"></div>
            </div>
            <div class="form_item">
                <label class="label">身份证号</label>
                <c:out value="${fn:substring(sessionScope.sessionKeyUserInfo.idNumber,0,1)}****************"></c:out>

            </div>
            <div class="form_item">
                <label class="label">性别</label>
                <span class="sex_item"><input type="radio" name="sex" value="1" datatype="*" errormsg="请选择性别">男</span>
                <span class="sex_item"><input type="radio" name="sex" value="0">女</span></div>
            <div class="Validform_checktip"></div>
            <div class="form_item short">
                <label for="age" class="label">年龄</label>
                <input type="text" name="age" id="age" class="text" datatype="n" nullmsg="年龄不能为空" errormsg="请填写数字"> 岁
                <div class="Validform_checktip"></div>
            </div>
            <div class="form_item short">
                <label for="pricePerDay" class="label">带团价格</label>
                <input type="text" name="pricePerDay" id="pricePerDay" class="text" datatype="n" nullmsg="带团价格不能为空"
                       errormsg="请填写数字"> 元/天
                <div class="Validform_checktip"></div>
            </div>
            <div class="form_item">
                <label for="speciality_id" class="label">导游爱好</label>
                <input type="text" name="speciality" id="speciality_id" class="text" datatype="*1-15" nullmsg="导游爱好不能为空"
                       errormsg="导游爱好不能超过15位！">
                <div class="Validform_checktip"></div>
            </div>
            <div class="form_item">
                <label for="guider_id" class="label">导游证号</label>
                <input type="text" name="guideNo" id="guider_id" class="text" datatype="*" nullmsg="导游证号不能为空">
                <div class="Validform_checktip"></div>
            </div>
            <div class="form_item">
                <label for="guider_guard_id" class="label">导游资格证号</label>
                <input type="text" name="guideCertificateNo" id="guider_guard_id" class="text" datatype="*"
                       nullmsg="导游资格证号不能为空">
                <div class="Validform_checktip"></div>
            </div>
            <div class="form_item">
                <label for="real_name_id" class="label">真实姓名</label>
                <input type="text" name="realName" id="real_name_id" class="text" datatype="*" nullmsg="真实姓名不能为空">
                <div class="Validform_checktip"></div>
            </div>
            <div class="form_item">
                <label class="label">导游资格证</label>
                <label class="upload_guide_ca"><img id="guider_certification_img_id" src="/images/upload_guide_ca.jpg"
                                                    alt="" width="90px" height="160px">请上传“全国导游之家APP”电子导游证
                    <div>
                        <div id="guider_certification_id"></div>
                    </div>
                </label>
                <input type="hidden" name="guideCertificateImg" id="guide_certificate_img_id">
            </div>
            <div class="form_item lang">
                <label class="label">带团语种</label>
                <span class="lang_item">
                        <input type="checkbox" name="language" value="chinese" datatype="*" nullmsg="请选择带团语种">中文</span>
                <span class="lang_item">
                        <input type="checkbox" name="language" value="english">英文</span>
                <span class="lang_item">
                        <input type="checkbox" name="language" value="japanese">日文</span>
                <div class="Validform_checktip"></div>
            </div>
            <div class="form_item">
                <label for="city" class="label">常驻城市</label>
                <select name="city" id="city">areaList
                    <c:forEach items="${areaList}" var="e" varStatus="s">
                        <option value="${e.name}">${e.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form_item short">
                <label for="work_age" class="label">带团时间</label>
                <input type="text" name="workAge" id="work_age" class="text" datatype="n" nullmsg="带团时间不能为空"
                       errormsg="请填写数字！">年
                <div class="Validform_checktip"></div>
            </div>
            <div class="form_item">
                <label for="familiar_line" class="label">擅长带团路线</label>
                <textarea name="familiarLine" id="familiar_line" cols="30" rows="10" class="textarea" datatype="*"
                          nullmsg="擅长带团路线不能为空"></textarea>
                <div class="Validform_checktip"></div>
            </div>
            <div class="form_item">
                <label for="introduce" class="label">自我介绍</label>
                <textarea name="introduce" id="introduce" cols="30" rows="10" class="textarea" datatype="*"
                          nullmsg="自我介绍不能为空"></textarea>
                <div class="Validform_checktip"></div>
            </div>
            <input type="submit" name="next_stop_2" value="下一步" class="find_pwd_btn">
        </form>
    </div>
</div>

<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>
<script type="text/javascript" src="/plugins/webuploader/webuploader.js" charset="utf-8"></script>
<script type="text/javascript" src="/js/platformjs/apply_guide_step_one.js" charset="utf-8"></script>
</body>
</html>