import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement;

import leanJAVA.connectSQL;

public class connectSQL {
	private Connection con = null; //Database objects 
	  //連接object 
	  private Statement stat = null; 
	  //執行,傳入之sql為完整字串 
	  private ResultSet rs = null; 
	  //結果集 
	  private PreparedStatement pst = null; 
	  //執行,傳入之sql為預儲之字申,需要傳入變數之位置 
	  //先利用?來做標示 
	
	public static void main(String[] args) {
		connectSQL test = new connectSQL();
	}

	/*
	 * 程式目的：建立虛無假設與對立假設
	 * 程式說明：
	 * 			1.從資料庫讀取虛無假設與對立假設實驗設定值
	 * 				1.1 和資料庫進行連線
	 * 				1.2讀取虛無假設數值與對立假設數值
	 * 				1.3將讀取虛無假設數值與對立假設數值寫入實驗紀錄表
	 * 				1.4結束連線
	 * 				1.5回傳虛無假設與對立假設數值給主程式
	 */

	private connectSQL() 
	  { 
		
		try { 
	    	/*
	    	 * 註冊Java連線mysql的驅動程式 
	    	 */
	      Class.forName("com.mysql.jdbc.Driver"); 
	      	/*
	      	 * 取得connection格式如下
	      	 * DriverManager.getConnection("jdbc:mysql://[伺服器名稱:Port號]/[資料庫名稱]?useUnicode=true&characterEncoding=[編碼格式]", 
	      	 * "[使用者帳戶]","[使用者密碼]")
	      	 */
	      con = DriverManager.getConnection( 
	      "jdbc:mysql://localhost:5432/test?useUnicode=true&characterEncoding=utf8", 
	      "root",""); 
	      
	    	} 
	    		/*
	    		 *  如果找不到mysql的驅動程式發出例外訊息
	    		 */
	    		catch(ClassNotFoundException ex1) 
	    		{ 
	    			System.out.println("DriverClassNotFound :"+ex1.toString()); 
	    		}
	    		/*
	    		 *  如果找不到SQL發出例外訊息
	    		 */
	    		catch(SQLException ex2) 
	    		{ 
	    			System.out.println("Exception :"+ex2.toString()); 
	    		} 
	  }
	/*
	 * 程式目的：設定信心水準
	 * 程式說明：
	 * 			1.從資料庫讀取信心水準並回傳給主程式
	 * 				1.1 和資料庫進行連線
	 * 				1.2讀取信心水準數值
	 * 				1.3將讀取信心水準數值寫入實驗紀錄表
	 * 				1.4結束連線
	 * 				1.5回傳信心水準數值給主程式
	 */
	
	/*
	 * 程式目的：設定信心水準
	 * 程式說明：
	 * 			1.從資料庫讀取信心水準並回傳給主程式
	 * 				1.1 和資料庫進行連線
	 * 				1.2讀取信心水準數值
	 * 				1.3將讀取信心水準數值寫入實驗紀錄表
	 * 				1.4結束連線
	 * 				1.5回傳信心水準數值給主程式
	 */
	
	/*
	 * 程式目的：進行卡方適切性檢定
	 * 程式說明：
	 * 			1.從資料庫讀取實驗原始數據
	 * 				1.1 和資料庫進行連線
	 * 				1.2讀取信心水準數值
	 * 				1.3將讀取信心水準數值寫入實驗紀錄表
	 * 				1.4結束連線
	 * 				1.5回傳信心水準數值給主程式
	 * 			2.將實驗數據依照 Sturge's rule決定組數
	 * 				公式為k=1+3.32log(n), k是組數;n是樣本數
	 * 			3.決定各組別的上界(upper bound)與下界(lower bound)並依照連續型資料型態調修正每組的上界與下界。
	 * 			4.計算每一組的實際觀察值(Oi), i代表第幾組資料的編號i,i={1,2,..., k }or {1,2,..., k+1}。
	 * 			5.計算每一組的理論觀察值(Ei), i代表第幾組資料的編號i,i={1,2,..., k }or {1,2,..., k+1}。
	 * 			6.計算統計量值 sum_i=1 ^ i=k or k+1 frac{(Oi-Ei)^2 }{Ei}。
	 * 			7.若大於統計量值的機率比信賴水準高或等於，則回傳拒絕宣聲，意謂實驗數據和特定平均數與變異數的常態分佈不吻合。
	 * 			  反之， 若小於等於統計量值的機率比信賴水準低，則回傳不拒絕宣聲，意謂沒有足夠證實實驗數據和特定平均數與變異數的常態分佈不吻合
	 * 			 即目前接受實驗數據和特定平均數與變異數的常態分佈吻合的宣稱。
	 * 			8.將實驗紀錄寫入到實驗紀錄表
	 * 				8.1 和資料庫進行連線
	 * 				8.2將檢定統計量值和檢定結果寫入實驗紀錄表
	 * 				8.4結束連線
	 * 				8.5回傳"第xxx筆實驗已經完成"給主程式
	 */
}
