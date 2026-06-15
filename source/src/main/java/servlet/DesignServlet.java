package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/a2/LoginServlet");
//			return;
//		}
//		//DAOが出来たら変える。
//		UsersDao userDao = new UsersDao();
//		List<User> userinfo = userDao.info(new User());
		
	
//	
//		// 検索結果をリクエストスコープに格納する
//		request.setAttribute("userinfo", userinfo);
		
		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/design.jsp");
		dispatcher.forward(request, response);
	}

}