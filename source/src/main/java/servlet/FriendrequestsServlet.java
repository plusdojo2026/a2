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

import dao.FriendsDao;
import dto.Friend;
import dto.User;


@WebServlet("/FriendrequestsServlet")
public class FriendrequestsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		//メッセージの判定と削除
		if (message != null) {
		    request.setAttribute("message", message);
		    session.removeAttribute("message");
		    System.out.println("messageをリクエストに移動し、セッションから削除しました");
		}
		// DaoとGsonの呼び出し
		FriendsDao fDao = new FriendsDao();

		
		//受けているリクエストを取得
		List<Friend> rqSearch = 
			fDao.requestSearch(new Friend
				(userId, null, 0, null, 0, 0));
		//コンソール確認用
		System.out.println("DAO①====="+userId+"のフレンド一覧=====");
		for (Friend f : rqSearch) {
		    System.out.println("friendUserId = " + f.getFriendUserId());
		}
		//申請中のリクエストを取得
		List<Friend> fmSearch = 
			fDao.requestFromMeSearch(new Friend
				(userId, null, 0, null, 0, 0));
		//コンソール確認用
		System.out.println("DAO①====="+userId+"のフレンド一覧=====");
		for (Friend f : fmSearch) {
		    System.out.println("friendUserId = " + f.getFriendUserId());
		}
		
		request.setAttribute("rqSearch", rqSearch);
		request.setAttribute("fmSearch", fmSearch);
		
		// フレンド一覧にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/request.jsp");
		dispatcher.forward(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String UserId = request.getParameter("targetUserId");
        String action = request.getParameter("action");

        System.out.println("★フレンド申請の判定処理が動きました！(FriendrequestsServlet)");
        
        if ("approve".equals(action)) {
        	
            System.out.println("ユーザーID [" + UserId + "] の申請を【承認】しました！🎉");
        } else if ("reject".equals(action)) {
        	
            System.out.println("ユーザーID [" + UserId + "] の申請を【拒否】しました！🙅‍♂️");
        }

        // 処理が終わったら、リクエスト一覧画面（RequestServlet）に引き返す
        response.sendRedirect(request.getContextPath() + "/RequestServlet");
    }
}