<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.gzsxt.cn/theme/hplus/article.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>项目管理系统 - 文章页面</title>
    <meta name="keywords" content="项目管理系统">
    <meta name="description" content="项目管理系统">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight article">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <div class="ibox">
                    <div class="ibox-content">
                    		<div class="pull-right">
                            <a href="/notice/list" class="btn btn-white btn-sm" ><i class="fa fa-reply"></i>  返回</a>
                        </div>
                        <div class="text-center  article-title">
                            <h2><strong>${notice.title}</strong></h2>
                        </div>
                        <div id="">
                            ${notice.remark}
                        </div>
                        <hr>


                        <hr> 
                        <div class="row text-center">
                        <a href="/notice/list" class="btn btn-white btn-sm" ><i class="fa fa-reply"></i> 返回</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/content.min.js?v=1.0.0"></script>

<%--    ${notice.remark.replaceAll('\\<[^\\u4E00-\\u9FA5]+\\>', '')}--%>
<%--    ${notice.remark.replaceAll('\\<img[^\\u4E00-\\u9FA5]+\\>', '<i class="fa fa-picture-o"></i>')}--%>
<script type="text/javascript">


</script>
</body>


</html>
