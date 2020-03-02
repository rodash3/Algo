package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 3. 3.
 * @see https://www.acmicpc.net/problem/14499 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 14080 KB
 * @time 116 ms
 * @caution 주사위를 굴릴 때마다 전개도를 갱신시킨다 
 */
public class BJ_14499_G5_주사위굴리기 {

	static int N, M, K;
	static int x, y; // 주사위 위치 좌표
	static int[][] map; // 지도
	static int[] dice = new int[4]; // 주사위 전개도 (남-북 방향)
	static int[] diceSide = new int[3]; // 주사위 전개도(서-위-동 면)
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 지도 N*M
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken()); // 주사위 놓은 곳 (x,y)
		y = Integer.parseInt(st.nextToken());
		//K = Integer.parseInt(st.nextToken()); // 명령 개수 - 불필요

		// 지도 input
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 명령 input - 동쪽: 1, 서쪽: 2, 북쪽: 3, 남쪽: 4
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			roll(Integer.parseInt(st.nextToken()));
		}
	}

	public static void roll(int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		// 바깥으로 이동시 명령 무시
		if (!isIn(nx, ny))
			return;
		
		// 이동 칸으로 주사위 좌표 이동
		x = nx;
		y = ny;
		
		// 주사위 전개도 갱신
		int temp;
		switch (dir) {
		case 1: // 동
			temp = diceSide[2];
			diceSide[2] = diceSide[1];
			diceSide[1] = diceSide[0];
			diceSide[0] = dice[3];
			
			dice[3] = temp;
			dice[1] = diceSide[1];
			break;
		case 2: // 서
			temp = diceSide[0];
			diceSide[0] = diceSide[1];
			diceSide[1] = diceSide[2];
			diceSide[2] = dice[3];
			
			dice[3] = temp;
			dice[1] = diceSide[1];
			break;
		case 3: // 북
			temp = dice[0];
			for(int i=0; i<3; i++) {
				dice[i] = dice[i+1];
			}
			dice[3] = temp;
			diceSide[1] = dice[1];
			break;
		case 4: // 남
			temp = dice[3];
			for(int i=2; i>=0; i--) {
				dice[i+1] = dice[i];
			}
			dice[0] = temp;
			diceSide[1] = dice[1];
			break;
		default:
			break;
		}

		if(map[x][y] == 0) {
			// 이동한 칸의 수가 0 -> 주사위의 바닥면에 쓰여 있는 수가 칸에 복사 
			map[x][y] = dice[3];
		}else {
			// 이동칸이 0이 아닌 경우 -> 칸의 수가 주사위 바닥면으로 복사, 칸은 0
			dice[3] = map[x][y];
			map[x][y] = 0;
		}
		
		// 주사위 윗면 출력
		System.out.println(dice[1]);
	}

	static int[] dx = { 0, 0, 0, -1, 1 }; // 동 서 북 남
	static int[] dy = { 0, 1, -1, 0, 0 };

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}
}
