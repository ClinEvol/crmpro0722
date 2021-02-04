 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    
</head>

<body class="gray-bg">
	<div class="wrapper2 wrapper-content2 animated fadeInRight">
    		<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>折线图</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="graph_flot.jsp#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="graph_flot.jsp#">选项1</a>
                                </li>
                                <li><a href="graph_flot.jsp#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="echarts" id="echarts-line-chart"></div>
                    </div>
                </div>
            </div>
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>柱状图</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="graph_flot.jsp#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="graph_flot.jsp#">选项1</a>
                                </li>
                                <li><a href="graph_flot.jsp#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">

                        <div class="echarts" id="echarts-bar-chart"></div>
                    </div>
                </div>
            </div>
        </div>
	</div>       
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
     <script src="${pageContext.request.contextPath}/js/plugins/select/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/echarts/echarts-all.js"></script>

   <script>
	$(document).ready(function() {
		// 设置按钮的样式
		$('.selectpicker').selectpicker('setStyle', 'btn-white').selectpicker('setStyle', 'btn-sm');
        var reg=/技术有限公司|股份有限公司|集团有限公司|集团|有限公司|\(中国\)|发展有限公司|\(集团\)/;
		var companyName=[];//公司名称
		var turnover=[];//营业额
		//请求后台数据  $.post(...,"JSON")
        $.getJSON("/dataCollect/listJSON","",function (result) {
            //遍历result
            $(result).each(function () {
                companyName.push(this.dacname.replace(reg,""));
                turnover.push(this.daturnover);
            });
            line.setOption(optionLine);
        });



        var line=echarts.init(document.getElementById("echarts-line-chart"));

        //折线图
        var optionLine = {
            title: {
                text: '营业额统计'
            },
            tooltip: {},
            legend: {
                data: ['营业额']
            },

            xAxis: {
                data: companyName,
            },
            yAxis: [{
                type: 'value',
                name: '营业额（万）',
                splitNumber: 10
            }],

            series: [{
                //折线上数字
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter:100
                    }
                },

                //顶上小图标名称
                name: '营业额',
                type: 'line',
                data: turnover,

                //平均值
                markLine: {
                    data: [{
                        type: 'average',
                        name: '平均值'
                    }]
                },

            }]
        };


	});
    </script>
    
</body>


</html>
