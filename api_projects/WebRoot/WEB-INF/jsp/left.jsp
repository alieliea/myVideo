<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${projects }" var="p">
	<li data-addtab="${p.name }" title="${p.name }" url="${p.id }">
		<a style="cursor: pointer;">
			<i class="fa fa-outdent red" aria-hidden="true"></i>
			<span>${p.name }</span>
		</a>
	</li>
</c:forEach>