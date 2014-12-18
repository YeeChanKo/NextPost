<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>View Your News - NEXT POST</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/stylish-portfolio.css" rel="stylesheet">
<link href="css/mycss.css" rel="stylesheet">
<link href="https://cdn.rawgit.com/openhiun/hangul/master/nanumbarungothic.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<header id="top" class="header">
	<center>
		<div class="text-vertical-center">
			<h1 id="firstspace">NEXT POST LIST</h1>
		</div>
		<c:forEach var="each" items="${AllPosts}">
			<a href="ShowArticle.do?nid=${each.nid}"><button type="button" class="btn btn-dark btn-lg articlebutton">${each.title}</button></a>
		</c:forEach>
	</center>
	</header>
</body>

</html>