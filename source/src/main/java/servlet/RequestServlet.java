package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 💡 画面を開く（GET）処理だけを担当します
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // --- 1. フレンド申請のダミーデータ ---
        List<Map<String, String>> friendRequests = new ArrayList<>();
        Map<String, String> f1 = new HashMap<>();
        f1.put("userId", "448444");
        f1.put("name", "マメ");
        f1.put("icon", "⑤");
        friendRequests.add(f1);

        // --- 2. グループ招待のダミーデータ ---
        List<Map<String, String>> groupRequests = new ArrayList<>();
        Map<String, String> g1 = new HashMap<>();
        g1.put("groupId", "265661");
        g1.put("groupName", "さやえんどう");
        g1.put("icon", "④");
        g1.put("name", "マメ");
        groupRequests.add(g1);

        // --- 3. JSPへデータを渡す ---
        request.setAttribute("friendRequests", friendRequests);
        request.setAttribute("groupRequests", groupRequests);

        // --- 4. リクエスト一覧画面（JSP）へ移動 ---
        // 🌟 ここを request.jsp に修正しました！
        request.getRequestDispatcher("/WEB-INF/jsp/request.jsp").forward(request, response);
    }
}