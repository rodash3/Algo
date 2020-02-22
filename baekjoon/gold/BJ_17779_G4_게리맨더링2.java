package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 2. 22.
 * @see https://www.acmicpc.net/problem/17779 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 27140 KB
 * @time 268 ms
 * @caution 구역간의 범위 - 인덱스 체크 주의
 */
public class BJ_17779_G4_게리맨더링2 {

	static int N;
	static int[][] map;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 재현시 크기 N*N

		map = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 1; r < N; r++) {
			for (int c = 1; c < N; c++) {
				// 기준점이 (r, c)일 때 가능한 d1, d2 구하기
				permutation(0, new int[2], r, c);
			}
		}

		System.out.println(MIN);
	}

	// 인구수 계산
	static void calPopulation(int x, int y, int d1, int d2) {
		int[] pops = new int[5];
		boolean[][] visited = new boolean[N + 1][N + 1];

		// 5구역 인구수 구하고 visited 처리
		int left = y, right = y;
		int lcnt = 1, rcnt = 1;
		int r = x;
		while (true) {
			for (int i = left; i <= right; i++) {
				visited[r][i] = true;
				pops[4] += map[r][i];
			}
			r++;
			if(r>x+d1+d2) break;
			
			if(lcnt <= d1) left--;
			else left++;
			if(rcnt <= d2) right++;
			else right--;
			rcnt++; lcnt++;
		}
		
		// 1 구역: 1 ≤ r < x+d1, 1 ≤ c ≤ y
		// 2 구역: 1 ≤ r ≤ x+d2, y < c ≤ N
		// 3 구역: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
		// 4 구역: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
		for (r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if(!visited[r][c]) {
					if(r>=1 && r<x+d1 && c>=1 && c <=y) {
						pops[0] += map[r][c];
					}else if (r>=1 && r<=x+d2 && c>y && c <=N) {
						pops[1] += map[r][c];
					}else if (r>=x+d1 && r<=N && c>=1 && c <y-d1+d2) {
						pops[2] += map[r][c];
					}else {
						pops[3] += map[r][c];
					}
					
				}
			}
		}
		
		Arrays.sort(pops);
		MIN = Math.min(pops[4]-pops[0], MIN);
	}

	// d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N
	// 중복 순열을 통해 d1, d2 결정 -> d[0] = d1, d[1] = d2
	static void permutation(int k, int[] d, int x, int y) {
		if (k == 2) {
			if (d[0] + d[1] + x <= N && y - d[0] >= 1 && y + d[1] <= N)
				calPopulation(x, y, d[0], d[1]);
			return;
		} else {
			for (int i = 1; i <= N - 2; i++) {
				d[k] = i;
				permutation(k + 1, d, x, y);
			}
		}
	}
}
