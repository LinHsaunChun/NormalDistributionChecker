package Database;

//���oSQL���A����
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 


public class CreateParameterSettingTable 
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
	 *  �إ�Parameter_Setting���A�ΨӬ�������ѼƳ]�w
	 */

	private String CreateParameter_Setting = "CREATE TABLE Parameter_Setting(" + 
	  "    test_id     INTEGER " + 
	  "  , lambda      text    " +
	  "  , mu          text    " +
	  "  , theta       text    " +
	  "  , input_date  text)"; 
		  		// CREATE TABLE [���W��]([���@�W��]  [���@���A], [���G�W��]  [���G���A])
		  public static void main(String[] args) 
		  { 
		    //���ݬݬO�_���` 
			  connectSQL Connection = new connectSQL(); //�s�uSQL 
			  Connection.createTable();
			  System.out.println("�ѼƳ]�w���GParameter Setting Table�A�إߦ��\");
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