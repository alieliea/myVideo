<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
<title>接口修改/添加</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link href="${base_url}assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
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
				<form action="" method="post" id="form" class="form-horizontal" onsubmit="return false">
					<input type="hidden" name="apiInfo.project_id" value="${projectsId }">
					<input type="hidden" name="apiInfo.id" value="${apiInfo.id }">
					<input type="hidden" name="apiInfo.user_id" value="${apiInfo.userId }">
					<input type="hidden" name="apiInfo.lastEdit" value="${apiInfo.lastEdit }">
					<input type="hidden" name="apiInfo.maxpage" id="maxpage" value="${apiInfo.maxpage }">
					<input type="hidden" name="isChange" value="${isChange }">
					<div class="form-group">
						<label class="col-md-3 control-label" for="text-input">接口名称</label>
						<div class="col-md-9">
							<p class="form-control-static">
								<input type="text" id="name" name="apiInfo.name" value="${apiInfo.name }" class="form-control" required>
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="text-input">接口地址</label>
						<div class="col-md-9">
							<p class="form-control-static">
								<input type="text" id="name" name="apiInfo.doUrl" value="${apiInfo.doUrl }" class="form-control" required>
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="text-input">接口详情</label>
						<div class="col-md-9">
							<p class="form-control-static">
								<textarea id="detail" rows="3" name="apiInfo.detail" class="form-control" required>${apiInfo.detail }</textarea>
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="text-input">返回情况</label>
						<div class="col-md-9">
							<div class="col-xs-2">
								<p class="form-control-static">
									<input type="checkbox" id="checkback" onclick="changeMaxpage(this);">是否为列表
								</p>
							</div>
							<div class="col-xs-2">
								<p class="form-control-static">
									<input type="text" id="subject" name="apiInfo.subject" value="${apiInfo.subject }" class="form-control">
									<span class="help-block">返回主体名称</span>
								</p>
							</div>
							<div class="col-xs-2">
								<p class="form-control-static">
									<select name="apiInfo.status" id="status">
										<option value="0" <c:if test="${apiInfo.status==0 }">selected</c:if>>未完成</option>
										<option value="1" <c:if test="${apiInfo.status==1 }">selected</c:if>>已完成</option>
										<option value="2" <c:if test="${apiInfo.status==2 }">selected</c:if>>测试通过（定稿）</option>
										<option value="3" <c:if test="${apiInfo.status==3 }">selected</c:if>>停用</option>
									</select>
								</p>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="text-input">传入参数
						<i class="fa fa-plus" title="增加参数" style="color:green;cursor: pointer;" onclick="addIn();"></i>
						</label>
						<div class="col-md-9" id="inparam">
							<c:if test="${inList!=null }">
								<c:forEach items="${inList }" var="in">
									<div class="infff">
										<div class="col-xs-2">
											<p class="form-control-static">
												<i class="fa fa-minus" title="删除此参数" style="color:red;cursor: pointer;" onclick="del(this);"></i>&nbsp;
												<input type="hidden" name="nature" value="0">是否必填
													<select id="request" name="request">
														<option value="0" <c:if test="${in.request==0 }">selected</c:if>>否</option>
														<option value="1" <c:if test="${in.request==1 }">selected</c:if>>是</option>
													</select>
											</p>
										</div>
										<div class="col-xs-2">
											<p class="form-control-static">
												<input type="text" id="subject" name="name" value="${in.name }" class="form-control" required>
												<span class="help-block">参数名</span>
											</p>
										</div>
										<div class="col-xs-2">
											<p class="form-control-static">
												<input type="text" id="subject" name="genre" value="${in.genre }" class="form-control" required>
												<span class="help-block">数据类型</span>
											</p>
										</div>
										<div class="col-xs-6">
											<p class="form-control-static">
												<input type="text" id="subject" name="particulars" value="${in.particulars }" class="form-control" required>
												<span class="help-block">说明</span>
											</p>
										</div>
									</div>
								</c:forEach>
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="text-input">返回参数
						<i class="fa fa-plus" title="增加参数" style="color:green;cursor: pointer;" onclick="addOut();"></i>
						</label>
						<div class="col-md-9" id="outparam">
							<c:if test="${outList!=null }">
								<c:forEach items="${outList }" var="out">
									<div class="infff">
										<div class="col-xs-2">
											<p class="form-control-static">
												<i class="fa fa-minus" title="删除此参数" style="color:red;cursor: pointer;" onclick="del(this);"></i>&nbsp;
												<input type="hidden" name="nature" value="1">
												<input type="hidden" name="request" value="0">
											</p>
										</div>
										<div class="col-xs-2">
											<p class="form-control-static">
												<input type="text" id="subject" name="name" value="${out.name }" class="form-control" required>
												<span class="help-block">参数名</span>
											</p>
										</div>
										<div class="col-xs-2">
											<p class="form-control-static">
												<input type="text" id="subject" name="genre" value="${out.genre }" class="form-control" required>
												<span class="help-block">数据类型</span>
											</p>
										</div>
										<div class="col-xs-6">
											<p class="form-control-static">
												<input type="text" id="subject" name="particulars" value="${out.particulars }" class="form-control" required>
												<span class="help-block">说明</span>
											</p>
										</div>
									</div>
								</c:forEach>
							</c:if>
						</div>
					</div>
					<div class="form-group" style="text-align: center;">
						<button class="bk-margin-5 btn btn-labeled btn-info" type="submit"onclick="save();">
							<span class="btn-label"><i class="fa fa-save"></i></span>提交
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
	<script src="${base_url}assets/plugins/jquery-validation/js/jquery.validate.js"></script>
	<script src="${base_url}assets/js/pages/form-validation.js"></script>
	<script src="${base_url}js/messages_cn.js"></script>
	<script src="${base_url}js/nestable.js"></script>
	<script src="${base_url}js/checkTrees.js"></script>
	<script src="${base_url}js/common.js"></script>
	<script type="text/javascript">
		var inparam = '<div class="infff"><div class="col-xs-2"><p class="form-control-static">'+
					'<i class="fa fa-minus" title="删除此参数" style="color:red;cursor: pointer;" onclick="del(this);"></i>&nbsp;'+
					'<input type="hidden" name="nature" value="0">'+
					'是否必填<select id="request" name="request">'+
					'<option value="0">否</option>'+
					'<option value="1">是</option></select>'+
					'</p></div><div class="col-xs-2"><p class="form-control-static">'+
					'<input type="text" id="subject" name="name" class="form-control" required>'+
					'<span class="help-block">参数名</span></p></div><div class="col-xs-2">'+
					'<p class="form-control-static">'+
					'<input type="text" id="subject" name="genre" class="form-control" required>'+
					'<span class="help-block">数据类型</span></p></div><div class="col-xs-6">'+
					'<p class="form-control-static">'+
					'<input type="text" id="subject" name="particulars" class="form-control" required>'+
					'<span class="help-block">说明</span></p></div></div>';
		var outparam = '<div class="infff"><div class="col-xs-2"><p class="form-control-static">'+
					'<i class="fa fa-minus" title="删除此参数" style="color:red;cursor: pointer;" onclick="del(this);"></i>&nbsp;'+
					'<input type="hidden" name="nature" value="1">'+
					'<input type="hidden" name="request" value="0">'+
					'</p></div><div class="col-xs-2"><p class="form-control-static">'+
					'<input type="text" id="subject" name="name" class="form-control" required>'+
					'<span class="help-block">参数名</span></p></div><div class="col-xs-2">'+
					'<p class="form-control-static">'+
					'<input type="text" id="subject" name="genre" class="form-control" required>'+
					'<span class="help-block">数据类型</span></p></div><div class="col-xs-6">'+
					'<p class="form-control-static">'+
					'<input type="text" id="subject" name="particulars" class="form-control" required>'+
					'<span class="help-block">说明</span></p></div></div>';
		function save() {
			if ($("#form").validate({}).form()) {
				$.ajax({
					url : '${back_url}projects/saveApiInfo',
					data : $("form").serialize(),
					success : function(data) {
						if (data.success) {
							window.parent.alertMessage("保存成功！", "保存成功！",'success', '', null);
						} else {
							window.parent.alertMessage('保存失败！', '保存失败！',"warning", '', null);
						}
						location.reload();
					}
				});
			}
		}
		$(function() {
		});
		
		function changeMaxpage(obj){
			if(obj.checked){
				$("#maxpage").val(1);
			}else{
				$("#maxpage").val(0);
			}
		}
		function addIn(){
			$("#inparam").append(inparam);
		}
		function addOut(){
			$("#outparam").append(outparam);
		}
		function del(obj){
			obj = $(obj);
			obj.parent().parent().parent().remove();
		}
	</script>
</body>
</html>