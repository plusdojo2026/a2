package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDao;
import dto.User;
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("user_id");/* <input type="text" name="xxxx"←この部分> */
		String password = request.getParameter("password");
		String userName = request.getParameter("user");
		String gender = request.getParameter("gender");
		Double height = Double.parseDouble(request.getParameter("height")); 
		Double targetWeight = Double.parseDouble(request.getParameter("target_weight"));
		//今日の日付を取得
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String dateTime = now.format(formatter);
		
		// 登録処理を行う
		UsersDao uDao = new UsersDao();
		
		User us = new User(0,  userName,  height,  gender,  targetWeight,  0,
				 userId,  password,  0,  0,  0,  dateTime);
		if (uDao.insert(us)) { // 登録成功
			// 登録成功したらセッションにユーザー情報を入れる
		    HttpSession session = request.getSession();
		    session.setAttribute("user", us);
			// ホームサーブレットにリダイレクトする
			response.sendRedirect("/a2/HomeServlet");
		} else { // 登録失敗			
			request.setAttribute("result","ユーザーIDが重複しています");
			// リクエストスコープに入力値格納して表示されたままにする
			request.setAttribute("user_id", userId);
		    request.setAttribute("password", password);
		    request.setAttribute("user", userName);
		    request.setAttribute("gender", gender);
		    request.setAttribute("height", height);
		    request.setAttribute("target_weight", targetWeight);
			
			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	
}