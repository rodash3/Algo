package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 5. 15.
 * @see https://www.acmicpc.net/problem/2636
 * @memory 15184 KB
 * @time 116 ms
 * @caution 내부 구멍은 접근 하면 안됨, X칸을 확장하면서 바깥에서 테두리만 접근
 */
public class BJ_2636_G5_치즈 {

	static int R,C; // map 크기 = RxC
	static int cheeseCnt, meltcheeseCnt; //현재 치즈칸 개수, 녹는 치즈칸 개수
	static int[][] map;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 1) cheeseCnt++; //최초 치즈 칸 개수 세기
			}
		}//input over
		
		int hour = 0;
		while (true) {
			// 치즈가 모두 녹았는지
			if(cheeseCnt - meltcheeseCnt == 0) break;
			else { // 아직 안녹음
				hour++;
				cheeseCnt -= meltcheeseCnt;
			}
			
			meltcheeseCnt = 0;
			bfs();		
		}
		//결과 출력
		System.out.println(hour + "\n" + cheeseCnt);
	}

	private static void bfs() {
		Queue<Dot> queue = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		// (0,0) 부터 바깥 공기만 탐색하면서 인접한 치즈(1)만 녹게 만든다
		queue.offer(new Dot(0, 0));
		visited[0][0] = true;
		
		while (!queue.isEmpty()) {
			Dot front = queue.poll();

			// 사 방탐색
			for(int d=0; d<4; d++) {
				int nx = dx[d] + front.x;
				int ny = dy[d] + front.y;
				
				if(isIn(nx, ny)) {
					if(!visited[nx][ny] && map[nx][ny] == 0) {
						queue.offer(new Dot(nx, ny));
						visited[nx][ny] = true;						
					}else if (map[nx][ny] == 1) { //공기와 인접한 치즈는 녹음
						map[nx][ny] = 0;
						visited[nx][ny] = true;
						meltcheeseCnt++;
					}
				}
				
			}
		}//while over
	}
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isIn(int x, int y) {
		return x>=0 && x<R && y>=0 && y<C;
	}
	
	static class Dot {
		int x, y;

		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}