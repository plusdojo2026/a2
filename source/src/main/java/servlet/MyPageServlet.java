package servlet;



@WebServlet("/MyPageServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MyPageServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/a2/LoginServlet");
			return;
		}
		
		UserDAO userDao = new UserDAO();
		List<User> userinfo = userDao.list(new User());
		
	
	
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("userinfo", userinfo);
		
		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);
	}

}