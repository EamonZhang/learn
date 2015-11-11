package editordistance;

import java.util.Date;

//http://www.cnblogs.com/biyeymyhjob/archive/2012/09/28/2707343.html
public class Test {
	public int fun(String source, String target) {
		int i, j;
		int[][] d = new int[source.length() + 1][target.length() + 1];
		for (i = 1; i < source.length() + 1; i++) {/* 初始化临界值 */
			d[i][0] = i;
		}
		for (j = 1; j < target.length() + 1; j++) {/* 初始化临界值 */
			d[0][j] = j;
		}
		for (i = 1; i < source.length() + 1; i++) {/* 动态规划填表 */
			for (j = 1; j < target.length() + 1; j++) {
				if (source.substring(i - 1, i).equals(
						target.substring(j - 1, j))) {
					d[i][j] = d[i - 1][j - 1];/* source的第i个和target的第j个相同时 */
				} else {/* 不同的时候则取三种操作最小的一个 */
					d[i][j] = min(d[i][j - 1] + 1, d[i - 1][j] + 1,
							d[i - 1][j - 1] + 1);
				}
			}
		}
		// for(int n = 0 ;n<= source.length();n++){
		// for(int m = 0;m <= target.length();m++){
		// System.out.print(d[n][m]+" ");
		// }
		// System.out.println();
		// }
		return d[source.length()][target.length()];
	}

	private int min(int i, int j, int k) {
		int min = i < j ? i : j;
		min = min < k ? min : k;
		return min;
	}

	public static void main(String[] args) {
		Test ss = new Test();
		String str1 = "sailn";
		String str2 = "failing";
		// ss.fun(str1, str2);
		System.out.println(ss.fun(str1, str2));
	}
	// 0 f a i l i n g
	// 0 0 1 2 3 4 5 6 7
	// s 1 1 2 3 4 5 6 7
	// a 2 2 1 2 3 4 5 6
	// i 3 3 2 1 2 3 4 5
	// l 4 4 3 2 1 2 3 4
	// n 5 5 4 3 2 2 2 3

}
