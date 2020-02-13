package Solving_Club;

import java.util.Scanner;

public class SWEA_1210_D4_Ladder1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int[][] ladder = new int[100][100];
		for (int i = 0; i < 10; i++) {
			sb.append("#").append(i + 1).append(" ");
			scanner.nextInt(); // 테스트 케이스 숫자 버리기

			// 100x100 읽기
			for (int k = 0; k < 100; k++) {
				for (int m = 0; m < 100; m++) {
					ladder[k][m] = scanner.nextInt();
				}
			}

			// 출구(2) 찾기
			int[] exit = { 99, 0 };
			for (int k = 0; k < 100; k++) {
				if (ladder[99][k] == 2)
					exit[1] = k;
			}

			// 출구부터 위로 올라가면서 X걸리는 출발점 찾기
			int[] pre_exit = { exit[0], exit[1] };
			while (exit[0] > 0) {
				for (int m = 0; m < dirs.length; m++) {
					int nr = exit[0] + dirs[m][0];
					int nc = exit[1] + dirs[m][1];
					if (pre_exit[0] == nr && pre_exit[1] == nc)
						continue;
					if (isIn(nr, nc) && ladder[nr][nc] == 1) {
						pre_exit[0] = exit[0];
						pre_exit[1] = exit[1];
						exit[0] = nr;
						exit[1] = nc;
						break;
					}
				}
			}
			sb.append(exit[1]).append("\n");
		}

		System.out.println(sb);
		scanner.close();
	}

	public static int[][] dirs = { { 0, 1 }, { 0, -1 }, { -1, 0 } }; // 오, 좌, 위

	private static boolean isIn(int r, int c) {
		return 0 <= r && 0 <= c && r < 100 && c < 100;
	}

}
