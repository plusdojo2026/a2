package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDao;
import dto.User;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EditProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//セッション
		HttpSession session = request.getSession();
		//セッションチェック
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect("/a2/LoginServlet");
			return;
		}
		//セッションのuser_idをuserIdに代入
		String userId=(String)session.getAttribute("user_id");		
		String message = (String) session.getAttribute("message");
		if (message != null) {
		    request.setAttribute("message", message);
		    session.removeAttribute("message");
		}
		
		UsersDao uDao = new UsersDao();
		User userInfo = uDao.userInfo(new User
				(0,null,0.0,null,0.0,0,userId,null,0,0,0,null));
	
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("userInfo", userInfo);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/edit_profile.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("user_id") == null) {
//			response.sendRedirect("/a2/LoginServlet");
//			return;
//		}
		
		request.setCharacterEncoding("UTF-8");
		String userName			= request.getParameter("userName");
		double height 			= Double.parseDouble(request.getParameter("height"));
		String gender 			= request.getParameter("gender");
		double targetWeight	= Double.parseDouble(request.getParameter("targetWeight"));
		String userId 			= request.getParameter("userId");
		UsersDao pDao =new UsersDao();
		HttpSession session = request.getSession();
				
		if (pDao.userInfoChange(new User
				(0,userName,height,gender,targetWeight,0,userId,null,0,0,0,null))) { // 変更成功
			session.setAttribute("message", "基本情報を変更出来ました。");
		} else { // 変更失敗
			session.setAttribute("message", "基本情報を変更出来ませんでした。");
		}
		// パスワードページにリダイレクト
		response.sendRedirect("/a2/EditProfileServlet");
	}
}