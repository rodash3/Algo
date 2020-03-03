package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 3. 3.
 * @see https://www.acmicpc.net/problem/15683 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 74852 KB
 * @time 520 ms
 * @caution cctv가 가질 수 있는 모든 방향의 경우를 뽑은 후 그 방향일 때 사각지대가 어떻게 되는지 계산
 * 			메모리 사용량, 시간이 많이 걸림
 */
public class BJ_15683_G5_감시 {

	static int N, M;
	static int[][] map; // 사무실
	static int[][] tempMap;
	static List<cctv> list = new ArrayList<>(); // cctv 리스트
	static int[] dirs = { 0, 4, 2, 4, 4, 1 }; // cctv 1~5번이 가질 수 있는 방향 개수
	static int MIN = Integer.MAX_VALUE; // 최소 사각지대 크기
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사무실 크기 N*M
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		tempMap = new int[N][M];
		// 사무실 input
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				// cctv 정보 리스트에 담기
				if (map[r][c] > 0 && map[r][c] < 6) {
					list.add(new cctv(r, c, map[r][c]));
				}
			}
		}
		
		dfs(0);
		System.out.println(MIN);
	}
	
	// 현재 cctv 방향에서 감시 가능한 영역을 구하여 사각지대 계산
	public static void calDeadZone(List<cctv> list) {
		// 사무실 복사
		for(int i=0; i<N; i++) {
			tempMap[i] = map[i].clone();
		}
		
		// 감시 영역 계산
		for(cctv c: list) { // 리스트의 cctv를 하나씩 꺼내서
			switch (c.type) { // cctv 종류에 따라 tempMap에 감시영역 -1로 표시
			case 1:
				watch(c.x, c.y, c.dir);
				break;
			case 2:
				watch(c.x, c.y, c.dir);
				watch(c.x, c.y, c.dir+2 > 4? c.dir-2 : c.dir+2);
				break;
			case 3:
				watch(c.x, c.y, c.dir);
				watch(c.x, c.y, c.dir+1 > 4? 1 : c.dir+1);
				break;
			case 4:
				watch(c.x, c.y, c.dir);
				watch(c.x, c.y, c.dir+1 > 4? 1 : c.dir+1);
				watch(c.x, c.y, c.dir-1 <= 0? 4 : c.dir-1);
				break;
			case 5:
				watch(c.x, c.y, c.dir);
				watch(c.x, c.y, c.dir+1);
				watch(c.x, c.y, c.dir+2);
				watch(c.x, c.y, c.dir+3);
				break;
			default:
				break;
			}
		}

		// 사각지대 크기 계산
		int cnt = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(tempMap[r][c] == 0) {
					cnt++;
				}
			}
		}
		MIN = Math.min(cnt, MIN);
	}
	
	// 방향 감시
	public static void watch(int x, int y, int dir) {
		while(true) {
			switch (dir) {
			case 1: //↑
				x--;
				break;
			case 2: //→
				y++;
				break;
			case 3: //↓
				x++;
				break;
			case 4: //←
				y--;
				break;
			default:
				break;
			}
			
			// 감시 방향 칸이 범위내에 있으면서 벽이 아닐 경우
			if(x>=0 && y>=0 && x<N && y<M && tempMap[x][y] !=6) {
				if(tempMap[x][y] == 0) {// 빈칸인 경우 -1 로 감시영역 표시
					tempMap[x][y] = -1;
				}
			}else {
				break;
			}
		}
	}

	// cctv의 모든 가능한 회전 방향 뽑기
	public static void dfs(int index) {
		if (index == list.size()) {
			calDeadZone(list);
			return;
		}

		for (int j = 1; j <= dirs[list.get(index).type]; j++) {
			list.get(index).dir = j;
			dfs(index + 1);
		}
	}
	
	static class cctv {
		int x; // 위치 (x, y)
		int y;
		int type; // 종류 1~5
		int dir; // 현재 cctv가 보는 방향

		public cctv(int x, int y, int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}
}
