package D3;

import java.util.Scanner;

public class SWEA_2805_D3_농작물수확하기 {

	public static void main(String[] args) {
		String src = "1\r\n" + "5\r\n" + "14054\r\n" + "44250\r\n" + "02032\r\n" + "51204\r\n" + "52212";
		Scanner scanner = new Scanner(System.in);
		scanner = new Scanner(src);

		int TC = scanner.nextInt();

		for (int i = 1; i <= TC; i++) {
			int N = scanner.nextInt(); // N*N 농장
			int[][] farm = new int[N][N];

			int sum = 0;
			for (int j = 0; j < N; j++) {
				String row = scanner.next();
				for (int k = 0; k < row.length(); k++) {
					farm[j][k] = row.charAt(k) - '0';
					sum += farm[j][k];
				}
			}

			int j = N/2;
			for (int up_row = 0, down_row = N - 1; up_row != down_row; up_row++, down_row--) {
				for (int start = 0, end = N - 1; start < j; start++, end--) {
					sum = sum - farm[up_row][start] - farm[down_row][start];
					sum = sum - farm[up_row][end] - farm[down_row][end];
				}
				j--;
			}
			System.out.println("#" + i + " " + sum);
		}

	}
}
