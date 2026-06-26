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
	public void saveRecord(String userId, String date, int stamp, String comments, double weight, double fat) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
        	// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a2", "Q9wE3rJYtjnxjH4g");

            // INSERTかUPDATEを1回で実行するSQL
			String checkSql = "SELECT COUNT(*) FROM storages WHERE user_id = ? AND date = ?";
	        ps = conn.prepareStatement(checkSql);
	        ps.setString(1, userId);
	        ps.setString(2, date);
	        rs = ps.executeQuery();

	        int count = 0;
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	        
	        ps.close(); // 一度クローズ

	        if (count > 0) {
	            // 既にデータがある場合はUPDATE
	            String updateSql = "UPDATE storages SET stamp = ?, comments = ?, weight = ?, fat = ? WHERE user_id = ? AND date = ?";
	            ps = conn.prepareStatement(updateSql);
	            ps.setInt(1, stamp);
	            ps.setString(2, comments);
	            ps.setDouble(3, weight);
	            ps.setDouble(4, fat);
	            ps.setString(5, userId);
	            ps.setString(6, date);
	            ps.executeUpdate();
	        } else {
	            // データがない場合は新規INSERT
	            String insertSql = "INSERT INTO storages (user_id, date, stamp, comments, weight, fat) VALUES (?, ?, ?, ?, ?, ?)";
	            ps = conn.prepareStatement(insertSql);
	            ps.setString(1, userId);
	            ps.setString(2, date);
	            ps.setInt(3, stamp);
	            ps.setString(4, comments);
	            ps.setDouble(5, weight);
	            ps.setDouble(6, fat);
	            ps.executeUpdate();
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
    }
	

	/*
     * ユーザーの指定した年月のスタンプ一覧を取得するメソッド
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
					"a2", "Q9wE3rJYtjnxjH4g");
	        
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
     * 指定された年月のメモ内容を取得するメソッド
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
					"a2", "Q9wE3rJYtjnxjH4g");
			
			// SQL文を準備する
	        // yearMonth = "2026-06"のような形式
	        String sql = "SELECT date, comments FROM storages "
	                   + "WHERE user_id = ? "
	                   + "AND DATE_FORMAT(date, '%Y-%m') = ?";

	        ps = conn.prepareStatement(sql);
	        ps.setString(1, userId);
	        ps.setString(2, yearMonth);
	        
	        // SQL文を実行
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            String date = rs.getString("date");        // 例:2026-06-10
	            String comments = rs.getString("comments");
	            memoMap.put(date, comments);
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
     * 指定された年月のトレーニング内容を取得するメソッド
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
					"a2", "Q9wE3rJYtjnxjH4g");
			
			// SQL文を準備する
			String sql = "SELECT date, id, tr_id, tr_weight, counts, sets, memo "
	                   + "FROM tr_storages "
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
     * 指定された年月の体重を取得するメソッド
     * 返り値：Map<"2026-06-10", 体重>
     */
	public Map<String, Double> getWeightByMonth(String userId, String yearMonth) {
	    Map<String, Double> map = new HashMap<>();
	    
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	    	// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a2", "Q9wE3rJYtjnxjH4g");
			
			// SQL文を準備する
			String sql = "SELECT date, weight FROM storages WHERE user_id = ? AND date LIKE ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, yearMonth + "%");
	        
			rs = ps.executeQuery();
		    
		    while (rs.next()) {
		        String date = rs.getString("date");
		        double weight = rs.getDouble("weight");
		        
		        // 体重が未入力（NULL）でなければMapに追加
		        if (!rs.wasNull()) {
		            map.put(date, weight);
		        }
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
	    return map;
	}
	
	/*
     * 指定された年月の体脂肪率を取得するメソッド
     * 返り値：Map<"2026-06-10", 体脂肪率>
     */
	public Map<String, Double> getFatByMonth(String userId, String yearMonth) {
	    Map<String, Double> fatMap = new HashMap<>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	    	// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a2", "Q9wE3rJYtjnxjH4g");
			
			// SQL文を準備する
	        String sql = "SELECT date, fat FROM storages WHERE user_id = ? AND DATE_FORMAT(date, '%Y-%m') = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, userId);
	        ps.setString(2, yearMonth);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            String date = rs.getString("date");
	            double fat = rs.getDouble("fat");
	            fatMap.put(date, fat);
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
	    return fatMap;
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
					"a2", "Q9wE3rJYtjnxjH4g");
			
			// SQL文を準備する
	        String sql = "INSERT INTO tr_storages (user_id, date, tr_id, tr_weight, counts, sets, memo) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
	 * 既存のトレーニング内容を更新するメソッド
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
					"a2", "Q9wE3rJYtjnxjH4g");
			
			// SQL文を準備する
	        String sql = "UPDATE tr_storages SET tr_id = ?, tr_weight = ?, counts = ?, sets = ?, memo = ? WHERE id = ?";
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
	 * 既存のトレーニング内容を削除するメソッド
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
					"a2", "Q9wE3rJYtjnxjH4g");
			
			// SQL文を準備する
	        String sql = "DELETE FROM tr_storages WHERE id = ?";
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
	//直近30回分の記録内容トレーニング内容の取得
	public List<Graph> getMonthGraph(String userId){
		//
		Connection conn = null;
		List<Graph> MonthList = new ArrayList<Graph>();
		
	try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a2", "Q9wE3rJYtjnxjH4g");
			
//			String yearMonth = String.format("%04d-%02d", year, MonthNumber);
			// SQL文を準備する,SELECTでユーザーIDが同じtr_storagesを選ぶ
			//直近10回分の記録を取得
			String sql = "SELECT * FROM"
					+ "(SELECT TS.tr_id , tr_item, tr_weight,"
					+ " counts, sets, DATE_FORMAT(TS.date, '%m-%d') AS td_date ,"
					+ " ROW_NUMBER() OVER (PARTITION BY TS.tr_id ,tr_weight ORDER BY TS.date DESC) AS rn "
					+ " FROM tr_storages AS TS "
					+ " INNER JOIN tr_items AS TI "
					+ " ON TS.tr_id = TI.tr_id "
					+ " WHERE TS.user_id = ? "
					+ " ) AS t "
					+ " WHERE t.rn <= 30 "
					+ " ORDER BY t.tr_id, t.td_date ,t.tr_weight DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,userId);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Graph graph = new Graph(rs.getInt("tr_id"),rs.getString("tr_item"), rs.getInt("tr_weight"),
						rs.getInt("counts"),rs.getInt("sets"),rs.getString("td_date") 
				);
				MonthList.add(graph);
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
			return MonthList;
	}
	
	
	//	直近10回分の記録内容トレーニング内容の取得
	public List<Graph> getWeekGraph(String userId){
		//
		Connection conn = null;
		List<Graph> WeekList = new ArrayList<Graph>();
		
	try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a2", "Q9wE3rJYtjnxjH4g");
			
//			String yearMonth = String.format("%04d-%02d", year, MonthNumber);
			// SQL文を準備する,SELECTでユーザーIDが同じtr_storagesを選ぶ
			//直近7回分の記録を取得
			String sql = "SELECT * FROM"
					+ "(SELECT TS.tr_id , tr_item, tr_weight,"
					+ " counts, sets, DATE_FORMAT(TS.date, '%m-%d') AS td_date ,"
					+ " ROW_NUMBER() OVER (PARTITION BY TS.tr_id ,tr_weight ORDER BY TS.date DESC) AS rn "
					+ " FROM tr_storages AS TS "
					+ " INNER JOIN tr_items AS TI "
					+ " ON TS.tr_id = TI.tr_id "
					+ " WHERE TS.user_id = ? "
					+ " ) AS t "
					+ " WHERE t.rn <= 7 "
					+ " ORDER BY t.tr_id, t.td_date ,t.tr_weight DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,userId);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Graph graph = new Graph(rs.getInt("tr_id"),rs.getString("tr_item"), rs.getInt("tr_weight"),
						rs.getInt("counts"),rs.getInt("sets"),rs.getString("td_date") 
				);
				WeekList.add(graph);
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
			return WeekList;
	}
/*	//ユーザーが記録したことのあるトレーニング項目の取得
	public List<Graph> getItemGraph(String userId,int year,int MonthNumber){
		//
		Connection conn = null;
		List<Graph> gItemList = new ArrayList<Graph>();
		
	try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a2", "Q9wE3rJYtjnxjH4g");
			
			String yearMonth = String.format("%04d-%02d", year, MonthNumber);
			// SQL文を準備する,SELECTでtr_storagesの中のtr_itemを選ぶ
			//１か月分日別に取得
			String sql = "SELECT DISTINCT tr_item,TS.tr_id"
					+ " FROM tr_storages AS TS INNER JOIN tr_items AS TI "
					+ " ON TS.tr_id = TI.tr_id "
					+ " WHERE TS.user_id = ? "
					+ " AND DATE_FORMAT(date,'%Y-%m') = ? "
					+ " ORDER BY TS.tr_id ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,userId);
			pStmt.setString(2,yearMonth);
			
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
*/
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
						"a2", "Q9wE3rJYtjnxjH4g");
		        
				
				
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


		
		
		
		
		
//もともとある欄のstoragesのテーブル
		
		
	public boolean insertStorage(Storage dto) {
		

		System.out.println("insertTrStorage実行");
		
		//読み込む用とデータを入れるよう
		Connection conn = null;
	    PreparedStatement ps = null;
	    
	    
	    
	    try {
		
	    	
	    	// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a2", "Q9wE3rJYtjnxjH4g");
		
		
			
			//もともとある項目を入れに行く
			 String sql =
					 "INSERT INTO storages "
					  + "(user_id, weight, fat, comments, stamp, date) "
					  + "VALUES (?, ?, ?, ?, ?, NOW())";
			 
			 //psに入れる
			 ps = conn.prepareStatement(sql);
			 
			//データをStorage.java（dto）から受け取る
			 ps.setString(1, dto.getUser_id());   // 後でログイン情報から取得
			 ps.setDouble(2, dto.getWeight());
			 ps.setObject(3, dto.getFat());
			 ps.setString(4, dto.getComments());
			 ps.setInt(5, dto.getStamp());
			 
			 
			 return ps.executeUpdate() > 0;
				
			
			
	    }catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			
			// データベースを切断
				try {
					if (ps != null) ps.close();
					if(conn != null)conn.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
	    return false;
	}












//追加項目のtr_storagesのテーブル
	
	public boolean insertTrStorage(Storage dto) {
		
		Connection conn = null;
		PreparedStatement psInsert = null;
	    
	    
		try {
		// JDBCドライバを読み込む
		Class.forName("com.mysql.cj.jdbc.Driver");
	
		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
				+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
				"a2", "Q9wE3rJYtjnxjH4g");
		
		
        
        
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
        
        
        
        int result = psInsert.executeUpdate();

 


        return result > 0;
		
        
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
				try {
		            if (psInsert != null) psInsert.close();
		            if (conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				
			}
		
		}
		return false;
	}
	
	
	
	//当日保存したデータがないかどうか調べるメソッド
	
	public boolean isTodaySaved(String userId) {

	    Connection conn = null;
	    PreparedStatement pStmt = null;
	    ResultSet rs = null;

	    boolean result = false;

	    try {

	        Class.forName("com.mysql.cj.jdbc.Driver");

	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a2", "Q9wE3rJYtjnxjH4g");

//	        storagesにしたのは項目よりも確実に保存するので
	        String sql =
	            "SELECT COUNT(*) cnt "
	          + "FROM storages "
	          + "WHERE user_id = ? "
	          + "AND DATE(date) = CURDATE()";

	        pStmt = conn.prepareStatement(sql);
	        pStmt.setString(1, userId);

	        rs = pStmt.executeQuery();

	        if(rs.next()) {
	            result = rs.getInt("cnt") > 0;
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {

	        try {
	            if(rs != null) rs.close();
	            if(pStmt != null) pStmt.close();
	            if(conn != null) conn.close();
	        } catch(SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return result;
	}
	
	
}




