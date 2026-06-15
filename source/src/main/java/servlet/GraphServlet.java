package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/GraphServlet")
public class GraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
//dogetここから
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}
		// 成長記録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/graph.jsp");
		dispatcher.forward(request, response);
		
		// リクエストパラメータを取得する,後で項目を増やす
		request.setCharacterEncoding("UTF-8");
		//今日の豆知識を取得する
		
		//その日のセリフを取得する
		
		//トレーニング項目を取得する
		
		//記録情報を取得する
		
		
		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/graph.jsp");
		dispatcher.forward(request, response);
	}
	
//dogetここまで
//dopostここから
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
	HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}


		
		
		
		
		
	}
//dopostここまで
}
