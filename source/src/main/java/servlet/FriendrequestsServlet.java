package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FriendrequestsServlet")
public class FriendrequestsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        String targetUserId = request.getParameter("targetUserId");
        String action = request.getParameter("action");

        System.out.println("★フレンド申請の判定処理が動きました！(FriendrequestsServlet)");
        
        if ("approve".equals(action)) {
            System.out.println("ユーザーID [" + targetUserId + "] の申請を【承認】しました！🎉");
        } else if ("reject".equals(action)) {
            System.out.println("ユーザーID [" + targetUserId + "] の申請を【拒否】しました！🙅‍♂️");
        }

        // 処理が終わったら、リクエスト一覧画面（RequestServlet）に引き返す
        response.sendRedirect(request.getContextPath() + "/RequestServlet");
    }
}