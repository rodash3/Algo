package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 3. 4.
 * @see https://www.acmicpc.net/problem/16236 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 14304 KB
 * @time 92 ms
 * @caution 멀리 있는 물고기는 계산할 필요 없음.
 * 			아기 상어의 위치가 바뀔 때 마다 bfs로 아기 상어 위치에서 가장 가까운 거리에 있는 물고기들을 
 * 			pq에 담은 후 (문제 조건에 맞게 정렬) pq의 front만 먹는 작업 계속
 */
public class BJ_16236_G5_아기상어 {

	private static int N;
	private static int[][] map; //N*N 공간
	private static int x, y, size, eat; // 아기상어의 위치(x,y), 크기, 먹은 물고기 수
	private static PriorityQueue<Dot> pq = new PriorityQueue<>(); // 먹을 수 있는 물고기 위치
	private static int time = 0; // 시간 초

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		// map input
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				// 아기 상어 정보
				if (map[r][c] == 9) {
					x = r;
					y = c;
					size = 2; // 처음 크기 2
					eat = 0;
					map[r][c] = 0; // 처음 자기 자신 위치 값 0으로 (지나갈 수 있음)
				}
			}
		} // input over

		eatAllFish();
	}

	public static void eatAllFish() {
		// 먹을 수 있는 가까운 물고기 pq에 담기
		getNearFish();
		
		while (!pq.isEmpty()) {
			Dot fish = pq.poll();

			x = fish.x; // 아기상어 위치 이동
			y = fish.y;
			time += fish.d; // 시간 이동 거리 만큼 증가
			eat++; // 먹은 물고기 수 증가
			map[x][y] = 0;
			
			// 크기만큼 물고기 먹으면 크기 증가
			if (eat == size) {
				size++;
				eat = 0;
			}
			// 새로운 자리에서 먹을 수 있는 물고기 pq 갱신
			getNearFish();
		}

		System.out.println(time);
	}

	// 현재 위치 근처의 물고기 pq에 넣기
	public static void getNearFish() {
		pq.clear();
		
		Queue<Dot> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		// 현재 위치 큐에 넣기
		queue.offer(new Dot(x, y, 0));
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			Dot front = queue.poll();

            // 큐의 물고기가 pq에 담긴 것보다 거리가 멀먼 중단
			if(!pq.isEmpty() && pq.peek().d < front.d) break;
			// 물고기이면 pq에 담기
			if(map[front.x][front.y] < size && map[front.x][front.y] > 0)
				pq.offer(new Dot(front.x, front.y, front.d));

			// 상하좌우 탐색
			for (int d = 0; d < 4; d++) {
				int nx = front.x + dx[d];
				int ny = front.y + dy[d];
				
				// 이동가능한 칸(0, size까지) 큐에 담기
				if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny] && map[nx][ny] <= size) {
					queue.offer(new Dot(nx, ny, front.d + 1));
					visited[nx][ny] = true;
				}
			}
		}
	}

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	// 물고기 클래스
	static class Dot implements Comparable<Dot> {
		Integer x; // 위치 (x,y)
		Integer y;
		Integer d; // 아기 상어와 거리

		public Dot(Integer x, Integer y, Integer d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public int compareTo(Dot o) {
			if (this.d.equals(o.d)) {
				if (this.x.equals(o.x)) {
					// x가 같다면 왼쪽에 있는 물고기가 우선
					return this.y.compareTo(o.y);
				} else {
					// 거리가 같다면 위쪽에 있는 물고기가 우선
					return this.x.compareTo(o.x);
				}
			} else {
				// 거리가 가까운 물고기가 우선
				return this.d.compareTo(o.d);
			}
		}
	}
}
