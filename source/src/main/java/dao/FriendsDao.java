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
			String sql = 
			"SELECT "			
			+" f.friend_user_id,"		//フレンドのユーザーid
			+" f.friend_request,"	//申請の承認フラグ
			+" u.user_name,"			//ユーザーネーム
			+" u.icon_id,"			//アイコンID
			+" u.point,"			//ポイント
			+" FROM friends As f "	//接続元
			+" JOIN users As u "		//接続先
			+" ON f.friend_user_id = u.user_id "	
			+" WHERE f.user_id = ?";	
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//?に代入する
			pStmt.setString(1,frSearch.getUserId());
	
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
	public List<TrStorage> TrSearch(TrStorage frTrSearch) {
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
			
			//?に代入する
			pStmt.setString(1,frTrSearch.getUserId());

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while(rs.next()) {
				TrStorage TrSearch = new TrStorage(
						rs.getString("user_id"),
						rs.getInt("tr_id"),
						rs.getInt("tr_weight"),
						rs.getInt("counts"),
						rs.getInt("sets"),
						rs.getString("memo"),
						rs.getDate("date")
						);
				frTrList.add(TrSearch);
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
