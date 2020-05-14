package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 5. 14.
 * @see https://www.acmicpc.net/problem/2468
 * @memory 54144 KB
 * @time 292 ms
 * @caution BFS
 */
public class BJ_2468_S1_안전영역 {

	static int N;
	static boolean[][] visited; //방문여부
	static int[][] map; // NxN
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //맵 크기
		
		map = new int[N][N];
		int MIN = 101, MAX = 0; //맵 높이의 최솟값과 최댓값
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()); //맵 입력
				if(map[r][c] > MAX) {
					MAX = map[r][c]; //최댓값 저장
				}
				if(map[r][c] < MIN) {
					MIN = map[r][c]; //최솟값 저장
				}
			}
		}//input for over
		
		int answer = 0; //안전 영역의 최대 개수
		for(int h=MIN; h<MAX; h++) { //h이하 높이는 잠긴다
			visited = new boolean[N][N];
			int cnt = 0; //h이하가 잠길 때 안전 영역 수
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(!visited[r][c] && map[r][c] > h) {
						bfs(r, c, h);
						cnt++;
					}
				}
			}//for r,c over
			answer = Math.max(answer, cnt);
		}
		
		System.out.println(answer==0? 1:answer);
	}

	private static void bfs(int r, int c, int h) {
		Queue<Dot> queue = new LinkedList<>();
		queue.offer(new Dot(r, c));
		visited[r][c] = true;
		
		while (!queue.isEmpty()) {
			Dot front = queue.poll();
			
			// 사방 탐색
			for(int d=0; d<4; d++) {
				int nx = dx[d] + front.x;
				int ny = dy[d] + front.y;
				
				if(isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] > h) {
					// 방문하지 않고 높이가 h보다 높을 경우 큐에 담기
					queue.offer(new Dot(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}//while over
	}

	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static boolean isIn(int x, int y) {
		return x>=0 && x<N && y<N && y>=0;
	}
	
	static class Dot {
		int x, y;

		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
