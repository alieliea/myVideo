<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>${projects.name }</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<link href="${base_url}assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${base_url}assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
	<link href="${base_url}assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
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
	<div class="col-xs-12 bk-margin-top-15">
		<div class="panel panel-default bk-bg-white">
			<div class="panel-heading bk-bg-white">
				<a href="javascript:editProjects(${projects.id})" class="btn btn-sm btn-info colorpicker-element" data-plugin-colorpicker="" data-color-format="hex">修改</a>
				<h6><span class="break"></span>项目：${projects.name }的接口列表
				测试地址：${projects.testUrl }<span class="break"></span>
				真实地址：${projects.realUrl }</h6>
			</div>
			<div class="panel-body">
				<div id="datatable-default_wrapper" class="dataTables_wrapper no-footer">
					<div class="row datatables-header form-inline">
						<div class="col-sm-12 col-md-12">
							<form action="${base_url}projects/manage" id="searchForm" method="post">
								<input type="hidden" name="projectsId" value="${projects.id }">
								<input type="hidden" name="pageNumber" id="pageNumber" value="${page.pageNumber }">
								<input type="hidden" name="pageSize" value="${page.pageSize }">
								<div id="datatable-default_filter" class="dataTables_filter">
									<div class="col-sm-2">
										<input type="text" name="name" value="${name }" class="form-control" placeholder="关键字">
									</div>
									<div class="col-sm-2">
										<input type="text" name="url" value="${url }" class="form-control" placeholder="接口地址">
									</div>
									<div class="col-sm-2">
										<select id="userid" name="userid" class="form-control">
											<option value="-1">接口作者</option>
											<c:forEach items="${userlist }" var="user">
												<option value="${user.id }">${user.realname }</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-sm-2">
										<select id="status" name="status" class="form-control">
											<option value="-1">接口状态</option>
											<option value="0">未完成</option>
											<option value="1">已完成</option>
											<option value="2">测试通过（定稿）</option>
											<option value="3">停用</option>
										</select>
									</div>
									<a href="javascript:search(1)" class="btn btn-sm btn-warning colorpicker-element" 
										data-plugin-colorpicker="" data-color-format="hex" data-color="rgb(42,111,244)">搜索</a>
									<a href="javascript:add()" class="btn btn-sm btn-info colorpicker-element" 
										data-plugin-colorpicker="" data-color-format="hex">新增</a>
								</div>
							</form>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table table-bordered table-striped dataTable no-footer" 
							id="datatable-default" role="grid" aria-describedby="datatable-default_info">
							<thead>
								<tr role="row">
									<th class="sorting_asc" tabindex="0" aria-controls="datatable-default" rowspan="1" 
										colspan="1" aria-sort="ascending" 
										aria-label="Rendering engine: activate to sort column ascending" 
										style="width: 15%;">接口名称</th>
									<th class="sorting" tabindex="0" aria-controls="datatable-default" 
										rowspan="1" colspan="1" aria-label="Browser: activate to sort column ascending" 
										style="width: 20%;">接口地址</th>
									<th class="sorting" tabindex="0" aria-controls="datatable-default" 
										rowspan="1" colspan="1" aria-label="Platform(s): activate to sort column ascending" 
										style="width: 10%;">编写者</th>
									<th class="sorting" tabindex="0" aria-controls="datatable-default" 
										rowspan="1" colspan="1" aria-label="Platform(s): activate to sort column ascending" 
										style="width: 10%;">最后修改人</th>
									<th class="hidden-phone sorting" tabindex="0" aria-controls="datatable-default" 
										rowspan="1" colspan="1" aria-label="Engine version: activate to sort column ascending" 
										style="width: 24%;">详情</th>
									<th class="hidden-phone sorting" tabindex="0" aria-controls="datatable-default" 
										rowspan="1" colspan="1" aria-label="CSS grade: activate to sort column ascending" 
										style="width: 10%;">状态</th>
									<th class="hidden-phone sorting" tabindex="0" aria-controls="datatable-default" 
										rowspan="1" colspan="1" aria-label="CSS grade: activate to sort column ascending" 
										style="width: 10%;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.list }" var="apiInfo">
									<tr class="gradeA odd" role="row">
										<td class="sorting_1">${apiInfo.name }</td>
										<td>${apiInfo.doUrl }</td>
										<td>
											<c:forEach items="${userlist }" var="user">
												<c:if test="${user.id==apiInfo.userId }">${user.realname }</c:if>
											</c:forEach>
										</td>
										<td>
											<c:if test="${apiInfo.lastEdit!=null }">
												<c:forEach items="${userlist }" var="user">
													<c:if test="${user.id==apiInfo.lastEdit }">${user.realname }</c:if>
												</c:forEach>
											</c:if>
										</td>
										<td>${apiInfo.detail }</td>
										<td>
											<c:if test="${apiInfo.status==0 }">未完成</c:if>
											<c:if test="${apiInfo.status==1 }">已完成</c:if>
											<c:if test="${apiInfo.status==2 }">测试通过（定稿）</c:if>
											<c:if test="${apiInfo.status==3 }">停用</c:if>
										</td>
										<td>查看 
											<c:if test="${loginuser.rank==1||loginuser.rank==0 }">| 修改</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				<div class="row datatables-footer">
					<div class="col-sm-12 col-md-6">
						<div class="dataTables_info" id="datatable-default_info" role="status" aria-live="polite">
						Showing ${(page.pageNumber-1)*page.pageSize+1} to 
							<c:choose>
								<c:when test="${page.pageNumber*page.pageSize+10 > page.totalRow}">
									${page.totalRow}
								</c:when>
								<c:otherwise>
									${page.pageNumber*page.pageSize+10}
								</c:otherwise>
							</c:choose>
						of ${page.totalRow} entries
						</div>
					</div>
					<div class="col-sm-12 col-md-6">
						<div class="dataTables_paginate paging_bs_normal" id="datatable-default_paginate">
							<ul class="pagination" style="float:right;">
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	</div>
	<div class="clearfix"></div>
	<script src="${base_url}assets/vendor/js/jquery.min.js"></script>
	<script src="${base_url}assets/vendor/js/jquery-2.1.1.min.js"></script>
	<script src="${base_url}assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="${base_url}assets/vendor/bootstrap/js/bootstrap-modal.js"></script>
	<script src="${base_url}js/pagination.js"></script>
	<script type="text/javascript">
		var status = ${status};
		var userid = ${userid};
		$("#userid").val(userid);
		$("#status").val(status);
       	pagination.init({
       		page : '${page.pageNumber}',
           	size : '${page.pageSize}',					
           	total : '${page.totalPage}',					
           	rows : '${page.totalRow}',					
           	url : '${back_url}projects/manage',					
       	});
       	
       	function search(page){
       		$("#searchForm").submit();
       	}
       	function editProjects(id){
       		
       	}
	</script>
</body>
</html>