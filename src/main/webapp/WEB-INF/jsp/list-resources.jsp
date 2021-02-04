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
    	<link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/select/bootstrap-select.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/plugins/zTreeStyle/zTreeStyle.css" />

</head>

<body class="gray-bg">
	<div class="wrapper2 wrapper-content2 animated fadeInRight">
	    <div class="row">
	    		<div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>资源管理</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="zTreeDemoBackground left" style="font-size: 16px">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
                    </div>
                </div>
            </div>
	    		<div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>资源编辑</h5>
                    </div>
                    <div class="ibox-content">
                        <form id="saveForm" class="form-horizontal">
                            <input type="hidden" name="opr" id="opr" value="save">
                            <input type="hidden" name="id" >
                            <div class="form-group">
                                <label class="col-sm-4 control-label">菜单资源名称：</label>

                                <div class="col-sm-7">
                                    <input type="text" name="name" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">父菜单：</label>

                                <div class="col-sm-7">
                                    <select id="sources" name="pid" class="selectpicker form-control">
										<option value="0">--------选择菜单---------</option>
									</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">菜单资源路径：</label>

                                <div class="col-sm-7">
                                    <input type="text" name="url" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">备注：</label>
                                <div class="col-sm-7">
                                    <textarea name="remark" class="form-control"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-8">
                                    <button class="btn btn-sm btn-white btnSave" type="button"><i class="fa fa-save"></i> 保存</button>
                                    <button class="btn btn-sm btn-white btnReset" type="reset"><i class="fa fa-undo"></i> 重置</button>
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
     <script src="${pageContext.request.contextPath}/js/plugins/select/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/ztree/jquery.ztree.core.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/ztree/jquery.ztree.exedit.js"></script>
   <script>
	$(document).ready(function() {
		// 设置按钮的样式
		$('.selectpicker').selectpicker('setStyle', 'btn-white').selectpicker('setStyle', 'btn-sm');
		

	});
    </script>
    <SCRIPT type="text/javascript">

	var setting = {

        async: {
            enable: true,
            url: "/sources/list",
            autoParam: ["id"]
        },
         view: {
                addHoverDom: function(treeId, treeNode){
                    var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
                    aObj.attr("href", "javascript:void(0);").removeAttr("target");
                    if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
                    var s ='<span id="btnGroup'+treeNode.tId+'">';
                    if ( treeNode.level == 1 ) {
                        s += '<span class="button edit" onclick="editNode('+treeNode.id+')"></span>';
                        if (treeNode.children.length == 0) {
                            s += '<span class="button remove" onclick="deleteNode('+treeNode.id+')"></span>';
                        }
                    } else if ( treeNode.level == 2 ) {
                        s += '<span class="button edit" onclick="editNode('+treeNode.id+')" ></span>';
                        s += '<span class="button remove" onclick="deleteNode('+treeNode.id+')" ></span>';
                    }
                    s += '</span>';
                    aObj.append(s);
                },
                removeHoverDom: function(treeId, treeNode){
                    $("#btnGroup"+treeNode.tId).remove();
                }
            }
			 
	  };


		
	//编辑
	function editNode(id){
	    $("#opr").val("update");
		$.getJSON("/sources/get","id="+id,function (result) {
            $("input[name='name']").val(result.name);
            $("input[name='url']").val(result.url);
            $("input[name='id']").val(result.id);
            $("textarea[name='remark']").text(result.remark);
            $("#sources").selectpicker('val',result.pid);
        });
	}

	//删除
	function deleteNode(id){
		//ajax请求台
		$.post("/sources/remove","id="+id,function(result){
            // 刷新数据
            if(result=="true"){
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                treeObj.reAsyncChildNodes(null, "refresh");
            }

		},"text");
	}
	
	
	$(document).ready(function(){

       $.fn.zTree.init($("#treeDemo"), setting);
       
       
       //加载菜单
        $.getJSON("/sources/listAll","",function (result) {
            $(result).each(function () {
                var option="<option value='"+this.id+"'>"+this.name+"</option>";
                $("#sources").append(option).selectpicker('refresh');
            });
        })
        
        //重置
        $(".btnReset").click(function () {
            myReset();
        });

        function myReset(){
            $("input[name='id']").val("0");
            $("#opr").val("save");
            $("#sources").selectpicker('val',"0");
            $("textarea[name='remark']").text("");
        }


        //保存数据
        $(".btnSave").click(function () {
            var opr=$("#opr").val();
            var data = $("#saveForm").serialize();
            alert(data)
            $.post("/sources/"+opr,data,function (result) {
                if(result=="true"){
                    alert("yrs")
                    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                    treeObj.reAsyncChildNodes(null, "refresh");
                    $("#saveForm")[0].reset();
                    myReset();
                }else{
                    alert("no")
                }
            },"text");
        });

	});
	</SCRIPT>
</body>


</html>
