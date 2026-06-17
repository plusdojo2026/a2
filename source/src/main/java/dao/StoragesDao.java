package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.Storage;
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
     * スタンプやメモを更新、新規追加するメソッド
     * 返り値：
     */
	public boolean saveRecord(String userId, String date, Integer stamp, String memo) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
        	// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

            // INSERTかUPDATEを1回で実行するSQL
            String sql =
                "INSERT INTO storages (user_id, date, stamp, memo) " +
                "VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "stamp = VALUES(stamp), " +
                "memo = VALUES(memo)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, date);
            ps.setObject(3, stamp);
            ps.setString(4, memo);

            return ps.executeUpdate() > 0;

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

        return false;
    }
	
	
	
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
	            String date = rs.getString("date"); // 例:2026-06-10
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
     * めも内容を取得するメソッド
     * 返り値：
     */
	public Map<String, String> getMemo(String userId, String yearMonth) {

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
	        // yearMonth = "2026-06"のような形式
	        String sql = "SELECT date, memo FROM storages "
	                   + "WHERE user_id = ? "
	                   + "AND DATE_FORMAT(date, '%Y-%m') = ?";

	        ps = conn.prepareStatement(sql);
	        ps.setString(1, userId);
	        ps.setString(2, yearMonth);

	        rs = ps.executeQuery();

	        while (rs.next()) {
	            String date = rs.getString("date");        // 例:2026-06-10
	            String memo = rs.getString("memo"); // nullの可能性あり
	            trainingMap.put(date, memo);
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
//}						
	
	//-----------成長記録ページDAOここから---------------//
	public List<Storage> getGraphList(String userId,int year,int MonthNumber){
		//
		Connection conn = null;
		List<Storage> GraphList = new ArrayList<Storage>();
	try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			
			// SQL文を準備する,SELECTでユーザーIDが同じtr_storagesを選ぶ
			//１か月分日別に取得
			String sql = "SELECT tr_id, tr_weight,"
					+ "counts, sets, DATE_FORMAT(date, '%Y-%m-%d') AS TS.date"
					+ "FROM tr_storages AS TS INNER JOIN tr_items AS TI"
					+ "ON TS.tr_id = TI.tr_item"
					+ "WHERE TS.user_id = ?"
					+ "AND YEAR(date) = ?"
					+ "AND MONTH(date)= ?"
					+ "ORDER BY TS.date";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,userId);
			pStmt.setInt(2,year);
			pStmt.setInt(3,MonthNumber);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Graph graph = new Graph(rs.getInt("tr_id"), rs.getInt("tr_weight"),
						rs.getInt("counts"),rs.getInt("sets"),rs.getString("group_date") 
				);
				GraphList.add(graph);
			}

			
	
			
		}catch (SQLException e) {
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
		
			// 結果を返す
			return GraphList;
	}
	//-----------成長記録ページDAOここまで---------------//


	
}

