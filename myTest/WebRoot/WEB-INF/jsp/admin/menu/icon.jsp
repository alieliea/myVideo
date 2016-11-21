<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

	<head>

		<!-- Basic -->
		<meta charset="UTF-8" />

		<title>icons</title>

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

		<!-- start: CSS file-->

		<!-- Vendor CSS-->
		<link href="${base_url}assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="${base_url}assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
		<link href="${base_url}assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		<!-- Page CSS -->
		<link href="${base_url}assets/css/style.css" rel="stylesheet" />
		<link href="${base_url}assets/css/add-ons.min.css" rel="stylesheet" />

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
		<div class="col-xs-12 bk-margin-top-15">
			<div class="panel panel-default bk-bg-white">
				<div class="panel-heading bk-bg-white">
					<h6>图标列表</h6>
				</div>
				<div class="panel-body bk-fg-info">
					<div class="row fontawesome-icon-list" style="margin-top: 15px;">
						<c:forEach items="${icons.list }" var="icon">
							<div class="col-md-1 modal-icon" id=${icon.id } style="width:8%;margin:5px;"><i class="fa ${icon.name }"></i></div>
						</c:forEach>
					</div>
				</div>
				<ul class="pagination" style="float:right;">
				</ul>
			</div>
		</div>
		<div class="clearfix"></div>
		<script src="${base_url}assets/vendor/js/jquery.min.js"></script>
		<script src="${base_url}assets/vendor/js/jquery-2.1.1.min.js"></script>
		<script src="${base_url}assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="${base_url}assets/vendor/bootstrap/js/bootstrap-modal.js"></script>
		<script src="${base_url}js/pagination.js"></script>
		<script type="text/javascript">
        	pagination.init({
        		page : '${icons.pageNumber}',
            	size : '${icons.pageSize}',					
            	total : '${icons.totalPage}',					
            	rows : '${icons.totalRow}',					
            	url : '${back_url}icon',					
        	});
        	$(".modal-icon").on("dblclick",function(){
        		if( typeof window.parent.showme === 'function' ){
            		window.parent.showme(this.id);
        		}
        	});
		</script>
	</body>
</html>