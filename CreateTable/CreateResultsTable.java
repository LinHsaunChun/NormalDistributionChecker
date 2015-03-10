package Database;


//取得SQL的服務檔
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 


public class CreateResultsTable 
{ 
	private Connection con = null; //Database objects 
	//連接object 
	private Statement stat = null; 
	private Statement stat2 = null; 
	private Statement stat3 = null; 
	//執行,傳入之sql為完整字串 
	private ResultSet rs = null; 
	//將結果存入rs變數
	private PreparedStatement pst = null; 
		  //執行,傳入之sql為預儲之字申,需要傳入變數之位置 


		  
		  
	/*
	 *  自定義SQL指令
	 */
	

	/*
	 *  建立Results_Record表格，用來紀錄實驗結果與實驗開始和結束時間
	 */
	
	private String CreateResultsTable = "CREATE TABLE Results_Record"+ "(" + 
			  "    result_id          INTEGER " + 
			  "  , lambda_set_up      text    " + //設定參數1
			  "  , mu_set_up          text    " + //設定參數2
			  "  , theta_set_up       text    " + //設定參數3
			  "  , Avg.UserInQueue    text    " + //觀察項目1
			  "  , StartTime          text    " + //實驗確認開始
			  "  , FinshTime  text)"; 		      //實驗確認結束
	

		  public static void main(String[] args) 
		  { 
		    //測看看是否正常 
			  connectSQL Connection = new connectSQL(); //連線SQL 
			  Connection.createTable();
			  System.out.println("實驗結果表格：Results Table，建立成功");
		  } 
		  
		  /*
		   *  Java連線MySQL function
		   */
		  public void connectSQL() 
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
		      "jdbc:mysql://localhost:5432/dbtest?useUnicode=true&characterEncoding=utf8", 
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
		  //建立table
		  public void ResultsTable() 
		  { 
		    try 
		    { 
		      stat2 = con.createStatement(); 
		      stat2.executeUpdate(CreateResultsTable); 
		      return;
		    } 
		    	catch(SQLException ex1) 
		    	{ 
		    		System.out.println("CreateDB Exception :" + ex1.toString()); 
		    	} 
	    	finally 
	    	{ 
	    		Close(); 
	    	} 

		  } 		  
		  //完整使用完資料庫後,記得要關閉所有Object 
		  //否則在等待Timeout時,可能會有Connection poor的狀況 
		  private void Close() 
		  { 
		    try 
		    { 
		      if(rs!=null) 
		      { 
		        rs.close(); 
		        rs = null; 
		      } 
		      if(stat!=null) 
		      { 
		        stat.close(); 
		        stat = null; 
		      } 
		      if(pst!=null) 
		      { 
		        pst.close(); 
		        pst = null; 
		      } 
		    } 
		    catch(SQLException e) 
		    { 
		      System.out.println("Close Exception :" + e.toString()); 
		    } 
		  } 
}
