package servlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.FriendsDao;
import dto.Friend;
import dto.TrStorage;
import dto.User;

@WebServlet("/FriendListServlet")
public class FriendListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// ログインしていない場合はログイン画面に飛ばす
		if (user == null) {
			response.sendRedirect("/a2/LoginServlet");
			return;
		}
		// ログイン中のユーザーIDを取得
		String userId = user.getUserId();
		
		
		
		//Daoの呼び出し
		FriendsDao fDao = new FriendsDao();
		Gson gson = new Gson();
		
		//<===DAO①===>ユーザのフレンドを検索
		List<Friend>friendSearch =
				fDao.friendSearch
				(new Friend(userId,null,0,null,0,0));
		//リクエストJson格納①
		String friendJson = gson.toJson(friendSearch);
		request.setAttribute("friendJson", friendJson);
		
		
		
		//friendUserIdを取り出す(拡張for文)
		for(Friend friendLoop:friendSearch) {
			String friendUserId = friendLoop.getFriendUserId();
			
			
			//<===DAO②===>フレンドの情報を取得（ユーザー名・アイコン・ポイント）
			List<Friend>frInfo =
					fDao.friendInfo
					(new Friend(null,friendUserId,0,null,0,0));
			//リクエストJson格納②
			String frInfoJson = gson.toJson(frInfo);
			request.setAttribute("frInfoJson", frInfoJson);
			
			
			
			//<===DAO③===>フレンドの最終トレーニングを取得
			List<TrStorage>trSearch =
					fDao.trSearch
					(new TrStorage(friendUserId,0,0,0,0,null,null));
			//リクエストJson格納③
			String trSearchJson = gson.toJson(trSearch);
			request.setAttribute("trSearchJson", trSearchJson);
		}
		
		
		
		// フレンド一覧にフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/friend_list.jsp");
				dispatcher.forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }
}