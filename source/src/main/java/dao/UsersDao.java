package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;

public class UsersDao {
	// 引数card指定された項目で検索して、取得されたデータのリストを返す
		public boolean insert(User us) {
			System.out.println("UsersDaoのinsertに入ったよ");
			Connection conn = null;
			boolean ans = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "INSERT INTO Users(number,user_name,height,gender,target_weight,user_id,password) "
						+ "VALUES (0, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				

				// SQL文を完成させる(?を左から順に埋めていく）
		
				System.out.println(us.getUserName());			
				pStmt.setString(1, us.getUserName());
				
				System.out.println(us.getHeight());				
				pStmt.setDouble(2, us.getHeight());				

				System.out.println(us.getGender());				
				pStmt.setString(3, us.getGender());			
				
				System.out.println(us.getTargetWeight());				
				pStmt.setDouble(4, us.getTargetWeight());				
				
				System.out.println(us.getUserId());				
				pStmt.setString(5, us.getUserId());			
				
				System.out.println(us.getPassword());				
				pStmt.setString(6, us.getPassword());
			
				
				// SQL文を実行し、結果表を取得する
				int result = pStmt.executeUpdate();
				System.out.println(result+"件追加されました");
				if(result ==1) {
					ans = true;
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
			return ans;
		}
		

		
		
		
//===========================マイページ用===============================
		
		public User userInfo(User searchUser) {
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
