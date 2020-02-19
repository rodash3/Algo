package hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7965_D4_퀴즈 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= TC; i++) {
			int N = Integer.parseInt(br.readLine());

			long sum = 0;

			for (int k = 1; k <= N; k++) {
				sum += byDnC(k, k);
				sum %= 1000000007;
			}

			System.out.println("#" + i + " " + sum);
		}

	}

	public static long byDnC(int x, int n) {
		if (n == 1) {
			return x;
		}
		if (n % 2 == 0) {
			long d1 = byDnC(x, n / 2);
			return (d1 * d1) % 1000000007;
		} else {
			long d1 = byDnC(x, n / 2);
			return ((d1 * d1) % 1000000007 * x) % 1000000007;
		}
	}

}
