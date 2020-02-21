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
 * @since 2020. 2. 21.
 * @see https://www.acmicpc.net/problem/14502 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 147512 KB
 * @time 440 ms
 * @caution 벽을 세울 위치 조합을 뽑은 후 map을 계속 초기화하여 bfs 돌려주어야 한다.
 */
public class BJ_14502_G5_연구소 {

	static List<Dot> list0 = new ArrayList<>(); // 초기 빈칸(0)의 위치
	static List<Dot> list2 = new ArrayList<>(); // 초기 바이러스(2)의 위치
	static int[][] map;
	static int N, M;
	static int safeArea; // 안전영역의 크기
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
				
				if(map[r][c] == 0) // 초기 0의 위치 저장
					list0.add(new Dot(r, c));
				else if (map[r][c] == 2) // 초기 2의 위치 저장
					list2.add(new Dot(r, c));
			}
		}
		
		combination(0, 3, new int[3], 0);
		System.out.println(safeArea); // 안전 영역 크기의 최댓값 출력
	}
	
	// 조합에서 얻은 위치에 벽을 세우는 함수 - 초기 map을 복사하여 만든 새로운 맵에 벽을 세운 후 bfs 함수에 넘겨준다
	static void setWall(int[] walls) {
		int[][] nmap = new int[N][M]; // 새로운 벽
		for(int i=0; i<map.length; i++) {
			nmap[i] = map[i].clone();
		}
		for(int i=0; i<3; i++) { // 벽 세우기
			nmap[list0.get(walls[i]).x][list0.get(walls[i]).y] = 1;
		}
		bfs(nmap);
	}
	
	// 바이러스(2)가 사방 인접 영역에 퍼지게 된다. 모두 퍼지고 난 이후 남아있는 0의 개수를 구한다.
	static void bfs(int[][] map) {
		Queue<Dot> queue = new LinkedList<>();
		for(Dot dot: list2) { // 최초 2 위치를 큐에 넣어준다
			queue.offer(dot);
		}
		
		while (!queue.isEmpty()) {
			Dot front = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nx = front.x + dx[d];
				int ny = front.y + dy[d];
				// 사방 탐색을 통해 2의 인접 빈칸(0)인 경우 2로 만들어주고 큐에 넣어준다
				if(isIn(nx, ny) && map[nx][ny] == 0) {
					queue.offer(new Dot(nx, ny));
					map[nx][ny] = 2;
				}
			}
		}

		// 바이러스 퍼진 후 map을 모두 탐색하여 안전 영역 크기 구하기
		int safe = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(map[r][c] == 0)
					safe++;
			}
		}
		// 최댓값을 취한다
		safeArea = Math.max(safe, safeArea);
	}

	// 0의 모든 위치중 벽을 세울 위치 3개를 뽑는 조합
	static void combination(int k, int r, int[] temp, int start) {
		if(k==r) {
			setWall(temp);
			return;
		}else {
			for(int i=start; i<list0.size(); i++) {
				temp[k] = i;
				combination(k+1, r, temp, i+1);
			}
		}
	}
	
	// map 범위 내에 있는지 확인
	static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static class Dot {
		int x;
		int y;
		public Dot(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
