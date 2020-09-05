package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 9. 6.
 * @see https://www.acmicpc.net/problem/11052
 * @memory 13340 KB
 * @time 92 ms
 * @caution
 */
public class BJ_11052_S1_ī�屸���ϱ� {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ī�� N��
		
		int[] P = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] price = new int[N+1];
		// 1�� �����θ� ���
		for(int i=0; i<=N; i++) {
			price[i] = P[1]*i;
		}
		// i�� �� ����
		for(int i=2; i<=N; i++) {
			for(int k=i; k<=N; k++) {
				if(price[k-i] + P[i] > price[k]) {
					price[k] = price[k-i] + P[i];
				}
			}
		}
		System.out.println(price[N]);
	}
}
