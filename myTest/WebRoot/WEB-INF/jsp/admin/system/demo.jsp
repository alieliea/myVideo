<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
	<!-- Mobile Metas -->
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		
		<!-- Import google fonts -->
        <link href='http://fonts.useso.com/css?family=Titillium+Web' rel='stylesheet' type='text/css'>
        
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
		
		
		<link rel="stylesheet" href="${base_url}kindeditor/themes/default/default.css" />
		<link rel="stylesheet" href="${base_url}kindeditor/plugins/code/prettify.css" />
		<script charset="utf-8" src="${base_url}kindeditor/kindeditor.js"></script>
		<script charset="utf-8" src="${base_url}kindeditor/lang/zh_CN.js"></script>
		<script charset="utf-8" src="${base_url}kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
				cssPath : '${base_url}kindeditor/plugins/code/prettify.css',
				uploadJson : '${base_url}upfiles/upload',
				fileManagerJson : '${base_url}upfiles/fileManager',
				allowFileManager : true,
				afterCreate : function(data) {
// 					var self = this;
// 					K.ctrl(document, 13, function() {
// 						self.sync();
// 						document.forms['example'].submit();
// 					});
// 					K.ctrl(self.edit.doc, 13, function() {
// 						self.sync();
// 						document.forms['example'].submit();
// 					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body>
	<form name="example" method="post" action="${base_url }hello">
		<textarea name="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"></textarea>
		<br />
		<input type="submit" name="button" value="提交内容" /> (提交快捷键: Ctrl + Enter)
	</form>
	<iframe src="${base_url }files?uuid=1" id="aaa"></iframe>
	
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
</body>
</html>