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
	public List<Word> SelectWord() {
		//一言を取得
		Connection conn = null;
		List<Word> DayWord = new ArrayList<Word>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する,検索SELECTで氏名と都道府県について検索し登録番号の昇順で表示
			String sql = " SELECT word ,word_of_day "
					+ " FROM words "
					+ " ORDER BY RAND() "
					+ " LIMIT 1 ";
			// SQL文を実行し、結果表を取得する
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
			Word dayWord = new Word(rs.getInt("word"),rs.getString("word_of_day"));
			DayWord.add(dayWord);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DayWord = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			DayWord = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					DayWord = null;
				}
			}
		}
		// 結果を返す
		return DayWord;
	}
	
}
