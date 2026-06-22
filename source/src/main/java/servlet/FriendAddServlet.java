package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FriendsDao;
import dto.Friend;
import dto.User;

@WebServlet("/FriendAddServlet")
public class FriendAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. ログイン中の自分のユーザーIDを取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("/a2/LoginServlet");
			return;
		}
		String myUserId = user.getUserId(); // 申請を送る人（自分）
		
		// 2. 画面から送られてきた相手のユーザーIDを取得
		request.setCharacterEncoding("UTF-8");
		String targetUserId = request.getParameter("targetUserId");
		
		// 3. DTOに2人のIDを入れる
		Friend frAdd = new Friend();
		frAdd.setUserId(myUserId);           // 自分
		frAdd.setFriendUserId(targetUserId); // 相手
		
		// 4. DAOを呼んで、データベースに登録してもらう
		FriendsDao dao = new FriendsDao();
		dao.friendAdd(frAdd);
		
		// 5. 処理が終わったらメッセージをセットして、フレンド一覧画面に戻る
		session.setAttribute("message", "フレンド申請を送信しました！");
		response.sendRedirect("/a2/FriendListServlet");
	}
}