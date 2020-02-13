package Solving_Club;

import java.util.Scanner;

public class SWEA_2001_D2_파리퇴치 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int TC = scanner.nextInt();

		for (int i = 1; i <= TC; i++) {
			int answer = 0;

			int N = scanner.nextInt(); // 파리 N*N 영역
			int[][] fly_area = new int[N][N];
			int M = scanner.nextInt(); // 파리채 M*M

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					fly_area[r][c] = scanner.nextInt();
				}
			}

			int sum;
			for (int j = 0; j < N - M + 1; j++) { // 행 이동
				for (int k = 0; k < N - M + 1; k++) { // 열 이동
					sum = 0;
					for (int m = j; m < M + j; m++) {
						for (int n = k; n < M + k; n++) {
							sum += fly_area[m][n];
						}
					}
					if (sum > answer)
						answer = sum;
				}
			}
			System.out.println("#" + i + " " + answer);
		}
		scanner.close();
	}

}
