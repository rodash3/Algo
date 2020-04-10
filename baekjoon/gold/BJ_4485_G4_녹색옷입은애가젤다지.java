package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 4. 10.
 * @see https://www.acmicpc.net/problem/4485
 * @memory 22156 KB
 * @time 204 ms
 * @caution
 */
public class BJ_4485_G4_녹색옷입은애가젤다지 {

	static int N;
	static int MAX = 20000;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int tc=1; ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			if(N == 0) System.exit(0);
			
			int[][] map = new int[N][N];
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			PriorityQueue<Dot> pq = new PriorityQueue<>();
			boolean[][] check = new boolean[N][N];
			int[][] D = new int[N][N];
			
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					D[r][c] = MAX;
				}
			}
			D[0][0] = map[0][0];
			pq.add(new Dot(0, 0, D[0][0]));
			
			while (!pq.isEmpty()) {
				Dot dot = pq.poll();

				for(int d=0; d<4; d++) {
					int nx = dx[d] + dot.x;
					int ny = dy[d] + dot.y;
					
					if(isIn(nx, ny) && !check[nx][ny] && D[nx][ny] > D[dot.x][dot.y] + map[nx][ny]) {
						D[nx][ny] = D[dot.x][dot.y] + map[nx][ny];
						pq.add(new Dot(nx, ny, D[nx][ny]));
					}
				}
				check[dot.x][dot.y] = true;
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("Problem ").append(tc).append(": ").append(D[N-1][N-1]);
			System.out.println(sb);
		}
	}
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}

	static class Dot implements Comparable<Dot>{
		int x;
		int y;
		int d;
		
		public Dot(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public int compareTo(Dot o) {
			return Integer.compare(this.d, o.d);
		}
	}
}
