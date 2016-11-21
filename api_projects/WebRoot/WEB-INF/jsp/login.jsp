<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

	<head>
	
		<!-- Basic -->
    	<meta charset="UTF-8" />

		<title>后台登录</title>
	
		<!-- Mobile Metas -->
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		
		<!-- Import google fonts -->
<!--         <link href='http://fonts.useso.com/css?family=Titillium+Web' rel='stylesheet' type='text/css'> -->
        
		<!-- Favicon and touch icons -->
		<link rel="shortcut icon" href="${base_url}assets/ico/favicon.ico" type="image/x-icon" />
		<link rel="apple-touch-icon" href="${base_url}assets/ico/apple-touch-icon.png" />
		<link rel="apple-touch-icon" sizes="57x57" href="${base_url}assets/ico/apple-touch-icon-57x57.png" />
		<link rel="apple-touch-icon" sizes="72x72" href="${base_url}assets/ico/apple-touch-icon-72x72.png" />
		<link rel="apple-touch-icon" sizes="76x76" href="${base_url}assets/ico/apple-touch-icon-76x76.png" />
		<link rel="apple-touch-icon" sizes="114x114" href="${base_url}assets/ico/apple-touch-icon-114x114.png" />
		<link rel="apple-touch-icon" sizes="120x120" href="${base_url}assets/ico/apple-touch-icon-120x120.png" />
		<link rel="apple-touch-icon" sizes="144x144" href="${base_url}assets/ico/apple-touch-icon-144x144.png" />
		<link rel="apple-touch-icon" sizes="152x152" href="${base_url}assets/ico/apple-touch-icon-152x152.png" />
		
	    <!-- start: CSS file-->
		
		<!-- Vendor CSS-->
		<link href="${base_url}assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="${base_url}assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
		<link href="${base_url}assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		<link href="${base_url}assets/vendor/css/pace.preloader.css" rel="stylesheet" />
		<link href="${base_url}css/css.css" rel="stylesheet" />
		
		<!-- Plugins CSS-->
		<link href="${base_url}assets/plugins/bootkit/css/bootkit.css" rel="stylesheet" />	
		<link href="${base_url}assets/plugins/pnotify/css/pnotify.custom.css" rel="stylesheet" />
		<link href="${base_url}assets/plugins/magnific-popup/css/magnific-popup.css" rel="stylesheet" />
		
		<!-- Theme CSS -->
		<link href="${base_url}assets/css/jquery.mmenu.css" rel="stylesheet" />
		
		<!-- Page CSS -->		
		<link href="${base_url}assets/css/style.css" rel="stylesheet" />
		<link href="${base_url}assets/css/add-ons.min.css" rel="stylesheet" />
		
		<style>
			footer {
				display: none;
			}
		</style>
		
		<!-- end: CSS file-->	
	    
		
		<!-- Head Libs -->
		<script src="${base_url}assets/plugins/modernizr/js/modernizr.js"></script>
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->	
	</head>

	<body>
		<!-- Start: Content -->
		<div class="container-fluid content">
			<div class="row">
				<!-- Main Page -->
				<div id="content" class="col-sm-12 full">
					<div class="row">
						<div class="login-box">
							<div class="panel">
								<div class="panel-body">								
									<div class="header bk-margin-bottom-20 text-center">										
										<img src="${base_url}assets/img/logo.png" class="img-responsive" alt="" />
										<h4>登录</h4>
									</div>		
									<form class="form-horizontal login" action="" method="post" onsubmit="return false">
										<div class="bk-padding-left-20 bk-padding-right-20">
											<div class="form-group">
												<label>用户名</label>
												<div class="input-group input-group-icon">
													<input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名" onkeypress="next(this)" required/>
													<span class="input-group-addon">
														<span class="icon">
															<i class="fa fa-user"></i>
														</span>
													</span>
												</div>
											</div>											
											<div class="form-group">
												<label>密码</label>
												<div class="input-group input-group-icon">
													<input type="password" class="form-control" name="userpass" id="userpass" placeholder="请输入密码" onkeypress="next(this)" required/>
													<span class="input-group-addon">
														<span class="icon">
															<i class="fa fa-key"></i>
														</span>
													</span>
												</div>
											</div>
											<div class="row bk-margin-top-20 bk-margin-bottom-10">
												<div class="col-sm-8">
													<div class="checkbox-custom checkbox-default">
														<input id="RememberMe" name="rememberme" type="checkbox"/>
														<label for="RememberMe">记住账号</label>
													</div>
												</div>
												<div class="col-sm-4 text-right">
													<button class="btn btn-primary hidden-xs">登录</button>
													<button class="btn btn-primary btn-block btn-lg visible-xs bk-margin-top-20">登录</button>
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>			
				</div>
				<!-- End Main Page -->
			</div>
		</div><!--/container-->
		<!-- start: JavaScript-->
		<!-- Vendor JS-->	
		
		<script src="${base_url}assets/vendor/js/jquery.min.js"></script>
		<script src="${base_url}assets/vendor/js/jquery-2.1.1.min.js"></script>
		<script src="${base_url}assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="${base_url}assets/vendor/bootstrap/js/bootstrap-modal.js"></script>
		
		<!-- Plugins JS-->
		<script src="${base_url}assets/plugins/jquery-validation/js/jquery.validate.js"></script>
		
		<!-- Theme JS -->		
		<script src="${base_url}assets/js/pages/form-validation.js"></script>
		<script src="${base_url}js/messages_cn.js"></script>
		<script src="${base_url}js/common.js"></script>
		
		<!-- end: JavaScript-->
		<script type="text/javascript">
			$(document).ready(function(){
				if(window === window.top){
				}else{
					window.parent.location.href="${base_url}system/login";
				}
				var rememberName = localStorage.getItem("username");
				if(rememberName != ''){
					$("#username").val(rememberName);
					$("#RememberMe").attr("checked","checked");
				}
				$("form").validate({  
			        submitHandler:function(form){
			        	var username = $("#username").val();
			        	var rememberMe = $("#RememberMe").attr("checked");
						if(rememberMe == 'checked'){
							localStorage.setItem("username", $("#username").val());
						}else{
							localStorage.removeItem("username");
						}
			        	$.ajax({
							url : '${back_url}admin/login',
							data : $("form").serialize(),
							success : function(data) {
								var url = '';
								var msg = data.msg;
								var success = 'success';
								if (data.success) {
									url = '${back_url}admin/main';
								}else{
									msg = "登录失败！";
									success = "warning";
								}
								alertMessage(msg,data.msg,success,url,null);
							}
						});
			        },
			        invalidHandler: function(form, validator) {  //不通过回调 
			        	return false; 
			        }
			    });  
			});
			function next(obj) {
				if (event.keyCode == 13) {
					var id = obj.id;
					if (id == "username") {
						$("#userpass").focus();
					}
					if (id == "userpass") {
						$("#login").click();
					}
				}
			}
		</script>
	</body>
	
</html>