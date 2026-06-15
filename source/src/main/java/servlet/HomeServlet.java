package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoragesDao;
import dto.Storage;
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	//直接servletを実行したときに動くメソッド（リダイレクトもこっちが動く）
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
	
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webappAns/LoginServlet");
			return;
		}*/
		
		//ここで色んな処理をする（daoに処理を依頼して、requestにセットしたりする）
//		豆友の申請が来ているかだけ。なぜなら表示するものはそれだけ。最初にうけとるものだけ
//		sessionから、ログインしているユーザーの情報を取得する
		
//		そのログインしているユーザーのIDを使って、豆のリクエスト情報を取得する
		
		
		

				
				
		// ホームページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}
	//<form method = "POST" でservletを指定したときに動くメソッド
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		入力した情報をもらってくる
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		
		
		String user_id = request.getParameter("user_id");
		double weight = Double.parseDouble(request.getParameter("weight"));
		double fat = Double.parseDouble(request.getParameter("fat"));
		String memo = request.getParameter("memo"); 
		int stamp = Integer.parseInt(request.getParameter("stamp"));
		
		String dayStr = request.getParameter("date");
		LocalDate day=null;
		if(!dayStr.equals("")) {
			day = LocalDate.parse(dayStr);
		}
		
		int id = Integer.parseInt(request.getParameter("id"));
		int tr_id = Integer.parseInt(request.getParameter("tr_id"));
		int tr_weight = Integer.parseInt(request.getParameter("tr_weight"));
		int counts = Integer.parseInt(request.getParameter("counts"));
		int sets = Integer.parseInt(request.getParameter("sets"));
		
		
		StoragesDao sDao = new StoragesDao();
		List<Storage> cardList = sDao.insert(new Storage(user_id, weight, fat, memo, stamp, id, tr_id, tr_weight, counts, sets ));
		
	}

}
