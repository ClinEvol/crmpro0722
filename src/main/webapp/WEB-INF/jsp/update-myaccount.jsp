 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                      <h5>报销<small>>报销申请</small></h5>
                  </div>
                  <div class="ibox-content">
                      <form id="saveForm" class="form-horizontal">
                          <input type="hidden" name="bxid" value="${baoxiao.bxid}">
                          <input type="hidden" name="bxstatus" id="status">
                          <div class="row">
                              <div class="form-group">
                                  <label class="col-sm-2 control-label">支出类型</label>
                                  <div class="col-sm-3">
                                      <select name="paymode" class="selectpicker form-control">

                                          <option value="1" <c:if test="${baoxiao.paymode==1}">selected</c:if> >出差票据</option>
                                          <option value="2" <c:if test="${baoxiao.paymode==2}">selected</c:if> >办公采购</option>
                                          <option value="3" <c:if test="${baoxiao.paymode==3}">selected</c:if> >其他</option>
                                      </select>
                                  </div>
                                  <label class="col-sm-2 control-label">使用时间</label>
                                  <div class="col-sm-3">
                                      <!--时间控件的id都不能改-->
                                      <input name="bxtime" id="start" value='<fmt:formatDate value="${baoxiao.bxtime}" pattern="yyyy-MM-dd"/>' class="laydate-icon form-control layer-date">
                                  </div>
                              </div>
                          </div>
                          <div class="row">
                              <div class="form-group">
                                  <label class="col-sm-2 control-label">总金额</label>
                                  <div class="col-sm-3">
                                      <input name="totalmoney" value="${baoxiao.totalmoney}" type="text" class="form-control" >
                                  </div>
                              </div>
                          </div>

                          <div class="row">
                              <div class="form-group">
                                  <label class="col-sm-2 control-label">备注</label>
                                  <div class="col-sm-9">
                                      <textarea name="bxremark" class="form-control">${baoxiao.bxremark}</textarea>
                                  </div>
                              </div>
                          </div>
                            <c:if test="${baoxiao.result!=null and baoxiao.result!=''}">
                          <div class="row">
                              <div class="form-group">
                                  <label class="col-sm-2 control-label">批准信息</label>
                                  <div class="col-sm-9">
                                      <textarea readonly name="result" class="form-control">${baoxiao.result}</textarea>
                                  </div>
                              </div>
                          </div>
                            </c:if>
                          <div class="row">
                              <div class="hr-line-dashed"></div>
                          </div>

                          <div class="row">
                              <div class="form-group">
                                  <div class="col-sm-2 col-sm-offset-3 text-right">
                                      <button type="button" class="btn btn-primary btnSave"><i class="fa fa-check"></i> 提交</button>
                                  </div>

                                  <div class="col-sm-2">
                                      <a href="/baoxiao/list?status=1" class="btn btn-white"><i class="fa fa-reply"></i> 返回</a>
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
	<script src="${pageContext.request.contextPath}/js/inputfile.js"></script>
	
   <script>
	$(document).ready(function() {
		
		// 设置按钮的样式
		$('.selectpicker').selectpicker('setStyle', 'btn-white');
		//初始化日期控件
		laydate({elem: "#start"});
		//--------------------下面是常用代码模版---------------------------------------

        //同意
        $(".btnSave").click(function () {
            $("#status").val("1");

            //序列化表单  json
            var data= $("#saveForm").serialize();
            //提效给后
            $.post("/baoxiao/update",data,function (result) {
                if(result=="true"){
                    swal({
                        title:"申请成功",
                        text: "申请成功,您已经成功申请！",
                        type: "success"
                    },function () {
                        location.href="/baoxiao/mylist";
                    });
                }else{
                    swal("申请失败","申请失败，请检查数据有效性！","error");
                }
            },"text");
        });
		
	});
   </script>
</body>


</html>
