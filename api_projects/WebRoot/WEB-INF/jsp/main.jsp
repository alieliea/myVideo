<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />

	<title>main</title>
    <!-- Mobile Metas -->
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	    <!-- start: CSS file-->
		
		<!-- Vendor CSS-->
		<link href="${base_url}assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${base_url}assets/vendor/bootstrap/css/bootstrap-addtabs.css" type="text/css" media="screen" />
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
		
		<!-- end: CSS file-->	
	    
		
		<!-- Head Libs -->
		<script src="${base_url}assets/plugins/modernizr/js/modernizr.js"></script>
</head>
<body>
<!-- Start: Header -->
		<div class="navbar" role="navigation">
			<div class="container-fluid container-nav">
				<!-- Navbar Action -->
				<ul class="nav navbar-nav navbar-actions navbar-left">
					<li class="visible-xs visible-sm"><a href="#" id="sidebar-menu"><i class="fa fa-navicon"></i></a></li>			
				</ul>
				<!-- Navbar Left -->
				<div class="navbar-left">
				</div>
				<!-- Navbar Right -->
				<div class="navbar-right">			
					<!-- Userbox -->
					<div class="userbox">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<figure class="profile-picture hidden-xs">
								<img src="${base_url }assets/img/avatar4.jpg" class="img-circle" alt="" />
							</figure>
							<div class="profile-info">
								<span class="name">${loginuser.realname }</span>
<!-- 								<span class="role"><i class="fa fa-circle bk-fg-success"></i>程序猿</span> -->
							</div>			
							<i class="fa custom-caret"></i>
						</a>
						<div class="dropdown-menu">
							<ul class="list-unstyled">
								<li class="dropdown-menu-header bk-bg-white bk-margin-top-15">	
								</li>	
								<li>
									<a href="${back_url }admin/toLogin"><i class="fa fa-power-off"></i>注销</a>
								</li>
							</ul>
						</div>
					</div>
					<!-- End Userbox -->
				</div>
				<!-- End Navbar Right -->
			</div>		
		</div>
		<!-- End: Header -->
		
		<!-- Start: Content -->
<div class="container-fluid content">	
	<div class="row">
		<div class="sidebar">
			<div class="sidebar-collapse">
				<!-- Sidebar Header Logo-->
				<div class="sidebar-header">
					<img src="${base_url }assets/img/logo.png" class="img-responsive" alt="" />
				</div>
				<!-- Sidebar Menu-->
				<div class="sidebar-menu">						
					<nav id="menu" class="nav-main" role="navigation">
						<ul class="nav nav-sidebar">
							<div class="panel-body text-center">								
								<div class="flag">
									<img src="${base_url }assets/img/flags/timg.jpg" class="img-flags" style="width: 100px;" alt="" />
								</div>
							</div>
							<jsp:include page="left.jsp"></jsp:include>
						</ul>
					</nav>
				</div>
				<!-- End Sidebar Menu-->
			</div>
			<!-- Sidebar Footer-->
			<div class="sidebar-footer" style="height:1px;">
			</div>
			<!-- End Sidebar Footer-->
		</div>
		<!-- End Sidebar -->
		
		<!-- Main Page -->
		<div class="main ">
			<!-- Page Header -->
			<div class="page-header" style="background: none;box-shadow:none;">
	       		<div class="col-md-12" style="margin-top: 5px;">
		            <div id="tabs">
		                <!-- Nav tabs -->
		                <ul class="nav nav-tabs" role="tablist">
		                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>                    
		                </ul>
		            </div>
		            <div class="tab-content">
		            </div>
		            <!-- Tab panes -->
				</div>
			</div>
			<!-- End Page Header -->	
		</div>
		<!-- End Main Page -->	
	</div>
</div>
	
		<div class="clearfix"></div>		
		
		
		<!-- start: JavaScript-->
		
		<!-- Vendor JS-->				
		<script src="${base_url}assets/vendor/js/jquery.min.js"></script>
		<script src="${base_url}assets/vendor/js/jquery-2.1.1.min.js"></script>
		<script src="${base_url}assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
		<script src="${base_url}assets/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="${base_url}assets/vendor/bootstrap/js/bootstrap-addtabs.js"></script>
		<script src="${base_url}assets/vendor/skycons/js/skycons.js"></script>
		<script src="${base_url}assets/vendor/js/pace.min.js"></script>
		
		<!-- Plugins JS-->	
		<script src="${base_url}assets/plugins/sparkline/js/jquery.sparkline.min.js"></script>
		<!-- Theme JS -->		
		<script src="${base_url}assets/js/jquery.mmenu.min.js"></script>
		<script src="${base_url}assets/js/core.min.js"></script>
		<script src="${base_url}js/common.js"></script> 
		
		<!-- Pages JS -->
		<script type="text/javascript">
            $(function(){
            	$('#tabs').addtabs({monitor:'.dropdown-menu'});
                $('.nav-sidebar > li.nav-parent').find('.nav-children a').on( 'click', function( ev ) {
					Addtabs.add({
			           id: $(this).parent().attr('data-addtab'),
			           title: $(this).parent().attr('title') ? $(this).parent().attr('title') : $(this).html(),
			           content: Addtabs.options.content ? Addtabs.options.content : $(this).attr('content'),
			           url: $(this).parent().attr('url')
			       });
					ev.stopPropagation();
				});
            });
        </script>
		<!-- end: JavaScript-->
</body>
</html>