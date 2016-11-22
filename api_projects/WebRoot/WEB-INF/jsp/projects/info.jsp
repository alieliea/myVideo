<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title></title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link href="${base_url}assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="${base_url}assets/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="${base_url}css/css.css" rel="stylesheet" />

<link href="${base_url}assets/vendor/skycons/css/skycons.css"
	rel="stylesheet" />
<link href="${base_url}assets/css/style.css" rel="stylesheet" />
<link href="${base_url}assets/css/add-ons.min.css" rel="stylesheet" />
<script src="${base_url}assets/plugins/modernizr/js/modernizr.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body>
	<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12"
		style="margin-top: 15px;width: 98%;">
		<div class="panel panel-default bk-bg-white">
			<div class="panel-heading bk-bg-white">
				<h6>
					<i class="fa fa-indent red"></i>项目内容
				</h6>
				<div class="panel-actions"></div>
			</div>
			<div class="panel-body">
				<form action="" method="post" id="form" class="form-horizontal"
					onsubmit="return false">
					<input type="hidden" value="${projects.id }" name="projects.id">
					<div class="form-group">
						<label class="col-md-3 control-label" for="text-input">项目名称</label>
						<div class="col-md-9">
							<input type="text" id="name" class="form-control"
								value="${projects.name }" placeholder="请输入项目名称" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="email-input">测试地址</label>
						<div class="col-md-9">
							<input type="text" id="testUrl" name="projects.test_url"
								value="${projects.testUrl }" class="form-control"
								placeholder="请输入测试地址" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="password-input">真实地址</label>
						<div class="col-md-9">
							<input type="text" id="realUrl" name="projects.real_url"
								value="${projects.realUrl }" class="form-control"
								placeholder="请输入真实地址" required>
						</div>
					</div>
					<div class="form-group" style="text-align: center;">
						<button class="bk-margin-5 btn btn-labeled btn-info" type="submit"
							onclick="save();">
							<span class="btn-label"><i class="fa fa-save"></i></span>保存
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="${base_url}assets/vendor/js/jquery.min.js"></script>
	<script src="${base_url}assets/vendor/js/jquery-2.1.1.min.js"></script>
	<script src="${base_url}assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="${base_url}assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${base_url}assets/plugins/jquery-validation/js/jquery.validate.js"></script>
	<script src="${base_url}assets/js/pages/form-validation.js"></script>
	<script src="${base_url}js/messages_cn.js"></script>
	<script src="${base_url}js/nestable.js"></script>
	<script src="${base_url}js/checkTrees.js"></script>
	<script src="${base_url}js/common.js"></script>
	<script type="text/javascript">
		function save() {
			if ($("#form").validate({}).form()) {
				$.ajax({
					url : '${back_url}projects/saveProjects',
					data : $("form").serialize(),
					success : function(data) {
						if (data.success) {
							window.parent.alertMessage("保存成功！", "保存成功！",
									'success', '', null);
						} else {
							window.parent.alertMessage('保存失败！', '保存失败！',
									"warning", '', null);
						}
					}
				});
			}
		}
		$(function() {
		});
	</script>
</body>
</html>