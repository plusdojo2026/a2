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

// ★ポイント1：前の画面のリンク先と名前を合わせました！
@WebServlet("/GroupAddServlet")
public class GroupAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        // ★ポイント2：グループIDの受け取りと、フレンドのダミーデータ作成
        
        // 1. 前の画面（モーダル）から渡されたバトン（グループID）を受け取る
        String groupId = request.getParameter("groupId");
        
        // 2. 追加候補となる「自分のフレンド一覧」のダミーデータを作る
        List<Map<String, String>> friendList = new ArrayList<>();
        
        Map<String, String> f1 = new HashMap<>();
        f1.put("userId", "10154");
        f1.put("name", "豆");
        f1.put("icon", "④");
        friendList.add(f1);
        
        Map<String, String> f2 = new HashMap<>();
        f2.put("userId", "102547");
        f2.put("name", "マメ");
        f2.put("icon", "⑥");
        friendList.add(f2);

        // 3. JSPにデータを渡す（グループIDも忘れずに次の画面へ持っていく！）
        request.setAttribute("groupId", groupId);
        request.setAttribute("friendList", friendList);
        
		// 4. JSP（画面）へ移動
		request.getRequestDispatcher("/WEB-INF/jsp/group_member_add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // ★ポイント3：追加ボタンが押された時の処理
        
        // 1. どのグループに追加するかを受け取る
        String groupId = request.getParameter("groupId");
        
        // 2. チェックボックスで選ばれた「追加したいフレンドのID」を配列で全部受け取る
        String[] addMemberIds = request.getParameterValues("addMemberIds");
        
        // （本来はここでデータベースにINSERTする処理を書きます）
        System.out.println("グループID：" + groupId + " にメンバーを追加します！");
        if(addMemberIds != null) {
            for(String id : addMemberIds) {
                System.out.println("追加するユーザーID：" + id);
            }
        }
        
        // 3. 終わったら、グループ一覧画面に戻す
        response.sendRedirect("GroupListServlet");
    }
}