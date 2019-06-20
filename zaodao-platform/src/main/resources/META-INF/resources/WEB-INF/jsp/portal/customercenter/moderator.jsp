<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!-- page: 我是版主 -->

<div class="moderator page_wrap">
    <div class="mpage_title">我是版主</div>
    <div class="moderator_top">
        <select name="select_class" id="article_label_id" onchange="getArticleList(1,1)" id="select_class"
                class="select_class">
            <option value="">全部板块</option>
            <option value="guideWord">导游词</option>
            <option value="skillsTour">带团技巧</option>
            <option value="guideTraining">导游培训</option>
            <option value="domesticRoutes">国内路线</option>
            <option value="exitRoutes">境外路线</option>
            <option value="customRoute">定制路线</option>
            <option value="guideTreeHole">导游树洞</option>
            <option value="caseTour">带团案例</option>
            <option value="complaintCase">投诉案例</option>
            <option value="problemSolving">问题处理</option>
        </select>
        <span class="moderator_sch"><input type="text" id="search_content_id" placeholder="按标题，文章发布人搜索"
                                           class="text"><button class="btn"
                                                                onclick="getArticleList(1,1)"></button></span>
    </div>
    <div id="article_list_id">

    </div>
</div>

<!-- 帖子列表模板-->
<script id="articleList-template" type="text/html">
    <ul class="moderator_list">
        {{#each rows}}
        <li>
            <span class="img pie"><img src="{{headImg}}" alt=""></span>
            <div class="con">
                <a target="_blank" href="/portal/guideHome/getArticleInfo_{{articleId}}.html" class="t">
                    {{#myif essenceStatus '==' '1' }}
                    <span class="stick_great">精</span>
                    {{/myif}}
                    {{#myif upStatus '==' '1' }}
                    <span class="stick_top">置顶</span>
                    {{/myif}}
                    {{title}}
                </a>
                <div class="f">
                    <span class="name">{{realName}}<i class="medal"></i></span>
                    <span class="date">{{createTime}} </span>
                    <div class="fr">
                        <span class="class_name">{{articleLabel label}}</span>
                        <div class="set_stamp">
                            <span class="set_stamp_btn">{{#if stampName}}{{stampName}}{{else}}设置图章{{/if}}</span>
                            <ul class="set_stamp_list pie">
                                <i class="arrow_manage"></i>
                                {{#each ../data.stamps}}
                                    <li><a href="javascript:void(0);"
                                           onclick="setStamp('{{../articleId}}','{{code}}','{{message}}')">{{message}}</a></li>
                                {{/each}}
                            </ul>
                        </div>
                        <div class="manage">
                            <span class="manage_blogs">管理</span>
                            <ul class="manage_list pie">
                                <i class="arrow_manage"></i>
                                <li>
                                    {{#myif upStatus '==' '1'}}
                                    <a href="javascript:void(0);" onclick="up('{{articleId}}',0)"
                                       class="stick_up">取消置顶</a>
                                    {{else}}
                                    <a href="javascript:void(0);" onclick="up('{{articleId}}',1)"
                                       class="stick_up">置顶</a>
                                    {{/myif}}
                                </li>
                                <li>
                                    {{#myif essenceStatus '==' '1'}}
                                    <a href="javascript:void(0);" onclick="essence('{{articleId}}',0)"
                                       class="cancel_stick_great">取消加精</a>
                                    {{else}}
                                    <a href="javascript:void(0);" onclick="essence('{{articleId}}',1)"
                                       class="cancel_stick_great">加精</a>
                                    {{/myif}}
                                </li>
                                <li><a href="javascript:void(0);" onclick="deleteArticle('{{articleId}}')" class="delete_blog">删除</a>
                                </li>
                                <li><a href="javascript:void(0);" onclick="moveArticle('{{articleId}}')" class="move_blog">移动</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </li>
        {{/each}}
    </ul>
    <div class="pages" id="moderator_list_page_id">

    </div>
</script>
<script type="text/javascript" src="/js/platformjs/moderator.js" charset="utf-8"></script>