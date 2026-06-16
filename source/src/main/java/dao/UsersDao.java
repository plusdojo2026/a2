package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.User;

public class UsersDao {
	// 引数card指定された項目で検索して、取得されたデータのリストを返す
		public List<User> select(User card) {
			Connection conn = null;
			List<User> cardList = new ArrayList<User>();

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "INSERT INTO Users VALUES (0, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				

				// SQL文を完成させる
				System.out.println(card.getNumber());
				if (card.getUser_id() != null) {
					pStmt.setString(1, card.getUser_id());
				} else {
				
				}

				
				System.out.println(card.getNumber());
				if (card.getPassword() != null) {
					pStmt.setString(1, card.getPassword());
				} else {
				
				}

				System.out.println(card.getNumber());
				if (card.getPassword() != null) {
					pStmt.setString(1, card.getPassword());
				} else {
				
				}
				
				System.out.println(card.getNumber());
				if (card.getGender() != null) {
					pStmt.setString(1, card.getGender());
				} else {
					pStmt.setString(1, "");
				}
				
				System.out.println(card.getNumber());
				if (card.getHeight() != null) {
					pStmt.setString(1, card.getHeight());
				} else {
				
				}
				
				System.out.println(card.getNumber());
				if (card.getTarget_weight() != null) {
					pStmt.setString(1, card.getTarget_weight());
				} else {
				
				}
				
				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

			
			} catch (SQLException e) {
				e.printStackTrace();
				cardList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				cardList = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						cardList = null;
					}
				}
			}

			// 結果を返す
			return cardList;
		}
		
		public List<User> user_info(User searchUser) {
			Connection conn = null;
			List<User> userInfo = new ArrayList<User>();

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する//
				String sql = 
				"SELECT users.number,user_name,height,gender,"
				+" target_weight,logical_delete,user_id,password,icon_id,"
				+" design_id,point,datetime"
				+" FROM users "
				+" JOIN logs"//接続先
				+" ON users.user_id=logs.user_id"//接続条件
				+" WHERE "//検索条件
				+" users.user_id = ?";
				
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				//もし何か入ってたらパーセントで囲んでSQLに代入する
				pStmt.setString(1,searchUser.getUser_id());

				
				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					User ui = new User(
							rs.getInt("number"),
							rs.getString("user_name"),
							rs.getString("height"),
							rs.getString("gender"),
							rs.getString("target_weight"),
							rs.getString("logical_delete"),
							rs.getString("user_id"),
							rs.getString("password"),
							rs.getString("icon_id"),
							rs.getString("design_id"),
							rs.getString("point"),
							rs.getString("datetime")
							);
					
					userInfo.add(ui);
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
