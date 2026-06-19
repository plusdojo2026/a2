package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoragesDao;
import dao.TrItemsDao;
import dto.Storage;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//直接servletを実行したときに動くメソッド（リダイレクトもこっちが動く）
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		
////		TrItemsDaoをインスタンス化するnewする
		
		TrItemsDao trdao = new TrItemsDao();
//		
//
////		トレーニング項目が items に入った
		List<String> items = trdao.getTrainingItems();
//	
//
		request.setAttribute("itemList", items);

		//IDが削除された場合、同じIDでログインしてホームに飛べないようにする
		
//		String userId = "mamemame01";
//		
//		// 登録処理を行う
//		UsersDao uDao = new UsersDao();
//		int logocal = uDao.getLogical(userId);
//		
		
		
		
		//スタンプを取得している
//		String userId = request.getParameter();
		
		StoragesDao stdao = new StoragesDao();
		
		List<Integer> stampList = stdao.getStampList();

		request.setAttribute("stampList", stampList);
		
		
//		System.out.println(stampList);
		
		
		
		
		/*
	
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webappAns/LoginServlet");
			return;
		}*/
		
		//ここで色んな処理をする（daoに処理を依頼して、requestにセットしたりする）
		
		

		// メニューページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
		
		
		
	}
	//<form method = "POST" でservletを指定したときに動くメソッド
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		System.out.println("HomeServlet doPost開始");
		
		
		
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		
		//項目追加した分の数を取ってきている
		int coun = Integer.parseInt(request.getParameter("coun"));
		
		
		//もらってきたデータを登録するアレイリスト
		ArrayList<Storage> detalist = new ArrayList<>();
		
		
		
		//追加項目を受け取る
				for (int i = 0; i <= coun; i++) {
					
					
					
					Storage  dto = new Storage();
					
					
					dto.setTr_weight(Integer.parseInt(request.getParameter("tr_weight"+ i)));
					dto.setCounts(Integer.parseInt(request.getParameter("counts"+ i)));
					dto.setSets(Integer.parseInt(request.getParameter("sets"+ i)));
					dto.setTrItem(request.getParameter("it"+ i));
					
					
					TrItemsDao trDao = new TrItemsDao();
					
					//ここでとってきた名前をもとに
					String trItem = request.getParameter("it" + i);
					
					//名前がIDにかわる
					int trId = trDao.getTrIdByItem(trItem);
					
					/* detalist.add(dto); */
					
					dto.setTr_id(trId);
					detalist.add(dto);
					
				}
				
				
				
				 System.out.println("データ件数: " + detalist.size()); 
				
		
		// ユーザーIDなど
		int storageId = Integer.parseInt(request.getParameter("storage_id"));
		String user_id = request.getParameter("user_id");
		
		
	
		//もともと表示がある項目を受け取る
		double weight = Double.parseDouble(request.getParameter("weight"));
		double fat = Double.parseDouble(request.getParameter("fat"));
		String comments = request.getParameter("comments");
		
		
		String memo = request.getParameter("memo");
		int stamp = Integer.parseInt(request.getParameter("stamp"));
		
		
		String dateStr = request.getParameter("date");
		LocalDate date=null;
		if(!dateStr.equals("")) {
			date = LocalDate.parse(dateStr);
		}
		
		
		
		
		
		
		
		
		
		//トレーニングid、tr_itemを取ってくるためのもの
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		/*
		 * int tr_id = Integer.parseInt(request.getParameter("it"+ i)); int tr_weight =
		 * Integer.parseInt(request.getParameter("tr_weight" + i)); int counts =
		 * Integer.parseInt(request.getParameter("counts" + i)); int sets =
		 * Integer.parseInt(request.getParameter("sets" + i));
		 */
	
	
	}

}
