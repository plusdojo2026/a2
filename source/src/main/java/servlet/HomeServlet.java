package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SavesDao;
import dao.StoragesDao;
import dao.TrItemsDao;
import dto.Save;
import dto.Storage;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 直接servletを実行したときに動くメソッド（リダイレクトもこっちが動く）
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

		// IDが削除された場合、同じIDでログインしてホームに飛べないようにする

//		String userId = "mamemame01";
//		
//		// 登録処理を行う
//		UsersDao uDao = new UsersDao();
//		int logocal = uDao.getLogical(userId);
//		

		// スタンプを取得している
//		String userId = request.getParameter();

		StoragesDao stdao = new StoragesDao();

		List<Integer> stampList = stdao.getStampList();

		request.setAttribute("stampList", stampList);

//		System.out.println(stampList);

		/*
		 * 
		 * // もしもログインしていなかったらログインサーブレットにリダイレクトする HttpSession session =
		 * request.getSession(); if (session.getAttribute("id") == null) {
		 * response.sendRedirect("/webappAns/LoginServlet"); return; }
		 */

		// ここで色んな処理をする（daoに処理を依頼して、requestにセットしたりする）

		// メニューページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);

	}

	// <form method = "POST" でservletを指定したときに動くメソッド
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ここからホームページからデータが飛んできたとき用//

		String savetime = request.getParameter("savetime");
		String saveb = request.getParameter("saveb");

		if (savetime != null) {

			System.out.println("一時保存ボタン");

			// 一時保存処理

		    
		    
		    
			 // リクエストパラメータを取得する
				request.setCharacterEncoding("UTF-8");
				
				//項目追加した分の数を取ってきている
				int coun = Integer.parseInt(request.getParameter("coun"));
				
				

				// System.out.println("coun=" + coun);

				/*
				 * for (int i = 0; i <= coun; i++) { System.out.println("tr_weight" + i + "=" +
				 * request.getParameter("tr_weight" + i));
				 * 
				 * System.out.println("counts" + i + "=" + request.getParameter("counts" + i));
				 * 
				 * System.out.println("sets" + i + "=" + request.getParameter("sets" + i));
				 * 
				 * System.out.println("it" + i + "=" + request.getParameter("it" + i)); }
				 */
				
				
				//もらってきたデータを登録するアレイリスト
				ArrayList<Save> svdetalist = new ArrayList<>();
				
				
				
				//追加項目を受け取る
						for (int i = 0; i < coun; i++) {
							
							
							
							Save  svdto = new Save();
							
							
							svdto.setTr_weight(Integer.parseInt(request.getParameter("tr_weight"+ i)));
							svdto.setCounts(Integer.parseInt(request.getParameter("counts"+ i)));
							svdto.setSets(Integer.parseInt(request.getParameter("sets"+ i)));
							svdto.setTrItem(request.getParameter("it"+ i));
							svdto.setMemo(request.getParameter("memo"));
							svdto.setUser_id("test");
						

							
							TrItemsDao trDao = new TrItemsDao();
							
							//ここでとってきた名前をもとに
							String trItem = request.getParameter("it" + i);
							
							//名前がIDにかわる
							int trId = trDao.getTrIdByItem(trItem);
							
							/* detalist.add(dto); */
							
							svdto.setTr_id(trId);
							svdetalist.add(svdto);
							
						}

						/* System.out.println("データ件数: " + detalist.size()); */

			// ユーザーIDなど
			/*
			 * int storageId = Integer.parseInt(request.getParameter("storage_id")); String
			 * user_id = request.getParameter("user_id");
			 */

			/*
			 * System.out.println("weight=" + request.getParameter("weight"));
			 * System.out.println("fat=" + request.getParameter("fat"));
			 */

			ArrayList<Save> svlist = new ArrayList<>();

			Save sdto = new Save();

			// もともと表示がある項目を受け取る

			sdto.setWeight(Double.parseDouble(request.getParameter("weight")));
			sdto.setFat(Double.parseDouble(request.getParameter("fat")));
			sdto.setComments(request.getParameter("comments"));
			sdto.setStamp(Integer.parseInt(request.getParameter("stamp")));
			sdto.setUser_id("test");

			// もともとある項目の豆作った
			svlist.add(sdto);

			SavesDao sdao = new SavesDao();

			/*
			 * System.out.println("DAO呼び出し直前");
			 * 
			 * boolean result1 = vdao.insertStorage(mdto);
			 * 
			 * System.out.println("storages登録結果=" + result1);
			 * 
			 * for (Storage dto : detalist) { boolean result2 = vdao.insertTrStorage(dto);
			 * System.out.println("tr_storages登録結果=" + result2); }
			 * 
			 * System.out.println("DAO呼び出し完了");
			 */

			for (Save dto : svdetalist) {
				sdao.insertTrSaves(dto);
			}
			for (Save dto : svlist) {
				sdao.insertSaves(dto);
			}

		} else if (saveb != null) {

			System.out.println("保存ボタン");

			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");

			// 項目追加した分の数を取ってきている
			int coun = Integer.parseInt(request.getParameter("coun"));

			System.out.println("coun=" + coun);

			/*
			 * for (int i = 0; i <= coun; i++) { System.out.println("tr_weight" + i + "=" +
			 * request.getParameter("tr_weight" + i));
			 * 
			 * System.out.println("counts" + i + "=" + request.getParameter("counts" + i));
			 * 
			 * System.out.println("sets" + i + "=" + request.getParameter("sets" + i));
			 * 
			 * System.out.println("it" + i + "=" + request.getParameter("it" + i)); }
			 */

			// もらってきたデータを登録するアレイリスト
			ArrayList<Storage> detalist = new ArrayList<>();

			// 追加項目を受け取る
			for (int i = 0; i < coun; i++) {

				Storage dto = new Storage();

				dto.setTr_weight(Integer.parseInt(request.getParameter("tr_weight" + i)));
				dto.setCounts(Integer.parseInt(request.getParameter("counts" + i)));
				dto.setSets(Integer.parseInt(request.getParameter("sets" + i)));
				dto.setTrItem(request.getParameter("it" + i));
				dto.setMemo(request.getParameter("memo"));
				dto.setUser_id("test");

				TrItemsDao trDao = new TrItemsDao();

				// ここでとってきた名前をもとに
				String trItem = request.getParameter("it" + i);

				// 名前がIDにかわる
				int trId = trDao.getTrIdByItem(trItem);

				/* detalist.add(dto); */

				dto.setTr_id(trId);
				detalist.add(dto);

			}

			System.out.println("データ件数: " + detalist.size());

			// ユーザーIDなど
			/*
			 * int storageId = Integer.parseInt(request.getParameter("storage_id")); String
			 * user_id = request.getParameter("user_id");
			 */

			/*
			 * System.out.println("weight=" + request.getParameter("weight"));
			 * System.out.println("fat=" + request.getParameter("fat"));
			 */

			ArrayList<Storage> list = new ArrayList<>();

			Storage mdto = new Storage();

			// もともと表示がある項目を受け取る

			mdto.setWeight(Double.parseDouble(request.getParameter("weight")));
			mdto.setFat(Double.parseDouble(request.getParameter("fat")));
			mdto.setComments(request.getParameter("comments"));
			mdto.setStamp(Integer.parseInt(request.getParameter("stamp")));
			mdto.setUser_id("test");

			// もともとある項目の豆作った
			list.add(mdto);

			StoragesDao vdao = new StoragesDao();

			/*
			 * System.out.println("DAO呼び出し直前");
			 * 
			 * boolean result1 = vdao.insertStorage(mdto);
			 * 
			 * System.out.println("storages登録結果=" + result1);
			 * 
			 * for (Storage dto : detalist) { boolean result2 = vdao.insertTrStorage(dto);
			 * System.out.println("tr_storages登録結果=" + result2); }
			 * 
			 * System.out.println("DAO呼び出し完了");
			 */

			for (Storage dto : detalist) {
				vdao.insertTrStorage(dto);
			}
			for (Storage dto : list) {
				vdao.insertStorage(dto);
			}

			// 本保存処理

		}

		/* System.out.println("HomeServlet doPost開始"); */

		/*
		 * // リクエストパラメータを取得する request.setCharacterEncoding("UTF-8");
		 * 
		 * //項目追加した分の数を取ってきている int coun =
		 * Integer.parseInt(request.getParameter("coun"));
		 * 
		 * 
		 * 
		 * System.out.println("coun=" + coun);
		 * 
		 * 
		 * for (int i = 0; i <= coun; i++) { System.out.println("tr_weight" + i + "=" +
		 * request.getParameter("tr_weight" + i));
		 * 
		 * System.out.println("counts" + i + "=" + request.getParameter("counts" + i));
		 * 
		 * System.out.println("sets" + i + "=" + request.getParameter("sets" + i));
		 * 
		 * System.out.println("it" + i + "=" + request.getParameter("it" + i)); }
		 * 
		 * 
		 * 
		 * //もらってきたデータを登録するアレイリスト ArrayList<Storage> detalist = new ArrayList<>();
		 * 
		 * 
		 * 
		 * //追加項目を受け取る for (int i = 0; i < coun; i++) {
		 * 
		 * 
		 * 
		 * Storage dto = new Storage();
		 * 
		 * 
		 * dto.setTr_weight(Integer.parseInt(request.getParameter("tr_weight"+ i)));
		 * dto.setCounts(Integer.parseInt(request.getParameter("counts"+ i)));
		 * dto.setSets(Integer.parseInt(request.getParameter("sets"+ i)));
		 * dto.setTrItem(request.getParameter("it"+ i));
		 * dto.setMemo(request.getParameter("memo")); dto.setUser_id("test");
		 * 
		 * 
		 * 
		 * TrItemsDao trDao = new TrItemsDao();
		 * 
		 * //ここでとってきた名前をもとに String trItem = request.getParameter("it" + i);
		 * 
		 * //名前がIDにかわる int trId = trDao.getTrIdByItem(trItem);
		 * 
		 * detalist.add(dto);
		 * 
		 * dto.setTr_id(trId); detalist.add(dto);
		 * 
		 * }
		 * 
		 * 
		 * 
		 * System.out.println("データ件数: " + detalist.size());
		 * 
		 * 
		 * // ユーザーIDなど
		 * 
		 * int storageId = Integer.parseInt(request.getParameter("storage_id")); String
		 * user_id = request.getParameter("user_id");
		 * 
		 * 
		 * 
		 * System.out.println("weight=" + request.getParameter("weight"));
		 * System.out.println("fat=" + request.getParameter("fat"));
		 * 
		 * 
		 * ArrayList<Storage> list = new ArrayList<>();
		 * 
		 * Storage mdto = new Storage();
		 * 
		 * 
		 * //もともと表示がある項目を受け取る
		 * 
		 * mdto.setWeight(Double.parseDouble(request.getParameter("weight")));
		 * mdto.setFat(Double.parseDouble(request.getParameter("fat")));
		 * mdto.setComments(request.getParameter("comments"));
		 * mdto.setStamp(Integer.parseInt(request.getParameter("stamp")));
		 * mdto.setUser_id("test");
		 * 
		 * 
		 * 
		 * //もともとある項目の豆作った list.add(mdto);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * StoragesDao vdao = new StoragesDao();
		 * 
		 * 
		 * 
		 * 
		 * 
		 * System.out.println("DAO呼び出し直前");
		 * 
		 * boolean result1 = vdao.insertStorage(mdto);
		 * 
		 * System.out.println("storages登録結果=" + result1);
		 * 
		 * for (Storage dto : detalist) { boolean result2 = vdao.insertTrStorage(dto);
		 * System.out.println("tr_storages登録結果=" + result2); }
		 * 
		 * System.out.println("DAO呼び出し完了");
		 * 
		 * 
		 * 
		 * 
		 * 
		 * for (Storage dto : detalist) { vdao.insertTrStorage(dto); } for (Storage dto
		 * : list) { vdao.insertStorage(dto); }
		 * 
		 */

		response.sendRedirect(request.getContextPath() + "/HomeServlet");

		// トレーニングid、tr_itemを取ってくるためのもの
		/* int id = Integer.parseInt(request.getParameter("id")); */

		/*
		 * int tr_id = Integer.parseInt(request.getParameter("it"+ i)); int tr_weight =
		 * Integer.parseInt(request.getParameter("tr_weight" + i)); int counts =
		 * Integer.parseInt(request.getParameter("counts" + i)); int sets =
		 * Integer.parseInt(request.getParameter("sets" + i));
		 */

	}

}
