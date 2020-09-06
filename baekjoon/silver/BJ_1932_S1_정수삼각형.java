package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 9. 6.
 * @see https://www.acmicpc.net/problem/1932
 * @memory 26512 KB
 * @time 244 ms
 * @caution 이차원 배열 DP
 */
public class BJ_1932_S1_정수삼각형 {

	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 삼각형 크기

		int[][] triangle = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int j = 0;
			while (st.hasMoreTokens()) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
				j++;
			}
		}

		int[][] dp = new int[N][N];
		dp[0][0] = triangle[0][0];

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i + 1; j++) {
				if (j == 0) { // 왼쪽
					dp[i][j] = dp[i-1][j] + triangle[i][j];
				} else if (j == i) { // 오른쪽
					dp[i][j] = dp[i-1][j-1] + triangle[i][j];
				} else { // 가운데
					dp[i][j] = Math.max(dp[i-1][j-1] + triangle[i][j], dp[i-1][j] + triangle[i][j]);
				}
			}
		} // for
		
		Arrays.sort(dp[N-1]);
		System.out.println(dp[N-1][N-1]);
	}
}
