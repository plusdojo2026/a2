package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StoragesDao;
import dao.WordsDao;
import dto.Graph;
import dto.User;
import dto.Word;

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
		HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("/a2/LoginServlet");
			return;
		}
		
		// リクエストパラメータを取得する,後で項目を増やす
		request.setCharacterEncoding("UTF-8");
		
		//ログインユーザー情報の取得(仮情報後で直す)
//		String userId = "user1";
		String userId = user.getUserId();
		
		
//直近30日のデータを格納する		
//記録情報のトレーニング内容を取得する	
		StoragesDao TrGraph = new StoragesDao();
		List<Graph> getMonthGraph = TrGraph.getMonthGraph(userId);		
		
//項目ごとにまとめる------------------------
		Map<String, Map<String , List<Graph>>>MonthGraph = new LinkedHashMap<>();
		
		for( Graph graph : getMonthGraph ) {
			//項目名
			String key = graph.getTr_item();
			//重量
			String weight = Integer.toString(graph.getTr_weight());
			
			MonthGraph.putIfAbsent(key, new HashMap<String , List<Graph>>());
			
			MonthGraph.get(key).putIfAbsent(weight, new ArrayList<Graph>());

			MonthGraph.get(key).get(weight).add(graph);
//			System.out.println(key);
//			System.out.println(MonthGraph.putIfAbsent(key, new ArrayList<>()));
		}

//直近10日のデータを格納する		
//記録情報のトレーニング内容を取得する	
		List<Graph> getWeekGraph = TrGraph.getWeekGraph(userId);		
		
//項目ごとにまとめる------------------------
		Map<String, Map<String , List<Graph>>> WeekGraph= new LinkedHashMap<>();
		
		for( Graph graph : getWeekGraph ) {
			//項目名
			String key = graph.getTr_item();
			//重量
			String weight = Integer.toString(graph.getTr_weight());
			
			WeekGraph.putIfAbsent(key, new HashMap<String , List<Graph>>());
			
			WeekGraph.get(key).putIfAbsent(weight, new ArrayList<Graph>());

			WeekGraph.get(key).get(weight).add(graph);
//			System.out.println(key);
//			System.out.println(WeekGraph.putIfAbsent(key, new ArrayList<>()));
		}		
		
				
		//一言セリフを受け取る
		WordsDao selectedWord = new WordsDao();
		List<Word> SelectWord = selectedWord.SelectWord();
		
		//背景などのユーザー情報を受け取る
		
		
		//JSPに送る
//		request.setAttribute("gItem",gItemList );
		request.setAttribute("MonthGraph",MonthGraph );
		request.setAttribute("WeekGraph",WeekGraph );
		request.setAttribute("SelectWord",SelectWord );

		
		
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
