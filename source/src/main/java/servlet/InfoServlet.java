package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.KnowledgesDao;
import dto.Knowledge;
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
    	
    	// 今日の豆知識を取得
    	KnowledgesDao dao = new KnowledgesDao();
    	Knowledge todayWord = dao.getTodayWord();
    	
    	if (todayWord != null) {
    	    request.setAttribute("todayTrivia", todayWord.getTrivia());
    	}
    	
    	// 今日のレシピを取得
    	Knowledge todayRecipe = dao.getTodayRecipe();
        if (todayRecipe != null) {
            request.setAttribute("recipe", todayRecipe);
        }

        request.getRequestDispatcher("/WEB-INF/jsp/info.jsp").forward(request, response);
    }

    
}
