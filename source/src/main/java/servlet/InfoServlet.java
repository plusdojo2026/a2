package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;

@WebServlet("/InfoServlet")
public class InfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	// セッションからユーザー情報を取得
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
    	// ログインしていない場合はログイン画面に飛ばす
    	if (user == null) {
    		response.sendRedirect("/a2/LoginServlet");
    		return;
    	}

        request.getRequestDispatcher("/WEB-INF/jsp/info.jsp").forward(request, response);
    }

    
    // 記録編集
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// セッションからユーザー情報を取得
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");

        request.setCharacterEncoding("UTF-8");

        // 情報ページへリダイレクト
        response.sendRedirect("/a2/InfoServlet");
    }
    
}
