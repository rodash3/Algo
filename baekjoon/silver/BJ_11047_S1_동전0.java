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
 * @caution 그리디
 */
public class BJ_11047_S1_동전0 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 동전 종류
		int K = Integer.parseInt(st.nextToken()); // 목표 가치
		
		int[] A = new int[N];
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}//input
		
		int cnt = 0; // K를 만들기 위한 최소 동전 개수
		while (K != 0) {
			int coin = 0; // K >= coin 중 가장 큰 동전
			for(int i=0; i<N; i++) {
				if(A[i] <= K) coin = A[i];
			}
			
			cnt += K/coin;
			K %= coin;
		}
		System.out.println(cnt);
	}
}
