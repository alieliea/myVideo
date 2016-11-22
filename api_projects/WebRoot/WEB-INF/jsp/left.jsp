<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<li class="nav-parent">
	<a style="cursor: pointer;">
		<i class="fa fa-folder-open" aria-hidden="true"></i><span>项目管理</span>
	</a>
	<ul class="nav nav-children">
		<c:forEach items="${projects }" var="p">
			<li data-addtab="${p.name }" title="${p.name }" url="${base_url}projects/manage?projectsId=${p.id }">
				<a style="cursor: pointer;">
					<i class="fa fa-outdent red" aria-hidden="true"></i>
					<span>${p.name }</span>
				</a>
			</li>
		</c:forEach>
			<li data-addtab="新增项目" title="新增项目" url="${base_url}projects/addProjects">
				<a style="cursor: pointer;">
					<i class="fa fa-plus" aria-hidden="true"></i>
					<span>新增项目</span>
				</a>
			</li>
	</ul>
</li>