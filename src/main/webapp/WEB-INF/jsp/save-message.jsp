 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.gzsxt.cn/theme/hplus/table_basic.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:01 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>项目管理系统 - 基础表格</title>
    <meta name="keywords" content="项目管理系统">
    <meta name="description" content="项目管理系统">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    	<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/select/bootstrap-select.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/customer.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
      <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>需求管理<small>>添加需求信息</small></h5>
                    </div>
                    <div class="ibox-content">
                        <form id="saveForm"  enctype="multipart/form-data" class="form-horizontal">
                       	<input name="send" type="hidden" value="${LOGIN.eid}">
                            <input id="status" name="status" type="hidden" >
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">收件人</label>
                                <div class="col-sm-4">
                                    <select id="Employee" name="receive" class="selectpicker form-control">
										<option value="0">--选择收件人---</option>

									</select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">标题</label>
                                <div class="col-sm-9">
                                    <input name="title" type="text" class="form-control" >
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">内容</label>
                                <div class="col-sm-9">
                                    <input type="hidden" name="content" id="content">
                                    <script id="editor" type="text/plain"></script>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group">
	                                <div id="file-pretty">
	                                <label class="col-sm-2 control-label">附件</label>
	                                <div class="col-sm-4">
	                                		<!---->
	                                		<input name="myfiles" type="file" class="form-control" >
	                                </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">类型</label>
                                <div class="col-sm-9 tips">
                                    <input name="type" value="0" checked  type="radio" >0工作
                                    <input name="type" value="1"  type="radio" >社交
                                    <input name="type" value="2"  type="radio" >文件
                                    <input name="type" value="3"  type="radio" >重要
                                </div>
                            </div>
                        </div>
                        
                        
                        
                        
                     	<div class="row">
                     		<div class="hr-line-dashed"></div>
                     	</div>
                          
                         <div class="row">
                            <div class="form-group">
                                <div class="col-sm-11 text-right">
                                    <button type="button" id="btnSend" class="btn btn-primary"><i class="fa fa-paper-plane"></i> 发送</button>
                                    <a href="list-message.jsp" class="btn btn-white"><i class="fa fa-times"></i> 放弃</a>
                                		<a href="list-message.jsp" class="btn btn-white"><i class="fa fa-pencil"></i> 存为草稿</a>
                                </div>   	
                            </div>
                       </div>
                       </form>
                    </div>
                </div>
            </div>
        </div>
    </div>














    
    
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/select/bootstrap-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/prettyfile/bootstrap-prettyfile.js"></script>
	<script src="${pageContext.request.contextPath}/js/inputfile.js"></script>
	
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
   <script>
	$(document).ready(function() {
		//实例化编辑器
		var ue = UE.getEditor('editor');
		// 设置按钮的样式
		$('.selectpicker').selectpicker('setStyle', 'btn-white');

		var eid=${eid};
        //加载项目信息
        function loadEmployee() {
            $.post("/employee/listAllJSON","",function (result) {
                $(result).each(function () {
                    var option="";
                    if(this.eid==eid){
                        option="<option selected value='"+this.eid+"'>"+this.ename+"</option>";
                    }else{
                        option="<option  value='"+this.eid+"'>"+this.ename+"</option>";
                    }
                    $("#Employee").append(option).selectpicker('refresh');
                });
            },"json");
        }
        loadEmployee();



        //发送消息按钮
        $("#btnSend").click(function(){
            //

            var content= UE.getEditor('editor').getContent();
            $("#content").val(content);

            $("#status").val(1);

            $.ajax({
                url: "/message/save",
                type: 'POST',
                dataType:"text",
                cache: false,
                data: new FormData($("#saveForm")[0]), //$() 工厂函数    将DOM对象转化JQuery对象,返回的是一个数组   $("#id")
                processData: false,
                contentType: false,  //把浏览器提交表单的方式变为：multipart/form-data
                success: function (result) {
                    if(result=="true"){
                        swal({
                            title: "发送成功",
                            text: "发送成功,为您返回列表页！",
                            type: "success",
                        }, function () {//此函数是点击删除执行的函数
                            $("#saveForm")[0].reset();
                            UE.getEditor('editor').setContent("");
                        });
                    }else{
                        swal({
                            title: "发送失败",
                            text: "发送失败,请稍后再试！",
                            type: "warning",
                        });
                    }
                }
            });
        });
		
	});
   </script>
   <style>
   	.edui-box{ width: 30px; height: 30px;}
   	
   </style>
</body>


</html>
