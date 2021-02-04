 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    	<link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/select/bootstrap-select.min.css" rel="stylesheet">

</head>

<body class="gray-bg">
	<div class="wrapper2 wrapper-content2 animated fadeInRight">
    <div class="ibox float-e-margins">
                   <div class="ibox-content">
                       <div class="row">
                           <form action="/task/mylist" method="post">
                               <div class="col-sm-3 col-sm-offset-4 text-right">
                                   <h3><small>搜索条件:</small></h3>
                               </div>
                               <div class="col-sm-2">
                                   <select name="type" class="selectpicker form-control">
                                       <option  value="0">选择类型</option>
                                       <option <c:if test="${type==1}">selected</c:if> value="1">任务标题</option>
                                       <option <c:if test="${type==3}">selected</c:if> value="3">分配者</option>
                                   </select>
                               </div>

                               <div class="col-sm-3">
                                   <div class="input-group">
                                       <input name="keyword" value="${keyword}" type="text" placeholder="请输入关键词" class="input-sm form-control">
                                       <span class="input-group-btn">
                                        <button type="submit" class="btn btn-sm btn-primary"><i class="fa fa-search"></i> 搜索</button>
                                    </span>
                                   </div>

                               </div>
                           </form>
                       </div>
                        <div class="hr-line-dashed2"></div>
                        <div class="table-responsive">
                            <table class="table table-striped list-table">
                                <thead>
                                    <tr>
                                        
                                        <th>序号</th>
                                        <th>任务标题</th>
                                        <th>创建人</th>
                                        <th>状态</th>
                                        <th>优先级</th>
                                        <th>开始时间</th>
                                        <th>结束时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="task">
                                    <tr>
                                        
                                         <td>${task.id}</td>
                                        <td>${task.tasktitle}</td>
                                        <td>${task.sharer.ename}</td>
                                        <td>
                                            <c:if test="${task.status==0}"><i class="fa fa-hourglass-start"></i> 分配</c:if>
                                            <c:if test="${task.status==1}"><i class="fa fa-hourglass-half"></i> 进行中...</c:if>
                                            <c:if test="${task.status==2}"><i class="fa fa-hourglass-end"></i>完成</c:if>
                                        </td>
                                        <td>
                                            <c:if test="${task.level=='高'}"><i class="fa fa-star"></i>高</c:if>
                                            <c:if test="${task.level=='中'}"><i class="fa fa-star-half-empty"></i>中</c:if>
                                            <c:if test="${task.level=='低'}"><i class="fa fa-star-o"></i>低</c:if>
                                            <c:if test="${task.level=='暂缓'}">暂缓</c:if>
                                        </td>
                                        <td><fmt:formatDate value="${task.starttime}" pattern="yyyy-MM-dd" /></td>
                                        <td><fmt:formatDate value="${task.endtime}" pattern="yyyy-MM-dd" /></td>
                                        <td>
                                        	<a class="btn btn-primary btn-xs" href="show-task.jsp"><i class="fa fa-eye"></i>查看</a>
                                            <c:if test="${task.status==0}">
                                                <button id="${task.id}" class="btn btn-success btn-xs btnBegin"><i class="fa fa-hourglass-start"></i>开始</button>
                                            </c:if>
                                            <c:if test="${task.status==1}">
                                                <button id="${task.id}"  class="btn btn-danger btn-xs btnFinish"><i class="fa fa-hourglass-end"></i> 完成</button>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${fn:length(list)==0}">
                                    <tr><td colspan="8"class="text-center">没有找到相关的数据</td></tr>
                                </c:if>
                                    
                                </tbody>
                            </table>
                        </div>
                       
                        <div class="row">
	                        
							<div class="col-sm-12 text-right">
                                <div class="btn-group">
                                    <a href="/task/mylist?pageNum=${page.pageNum-1}&keyword=${keyword}&type=${type}" class="btn btn-sm btn-white <c:if test="${page.pageNum-1<1}">disabled</c:if> "><i class="glyphicon glyphicon-chevron-left"></i></a>
                                    <c:forEach begin="1" end="${page.pages}" varStatus="index">
                                        <a href="/task/mylist?pageNum=${index.index}&keyword=${keyword}&type=${type}" class="btn btn-sm btn-white <c:if test="${page.pageNum==index.index}">active</c:if> ">${index.index}</a>
                                    </c:forEach>
                                    <a href="/task/mylist?pageNum=${page.pageNum+1}&keyword=${keyword}&type=${type}" class="btn btn-sm btn-white <c:if test="${page.pageNum+1>page.pages}">disabled</c:if> "><i class="glyphicon glyphicon-chevron-right"></i></a>
                                </div>
							</div>
                        </div>
						
                    </div>
                </div>
     </div>       
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
     <script src="${pageContext.request.contextPath}/js/plugins/select/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>

   <script>
	$(document).ready(function() {
		// 设置按钮的样式
		$('.selectpicker').selectpicker('setStyle', 'btn-white').selectpicker('setStyle', 'btn-sm');

        $("body").on("click",".btnFinish",function () {
		    var $this=$(this);
		    var id=$(this).prop("id");
            $.post("/task/update",{"id":id,"status":2},function (result) {
                if(result=="true"){
                    swal("状态修改成功！","任务已经完成！","success");
                    $this.parents("tr").children(":eq(3)").html("<i class='fa fa-hourglass-end'></i>完成");
                    $this.remove();
                }else{
                    swal("状态修改失败！","状态修改失败，请稍后再试！","error");
                }
            },"text");
        });

        $("body").on("click",".btnBegin",function () {
            var $this=$(this);
            var id=$(this).prop("id");
            $.post("/task/update",{"id":id,"status":1},function (result) {
                if(result=="true"){
                    swal("状态修改成功！","任务已经开始，请注意完成时间！","success");
                    $this.parents("tr").children(":eq(3)").html("<i class='fa fa-hourglass-half'></i> 进行中...");
                    $this.html("<i class='fa fa-hourglass-end'></i> 完成").removeClass("btn-success btnBegin").addClass("btn-danger btnFinish").unbind("click");
                }else{
                    swal("状态修改失败！","状态修改失败，请稍后再试！","error");
                }
            },"text");
        });


	});
    </script>
    
</body>


</html>
