package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import dto.Knowledge;

public class KnowledgesDao {
	
	//-------------情報ページのDAOここから--------------//
	/*
     * 日替わりの一言を取得するメソッド
     * 返り値：KnowledgeDTO
     */
	public Knowledge getTodayWord() {
		Knowledge word = null;
		int day = LocalDate.now().getDayOfMonth();
		
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
	        
			// wordsテーブルの全件数を取得（40件）
            int count = 0;
	        String preSql = "SELECT COUNT(*) FROM knowledges";
	        ps = conn.prepareStatement(preSql);
	        // SQL文を実行
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1); // 1行目の総件数を取得
	        }
	        if (count == 0) {
	        	return null;
	        }
	        
	        // 一度クリアする
	        rs.close();
	        ps.close();
	        
	        // 日替わりのwordIdを計算
            int wordId = (day % count) + 1;
            String sql = "SELECT * FROM knowledges WHERE knowledge_num = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, wordId);
            // SQL文を実行
	        rs = ps.executeQuery();
            
            if (rs.next()) {
                word = new Knowledge(rs.getInt("knowledge_num"), rs.getString("trivia"));
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
		
		return word;
	}
}
