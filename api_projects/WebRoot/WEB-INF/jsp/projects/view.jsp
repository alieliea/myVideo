<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link href="${base_url}assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="${base_url}assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
<link href="${base_url}css/css.css" rel="stylesheet" />

<link href="${base_url}assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
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
	<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12" style="margin-top: 15px;width: 98%;">
		<div class="panel panel-default bk-bg-white">
			<div class="panel-heading bk-bg-white">
				<h6>
					<i class="fa fa-indent red"></i>接口内容
				</h6>
				<div class="panel-actions"></div>
			</div>
			<div class="panel-body">
				<form action="" method="post" id="form" class="form-horizontal"
					onsubmit="return false">
					<div class="form-group">
						<label class="col-md-3 control-label" for="text-input">接口名称</label>
						<div class="col-md-9">
							<p class="form-control-static">${apiInfo.name }</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="password-input">接口地址</label>
						<div class="col-md-9">
							<input type="hidden" id="doUrl" name="api_doUrl" value="${apiInfo.doUrl }">
							<p class="form-control-static">${apiInfo.doUrl }</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="email-input">接口详情</label>
						<div class="col-md-9">
							<textarea id="textarea-input" rows="3" class="form-control" readonly="readonly">${apiInfo.detail }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="inname">传入参数：</label>
						<div class="col-md-9">
						<c:forEach items="${ inList}" var="in">
							<div class="col-xs-2">
								<c:if test="${in.request==1 }">
									<input type="text" id="inname" name="${in.name}" class="form-control" required>
									<span class="help-block"><font color="red">*</font>${in.name}（${in.genre}）--${in.particulars}</span>
								</c:if>
								<c:if test="${in.request==0 }">
									<input type="text" id="inname" name="${in.name}" class="form-control">
									<span class="help-block">${in.name}（${in.genre}）--${in.particulars}</span>
								</c:if>
							</div>
						</c:forEach>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="inname">返回参数：</label>
						<div class="col-md-9">
							<div class="col-xs-4">
								<span class="help-block">
									{<br>&nbsp;status:1（int）<br>
									&nbsp;info:ok（String）<br>
									<c:if test="${apiInfo.maxpage!=null && apiInfo.maxpage!=0}">&nbsp;maxpage:总页数（int）<br></c:if>
									<c:choose>
										<c:when test="${apiInfo.subject!=null && apiInfo.subject!=''}">
											&nbsp;${apiInfo.subject}{<br>
											<c:forEach items="${ outList}" var="out">
												&nbsp;&nbsp;${out.name}:${out.particulars}（${out.genre}）,<br>
											</c:forEach>
											&nbsp;}
										</c:when>
										<c:otherwise>
											<c:forEach items="${ outList}" var="out">
												&nbsp;${out.name}:${out.particulars}（${out.genre}）<br>
											</c:forEach>
										</c:otherwise>
									</c:choose>
									<br>}
								</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="email-input">地址选择</label>
						<div class="col-md-4">
							<select	name="api_url" class="form-control">
								<option value="${projects.testUrl }">${projects.testUrl }</option>
								<option value="${projects.realUrl }">${projects.realUrl }</option>
							</select>
						</div>
					</div>
					<div class="form-group" style="text-align: center;">
						<button class="bk-margin-5 btn btn-labeled btn-info" type="submit"onclick="save();">
							<span class="btn-label"><i class="fa fa-cogs"></i></span>调试
						</button>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="email-input">调试参数</label>
						<div class="col-md-4" id="yourParam">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="email-input">调试结果</label>
						<div class="col-md-9">
							<textarea id="result" rows="7" class="form-control"></textarea>
						</div>
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
					url : '${back_url}api/doTestApi',
					data : $("form").serialize(),
					type :'post',
					success : function(data) {
						$("#result").val(data.result);
						$("#yourParam").html(data.param);
					}
				});
			}
		}
		$(function() {
		});
	</script>
</body>
</html>