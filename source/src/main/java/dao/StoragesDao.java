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
			
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/         ここにデーターベースの大元の名前?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			
			// SQL文を準備する
			String sql = "SELECT number, company, department, position, name, "
					+ "zipcode, address, phone, fax, email, remarks "
					+ "FROM Bc "
					+ "WHERE company LIKE ? AND department LIKE ? AND position LIKE ? "
					+ "AND name LIKE ? AND zipcode LIKE ? AND address LIKE ? AND phone LIKE ? "
					+ "AND fax LIKE ? AND email LIKE ? AND remarks LIKE ? "
					+ "ORDER BY number";
			PreparedStatement pStmt = conn.prepareStatement(sql);
						
		}
						
	}
}
