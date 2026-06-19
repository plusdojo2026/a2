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
					"root", "password");
	
			// SQL文を準備する//
			String sql = "SELECT "
					+ "DISTINCT "
					+ "CASE "
					//?が一致したらTHENを返す
					+ "WHEN user_id =  ?  THEN friend_user_id "
					+ "WHEN friend_user_id =  ?  THEN user_id "
					//SQL内の新しいテーブル名
					+ "END AS friend "
					+ "FROM friends "
					+ "WHERE user_id =  ?  "
					+ "OR friend_user_id = ? ";	
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//?に代入する
			pStmt.setString(1,frSearch.getUserId());
			pStmt.setString(2,frSearch.getUserId());
			pStmt.setString(3,frSearch.getUserId());
			pStmt.setString(4,frSearch.getUserId());
	
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
	
			// 結果表をコレクションにコピーする
			while(rs.next()) {
				Friend friendSearch = new Friend(
						rs.getString("user_id"),
						rs.getString("friend_user_id"),
						rs.getInt("friend_request"),
						rs.getString("user_name"),
						rs.getInt("icon_id"),
						rs.getInt("point")
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
						"root", "password");

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
							rs.getString("friend_user_id"),
							rs.getInt("friend_request"),
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
					"root", "password");

			// SQL文を準備する//
			String sql = 
			"SELECT "			
			+" user_id,"			//ユーザーID
			+" tr_id,"				//トレーニングID
			+" tr_weight,"			//重量
			+" counts,"				//回数
			+" sets,"				//セット数
			+" memo,"				//小さいメモ
			+" date"				//日付
			+" FROM tr_storages"	//検索テーブル	
			+" WHERE date = (SELECT DATE(MAX(date)) FROM tr_storages)"
			+" AND user_id=?";	
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//?に代入する=>ここでfriendUserIdからuserIdに変換
			pStmt.setString(1,frTrSearch.getFriendUserId());

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while(rs.next()) {
				TrStorage trSearch = new TrStorage(
						rs.getString("user_id"),
						rs.getInt("tr_id"),
						rs.getInt("tr_weight"),
						rs.getInt("counts"),
						rs.getInt("sets"),
						rs.getString("memo"),
						rs.getDate("date")
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
	
	
}
