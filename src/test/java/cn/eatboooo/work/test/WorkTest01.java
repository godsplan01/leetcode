/**
 * @projectName leetcode
 * @package cn.eatboooo.work.test
 * @className cn.eatboooo.work.test.WorkTest01
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package cn.eatboooo.work.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

/**
 * WorkTest01
 * @description thunisoft华宇
 * @author weiZhiLin
 * @date 2021/7/6 10:08
 * @version 1.0
 */
public class WorkTest01 {

    // 下划线分隔的单词转驼峰命名
    @Test
    void test01() {
        Map<String, Object> nao = new HashMap<>();
        Map<String, Object> nao2 = new HashMap<>();
        ArrayList<Map> objects = new ArrayList<>();
        objects.add(nao);
        objects.add(nao2);
        nao.put("abc_cca", new HashMap<>());
        nao.put("asdCCd", new HashMap<>());
        nao.put("abcdsd_cca", new HashMap<>());
        nao.put("abac_wccsaa", new HashMap<>());
        nao2.put("abc_cca", new HashMap<>());
        nao2.put("asdCCd", new HashMap<>());
        nao2.put("abcdsd_cca", new HashMap<>());
        nao2.put("abac_wccsaa", new HashMap<>());
        toHumpName(objects);
        for (Map object : objects) {
            Set<String> strings = object.keySet();
            for (String string : strings) {
                System.out.println(object.hashCode() + "string = " + string);
            }
        }

    }

    private void toHumpName(List<Map> content) {
        List<Map> arrayList = new ArrayList();
        for (int i = 0; i < content.size(); i++) {
            Map map = content.get(i);
            Map<String, Object> rmap = new HashMap<>();
            Set set = map.keySet();
            for (Object o : set) {
                String s = String.valueOf(o);
                if (s.contains("_")) {
                    String humpName = toHumpNameProcess(s);
                    rmap.put(humpName, map.get(s));
                } else {
                    rmap.put(s, map.get(s));
                }
            }
            arrayList.add(rmap);
            content.remove(i--);
        }
        content.addAll(arrayList);
    }

    private String toHumpNameProcess(String s) {
        String[] s1 = s.split("_");
        if (s1.length < 2) {
            return s1[0];
        }
        for (int i = 1; i < s1.length; i++) {
            String s2 = s1[i];
            s1[0] += s2.substring(0, 1).toUpperCase() + s2.substring(1);
        }
        return s1[0];
    }

    // 字符串分隔
    @Test
    void test02() {
        String[] split = "year\\文书类\\永久".split("\\\\");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            System.out.println("s = " + s);
        }

    }

    // if (a == (Integer) 1 && a == (Integer) 2 && a == (Integer) 3) 是否可能成立
    @Test
    void test03() {
        Class cache = Integer.class.getDeclaredClasses()[0];
        Field c = null;
        try {
            c = cache.getDeclaredField("cache");
            c.setAccessible(true);
            Integer[] array = (Integer[]) c.get(cache);
            // array[129] is 1
            array[130] = array[129];
            // Set 2 to be 1
            array[131] = array[129];
            // Set 3 to be 1
            Integer a = 1;
            if (a == (Integer) 1 && a == (Integer) 2 && a == (Integer) 3) {
                System.out.println("Success");
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // html 解析，替换某个 url 为指定 url
    @Test
    void test04() {
        String html = "<!DOCTYPE HTML> <html lang=\"zh-CN\"> <head> <meta charset=\"utf-8\"> <meta name=\"context-path\"/> <title>视频测试</title> <style> .modal-open .modal { overflow: initial; } article ol, article ul, .ez-footer ul, .ez-footer ol { padding-left: 20px !important; } .no-name { line-height: 3px; } .dropdown-file-list.downAll { text-align: center; border-bottom: none; display: block; } .template-one .comment-number { cursor: pointer; } .textarea { resize: none; } .template-one .comments-box { border: 1px solid #f2f2f2; height: 100%; width: 350px; position: absolute; right: 10px; top: 0px; background-color: #fff; z-index: 10; overflow: auto; display: none; } .template-one .comments-header { height: 30px; background-color: #f2f2f2; } .template-one .comments-header > .fa-angle-double-right { font-size: 20px; margin-left: 10px; cursor: pointer; } .template-one .btn-comment { position: relative; left: -14px; } .template-one .anonymous { position: relative; left: 0px; } .template-one .template-expression, .template-one .template-expressions { position: relative; } .template-one .template-expression > div { position: absolute; top: -40px; left: 0; } .template-one .template-expressions > div { position: absolute; left: 16%; top: -50px; } .template-one .preview-picture > img { display: none; } .template-one .look { position: absolute; top: 3%; right: 15%; } .label { font-size: 95%; } .template-one .summary-well { background: #f7f7f7; } .template-one .summary-well > p { margin-top: 10px; margin: 10px; padding-top: 10px; } .dropdown-menu { max-width: 400px; min-height: 50px !important; padding-bottom: 5px; } .dropdown-file-list { line-height: 42px; padding: 0 16px; overflow: hidden; border-bottom: 1px solid #e8e8e8; display: flex; } .dropdown-file-list .dropdown-file-display { flex: 1; display: block; float: left; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; } .dropdown-file-list .dropdown-file-download { display: block; float: right; vertical-align: middle; clear: none; padding: 0; line-height: 42px; } .dropdown-file-list .dropdown-file-view { display: block; float: right; width: auto; clear: none; padding: 0; line-height: 42px; margin-right: 5px; } .comment { padding: 10px 0 5px 0; } .column-article table.noBorderTable td, table.noBorderTable th, table.noBorderTable caption { border: 1px solid #ddd !important } .column-article table { margin-bottom: 10px; border-collapse: collapse; display: table; } .column-article td, .column-article th { padding: 5px 10px; border: 1px solid #DDD; } .column-article caption { border: 1px solid #DDD; border-bottom: 0; padding: 3px; text-align: center; } .column-article th { border-top: 1px solid #BBB; background-color: #F7F7F7; } .column-article table tr.firstRow th { border-top-width: 2px; } .column-article .ue-table-interlace-color-single { background-color: #fcfcfc; } .column-article .ue-table-interlace-color-double { background-color: #f7faff; } .column-article td p { margin: 0; padding: 0; } .attach-dropdown .dropdown-menu { min-width: 400px; color: #999; } .dropdown-menu > .dropdown-file-list > a { color: #1b6094; } .jp-video .jp-full-screen { margin-left: 0px; } .email-preview-attachment-head .attachment-head-name { font-weight: 400; } .meta-top { border-bottom: 0; line-height: 14px; } .fd-detail-title{ font-size: 28px; font-weight: normal; font-stretch: normal; line-height: 50px; letter-spacing: 1px; color: #282828; margin-bottom: 4px; } .fd-detail-subtitle{ margin-top: 0; margin-bottom: 4px; } </style> <link href=\"plugins/face/style.css\" rel=\"stylesheet\"> <script src=\"common/components/goldgrid/js/jquery.goldgrid.js\"></script> </head> <body> <div style=\"background: #eceef1;min-height: 100vh;\"> <div style=\"background: #fff;padding:0 16px;min-height: 100vh;\" class=\"container website-infoview-content\"> <article class=\"article template-one mb-0\" id=\"info_view_article\"> <div class=\"clearfix mt-15 mb-15\"> <div class=\"inline-block pull-left\"> <span>默认单位>内部期刊>拉萨信息>详情</span> </div> <div class=\"inline-block pull-right\"> <div class=\"inline-block pull-right\"> </div> </div> </div> <div class=\"line\"></div> <header> <h3 class=\"fd-detail-title text-center\"> <strong>视频测试</strong> </h3> <h4 class=\"fd-detail-subtitle text-center\"></h4> <div class=\"meta-top clearfix\"> <ul class=\"list-inline text-center text-sm mb-5\"> <li> <time>2021-07-19 14:24</time> </li> <li class=\"single-overflow\" style=\"max-width: 200px;\" data-toggle=\"tooltip\" title=\"默认单位\">来源：<span>默认单位</span></li> <li>作者：<span>李某人</span></li> </ul> <!-- <div class=\"dropdown pull-right attach-dropdown mb-5\">--> <!-- <ul class=\"list-inline text-sm\">--> <!-- <li th:if=\"${isLike== 0 && columnEntity.allowLike == 1}\"><a style=\"color:#999\" class=\"fa fa-thumbs-o-up\" onclick=\"changeLike(this)\" data-value=\"1\"></a>&nbsp;&nbsp;<span id=\"isNotLike\" th:text=\"${likeNum}\"></span></li>--> <!-- <li th:if=\"${isLike== 1 && columnEntity.allowLike == 1}\"><a class=\"fa fa-thumbs-up\" onclick=\"changeLike(this)\" data-value=\"2\">&nbsp;&nbsp;<span id=\"isLike\" th:text=\"${likeNum}\"></span></a></li>--> <!-- <li th:if=\"${entity != null}\" onclick=\"getReadList(this)\"><a>点击：<span id=\"kits\" th:attr=\"kitsValue = ${entity.kits !=null? entity.kits : ''}\" th:text=\"${entity.kits !=null? entity.kits : ''}\">1</span></a></li>--> <!-- <li th:if=\"${entity == null}\" >点击：<span >1</span></li>--> <!-- <li th:if=\"${entity != null&&entity.allowComment==1}\" id=\"comment_num_li\" class=\"text-deepblue\"><a onclick=\"getLocation('comments')\">评论：<span id=\"comment_num\" >0</span></a></li>--> <!-- </ul>--> <!-- </div>--> </div> </header> <section> <article class=\"column-article\" id=\"content\"><p><video class=\"edui-upload-video video-js vjs-default-skin video-js\" controls=\"\" preload=\"auto\" width=\"800\" height=\"600\" src=\"http://172.25.17.118:8088/flexbase/attachment/video?fid=2c9991f67a98f3fe017abd702901576e&chksum=9f1023e6933ced5889c162be2e43ae1b\" data-setup=\"{}\"><source src=\"http://172.25.17.118:8088/flexbase/attachment/video?fid=2c9991f67a98f3fe017abd702901576e&chksum=9f1023e6933ced5889c162be2e43ae1b\" type=\"video/mp4\"/></video>&nbsp;</p></article> <!-- <div th:if=\"${entity != null && entity.imgId != null && imgList != null && imgList.size()>0}\">--> <!-- <ul aria-labelledby=\"tool-sort\">--> <!-- <li th:each=\"attachment,attachmentStat : ${imgList}\" th:if=\"${attachment != null}\" th:data-id=\"${attachment.id}\" th:data-num=\"${attachment.checksum}\" class=\"mt-10\">--> <!-- <img th:src=\"${flexServerPath + attachment.previewUrl}\" title=\"\" alt=\"\" style=\"max-width:920px;\">--> <!-- </li>--> <!-- </ul>--> <!-- </div>--> </section> <footer class=\"text-sm mt-20\"> <div class=\"line mb-5\"></div> <!-- <div class=\"inline-block pull-left mt-5\" style=\"color:#999;\" >--> <!-- <p th:if=\"${entity == null}\" class='text text-muted'>标签：<span></span></p>--> <!-- <p class=\"single-overflow\" style=\"max-width: 320px;\" data-toggle=\"tooltip\" th:if=\"${entity != null&&entity.tag != null}\" th:title=\"${entity.tag}\">标签：<span th:each=\"it,userStat:${entity == null ? '' : (entity.tag == null ? '' : entity.tag.split('；'))}\" th:switch=\"${new java.util.Random().nextInt(5)}\"><span th:if=\"${it!=''&&it!=null&&userStat.index<5&&userStat.index<userStat.size-1}\" th:text=\"${it}+'、'\" >标签</span><span th:if=\"${it!=''&&it!=null&&(userStat.index==5||userStat.index==userStat.size-1)}\" th:text=\"${it}\">标签</span></span></p>--> <!-- </div>--> <!-- <div class=\"inline-block pull-right mt-5\" style=\"color:#999;\">--> <!-- <p th:if=\"${entity == null}\" class='text' >来源：<span></span></p>--> <!-- <p th:if=\"${entity != null && entity.source!=null}\" class=\"text single-overflow\" style=\"max-width: 320px;\" data-toggle=\"tooltip\" th:title=\"${entity.source}\">来源：<span th:utext=\"${entity.source}\"></span></p>--> <!-- </div>--> <!-- <div class=\"meta-bottom clearfix\" style=\"border: none\">--> <!-- <p style=\"text-align: center\">--> <!-- <a id=\"preViewBtn\"--> <!-- th:if=\"${entity != null && entity.allowPrint eq 1 }\"--> <!-- href=\"javascript:printContent();\"--> <!-- class=\"btn btn-default btn-sm print-preview\"> <i--> <!-- class=\"fa fa-print fa-fw\"></i>打印预览--> <!-- </a>--> <!-- </p>--> <!-- </div>--> </footer> </article> <div id=\"comments\"> <h4 id=\"comment_num_h4\"></h4> <ul class=\"media-list comments-list\" id=\"comment_ul\"> </ul> </div> </div> </div> </body> <!-- 默认模板一结束 --> <script> var flexServerPath = EZUI.common.$basePath; var cmsflexOfficePath = EZUI.common.$officePath; //内容id var infoId = \"c91e82824f2f4e5683a72bfb48480c6b\"; //内容状态 0：草稿；1：发布 var status = 1; //是否是管理员 var isManager = true; //是否允许评论 var allowComment = \"0\"; //评论匿名 0：关闭 1：开启 var allowAnonymous = \"1\"; //内容类型 0：文本；1：html；2：md；3：图片；4：视频 var contentType = 1; //当前用户id var currentUserId = \"ff80808175ab19df0175ab87746f0459\"; var createdBy = \"ff80808175ab19df0175ab87746f0459\"; //当前用户头像 var currentUserImg = \"http:\\/\\/172.25.17.118:8088\\/flexbase\\/attachment\\/image?fid=2c9991f6797e14d10179825ba680195b\\u0026chksum=034865f3b82c609cb03c2765f55aec7e\"; var currentUserName = \"\\u674E\\u67D0\\u4EBA\"; var orgName = null if (!currentUserImg) { currentUserImg = EZUI.common.$officePagePath + '/web/dist/fui/images/unknow.png'; } else { if (currentUserImg == 'name') { currentUserImg = createTextImage(currentUserName); } else { currentUserImg = CheckUrl(currentUserImg) ? currentUserImg : (EZUI.common.$officePagePath + '/' + currentUserImg); } } var attachmentList = []; if (attachmentList) { for (var i = 0; i < attachmentList.length; i++) { var fileIcon = $.fn.ezUploader.getFileIcon(attachmentList[i].fileName); $(\"#fileIcon\" + i).html(fileIcon); } } var allowCopy = \"1\"; if (allowCopy != '1') { $(\"#info_view_article\").css(\"user-select\", \"none\"); } var currentUserName = \"\\u674E\\u67D0\\u4EBA\"; //已发布内容显示评论 loadScript(EZUI.common.$officePagePath + \"/web/dist/modules/info/js/infoView.js\", function () { if (allowComment != '1') { $(\".comment-number\").hide(); } if (status == '1') { //updateInfoKits(); if (allowComment == '1') { initComment(); } } var contFileType = null; if (contFileType == 'video') { var fileName = \"\"; var fileUrl = \"\"; pagePreviewVideo(fileName, fileUrl) } if ($(\"#docIframe\")) { setTimeout(function () { $(\"#docIframe\").css('width', '90%'); $(\"#docIframe\").css('height', '1000px'); }, 300); } }); //初始化md内容显示 if (contentType == '2') { //initMdView(); } else if (contentType == '1') { //处理跨域 $(\"#content\").find(\".ueditor_file_link\").each(function () { var href = $(this).attr('href'); $(this).attr('href', href + \"&token=\" + FlexCommon.getToken()); }); } var isDing = \"0\"; if (isDing == 1) { $(\"#preViewBtn\").hide(); } //创建评论 function initComment() { var commentDiv = ''; if (allowComment == '1') { commentDiv += '<div class=\"media comment comment-area-reply no-border-top\">' + '<a class=\"media-left\" style=\"color:#fff;\">' + '<img src=\"' + currentUserImg + '\" class=\"avator-xs img-circle\" alt=\"\">' + '</a>' + '<div class=\"media-body pb-30\">' + '<div class=\"form-group\">' + '<textarea maxlength=\"500\" class=\"form-control textarea\" rows=\"3\" placeholder=\"发表评论\" id=\"comment_content\"></textarea>' + '</div>' + '<div class=\"mt-15\">' + ' <div class=\"comment_emotion pull-left\" id=\"comment_emotion\"></div>' + // '<div class=\"emotion fa fa-comments pull-left\" id=\"comment_emotion\" style=\"cursor:pointer\">表情</span>'+ '<button type=\"button\" class=\"btn btn-primary pull-right pb-6\" onclick=\"sendComment();\">发表评论</button>' + '</div>'; if (allowAnonymous == '1') { commentDiv += '<div class=\"checkbox pull-right mr-10\"><label style=\"padding-left: 10px;\"><input type=\"checkbox\" id=\"anonymous\" ><span class=\"pl-0\">匿名</span></label></div>'; } commentDiv += '<div id=\"comment_show\" style=\"display:none;\"></div>'; commentDiv += '</div>'; commentDiv += '</div>'; } else { $(\"#comment_num_li\").hide(); } $('#comment_num_h4').after(commentDiv); loadScript(EZUI.common.$officePagePath + \"/web/dist/plugins/face/jquery.face.js\", function () { //初始化表情 $('#comment_emotion').qqFace({ id: 'facebox', //表情盒子的ID assign: 'comment_content', //给那个控件赋值 path: EZUI.common.$officePagePath + '/web/dist/plugins/face/face/', //表情存放的路径 }); }); getCommentList(); } //查询评论列表 function getCommentList() { EZUI.cloud.GET(cmsflexOfficePath + \"/api/w/info/comment/findCommentList\", \"infoId=\" + infoId, function (result) { if (!result || !result.data) { layer.msg('获取评论列表失败!', { icon: 2 }) return false; } if (result.data != null) { var arrayLen = result.data.length; $('#comment_num').text(arrayLen); $('#comment_num_h4').text('评论（' + arrayLen + '）'); var addContent = createDom(result.data); var $commentUl = $('#comment_ul'); $commentUl.empty(); $commentUl.append(addContent); } }, null, {async: true}); } //创建评论内容 function createDom(dataList) { var commentLi = ''; var createrImg = ''; var keyObj; var replyArray; var createdName = ''; for (var ii = 0; ii < dataList.length; ii++) { var dataMap = dataList[ii]; if (dataMap != null) { for (var key in dataMap) { replyArray = dataMap[key]; keyObj = eval('(' + key + ')'); var createrImg = keyObj.createrImg; if (keyObj.createdBy == '1') { if (keyObj.anonymous == '1') { createdName = '匿名用户'; createrImg = EZUI.common.$officePagePath + '/web/dist/fui/images/unknow.png'; } else { createdName = \"系统管理员\"; createrImg = EZUI.common.$officePagePath + '/web/dist/fui/images/admin.png'; } } else { if (keyObj.anonymous == '1' || !keyObj.createdByName) { createrImg = EZUI.common.$officePagePath + '/web/dist/fui/images/unknow.png'; createdName = '匿名用户'; } else { createdName = keyObj.createdByName; } } if (!createrImg) { createrImg = EZUI.common.$officePagePath + '/web/dist/fui/images/unknow.png'; } else { if (createrImg == 'name') { createrImg = createTextImage(createdName); } else { createrImg = CheckUrl(createrImg) ? createrImg : (EZUI.common.$officePagePath + '/' + createrImg); } } var keyObjCont = replaceCode(decodeURIComponent(encodeURIComponent(keyObj.content))); commentLi += '<li class=\"media comment\">' + '<a class=\"media-left\" href=\"javascript:;\" style=\"color:#fff;\">' + '<img src=\"' + createrImg + '\" onerror=\"this.src=\\'' + EZUI.common.$officePagePath + '/web/dist/fui/images/unknow.png\\'\" class=\"avator-xs img-circle\" alt=\"\">' + '</a>' + '<div class=\"media-body\" data-id=\"' + keyObj.id + '\">' + '<p class=\"media-heading\"><a href=\"javascript:;\">' + createdName + '</a></p>' + '<p class=\"comment-meta\"><time>' + keyObj.createdDate + '</time></p>' + '<p>' + keyObjCont + '</p>' + '<ul class=\"list-inline comment-actions\">'; if (allowComment == '0') { commentLi += '</ul>'; } else { commentLi += '<li class=\"comment-action-reply\"><a href=\"javascript:;\" onclick=\"openReply(this,\\'' + createdName + '\\');\" data-openstatus=\"0\">回复</a></li>'; if (isManager || currentUserId == createdBy || keyObj.createdBy == currentUserId) { //作者 , 用户只能删除自己的评论,管理员可以删除任何评论 commentLi += '<li class=\"comment-action-del\"><a href=\"javascript:deleteComment(\\'' + keyObj.id + '\\');\">删除</a></li>'; } commentLi += '</ul>'; } //遍历评论的回复 for (var i = 0; i < replyArray.length; i++) { //判断用户头像和名称 var replyerImg = replyArray[i].createrImg; var replyerName = replyArray[i].createdName; if (replyArray[i].createdBy == '1') { if (replyArray[i].anonymous == '1') { replyerName = '匿名用户'; replyerImg = EZUI.common.$officePagePath + '/web/dist/fui/images/unknow.png'; } else { replyerName = \"系统管理员\"; replyerImg = EZUI.common.$officePagePath + '/web/dist/fui/images/admin.png'; } } else { if (replyArray[i].anonymous == '1' || !replyerName) { replyerImg = EZUI.common.$officePagePath + '/web/dist/fui/images/unknow.png'; replyerName = '匿名用户'; } if (!replyerImg) { replyerImg = EZUI.common.$officePagePath + '/web/dist/fui/images/unknow.png'; } else { if (replyerImg == 'name') { replyerImg = createTextImage(replyerName); } else { replyerImg = CheckUrl(replyerImg) ? replyerImg : (EZUI.common.$officePagePath + '/' + replyerImg); } } } //删除按钮 var deleteText = ''; if (allowComment == '1' && (isManager || currentUserId == createdBy || replyArray[i].createdBy == currentUserId)) { deleteText = '<ul class=\"list-inline comment-actions\"><li class=\"comment-action-del\">' + '<a href=\"javascript:deleteComment(\\'' + replyArray[i].id + '\\',\\'1\\');\">删除</a></li>' + '</li></ul>' } var replyContent = replyArray[i].content; replyContent = replaceCode(decodeURIComponent(encodeURIComponent(replyContent))); if (!(replyContent.indexOf('回复@') > -1)) { replyContent = '回复@' + createdName + ': ' + replyContent; } commentLi += '<div class=\"media comment\">' + '<a class=\"media-left\" href=\"javascript:;\" style=\"color:#fff;\">' + '<img src=\"' + replyerImg + '\" onerror=\"this.src=\\'' + EZUI.common.$officePagePath + '/web/dist/fui/images/unknow.png\\'\" class=\"avator-xs img-circle\" alt=\"\">' + '</a>' + '<div class=\"media-body\">' + '<p class=\"media-heading\"><a href=\"javascript:;\">' + replyerName + '</a>' + deleteText + '</p>' + '<p class=\"comment-meta\"><time>' + replyArray[i].createdDate + '</time></p>' + '<p>' + replyContent + '</p>' + '</div>' + '</div>'; } commentLi += '</div></li>'; } } } return commentLi; } // 删除评论 function deleteComment(id, columnType) { var msg = \"确定删除这条评论?\"; if (columnType == '1') { msg = \"确定删除这条回复?\"; } $.ezui.util.confirm(msg, {title: \"提示\"}, function () { EZUI.cloud.POST(cmsflexOfficePath + '/api/w/info/comment/delete', 'id=' + id + '&columnType=' + (columnType ? columnType : \"\"), function (result) { layer.msg('操作成功!', { icon: 1 }); getCommentList(); }) }); } // 回复输入区域对象 var num = 0; // 打开回复输入框 function openReply(obj, createdName) { var $div = $(obj).parents(\".media-body\").eq(0); // 判断当前div中是否已经存在回复输入框，存在时点击回复按钮，删除回复输入区，否则添加输入区 if ($div.find('div.comment-area-reply').length > 0) { $div.find('div.comment-area-reply').remove(); return false; } var $replyContent = $('<div class=\"media comment comment-area-reply\">' + '<a class=\"media-left\" style=\"color:#fff;\">' + '<img src=\"' + currentUserImg + '\" class=\"avator-xs img-circle\" alt=\"\">' + '</a>' + '<div class=\"media-body\">' + '<div class=\"form-group mr-5 mb-10\">' + '<textarea maxlength=\"500\" class=\"form-control reply_content textarea\" rows=\"3\" placeholder=\"发表回复\" id=\"reply_content' + num + '\"></textarea>' + '</div>' + '<div class=\"mt-5\">' + ' <div class=\"reply_emotion pull-left mt-5\" id=\"reply_emotion' + num + '\"></div>' + '<button class=\"btn btn-primary pull-right mr-5\" onclick=\"sendReply(this,' + num + ',\\'' + createdName + '\\'' + ');\">发表回复</button>' + (allowAnonymous == '1' ? '<div class=\"checkbox pull-right mr-10\"><label style=\"padding-left: 10px;\"><input type=\"checkbox\" id=\"replyAnonymous' + num + '\" ><span class=\"pl-0\">匿名</span></label></div>' : \"\") + '<div id=\"reply_show\" style=\"display:none;\"></div>' + '</div>' + '</div>' + '</div>'); $replyContent.find('textarea').val(''); $div.append($replyContent); $('#reply_emotion' + num).qqFace({ id: 'facebox' + num, //表情盒子的ID assign: 'reply_content' + num, //给那个控件赋值 path: EZUI.common.$location + 'plugins/face/face/', //表情存放的路径 }); num = num + 1; } // 回复评论的内容 function sendReply(obj, num, createdName) { var $obj = $(obj); var commentId = $obj.parents('.comment-area-reply').eq(0).parent('.media-body').data('id'); var str = $obj.parents('.comment-area-reply').eq(0).find('.reply_content').val(); var replyAnonymous = $(\"#replyAnonymous\" + num).is(\":checked\") ? 1 : 0; sendReplyFun(commentId, str, replyAnonymous, createdName); } function loadScript(url, callback) { var script = document.createElement(\"script\"); script.type = \"text/javaScript\"; if (script.readyState) {//IE script.onreadystatechange = function () { if (script.readyState == \"loaded\" || script.readyState == \"complete\") { script.onreadystatechange = null; callback && callback(); } }; } else { script.onload = function () { callback && callback(); }; } script.src = url; document.getElementById(\"info_view_article\").appendChild(script); } function CheckUrl(str_url) { var strRegex = \"[a-zA-z]+://[^\\s]*\"; var re = new RegExp(strRegex); return re.test(str_url) } </script> <script> //点击评论数量出现弹框 $(\".template-one .comment-number\").click(function () { $(\".template-one .comments-box\").show() }) $(\".comments-header .fa-angle-double-right\").click(function () { $(\".template-one .comments-box \").hide() }) </script> </html>";
        Document document = Jsoup.parse(html);
        String attr = document.select("video").attr("src");
        int i = attr.indexOf("?");
//        attr = attr.substring(attr.indexOf("?")) + "videoURL";
        System.out.println(attr);
        document.select("video").attr("src", 1 + attr);
        attr = document.select("video").attr("src");
        document.toString();
        System.out.println(document.toString());
    }


}
