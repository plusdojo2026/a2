package servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import java.util.List;
//import dao.FriendsDao;
//import model.User; //
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
	    
	    
	    List<String> icons1 = new ArrayList<>();
	    icons1.add("①");
	    icons1.add("②");
	    icons1.add("③"); 
	   
	    g1.put("iconList", icons1);

	    groupList.add(g1);
        
	    Map<String, Object> g2 = new HashMap<>();
	    g2.put("groupId", "2");
	    g2.put("GroupName", "イソフラボンボンボン");
	    List<String> icons2 = new ArrayList<>();
	    icons2.add("🟢");
	    icons2.add("🔴");
	    g2.put("iconList", icons2);
	    groupList.add(g2);
        request.setAttribute("groupList", groupList);
        
	request.getRequestDispatcher("/WEB-INF/jsp/group_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }
}