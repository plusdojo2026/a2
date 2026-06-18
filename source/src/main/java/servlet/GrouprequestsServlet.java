package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 💡 URLとクラス名が新しくなりました！
@WebServlet("/GrouprequestsServlet")
public class GrouprequestsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        String groupId = request.getParameter("groupId");
        String action = request.getParameter("action");

        System.out.println("★グループ招待の判定処理が動きました！(GrouprequestsServlet)");
        
        if ("approve".equals(action)) {
            System.out.println("グループID [" + groupId + "] への招待を【承認（参加）】しました！🤝");
        } else if ("reject".equals(action)) {
            System.out.println("グループID [" + groupId + "] への招待を【拒否（辞退）】しました！👋");
        }

        // 処理が終わったら、リクエスト一覧画面（RequestServlet）に引き返す
        response.sendRedirect(request.getContextPath() + "/RequestServlet");
    }
}