package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 9. 7.
 * @see https://www.acmicpc.net/problem/11047
 * @memory 12988 KB
 * @time 80 ms
 * @caution �׸���
 */
public class BJ_11047_S1_����0 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ���� ����
		int K = Integer.parseInt(st.nextToken()); // ��ǥ ��ġ
		
		int[] A = new int[N];
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}//input
		
		int cnt = 0; // K�� ����� ���� �ּ� ���� ����
		while (K != 0) {
			int coin = 0; // K >= coin �� ���� ū ����
			for(int i=0; i<N; i++) {
				if(A[i] <= K) coin = A[i];
			}
			
			cnt += K/coin;
			K %= coin;
		}
		System.out.println(cnt);
	}
}
