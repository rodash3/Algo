package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2567_B1_색종이2 {

	public static void main(String[] args) throws IOException {

		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());		
		int paper_num = Integer.parseInt(stringTokenizer.nextToken()); //색종이 개수
		
		int[][] paper = new int[100][100];
		
		for(int i=0; i<paper_num; i++) {
			stringTokenizer = new StringTokenizer(bReader.readLine());
			
			int left = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int down = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			
			for(int j=left; j<10+left; j++) {
				//둘레만 숫자 +1 증가시키기
				if(j==left || j==9+left) {
					for(int k=down; k<10+down; k++) {
						paper[j][k] += 1;
					}
				}else {
					paper[j][down] += 1;
					paper[j][down+9] += 1;
				}
				
				// 면적 +10
				for(int m=down; m<10+down; m++) {
					paper[j][m] += 10;
				}
			}
		}
		
		int[][] circum = new int[5][5];
		int sum = 0;
		for(int r=0; r<100; r++) {
			for(int c=0; c<100; c++) {
				if(paper[r][c]%10 >=1) {
					circum[paper[r][c]/10][paper[r][c]%10]++;
					sum++;
				}
			}
		}
		for(int m=2; m<5; m++) {
			for(int n=1; n<5; n++) {
				sum -= circum[m][n] * n;
			}
		}
		System.out.println(sum);
	}

}
