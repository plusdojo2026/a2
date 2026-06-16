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

@WebServlet("/GroupListServlet")
public class GroupListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Map<String, Object>> groupList = new ArrayList<>();
	    
	   
	    Map<String, Object> g1 = new HashMap<>();
	    g1.put("groupId", "1"); 
	    g1.put("GroupName", "さやえんどう");
	    
	    List<Map<String, String>> members1 = new ArrayList<>();
	    
	    Map<String, String> m1 = new HashMap<>();
	    m1.put("icon", "①");
	    m1.put("name", "枝豆");
	    members1.add(m1);
	    
	    Map<String, String> m2 = new HashMap<>();
	    m2.put("icon", "②");
	    m2.put("name", "のっけ");
	    members1.add(m2);
	    
	    Map<String, String> m3 = new HashMap<>();
	    m3.put("icon", "③");
	    m3.put("name", "大豆");
	    members1.add(m3);
	    
	    
	    g1.put("memberList", members1);
	    groupList.add(g1);
        
	   
	    Map<String, Object> g2 = new HashMap<>();
	    g2.put("groupId", "2");
	    g2.put("GroupName", "イソフラボンボンボン");
	    
	    List<Map<String, String>> members2 = new ArrayList<>();
	    
	    Map<String, String> m4 = new HashMap<>();
	    m4.put("icon", "🟢");
	    m4.put("name", "そら豆");
	    members2.add(m4);
	    
	    Map<String, String> m5 = new HashMap<>();
	    m5.put("icon", "🔴");
	    m5.put("name", "小豆");
	    members2.add(m5);
	    
	    g2.put("memberList", members2);
	    groupList.add(g2);
        
	   
        request.setAttribute("groupList", groupList);
        request.getRequestDispatcher("/WEB-INF/jsp/group_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }
}