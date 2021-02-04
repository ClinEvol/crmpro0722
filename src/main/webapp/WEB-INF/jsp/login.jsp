<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
	<title>Home</title>
	<!-- Meta tag Keywords -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8" />
	<meta name="keywords" content=""
	/>
	<script>
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	</script>
	<!-- Meta tag Keywords -->
	<!-- css files -->
	
	<link href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginstyle.css" type="text/css" media="all" />
	<link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
</head>

<body>
	<!-- bg effect -->
	<div id="bg">
		<canvas></canvas>
		<canvas></canvas>
		<canvas></canvas>
	</div>
	
	<!-- content -->
	<div class="sub-main-w3">
		<form id="saveForm" method="post">
			<h2>欢迎登录项目管理系统
				<i class="fa fa-level-down"></i>
			</h2>
			 <div class="row">
                <div class="form-group">
                    <label class="col-sm-4 control-label text-right">用户名<i class="fa fa-user"></i></label>
                    <div class="col-sm-6">
                        <input name="username" type="text" class="form-control input-sm">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label class="col-sm-4 control-label text-right">密码<i class="fa fa-unlock-alt"></i></label>
                    <div class="col-sm-6">
                        <input name="password" type="password" class="form-control input-sm">
                    </div>
                </div>
            </div>
			<div class="row">
                <div class="form-group">
                    <label class="col-sm-4 control-label text-right">验证码<i class="fa fa-picture-o"></i></label>
                    <div class="col-sm-3">
                        <input name="code" type="text" class="form-control input-sm">
                    </div>
                    <div class="col-sm-3 text-right">
                        <img src="/logincode.jpg" id="img" class="logincode" onclick="document.getElementById('img').src='/logincode.jpg?'+new Date().getTime();"  />
                    </div>
                </div>
            </div>
			<button type="button" class="btnSave"><i class="fa fa-send"></i> 登 录</button>
			
		</form>
	</div>

	<div class="footer">
		<p>Copyright &copy; 2018.项目理管系统 All rights reserved.</p>
	</div>


	
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	
	<script src="${pageContext.request.contextPath}/js/canva_moving_effect.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>

</body>

<script>
	$(function () {
		//添加
		$(".btnSave").click(function () {
			//序列化表单  json
			var data= $("#saveForm").serialize();
			//提效给后
			$.post("/employee/login",data,function (result) {
				if(result=="codeError"){
					swal("登录失败","验证码不正确！","error");
				}else if(result=="false"){
					swal("登录失败","用户名或密码不正确！","error");
				}else{
					location.href="/";
				}
			},"text");
		});
	});

</script>
</html>