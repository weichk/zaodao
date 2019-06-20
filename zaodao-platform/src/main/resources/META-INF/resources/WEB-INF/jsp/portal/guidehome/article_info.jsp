<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>${article.title}-早导网</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="导游论坛,导游带团,导游日记,导游路线,导游行程,导游问答,导游词,导游游记">
    <meta http-equiv="description" content="早导网导游论坛,是为导游群体打造的专属社区,包含有带团日志、导游树洞等记录导游生涯的生活板块,又有导游秘籍、案例分析等有助于导游成长的知识板块,导游的聚集地就在早导网导游社区.">
    <meta charset="utf-8">
    <meta name="X-CSRF-TOKEN" content="${requestScope["org.springframework.security.web.csrf.CsrfToken"].token}"/>
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <meta name="renderer" content="webkit">
    <jsp:include page="/WEB-INF/jsp/portal/common/include.jsp"/>
    <script src="/js/modernizr.min.js"></script>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/css/travel_diary.css"/>
</head>

<body class="grey_bg">
<!-- 导航 -->
<jsp:include page="/portal/portlet/nav.html?pageIndex=guideHome"/>

<%--<!-- banner -->--%>
<%--<jsp:include page="/portal/portlet/innerBanner.html?bannerType=guideHomeBanner"/>--%>

<!-- 帖子详情 -->
<input type="hidden" value="${isLogin}" id="is_login_id"/>
<input type="hidden" value="${havaRedBag}" id="hava_red_bag_id"/>
<input type="hidden" value="${getPoint}" id="get_point_id"/>
<input type="hidden" value="${isShutup}" id="is_shutup_id"/>
<div class="travel_diary wrap">
    <div class="top">
        <div class="breadcrum"><a href="/portal/guideHome/blogList.html?type=${article.articleType.code}">
            ${article.articleType.message}</a>
            > <a
                    href="/portal/guideHome/blogList.html?type=${article.articleType.code}&lable=${article.label.code}
">${article.label.message}</a></div>
    </div>

    <div id="quik_btns" class="quik_btns">
        <a href="javascript:void(0);" onclick="redirectReply()" class="goto_reply"><i class="sprite icon_dial_w"></i>回复</a>
        <c:if test="${sessionScope.sessionKeyUserInfo != null && sessionScope.sessionKeyUserInfo.isTourGuide == 1}">
            <a href="javascript:void(0);" onclick="goWritBlog()" class="goto_post"><i class="sprite icon_pencil"></i>发帖</a>
        </c:if>

    </div>

    <div class="travel_diary_list">
        <noscript id="article_zhuaqu_id">${article.content}</noscript>
            <div class="item" id="article_info_item_id">

            </div>

        <div class="share_btns">分享至：
            <!-- JiaThis Button BEGIN -->
            <div class="jiathis_style_24x24">
                <a class="jiathis_button_qzone"></a>
                <a class="jiathis_button_tsina"></a>
                <a class="jiathis_button_tqq"></a>
                <a class="jiathis_button_weixin"></a>
                <a class="jiathis_button_renren"></a>
                <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
            </div>
            <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
            <!-- JiaThis Button END -->
        </div>
        <!-- item -->
        <div id="article_review_item_id">

        </div>
        <!-- item -->

    </div>
    <div class="footer_pages" >
        <div class="pages" id="reviewsPageId">

        </div>
    </div>

    <form action="/portal/articleInfo/articleReview.html" method="post" name="reply_post" class="reply_post"
          id="reply_form_id" enctype="multipart/form-data">
        <input type="hidden" name="articleId" value="${id}" id="articleId"/>
        <div class="author_info left" id="reply_left_div_id">
            <c:choose>
                <c:when test="${sessionScope.sessionKeyUserInfo.headImg != null && sessionScope.sessionKeyUserInfo.headImg != ''}">
                    <div class="img pie"><img src="${sessionScope.sessionKeyUserInfo.headImg}" alt="" class="pie"></div>
                </c:when>
                <c:otherwise>
                    <div class="img pie"><img src="/images/default_header.png" alt="" class="pie"></div>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="right">
            <textarea class="layui-textarea layui-hide" placeholder="我要自问自答" name="reviewContent" id="review_content_id" style="display: none">
            </textarea>
            <div class="form_item"><input type="submit" name="reply_btn" value="回复" class="reply_btn pie" id="review_submit_id"></div>
        </div>
    </form>

</div>
<!-- main content end -->
<!-- footer -->
<jsp:include page="/portal/portlet/footer.html"/>

<!-- 文章详情信息模板 -->
<script id="atricleInfo-template" type="text/html">
    <div class="left author_info">
        <div class="img pie">
            {{#if headImg}}
                <img src="{{headImg}}" class="pie">
            {{else}}
                <img src="/images/default_header.png" class="pie">
            {{/if}}
        </div>
        <div class="guide_id">
            {{#if userName}}
            {{userName}}
            {{else}}
            {{mobileNo}}
            {{/if}}
            </div>
        <span class="poster">楼主</span>
        <div class="level"><i class="medal"></i>{{pointGradeTitle}}</div>
        <ul class="info_list">
            <li>
                <div class="num">{{articleCount}}</div>
                <div class="type">帖子</div>
            </li>
            <li>
                <div class="num">{{integral}}</div>
                <div class="type">积分</div>
            </li>
        </ul>
        {{#if medalCodes}}
            <div class="honor">
                <div class="t">荣誉勋章</div>
                <ul class="honor_medals">
                    {{#each medalCodes}}
                        <li><img src="/images/medal/{{this}}.png" alt=""><span class="title">最佳口才勋章</span></li>
                    {{/each}}
                </ul>
            </div>
        {{/if}}
    </div>
    <div class="right diary_con">
        {{#if article.stampCode}}
            {{#myif article.stampCode '!=' ''}}
                <div class="stamp"><img src="/images/stamp/{{article.stampCode}}_big.png" alt=""></div>
            {{/myif}}
        {{/if}}
        <div class="title">
            {{#myif article.upStatus '==' '1'}}
                <span class="stick_top">置顶</span>
            {{/myif}}
            {{#myif article.essenceStatus '==' '1'}}
                <span class="stick_great">精</span>
            {{/myif}}
            {{article.title}}
            {{#myif isModerator '==' 1}}
                {{#myif moderatorPermission '==' article.articleType}}
                    <div class="set_stamp">
                        <span class="set_stamp_btn">设置图章</span>
                        <ul class="set_stamp_list pie">
                            <i class="arrow_manage"></i>
                            {{#each stampCodes}}
                                <li><a href="javascript:void(0);" onclick="setStampCode('{{../article.id}}','{{code}}')" class="handsome">{{message}}</a></li>
                            {{/each}}
                        </ul>
                    </div>
                    <div class="manage">
                        <span class="manage_blogs">管理</span>
                        <ul class="manage_list pie">
                            <i class="arrow_manage"></i>
                            <li>
                                {{#myif article.upStatus '==' '1'}}
                                <a href="javascript:void(0);" onclick="up('{{article.id}}',0)"
                                   class="stick_up">取消置顶</a>
                                {{else}}
                                <a href="javascript:void(0);" onclick="up('{{article.id}}',1)"
                                   class="stick_up">置顶</a>
                                {{/myif}}
                            </li>
                            <li>
                                {{#myif article.essenceStatus '==' '1'}}
                                <a href="javascript:void(0);" onclick="essence('{{article.id}}',0)"
                                   class="cancel_stick_great">取消加精</a>
                                {{else}}
                                <a href="javascript:void(0);" onclick="essence('{{article.id}}',1)"
                                   class="cancel_stick_great">加精</a>
                                {{/myif}}
                            </li>
                            <li><a href="javascript:void(0);" onclick="deleteArticle('{{article.id}}')" class="delete_blog">删除</a></li>
                            <li><a href="javascript:void(0);" onclick="moveArticle('{{article.id}}')" class="move_blog">移动</a></li>
                        </ul>
                    </div>
                {{/myif}}
                {{#myif moderatorPermission '==' 'all'}}
                <div class="set_stamp">
                    <span class="set_stamp_btn">设置图章</span>
                    <ul class="set_stamp_list pie">
                        <i class="arrow_manage"></i>
                        {{#each stampCodes}}
                            <li><a href="javascript:void(0);" onclick="setStampCode('{{../article.id}}','{{code}}')" class="handsome">{{message}}</a></li>
                        {{/each}}
                    </ul>
                </div>
                <div class="manage">
                    <span class="manage_blogs">管理</span>
                    <ul class="manage_list pie">
                        <i class="arrow_manage"></i>
                        <li>
                            {{#myif article.upStatus '==' '1'}}
                            <a href="javascript:void(0);" onclick="up('{{article.id}}',0)"
                               class="stick_up">取消置顶</a>
                            {{else}}
                            <a href="javascript:void(0);" onclick="up('{{article.id}}',1)"
                               class="stick_up">置顶</a>
                            {{/myif}}
                        </li>
                        <li>
                            {{#myif article.essenceStatus '==' '1'}}
                            <a href="javascript:void(0);" onclick="essence('{{article.id}}',0)"
                               class="cancel_stick_great">取消加精</a>
                            {{else}}
                            <a href="javascript:void(0);" onclick="essence('{{article.id}}',1)"
                               class="cancel_stick_great">加精</a>
                            {{/myif}}
                        </li>
                        <li><a href="javascript:void(0);" onclick="deleteArticle('{{article.id}}')" class="delete_blog">删除</a></li>
                        <li><a href="javascript:void(0);" onclick="moveArticle('{{article.id}}')" class="move_blog">移动</a></li>
                    </ul>
                </div>
                {{/myif}}
            {{/myif}}
        </div>
        <div class="info">
            <span class="date">发表于：{{article.createTime}} </span>
            <div class="r">
                <span class="re"><i class="sprite icon_dial_bl"></i>{{articleCount}}</span>
                <span class="view"><i class="sprite icon_eye_bl"></i>{{article.readCount}}</span>
            </div>
        </div>
        <div class="con">
            <p>{{{article.content}}}</p>
        </div>
        <div class="ac">
                <a href="javascript:void(0);" onclick="onlyReward('{{article.userId}}','{{article.id}}','article')" class="blog_award"><i class="icon"></i><span class="word">打赏</span></a>
                <div class="award_info">{{article.rewardCount}}人共打赏{{article.rewardTotalAmount}}积分</div>
                <div class="award_pepole">
                    <ul class="award_list">
                        {{#each articleRewardLogDtoList}}
                            {{#if headImg}}
                                <li><a href="#"><img src="{{headImg}}" alt=""></a></li>
                            {{else}}
                                <li><a href="#"><img src="/images/icon_header.png" alt=""></a></li>
                            {{/if}}
                            {{#myif @index '==' 46}}
                                <li><a href="#"><img src="/images/icon_ellip.png" alt=""></a></li>
                            {{/myif}}

                        {{/each}}
                    </ul>
                </div>
        </div>
        <div class="tool_favor">
            {{#myif havaEnshrine '==' 1}}
                <a href="javascript:void(0);" onclick="enshrine('${id}')" class="favor on" id="enshrine_id"><i class="icon"></i><span class="name">收藏</span><span class="num"><div id="enshrine_num_id">{{article.enshrineCount}}</div></span></a>
            {{else}}
                 <a href="javascript:void(0);" onclick="enshrine('${id}')" class="favor" id="enshrine_id"><i class="icon"></i><span class="name">收藏</span><span class="num"><div id="enshrine_num_id">{{article.enshrineCount}}</div></span></a>
            {{/myif}}

            {{#myif havaPraise '==' 1}}
                <a href="javascript:void(0);" onclick="likeHeart('${id}')" class="like on" id="like_heart_id"><i class="icon"></i><span class="name">喜欢</span><span class="num" ><div id="like_heart_num_id">{{article.praiseCount}}</div></span></a>
            {{else}}
                <a href="javascript:void(0);" onclick="likeHeart('${id}')" class="like" id="like_heart_id"><i class="icon"></i><span class="name">喜欢</span><span class="num"><div id="like_heart_num_id">{{article.praiseCount}}</div></span></a>
            {{/myif}}
        </div>
    </div>
</script>

<!-- 文章回复信息模板 -->
<script id="atricleReview-template" type="text/html">
    {{#each rows}}
    <div class="item">

    <div class="left author_info">
        <div class="img pie">
            {{#if headImg}}
                <img src="{{headImg}}" class="pie">
            {{else}}
                <img src="/images/default_header.png" class="pie">
            {{/if}}
        </div>
        <div class="guide_id">
            {{#if userName}}
                {{userName}}
            {{else}}
                {{mobileNo}}
            {{/if}}
        </div>
        <div class="level"><i class="medal"></i>
            {{pointGradeTitle}}

        </div>
        <ul class="info_list">
            <li>
                <div class="num">{{articleCount}}</div>
                <div class="type">帖子</div>
            </li>
            <li>
                <div class="num">{{integral}}</div>
                <div class="type">积分</div>
            </li>
        </ul>
    </div>

    <div class="right diary_con">
        <div class="info">
            <span class="date">发表于：{{articleReview.createTime}} </span>
        </div>
        {{#myif articleReview.shieldStatus '==' 1}}
            <c:choose>
                <c:when test="${sessionScope.sessionKeyUserInfo.isModerator != 1}">
                    <div class="locked_con">
                        <p>该评论已被删除</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="con">
                        <p>
                            {{{articleReview.content}}}
                        </p>
                    </div>
                    <div class="reply_col">
                        <a href="javascript:void(0);" onclick="showReplyContent('{{articleReview.id}}')" class="btn_reply"><i class="sprite icon_dial_bl"></i>回复(<span class="reply_num">{{articleReview.replyCount}}</span>)</a>
                        <a href="javascript:void(0);" onclick="onlyReward('{{articleReview.reviewUserId}}','{{articleReview.id}}','review')" class="btn_award">打赏</a>
                    </div>

                    <div class="reply_content" id="reply_content_id_{{articleReview.id}}">
                        <form action="#" method="post" name="simple_reply" class="simple_reply">
                    <span class="img_head pie">
                            <c:choose>
                                <c:when test="${sessionScope.sessionKeyUserInfo.headImg != null && sessionScope.sessionKeyUserInfo.headImg != ''}">
                                    <img src="${sessionScope.sessionKeyUserInfo.headImg}" class="pie">
                                </c:when>
                                <c:otherwise>
                                    <img src="/images/default_header.png" class="pie">
                                </c:otherwise>
                            </c:choose>
                    </span>
                            <div class="text pie"><textarea name="sreply_con" placeholder="我也说两句" class="sreply_con" id="reply_review_content_id_{{articleReview.id}}"></textarea></div>
                            <input type="button" name="simple_reply_btn" class="uni_btn" value="回复" onclick="replyReview('${id}','{{articleReview.id}}')">
                        </form>
                        <ul class="reply_list">
                            {{#each articleReview.articleReviewChildDtoList}}
                            <li>
                                <div class="top">
                                <span class="name">{{#if userName}}{{userName}}{{else}}{{mobileNo}}{{/if}}：</span>
                                    <div class="text">{{content}}</div>
                                </div>
                                <div class="f"><span class="date">{{createTime}}</span></div>
                            </li>
                            {{/each}}
                        </ul>
                        <a href="javascript:void(0);" onclick="hideReplyContent('{{articleReview.id}}')" class="hide_reply_list"></a>
                    </div>
                    <c:if test="${sessionScope.sessionKeyUserInfo.isModerator == 1}">
                        <div class="reply_tools"><a href="javascript:void(0);" onclick="setShield('{{articleReview.id}}',0)" class="tool_lock">取消屏蔽</a></div>
                    </c:if>
                </c:otherwise>
            </c:choose>
        {{else}}
            <div class="con">
                <p>
                    {{{articleReview.content}}}
                </p>
            </div>
            <div class="reply_col">
                <a href="javascript:void(0);" onclick="showReplyContent('{{articleReview.id}}')" class="btn_reply"><i class="sprite icon_dial_bl"></i>回复(<span class="reply_num">{{articleReview.replyCount}}</span>)</a>
                <a href="javascript:void(0);" onclick="onlyReward('{{articleReview.reviewUserId}}','{{articleReview.id}}','review')" class="btn_award">打赏</a>
            </div>

            <div class="reply_content" id="reply_content_id_{{articleReview.id}}">
                <form action="#" method="post" name="simple_reply" class="simple_reply">
                        <span class="img_head pie">
                                <c:choose>
                            <c:when test="${sessionScope.sessionKeyUserInfo.headImg != null && sessionScope.sessionKeyUserInfo.headImg != ''}">
                                <img src="${sessionScope.sessionKeyUserInfo.headImg}" class="pie">
                            </c:when>
                            <c:otherwise>
                                <img src="/images/default_header.png" class="pie">
                            </c:otherwise>
                        </c:choose>
                        </span>
                    <div class="text pie"><textarea name="sreply_con" placeholder="我也说两句" class="sreply_con" id="reply_review_content_id_{{articleReview.id}}"></textarea></div>
                    <input type="button" name="simple_reply_btn" class="uni_btn" value="回复" onclick="replyReview('${id}','{{articleReview.id}}')">
                </form>
                <ul class="reply_list">
                    {{#each articleReview.articleReviewChildDtoList}}
                    <li>
                        <div class="top">
                                    <span class="name">
                                        {{#if userName}}
                                            {{userName}}
                                        {{else}}
                                            {{mobileNo}}
                                        {{/if}}
                                        ：</span>
                            <div class="text">{{content}}</div>
                        </div>
                        <div class="f"><span class="date">{{createTime}}</span></div>
                    </li>
                    {{/each}}
                </ul>
                <a href="javascript:void(0);" onclick="hideReplyContent('{{articleReview.id}}')" class="hide_reply_list"></a>
            </div>
            <c:if test="${sessionScope.sessionKeyUserInfo.isModerator == 1}">
                <div class="reply_tools"><a href="javascript:void(0);" onclick="setShield('{{articleReview.id}}',1)" class="tool_lock">设置屏蔽</a></div>
            </c:if>
       {{/myif}}
    </div>
    </div>
    {{/each}}
    <!-- item -->
</script>
<script type="text/javascript" src="/js/platformjs/article_info.js"></script>
<script src="/plugins/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use('layedit', function () {
        var layedit = layui.layedit;
        //自定义工具栏
        var index = layedit.build('review_content_id', {
            tool: ['strong', 'italic', 'underline', 'del', '|', 'left', 'center', 'right', '|', 'face', 'link', 'unlink']
            , height: 150
        });
        $('#review_submit_id').on('click', function(){
            $('#review_content_id').val(layedit.getContent(index));
        });
    });

    function goWritBlog() {
        if (${sessionScope.sessionKeyUserInfo.isShutup==1}) {
            $.platform.simplemsg("您已被禁言，请联系管理员！", 1000, 5);
            return;
        }
        window.location.href = "/portal/services/guideCenter/writeBlog.html";
    }
</script>
</body>
