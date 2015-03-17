import java.math.*;
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
	 * 				Step3.4.�p��έp�q�� \sum_{i=1}^{k}  \frac{((o_{i}-e_{i})^(2))}{e_{i}}
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
		System.out.println("�п�J�H�ߤ���(Confidence level; 1 - alpha %)");
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
			double upper_k1  = 0;
			double lower_k1  = Double.NEGATIVE_INFINITY;
			
			double upper_k2  = muInput-3*sigmaInput;
			double lower_k2  = muInput-4*sigmaInput;
			
			double upper_k3  = muInput-2*sigmaInput;
			double lower_k3  = muInput-3*sigmaInput;
			
			double upper_k4  = muInput-sigmaInput;
			double lower_k4  = muInput-2*sigmaInput;
			
			double upper_k5  = muInput;
			double lower_k5  = muInput-sigmaInput;
			
			double upper_k6  = muInput+sigmaInput;
			double lower_k6  = muInput;
			
			double upper_k7  = muInput+sigmaInput;
			double lower_k7  = muInput+2*sigmaInput;
			
			double upper_k8  = muInput+3*sigmaInput;
			double lower_k8  = muInput+2*sigmaInput;
			
			double upper_k9  = muInput+4*sigmaInput;
			double lower_k9  = muInput+3*sigmaInput;
			
			double upper_k10 = Double.POSITIVE_INFINITY;
			double lower_k10 = muInput+4*sigmaInput;

				/*
				 * 3.1.1 �p��U�ժ����v��Pr_ki, i=1,2,3,...,10
				 * �w�n���`�A�������
				 * f(x)= (1/(2*mu*(sigma^(2)))^(2) ) * exp^((-1*(x-mu)^(2))/(2*sigma^(2)))
				 * �W����
				 * 
				 * �U����
				 * 
				 */


			double Pr_k1 = 0.423; //�n���������D
			double Pr_k2 = 0;
			double Pr_k3 = 0;
			double Pr_k4 = 0;
			double Pr_k5 = 0;
			double Pr_k6 = 0;
			double Pr_k7 = 0;
			double Pr_k8 = 0;
			double Pr_k9 = 0;
			double Pr_k10 = 0;
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
			System.out.println("K_9�����v��"+Pr_k9);
			System.out.println("K_10�����v��"+Pr_k10);

			
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
			double e_k9 = Math.round(Pr_k9*SampleNumInput);
			double e_k10 = Math.round(Pr_k10*SampleNumInput);
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
			System.out.println("K_9���z�ץX�{���Ƭ�"+e_k9);
			System.out.println("K_10���z�ץX�{���Ƭ�"+e_k10);
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
		
			System.out.println("�п�J�bk9�϶����X�{����");
			java.util.Scanner o_k9InputData = new
			        java.util.Scanner(System.in);
					double o_k9 = o_k9InputData.nextDouble();
		
			System.out.println("�п�J�bk10�϶����X�{����");
			java.util.Scanner o_k10InputData = new
			        java.util.Scanner(System.in);
					double o_k10 = o_k10InputData.nextDouble();
		
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
			System.out.println("K_9����ڥX�{���Ƭ�"+o_k9);
			System.out.println("K_10����ڥX�{���Ƭ�"+o_k10);
			/*
			 * Step3.4.�p��έp�q�� \sum_{i=1}^{k}  \frac{((o_{i}-e_{i})^(2))}{e_{i}}
			 */
			double F_k1 = Math.pow(o_k1-e_k1, 2)/e_k1;
			double F_k2 = Math.pow(o_k2-e_k2, 2)/e_k2;
			double F_k3 = Math.pow(o_k3-e_k3, 2)/e_k3;
			double F_k4 = Math.pow(o_k4-e_k4, 2)/e_k4;
			double F_k5 = Math.pow(o_k5-e_k5, 2)/e_k5;
			double F_k6 = Math.pow(o_k6-e_k6, 2)/e_k6;
			double F_k7 = Math.pow(o_k7-e_k7, 2)/e_k7;
			double F_k8 = Math.pow(o_k8-e_k8, 2)/e_k8;
			double F_k9 = Math.pow(o_k9-e_k9, 2)/e_k9;
			double F_k10 = Math.pow(o_k10-e_k10, 2)/e_k10;
			double final_F = F_k1+F_k2+F_k3+F_k4+F_k5+F_k6+F_k7+F_k8+F_k9+F_k10;
			
		/*
		 * Step4. �p��P-value  **�n�����D
		 */
			
			// double P_value = �d���Ʋέp�ȿn����L���j;
		/*
		 * Step5. �˩w���G
		 * �p�G��έp���٤j�����v��ƭ��n�p��0.05�A�h��� 
		 * "Reject H_0�A�������Ҿڱ�½�A�Y�����[���ƲŦX�`�A����������"+muInput+"�ܲ���"+sigmaInput"
		 * �_�h���
		 * "Do not reject H_0�A�S�������Ҿڱ�½�A���[���ƲŦX�`�A����������"+muInput+"�ܲ���"+sigmaInput"
		 */
			if(P_value<=0.05)
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