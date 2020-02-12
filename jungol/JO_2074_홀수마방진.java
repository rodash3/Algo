package jungol;

import java.util.Scanner;

public class JO_2074_홀수마방진 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(); // 정사각형 크기

		int[][] square = new int[n][n];
		square[0][n/2] = 1;   // 1을 넣는 위치는 첫 번째 행 가운데
		
		int x = 0;
		int y = n/2;
		int num = 1;
		while (num < n*n) {
			if(num%n == 0) { // 숫자가 N의 배수이면 바로 아래의 행에 숫자 넣기
				x++;
				square[x][y] = ++num;
			}else {
				x= x-1;
				y= y-1;
				if(x<0) {
					x = n-1;
				}
				if(y<0) {
					y = n-1;
				}
				square[x][y] = ++num;
			}
		}
		
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				System.out.print(square[r][c]+ " ");
			}
			System.out.println();
		}
		scanner.close();
	}

}