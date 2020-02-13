package Solving_Club;

import java.util.Scanner;

public class SWEA_1209_D3_Sum {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			int answer = 0;
			scanner.next(); // 테스트케이스 번호 버림

			int sum;
			int[][] arr = new int[100][100];
			for (int r = 0; r < 100; r++) {
				sum = 0;
				for (int c = 0; c < 100; c++) {
					arr[r][c] = scanner.nextInt(); // 데이터 넣기
					sum += arr[r][c];
				}
				if (answer < sum)
					answer = sum; // 행의 합
			}

			// 열의 합
			for (int c = 0; c < 100; c++) {
				sum = 0;
				for (int r = 0; r < 100; r++) {
					sum += arr[r][c];
				}
				if (answer < sum)
					answer = sum;
			}

			// 대각선 합
			sum = 0;
			for (int c = 0, r = 0; c < 100; c++, r++) {
				sum += arr[r][c];
			}
			if (answer < sum)
				answer = sum;
			
			sum = 0;
			for (int c = 0, r = 100-1; c < 100; c++, r--) {
				sum += arr[r][c];
			}
			if (answer < sum)
				answer = sum;

			System.out.println("#" + tc + " " + answer);
		}
	}

}
