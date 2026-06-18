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

import dto.Graph;
import dto.Storage;
public class StoragesDao {
	
	
	
	
	//-------------カレンダーページのDAOここから--------------//
	/*
     * スタンプやメモを更新、新規追加するメソッド
     * 返り値：trueかfaulse
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
            
            //ps.executeUpdate()で１件以上更新されてたらtrueが返る
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
	        
	        // SQL文を実行
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
     * メモ内容を取得するメソッド
     * 返り値：Map<"2026-06-10", "メモの内容">
     */
	public Map<String, String> getMemo(String userId, String yearMonth) {

	    Map<String, String> memoMap = new HashMap<>();

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
	        
	        // SQL文を実行
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            String date = rs.getString("date");        // 例:2026-06-10
	            String memo = rs.getString("memo");
	            memoMap.put(date, memo);
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

	    return memoMap;
	}

	/*
     * 指定された日付のトレーニング内容を取得するメソッド
     * 返り値：List<Storage>（trainingList）
     */
	public Map<String, List<Storage>> getTrainingByMonth(String userId, String yearMonth) {

		Map<String, List<Storage>> trainingMap = new HashMap<>();

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
			String sql = "SELECT date, id, tr_id, tr_weight, counts, sets, memo "
	                   + "FROM tr_strages "
	                   + "WHERE user_id = ? AND DATE_FORMAT(date, '%Y-%m') = ? "
	                   + "ORDER BY date, id";

	        ps = conn.prepareStatement(sql);
	        ps.setString(1, userId);
	        ps.setString(2, yearMonth);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            String date = rs.getString("date"); // 例: 2026-06-10
	            
	            Storage s = new Storage();
	            s.setId(rs.getInt("id"));
	            s.setTr_id(rs.getInt("tr_id"));
	            s.setTr_weight(rs.getInt("tr_weight"));
	            s.setCounts(rs.getInt("counts"));
	            s.setSets(rs.getInt("sets"));
	            s.setMemo(rs.getString("memo"));

	            // 日付ごとのリストを取得、なければ新規作成してMapに格納
	            trainingMap.computeIfAbsent(date, k -> new ArrayList<>()).add(s);
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
	
	
	
	/*
	 * トレーニング内容を新規追加するメソッド
	 */
	public boolean insertTraining(String userId, String date, Storage s) {
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
	        String sql = "INSERT INTO tr_strages (user_id, date, tr_id, tr_weight, counts, sets, memo) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, userId);
	        ps.setString(2, date);
	        ps.setInt(3, s.getTr_id());
	        ps.setInt(4, s.getTr_weight());
	        ps.setInt(5, s.getCounts());
	        ps.setInt(6, s.getSets());
	        ps.setString(7, s.getMemo());
	        
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
	 * 既存のトレーニング内容を更新するメソッド（主キーidを指定）
	 */
	public boolean updateTraining(Storage s) {
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
	        String sql = "UPDATE tr_strages SET tr_id = ?, tr_weight = ?, counts = ?, sets = ?, memo = ? WHERE id = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, s.getTr_id());
	        ps.setInt(2, s.getTr_weight());
	        ps.setInt(3, s.getCounts());
	        ps.setInt(4, s.getSets());
	        ps.setString(5, s.getMemo());
	        ps.setInt(6, s.getId());
	        
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
	 * トレーニング内容を削除するメソッド
	 */
	public boolean deleteTraining(int id) {
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
	        String sql = "DELETE FROM tr_strages WHERE id = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, id);
	        
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
	

	//-------------カレンダーページのDAOここまで--------------//

	
	
				
	
	//-----------成長記録ページDAOここから---------------//
	//記録内容トレーニング内容の取得
	public List<Graph> getGraphList(String userId,int year,int MonthNumber){
		//
		Connection conn = null;
		List<Graph> GraphList = new ArrayList<Graph>();
		
	try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			String yearMonth = String.format("%04d-%02d", year, MonthNumber);
			// SQL文を準備する,SELECTでユーザーIDが同じtr_storagesを選ぶ
			//１か月分日別に取得
			String sql = "SELECT tr_item, tr_weight,"
					+ " counts, sets, DATE_FORMAT(date, '%Y-%m-%d') AS TD_date "
					+ " FROM tr_storages AS TS "
					+ " INNER JOIN tr_items AS TI "
					+ " ON TS.tr_id = TI.tr_id "
					+ " WHERE TS.user_id = ? "
					+ " AND DATE_FORMAT(date,'%Y-%m') = ? "
					+ " ORDER BY TD_date ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,userId);
			pStmt.setString(2,yearMonth);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Graph graph = new Graph(rs.getString("tr_item"), rs.getInt("tr_weight"),
						rs.getInt("counts"),rs.getInt("sets"),rs.getString("TD_date") 
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
	
	//ユーザーが記録したことのあるトレーニング項目の取得
	public List<Graph> getItemGraph(String userId,int year){
		//
		Connection conn = null;
		List<Graph> gItemList = new ArrayList<Graph>();
		
	try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			
			// SQL文を準備する,SELECTでtr_storagesの中のtr_itemを選ぶ
			//１か月分日別に取得
			String sql = "SELECT DISTINCT tr_item,TS.tr_id"
					+ " FROM tr_storages AS TS INNER JOIN tr_items AS TI "
					+ " ON TS.tr_id = TI.tr_id "
					+ " WHERE TS.user_id = ? "
					+ " AND DATE_FORMAT(date,'%Y') = ? "
					+ " ORDER BY TS.tr_id ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,userId);
			pStmt.setInt(2,year);
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Graph graphItem = new Graph(rs.getString("tr_item"),rs.getInt("tr_id"));
				gItemList.add(graphItem);
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
			return gItemList;
	}
	
	//-----------成長記録ページDAOここまで---------------//


	
	//-------------ホームページのDAOここから--------------//
		//スタンプ取得用
		
		
		public List<Integer> getStampList() {

			//リストにする
			List<Integer> stampList = new ArrayList<>();

			
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
		        
				
				
		        // SQL文を準備する　stampの番号をとってきている
		        String sql = "SELECT stamp FROM storages ";
		        
		        ps = conn.prepareStatement(sql);
		        
//		        ps.setString();
		        
		        // SQL文を実行し、結果表を取得する
		        rs = ps.executeQuery();
		        
		        
		        while (rs.next()) {
		        	stampList.add(rs.getInt("stamp"));
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

		    return stampList;
		}
}

