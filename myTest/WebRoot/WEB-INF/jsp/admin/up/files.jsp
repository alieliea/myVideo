<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>附件上传</title>
		<!-- Mobile Metas -->
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		
		<!-- Import google fonts
        <link href='http://fonts.useso.com/css?family=Titillium+Web' rel='stylesheet' type='text/css'> -->
        
		<link rel="stylesheet" href="${base_url}kindeditor/themes/default/default.css" />
		<!-- Vendor CSS-->
		<link href="${base_url}assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="${base_url}assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
		<link href="${base_url}assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		<link href="${base_url}assets/vendor/css/pace.preloader.css" rel="stylesheet" />
		
		<!-- Plugins CSS-->
		<link href="${base_url}assets/plugins/bootkit/css/bootkit.css" rel="stylesheet" />	
		
		<!-- Theme CSS -->
		<link href="${base_url}assets/css/jquery.mmenu.css" rel="stylesheet" />
		
		<!-- Page CSS -->		
		<link href="${base_url}assets/css/style.css" rel="stylesheet" />
		<link href="${base_url}assets/css/add-ons.min.css" rel="stylesheet" />
		
		<script src="${base_url}kindeditor/kindeditor.js"></script>
		<script src="${base_url}kindeditor/lang/zh_CN.js"></script>
		
	</head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-8 text-left bk-vcenter">
						<h6 class="bk-margin-off">附件：</h6>
					</div>
					<div class="col-xs-4 bk-vcenter text-right">
						<i class="fa fa-folder-open-o"></i>
					</div>
				</div>
			</div>
			<div class="panel-body" style="padding: 25px 0 0 50px;">
<!-- 				<input type="text" id="url" value="" /> -->
				<input type="button" id="insertfile" value="上传附件" />
				<ol style="margin: 20px 0 0 0;">
				<c:forEach items="${list }" var="files">
					<li>
						<a target="_blank" href="${base_url}img/${files.path}">
							${files.name }
						</a>
						<a href="javascript:void(0);" class="btn-close" onclick="deleteFiles(${files.id});"><i class="fa fa-times"></i></a>
					</li>
				</c:forEach>
				</ol>
			</div>
		</div>
		<div class="modal fade" id="alert" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div id="alert_div" class="modal-block modal-header-color modal-block-success mfp-hide">
				<div class="panel">
				<div class="panel-heading">
					<h2 class="panel-title" id="alert_title"></h2>
				</div>
				<div class="panel-body bk-noradius">
					<div class="modal-wrapper">
						<div class="modal-icon">
							<i class="fa fa-check" id="alert_icon"></i>
						</div>
						<div class="modal-text">
							<p id="alert_p"></p>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<div class="col-md-12 text-right">
							<button class="btn btn-success modal-dismiss" data-dismiss="modal" id="alert_btn">OK</button>
						</div>
					</div>
				</div>
				</div>
			</div>
        </div>
	
		<!-- Vendor JS-->				
		<script src="${base_url}assets/vendor/js/jquery.min.js"></script>
		<script src="${base_url}assets/vendor/js/jquery-2.1.1.min.js"></script>
		<script src="${base_url}assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
		<script src="${base_url}assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="${base_url}assets/vendor/skycons/js/skycons.js"></script>	
		
		<!-- Plugins JS-->
		<script src="${base_url}assets/plugins/autosize/jquery.autosize.min.js"></script>
		<script src="${base_url}assets/plugins/jquery-validation/js/jquery.validate.js"></script>
		<script src="${base_url}assets/plugins/sparkline/js/jquery.sparkline.min.js"></script>
		
		<!-- Theme JS -->		
		<script src="${base_url}assets/js/jquery.mmenu.min.js"></script>
		<script src="${base_url}assets/js/core.min.js"></script>
		
		<!-- Pages JS -->
		<script src="${base_url}assets/js/pages/form-validation.js"></script>
		<script src="${base_url}js/common.js"></script>
		<script>
			KindEditor.ready(function(K) {
				var editor = K.editor({
					cssPath : '${base_url}kindeditor/plugins/code/prettify.css',
					uploadJson : '${base_url}upfiles/upload',
					fileManagerJson : '${base_url}upfiles/fileManager',
					allowFileManager : true
				});
				K('#insertfile').click(function() {
					editor.loadPlugin('insertfile', function() {
						editor.plugin.fileDialog({
							fileUrl : K('#url').val(),
							clickFn : function(url, title,filename) {
// 								K('#url').val(url);
								alert(filename);
								var fileName = $('[name="upName"]').val();
// 								saveFile(fileName,url);
								editor.hideDialog();
							}
						});
					});
				});
			});
		</script>
		<script type="text/javascript">
			var flag = (window === window.top);
			var frameId = window.frameElement && window.frameElement.id || '';
			if(frameId.trim() != ""){
				obj = "document.getElementById('" + frameId + "').contentWindow.location.reload(true)";
			}else{
				obj = "window.location.reload()";
			}
			function deleteFiles(id){
				$.ajax({
					url : '${base_url}files/delFiles?id=' + id,
					success : function(data) {
						if(data.success){
							if(flag){
								alertMessage("操作成功",data.msg,"success","",obj);
							}else{
								window.parent.alertMessage("操作成功",data.msg,"success","",obj);
							}
						}else{
							if(flag){
								alertMessage("操作失败",data.msg,"warning","",null);
							}else{
								window.parent.alertMessage("操作失败",data.msg,"warning","",null);
							}
						}
					}
				});
			}
			function saveFile(name,url){
				var uuid = ${uuid};
				$.ajax({
					url:"${base_url}files/saveFiles",
					data:{path:url,name:name,realname:name,uuid:uuid},
					type: "POST",
					success:function (data){
						alertMessage("上传成功","上传成功","success","","window.location.reload()");
					}
				});
			}
		</script>
	</body>
</html>