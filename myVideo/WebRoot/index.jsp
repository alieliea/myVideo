<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>videos</title>
<script type="text/javascript">
	function search(name,path){
		name = name.replace("//","/");
		path = path.replace("//","/");
		document.getElementById("name").value = name;
		document.getElementById("path").value = path;
		document.getElementById('form').submit();
	}
	function videoPlay(name,path){
		name = name.replace("//","/");
		path = path.replace("//","/");
		document.getElementById("vpname").value = name;
		document.getElementById("vppath").value = path;
		document.getElementById('form2').submit();
	}
	
	function backUp(path){
		path = path.substring(0,path.lastIndexOf("/"));
		if(path == ""){
			path = "/";
		}
		document.getElementById("name").value = "";
		document.getElementById("path").value = path;
		document.getElementById('form').submit();
	}
</script>
</head>
<body>
	<form action="videos" id="form" method="post">
		名称:<input type="text" name="name" id="name" value="${name }">
		<input type="hidden" name="path" id="path">
		<input type="submit" value="搜索">
	</form>
	<form action="videoPlay" id="form2" method="post">
		<input type="hidden" name="name" id="vpname">
		<input type="hidden" name="path" id="vppath">
	</form>
	<table style="width: 95%">
		<tr>
			<th width="30%">文件名</th>
			<th width="20%">路径</th>
			<th width="10%">大小</th>
			<th width="10%">类型</th>
			<th width="20%">播放时间</th>
		</tr>
		<c:if test="${path!='/'}">
			<tr>
				<td><img src="img/arrow-up.jpg" style="width: 20px;height: 20px;cursor: pointer;" onclick="backUp('${path}');"/>...</td>
			</tr>
		</c:if>
		<c:forEach items="${list }" var="file">
			<tr align="center">
				<c:choose>
					<c:when test="${file.isFile }">
						<td align="left">
						<c:choose>
							<c:when test="${file.type=='视频' }">
								<a href="javascript:videoPlay('${file.fileName }','${file.direct }/${file.fileName }')">${file.fileName }</a>
							</c:when>
							<c:otherwise>
								<a href="${file.direct }/${file.fileName }">${file.fileName }</a>
							</c:otherwise>
						</c:choose>
						</td>
					</c:when>
					<c:otherwise>
						<td align="left"><a href="javascript:search('','${file.direct }/${file.fileName }')">${file.fileName }</a>
						</td>
					</c:otherwise>
				</c:choose>
					<td>${file.direct }</td>
					<td>${file.size }</td>
					<td>${file.type }</td>
					<td>${file.times }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>