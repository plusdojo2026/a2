package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Word;

public class WordsDao {
	
	//-------------情報ページのDAOここから--------------//
	/*
     * メソッド
     * 返り値：
     */
	public List<Word> getWord() {
		List<Word> wordList = new ArrayList<>();
		
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
	        String sql = "";
	        
	        // SQL文を実行
	        rs = ps.executeQuery();
	        
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
		
		return wordList;
	}
}
