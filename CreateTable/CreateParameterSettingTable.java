package Database;

//取得SQL的服務檔
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 


public class CreateParameterSettingTable 
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
	 *  建立Parameter_Setting表格，用來紀錄實驗參數設定
	 */

	private String CreateParameter_Setting = "CREATE TABLE Parameter_Setting(" + 
	  "    test_id     INTEGER " + 
	  "  , lambda      text    " +
	  "  , mu          text    " +
	  "  , theta       text    " +
	  "  , input_date  text)"; 
		  		// CREATE TABLE [表格名稱]([欄位一名稱]  [欄位一型態], [欄位二名稱]  [欄位二型態])
		  public static void main(String[] args) 
		  { 
		    //測看看是否正常 
			  connectSQL Connection = new connectSQL(); //連線SQL 
			  Connection.createTable();
			  System.out.println("參數設定表格：Parameter Setting Table，建立成功");
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

		  public void Parameter_Setting() 
		  { 
		    try 
		    { 
		      stat = con.createStatement(); 
		      stat.executeUpdate(CreateParameter_Setting); 
		      return;
		    } 
		    	catch(SQLException ex1) 
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