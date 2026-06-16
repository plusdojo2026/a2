package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	    	// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
	        
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

	    } catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	    return stampMap;
	}

	/*
     * スタンプを編集するメソッド
     * 返り値：
     */
	public boolean updateStamp(String userId, String date, int stamp) {

	    Connection conn = null;
	    PreparedStatement ps = null;

	    try {
	    	// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文を準備する
	        String sql = "UPDATE storages SET stamp = ? WHERE user_id = ? AND date = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, stamp);
	        ps.setString(2, userId);
	        ps.setString(3, date);

	        int result = ps.executeUpdate();
	        return result == 1;  // 成功なら true

	    } catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
     * トレーニング項目を編集するメソッド
     * 返り値：
     */
	public boolean updateTraining(String userId, String date, String training) {

	    Connection conn = null;
	    PreparedStatement ps = null;

	    try {
	    	// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文を準備する
	        String sql = "UPDATE storages SET training = ? WHERE user_id = ? AND date = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, training);
	        ps.setString(2, userId);
	        ps.setString(3, date);

	        int result = ps.executeUpdate();
	        return result == 1;

	    } catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/*
     * トレーニング内容を取得するメソッド
     * 返り値：
     */
	public Map<String, String> getTrainingByMonth(String userId, String yearMonth) {

	    Map<String, String> trainingMap = new HashMap<>();

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
	        // yearMonth = "2026-06" のような形式
	        String sql = "SELECT date, training FROM storages "
	                   + "WHERE user_id = ? "
	                   + "AND DATE_FORMAT(date, '%Y-%m') = ?";

	        ps = conn.prepareStatement(sql);
	        ps.setString(1, userId);
	        ps.setString(2, yearMonth);

	        rs = ps.executeQuery();

	        while (rs.next()) {
	            String date = rs.getString("date");        // 例: 2026-06-10
	            String training = rs.getString("training"); // null の可能性あり
	            trainingMap.put(date, training);
	        }

	    } catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	    return trainingMap;
	}



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
