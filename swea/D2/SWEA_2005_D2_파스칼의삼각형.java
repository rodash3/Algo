package Solving_Club;

import java.util.Scanner;

public class SWEA_2005_D2_파스칼의삼각형 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int TC = scanner.nextInt();

		for (int i = 1; i <= TC; i++) {

			int N = scanner.nextInt();

			int[][] triangle = new int[N][];

			for (int j = 0; j < N; j++) { // 삼각형 그리기
				triangle[j] = new int[j + 1];

				triangle[j][0] = 1;
				triangle[j][j] = 1;

				for (int k = 1; k < j; k++) {
					triangle[j][k] = triangle[j - 1][k - 1] + triangle[j - 1][k];
				}
			}

			// 출력
			System.out.println("#" + i);
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < triangle[r].length; c++) {
					System.out.print(triangle[r][c] + " ");
				}
				System.out.print("\n");
			}
		}
		scanner.close();
	}

}
