package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 5. 29.
 * @see https://www.acmicpc.net/problem/17142
 * @memory 41400 KB
 * @time 224 ms
 * @caution 비활성 바이러스가 활성화되는 데에도 1초가 소요된다.
 * 			0칸을 모두 전파시킬때까지 소요되는 시간을 구해야한다 (즉 2만 남은 경우 전파시켜도 시간에 영향x)
 */
public class BJ_17142_G4_연구소3 {

	static int N, M, MIN=Integer.MAX_VALUE;
	static int[][] map; //연구소 맵
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //연구소 크기 NxN
		M = Integer.parseInt(st.nextToken()); //놓을 수 있는 바이러스 개수
		
		List<Point> list = new ArrayList<>(); //바이러스(2칸)의 위치 리스트
		map = new int[N][N];
		for(int r=0; r<N; r++) {
			st= new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 2) {
					// 바이러스칸이면 리스트에 넣기
					list.add(new Point(r, c, 0));
				}
			}
		}//input
		
		combi(0, new Point[M], 0, list);
		System.out.println(MIN==Integer.MAX_VALUE? -1:MIN);
	}//main

	// 바이러스 리스트 중 M개를 초기 활성화 바이러스로 뽑기 - 조합
	private static void combi(int k, Point[] points, int start, List<Point> list) {
		if(k==M) {
			bfs(points);
		}else {
			for(int i=start; i<list.size(); i++) {
				points[k] = list.get(i);
				combi(k+1, points, i+1, list);
			}
		}
	}//combi

	private static void bfs(Point[] virus) {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		// 초기 활성 바이러스 큐에 넣기
		for(Point v: virus) {
			queue.offer(v);
			visited[v.x][v.y] = true;
		}
		int time = 0; // 소요 시간
		
		while (!queue.isEmpty()) {
			Point front = queue.poll();
			// 0칸을 전파시켰을 때만 소요시간 카운트 (2칸은 시간 영향x)
			if(map[front.x][front.y] == 0)
				time = front.t;
			
			// 바이러스 인접칸 사방 탐색
			for(int d=0; d<4; d++) {
				int nx = front.x + dx[d];
				int ny = front.y + dy[d];
				
				// 방문하지 않은 칸이며 벽이 아니면 큐에 넣기
				if(isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] != 1) {
					queue.offer(new Point(nx, ny, front.t+1));
					visited[nx][ny] = true;
				}
			}
		}//while
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				//0칸에 방문하지 않았다면 0칸을 모두 전파시키지 못한 것
				if(!visited[r][c] && map[r][c] == 0) return;
			}
		}
		MIN = Math.min(time, MIN); //0칸을 전파시키는데 걸린 최소 시간 갱신
	}//bfs

	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
	
	static class Point{
		int x,y,t; //(x,y)좌표, 시간 t

		public Point(int x, int y, int t) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}
}
