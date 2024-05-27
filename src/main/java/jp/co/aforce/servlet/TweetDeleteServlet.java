package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.dao.TweetDAO;

@WebServlet("/tweet_delete")
public class TweetDeleteServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータから削除するツイートのIDを取得
		int tweetId = Integer.parseInt(request.getParameter("tweetId"));

		// データベースからツイートを削除する処理についてはTweetDAOから行う
		// TweetDAOのインスタンスを作成する
		TweetDAO tweetDAO = new TweetDAO();
		// メッセージを格納する変数
		String message;

		try {
			// TweetDAOのdeleteTweetメソッドを使用する
			tweetDAO.deleteTweet(tweetId);
			message = "ツイートが削除されました";
		} catch (Exception e) {
			message = "ツイートが削除されませんでした";
			e.printStackTrace();
		}

		// セッション属性にメッセージを格納する
		request.getSession().setAttribute("message", message);
		// ツイート一覧ページにリダイレクトする
		response.sendRedirect("tweet_list");
	}
}
