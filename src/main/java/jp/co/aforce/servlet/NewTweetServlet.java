package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.dao.TweetDAO;

@WebServlet("/new_tweet")
public class NewTweetServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String content = request.getParameter("content");
		String author = request.getParameter("author");

		TweetDAO tweetDAO = new TweetDAO();
		String message;

		try {
			tweetDAO.addTweet(content, author);
			message = "ツイートが投稿されました";
		} catch (Exception e) {
			e.printStackTrace();
			message = "ツイートの投稿に失敗しました";
		}

		request.getSession().setAttribute("message", message);
		response.sendRedirect("tweet_list");
	}
}
