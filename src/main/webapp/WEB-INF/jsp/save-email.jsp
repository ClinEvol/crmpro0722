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
    	<link href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/select/bootstrap-select.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
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
                        <form id="saveForm" class="form-horizontal">
                       	
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">收件人</label>
                                <div class="col-sm-4">
                                    <select id="Employee" name="eid" class="selectpicker form-control">
                                        <option value="0">--选择收件人---</option>

                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">主题</label>
                                <div class="col-sm-9">
                                    <input name="title" type="text" class="form-control" >
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">内容</label>
                                <div class="col-sm-9">
                                    <input name="content" id="content" type="hidden" class="form-control" >
                                    <script id="editor" type="text/plain"></script>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">定时发送</label>
                                <div class="col-sm-1 text-left tips">
                                	<input name="isTime" type="checkbox" />
                                </div>
                                <label class="col-sm-1 control-label">时间</label>
                                <div class="col-sm-4">
                                    <input name="time" class="form-control layer-date" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                                    <label class="laydate-icon"></label>
                                </div>
                            </div>
                        </div>
                        
                     	<div class="row">
                     		<div class="hr-line-dashed"></div>
                     	</div>
                          
                         <div class="row">
                            <div class="form-group">
                                <div class="col-sm-3 col-sm-offset-3 text-right">
                                    <button id="btnSend" type="button" class="btn btn-primary"><i class="fa fa-paper-plane"></i> 发送</button>
                                </div>
                            </div>
                       </div>
                       </form>
                    </div>
                </div>
            </div>
        </div>
    </div>




    <!--加载动画模态框-->
    <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>邮件正在发送，请稍后...</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="spiner-example">
                            <div class="sk-spinner sk-spinner-wave">
                                <div class="sk-rect1"></div>
                                <div class="sk-rect2"></div>
                                <div class="sk-rect3"></div>
                                <div class="sk-rect4"></div>
                                <div class="sk-rect5"></div>
                            </div>
                        </div>
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
	<script src="${pageContext.request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
	
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
   <script>
	$(document).ready(function() {
		
		//实例化编辑器
		var ue = UE.getEditor('editor');
		// 设置按钮的样式
		$('.selectpicker').selectpicker('setStyle', 'btn-white');

        function loadEmployee() {
            $.post("/employee/listAllJSON","",function (result) {
                $(result).each(function () {
                    var option= option="<option  value='"+this.eid+"'>"+this.ename+"</option>";
                    $("#Employee").append(option).selectpicker('refresh');
                });
            },"json");
        }
        loadEmployee();


//发送消息按钮
        $("#btnSend").click(function(){
            var content= UE.getEditor('editor').getContent();
            $("#content").val(content);
            $.ajax({
                url: "/email/save",
                type: 'POST',
                dataType:"text",
                cache: false,
                data: new FormData($("#saveForm")[0]), //$() 工厂函数    将DOM对象转化JQuery对象,返回的是一个数组   $("#id")
                processData: false,
                contentType: false,  //把浏览器提交表单的方式变为：multipart/form-data
                beforeSend:function(){
                    $(".modal").modal();
                },
                success: function (result) {
                    $(".modal").modal("hide");
                    if(result=="true"){
                        swal({
                            title: "发送成功",
                            text: "发送成功",
                            type: "success",
                        }, function () {//此函数是点击删除执行的函数
                            $("#saveForm")[0].reset();
                            UE.getEditor('editor').setContent("");
                        });
                    }else if(result=="noEmail"){
                        swal("发送失败","没有查询该用户有邮箱","error");
                    }else{
                        swal("发送失败","网络异常，请稍后再试！","error");
                    }
                }
            });
        });

	});
   </script>
</body>


</html>
