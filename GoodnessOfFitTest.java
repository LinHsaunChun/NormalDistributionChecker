import java.math.*;

import org.apache.commons.math3.*;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;

import java.lang.Object;
public class GoodnessOfFitTest{
	
	/*
	 * �{���ت��G�i��d��A�����˩w
	 * �{�������G
	 * 			Step0.�M�w����˥���(n)
	 * 			Step1.��J��L���]�P��߰��]����]�w��
	 * 			Step2.��J�H�ߤ���(1 - alpha %)
	 * 			Step3.�p���˩w�έp�q��
	 * 				Step3.1.�N���v��Ƥ��Φ����P�աA�ռƬ�k(�ռƦb5~10����)
	 * 						�N���˩w���`�A���t���v����K�աA��k�ժ����v���n���`�A���t�Ak=1, 2, 3, ..., k
	 * 						�U��(lower bound)����k-1���ȡF�W��(upper bound)����k���ȡC
	 * 				Step3.2.�p��U�ղz�צ���(e_{i})
	 * 						Step3.2.1. �p��z�ץX�{����
	 * 							        ��k�եX�u���z�צ��Ƭ���k�ժ��z�צ���e_{i}
	 * 				Step3.3.�p��U�չ���[���(o_{i})
	 * 				Step3.4.�p��έp�q�� 
	 * 			Step4. �p��P-value
	 * 			Step5. �˩w���G
	 * 			
	 */
		  
	public static void main(String[] args) {

		
		/*
		 *  Step0.�M�w����˥���(n)
		 */
		   System.out.println("�п�J����˥���(n)");
			java.util.Scanner SampleNumInputData = new
	                java.util.Scanner(System.in);
					int SampleNumInput = SampleNumInputData.nextInt();
			System.out.println("�z��J���˥��Ƭ��G"+SampleNumInput);
	   
	   	/*
		 * Step1.��J��L���]�P��߰��]����]�w��
		 */
	   

		   System.out.println("�п�J�n�˩w���`�A�����������ƭ� (mu)");
			java.util.Scanner muInputData = new
	                java.util.Scanner(System.in);
					double muInput = muInputData.nextDouble();
			System.out.println("�п�J�n�˩w���`�A�������ܲ��ƭ� (sigma)");
			java.util.Scanner sigmaInputData = new
			        java.util.Scanner(System.in);
					double sigmaInput = sigmaInputData.nextDouble();
			System.out.println("�A�n�˩w���`�A�����������ƭ� (mu)��"+muInput+"�ܲ��ƭ� (sigma)��"+sigmaInput);
			System.out.println("��L���](H_{0})�����[���Ƥ��ŦX�`�A����������"+muInput+"�ܲ���"+sigmaInput);
			System.out.println("��߰��](H_{1})�����[���ƲŦX�`�A����������"+muInput+"�ܲ���"+sigmaInput);

	   
		/*
		 * Step2.��J�H�ߤ���(1 - alpha %)
		 */
	   

		   System.out.println("�п�J�H�ߤ���(Confidence level; 1 - alpha %)�A0.0��1.0�������ƭ�");
			java.util.Scanner ConfidenceLevelInputData = new
	        	java.util.Scanner(System.in);
			double ConfidenceLevelInput = ConfidenceLevelInputData.nextDouble();
			System.out.println("�A��J���H�ߤ��Ǭ�"+ConfidenceLevelInput);		
				double alpha = 1- ConfidenceLevelInput;	
			// System.out.println("alpha��"+alpha); //�ˬd��


		   /*
			 *   Step3.�p���˩w�έp�q��
			 */
				
				/*
				 * Step3.1.�N���v��Ƥ��Φ����P�աA�ռƬ�k(�ռƦb5~10����)
				 * 
				 * k=1 ;  -infinite ~ mu-4sigma
				 * k=2 ;  mu-4sigma ~ mu-3sigma
				 * k=3 ;  mu-3sigma ~ mu-2sigma
				 * k=4 ;  mu-2sigma ~ mu-sigma
				 * k=5 ;  mu-sigma  ~ mu
				 * k=6 ;  mu  ~ mu+sigma
				 * k=7 ;  mu+sigma  ~ mu+2sigma 
				 * k=8 ;  mu+2sigma  ~ mu+3sigma
				 * k=9 ;  mu+3sigma  ~ mu+4sigma
				 * k=10 ;  mu+4sigma  ~ infinite
				 * 
				 */
				
				double upper_k1  = muInput-3*sigmaInput;
				double lower_k1  = Double.MIN_VALUE;
				
				double upper_k2  = muInput-2*sigmaInput;
				double lower_k2  = muInput-3*sigmaInput;
				
				double upper_k3  = muInput-sigmaInput;
				double lower_k3  = muInput-2*sigmaInput;
				
				double upper_k4  = muInput;
				double lower_k4  = muInput-sigmaInput;
				
				double upper_k5  = muInput+sigmaInput;
				double lower_k5  = muInput;
				
				double upper_k6  = muInput+2*sigmaInput;
				double lower_k6  = muInput+sigmaInput;
				
				double upper_k7  = muInput+3*sigmaInput;
				double lower_k7  = muInput+2*sigmaInput;
						
				double upper_k8  = Double.MAX_VALUE;
				double lower_k8  = muInput+3*sigmaInput;

				
				/*
				 * �ˬd��
				 */
				System.out.println("K_1���϶���"+"("+upper_k1+","+lower_k1+")");
				System.out.println("K_2���϶���"+"("+upper_k2+","+lower_k2+")");
				System.out.println("K_3���϶���"+"("+upper_k3+","+lower_k3+")");
				System.out.println("K_4���϶���"+"("+upper_k4+","+lower_k4+")");
				System.out.println("K_5���϶���"+"("+upper_k5+","+lower_k5+")");
				System.out.println("K_6���϶���"+"("+upper_k6+","+lower_k6+")");
				System.out.println("K_7���϶���"+"("+upper_k7+","+lower_k7+")");
				System.out.println("K_8���϶���"+"("+upper_k8+","+lower_k8+")");


					/*
					 * 3.1.1 �p��U�ժ����v��Pr_ki, i=1,2,3,...,10
					 * �w�n���`�A�������
					 * f(x)= (1/(2*mu*(sigma^(2)))^(2) ) * exp^((-1*(x-mu)^(2))/(2*sigma^(2)))
					 * 
					 * 
					 * 
					 * 
					 */
				NormalDistribution s = new NormalDistribution(muInput,sigmaInput);
				
				double Pr_k1up = s.cumulativeProbability(upper_k1);
				double Pr_k2up = s.cumulativeProbability(upper_k2);
				double Pr_k3up = s.cumulativeProbability(upper_k3);
				double Pr_k4up = s.cumulativeProbability(upper_k4);
				double Pr_k5up = s.cumulativeProbability(upper_k5);
				double Pr_k6up = s.cumulativeProbability(upper_k6);
				double Pr_k7up = s.cumulativeProbability(upper_k7);
				double Pr_k8up = s.cumulativeProbability(upper_k8);
				
				/*
				 *  �ˬd�֭p�n�v��
				 */
				System.out.println("�ֿn��K_1�W�������v��"+Pr_k1up);
				System.out.println("�ֿn��K_2�W�������v��"+Pr_k2up);
				System.out.println("�ֿn��K_3�W�������v��"+Pr_k3up);
				System.out.println("�ֿn��K_4�W�������v��"+Pr_k4up);
				System.out.println("�ֿn��K_5�W�������v��"+Pr_k5up);
				System.out.println("�ֿn��K_6�W�������v��"+Pr_k6up);
				System.out.println("�ֿn��K_7�W�������v��"+Pr_k7up);
				System.out.println("�ֿn��K_8�W�������v��"+Pr_k8up);
				
				double Pr_k1low = s.cumulativeProbability(lower_k1);
				double Pr_k2low = s.cumulativeProbability(lower_k2);
				double Pr_k3low = s.cumulativeProbability(lower_k3);
				double Pr_k4low = s.cumulativeProbability(lower_k4);
				double Pr_k5low = s.cumulativeProbability(lower_k5);
				double Pr_k6low = s.cumulativeProbability(lower_k6);
				double Pr_k7low = s.cumulativeProbability(lower_k7);
				double Pr_k8low = s.cumulativeProbability(lower_k8);
				/*
				 *  �ˬd�֭p�n�v��
				 */
				System.out.println("�ֿn��K_1�U�������v��"+Pr_k1low);
				System.out.println("�ֿn��K_2�U�������v��"+Pr_k2low);
				System.out.println("�ֿn��K_3�U�������v��"+Pr_k3low);
				System.out.println("�ֿn��K_4�U�������v��"+Pr_k4low);
				System.out.println("�ֿn��K_5�U�������v��"+Pr_k5low);
				System.out.println("�ֿn��K_6�U�������v��"+Pr_k6low);
				System.out.println("�ֿn��K_7�U�������v��"+Pr_k7low);
				System.out.println("�ֿn��K_8�U�������v��"+Pr_k8low);
				
				double Pr_k1 = Pr_k1up-Pr_k1low;
				double Pr_k2 = Pr_k2up-Pr_k2low;
				double Pr_k3 = Pr_k3up-Pr_k3low;
				double Pr_k4 = Pr_k4up-Pr_k4low;
				double Pr_k5 = Pr_k5up-Pr_k5low;
				double Pr_k6 = Pr_k6up-Pr_k6low;
				double Pr_k7 = Pr_k7up-Pr_k7low;
				double Pr_k8 = Pr_k8up-Pr_k8low;			
				
				/*
				 * �ˬd�C�@�ժ����v��
				 */
				System.out.println("K_1�����v��"+Pr_k1);
				System.out.println("K_2�����v��"+Pr_k2);
				System.out.println("K_3�����v��"+Pr_k3);
				System.out.println("K_4�����v��"+Pr_k4);
				System.out.println("K_5�����v��"+Pr_k5);
				System.out.println("K_6�����v��"+Pr_k6);
				System.out.println("K_7�����v��"+Pr_k7);
				System.out.println("K_8�����v��"+Pr_k8);

				
				/*
				 * Step3.2.�p��U�ղz�צ���(e_{i})
				 * �w�w�Ƥ��ռơA�p�G�w�p����
				 */
				
					/*
					 * Step3.2.1. �p��z�ץX�{����
		 			 *  		        ��k�եX�u���z�צ��Ƭ���k�ժ��z�׾��vPr(K=k)*n
					 */
				double e_k1 = Math.round(Pr_k1*SampleNumInput); 
				double e_k2 = Math.round(Pr_k2*SampleNumInput);
				double e_k3 = Math.round(Pr_k3*SampleNumInput);
				double e_k4 = Math.round(Pr_k4*SampleNumInput);
				double e_k5 = Math.round(Pr_k5*SampleNumInput);
				double e_k6 = Math.round(Pr_k6*SampleNumInput);
				double e_k7 = Math.round(Pr_k7*SampleNumInput);
				double e_k8 = Math.round(Pr_k8*SampleNumInput);

				
				
				/*
				 * �ˬd�C�@�ժ��z�ץX�{����
				 */
			
				System.out.println("K_1���z�ץX�{���Ƭ�"+e_k1);
				System.out.println("K_2���z�ץX�{���Ƭ�"+e_k2);
				System.out.println("K_3���z�ץX�{���Ƭ�"+e_k3);
				System.out.println("K_4���z�ץX�{���Ƭ�"+e_k4);
				System.out.println("K_5���z�ץX�{���Ƭ�"+e_k5);
				System.out.println("K_6���z�ץX�{���Ƭ�"+e_k6);
				System.out.println("K_7���z�ץX�{���Ƭ�"+e_k7);
				System.out.println("K_8���z�ץX�{���Ƭ�"+e_k8);
				
				/*
				 *  �s���x�}�Φ����᭱���d���˩w��ƨϥ�
				 */
				
				double[] ExpectationNum = {e_k1, e_k2, e_k3, e_k4, e_k5,e_k6,e_k7,e_k8};
				
				/*
				 * Step3.3.�p��U�չ���[���(o_{i})
				 */
				
				System.out.println("�п�J�bk1�϶����X�{����");
				java.util.Scanner o_k1InputData = new
				        java.util.Scanner(System.in);
						double o_k1 = o_k1InputData.nextDouble();
				
				System.out.println("�п�J�bk1�϶����X�{����");
				java.util.Scanner o_k2InputData = new
				        java.util.Scanner(System.in);
						double o_k2 = o_k2InputData.nextDouble();
				
				System.out.println("�п�J�bk3�϶����X�{����");
				java.util.Scanner o_k3InputData = new
				        java.util.Scanner(System.in);
						double o_k3 = o_k3InputData.nextDouble();

				System.out.println("�п�J�bk4�϶����X�{����");
				java.util.Scanner o_k4InputData = new
				        java.util.Scanner(System.in);
						double o_k4 = o_k4InputData.nextDouble();

				System.out.println("�п�J�bk5�϶����X�{����");
				java.util.Scanner o_k5InputData = new
				        java.util.Scanner(System.in);
						double o_k5 = o_k5InputData.nextDouble();

				System.out.println("�п�J�bk6�϶����X�{����");
				java.util.Scanner o_k6InputData = new
				        java.util.Scanner(System.in);
						double o_k6 = o_k6InputData.nextDouble();
		
				System.out.println("�п�J�bk7�϶����X�{����");
				java.util.Scanner o_k7InputData = new
				        java.util.Scanner(System.in);
						double o_k7 = o_k7InputData.nextDouble();
			
				System.out.println("�п�J�bk8�϶����X�{����");
				java.util.Scanner o_k8InputData = new
				        java.util.Scanner(System.in);
						double o_k8 = o_k8InputData.nextDouble();
			
			
				/*
				 * �ˬd�C�@�ժ���ڥX�{����
				 */

				System.out.println("K_1����ڥX�{���Ƭ�"+o_k1);
				System.out.println("K_2����ڥX�{���Ƭ�"+o_k2);
				System.out.println("K_3����ڥX�{���Ƭ�"+o_k3);
				System.out.println("K_4����ڥX�{���Ƭ�"+o_k4);
				System.out.println("K_5����ڥX�{���Ƭ�"+o_k5);
				System.out.println("K_6����ڥX�{���Ƭ�"+o_k6);
				System.out.println("K_7����ڥX�{���Ƭ�"+o_k7);
				System.out.println("K_8����ڥX�{���Ƭ�"+o_k8);
				double[] ObserveNum = {o_k1, o_k2, o_k3, o_k4, o_k5,o_k6,o_k7,o_k8};
					
				/*
				 * Step3.4.�p��έp�q�� \sum_{i=1}^{k}  \frac{((o_{i}-e_{i})^(2))}{e_{i}}
				 */
				double F_k1 = Math.pow(o_k1-e_k1, 2)/e_k1;
				/*
				 * �קKe_k1���ɡA�y���ƾ��޿���~
				 */				
				if(e_k1==0)
				{
					e_k1 = 0.00000000001;
				}
				double F_k2 = Math.pow(o_k2-e_k2, 2)/e_k2;
				/*
				 * �קKe_k2���ɡA�y���ƾ��޿���~
				 */				
				if(e_k2==0)
				{
					e_k2 = 0.00000000001;
				}
				double F_k3 = Math.pow(o_k3-e_k3, 2)/e_k3;
				/*
				 * �קKe_k3���ɡA�y���ƾ��޿���~
				 */				
				if(e_k3==0)
				{
					e_k3 = 0.00000000001;
				}
				double F_k4 = Math.pow(o_k4-e_k4, 2)/e_k4;
				/*
				 * �קKe_k47���ɡA�y���ƾ��޿���~
				 */				
				if(e_k4==0)
				{
					e_k4 = 0.00000000001;
				}
				double F_k5 = Math.pow(o_k5-e_k5, 2)/e_k5;
				/*
				 * �קKe_k5���ɡA�y���ƾ��޿���~
				 */				
				if(e_k5==0)
				{
					e_k5 = 0.00000000001;
				}
				double F_k6 = Math.pow(o_k6-e_k6, 2)/e_k6;
				/*
				 * �קKe_k6���ɡA�y���ƾ��޿���~
				 */				
				if(e_k6==0)
				{
					e_k6 = 0.00000000001;
				}
				double F_k7 = Math.pow(o_k7-e_k7, 2)/e_k7;
				/*
				 * �קKe_k7���ɡA�y���ƾ��޿���~
				 */
				if(e_k7==0)
				{
					e_k7 = 0.00000000001;
				}
				double F_k8 = Math.pow(o_k8-e_k8, 2)/e_k8;
				/*
				 * �קKe_k8���ɡA�y���ƾ��޿���~
				 */
					if(e_k8==0)
					{
						e_k8 = 0.00000000001;
					}
				double final_F = F_k1+F_k2+F_k3+F_k4+F_k5+F_k6+F_k7+F_k8;
				
				/*
				 * �ˬd��
				 */
				System.out.println("K_1���έp�Ȭ�"+F_k1);
				System.out.println("K_2���έp�Ȭ�"+F_k2);
				System.out.println("K_3���έp�Ȭ�"+F_k3);
				System.out.println("K_4���έp�Ȭ�"+F_k4);
				System.out.println("K_5���έp�Ȭ�"+F_k5);
				System.out.println("K_6���έp�Ȭ�"+F_k6);
				System.out.println("K_7���έp�Ȭ�"+F_k7);
				System.out.println("K_8���έp�Ȭ�"+F_k8);
				System.out.println("�����˩w���έp�Ȭ�"+final_F);
			/*
			 * Step4. �p��P-value  
			 */
				/*
				 *  �ɤJ�d�������ơA�ѼƬ��ۥѫ�
				 */
				ChiSquaredDistribution d = new ChiSquaredDistribution(SampleNumInput-1);
				double StatisticalValue= d.cumulativeProbability(final_F);
				double P_value = 1-StatisticalValue;
				/*
				 * �ˬd��
				 */
				System.out.println("�ֿn���v��"+StatisticalValue);
				System.out.println("p value is "+P_value);
			/*
			 * Step5. �˩w���G
			 * �p�G��έp���٤j�����v��ƭ��n�p��alpha�A�h��� 
			 * "Reject H_0�A�������Ҿڱ�½�A�Y�����[���ƲŦX�`�A����������"+muInput+"�ܲ���"+sigmaInput"
			 * �_�h���
			 * "Do not reject H_0�A�S�������Ҿڱ�½�A���[���ƲŦX�`�A����������"+muInput+"�ܲ���"+sigmaInput"
			 */
				if(P_value<=alpha)
				{
					System.out.println("Reject H_0�A�������Ҿڱ�½�A���[���Ƥ��ŦX�`�A����������"+muInput+"�ܲ���"+sigmaInput);
					System.out.println("�N�׸��[��ƶq�����A�q�`�A�����A������"+muInput+"�ܲ���"+sigmaInput);
				}
				else
				{
					System.out.println("Do not reject H_0�A�S�������Ҿڱ�½�A���[���Ƥ��ŦX�`�A����������"+muInput+"�ܲ���"+sigmaInput);
					System.out.println("�N�׸��[��ƶq�������A�q�`�A�����A������"+muInput+"�ܲ���"+sigmaInput);
				}
		
	}

	
}