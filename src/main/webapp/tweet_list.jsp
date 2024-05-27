<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ツイート一覧</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
	<div class="container">
		<h1>ツイート一覧</h1>
		<%-- 新規投稿 --%>
		<p>
			<a href="new_tweet.jsp">新規投稿</a>
		</p>

		<%-- メッセージがあれば表示 --%>
		
		<%String message = (String) session.getAttribute("message");%>
		<%if (message != null && !message.isEmpty()) {%>
		<p><%=message%></p>

		<%
		session.removeAttribute("message");
		}
		%>
		
		<%-- JSTLを使用した記述 --%>
		<%--
		<c:if test="${not empty sessionScope.message}">
			<p>${sessionScope.message}</p>
			<c:remove var="message" scope="session" />
		</c:if>
		 --%>

		<%-- ツイート一覧の表示 --%>
		<ul class="tweet-list">
			<c:forEach var="tweet" items="${tweets}">
				<li>
					<div class="tweet-content">
						<p>${tweet.content}</p>
						<p class="tweet-info">投稿者: ${tweet.author} - 投稿日時: ${tweet.postedAt}</p>
						<%-- ツイート削除 --%>
						<form action="tweet_delete" method="post">
							<input type="hidden" name="tweetId" value="${tweet.id}">
							<button type="submit">削除</button>
						</form>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
