package d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 3. 13.
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do
 * @memory 26420 KB
 * @time 122 ms
 * @caution 악마(*)는 1개 이상
 */
public class SWEA_7793_D5_오나의여신님 {

	static int N, M;
	static char[][] map;
	static int xD, yD; // 여신 위치
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=TC; i++) {
			// input
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			
			for(int r=0; r<N; r++) {
				String str = br.readLine();
				map[r] = str.toCharArray();
			}//input over
			
			System.out.print("#" + i + " "); // 정답 출력
			bfs();
		}//for over
		
	}
	
	// 수연이의 위치는 S, 여신의 공간은 D, 돌의 위치는 X, 악마는 *
	// 악마 손아귀가 확장되면서 수연이 이동
	public static void bfs() {
		Queue<Dot> devil = new LinkedList<>();
		Queue<Dot> sy = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		// 처음 악마 위치와 수연이 위치 큐에 넣기
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(map[r][c] == '*') {
					// 악마 위치
					devil.offer(new Dot(r, c, 0));
				}else if (map[r][c] == 'S') {
					// 수연이 위치
					sy.offer(new Dot(r, c, 0));
					visited[r][c] = true;
				}else if (map[r][c] == 'D') {
					// 여신 위치 좌표
					xD = r;
					yD = c;
				}
			}
		}
		
		int time = 0; // 수연이가 여신에게 가기위한 최소 시간
		while (!sy.isEmpty()) {
			Dot syDot = sy.poll();
			time = syDot.t;
			
			// 여신에게 도달하면 시간 출력후 종료
			if(syDot.x == xD && syDot.y == yD) {
				System.out.println(time);
				return;
			}
			
			// 현재 시점에서 악마 손아귀 확장
			while(!devil.isEmpty() && devil.peek().t == time) {
				Dot devilDot = devil.poll();
				
				for(int d=0; d<4; d++) {
					int nx = devilDot.x + dx[d];
					int ny = devilDot.y + dy[d];
					if(isIn(nx, ny) && map[nx][ny] == '.') {
						devil.offer(new Dot(nx, ny, time + 1));
						map[nx][ny] = '*';
					}
				}
			}

			// 수연이 이동
			for(int d=0; d<4; d++) {
				int nx = syDot.x + dx[d];
				int ny = syDot.y + dy[d];
				if(isIn(nx, ny) && !visited[nx][ny] && (map[nx][ny] == '.' || map[nx][ny] == 'D')) {
					sy.offer(new Dot(nx, ny, time + 1));
					visited[nx][ny] = true;
				}
			}
		}
		
		// 여신에게 도달 불가능
		System.out.println("GAME OVER");
	}

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}
	
	static class Dot {
		int x; // 위치 (x,y)
		int y;
		int t; // 시간

		public Dot(int x, int y, int t) {
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}
}
