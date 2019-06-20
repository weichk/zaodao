<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp"%>
<%--<div class="footer">--%>
    <%--<div class="wrap">--%>
        <%--<div class="foo_c1">--%>
            <%--<div class="foo_logo"><img src="/images/logo.png" alt=""></div>--%>
            <%--<ul class="footer_contact_list">--%>
                <%--<li><i class="sprite icon_location_w"></i>${serviceAddress}</li>--%>
                <%--&lt;%&ndash;<li><i class="sprite icon_tel_w"></i>${serviceTel}</li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<li><i class="sprite icon_fax_w"></i>${serviceFax}</li>&ndash;%&gt;--%>
                <%--<li><i class="sprite icon_mail_w"></i>${serviceEmail}</li>--%>
            <%--</ul>--%>
        <%--</div>--%>
        <%--<div class="foo_c2">--%>
            <%--<div class="footer_title">关于早导网</div>--%>
            <%--<ul class="footer_link_list">--%>
                <%--<li><a href="/portal/about.html?childIndex=aboutus">关于我们</a></li>--%>
                <%--<li><a href="/portal/about.html?childIndex=serviceAssurance">服务保障</a></li>--%>
                <%--<li><a href="/portal/about.html?childIndex=supportingOrganizations">支持机构</a></li>--%>
            <%--</ul>--%>
        <%--</div>--%>
        <%--<div class="foo_c2">--%>
            <%--<div class="footer_title">帮助中心</div>--%>
            <%--<ul class="footer_link_list">--%>
                <%--<li><a href="/portal/about.html?childIndex=guideProblem">导游问答</a></li>--%>
                <%--<li><a href="/portal/about.html?childIndex=siteProblem">反馈中心</a></li>--%>
            <%--</ul>--%>
        <%--</div>--%>
        <%--<div class="foo_right">--%>
            <%--<div class="gzh"><img src="/images/wx_gzh.png" alt=""><br>早导网公众号</div>--%>
            <%--<div class="qq_group">QQ群：${serviceQQGroup}</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="copyright wrap">${serviceCopyright}</div>--%>
<%--</div>--%>

<!-- footer -->
<div class="footer">
    <div class="footer_wrap">
        <ul>
            <li>
                <a href="/portal/about.html?childIndex=aboutus" class="t"><img src="/images/icon_footer_080901.png" alt=""><span class="name">关于我们</span></a>
                <div class="con">${aboutusSubject}</div>
            </li>
            <li>
                <a href="/portal/about.html?childIndex=serviceAssurance" class="t"><img src="/images/icon_footer_080902.png" alt=""><span class="name">服务保障</span></a>
                <div class="con">${serviceAssuranceSubject}</div>
            </li>
            <li>
                <a href="/portal/about.html?childIndex=supportingOrganizations" class="t"><img src="/images/icon_footer_080903.png" alt=""><span class="name">支持机构</span></a>
                <div class="con">${supportingOrganizationsSubject}</div>
            </li>
            <li>
                <a href="/portal/about.html?childIndex=siteProblem" class="t"><img src="/images/icon_footer_080904.png" alt=""><span class="name">反馈中心</span></a>
                <div class="con">${siteProblemSubject}</div>
            </li>
            <li>
                <a href="javascript:void(0);"
                   class="t"><img src="/images/icon_footer_080905.png" alt=""><span class="name">微信公众号</span></a>
                <div class="con">记录导游生涯的点点滴滴，分享导游生涯的酸甜苦辣，打造导游员聚集地、大本营，解决导游的所有问题</div>
            </li>
        </ul>
        <div class="copyright">${serviceCopyright}</div>
    </div>
</div>
<!-- footer end -->
<!-- 回到顶部 -->
<div id="gotop" class="sprite icon_top"></div>
<script type="text/javascript" src="/js/global.js"></script>
<!-- footer end -->