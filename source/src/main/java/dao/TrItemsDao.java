package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TrItemsDao {

	
	//-------------ホームページのDAOここから--------------//
	//項目取得用//
	
	public List<String> getTrainingItems(String userId){
		
		List<String> cardList = new ArrayList<String>();
		
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    
	    try {
	    	
	    	
	    	// JDBCドライバを読み込む
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	
	    	// データベースに接続する
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
	    	+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
	    	"root", "password");
	    	
	    	
	    	
	   
	    	
	    	
	    	// SQL文を準備する
	    	
	    	String sql =
	                "SELECT training_name FROM training_item WHERE user_id = ?";
	    	
	    	
	    	
	    	//SQLを実行できる状態にする文章　SQL文の後にこれがないと動かない
	    	ps = conn.prepareStatement(sql);
	    	
	    	//上のSQL文の？に入れてあげている
	    	ps.setString(1, userId);
	    	
	    	//SQLを実行する　実行結果はrsへ入る
	    	rs = ps.executeQuery();
	    	
	    	
	    	while (rs.next()) {
	    		
	    		itemList.add(
	                    rs.getString("training_name")
	                );
	    	}
	    	
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	
}
