package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author rodash3
 * @since 2020. 9. 6.
 * @see https://www.acmicpc.net/problem/11727
 * @memory 12988 KB
 * @time 80 ms
 * @caution 값이 커질 수 있으므로 계산할 때마다 %연산 필요
 */
public class BJ_11727_S3_2xn타일링2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 2xN 크기의 직사각형
		
		int[] dp = new int[N+1];
		// 1x2 로만 채우기
		Arrays.fill(dp, 1);
		
		// 2x2, 2x1 포함
		for(int i=2; i<=N; i++) {
			dp[i] = (dp[i-1] + 2*dp[i-2])%10007;
		}
		System.out.println(dp[N]);
	}
}
