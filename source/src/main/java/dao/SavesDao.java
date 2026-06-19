package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.Storage;

public class SavesDao {

	//追加項目のtr_storagesのテーブル
		public boolean insertTrStorage(Storage dto) {
			
			
			
			Connection conn = null;
			PreparedStatement psDelete = null;
			PreparedStatement psInsert = null;
		    
		    
			try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			
			//古いデータを削除するSQL文
			String deleteSql = "DELETE FROM tr_storages";
	        psDelete = conn.prepareStatement(deleteSql);
	        psDelete.executeUpdate();
	        
	        
	        //新しいデータを登録するSQL文
	        String insertSql =
	        "INSERT INTO tr_storages "
	         + "(user_id, tr_id, tr_weight, counts, sets, memo, date) "
	         + "VALUES (?, ?, ?, ?, ?, ?, NOW())";
			
	        psInsert = conn.prepareStatement(insertSql);
	        
	        psInsert.setString(1, dto.getUser_id());
	        psInsert.setInt(2, dto.getTr_id());
	        psInsert.setInt(3, dto.getTr_weight());
	        psInsert.setInt(4, dto.getCounts());
	        psInsert.setInt(5, dto.getSets());
	        psInsert.setString(6, dto.getMemo());

	        return psInsert.executeUpdate() > 0;
			
	        
			}catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				// データベースを切断
					try {
						if (psDelete != null) psDelete.close();
			            if (psInsert != null) psInsert.close();
			            if (conn != null) conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					
				}
			
		}
			return false;
	}
		
}
