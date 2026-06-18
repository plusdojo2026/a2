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

@WebServlet("/PasswordServlet")
public class PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PasswordServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
//		if (session.getAttribute("user_id") == null) {
//			response.sendRedirect("/a2/LoginServlet");
//			return;
//		}
//		//セッションのuser_idをuserIdに代入
//		String userId=(String)session.getAttribute("user_id");
		
		String message = (String) session.getAttribute("message");
		if (message != null) {
		    request.setAttribute("message", message);
		    session.removeAttribute("message");
		}
		
		String userId="User1";//本来はセッションから取得
		
		UsersDao uDao = new UsersDao();
		User userInfo = uDao.userInfo(new User
				(0,null,0.0,null,0.0,0,userId,null,0,0,0,null));
	
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("userInfo", userInfo);
		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/password.jsp");
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
		String userId = request.getParameter("userId");
		String inputPassword = request.getParameter("inputPassword");
		String password = request.getParameter("newPassword");
		UsersDao pDao =new UsersDao();
		//フォワード用
		UsersDao uDao = new UsersDao();
		User userInfo = uDao.userInfo(
		    new User(0,null,0.0,null,0.0,0,userId,null,0,0,0,null)
		);
		HttpSession session = request.getSession();
		
		if(pDao.passwordCheck(userId,inputPassword)) {
			if (pDao.passwordChange(new User
					(0,null,0.0,null,0.0,0,userId,password,0,0,0,null))) { // 変更成功
				session.setAttribute("message", "パスワードを変更しました。");
				// マイページにリダイレクト
				response.sendRedirect("/a2/MyPageServlet");
			} else { // 変更失敗
				session.setAttribute("message", "パスワードに失敗しました。");
				request.setAttribute("userInfo", userInfo);
				// パスワードページにリダイレクト
				response.sendRedirect("/a2/PasswordServlet");
			}

		}else {
			session.setAttribute("message", "現在のパスワードに誤りがありました。");
			request.setAttribute("userInfo", userInfo);
			// パスワードページにリダイレクト
			response.sendRedirect("/a2/PasswordServlet");
		}
	}

}