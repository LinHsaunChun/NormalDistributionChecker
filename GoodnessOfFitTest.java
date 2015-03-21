import java.math.*;

import org.apache.commons.math3.*;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;

import java.lang.Object;
public class GoodnessOfFitTest{
	
	/*
	 * 程式目的：進行卡方適切性檢定
	 * 程式說明：
	 * 			Step0.決定實驗樣本數(n)
	 * 			Step1.輸入虛無假設與對立假設實驗設定值
	 * 			Step2.輸入信心水準(1 - alpha %)
	 * 			Step3.計算檢定統計量值
	 * 				Step3.1.將機率函數切割成不同組，組數為k(組數在5~10之間)
	 * 						將欲檢定的常態分配機率分成K組，第k組的機率為積分常態分配，k=1, 2, 3, ..., k
	 * 						下限(lower bound)為第k-1的值；上限(upper bound)為第k的值。
	 * 				Step3.2.計算各組理論次數(e_{i})
	 * 						Step3.2.1. 計算理論出現次數
	 * 							        第k組出線的理論次數為第k組的理論次數e_{i}
	 * 				Step3.3.計算各組實際觀察次數(o_{i})
	 * 				Step3.4.計算統計量值 
	 * 			Step4. 計算P-value
	 * 			Step5. 檢定結果
	 * 			
	 */
		  
	public static void main(String[] args) {

		
		/*
		 *  Step0.決定實驗樣本數(n)
		 */
		   System.out.println("請輸入實驗樣本數(n)");
			java.util.Scanner SampleNumInputData = new
	                java.util.Scanner(System.in);
					int SampleNumInput = SampleNumInputData.nextInt();
			System.out.println("您輸入的樣本數為："+SampleNumInput);
	   
	   	/*
		 * Step1.輸入虛無假設與對立假設實驗設定值
		 */
	   

		   System.out.println("請輸入要檢定的常態分布的平均數值 (mu)");
			java.util.Scanner muInputData = new
	                java.util.Scanner(System.in);
					double muInput = muInputData.nextDouble();
			System.out.println("請輸入要檢定的常態分布的變異數值 (sigma)");
			java.util.Scanner sigmaInputData = new
			        java.util.Scanner(System.in);
					double sigmaInput = sigmaInputData.nextDouble();
			System.out.println("你要檢定的常態分布的平均數值 (mu)為"+muInput+"變異數值 (sigma)為"+sigmaInput);
			System.out.println("虛無假設(H_{0})為該觀察資料不符合常態分布平均數"+muInput+"變異數"+sigmaInput);
			System.out.println("對立假設(H_{1})為該觀察資料符合常態分布平均數"+muInput+"變異數"+sigmaInput);

	   
		/*
		 * Step2.輸入信心水準(1 - alpha %)
		 */
	   

		   System.out.println("請輸入信心水準(Confidence level; 1 - alpha %)，0.0到1.0之間的數值");
			java.util.Scanner ConfidenceLevelInputData = new
	        	java.util.Scanner(System.in);
			double ConfidenceLevelInput = ConfidenceLevelInputData.nextDouble();
			System.out.println("你輸入的信心水準為"+ConfidenceLevelInput);		
				double alpha = 1- ConfidenceLevelInput;	
			// System.out.println("alpha為"+alpha); //檢查用


		   /*
			 *   Step3.計算檢定統計量值
			 */
				
				/*
				 * Step3.1.將機率函數切割成不同組，組數為k(組數在5~10之間)
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
				 * 檢查用
				 */
				System.out.println("K_1的區間為"+"("+upper_k1+","+lower_k1+")");
				System.out.println("K_2的區間為"+"("+upper_k2+","+lower_k2+")");
				System.out.println("K_3的區間為"+"("+upper_k3+","+lower_k3+")");
				System.out.println("K_4的區間為"+"("+upper_k4+","+lower_k4+")");
				System.out.println("K_5的區間為"+"("+upper_k5+","+lower_k5+")");
				System.out.println("K_6的區間為"+"("+upper_k6+","+lower_k6+")");
				System.out.println("K_7的區間為"+"("+upper_k7+","+lower_k7+")");
				System.out.println("K_8的區間為"+"("+upper_k8+","+lower_k8+")");


					/*
					 * 3.1.1 計算各組的機率值Pr_ki, i=1,2,3,...,10
					 * 定積分常態分部函數
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
				 *  檢查累計積率值
				 */
				System.out.println("累積到K_1上限的機率為"+Pr_k1up);
				System.out.println("累積到K_2上限的機率為"+Pr_k2up);
				System.out.println("累積到K_3上限的機率為"+Pr_k3up);
				System.out.println("累積到K_4上限的機率為"+Pr_k4up);
				System.out.println("累積到K_5上限的機率為"+Pr_k5up);
				System.out.println("累積到K_6上限的機率為"+Pr_k6up);
				System.out.println("累積到K_7上限的機率為"+Pr_k7up);
				System.out.println("累積到K_8上限的機率為"+Pr_k8up);
				
				double Pr_k1low = s.cumulativeProbability(lower_k1);
				double Pr_k2low = s.cumulativeProbability(lower_k2);
				double Pr_k3low = s.cumulativeProbability(lower_k3);
				double Pr_k4low = s.cumulativeProbability(lower_k4);
				double Pr_k5low = s.cumulativeProbability(lower_k5);
				double Pr_k6low = s.cumulativeProbability(lower_k6);
				double Pr_k7low = s.cumulativeProbability(lower_k7);
				double Pr_k8low = s.cumulativeProbability(lower_k8);
				/*
				 *  檢查累計積率值
				 */
				System.out.println("累積到K_1下限的機率為"+Pr_k1low);
				System.out.println("累積到K_2下限的機率為"+Pr_k2low);
				System.out.println("累積到K_3下限的機率為"+Pr_k3low);
				System.out.println("累積到K_4下限的機率為"+Pr_k4low);
				System.out.println("累積到K_5下限的機率為"+Pr_k5low);
				System.out.println("累積到K_6下限的機率為"+Pr_k6low);
				System.out.println("累積到K_7下限的機率為"+Pr_k7low);
				System.out.println("累積到K_8下限的機率為"+Pr_k8low);
				
				double Pr_k1 = Pr_k1up-Pr_k1low;
				double Pr_k2 = Pr_k2up-Pr_k2low;
				double Pr_k3 = Pr_k3up-Pr_k3low;
				double Pr_k4 = Pr_k4up-Pr_k4low;
				double Pr_k5 = Pr_k5up-Pr_k5low;
				double Pr_k6 = Pr_k6up-Pr_k6low;
				double Pr_k7 = Pr_k7up-Pr_k7low;
				double Pr_k8 = Pr_k8up-Pr_k8low;			
				
				/*
				 * 檢查每一組的機率值
				 */
				System.out.println("K_1的機率為"+Pr_k1);
				System.out.println("K_2的機率為"+Pr_k2);
				System.out.println("K_3的機率為"+Pr_k3);
				System.out.println("K_4的機率為"+Pr_k4);
				System.out.println("K_5的機率為"+Pr_k5);
				System.out.println("K_6的機率為"+Pr_k6);
				System.out.println("K_7的機率為"+Pr_k7);
				System.out.println("K_8的機率為"+Pr_k8);

				
				/*
				 * Step3.2.計算各組理論次數(e_{i})
				 * 預定化分組數，如果預計次數
				 */
				
					/*
					 * Step3.2.1. 計算理論出現次數
		 			 *  		        第k組出線的理論次數為第k組的理論機率Pr(K=k)*n
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
				 * 檢查每一組的理論出現次數
				 */
			
				System.out.println("K_1的理論出現次數為"+e_k1);
				System.out.println("K_2的理論出現次數為"+e_k2);
				System.out.println("K_3的理論出現次數為"+e_k3);
				System.out.println("K_4的理論出現次數為"+e_k4);
				System.out.println("K_5的理論出現次數為"+e_k5);
				System.out.println("K_6的理論出現次數為"+e_k6);
				System.out.println("K_7的理論出現次數為"+e_k7);
				System.out.println("K_8的理論出現次數為"+e_k8);
				
				/*
				 *  存成矩陣形式給後面的卡方檢定函數使用
				 */
				
				double[] ExpectationNum = {e_k1, e_k2, e_k3, e_k4, e_k5,e_k6,e_k7,e_k8};
				
				/*
				 * Step3.3.計算各組實際觀察次數(o_{i})
				 */
				
				System.out.println("請輸入在k1區間的出現次數");
				java.util.Scanner o_k1InputData = new
				        java.util.Scanner(System.in);
						double o_k1 = o_k1InputData.nextDouble();
				
				System.out.println("請輸入在k1區間的出現次數");
				java.util.Scanner o_k2InputData = new
				        java.util.Scanner(System.in);
						double o_k2 = o_k2InputData.nextDouble();
				
				System.out.println("請輸入在k3區間的出現次數");
				java.util.Scanner o_k3InputData = new
				        java.util.Scanner(System.in);
						double o_k3 = o_k3InputData.nextDouble();

				System.out.println("請輸入在k4區間的出現次數");
				java.util.Scanner o_k4InputData = new
				        java.util.Scanner(System.in);
						double o_k4 = o_k4InputData.nextDouble();

				System.out.println("請輸入在k5區間的出現次數");
				java.util.Scanner o_k5InputData = new
				        java.util.Scanner(System.in);
						double o_k5 = o_k5InputData.nextDouble();

				System.out.println("請輸入在k6區間的出現次數");
				java.util.Scanner o_k6InputData = new
				        java.util.Scanner(System.in);
						double o_k6 = o_k6InputData.nextDouble();
		
				System.out.println("請輸入在k7區間的出現次數");
				java.util.Scanner o_k7InputData = new
				        java.util.Scanner(System.in);
						double o_k7 = o_k7InputData.nextDouble();
			
				System.out.println("請輸入在k8區間的出現次數");
				java.util.Scanner o_k8InputData = new
				        java.util.Scanner(System.in);
						double o_k8 = o_k8InputData.nextDouble();
			
			
				/*
				 * 檢查每一組的實際出現次數
				 */

				System.out.println("K_1的實際出現次數為"+o_k1);
				System.out.println("K_2的實際出現次數為"+o_k2);
				System.out.println("K_3的實際出現次數為"+o_k3);
				System.out.println("K_4的實際出現次數為"+o_k4);
				System.out.println("K_5的實際出現次數為"+o_k5);
				System.out.println("K_6的實際出現次數為"+o_k6);
				System.out.println("K_7的實際出現次數為"+o_k7);
				System.out.println("K_8的實際出現次數為"+o_k8);
				double[] ObserveNum = {o_k1, o_k2, o_k3, o_k4, o_k5,o_k6,o_k7,o_k8};
					
				/*
				 * Step3.4.計算統計量值 \sum_{i=1}^{k}  \frac{((o_{i}-e_{i})^(2))}{e_{i}}
				 */
				double F_k1 = Math.pow(o_k1-e_k1, 2)/e_k1;
				/*
				 * 避免e_k1為時，造成數學邏輯錯誤
				 */				
				if(e_k1==0)
				{
					e_k1 = 0.00000000001;
				}
				double F_k2 = Math.pow(o_k2-e_k2, 2)/e_k2;
				/*
				 * 避免e_k2為時，造成數學邏輯錯誤
				 */				
				if(e_k2==0)
				{
					e_k2 = 0.00000000001;
				}
				double F_k3 = Math.pow(o_k3-e_k3, 2)/e_k3;
				/*
				 * 避免e_k3為時，造成數學邏輯錯誤
				 */				
				if(e_k3==0)
				{
					e_k3 = 0.00000000001;
				}
				double F_k4 = Math.pow(o_k4-e_k4, 2)/e_k4;
				/*
				 * 避免e_k47為時，造成數學邏輯錯誤
				 */				
				if(e_k4==0)
				{
					e_k4 = 0.00000000001;
				}
				double F_k5 = Math.pow(o_k5-e_k5, 2)/e_k5;
				/*
				 * 避免e_k5為時，造成數學邏輯錯誤
				 */				
				if(e_k5==0)
				{
					e_k5 = 0.00000000001;
				}
				double F_k6 = Math.pow(o_k6-e_k6, 2)/e_k6;
				/*
				 * 避免e_k6為時，造成數學邏輯錯誤
				 */				
				if(e_k6==0)
				{
					e_k6 = 0.00000000001;
				}
				double F_k7 = Math.pow(o_k7-e_k7, 2)/e_k7;
				/*
				 * 避免e_k7為時，造成數學邏輯錯誤
				 */
				if(e_k7==0)
				{
					e_k7 = 0.00000000001;
				}
				double F_k8 = Math.pow(o_k8-e_k8, 2)/e_k8;
				/*
				 * 避免e_k8為時，造成數學邏輯錯誤
				 */
					if(e_k8==0)
					{
						e_k8 = 0.00000000001;
					}
				double final_F = F_k1+F_k2+F_k3+F_k4+F_k5+F_k6+F_k7+F_k8;
				
				/*
				 * 檢查用
				 */
				System.out.println("K_1的統計值為"+F_k1);
				System.out.println("K_2的統計值為"+F_k2);
				System.out.println("K_3的統計值為"+F_k3);
				System.out.println("K_4的統計值為"+F_k4);
				System.out.println("K_5的統計值為"+F_k5);
				System.out.println("K_6的統計值為"+F_k6);
				System.out.println("K_7的統計值為"+F_k7);
				System.out.println("K_8的統計值為"+F_k8);
				System.out.println("本次檢定的統計值為"+final_F);
			/*
			 * Step4. 計算P-value  
			 */
				/*
				 *  導入卡方分布函數，參數為自由度
				 */
				ChiSquaredDistribution d = new ChiSquaredDistribution(SampleNumInput-1);
				double StatisticalValue= d.cumulativeProbability(final_F);
				double P_value = 1-StatisticalValue;
				/*
				 * 檢查用
				 */
				System.out.println("累積機率為"+StatisticalValue);
				System.out.println("p value is "+P_value);
			/*
			 * Step5. 檢定結果
			 * 如果比統計值還大的機率函數面積小於alpha，則顯示 
			 * "Reject H_0，有足夠證據推翻，即為該觀察資料符合常態分布平均數"+muInput+"變異數"+sigmaInput"
			 * 否則顯示
			 * "Do not reject H_0，沒有足夠證據推翻，該觀察資料符合常態分布平均數"+muInput+"變異數"+sigmaInput"
			 */
				if(P_value<=alpha)
				{
					System.out.println("Reject H_0，有足夠證據推翻，該觀察資料不符合常態分布平均數"+muInput+"變異數"+sigmaInput);
					System.out.println("意謂該觀察數量分部服從常態分部，平均數"+muInput+"變異數"+sigmaInput);
				}
				else
				{
					System.out.println("Do not reject H_0，沒有足夠證據推翻，該觀察資料不符合常態分布平均數"+muInput+"變異數"+sigmaInput);
					System.out.println("意謂該觀察數量分部不服從常態分部，平均數"+muInput+"變異數"+sigmaInput);
				}
		
	}

	
}