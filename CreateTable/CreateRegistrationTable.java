package Database;


//���oSQL���A����
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 


public class CreateRegistrationTable 
{ 
	private Connection con = null; //Database objects 
	//�s��object 
	private Statement stat = null; 
	private Statement stat2 = null; 
	private Statement stat3 = null; 
	//����,�ǤJ��sql������r�� 
	private ResultSet rs = null; 
	//�N���G�s�Jrs�ܼ�
	private PreparedStatement pst = null; 
		  //����,�ǤJ��sql���w�x���r��,�ݭn�ǤJ�ܼƤ���m 


		  
		  
	/*
	 *  �۩w�qSQL���O
	 */	
	/*
	 *  �إ�Registration���A�ΨӬ������窺���d���A
	 */
	
	private String CreateRegistrationTable = "CREATE TABLE Registration"+ "(" + 
			  "    test_id           INTEGER " + 
			  "  , lambda            text    " +
			  "  , mu                text    " +
			  "  , theta             text    " +
			  "  , CheckPoint  text)";           //0:�|���}�l 1:�i�椤 2:���秹��  99:���粧�`

		  public static void main(String[] args) 
		  { 
		    //���ݬݬO�_���` 
			  connectSQL Connection = new connectSQL(); //�s�uSQL 
			  Connection.createTable();
			  System.out.println("���U���GCreateRegistrationTable�A�إߦ��\");
			  
	  

		  
		  } 
		  
		  /*
		   *  Java�s�uMySQL function
		   */
		  public void connectSQL() 
		  { 
		    try { 
		    	/*
		    	 * ���UJava�s�umysql���X�ʵ{�� 
		    	 */
		      Class.forName("com.mysql.jdbc.Driver"); 
		      	/*
		      	 * ���oconnection�榡�p�U
		      	 * DriverManager.getConnection("jdbc:mysql://[���A���W��:Port��]/[��Ʈw�W��]?useUnicode=true&characterEncoding=[�s�X�榡]", 
		      	 * "[�ϥΪ̱b��]","[�ϥΪ̱K�X]")
		      	 */
		      con = DriverManager.getConnection( 
		      "jdbc:mysql://localhost:5432/dbtest?useUnicode=true&characterEncoding=utf8", 
		      "root",""); 
		      
		    	} 
		    		/*
		    		 *  �p�G�䤣��mysql���X�ʵ{���o�X�ҥ~�T��
		    		 */
		    		catch(ClassNotFoundException ex1) 
		    		{ 
		    			System.out.println("DriverClassNotFound :"+ex1.toString()); 
		    		}
		    		/*
		    		 *  �p�G�䤣��SQL�o�X�ҥ~�T��
		    		 */
		    		catch(SQLException ex2) 
		    		{ 
		    			System.out.println("Exception :"+ex2.toString()); 
		    		} 
		  } 
		  
		  
		  
		  //�إ�table���覡 
		  //�i�H�ݬ�Statement���ϥΤ覡 
		  public void RegistrationTable() 
		  { 
		    try 
		    { 
		      stat3 = con.createStatement(); 
		      stat3.executeUpdate(CreateRegistrationTable); 
		      return;
		    } 
		    	catch(SQLException ex1) 
		    	{ 
		    		Close();
		    	} 

		  } 		  
		  //����ϥΧ���Ʈw��,�O�o�n�����Ҧ�Object 
		  //�_�h�b����Timeout��,�i��|��Connection poor�����p 
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