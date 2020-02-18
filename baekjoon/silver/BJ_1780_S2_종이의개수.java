package algo_ad.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1780_S2_종이의개수 {

	static int flag;
	static int[][] paper;
	static int[] cnt;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // N*N 종이 (N은 3의 제곱수)
		
		paper = new int[N][N];
		cnt = new int[3]; // -1, 0, 1로 채워진 종이 개수

		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				paper[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		count(0, 0, N);
		for(int n: cnt)
			System.out.println(n);
	}

	
	static void count(int x, int y, int N) {
		flag = paper[x][y];
		for(int r=x; r<x+N; r++) {
			for(int c=y; c<y+N; c++) {
				
				if(flag != paper[r][c]) {
					for(int i=0; i<3; i++) {
						for(int j=0; j<3; j++) {
							count(x+N/3*i, y+N/3*j, N/3);
						}
					}
					return;
				}
				
			}
		}
		cnt[flag+1]++;
	}
}
