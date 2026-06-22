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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.SavesDao;
import dao.StoragesDao;
import dao.TrItemsDao;
import dao.UsersDao;
import dto.Save;
import dto.Storage;
import dto.User;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	// 直接servletを実行したときに動くメソッド（リダイレクトもこっちが動く）
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		HttpSession session = request.getSession();

	    User user = (User)session.getAttribute("user");
	    
	    
	    if(user != null) {
	    	
	    	// 現在の日付（今日）を取得 コピペ
	        java.time.LocalDate today = java.time.LocalDate.now();
	        
	        // セッションから「前回チェックした日付」を取得
	        java.time.LocalDate lastCheckDate = (java.time.LocalDate) session.getAttribute("lastCheckDate");
	        
	        
	        
	        // 初回アクセス、または日付が変わっていた場合
	        if (lastCheckDate == null || !lastCheckDate.equals(today)) {
	        	
	        	// ここで一時保存データを本保存に移行する（Daoのメソッドを呼ぶ）
	            SavesDao sdao = new SavesDao();
	            sdao.migrateTempToMain(user.getUserId()); // ユーザーIDを渡して、その人の分だけ移行
	        }
	        
	        // 日付チェック更新
            session.setAttribute("lastCheckDate", today);
            
            // 自動保存されたので、画面表示用の一時保存セッションもさっぱり消しておく
            session.removeAttribute("weight");
            session.removeAttribute("fat");
            session.removeAttribute("comments");
            session.removeAttribute("stamp");
	    	
	    }
	    
	    
	    
	    //今日保存されているかの確認を行う
	    
	    StoragesDao stdao = new StoragesDao();

	    boolean todaySaved =stdao.isTodaySaved(user.getUserId());

	    request.setAttribute("todaySaved",todaySaved);
	    
	    
	    //目標体重の判別用
	    Boolean goalAchieved =(Boolean)session.getAttribute("goalAchieved");

	    
	    request.setAttribute("goalAchieved",goalAchieved);
	    
	    
	
	    //TrItemsDaoをインスタンス化するnewする

		TrItemsDao trdao = new TrItemsDao();
		

		//トレーニング項目が items に入った
		List<String> items = trdao.getTrainingItems();

		
		
		//itemsを"itemList"という名前でjspに渡してる
		request.setAttribute("itemList", items);
		
		
		
		
		//	SavesDaoをインスタンス化するnewする
		SavesDao sdao = new SavesDao();
		
		//一時保存してあるデータをDBから取得しに行った（一時保存用のデータベースからとりに行っている）
		//一時保存したデータが saveDetailList に入った
		List<Save> saveDetailList =sdao.selectTrSaves(user.getUserId());
		
		
		//saveDetailList を"saveDetailList"という名前でjspに渡してる
		request.setAttribute("saveDetailList", saveDetailList);
		
		
		
		
		//セッションに保存していた値をJSPへ渡している処理をする このタイミングでセッションから取り出している
		
		request.setAttribute("weight",request.getSession().getAttribute("weight"));

		request.setAttribute("fat",request.getSession().getAttribute("fat"));

		request.setAttribute("comments",request.getSession().getAttribute("comments"));

		request.setAttribute("stamp",request.getSession().getAttribute("stamp"));
		

		// IDが削除された場合、同じIDでログインしてホームに飛べないようにする

//		String userId = "mamemame01";
//		
//		// 登録処理を行う
//		UsersDao uDao = new UsersDao();
//		int logocal = uDao.getLogical(userId);
//		

		
		
		
		
		// スタンプを取得している
//		String userId = request.getParameter();
		//下の実装は上で行っている
		/* StoragesDao stdao = new StoragesDao(); */

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
		
		HttpSession session = request.getSession();
		//ログインしているユーザーの方法を取得
		User user = (User)session.getAttribute("user");
		
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

			SavesDao sdao = new SavesDao();
		    sdao.deleteTrSaves(user.getUserId());
			 
				
			//項目追加した分の数を取ってきている
			int coun = Integer.parseInt(request.getParameter("coun"));
				
				
				
			//もらってきたデータを登録するアレイリスト
			ArrayList<Save> svdetalist = new ArrayList<>();
				
				
				
				//追加項目をすべて受け取る処理
				
			for (int i = 0; i < coun; i++) {

				//項目名を取得
			    String item = request.getParameter("it" + i);

			    // 項目名が空なら保存しない
			    if (item == null || item.trim().isEmpty()) {
			        continue;
			    }

			    String weightStr = request.getParameter("tr_weight" + i);
			    String countsStr = request.getParameter("counts" + i);
			    String setsStr = request.getParameter("sets" + i);

			    //Save.java実装
			    Save svdto = new Save();

			    svdto.setTrItem(item);

			    //これはただのセッター
			    svdto.setTr_weight(
			    	//if(weightStr == null || weightStr.trim().isEmpty()){
			        weightStr == null || weightStr.trim().isEmpty()
			        	//trueのときの処理
			            ? 0
			            //falseのときの処理
			            : Integer.parseInt(weightStr)
			    );
			    // num == 0 ? "Yes":"No"
			    //? がifぶんになってる。？の前の条件に対して、trueかfalseかを書く
			    //これが噂の三項演算子

			    svdto.setCounts(
			        countsStr == null || countsStr.trim().isEmpty()
			            ? 0
			            : Integer.parseInt(countsStr)
			    );

			    svdto.setSets(
			        setsStr == null || setsStr.trim().isEmpty()
			            ? 0
			            : Integer.parseInt(setsStr)
			    );

			    svdto.setMemo(request.getParameter("memo" + i));
			    svdto.setUser_id(user.getUserId());

			    TrItemsDao trDao = new TrItemsDao();

			    String trItem = item;

			    int trId = trDao.getTrIdByItem(trItem);

			    svdto.setTr_id(trId);

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
			sdto.setUser_id(user.getUserId());

			
			// もともとある項目の豆作った
			svlist.add(sdto);

			
			//SavesDaoをnewして使えるようにしている
			SavesDao svdao = new SavesDao();

			

			//ここで作った項目をデータベースに入れる処理をしている
			//（リストが二つなのはもともとある体重、体脂肪のリストと追加項目用のリスト）
			for (Save dto : svdetalist) {
				svdao.insertTrSaves(dto);
			}
			for (Save dto : svlist) {
				svdao.insertSaves(dto);
			}
			
			//一時保存データを取得
			
			List<Save> list = sdao.selectTrSaves(user.getUserId());
			request.setAttribute("trSaveList",list);
			
			//javaScriptでそのまま使えるようにするために、json形式に変換
			Gson gson = new Gson();
			String trSaveJson = gson.toJson(list);
			request.setAttribute("trSaveJson", trSaveJson);
			
//			JSP側のjavascriptではこんな感じで使える
//			const trSaveJson =${trSaveJson};
//			trSaveJson[0].getTrItem();
			
			
			//セッションにもらったデータを保持している（もともと記載しなくてはいけない項目の部分
			
			session.setAttribute("weight",request.getParameter("weight"));

			session.setAttribute("fat",request.getParameter("fat"));

			session.setAttribute("comments",request.getParameter("comments"));

			session.setAttribute("stamp",request.getParameter("stamp"));
			
			
			
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

			for (int i = 0; i < coun; i++) {

			    String item = request.getParameter("it" + i);

			    if (item == null || item.trim().isEmpty()) {
			        continue;
			    }

			    String weightStr = request.getParameter("tr_weight" + i);
			    String countsStr = request.getParameter("counts" + i);
			    String setsStr = request.getParameter("sets" + i);

			    Storage dto = new Storage();

			    dto.setTrItem(item);

			    //からの場合０で保存
			    dto.setTr_weight(
			        weightStr == null || weightStr.trim().isEmpty()
			        ? 0
			        : Integer.parseInt(weightStr)
			    );

			    dto.setCounts(
			        countsStr == null || countsStr.trim().isEmpty()
			        ? 0
			        : Integer.parseInt(countsStr)
			    );

			    dto.setSets(
			        setsStr == null || setsStr.trim().isEmpty()
			        ? 0
			        : Integer.parseInt(setsStr)
			    );

			    dto.setMemo(request.getParameter("memo" + i));
			    dto.setUser_id(user.getUserId());

			    TrItemsDao trDao = new TrItemsDao();

			    int trId = trDao.getTrIdByItem(item);

			    dto.setTr_id(trId);

			    detalist.add(dto);
			}
			// 追加項目を受け取る
			/*
			 * for (int i = 0; i < coun; i++) {
			 * 
			 * Storage dto = new Storage();
			 * 
			 * dto.setTr_weight(Integer.parseInt(request.getParameter("tr_weight" + i)));
			 * dto.setCounts(Integer.parseInt(request.getParameter("counts" + i)));
			 * dto.setSets(Integer.parseInt(request.getParameter("sets" + i)));
			 * dto.setTrItem(request.getParameter("it" + i));
			 * dto.setMemo(request.getParameter("memo"+i));
			 * dto.setUser_id(user.getUserId());
			 * 
			 * TrItemsDao trDao = new TrItemsDao();
			 * 
			 * // ここでとってきた名前をもとに String trItem = request.getParameter("it" + i);
			 * 
			 * // 名前がIDにかわる int trId = trDao.getTrIdByItem(trItem);
			 * 
			 * 
			 * 
			 * dto.setTr_id(trId); detalist.add(dto);
			 * 
			 * }
			 */

			System.out.println("データ件数: " + detalist.size());

			// ユーザーIDなど
			/*
			 * int storageId = Integer.parseInt(request.getParameter("storage_id")); String
			 * user_id = request.getParameter("user_id");
			 */

			

			ArrayList<Storage> list = new ArrayList<>();

			Storage mdto = new Storage();

			// もともと表示がある項目を受け取る

			//weightを別で使うので分けて書く 変数作って豆にセットする
			double weight = Double.parseDouble(request.getParameter("weight"));
			mdto.setWeight(weight);
			
			
			mdto.setFat(Double.parseDouble(request.getParameter("fat")));
			mdto.setComments(request.getParameter("comments"));
			mdto.setStamp(Integer.parseInt(request.getParameter("stamp")));
			mdto.setUser_id(user.getUserId());
			
			
			// 目標体重取得
			UsersDao udao = new UsersDao();

			double targetWeight = udao.getTargetWeight(user.getUserId());
			
			
			// 達成判定 まずは達成してない前提のためfalseを入れる
			boolean goalAchieved = false;

			//体重が目標体重より低ければ
			if(weight <= targetWeight){
			    goalAchieved = true;
			}

			// セッションに保存 jspにgoalAchievedを"goalAchieved"という名前で送る
			session.setAttribute("goalAchieved",goalAchieved);
			
			

			// もともとある項目の豆作った
			list.add(mdto);

			StoragesDao vdao = new StoragesDao();
			
			

			for (Storage dto : detalist) {
				vdao.insertTrStorage(dto);
			}
			for (Storage dto : list) {
				vdao.insertStorage(dto);
			}

			
			// 一時保存削除
			SavesDao sdao = new SavesDao();
			sdao.deleteTrSaves(user.getUserId());
			sdao.deleteSaves(user.getUserId());
			
			
			//セッションからも情報を取り除く
			session.removeAttribute("weight");
		    session.removeAttribute("fat");
		    session.removeAttribute("comments");
		    session.removeAttribute("stamp");
		    

			response.sendRedirect(request.getContextPath() + "/HomeServlet");
			return;

		}

		
	}	
}

