package servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.FriendsDao;
import dto.Friend;
import dto.FriendSet;
import dto.TrStorage;

@WebServlet("/FriendListServlet")
public class FriendListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("user");
//		
//
//		// ログインしていない場合はログイン画面に飛ばす
//		if (user == null) {
//			response.sendRedirect("/a2/LoginServlet");
//			return;
//		}
//		// ログイン中のユーザーIDを取得
//		String userId = user.getUserId();
		
		String userId="user1";
		
		
		// DaoとGsonの呼び出し
		FriendsDao fDao = new FriendsDao();
		Gson gson = new Gson();
		
		// <===DAO①===> ユーザのフレンド一覧を取得
		List<Friend> friendSearch = 
				fDao.friendSearch(new Friend
						(userId, null, 0, null, 0, 0));
		// <===DAO①===>コンソール確認用
		for (Friend f : friendSearch) {
		    System.out.println("friendUserId = " + f.getFriendUserId());
		}
		
		// 全てのフレンドの詳細データを格納するリストを用意
		List<FriendSet> friendFullList = new ArrayList<>();
		
		// friendUserIdを取り出す(拡張for文)
		for (Friend friendLoop : friendSearch) {
			String friendUserId = friendLoop.getFriendUserId();
			
			// <===DAO②===> フレンドの情報を取得（ユーザー名・アイコン・ポイント）
			List<Friend> frInfo = 
					fDao.friendInfo(new Friend
							(null, friendUserId, 0, null, 0, 0));
			
			// <===DAO②===>コンソール確認用
		    System.out.println("DAO② 取ってこれているかの確認(0or1) = " + frInfo.size());
		    if (!frInfo.isEmpty()) {
		        System.out.println("DAO② userName = " + frInfo.get(0).getUserName());
		    }
			
			
			// <===DAO③===> フレンドの最終トレーニングを取得
			List<TrStorage> trSearch = 
					fDao.trSearch(new TrStorage
							(friendUserId, 0, 0, 0, 0, null, null));
			
			// <===DAO③===>コンソール確認用
			System.out.println("DAO③ trSearch の数 = " + trSearch.size());
			
			
			FriendSet set = new FriendSet();

			set.setFriend(friendLoop);
			set.setFriendInfo(frInfo.isEmpty() ? null : frInfo.get(0));
			set.setLatestTraining(trSearch);

		    friendFullList.add(set);
		}
		
		
		request.setAttribute("friendFullList", friendFullList);
		// JSONに変換してリクエストに格納
		String friendDataJson = gson.toJson(friendFullList);
		request.setAttribute("friendDataJson", friendDataJson);
		
		// フレンド一覧にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/friend_list.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }
}