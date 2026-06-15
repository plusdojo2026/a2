package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import dto.Storage;

public class StoragesDao {
	public List<Storage> select(Storage ???) {
		Connection conn = null;
		List<Storage> cardList = new ArrayList<Storage>();
		
		
		try {
			
			// JDBCドライバを読み込む データベース接続した
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			
			
			
			// SQL文を準備する
			String sql = "SELECT"
					+ "storage_id, "
					+"user_id, "
					+"weight, "
					+"fat, "
					+"memo, "
					+"stamp, "
					+"date, "
					+"id, "
					+"tr_id, "
					+"tr_weight, "
					+"counts, "
					+"sets, "
					+ "FROM storages"
					+ "WHERE storage_id LIKE ?" 
					+ "AND user_id LIKE ? "
					+ "AND weight LIKE ?"
					+ "AND fat LIKE ?"
					+ "AND memo LIKE ?"
					+ "AND stamp LIKE ?"
					+ "AND date LIKE ?"
					+ "AND id LIKE ?"
					+ "AND tr_id LIKE ?"
					+ "AND tr_weight LIKE ?"
					+ "AND counts LIKE ?"
					+ "AND sets LIKE ?"
					+ "ORDER BY storage_id";
					
			PreparedStatement pStmt = conn.prepareStatement(sql);	
			
						
		}
						
	}
}
