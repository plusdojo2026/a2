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

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EditProfileServlet() {
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
		
		if (pDao.userInfoChange(new User
				(0,userName,height,gender,targetWeight,0,userId,null,0,0,0,null))) { // 変更成功
			request.setAttribute("message", new Message("ユーザー情報を変更しました。"));
		} else { // 変更失敗
			request.setAttribute("message", new Message("ユーザー情報を変更できませんでした。"));
		}
		// マイページにリダイレクト
		response.sendRedirect("/a2/MyPageServlet");
	}
}