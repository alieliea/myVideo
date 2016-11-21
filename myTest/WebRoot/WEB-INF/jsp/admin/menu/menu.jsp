<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
    	<meta charset="UTF-8" />
		<title></title>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<link href="${base_url}assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="${base_url}assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		<link href="${base_url}css/css.css" rel="stylesheet" />
		<!-- Plugins CSS-->
		<!-- Theme CSS -->
		<!-- Page CSS -->		
		<link href="${base_url}assets/css/style.css" rel="stylesheet" />
		<link href="${base_url}assets/css/add-ons.min.css" rel="stylesheet" />
		<!-- end: CSS file-->	
		<!-- Head Libs -->
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->	
	</head>
	<body>				
		<div class="col-sm-6" style="width: 100%; margin-top: 15px;">
			<div class="panel panel-default bk-bg-white">
				<div class="panel-heading bk-bg-white">
					<h6><i class="fa fa-outdent red"></i>菜单栏</h6>
				</div>
				<div class="panel-body clearfix">
					<div class="dd" id="nestable">
						<ol class="dd-list">
							<c:forEach items="${list }" var="menu">
								<li class="dd-item dd-collapsed" id="item${menu.id }" data-id="item${menu.id }" childs="${menu.isChild}">
									<div class="dd-handle">
										<div id="${menu.EName }" class="checkbox-custom">
										<input type="checkbox" id="${menu.id }"/><label for="${menu.EName }">${menu.name }</label></div></div>
								</li>
							</c:forEach>
						</ol>
					</div>
					<div class="dd">
						
						<div class="panel panel-default bk-bg-white">
								<div class="panel-body">
									<div class="row" style="text-align: center;">
									<button class="bk-margin-5 btn btn-warning btn-circle" onclick="edit();"><i class="fa fa-pencil"></i></button>
									<button class="bk-margin-5 btn btn-danger btn-circle" onclick="deleteMenu();"><i class="fa fa-times"></i></button>
									</div>
									<form action="" method="post" enctype="multipart/form-data" class="form-horizontal">
										<div class="form-group">
											<label class="col-md-3 control-label">父菜单</label>
											<div class="col-md-9">
												<p class="form-control-static" id="fatherName">根目录</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label" for="text-input">菜单名</label>
											<div class="col-md-9">
												<input type="text" id="name" name="menu.name" class="form-control" placeholder="菜单名" required>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label" for="email-input">英文简称</label>
											<div class="col-md-9">
												<input type="text" id="ename" name="menu.e_name" class="form-control" placeholder="英文简称" required>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label" for="password-input">url</label>
											<div class="col-md-9">
												<input type="text" id="url" name="menu.url" class="form-control" placeholder="url" required>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label" for="password-input">icon</label>
											<div class="col-md-9">
												<i class="fa fa-outdent red" style="font-size: 18px;"></i>
												<input type="hidden" value="fa fa-outdent red" name="menu.icon"/>
												<span class="help-block">双击修改</span>
											</div>
										</div>
										<div class="form-group" style="text-align: center;">
												<button class="bk-margin-5 btn btn-labeled btn-info" type="submit">
													<span class="btn-label"><i class="fa fa-save"></i></span>保存</button>
										</div>
									</form>
								</div>								
							</div>
					</div>
				</div>
			</div>
		</div>
		<!-- start: JavaScript-->
		<!-- Vendor JS-->				
		<script src="${base_url}assets/vendor/js/jquery.min.js"></script>
		<script src="${base_url}assets/vendor/js/jquery-2.1.1.min.js"></script>
		<script src="${base_url}assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
		<script src="${base_url}assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="${base_url}assets/plugins/jquery-validation/js/jquery.validate.js"></script>
		<!-- Plugins JS-->
		
		<!-- Theme JS -->
		<script src="${base_url}assets/js/pages/form-validation.js"></script>
		<script src="${base_url}js/messages_cn.js"></script>
		<!-- Pages JS -->
		<script src="${base_url}js/nestable.js"></script>
		<script src="${base_url}js/checkTrees.js"></script> 
		<script src="${base_url}js/common.js"></script> 
		<!-- end: JavaScript-->
		<script type="text/javascript">
			var menuId="";
			var flag = false;
			$(function() {
				linkage = false;
				url = '${back_url}menu/childMenu';
// 				childMenu(0);
				$("form").validate({
					submitHandler:function(form){
						var frameId = window.frameElement && window.frameElement.id || '';
						$.ajax({
							url : '${back_url}menu/saveMenu',
							data : $("form").serialize(),
							success : function(data) {
								var obj = null;
								var url = '';
								var msg = "保存成功！";
								var success = 'success';
								if (data.success) {
									obj = "refreshIframe()";
								}else{
									msg = data.msg==null?'保存失败！':data.msg;
									success = "warning";
								}
								window.parent.alertMessage(msg,msg,success,url,obj);
							}
						});
					},
			        invalidHandler: function(form, validator) {  //不通过回调 
			        	return false; 
			        }
				});
				$(".dd").on("click",".dd-handle,:checkbox",function() {
					var obj = $(this);
					if (!obj.hasClass('dd-handle')) {
						obj=obj.parent().parent();
					}
					var check = obj.find(":checkbox");
					var flag = check.attr("checked") == "checked";
					var fatherName = "根目录";
					if(flag){
						fatherName = obj.find("label").html();
					}
					$("form").find("#fatherName").html(fatherName);
				});
			});
			
			function isChecked(){
				flag=false;
				menuId="";
				$(".dd :checkbox").each(function(){
					if ($(this).attr("checked") == "checked") { 
						menuId += this.id + ",";
						if($("#item"+this.id).attr("childs")==1){
							flag=true;
						}
					}
				});
			}
			
			function deleteMenu(){
				isChecked();
				var frameId = window.frameElement && window.frameElement.id || '';
				if(menuId==''){
					window.parent.alertMessage("删除错误！","请选择需要删除的菜单！","warning",'',null);
				}else if(flag){
					window.parent.alertMessage("删除错误！","请先删除子菜单！","warning",'',null);
				}else{
					menuId=menuId.substring(0,menuId.length-1);
					$.ajax({
						url : '${back_url}menu/delMenu',
						data : {id:menuId},
						success : function(data) {
							var obj = null;
							var url = '';
							var msg = "删除成功！";
							var success = 'success';
							if (data.success) {
								obj = "refreshIframe()";
							}else{
								msg = "删除失败！";
								success = "warning";
							}
							window.parent.alertMessage(msg,msg,success,url,obj);
						}
					});
				}
			}
		</script>
	</body>
</html>
