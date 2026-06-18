package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoragesDao;
import dto.Graph;

	
/**
 * Servlet implementation class GraphServlet
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
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/a2/LoginServlet");
//			return;
//		}
		
		// リクエストパラメータを取得する,後で項目を増やす
		request.setCharacterEncoding("UTF-8");
//		//その日のセリフを取得する
//		String  word_of_day= request.getParameter("word_of_day");		
//		//トレーニング項目を取得する
//		String tr_item= request.getParameter("tr_item");
//		//ユーザー情報と初期画面表示のデータを取得する
//		String user_name= request.getParameter("user_name");
//		String user_id= request.getParameter("user_id");
//		String desin_id= request.getParameter("desin_id");
//		String icon_id= request.getParameter("icon_id");
//
//		//記録情報を取得する
//		double weight= Double.parseDouble(request.getParameter("weight"));
//		double fat= Double.parseDouble(request.getParameter("fat"));
//		String memo= request.getParameter("memo");
//		LocalDate date= LocalDate.parse(request.getParameter("date"));
//		//トレーニング内容を取得する
//		int id= Integer.parseInt(request.getParameter("id"));
//		int tr_id= Integer.parseInt(request.getParameter("tr_id"));
//		int tr_weight= Integer.parseInt(request.getParameter("tr_weight"));
//		int counts= Integer.parseInt(request.getParameter("counts"));
//		int set= Integer.parseInt(request.getParameter("set"));
//		
		//現在の日付を取得
		LocalDate today = LocalDate.now();
		
		//年を取得
		int year = today.getYear();
		//今月と来月を取得
		Month month = today.getMonth();
		int monthNumber = month.getValue();
//		int nextMonth = month.getValue()+1;
		
		//ログインユーザー情報の取得(仮情報後で直す)
		String userId = "user1";
		
		//記録情報のトレーニング内容を取得する	
		StoragesDao TrGraph = new StoragesDao();
		List<Graph> getGraphList = TrGraph.getGraphList(userId,year,monthNumber);
		
		//記録したことのあるトレーニング項目を検索する
		StoragesDao ItemGraph = new StoragesDao();
		List<Graph> gItemList = ItemGraph.getItemGraph(userId,year);
		
		//一言セリフを受け取る
		
		//ユーザー情報を受け取る
		
		
		//JSPに送る
		request.setAttribute("GraphList",getGraphList );
		request.setAttribute("gItem",gItemList );
		
		
		
		
		// 成長記録ページにフォワードする
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
//	HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/a2/LoginServlet");
//			return;
//		}


		
		
		
		
		
	}
//dopostここまで
}
