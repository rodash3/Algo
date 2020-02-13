package Solving_Club;

import java.util.Scanner;

public class SWEA_1954_D2_달팽이숫자 {

	public static void main(String[] args) {
		
		StringBuilder sb = new StringBuilder();

		Scanner scanner = new Scanner(System.in);
		int TC = scanner.nextInt(); // 테스트 케이스
		int[][] snail = null;
		for(int i=0; i<TC; i++) {
			int N = scanner.nextInt();
			snail = new int[N][N];
			snail[0][0] = 1;

			int d = 0; // 방향 바꾸는 변수
			int nr = 0; // 다음 행
			int nc = 0; // 다음 열
			for(int k=2; k<=N*N; k++) {
				while (true) {
					// 배열에 벗어나지 않고, 배열안 숫자가 0일때만 숫자 입력
					if(isIn(nr+dirs[d][0], nc+dirs[d][1], snail) && snail[nr+dirs[d][0]][nc+dirs[d][1]] == 0) {
						nr = nr+dirs[d][0];
						nc = nc+dirs[d][1];
						snail[nr][nc] = k;
						break;
					}
					// 방향 바꿈
					else {
						d = (d+1)%4;
					}
				}
			}

			sb.append("#"+(i+1)+"\n");
			for(int row=0; row<N; row++) {
				for(int col=0; col<N; col++) {
					sb.append(snail[row][col]+" ");
				}
				sb.append("\n");
			}

		}
		System.out.print(sb);
		scanner.close();
	}
	
	static int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; //우하좌상 달팽이 움직임
	
	private static boolean isIn(int r, int c, int[][] nums) {
		return 0 <= r && 0 <= c && r < nums.length && c < nums[0].length;
	}

}
