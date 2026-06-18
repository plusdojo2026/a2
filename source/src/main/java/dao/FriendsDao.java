package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;

public class FriendsDao {
	//==========================フレンドID検索用=============================
	public User friendSearch(User searchUser) {
		Connection conn = null;
		User userInfo = null;

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
			+" number,"				//管理番号
			+" user_name,"			//ユーザーネーム
			+" height,"				//身長
			+" gender,"				//性別
			+" target_weight,"		//目標体重
			+" logical_delete,"		//論理削除キー
			+" user_id,"			//ユーザーID
			+" password,"			//パスワード
			+" icon_id,"			//アイコン番号
			+" design_id,"			//着せ替え番号
			+" point"				//豆ポイント
			+" FROM users "			//テーブル
			+" WHERE user_id = ?";	//条件
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//?に代入する
			pStmt.setString(1,searchUser.getUserId());

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			if(rs.next()) {
				userInfo = new User(
						rs.getInt	("number"),
						rs.getString("user_name"),
						rs.getDouble("height"),
						rs.getString("gender"),
						rs.getDouble("target_weight"),
						rs.getInt	("logical_delete"),
						rs.getString("user_id"),
						rs.getString("password"),
						rs.getInt	("icon_id"),
						rs.getInt	("design_id"),
						rs.getInt	("point"),
						null
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			searchUser = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			searchUser = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					searchUser = null;
				}
			}
		}
		// 結果を返す
		return userInfo;
	}
	
	
}
