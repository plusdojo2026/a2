package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Friend;
import dto.TrStorage;


public class FriendsDao {
	//==========================フレンド申請済みへの対応用=============================
	public List<Friend> alreadyFriend(Friend aFriend) {
		Connection conn = null;
		List<Friend> aFriendList = new ArrayList<Friend>();
	
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a2", "Q9wE3rJYtjnxjH4g");
	
			// SQL文を準備する//
			String sql = "SELECT "
					+" DISTINCT "
					+" CASE "
					//?が一致したらTHENを返す
					+" WHEN user_id =  ?  THEN friend_user_id "
					+" ELSE user_id "
					//SQL内の新しいテーブル名
					+" END AS friend_user_id "
					+" FROM friends "
					+" WHERE ( user_id =  ?  "
					+" OR friend_user_id = ? )"
					+" AND friend_request = 0";	//フレンドだけ指定
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//?に代入する
			pStmt.setString(1,aFriend.getUserId());
			pStmt.setString(2,aFriend.getUserId());
			pStmt.setString(3,aFriend.getUserId());
	
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
	
			// 結果表をコレクションにコピーする
			while(rs.next()) {
				Friend friendSearch = new Friend(
						null,
						rs.getString("friend_user_id"),
						0,
						null,
						0,
						0
						);
				aFriendList.add(friendSearch);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			aFriend = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			aFriend = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					aFriend = null;
				}
			}
		}
		// 結果を返す
		return aFriendList;
	}
	//==========================フレンド一覧①用=============================
	public List<Friend> friendSearch(Friend frSearch) {
		Connection conn = null;
		List<Friend> friendsList = new ArrayList<Friend>();
	
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a2", "Q9wE3rJYtjnxjH4g");
	
			// SQL文を準備する//
			String sql = "SELECT "
					+" DISTINCT "
					+" CASE "
					//?が一致したらTHENを返す
					+" WHEN user_id =  ?  THEN friend_user_id "
					+" ELSE user_id "
					//SQL内の新しいテーブル名
					+" END AS friend_user_id "
					+" FROM friends "
					+" WHERE ( user_id =  ?  "
					+" OR friend_user_id = ? )"
					+" AND friend_request = 1";	//フレンドだけ指定
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//?に代入する
			pStmt.setString(1,frSearch.getUserId());
			pStmt.setString(2,frSearch.getUserId());
			pStmt.setString(3,frSearch.getUserId());
	
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
	
			// 結果表をコレクションにコピーする
			while(rs.next()) {
				Friend friendSearch = new Friend(
						null,
						rs.getString("friend_user_id"),
						0,
						null,
						0,
						0
						);
				friendsList.add(friendSearch);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			frSearch = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			frSearch = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					frSearch = null;
				}
			}
		}
		// 結果を返す
		return friendsList;
	}
	//==========================フレンド一覧②用=============================
		public List<Friend> friendInfo(Friend frInfo) {
			Connection conn = null;
			List<Friend> frInfoList = new ArrayList<Friend>();

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"a2", "Q9wE3rJYtjnxjH4g");

				// SQL文を準備する//
				String sql = 
				"SELECT "			
				+" user_id, "			//ユーザーID
				+" user_name, "			//ユーザー名
				+" icon_id, "			//アイコンID
				+" point "				//ポイント
				+" FROM users"			//検索テーブル		
				+" WHERE user_id=?";	//条件
				
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				//?に代入する=>ここでfriendUserIdからuserIdに変換
				pStmt.setString(1,frInfo.getFriendUserId());

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while(rs.next()) {
					Friend friendInfo = new Friend(
							rs.getString("user_id"),
							null,
							0,
							rs.getString("user_name"),
							rs.getInt("icon_id"),
							rs.getInt("point")
							);
					frInfoList.add(friendInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				frInfo = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				frInfo = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						frInfo = null;
					}
				}
			}
			// 結果を返す
			return frInfoList;
		}
	//==========================フレンド一覧③用=============================
	public List<TrStorage> trSearch(TrStorage frTrSearch) {
		Connection conn = null;
		List<TrStorage> frTrList = new ArrayList<TrStorage>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a2", "Q9wE3rJYtjnxjH4g");

			// SQL文を準備する//
			String sql = 
				    "SELECT "
				    +" ti.tr_item, "
				    +" t.tr_weight, "
				    +" t.counts, "
				    +" t.sets,"
				    +" t.memo,"
				    +" DATE_FORMAT(t.date, '%Y-%m-%d') "
				    +" AS date"
				    +" FROM tr_storages t "
				    +" JOIN tr_items ti "
				    +" ON t.tr_id = ti.tr_id "
				    +" WHERE t.user_id = ? "
				    +" AND t.date = ( SELECT MAX(st.date) "
				    +" FROM tr_storages st "
				    +" WHERE st.user_id = t.user_id)";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//?に代入する=>ここでfriendUserIdからuserIdに変換
			pStmt.setString(1,frTrSearch.getFriendUserId());

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while(rs.next()) {
				TrStorage trSearch = new TrStorage(
						null,
						rs.getString("tr_item"),
						rs.getInt("tr_weight"),
						rs.getInt("counts"),
						rs.getInt("sets"),
						rs.getString("memo"),
						rs.getString("date")
						);
				frTrList.add(trSearch);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			frTrSearch = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			frTrSearch = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					frTrSearch = null;
				}
			}
		}
		// 結果を返す
		return frTrList;
	}
	//==========================ユーザー検索用=============================
	public Friend search(Friend search) {
		Connection conn = null;
		Friend searchAns = null;
	
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a2", "Q9wE3rJYtjnxjH4g");
	
			// SQL文を準備する//
			String sql = "SELECT "
					+" user_name,"
					+" point,"
					+" user_id,"
					+" icon_id"
					+" FROM users"
					+" WHERE user_id=?"
					+" AND logical_delete = 0";
					
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//?に代入する
			pStmt.setString(1,search.getUserId());
	
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
	
			// 結果表をコレクションにコピーする
			if(rs.next()) {
				searchAns = new Friend(
						rs.getString("user_id"),
						null,
						0,
						rs.getString("user_name"),
						rs.getInt("icon_id"),
						rs.getInt("point")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			search = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			search = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					search = null;
				}
			}
		}
		// 結果を返す
		return searchAns;
	}
	

//==========================フレンド申請用=============================
public boolean friendAdd(Friend frAdd) {
	Connection conn = null;
	boolean result = false;

	try {
		// JDBCドライバを読み込む
		Class.forName("com.mysql.cj.jdbc.Driver");
		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
				+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
				"a2", "Q9wE3rJYtjnxjH4g");

		// SQL文を準備する//
		String sql = "INSERT INTO "
				+" friends "
				+" (user_id,friend_user_id,friend_request) "
				+" VALUES"
				+" (?,?,0)";
				
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		//?に代入する
		pStmt.setString(1,frAdd.getUserId());
		pStmt.setString(2,frAdd.getFriendUserId());

		// 結果表をコレクションにコピーする
		// SQL文を実行する
		if (pStmt.executeUpdate() == 1) {
			result = true;
		}
	} catch (SQLException e) {
		e.printStackTrace();
		frAdd = null;
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
		frAdd = null;
	} finally {
		// データベースを切断
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				frAdd = null;
			}
		}
	}
	// 結果を返す
	return result;
}
//==========================リクエスト受けてるか確認=============================
	public List<Friend> requestSearch(Friend rqSearch) {
		Connection conn = null;
		List<Friend> rqSearchAns = new ArrayList<Friend>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a2", "Q9wE3rJYtjnxjH4g");
	
			// SQL文を準備する//
			String sql = "SELECT "
					+" f.user_id,"
					+" u.user_name,"
					+" u.icon_id,"
					+" u.point"
					+" FROM friends f"
					+" JOIN users u"
					+" ON f.user_id = u.user_id"
					+" WHERE f.friend_user_id = ? "
					+" AND f.friend_request = 0 ";
			
					
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//?に代入する
			pStmt.setString(1,rqSearch.getUserId());
	
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
	
			// 結果表をコレクションにコピーする
			while(rs.next()) {
				Friend rAns = new Friend(
						rs.getString("user_id"),
						null,
						0,
						rs.getString("user_name"),
						rs.getInt("icon_id"),
						rs.getInt("point")
						);
				rqSearchAns.add(rAns);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			rqSearch = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			rqSearch = null;
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
		return rqSearchAns;
	}
	
//==========================リクエスト受けてるか確認(boolean)=============================
	public boolean requestCheck(String userId) {
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a2", "Q9wE3rJYtjnxjH4g");
	
			// SQL文を準備する//
			String sql = "SELECT "
					+" user_id "
					+" FROM friends "
					+" WHERE friend_user_id = ? "
					+" AND friend_request = 0 ";
					
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//?に代入する
			pStmt.setString(1,userId);
			//実行
			ResultSet rs = pStmt.executeQuery();
	
			if(rs.next()) {
				result=true;
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
		// 結果を返す
		return result;
	}
//==========================リクエスト出来ているかの確認=============================
	public List<Friend> requestFromMeSearch(Friend fmSearch) {
		Connection conn = null;
		List<Friend> fmSearchAns = new ArrayList<Friend>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"a2", "Q9wE3rJYtjnxjH4g");
	
			// SQL文を準備する//
			String sql = "SELECT "
					+" u.user_id,"
					+" u.user_name,"
					+" u.icon_id,"
					+" u.point"
					+" FROM friends f"
					+" JOIN users u"
					+" ON f.friend_user_id = u.user_id"
					+" WHERE f.user_id = ? "
					+" AND f.friend_request = 0 ";
			
					
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//?に代入する
			pStmt.setString(1,fmSearch.getUserId());
	
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
	
			// 結果表をコレクションにコピーする
			while(rs.next()) {
				Friend rAns = new Friend(
						rs.getString("user_id"),
						null,
						0,
						rs.getString("user_name"),
						rs.getInt("icon_id"),
						rs.getInt("point")
						);
				fmSearchAns.add(rAns);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			fmSearch = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			fmSearch = null;
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
		return fmSearchAns;
	}
//===========================リクエスト承認用==============================

		// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
		public boolean requestPermission(Friend rPermission) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"a2", "Q9wE3rJYtjnxjH4g");

				// SQL文を準備する
				String sql = "UPDATE friends SET "
						+" friend_request = 1 "
						+" WHERE user_id= ? "
						+" AND friend_user_id = ? ";
				PreparedStatement pStmt = conn.prepareStatement(sql);		
				// SQL文を完成させる

				pStmt.setString(1, rPermission.getUserId());
				pStmt.setString(2, rPermission.getFriendUserId());
				
				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
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
			// 結果を返す
			return result;
		}
//===========================リクエスト却下用==============================

		// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
		public boolean requestRejected(Friend rRejected) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"a2", "Q9wE3rJYtjnxjH4g");

				// SQL文を準備する
				String sql = "DELETE FROM friends  "
						+" WHERE user_id= ? "
						+" AND friend_user_id = ? ";
				PreparedStatement pStmt = conn.prepareStatement(sql);		
				// SQL文を完成させる

				pStmt.setString(1, rRejected.getUserId());
				pStmt.setString(2, rRejected.getFriendUserId());
				
				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
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
			// 結果を返す
			return result;
		}
//===========================フレンド削除用==============================

		// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
		public boolean friendDelete(String userId,String friendUserId) {
			Connection conn = null;
			boolean result = false;
			
			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"a2", "Q9wE3rJYtjnxjH4g");
				
				// SQL文を準備する
				String sql = "DELETE  "
						+" FROM friends "
						+" WHERE ("
						+ "(user_id = ? AND friend_user_id = ? ) "
						+" OR (user_id = ? AND friend_user_id = ? )) "
						+" AND friend_request = 1";	
				PreparedStatement pStmt = conn.prepareStatement(sql);		
				// SQL文を完成させる
					
				pStmt.setString(1, userId);
				pStmt.setString(2, friendUserId);
				pStmt.setString(3, friendUserId);
				pStmt.setString(4, userId);
				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
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
			// 結果を返す
			return result;
		}

}

