package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Save;

public class SavesDao {

	
	//
		public boolean insertSaves(Save dto) {
			
			//データベース接続用とデータ削除用とデータ入れる用
			Connection conn = null;
			PreparedStatement psDelete = null;
			PreparedStatement psInsert = null;
		
		
		
		try {
		
			
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			
			
			
			//古いデータを削除するSQL文
			//ユーザーIDで指定したところのデータを消さなくてはいけない
			
			String deleteSql = "DELETE FROM saves\r\n" + "WHERE user_id = ?";
			//作ったところにデータ削除構文を入れる
			psDelete = conn.prepareStatement(deleteSql);
			psDelete.setString(1, dto.getUser_id());

			psDelete.executeUpdate();
		    //実行
			/* int result = psInsert.executeUpdate(); */

		  
		    
		    
			
		    
		    //新しいデータを登録するSQL文　（日付は登録した日のものNOW()を使用）
			 String sql =
					 "INSERT INTO saves "
					  + "(user_id, weight, fat, comments, stamp, date) "
					  + "VALUES (?, ?, ?, ?, ?, NOW())";
			 
			//作ったところにデータを登録構文を入れる
			 psInsert = conn.prepareStatement(sql);
			 
			 
			 //データをsave.java（dto）から受け取る
			 psInsert.setString(1, dto.getUser_id());   // 後でログイン情報から取得
			 psInsert.setDouble(2, dto.getWeight());
			 psInsert.setDouble(3, dto.getFat());
			 psInsert.setString(4, dto.getComments());
			 psInsert.setInt(5, dto.getStamp());
			 
			 
			 //psInsert.executeUpdate() はSQLを実行するメソッド
			 //一件登録されたら１という数字がかえってくる
			 //出来なかったら０なので, true(成功) false(失敗)のどちらかを返すという構文
			 return psInsert.executeUpdate() > 0;
				
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			
			// データベースを切断
					try {
						//三つすべてなくなったらすべて閉じて
						if (psDelete != null) psDelete.close();
			            if (psInsert != null) psInsert.close();
			            if (conn != null) conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				
			}
		    return false;
		}


	
		//保存用
		public void deleteTrSaves(String userId) {

		    Connection conn = null;
		    PreparedStatement pStmt = null;

		    try {

		        Class.forName("com.mysql.cj.jdbc.Driver");

		        conn = DriverManager.getConnection(
		            "jdbc:mysql://localhost:3306/a2?"
		            + "characterEncoding=utf8&useSSL=false"
		            + "&serverTimezone=GMT%2B9",
		            "root",
		            "password"
		        );

		        String sql =
		            "DELETE FROM tr_saves WHERE user_id = ?";

		        pStmt = conn.prepareStatement(sql);

		        pStmt.setString(1, userId);

		        pStmt.executeUpdate();

		    } catch(Exception e) {
		        e.printStackTrace();
		    }finally {
		        try {
		            if (pStmt != null) pStmt.close();
		            if (conn != null) conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
		
		
	
	
	
	
	//追加項目の一時保存、前のデータ消されるバージョン　tr_storagesのテーブル
		public boolean insertTrSaves(Save dto) {
			
			
			
			Connection conn = null;
			PreparedStatement psDelete = null;
			PreparedStatement psInsert = null;
		    
		    
			try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			
			//古いデータを削除するSQL文
			//ユーザーIDを指定してその部分を消さなくてはいけない
			/*
			 * String deleteSql = "DELETE FROM tr_saves"; psDelete =
			 * conn.prepareStatement(deleteSql); psDelete.executeUpdate();
			 */
	        
	        
	        //新しいデータを登録するSQL文
	        String insertSql =
	        "INSERT INTO tr_saves "
	         + "(user_id, tr_id, tr_weight, counts, sets, memo, date) "
	         + "VALUES (?, ?, ?, ?, ?, ?, NOW())";
			
	        psInsert = conn.prepareStatement(insertSql);
	        
	        psInsert.setString(1, dto.getUser_id());
	        psInsert.setInt(2, dto.getTr_id());
	        psInsert.setInt(3, dto.getTr_weight());
	        psInsert.setInt(4, dto.getCounts());
	        psInsert.setInt(5, dto.getSets());
	        psInsert.setString(6, dto.getMemo());

	        return psInsert.executeUpdate() > 0;
			
	        
			}catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				// データベースを切断
					try {
						if (psDelete != null) psDelete.close();
			            if (psInsert != null) psInsert.close();
			            if (conn != null) conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					
				}
			
		}
			return false;
	}
		
		
		
		
		public List<Save> selectTrSaves(String userId) {

		    Connection conn = null;
		    PreparedStatement pStmt = null;
		    ResultSet rs = null;

		    List<Save> list = new ArrayList<>();

		    try {

		        Class.forName("com.mysql.cj.jdbc.Driver");

		        conn = DriverManager.getConnection(
		            "jdbc:mysql://localhost:3306/a2?"
		            + "characterEncoding=utf8&useSSL=false"
		            + "&serverTimezone=GMT%2B9",
		            "root",
		            "password"
		        );

		        String sql =
		                "SELECT t.tr_item, s.tr_weight, s.counts, s.sets, s.memo "
		              + "FROM tr_saves s "
		              + "INNER JOIN tr_items t "
		              + "ON s.tr_id = t.tr_id "
		              + "WHERE s.user_id = ?";

		        pStmt = conn.prepareStatement(sql);
		        pStmt.setString(1, userId);

		        rs = pStmt.executeQuery();

		        while(rs.next()) {

		            Save dto = new Save();

		            dto.setTrItem(rs.getString("tr_item")); 
		            
		            dto.setTr_weight(rs.getInt("tr_weight"));
		            dto.setCounts(rs.getInt("counts"));
		            dto.setSets(rs.getInt("sets"));
		            dto.setMemo(rs.getString("memo"));

		            list.add(dto);
		        }

		    } catch(Exception e) {
		        e.printStackTrace();
		    } finally {

		        try {
		            if(rs != null) rs.close();
		            if(pStmt != null) pStmt.close();
		            if(conn != null) conn.close();
		        } catch(SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    return list;
		}
}
