package editordistance;

import java.util.Date;

/**
 * @author zhangjin
 *
 */
class StringSimilar {
	public static void main(String[] args) {
		String str1 = "中国e";
		String str2 = "中国人";
		
//		int l1 = str1.length();
//		int l2 = str2.length();
//		
//		int l = l1;
//		if(l1>l2){
//			l = l2;
//		}
		double k = new StringSimilar().getStringSimilar(str1,str2);
		System.out.println(k);
	}
	// 编辑距离求串相似度
	public double getStringSimilar(String s1, String s2) {
		// TODOAuto-generatedmethodstub
		double d[][];// matrix
		int n;// lengthofs
		int m;// lengthoft
		int i;// iteratesthroughs
		int j;// iteratesthrought
		char s_i;// ithcharacterofs
		char t_j;// jthcharacteroft
		double cost;// cost
		// Step1
		n = s1.length();
		m = s2.length();
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		d = new double[n + 1][m + 1];
		// Step2
		for (i = 0; i <= n; i++) {
			d[i][0] = i;
		}
		for (j = 0; j <= m; j++) {
			d[0][j] = j;
		}
		// Step3
		for (i = 1; i <= n; i++) {
			s_i = s1.charAt(i - 1);
			// Step4
			for (j = 1; j <= m; j++) {
				t_j = s2.charAt(j - 1);
				// Step5
				if (s_i == t_j) {
					cost = 0;
				} else {
					cost = 1;
				}
				// Step6
				d[i][j] = Minimum(d[i - 1][j] + 1, d[i][j - 1] + 1,
						d[i - 1][j - 1] + cost);
			}
		}
		// Step7
		return d[n][m];
	}

	// 求最小值
	private double Minimum(double a, double b, double c) {
		// TODOAuto-generatedmethodstub
		double mi;
		mi = a;
		if (b < mi) {
			mi = b;
		}
		if (c < mi) {
			mi = c;
		}
		return mi;
	}
}
