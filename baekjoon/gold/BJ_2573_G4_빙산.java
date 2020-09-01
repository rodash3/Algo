package quest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 9. 1.
 * @see https://www.acmicpc.net/problem/2573
 * @memory 107744 KB
 * @time 500 ms
 * @caution 해당 년에서 녹아서 0이 된 곳(빙산)과 원래 0인 곳(바다) 주의
 */
public class BJ_2573_G4_빙산 {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 0년일때 두덩어리 이상인지 확인
		int cnt = 0; // 빙산 덩어리 개수
		visited = new boolean[N][M];
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(map[r][c] > 0 && !visited[r][c]) {
					countIceBergs(r, c);
					cnt++;
				}
			}
		}

		if(cnt == 1){
			// 0년일때 한덩어리면 빙산 녹이기
			int year = 0; // 시간(년)
			while (cnt <= 1) {
				visited = new boolean[N][M];
				cnt = 0;
				
				for(int r=0; r<N; r++) {
					for(int c=0; c<M; c++) {
						if(map[r][c] > 0 && !visited[r][c]) {
							meltIceBergs(r, c);
							cnt++;
						}
					}
				}
				if(cnt == 0) { // 녹아도 빙산 분리 불가능
					System.out.println(0);
					return;
				}else if (cnt == 1) {
					year++;
				}
			}//while
			System.out.println(year);
			return;
		}else {
			// 0년일때 덩어리 2개 이상 or 0개 이면 0 출력
			System.out.println(0);
		}
	}//main
	
	private static void countIceBergs(int r, int c) {
		Queue<Dot> queue = new LinkedList<>();
		queue.offer(new Dot(r, c));
		visited[r][c] = true;
		
		while (!queue.isEmpty()) {
			Dot front = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nx = dx[d] + front.x;
				int ny = dy[d] + front.y;
				
				if(map[nx][ny] > 0 && !visited[nx][ny]) {
					queue.offer(new Dot(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
	}

	private static void meltIceBergs(int r, int c) {
		Queue<Dot> queue = new LinkedList<>();
		queue.offer(new Dot(r, c));
		visited[r][c] = true;
		
		while (!queue.isEmpty()) {
			Dot front = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nx = dx[d] + front.x;
				int ny = dy[d] + front.y;
				
				if(map[nx][ny] == 0 && !visited[nx][ny]) {
					// 인접면이 0이면 1 녹음 (map=0 이지만 visited=true 인 경우는 빙산, 녹는데 영향X)
					if(map[front.x][front.y] > 0) map[front.x][front.y]--;
				}else if(map[nx][ny] > 0 && !visited[nx][ny]) {						
					queue.offer(new Dot(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}	
	}

	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static class Dot{
		int x, y;

		public Dot(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
