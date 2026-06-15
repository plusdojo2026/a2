package servlet;
import java.io.IOException;
//import java.util.List;
//import dao.FriendsDao;
//import model.User; //
import java.util.ArrayList; // テスト
import java.util.HashMap;    // テスト
import java.util.List;       // テスト
import java.util.Map;        // テスト

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FriendListServlet")
public class FriendListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//テスト	
List<Map<String, String>> friendList = new ArrayList<>();
        
        Map<String, String> f1 = new HashMap<>();
        f1.put("userId", "123456");
        f1.put("Name", "枝豆");
        f1.put("Point", "1200");
        friendList.add(f1);
        
        Map<String, String> f2 = new HashMap<>();
        f2.put("userId", "123458");
        f2.put("Name", "のっけ");
        f2.put("Point", "500");
        friendList.add(f2);
        
        
        request.setAttribute("friendList", friendList);
        
	//テスト
        
	request.getRequestDispatcher("/WEB-INF/jsp/friend_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }
}