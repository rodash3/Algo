package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 2. 26.
 * @see https://www.acmicpc.net/problem/14890 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 14160 KB
 * @time 96 ms
 * @caution 경사로를 겹치지 않게 놓기 위해서 hasSlope로 경사로가 놓인 곳인지 체크하였다.
 */
public class BJ_14890_G4_경사로 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 행 보기
		int cnt = 2 * N;
		for (int r = 0; r < N; r++) {
			boolean[] hasSlope = new boolean[N];
			for(int c=0; c<N-1; c++) {
				int dif = map[r][c] - map[r][c+1];
				if(dif == 0) { // 높이 같음
					continue;
				}else if (dif == 1) { // 왼(c)이 크다
					if(c+L < N) {
						boolean isFlat = true; // 경사로 놓일 곳이 flat한지 (높이가 전부 같은지)
						for(int y=c+1; y<=c+L; y++) {
							if(map[r][c+1] != map[r][y] || hasSlope[y]) {
								// 높이가 다르거나 이미 경사로가 놓인 곳 -> 경사로 놓을 수 없음, 길이 아님
								isFlat = false;
							}
						}
						if(!isFlat) {
							cnt--;
							break;
						}else {
							// 경사로를 놓을 수 있다, 경사로 놓았다고 true로 체크
							for(int y=c+1; y<=c+L; y++) {
								hasSlope[y] = true;
							}
						}
					}
					else { // map 범위를 벗어남 -> 길이 아님
						cnt--;
						break;
					}
				}else if (dif == -1) { // 오른쪽(c+1)이 크다
					if(c-L+1 >= 0 ) {
						boolean isFlat = true;
						for(int y=c; y>c-L; y--) {
							if(map[r][c] != map[r][y] || hasSlope[y]) {
								// 높이가 다르거나 이미 경사로가 놓인 곳 -> 경사로 놓을 수 없음, 길이 아님
								isFlat = false;
							}
						}
						if(!isFlat) {
							cnt--;
							break;
						}else {
							// 경사로를 놓을 수 있다, 경사로 놓았다고 true로 체크
							for(int y=c; y>c-L; y--) {
								hasSlope[y] = true;
							}
						}
					}
					else { // map 범위를 벗어남 -> 길이 아님
						cnt--;
						break;
					}
				}else { // 차이가 2 이상
					cnt--;
					break;
				}
			}
		}

		// 열 보기
		for (int c = 0; c < N; c++) {
			boolean[] hasSlope = new boolean[N];
			for(int r=0; r<N-1; r++) {
				int dif = map[r][c] - map[r+1][c];
				if(dif == 0) { // 높이 같음
					continue;
				}else if (dif == 1) { // 위(r)가 크다
					if(r+L < N) {
						boolean isFlat = true;
						for(int x=r+1; x<=r+L; x++) {
							if(map[r+1][c] != map[x][c] || hasSlope[x]) {
								isFlat = false;
							}
						}
						if(!isFlat) { // 경사로 놓을 수 없음
							cnt--;
							break;
						}else { // 경사로 놓기
							for(int x=r+1; x<=r+L; x++) {
								hasSlope[x] = true;
							}
						}
					}
					else { // 범위를 벗어남
						cnt--;
						break;
					}
				}else if (dif == -1) { // 아래(r+1)이 크다
					if(r-L+1 >= 0 ) {
						boolean isFlat = true;
						for(int x=r; x>r-L; x--) {
							if(map[r][c] != map[x][c] || hasSlope[x]) {
								isFlat = false;
							}
						}
						if(!isFlat) { // 경사로 놓을 수 없음
							cnt--;
							break;
						}else {
							// 경사로 놓기
							for(int x=r; x>r-L; x--) {
								hasSlope[x] = true;
							}
						}
					}
					else { // 범위를 벗어남
						cnt--;
						break;
					}
				}else { // 차이가 2 이상
					cnt--;
					break;
				}
			}
		}

		System.out.println(cnt);
	}

}
