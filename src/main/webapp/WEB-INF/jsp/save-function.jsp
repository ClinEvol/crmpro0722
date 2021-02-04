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
                                <label class="col-sm-2 control-label">选择项目</label>
                                <div class="col-sm-3">
                                    <select id="project" name="proFk" class="selectpicker form-control">
                                        <option value="0">---选择项目----</option>
									</select>
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">选择需求</label>
                                <div class="col-sm-3">
                                    <input type="hidden" name="analysisFk" id="analysisFk">
                                    <input readonly id="analysisName" type="text" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">选择模块</label>
                                <div class="col-sm-3">
                                	<select id="module" name="moduleFk" class="selectpicker form-control">
                                        <option value="0">---选择模块----</option>
									</select>
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">优先级</label>
                                <div class="col-sm-3">
                                    <select name="level" class="selectpicker form-control">
                                        <option value="0">---选择级别---</option>
                                        <option value="低">低</option>
                                        <option value="中">中</option>
                                        <option value="高">高</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">功能名称</label>
                                <div class="col-sm-3">
                                		<input name="functionname" type="text" class="form-control input-sm">
                                </div>
                                
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">简单描述</label>
                                <div class="col-sm-9">
                                    <textarea name="simpledis" class="form-control"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">详细描述</label>
                                <div class="col-sm-9">
                                    <textarea name="detaileddis" class="form-control"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-9">
                                    <textarea name="remark" class="form-control"></textarea>
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
                                	<a href="/function/list" class="btn btn-white"><i class="fa fa-reply"></i> 返回</a>
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


            //加载项目信息
            function loadProject() {
                $.post("/project/listJSON","",function (result) {
                    $(result).each(function () {
                        var option="<option data-analysisid='"+this.pid+"' value='"+this.pid+"'>"+this.name+"</option>";
                        $("#project").append(option).selectpicker('refresh');
                    });
                },"json");
            }
            loadProject();

            //选择项目时给id字段赋值
            $("#project").change(function () {
                //获取选中的项目id
                var pid=$("#project").children(":selected").data("analysisid");
                $("#analysisFk").val(pid);
                //查需求
                analysisById(pid);

            });
            //查询对应的需求
            function analysisById(id) {
                $.post("/analysis/getById","id="+id,function (result) {
                    if(result==null){//没有查到对应的需求
                        //禁用保存按
                        $(".btnSave").prop("disabled","disabled");
                        swal({
                            title: "没有该项目的需求",
                            text: "是否前住添加需求",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "yes",
                            closeOnConfirm: false
                        }, function () {//此函数是点击删除执行的函数
                            location.href="/analysis/tosave";
                        });
                    }else{
                        //解除禁用
                        $(".btnSave").removeAttr("disabled");
                        //把需求名称显示的文本框中
                        $("#analysisName").val(result.title);
                        //查对应的模块
                        listByAnalysisFk(id);
                    }
                },"json");
            }

            //查询对应的模块
            function listByAnalysisFk(analysis_fk){
                $.post("/module/listByAnalysisFk","analysis_fk="+analysis_fk,function (result) {
                    if(result.length==0){//没有查到对应的模块   返回的是[]
                        //禁用保存按
                        $(".btnSave").prop("disabled","disabled");
                        swal({
                            title: "没有该项目的模块",
                            text: "是否前住添加模块",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "yes",
                            closeOnConfirm: false
                        }, function () {//此函数是点击删除执行的函数
                            location.href="/module/tosave";
                        });
                    }else{
                        //解除禁用
                        $(".btnSave").removeAttr("disabled");
                        $("#module").html("");//清除原本的容内
                        //把模块名称显示的下拉框中
                        var option="<option value='0'>---选择模块----</option>";
                        $("#module").append(option).selectpicker('refresh');
                        $(result).each(function () {
                            var option="<option  value='"+this.id+"'>"+this.modulename+"</option>";
                            $("#module").append(option).selectpicker('refresh');
                        });
                    }
                },"json");
            }


            //添加
            $(".btnSave").click(function () {
                //序列化表单  json
                var data= $("#saveForm").serialize();
                //提效给后
                $.post("/function/save",data,function (result) {
                    if(result=="true"){
                        swal({
                            title:"添加成功",
                            text: "添加成功,您已经成功添加！",
                            type: "success"
                        },function () {
                            location.href="/function/list";
                        });
                    }else{
                        swal("添加失败","添加失败，请检查数据有效性！","error");
                    }
                },"text");
            });

        });
    </script>
   
</body>


</html>
