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

		
		
		
		//一時保存できた際に保存できたことを知らせる文を表示させている
		//msgはdo postで送ったものをとってきている
		String msg = request.getParameter("msg");

		//"tempSaved"という単語と同じものがmsgに入っているか。確認したら"一時保存できました"と出す
	    if ("tempSaved".equals(msg)) {
	        request.setAttribute("message", "一時保存できました");
	    }
	    
	    
	    

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
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		// ここからホームページからデータが飛んできたとき用//

		//ボタンが押されたという情報をもってきている
		String savetime = request.getParameter("savetime");
		String saveb = request.getParameter("saveb");
		

		//どっちのボタンが押されたかの分類わけ
		//savetime != nullはsavetimeが押されているので一時保存ボタンを押されて情報を送られてきたとき
		if (savetime != null) {

			System.out.println("一時保存ボタン");

			// 一時保存処理開始

		    
			 
				
				//項目追加した分の数を取ってきている
				int coun = Integer.parseInt(request.getParameter("coun"));
				
				
				
				//もらってきたデータを登録するアレイリスト
				ArrayList<Save> svdetalist = new ArrayList<>();
				
				
				
				//追加項目をすべて受け取る処理
				
				for (int i = 0; i < coun; i++) {
					
					//Save.javaを使う
					Save  svdto = new Save();
					
					//for文で繰り返しながら追加項目データをとってきてdtoに入れる
					svdto.setTr_weight(Integer.parseInt(request.getParameter("tr_weight"+ i)));
					svdto.setCounts(Integer.parseInt(request.getParameter("counts"+ i)));
					svdto.setSets(Integer.parseInt(request.getParameter("sets"+ i)));
					svdto.setTrItem(request.getParameter("it"+ i));
					svdto.setMemo(request.getParameter("memo"+i));
					svdto.setUser_id("test");
					
					
					//TrItemsDaoを使う（トレーニング項目名からトレーニング項目番号に変更するため）
					TrItemsDao trDao = new TrItemsDao();
					
					//ここで項目名をとってくる
					String trItem = request.getParameter("it" + i);
					
					//名前がIDにかわる
					int trId = trDao.getTrIdByItem(trItem);
					
					//ここではじめてdtoに入れる（今までは項目名しかもっていなかったので番号を入れることができる）
					svdto.setTr_id(trId);
					
					//豆にセット
					svdetalist.add(svdto);
					
				}

						
			// ユーザーIDなどをもってきて作るところ
			/*
			 * int storageId = Integer.parseInt(request.getParameter("storage_id")); String
			 * user_id = request.getParameter("user_id");
			 */

				
			//二つ目のリストを作る（データベースが違うので違う豆を作る）
			ArrayList<Save> svlist = new ArrayList<>();

			//Save.javaを使う(二回目、一応名前変えた）
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

			

			for (Save dto : svdetalist) {
				sdao.insertTrSaves(dto);
			}
			for (Save dto : svlist) {
				sdao.insertSaves(dto);
			}
			
			//ホームサーブレットに移動する際に　msg=tempSaved　でtempSavedという情報も送っている
			//URLをつくっていると考えるといいrequest.getContextPath()はa2のこと
			///a2/HomeServlet?msg=tempSavedというURLになる
			response.sendRedirect( request.getContextPath() + "/HomeServlet?msg=tempSaved");
			
			return;

			//ここからは保存ボタンが押されたときに起こる処理！
		} else if (saveb != null) {

			
			// 本保存処理
			
			System.out.println("保存ボタン");

			/*
			 * // リクエストパラメータを取得する request.setCharacterEncoding("UTF-8");
			 */
			// 項目追加した分の数を取ってきている
			int coun = Integer.parseInt(request.getParameter("coun"));

			System.out.println("coun=" + coun);

			

			// もらってきたデータを登録するアレイリスト
			ArrayList<Storage> detalist = new ArrayList<>();

			// 追加項目を受け取る
			for (int i = 0; i < coun; i++) {

				Storage dto = new Storage();

				dto.setTr_weight(Integer.parseInt(request.getParameter("tr_weight" + i)));
				dto.setCounts(Integer.parseInt(request.getParameter("counts" + i)));
				dto.setSets(Integer.parseInt(request.getParameter("sets" + i)));
				dto.setTrItem(request.getParameter("it" + i));
				dto.setMemo(request.getParameter("memo"+i));
				dto.setUser_id("test");

				TrItemsDao trDao = new TrItemsDao();

				// ここでとってきた名前をもとに
				String trItem = request.getParameter("it" + i);

				// 名前がIDにかわる
				int trId = trDao.getTrIdByItem(trItem);

				

				dto.setTr_id(trId);
				detalist.add(dto);

			}

			System.out.println("データ件数: " + detalist.size());

			// ユーザーIDなど
			/*
			 * int storageId = Integer.parseInt(request.getParameter("storage_id")); String
			 * user_id = request.getParameter("user_id");
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
			
			

			for (Storage dto : detalist) {
				vdao.insertTrStorage(dto);
			}
			for (Storage dto : list) {
				vdao.insertStorage(dto);
			}

			response.sendRedirect(request.getContextPath() + "/HomeServlet");
			return;

		}

		
	}	
}

