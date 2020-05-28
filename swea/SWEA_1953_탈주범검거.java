import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 5. 29.
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq&categoryId=AV5PpLlKAQ4DFAUq&categoryType=CODE
 * @memory 21,740 KB
 * @time 181 ms
 * @caution
 */
public class SWEA_1953_탈주범검거 {

	static int[][] map;
	static int N,M;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //맵 NxM
			M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken()); //맨홀 RxC
			int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken()); //탈출후 소요 시간
			
			map = new int[N][M];
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}//input
			
			System.out.println("#"+tc+" "+bfs(R, C, L));
		}
	}//main

	private static int bfs(int r, int c, int l) {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.offer(new Point(r, c, 1));
		visited[r][c] = true;
		int cnt = 0; // 범인이 갈 수 있는 칸수
		
		while (!queue.isEmpty()) {
			Point front = queue.poll();
			cnt++;
			
			for(int i=0; i<dx[map[front.x][front.y]].length; i++) {
				int nx = front.x + dx[map[front.x][front.y]][i];
				int ny = front.y + dy[map[front.x][front.y]][i];
				
				// 다음칸으로 이동 가능할 경우 큐에 넣기
				if(isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] > 0 
						&& front.t<l && check(front.x, front.y, nx, ny)) {
					queue.offer(new Point(nx, ny, front.t+1));
					visited[nx][ny] = true; // 방문 처리
				}
			}
		}
		
		return cnt;
	}
	
	// 터널이 이어졌는지 확인: (nx,ny) -> (x,y) 이동할 수 있는지 
	private static boolean check(int x, int y, int nx, int ny) {
		boolean flag = false;
		for (int i = 0; i < dx[map[nx][ny]].length; i++) {
			int r = nx + dx[map[nx][ny]][i];
			int c = ny + dy[map[nx][ny]][i];

			// 다음칸에서 이전칸으로 돌아갈 수 있다면 연결된 것
			if (r == x && c == y) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	// 터널 모양에 따른 갈 수 있는 방향
	static int[][] dx = {
			{},{0,0,1,-1},{1,-1},{0,0},{-1,0},{1,0},{1,0},{-1,0}
	};
	static int[][] dy = {
			{},{1,-1,0,0},{0,0},{1,-1},{0,1},{0,1},{0,-1},{0,-1}	
	};
	
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}
	
	static class Point{
		int x, y, t; // (x,y)좌표, 시간
		
		public Point(int x, int y, int t) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}
}
