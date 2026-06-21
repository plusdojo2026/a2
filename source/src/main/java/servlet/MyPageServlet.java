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
import dto.User;

@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MyPageServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//セッション
		HttpSession session = request.getSession();
		//セッションチェック
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect("/a2/LoginServlet");
			return;
		}
		//セッションのuser_idをuserIdに代入
		String userId=(String)session.getAttribute("user_id");		
		String message = (String) session.getAttribute("message");
		if (message != null) {
		    request.setAttribute("message", message);
		    session.removeAttribute("message");
		}

		
		UsersDao uDao = new UsersDao();
		User userInfo = uDao.userInfo(new User
				(0,null,0.0,null,0.0,0,userId,null,0,0,0,null));
	
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("userInfo", userInfo);
		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_page.jsp");
		dispatcher.forward(request, response);
	}

}