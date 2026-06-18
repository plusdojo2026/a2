package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TrItem;

public class TrItemsDao {

	
	//-------------ホームページのDAOここから--------------//
	//項目取得用//
	

		public List<String> getTrainingItems(){

		
		List<String> itemList = new ArrayList<String>();
		
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
	    	String sql ="SELECT tr_item FROM tr_item";
	    	
	    	
	    	//SQLを実行できる状態にする文章　SQL文の後にこれがないと動かない
	    	ps = conn.prepareStatement(sql);
	    	
	    	
	    	//SQLを実行する　実行結果はrsへ入る
	    	rs = ps.executeQuery();
	    	
	    	
	    	while (rs.next()) {
	    	    String item = rs.getString("tr_item");
	    	    System.out.println("取得: " + item);
	    	    itemList.add(item);
	    	}
	    	
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    return itemList;
	}
	
	
	
	//トレーニングIDを取ってくる
	
	
		public int getTrIdByItem(String trItem) {
		    
		    Connection conn = null;
		    PreparedStatement pStmt = null;
		    ResultSet rs = null;

		    try {
		    	
		    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
		    	    	+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
		    	    	"root", "password");
		    	    	

		    	
		        String sql =
		            "SELECT tr_id FROM tr_items WHERE tr_item = ?";

		        pStmt = conn.prepareStatement(sql);
		        pStmt.setString(1, trItem);

		        rs = pStmt.executeQuery();

		        if (rs.next()) {
		            return rs.getInt("tr_id");
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return 0;
		}
	
	
	//-------------カレンダーページのDAOここから--------------//
	/*
	 * トレーニング項目を全部取得するメソッド
	 */
		public List<TrItem> getAllTrainingItems() {
		    List<TrItem> list = new ArrayList<>();
		    
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
				
				String sql = "SELECT tr_id, tr_item FROM tr_items ORDER BY tr_id ASC";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				
		        while (rs.next()) {
		        	TrItem item = new TrItem();
		            item.setTrId(rs.getString("tr_id"));
		            item.setTrItem(rs.getString("tr_item"));
		            list.add(item);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return list;
		}
	//-------------カレンダーページのDAOここまで--------------//
}
