package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;

public class UsersDao {
	//==========================ログイン用=============================
	public User login(User us) {
		User user = null;
		Connection conn = null;
		boolean loginResult = false;
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "SELECT * FROM users WHERE user_id=? AND password=? AND logical_delete=0";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, us.getUserId());
			pStmt.setString(2, us.getPassword());

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			
			if (rs.next()) {
		
				user = new User(rs.getInt("number"), rs.getString("user_name"), rs.getDouble("height"),
						rs.getString("gender"), rs.getDouble("target_weight"), rs.getInt("logical_delete"), 
						rs.getString("user_id"), rs.getString("password"), rs.getInt("icon_id"), rs.getInt("design_id")
						, rs.getInt("point"), "");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			loginResult = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			loginResult = false;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					loginResult = false;
				}
			}
		}

		// 結果を返す
		return user;
	}
	
	
	
	
	//==========================論理削除用=============================
	
//	//public int getLogical(String user_id) {
//		//User user = null;
//		Connection conn = null;
//		boolean loginResult = false;
//		int logocal ;
//		try {
//			// JDBCドライバを読み込む
//			Class.forName("com.mysql.cj.jdbc.Driver");
//
//			// データベースに接続する
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
//					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
//					"root", "password");
//
//			// SELECT文を準備する
//			String sql = "SELECT logical_delete FROM users WHERE user_id=? ";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setString(1, user_id);
//
//			// SELECT文を実行し、結果表を取得する
//			ResultSet rs = pStmt.executeQuery();
//			
//			int logical = Integer.parseInt (rs);
//			
//			while(rs.next());
//			
//			
//
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			loginResult = false;
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			loginResult = false;
//		} finally {
//			// データベースを切断
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//					loginResult = false;
//				}
//			}
//		}
//
//		// 結果を返す
//		return logocal;
//	}
	
	
	//==========================新規登録用=============================
	
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
//==========================パスワード変更用=============================
		
		// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
		public boolean passwordChange(User psChange) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "UPDATE users SET "
						+" password=? "
						+" WHERE user_id=? ";
				PreparedStatement pStmt = conn.prepareStatement(sql);		
				// SQL文を完成させる

					pStmt.setString(1, psChange.getPassword());
					pStmt.setString(2, psChange.getUserId());
				
				
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
//==========================パスワード確認用=============================
		
		// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
		//DTOを使わず生でデータを渡す
		public boolean passwordCheck(String userId,String inputPassword) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "SELECT password "
						+" FROM users "
						+" WHERE user_id=? ";
				PreparedStatement pStmt = conn.prepareStatement(sql);		
				// SQL文を完成させる
				pStmt.setString(1, userId);
				
				ResultSet rs =pStmt.executeQuery();
				
				// SQL文を実行する
				if (rs.next()) {
					String dbPassword = rs.getString("password");
					return dbPassword.equals(inputPassword);
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
//===========================基本情報変更用==============================
		
		// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
		public boolean userInfoChange(User uiChange) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "UPDATE Users SET "
						+" user_name=?,"
						+" height=?,"
						+" gender=?,"
						+" target_weight=? "
						+" WHERE user_id=? ";
				PreparedStatement pStmt = conn.prepareStatement(sql);		
				// SQL文を完成させる
				if (uiChange.getUserName() != null) {
					pStmt.setString(1, uiChange.getUserName());
				} else {
					pStmt.setString(1, "");
				}
				if (uiChange.getHeight() != null) {
					pStmt.setDouble(2, uiChange.getHeight());
				} else {
					pStmt.setString(2, "");
				}
				if (uiChange.getGender() != null) {
					pStmt.setString(3, uiChange.getGender());
				} else {
					pStmt.setString(3, "");
				}
				if (uiChange.getTargetWeight() != null) {
					pStmt.setDouble(4, uiChange.getTargetWeight());
				} else {
					pStmt.setString(4, "");
				}
				pStmt.setString(5, uiChange.getUserId());
				
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
//===========================アイコン変更用==============================
		
		// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
		public boolean iconChange(User iChange) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "UPDATE Users SET "
						+" icon_id=? "
						+" WHERE user_id=? ";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				
				pStmt.setInt(1, iChange.getIconId());
				pStmt.setString(2, iChange.getUserId());
				
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
//===========================着せ替え変更用==============================

		// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
		public boolean designChange(User dChange) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "UPDATE Users SET "
						+" design_id=? "
						+" WHERE user_id=? ";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				
				pStmt.setInt(1, dChange.getDesignId());
				pStmt.setString(2, dChange.getUserId());
				
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
//========================アカウント論理削除用============================

		// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
		public boolean deleteAccount(User delAccount) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "UPDATE Users SET "
						+" logical_delete=1,"
						+" WHERE user_id=? ";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				
				pStmt.setString(1, delAccount.getUserId());
				
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
