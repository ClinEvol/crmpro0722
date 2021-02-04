 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

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
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
      <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>项目管理<small>>添加项目信息</small></h5>
                    </div>
                    <div class="ibox-content">
                        <form id="saveForm" class="form-horizontal">
                            <input type="hidden" name="pid" value="${project.pid}">
                       	<div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">项目名称</label>
                                <div class="col-sm-3">
                                    <input value="${project.name}" name="name" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">客户公司名称</label>
                                <div class="col-sm-3">
                                    <select name="companyname" id="company" class="selectpicker form-control">
                                            <option value="0">---客户公司名称---</option>
									</select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">客户方负责人</label>
                                <div class="col-sm-3">
                                    <input value="${project.companyperson}" name="companyperson" id="companyperson" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">项目经理</label>
                                <div class="col-sm-3">
                                    <select name="empFk" id="projectManager" class="selectpicker form-control">
                                        <option value="0">---选择项目经理---</option>
									</select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">开发人数</label>
                                <div class="col-sm-3">
                                    <input value="${project.empcount}" name="empcount" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">开始时间</label>
                                <div class="col-sm-3">

                                		<!--时间控件的id都不能改-->
                                    <input value=' <fmt:formatDate value="${project.starttime}" pattern="yyyy-MM-dd" />' name="starttime" id="start" class="laydate-icon form-control layer-date">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">立项时间</label>
                                <div class="col-sm-3">
                                    <input name="buildtime" value=' <fmt:formatDate value="${project.buildtime}" pattern="yyyy-MM-dd" />' id="buid" class="laydate-icon form-control layer-date">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">预估成本</label>
                                <div class="col-sm-3">
                                    <input value="${project.cost}" name="cost" type="text" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">级别</label>
                                <div class="col-sm-3">
                                    <select name="level" class="selectpicker form-control">

										<option value="0">---选择级别---</option>
                                        <option value="1" <c:if test="${project.level==1}">selected</c:if> >暂缓</option>
                                        <option value="2" <c:if test="${project.level==1}">selected</c:if> >低</option>
                                        <option value="3" <c:if test="${project.level==1}">selected</c:if> >中</option>
                                        <option value="4" <c:if test="${project.level==1}">selected</c:if> >高</option>
									</select>
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">计划完成时间</label>
                                <div class="col-sm-3">
                                    <input name="endtime" id="finish" value=' <fmt:formatDate value="${project.endtime}" pattern="yyyy-MM-dd" />' class="laydate-icon form-control layer-date">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-9">
                                    <textarea name="remark" class="form-control">${project.remark}</textarea>
                                </div>
                                
                            </div>
                        </div>
                        
                     	<div class="row">
                     		<div class="hr-line-dashed"></div>
                     	</div>
                          
                         <div class="row">
                            <div class="form-group">
                                <div class="col-sm-3 col-sm-offset-3 text-right">
                                    <button type="button" class="btn btn-primary btnSave"><i class="fa fa-save"></i> 保存内容</button>
                                </div>
                                <div class="col-sm-3">
                                	<a href="/project/list" class="btn btn-white"><i class="fa fa-reply"></i> 返回</a>
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
	<script src="${pageContext.request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
   <script>
	$(document).ready(function() {
		// 设置按钮的样式
		$('.selectpicker').selectpicker('setStyle', 'btn-white');
		//初始化日期控件
		laydate({elem: "#finish"});
		laydate({elem: "#buid"});
		laydate({elem: "#start"});

		var empFk=${project.empFk};
		var customerId=${project.companyname};
		
		//加项目经
        function loadEmployee() {
            $.post("/employee/listJSON","position=4",function (result) {
                $(result).each(function () {
                    var option="";
                    if(empFk==this.eid){
                        option="<option selected value='"+this.eid+"'>"+this.ename+"</option>";
                    }else{
                        option="<option value='"+this.eid+"'>"+this.ename+"</option>";
                    }

                    $("#projectManager").append(option).selectpicker('refresh');
                });
            },"json");
        }
        loadEmployee();

        //加项客户方信息
        function loadCustomer() {
            $.post("/customer/listJSON","",function (result) {
                $(result).each(function () {
                    var option="";
                    if(customerId==this.id){
                        option="<option selected data-companyperson='"+this.companyperson+"' value='"+this.id+"'>"+this.comname+"</option>";
                    }else{
                        option="<option data-companyperson='"+this.companyperson+"' value='"+this.id+"'>"+this.comname+"</option>";
                    }

                    $("#company").append(option).selectpicker('refresh');
                });
            },"json");
        }
        loadCustomer();

        //选择客户方信息时显对应的联系人
        $("#company").change(function () {
            //children(":selected")选择 company的子元素，:selected代表只要被选中的
            //data("companyperson"获取data-companyperson属性的值
            var companyperson= $("#company").children(":selected").data("companyperson");
            $("#companyperson").val(companyperson);
        });

        //添加
        $(".btnSave").click(function () {
            //序列化表单  json
            var data= $("#saveForm").serialize();
            //提效给后
            $.post("/project/update",data,function (result) {
                if(result=="true"){
                    swal({
                        title:"修改成功",
                        text: "修改成功,您已经成功修改！",
                        type: "success"
                    },function () {
                        location.href="/project/list";
                    });
                }else{
                    swal("修改失败","修改失败，请检查数据有效性！","error");
                }
            },"text");
        });
		


		
	});
   </script>
   <!-- 修复日期控件长度-->
   <link href="${pageContext.request.contextPath}/css/customer.css" rel="stylesheet">
</body>


</html>
