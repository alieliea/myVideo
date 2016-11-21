<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach items="${leftMenuList }" var="menu">
	<c:choose>
		<c:when test="${menu.childList !=null}">
			<li class="nav-parent">
				<a style="cursor: pointer;">
					<i class="${menu.icon }" aria-hidden="true"></i><span>${menu.name }</span>
				</a>
				<ul class="nav nav-children">
					<c:forEach items="${menu.childList }" var="child">
						<li data-addtab="${child.EName }" title="${child.name }" url="${back_url }${child.url }">
							<a style="cursor: pointer;"><i class="${child.icon }" aria-hidden="true"></i><span class="text">${child.name }</span></a></li>
					</c:forEach>
				</ul>
			</li>
		</c:when>
		<c:otherwise>
			<li data-addtab="${menu.EName }" title="${menu.name }" url="${menu.url }">
				<a style="cursor: pointer;">
					<i class="${menu.icon }" aria-hidden="true"></i><span>${menu.name }</span>
				</a>
			</li>
		</c:otherwise>
	</c:choose>
</c:forEach>