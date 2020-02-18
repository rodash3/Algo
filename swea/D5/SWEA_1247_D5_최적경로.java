package algo_ad.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1247_D5_최적경로 {

	static int N;
	static Dot office, home;
	static Dot[] customers;
	static boolean[] visited;
	static int minDistance;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= TC; i++) {

			minDistance = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine()); // 고객의 수
			st = new StringTokenizer(br.readLine());
			office = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			customers = new Dot[N];
			for (int j = 0; j < N; j++) {
				customers[j] = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			visited = new boolean[N];
			perm(0, N, new Dot[N]);

			System.out.println("#" + i + " " + minDistance);
		}

	}

	static void perm(int k, int r, Dot[] temp) {
		if (k == r) {
			calDistance(temp);
//			System.out.println(Arrays.toString(temp));
			return;
		} else {
			for (int i = 0; i < N; i++) {
				if (!visited[i]) {
					temp[k] = customers[i];
					visited[i] = true;
					perm(k + 1, r, temp);
					visited[i] = false;
				}
			}
		}
	}

	private static void calDistance(Dot[] temp) {
		int distance = 0;

		distance += Math.abs(office.x - temp[0].x) + Math.abs(office.y - temp[0].y);
		for (int i = 0; i < N - 1; i++) {
			distance += Math.abs(temp[i].x - temp[i + 1].x) + Math.abs(temp[i].y - temp[i + 1].y);
			if (distance > minDistance)
				return;
		}
		distance += Math.abs(temp[N - 1].x - home.x) + Math.abs(temp[N - 1].y - home.y);

		minDistance = Math.min(distance, minDistance);
	}

	static class Dot {
		int x;
		int y;

		public Dot(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Dot [x=" + x + ", y=" + y + "]";
		}
	}
}