import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement;

import leanJAVA.connectSQL;

public class connectSQL {
	private Connection con = null; //Database objects 
	  //�s��object 
	  private Statement stat = null; 
	  //����,�ǤJ��sql������r�� 
	  private ResultSet rs = null; 
	  //���G�� 
	  private PreparedStatement pst = null; 
	  //����,�ǤJ��sql���w�x���r��,�ݭn�ǤJ�ܼƤ���m 
	  //���Q��?�Ӱ��Х� 
	
	public static void main(String[] args) {
		connectSQL test = new connectSQL();
	}

	/*
	 * �{���ت��G�إߵ�L���]�P��߰��]
	 * �{�������G
	 * 			1.�q��ƮwŪ����L���]�P��߰��]����]�w��
	 * 				1.1 �M��Ʈw�i��s�u
	 * 				1.2Ū����L���]�ƭȻP��߰��]�ƭ�
	 * 				1.3�NŪ����L���]�ƭȻP��߰��]�ƭȼg�J���������
	 * 				1.4�����s�u
	 * 				1.5�^�ǵ�L���]�P��߰��]�ƭȵ��D�{��
	 */

	private connectSQL() 
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
	      "jdbc:mysql://localhost:5432/test?useUnicode=true&characterEncoding=utf8", 
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
	/*
	 * �{���ت��G�]�w�H�ߤ���
	 * �{�������G
	 * 			1.�q��ƮwŪ���H�ߤ��Ǩæ^�ǵ��D�{��
	 * 				1.1 �M��Ʈw�i��s�u
	 * 				1.2Ū���H�ߤ��Ǽƭ�
	 * 				1.3�NŪ���H�ߤ��Ǽƭȼg�J���������
	 * 				1.4�����s�u
	 * 				1.5�^�ǫH�ߤ��Ǽƭȵ��D�{��
	 */
	
	/*
	 * �{���ت��G�]�w�H�ߤ���
	 * �{�������G
	 * 			1.�q��ƮwŪ���H�ߤ��Ǩæ^�ǵ��D�{��
	 * 				1.1 �M��Ʈw�i��s�u
	 * 				1.2Ū���H�ߤ��Ǽƭ�
	 * 				1.3�NŪ���H�ߤ��Ǽƭȼg�J���������
	 * 				1.4�����s�u
	 * 				1.5�^�ǫH�ߤ��Ǽƭȵ��D�{��
	 */
	
	/*
	 * �{���ت��G�i��d��A�����˩w
	 * �{�������G
	 * 			1.�q��ƮwŪ�������l�ƾ�
	 * 				1.1 �M��Ʈw�i��s�u
	 * 				1.2Ū���H�ߤ��Ǽƭ�
	 * 				1.3�NŪ���H�ߤ��Ǽƭȼg�J���������
	 * 				1.4�����s�u
	 * 				1.5�^�ǫH�ߤ��Ǽƭȵ��D�{��
	 * 			2.�N����ƾڨ̷� Sturge's rule�M�w�ռ�
	 * 				������k=1+3.32log(n), k�O�ռ�;n�O�˥���
	 * 			3.�M�w�U�էO���W��(upper bound)�P�U��(lower bound)�è̷ӳs�򫬸�ƫ��A�խץ��C�ժ��W�ɻP�U�ɡC
	 * 			4.�p��C�@�ժ�����[���(Oi), i�N��ĴX�ո�ƪ��s��i,i={1,2,..., k }or {1,2,..., k+1}�C
	 * 			5.�p��C�@�ժ��z���[���(Ei), i�N��ĴX�ո�ƪ��s��i,i={1,2,..., k }or {1,2,..., k+1}�C
	 * 			6.�p��έp�q�� sum_i=1 ^ i=k or k+1 frac{(Oi-Ei)^2 }{Ei}�C
	 * 			7.�Y�j��έp�q�Ȫ����v��H����ǰ��ε���A�h�^�ǩڵ����n�A�N�׹���ƾکM�S�w�����ƻP�ܲ��ƪ��`�A���G���k�X�C
	 * 			  �Ϥ��A �Y�p�󵥩�έp�q�Ȫ����v��H����ǧC�A�h�^�Ǥ��ڵ����n�A�N�רS�������ҹ����ƾکM�S�w�����ƻP�ܲ��ƪ��`�A���G���k�X
	 * 			 �Y�ثe��������ƾکM�S�w�����ƻP�ܲ��ƪ��`�A���G�k�X���ź١C
	 * 			8.�N��������g�J����������
	 * 				8.1 �M��Ʈw�i��s�u
	 * 				8.2�N�˩w�έp�q�ȩM�˩w���G�g�J���������
	 * 				8.4�����s�u
	 * 				8.5�^��"��xxx������w�g����"���D�{��
	 */
}
