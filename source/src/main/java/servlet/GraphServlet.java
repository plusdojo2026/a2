package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

//		
//		//現在の日付を取得
//		LocalDate today = LocalDate.now();
//		
//		//年を取得
//		int year = today.getYear();
//		//今月と来月を取得
//		Month month = today.getMonth();
//		int monthNumber = month.getValue();
//		int nextMonth = month.getValue()+1;
		
		//ログインユーザー情報の取得(仮情報後で直す)
		String userId = "user1";

//直近30日のデータを格納する		
//記録情報のトレーニング内容を取得する	
		StoragesDao TrGraph = new StoragesDao();
		List<Graph> getMonthGraph = TrGraph.getMonthGraph(userId);		
		
//項目ごとにまとめる------------------------
		Map<String, List<Graph> >MonthGraph = new LinkedHashMap<>();
		
		for( Graph graph : getMonthGraph ) {
			String key = graph.getTr_item();
			MonthGraph.putIfAbsent(key, new ArrayList<>());
			MonthGraph.get(key).add(graph);
			System.out.println(key);
			System.out.println(MonthGraph.putIfAbsent(key, new ArrayList<>()));
		}

//直近10日のデータを格納する		
//記録情報のトレーニング内容を取得する	
		List<Graph> getWeekGraph = TrGraph.getWeekGraph(userId);		
		
//項目ごとにまとめる------------------------
		Map<String, List<Graph> > WeekGraph= new LinkedHashMap<>();
		
		for( Graph graph : getWeekGraph ) {
			String key = graph.getTr_item();
			WeekGraph.putIfAbsent(key, new ArrayList<>());
			WeekGraph.get(key).add(graph);
			System.out.println(key);
			System.out.println(WeekGraph.putIfAbsent(key, new ArrayList<>()));
		}		
		
		
//		//記録したことのあるトレーニング項目を検索する
//				StoragesDao ItemGraph = new StoragesDao();
//				List<Graph> gItemList = ItemGraph.getItemGraph(userId,year,monthNumber);
				
		//一言セリフを受け取る
		
		//背景などのユーザー情報を受け取る
		
		
		//JSPに送る
//		request.setAttribute("gItem",gItemList );
		request.setAttribute("MonthGraph",MonthGraph );
		request.setAttribute("WeekGraph",WeekGraph );

		
		
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
