<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<div class="site_search">
    <form action="/portal/search.html" method="post" name="search" class="sch_form">
        <input name="keywords" type="text" placeholder="想找什么样的导游呢？" class="sch_input"><input
            type="submit"
            value="搜索"
            class="sch_submit">
    </form>
</div>