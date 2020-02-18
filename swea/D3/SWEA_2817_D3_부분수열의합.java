package algo_ad.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2817_D3_부분수열의합 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= TC; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // N개의 자연수
			int K = Integer.parseInt(st.nextToken()); // 부분 수열의 합
			int cnt = 0;

			int[] src = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				src[j] = Integer.parseInt(st.nextToken());
			}

			for (int j = 0; j < 1 << N; j++) {
				int sum = 0;
				for (int k = 0; k < N; k++) {
					if ((j & 1 << k) > 0) {
						sum += src[k];
					}
				}
				if (sum == K) {
					cnt++;
				}
			}

			System.out.println("#" + i + " " + cnt);
		}

	}

}
