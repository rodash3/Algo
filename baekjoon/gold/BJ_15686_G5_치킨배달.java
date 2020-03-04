package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 3. 4.
 * @see https://www.acmicpc.net/problem/15686 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 15048 KB
 * @time 188 ms
 * @caution 집 위치와 치킨집 위치를 리스트에 모두 담은 후, 이중 남길 치킨집을 조합으로 M개 뽑아 각 경우 치킨거리 계산
 */
public class BJ_15686_G5_치킨배달 {

	static int N, M;
	static int[][] map; // 도시
	static List<Dot> houseList = new ArrayList<>(); // 집 리스트
	static List<Dot> chickenList = new ArrayList<>(); // 치킨집 리스트
	static int MIN = Integer.MAX_VALUE; // 최소 치킨거리
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시 N*N
		M = Integer.parseInt(st.nextToken()); // 남길 치킨집 개수
		
		// 도시 input
		map = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if(map[r][c] == 1) { // 1이면 집 리스트에 위치 담기
					houseList.add(new Dot(r, c));
				}else if(map[r][c] == 2) { // 2면 치킨집 리스트에 위치 담기
					chickenList.add(new Dot(r, c));
				}
			}
		}
		
		// 남길 치킨집 M개를 조합으로 뽑아서 각각 그 때의 치킨 거리 구하기 
		combination(0, M, new int[M], 0);
		System.out.println(MIN);
	}
	
	// M개 치킨집을 남겼을 때 도시의 치킨 거리 구하기 - temp: 남긴 치킨집 리스트의 인덱스
	public static void calChickenDistance(int[] temp) {
		int sum = 0; // 도시의 치킨거리
		
		// 각 집에서의 치킨 거리 구하기
		for(Dot h: houseList) {
			int cd = Integer.MAX_VALUE;
			// M개의 치킨집과 거리 계산 후 가장 작은 거리가 그 집의 치킨 거리
			for(int i: temp) {
				cd = Math.min(Math.abs(chickenList.get(i).x - h.x) + Math.abs(chickenList.get(i).y - h.y), cd);
			}
			sum += cd; // 도시 치킨거리에 더함
		}
		
		MIN = Math.min(sum, MIN);
	}
	
	// 폐업시키지 않고 남길 치킨집 M개 조합 뽑기
	public static void combination(int k, int r, int[] temp, int start) {
		if(k==r) {
			calChickenDistance(temp);
		}else {
			for(int i=start; i<chickenList.size(); i++) {
				temp[k] = i;
				combination(k+1, r, temp, i+1);
			}
		}
	}

	// 위치 정보
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
