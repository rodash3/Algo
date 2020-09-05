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
 * @caution ���� Ŀ�� �� �����Ƿ� ����� ������ %���� �ʿ�
 */
public class BJ_11727_S3_2xnŸ�ϸ�2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 2xN ũ���� ���簢��
		
		int[] dp = new int[N+1];
		// 1x2 �θ� ä���
		Arrays.fill(dp, 1);
		
		// 2x2, 2x1 ����
		for(int i=2; i<=N; i++) {
			dp[i] = (dp[i-1] + 2*dp[i-2])%10007;
		}
		System.out.println(dp[N]);
	}
}
