package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7576_S1_토마토 {

	private static int M;
	private static int N;
	private static int[][] box;
	static Queue<Dot> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 상자 가로칸
		N = Integer.parseInt(st.nextToken()); // 상자 세로칸

		// 토마토 상태 입력
		box = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				box[r][c] = Integer.parseInt(st.nextToken());
				// 상태가 익은 토마토는 큐에 넣어줌
				if (box[r][c] == 1) {
					queue.offer(new Dot(r, c, 0));
				}
			}
		}

		bfs();

	}

	static void bfs() {

		int day = 0;
		while (!queue.isEmpty()) {
			Dot front = queue.poll();
			day = front.day;

			for (int d = 0; d < 4; d++) {
				int nx = front.x + dx[d];
				int ny = front.y + dy[d];

				if (isIn(nx, ny) && box[nx][ny] == 0) {
					queue.offer(new Dot(nx, ny, day+1));
					box[nx][ny] = 1;
				}
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (box[r][c] == 0) {
					// 익을 수 없는 토마토
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(day);
	}

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	static class Dot {
		int x;
		int y;
		int day;

		public Dot(int x, int y, int day) {
			super();
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}
}