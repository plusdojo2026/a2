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

import dto.User;

public class MamepointDao {

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

}
