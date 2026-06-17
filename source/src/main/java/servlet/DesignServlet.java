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
import dto.Message;
import dto.User;

@WebServlet("/DesignServlet")
public class DesignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DesignServlet() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/design.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/webapp/LoginServlet");
//			return;
//		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String numberStr = request.getParameter("number");
		int number=Integer.parseInt(numberStr);
		
		// 数字によって振り分ける
		UsersDao bDao = new UsersDao();
		if (number<=7) {
			int iconId = number;
			if (bDao.iconChange(new User
					(0,null,0.0,null,0.0,0,userId,null,iconId,0,0,null))) { // 更新成功
				HttpSession session = request.getSession();
				session.setAttribute("message", "アイコンを更新しました");
				session.removeAttribute("message");
				
			} else { // 更新失敗
				request.setAttribute("message", new Message("アイコンを更新できませんでした。"));
			}
		} else {
			int designId = number-8;
			if (bDao.designChange(new User
					(0,null,0.0,null,0.0,0,userId,null,0,designId,0,null))) { // 更新成功
				request.setAttribute("message", new Message("着せ替えを更新しました。"));
			} else { // 更新失敗
				request.setAttribute("message", new Message("着せ替えを更新できませんでした。"));
			}
		}

		// デザインページにリダイレクト
		response.sendRedirect("/a2/DesignServlet");
	}
}
