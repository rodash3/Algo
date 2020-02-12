package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1438_색종이 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 색종이 개수
		int[][] paper = new int[100][100];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			for(int r=l; r<l+10; r++) {
				for(int c=d; c<d+10; c++) {
					paper[r][c] = 1;
				}
			}
		}
		
		int area = 0;
		for(int r=0; r<100; r++) {
			for(int c=0; c<100; c++) {
				if(paper[r][c] == 1) {
					area++;
				}
			}
		}
		System.out.println(area);
	}

}
