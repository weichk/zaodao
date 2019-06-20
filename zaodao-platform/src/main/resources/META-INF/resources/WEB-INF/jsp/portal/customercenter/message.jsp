<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/portal/common/taglibs.jsp" %>
<!-- page: 我的消息 -->

<div class="my_message page_wrap">

    <div class="mslide_tabs">
        <ul class="nav">
            <li><a class="current" href="javascript:void(0);" onclick="getSysMessageList(1, 1)">系统消息</a>
                <c:if test="${notReadCusMesCount>0}">
                    <i class="unread_count">${notReadCusMesCount}</i>
                </c:if>
            </li>
            <li><a href="javascript:void(0);" onclick="getReplyMessageList(1, 1)">我的回复</a>
                <c:if test="${notReadArticleReviewCount>0}">
                    <i class="unread_count">${notReadArticleReviewCount}</i>
                </c:if>
            </li>
            <c:if test="${sessionScope.sessionKeyUserInfo.isTourGuide == 1}">
                <li><a href="javascript:void(0);" onclick="getLeaveMessageList(1,1)">我的留言</a>
                    <c:if test="${notReadLeaveMesCount>0}">
                        <i class="unread_count">${notReadLeaveMesCount}</i>
                    </c:if>
                </li>
            </c:if>

        </ul>
        <span class="m_indicator"></span>
    </div>

    <div class="mslide_content">

        <div class="item" id="sys_message_list_id">

        </div>
        <!-- item -->

        <div class="item" id="reply_message_list_id">

        </div>
        <!-- item -->

        <div class="item" id="leave_message_list_id">

        </div>
        <!-- item -->
    </div>

</div>
<!-- 系统消息模板-->
<script id="sysMessage-template" type="text/html">
    <ul class="message_list">
        {{#each rows}}
        <li>
            <div class="head pie"><i class="sprite icon_bell_bl"></i></div>
            <div class="con">
                <div class="t">{{messageTitle}}<span class="date">{{createTime}}</span></div>
                <p>{{{message}}}</p>
            </div>
            <div class="tools">
                <a href="javascript:void(0);" onclick="isDeleteMessage('sysMessage','{{id}}')" class="del_msg single">删除消息</a>
            </div>
        </li>
        {{/each}}
    </ul>
    <div class="pages" id="sysMessagePageId">

    </div>
</script>

<!-- 我的回复模板-->
<script id="replyMessage-template" type="text/html">
    <ul class="message_list">
        {{#each rows}}
        <li>
            <div class="head pie"><img src="{{headImg}}" alt="" class="pie"></div>
            <div class="con">
                <div class="t">{{userName}}<span class="date">{{createTime}}</span></div>
                <p>在<a href="/portal/guideHome/getArticleInfo_{{articleId}}.html" class="link">《{{articleTitle}}》</a>回复了你
                </p>
            </div>
            <div class="tools">
                <a href="/portal/guideHome/getArticleInfo_{{articleId}}.html" class="readit uni_btn pie">查看详情</a>
                <a href="javascript:void(0);" onclick="isDeleteMessage('replyMessage','{{replyMessageId}}')"
                   class="del_msg">删除消息</a>
            </div>
        </li>
        {{/each}}
    </ul>
    <div class="pages" id="replyMessagePageId">

    </div>
</script>

<!-- 我的留言模板-->
<script id="leaveMessage-template" type="text/html">
    <ul class="leave_word_list">
        {{#each rows}}
        <li>
            <div class="my_words">
                <div class="head pie"><img src="{{headImg}}" alt="" class="pie"></div>
                <div class="con">
                    <div class="t">
                        {{#if realName}}
                        {{realName}}
                        {{else}}
                        {{mobileNo}}
                        {{/if}}
                        <span class="date">{{createTime}}</span>
                    </div>
                    <p>{{content}}</p>
                    <a href="javascript:void(0);" class="btn_reply" onclick="btnReplay(this)">回复</a>
                </div>
            </div>
            <form action="#" name="leave_word_form" method="post" class="leave_word_form">
                <div class="form_item"><textarea name="leave_word_content" placeholder="我来说两句"
                                                 class="leave_word_content" id="repay_content_{{messageId}}"></textarea>
                </div>
                <div class="form_btns"><a href="javascript:void(0);" class="cancel_leave_word"
                                          onclick="cancelLeaveWord(this)">取消</a><input type="button" value="确定"
                                                                                       class="uni_btn pie"
                                                                                       onclick="replyLeaveMessage('{{messageId}}','{{leaveCusUserId}}')">
                </div>
            </form>
            <div class="guide_reply">
                {{#each leaveMessageReplys}}
                <div class="t">【我的回复】<span class="date">{{createTime}}</span></div>
                <p>{{replyContent}}</p>
                {{/each}}
            </div>
        </li>
        {{/each}}
    </ul>
    <div class="pages" id="leaveMessagePageId">

    </div>
</script>
<script type="text/javascript" src="/js/platformjs/message.js" charset="utf-8"></script>