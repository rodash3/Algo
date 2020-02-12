package Solving_Club;

import java.util.Scanner;

public class SWEA_5356_D3_의석이의세로로말해요 {

	private static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testcase = scanner.nextInt();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < testcase; i++) {
			sb.append("#").append(i + 1).append(" ");
			// 각 테스트케이스마다 5줄 읽고 가장 긴 글자수 찾기
			String[] data = new String[5];
			int max_leng = 0;
			for (int k = 0; k < 5; k++) {
				data[k] = scanner.next();
				if (max_leng < data[k].length())
					max_leng = data[k].length();
			}

			// 세로로 읽기
			for (int k = 0; k < max_leng; k++) {
				for (int m = 0; m < 5; m++) {
					if (data[m].length() > k)
						sb.append(data[m].charAt(k));
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
		scanner.close();
	}
}
