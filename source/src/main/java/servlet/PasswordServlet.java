package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDao;
import dto.Message;
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
//		HttpSession session = request.getSession();
//		if (session.getAttribute("user_id") == null) {
//			response.sendRedirect("/a2/LoginServlet");
//			return;
//		}
//		//セッションのuser_idをuserIdに代入
//		String userId=(String)session.getAttribute("user_id");
//		
		
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
		String password = request.getParameter("password");
		UsersDao pDao =new UsersDao();
		
		if (pDao.passwordChange(new User
				(0,null,0.0,null,0.0,0,userId,password,0,0,0,null))) { // 変更成功
			request.setAttribute("message", new Message( "パスワードを変更しました。"));
		} else { // 変更失敗
			request.setAttribute("message", new Message("パスワードを変更できませんでした。"));
		}
		// マイページにリダイレクト
		response.sendRedirect("/a2/MyPageServlet");
	}

}