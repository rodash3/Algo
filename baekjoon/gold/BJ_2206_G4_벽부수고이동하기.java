package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2206_G4_벽부수고이동하기 {

	static int N, M;
	static int[][] map;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N*M 맵
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			String string = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = string.charAt(j) - '0';
			}
		}
		bfs();
	}

	static void bfs() {
		Queue<Dot> queue = new LinkedList<>();
		queue.offer(new Dot(0, 0, 1, false));
		visited[0][0][0] = true;
		visited[0][0][1] = true;
		int distance;
		
		if(0==N-1 && 0==M-1) {
			System.out.println(1);
			return;
		}

		while (!queue.isEmpty()) {
			Dot front = queue.poll();
			distance = front.d;

			for (int d = 0; d < 4; d++) {
				int nx = front.x + dx[d];
				int ny = front.y + dy[d];

				// 끝점 도착
				if (nx == N - 1 && ny == M - 1) {
					System.out.println(distance + 1);
					return;
				}
				
				// 벽 부순 적이 있다
				if (front.isCrashed) {
					if (isIn(nx, ny) && !visited[nx][ny][1] && map[nx][ny] == 0) {
						queue.offer(new Dot(nx, ny, distance + 1, true));
						visited[nx][ny][1] = true;
					}
				} else { // 벽 부순 적 없다
					if (isIn(nx, ny) && !visited[nx][ny][0] && map[nx][ny] == 0) {
						queue.offer(new Dot(nx, ny, distance + 1, false));
						visited[nx][ny][1] = true;
						visited[nx][ny][0] = true;
					}else if (isIn(nx, ny) && !visited[nx][ny][1] && map[nx][ny] == 1) {
						// 부실 수 있는 상태
						queue.offer(new Dot(nx, ny, distance + 1, true));
						visited[nx][ny][1] = true;
					}

				}

			} // for over
		} // while over
		System.out.println(-1);
	}

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	static class Dot {
		int x;
		int y;
		int d;
		boolean isCrashed;

		public Dot(int x, int y, int d, boolean isCrashed) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.isCrashed = isCrashed;
		}

		@Override
		public String toString() {
			return "Dot [x=" + x + ", y=" + y + ", d=" + d + ", isCrashed=" + isCrashed + "]";
		}
		
	}
	

}