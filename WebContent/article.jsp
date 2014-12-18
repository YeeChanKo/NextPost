<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>${article.title} - NEXT POST</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/stylish-portfolio.css" rel="stylesheet">
<link href="css/mycss.css" rel="stylesheet">
<link href="https://cdn.rawgit.com/openhiun/hangul/master/nanumbarungothic.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<header id="top" class="header">
	<center>
		<div class="well wellarticle">
			<h1>${article.title}</h1>

			<p>
				by <a href="#"> ${article.writer} </a>
			</p>

			<p>
				<span class="glyphicon glyphicon-time"></span> Posted on ${article.creation}
			</p>

			<!-- Post Content -->
			<div class="well wellcomment articlecontent">
				<pre>${article.content}</pre>
			</div>

			<c:forEach var="each" items="${comments}" varStatus="loopStatus">
				<div class="well wellcomment">
					<div class="commentLeft">
						${loopStatus.index+1}. <a href="#">${each.writer}</a>님의 댓글:
					</div>
					<div class="commentRight">
                        <pre>"${each.content}"</pre>
                    </div>
                </div>
			</c:forEach>

			<!-- Comments Form -->
			<div class="well wellcomment">
				<h4>Leave a Comment</h4>
				<form method="post" action="PostComment.do?nid=${article.nid}">
					<div class="form-group">
						<input class="form-control" placeholder="작성자" name="WRITER">
						<textarea class="form-control" placeholder="내용" name="CONTENT"></textarea>
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</center>
	</header>
</body>

</html>