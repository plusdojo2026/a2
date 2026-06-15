package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class StoragesDao {
	/*
	 * public List<Storage> select(Storage ???) { Connection conn = null;
	 * List<Storage> cardList = new ArrayList<Storage>();
	 * 
	 * 
	 * try {
	 * 
	 * // JDBCドライバを読み込む データベース接続した Class.forName("com.mysql.cj.jdbc.Driver");
	 * 
	 * 
	 * conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2" +
	 * "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
	 * "root", "password");
	 * 
	 * 
	 * // SQL文を準備する String sql =
	 * "SELECT number, company, department, position, name, " +
	 * "zipcode, address, phone, fax, email, remarks " + "FROM Bc " +
	 * "WHERE company LIKE ? AND department LIKE ? AND position LIKE ? " +
	 * "AND name LIKE ? AND zipcode LIKE ? AND address LIKE ? AND phone LIKE ? " +
	 * "AND fax LIKE ? AND email LIKE ? AND remarks LIKE ? " + "ORDER BY number";
	 * PreparedStatement pStmt = conn.prepareStatement(sql);
	 * 
	 * }
	 * 
	 * }
	 */
	
	
	
	//-------------カレンダーページのDAOここから--------------//
	/*
     * 指定したユーザーの、指定した年月のスタンプ一覧を取得するメソッド
     * 返り値：Map<"2026-07-10", stamp番号>
     */
	public Map<String, Integer> getStampByMonth(String userId, String yearMonth) {

	    Map<String, Integer> stampMap = new HashMap<>();

	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        // JDBCドライバ読み込み
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // DB接続
	        conn = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/a2?"
	                + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
	                "root", "password"
	        );
	        
	        // SQL文を準備する
	        String sql = "SELECT date, stamp FROM storages "
		               + "WHERE user_id = ? "
		               + "AND DATE_FORMAT(date, '%Y-%m') = ?";
	        
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, userId);
	        ps.setString(2, yearMonth);
	        
	        // SQL文を実行し、結果表を取得する
	        rs = ps.executeQuery();
	        //stampMapに日付とスタンプをセットで入れ込む
	        while (rs.next()) {
	            String date = rs.getString("date"); // 例: 2026-06-10
	            int stamp = rs.getInt("stamp");
	            stampMap.put(date, stamp);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (Exception e) {}
	        try { if (ps != null) ps.close(); } catch (Exception e) {}
	        try { if (conn != null) conn.close(); } catch (Exception e) {}
	    }

	    return stampMap;
	}

	/*
     * 指定した日付の記録を取得するメソッド
     * 返り値：
     */

	//-------------カレンダーページのDAOここまで--------------//
	
	
//public class StoragesDao {
//	public List<Storage> select(Storage ) {
//		Connection conn = null;
//		List<Storage> cardList = new ArrayList<Storage>();
//		
//		
//		try {
//			
//			// JDBCドライバを読み込む データベース接続した
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			
//			// データベースに接続する
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2"
//					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
//					"root", "password");
//			
//			
//			
//			
//			// SQL文を準備する
//			String sql = "SELECT"
//					+ "storage_id, "
//					+"user_id, "
//					+"weight, "
//					+"fat, "
//					+"memo, "
//					+"stamp, "
//					+"date, "
//					+"id, "
//					+"tr_id, "
//					+"tr_weight, "
//					+"counts, "
//					+"sets, "
//					+ "FROM storages"
//					+ "WHERE storage_id LIKE ?" 
//					+ "AND user_id LIKE ? "
//					+ "AND weight LIKE ?"
//					+ "AND fat LIKE ?"
//					+ "AND memo LIKE ?"
//					+ "AND stamp LIKE ?"
//					+ "AND date LIKE ?"
//					+ "AND id LIKE ?"
//					+ "AND tr_id LIKE ?"
//					+ "AND tr_weight LIKE ?"
//					+ "AND counts LIKE ?"
//					+ "AND sets LIKE ?"
//					+ "ORDER BY storage_id";
//					
//			PreparedStatement pStmt = conn.prepareStatement(sql);	
//			
//						
//		}
						
	}
//}
