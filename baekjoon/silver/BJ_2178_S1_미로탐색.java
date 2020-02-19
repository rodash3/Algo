package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2178_S1_미로탐색 {

	static int N;
	static int M;
	static boolean[][] maze;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N*M 미로
		M = Integer.parseInt(st.nextToken());

		maze = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			String string = br.readLine();
			for (int c = 0; c < M; c++) {
				if (string.charAt(c) == '1') {
					maze[r][c] = true;
				}
			}
		}

		bfs();
	}

	static void bfs() {
		Queue<Dot> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.offer(new Dot(0, 0, 1)); // 시작점
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			Dot front = queue.poll();
			int distance = front.distance;

			for (int d = 0; d < 4; d++) {
				int nx = front.x + dx[d];
				int ny = front.y + dy[d];

				if (isIn(nx, ny) && !visited[nx][ny] && maze[nx][ny]) {
					if (nx == N - 1 && ny == M - 1) {
						System.out.println(distance + 1);
						return;
					}
					queue.offer(new Dot(nx, ny, distance + 1));
					visited[nx][ny] = true;
				}
			}
		}
	}

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	static class Dot {
		int x;
		int y;
		int distance;

		public Dot(int x, int y, int distance) {
			super();
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
}
