package servlet;
import java.io.IOException;

//import java.util.List;
//import dao.FriendsDao;
//import model.User; //
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FriendsDao;
import dto.User;

@WebServlet("/FriendDeleteServlet")
public class FriendDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//セッション
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// ログインしていない場合はログイン画面に飛ばす
		if (user == null) {
			response.sendRedirect("/a2/LoginServlet");
			return;
		}
		//セッションのuserをuserIdに代入
		String userId = user.getUserId();
		String message = (String) session.getAttribute("message");
		if (message != null) {
		    request.setAttribute("message", message);
		    session.removeAttribute("message");
		}
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String[] friendUserIds = request.getParameterValues("deleteIds");
		//ブーリアンの初期化
		boolean success = true;
		FriendsDao fDao = new FriendsDao();
		//入ってる分だけ回す
		if (friendUserIds != null) {
			for (String friendUserId : friendUserIds) {
				boolean result = fDao.friendDelete(userId, friendUserId);
				if (!result) {
					success = false;
				}
			}
		}
		if(success==true) {
			session.setAttribute("message", "フレンドを削除しました。");
		}else {
			session.setAttribute("message", "フレンドを削除出来ませんでした。");
		}
		
		response.sendRedirect("/a2/FriendListServlet");
    }
}