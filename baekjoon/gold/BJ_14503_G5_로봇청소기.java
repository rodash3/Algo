package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 2. 27.
 * @see https://www.acmicpc.net/problem/14503 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 13260 KB
 * @time 80 ms
 * @caution 문제 이해가 가장 중요하다 - 후진 시에는 회전X, 처음 방향 유지
 */
public class BJ_14503_G5_로봇청소기 {

	static int N, M;
	static int[][] map;
	static boolean[][] cleaned; // 청소한 구역인지

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cleaned = new boolean[N][M];
		st = new StringTokenizer(br.readLine());
		int rx = Integer.parseInt(st.nextToken()); // 로봇 위치 : x 좌표
		int ry = Integer.parseInt(st.nextToken()); // 로봇 위치 : y 좌표
		int rd = Integer.parseInt(st.nextToken()); // 로봇이 바라보는 방향

		// 맵 데이터 입력
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int count = 0; // 청소한 구역의 수
		// loop1 - 1번) 현재 위치를 청소한다
		loop1: while (true) {
			cleaned[rx][ry] = true; // 청소 표시
			count++; // 청소 구역 수 증가

			// loop2 - 2번) 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색
			loop2: while (true) {
				// 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는 후진할 수 있으면 후진, 후진할 수 없으면 작동 중지
				if ((cleaned[rx + 1][ry] || map[rx + 1][ry] == 1) && (cleaned[rx][ry + 1] || map[rx][ry + 1] == 1)
						&& (cleaned[rx - 1][ry] || map[rx - 1][ry] == 1)
						&& (cleaned[rx][ry - 1] || map[rx][ry - 1] == 1)) {
					if (rd == 0) {
						if (map[rx + 1][ry] == 0) {
							rx = rx + 1; // 북 방향 보고 있을 때 아래로 한칸 후진
						} else {
							break loop1; // 작동 중지
						}
					} else if (rd == 1) {
						if (map[rx][ry - 1] == 0) {
							ry = ry - 1; // 동 방향, 왼쪽으로 한칸 후진
						} else {
							break loop1; // 작동 중지
						}
					} else if (rd == 2) {
						if (map[rx - 1][ry] == 0) {
							rx = rx - 1; // 남 방향, 위로 한칸 후진
						} else {
							break loop1; // 작동 중지
						}
					} else {
						if (map[rx][ry + 1] == 0) {
							ry = ry + 1; // 서 방향, 오른쪽으로 한칸 후진
						} else {
							break loop1; // 작동 중지
						}
					}
				} else { // 사방에 청소할 칸이 남아있으면
					if (rd == 0) { // 북
						// 왼쪽 방향(서)에 청소할 칸이 존재, 서 방향으로 회전한 다음 한 칸을 전진하고 다시 1번
						if (map[rx][ry - 1] == 0 && !cleaned[rx][ry - 1]) {
							rd = 3; // 회전
							ry--; // 한칸 전진
							break loop2; // 1번부터 다시 실행
						} else {
							rd = 3; // 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전
						}
					} else if (rd == 1) { // 동
						// 왼쪽 방향(북)에 청소할 칸이 존재, 북 방향으로 회전한 다음 한 칸을 전진하고 다시 1번
						if (map[rx - 1][ry] == 0 && !cleaned[rx - 1][ry]) {
							rd = 0;
							rx--;
							break loop2;
						} else {
							rd = 0; // 청소할 공간이 없음, 왼 방향으로 회전
						}
					} else if (rd == 2) { // 남
						// 왼쪽 방향(동)에 청소할 칸이 존재, 동 방향으로 회전한 다음 한 칸을 전진하고 다시 1번
						if (map[rx][ry + 1] == 0 && !cleaned[rx][ry + 1]) {
							rd = 1;
							ry++;
							break loop2;
						} else {
							rd = 1; // 청소할 공간이 없음, 왼 방향으로 회전
						}
					} else { // 서
						// 왼쪽 방향(남)에 청소할 칸이 존재, 남 방향으로 회전한 다음 한 칸을 전진하고 다시 1번
						if (map[rx + 1][ry] == 0 && !cleaned[rx + 1][ry]) {
							rd = 2;
							rx++;
							break loop2;
						} else {
							rd = 2; // 청소할 공간이 없음, 왼 방향으로 회전
						}
					}
				}

			} // loop2 over
		} // loop1 over

		System.out.println(count);
	}

}
