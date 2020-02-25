package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 2. 25.
 * @see https://www.acmicpc.net/problem/17144 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 133636 KB
 * @time 396 ms
 * @caution 바람 방향에 맞게 값 이동시키기
 */
public class BJ_17144_G5_미세먼지안녕 {

	static int R, C, T;
	static int[][] map;
	static Queue<Dot> queue = new LinkedList<>();
	static int uAC, dAC;  // 공기청정기의 위치 (행)
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (c == 0 && map[r][c] == -1) { // 공기청정기 칸
					if(uAC == 0) {
						uAC = r;
						dAC = r+1;
					}
				}
			}
		}
		
		for(int t=0; t<T; t++) {
			
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					if(map[r][c] > 0) {
						queue.offer(new Dot(r, c, map[r][c]));
					}
				}
			}
			spread();
			System.out.println("퍼짐");
			for(int[] m: map) {
				System.out.println(Arrays.toString(m));
			}
			System.out.println();
			upCirculate(uAC);
			downCirculate(dAC);
			for(int[] m: map) {
				System.out.println(Arrays.toString(m));
			}
			System.out.println();
		}//for over

		int sum = 0;
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				sum += map[r][c];
			}
		}
		System.out.println(sum+2);
	}
	
	private static void upCirculate(int x) {
		// 바람의 방향의 마지막 부터 거꾸로 먼지 옮김
		// 0열 ↓
		for(int r = x-2; r>=0; r--) {
			map[r+1][0] = map[r][0];
		}
		// 0행 ←
		for(int c=0; c<C-1; c++) {
			map[0][c] = map[0][c+1];
		}
		// C-1열 ↑
		for(int r=1; r<=x; r++) {
			map[r-1][C-1] = map[r][C-1];
		}
		// x행 →
		for(int c=C-2; c>0; c--) {
			map[x][c+1] = map[x][c];
		}
		map[x][1] = 0; // 바람에 의한 0
	}

	private static void downCirculate(int x) {
		// 바람의 방향의 마지막 부터 거꾸로 먼지 옮김
		// 0열 ↑
		for(int r = x+2; r<R; r++) {
			map[r-1][0] = map[r][0];
		}
		// R-1행 ←
		for(int c=1; c<C; c++) {
			map[R-1][c-1] = map[R-1][c];
		}
		// C-1열 ↓
		for(int r=R-1; r>x; r--) {
			map[r][C-1] = map[r-1][C-1];
		}
		// x행 →
		for(int c=C-2; c>0; c--) {
			map[x][c+1] = map[x][c];
		}
		map[x][1] = 0; // 바람에 의한 0
	}

	private static void spread() {
		
		while (!queue.isEmpty()) {
			Dot front = queue.poll();
			int dust = front.dust/5;
			
			for(int d=0; d<4; d++) {
				int nx = front.x + dx[d];
				int ny = front.y + dy[d];
				
				if(isIn(nx, ny) && map[nx][ny] != -1) {
					map[nx][ny] += dust;
					map[front.x][front.y] -= dust;
				}
			}
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
		int dust;

		public Dot(int x, int y, int dust) {
			super();
			this.x = x;
			this.y = y;
			this.dust = dust;
		}

		@Override
		public String toString() {
			return "Dot [x=" + x + ", y=" + y + "]";
		}
	}
}
