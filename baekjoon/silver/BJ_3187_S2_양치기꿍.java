package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3187_S2_양치기꿍 {

	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static int liveSheep, liveWolf;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			String string = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = string.charAt(c);
			}
		}

		visited = new boolean[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (!visited[r][c] && (map[r][c] == 'v' || map[r][c] == 'k')) {
					bfs(r, c);
				}
			}
		}

		System.out.println(liveSheep + " " + liveWolf);
	}

	static void bfs(int x, int y) {
		Queue<Dot> queue = new LinkedList<>();
		queue.offer(new Dot(x, y));
		visited[x][y] = true;

		int sheep = 0;
		int wolf = 0;
		while (!queue.isEmpty()) {
			Dot front = queue.poll();
			if (map[front.x][front.y] == 'v') {
				wolf++;
			} else if (map[front.x][front.y] == 'k') {
				sheep++;
			}

			for (int d = 0; d < 4; d++) {
				int nx = front.x + dx[d];
				int ny = front.y + dy[d];

				if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] != '#') {
					queue.offer(new Dot(nx, ny));
					visited[nx][ny] = true;
				}
			}
		} // while over

		if (sheep > wolf) {
			liveSheep += sheep;
		} else {
			liveWolf += wolf;
		}

	}

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	static class Dot {
		int x;
		int y;

		public Dot(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
