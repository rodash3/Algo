package jungol;

import java.util.Scanner;

public class JO_1707_달팽이사각형 {

	static int n;
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt(); // 정사각형 크기
		
		int[][] rect = new int[n][n];
		
		int x=0;
		int y=0;
		rect[0][0] = 1;
		int num = 2;
		while (num <= n*n) {  //달팽이 움직임 오 -> 아 -> 왼 -> 위
			while (isIn(x, y+1) && rect[x][y+1] == 0) { // 오른쪽 채우기
				y++;
				rect[x][y] = num++;
			}
			while (isIn(x+1, y) && rect[x+1][y] == 0) {
				x++;
				rect[x][y] = num++;
			}
			while (isIn(x, y-1) && rect[x][y-1] == 0) {
				y--;
				rect[x][y] = num++;
			}
			while (isIn(x-1, y) && rect[x-1][y] == 0) {
				x--;
				rect[x][y] = num++;
			}
		}

		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				System.out.print(rect[r][c]+ " ");
			}
			System.out.println();
		}
		scanner.close();

	}

	static boolean isIn(int r, int c) {
		return r>=0 && r<n && c>=0 && c<n;
	}
}
