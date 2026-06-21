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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.FriendsDao;
import dao.TrItemsDao;
import dto.Friend;
import dto.FriendSet;
import dto.TrItem;
import dto.TrStorage;

@WebServlet("/FriendListServlet")
public class FriendListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
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
		
		//セッションからメッセージと検索したいuserのidを取り出して削除
		String message = (String) session.getAttribute("message");
		String searchId = (String) session.getAttribute("searchId");
		
		System.out.println("=== セッション確認開始 ===");
		System.out.println("message: " + message);
		System.out.println("searchId: " + searchId);
		
		if (message != null) {
		    request.setAttribute("message", message);
		    session.removeAttribute("message");
		    System.out.println("messageをリクエストに移動し、セッションから削除しました");
		}
		//anserに入ってるuserIdを取り出して検索してリクエストに入れる
		if (searchId != null) {
			String sId = searchId;
			session.removeAttribute("searchId");
			System.out.println("検索用ID: " + sId);
			Friend searchAns = 
					fDao.search(new Friend
							(sId, null, 0, null, 0, 0));
			System.out.println("検索条件作成: " + searchAns);
			
			if(searchAns != null) {
				request.setAttribute("searchAns", searchAns);
				//JSONに変換
				String searchAnsJson = gson.toJson(searchAns);
				//requestに検索結果を格納
				request.setAttribute("searchAnsJson", searchAnsJson);
				System.out.println("検索結果を request に格納");
			}else {
				request.setAttribute("message", "ユーザーが見つかりませんでした。");
				System.out.println("検索結果なし");
			}
		}
		System.out.println("=== 処理終了 ===");
		
		
		// <===一覧用DAO①===> ユーザのフレンド一覧を取得
		List<Friend> friendSearch = 
				fDao.friendSearch(new Friend
						(userId, null, 0, null, 0, 0));
		// <===一覧用DAO①===>コンソール確認用
		System.out.println("DAO①====="+userId+"のフレンド一覧=====");
		for (Friend f : friendSearch) {
		    System.out.println("friendUserId = " + f.getFriendUserId());
		}
		
		// 全てのフレンドの詳細データを格納するリストを用意
		List<FriendSet> friendFullList = new ArrayList<>();
		
		// friendUserIdを取り出す(拡張for文)
		for (Friend friendLoop : friendSearch) {
			String friendUserId = friendLoop.getFriendUserId();
			
			// <===一覧用DAO②===> フレンドの情報を取得（ユーザー名・アイコン・ポイント）
			List<Friend> frInfo = 
					fDao.friendInfo(new Friend
							(null, friendUserId, 0, null, 0, 0));
			
			// <===一覧用DAO②===>コンソール確認用
		    System.out.println("====="+friendUserId+"の情報=====");
		    if (!frInfo.isEmpty()) {
		        System.out.println("DAO②|| userName = " + frInfo.get(0).getUserName());
		    }
			
			
			// <===一覧用DAO③===> フレンドの最終トレーニングを取得
			List<TrStorage> trSearch = 
					fDao.trSearch(new TrStorage
							(friendUserId, null, 0, 0, 0, null, null));
			
			// <===一覧用DAO③===>コンソール確認用
			System.out.println("DAO③|| トレーニングの数 = " + trSearch.size());
			
			
			//FriendSetの呼び出しとセット
			FriendSet set = new FriendSet();
			set.setFriend(friendLoop);
			set.setFriendInfo(frInfo.isEmpty() ? null : frInfo.get(0));
			set.setLatestTraining(trSearch);
			
		    friendFullList.add(set);
		}
		TrItemsDao trItemDao = new TrItemsDao();
		List<TrItem> itemList = trItemDao.getAllTrainingItems();
		
		request.setAttribute("friendFullList", friendFullList);
		// JSONに変換してリクエストに格納
		String friendDataJson = gson.toJson(friendFullList);
		String itemListJson = gson.toJson(itemList);
		
		//リクエストに
		request.setAttribute("friendDataJson", friendDataJson);
		request.setAttribute("itemListJson", itemListJson);
		
		// フレンド一覧にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/friend_list.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
//		if (session.getAttribute("user_id") == null) {
//			response.sendRedirect("/a2/LoginServlet");
//			return;
//		}
		request.setCharacterEncoding("UTF-8");
		String searchId = request.getParameter("searchId");
		
		//セッションに検索用user_idを格納
		session.setAttribute("searchId", searchId);
		// パスワードページにリダイレクト
		response.sendRedirect("/a2/FriendListServlet");
		
    }
}