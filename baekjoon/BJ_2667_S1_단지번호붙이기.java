package algo_basic.day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BJ_2667_S1_단지번호붙이기 {

	static List<Integer> list = new ArrayList<>();
	static int[][] map;
	static boolean[][] visited;
	static int N;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 지도 크기 N*N
		
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int r=0; r<N; r++) {
			String str = br.readLine();
			for(int c=0; c<N; c++) {
				map[r][c] = str.charAt(c)-'0';
			}
		}
		
//		Queue<dot> queue = new LinkedList<>();
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c] == 1 && !visited[r][c]) {
//					int cnt = 0;
//					visited[r][c] = true;
//					queue.offer(new dot(r, c));
//					while (!queue.isEmpty()) {
//						cnt++;
//						int x = queue.peek().x;
//						int y = queue.peek().y;
//						queue.poll();
//						for(int d=0; d<4; d++) {
//							int nx = x + dx[d];
//							int ny = y + dy[d];
//							if(isIn(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
//								queue.offer(new dot(nx, ny));
//								visited[nx][ny] = true;
//							}
//						}
//					}
//					list.add(cnt);
					list.add(bfs(r, c));
				}
			}
		}
		
		System.out.println(list.size());
		Collections.sort(list);
		for(int n: list)
			System.out.println(n);
	}
	
	public static int bfs(int row, int col) {
		int cnt = 1; // 최초에는 row, col에 있는 아파트 하나
		// 지점 방문 처리
		visited[row][col] = true;
		Queue<dot> queue = new LinkedList<>();
		queue.offer(new dot(row, col));
		
		while (!queue.isEmpty()) {
			dot front = queue.poll();
			// 자식 탐방
			for(int d=0; d<dx.length; d++) {
				int nr = front.x + dx[d];
				int nc = front.y + dy[d];
				if(isIn(nr, nc) && map[nr][nc] == 1 && !visited[nr][nc]) {
					queue.offer(new dot(nr, nc));
					visited[nr][nc] = true;
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
	
	static class dot{
		int x;
		int y;
		
		public dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
