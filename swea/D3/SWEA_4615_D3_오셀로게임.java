package D3;

import java.util.Scanner;

public class SWEA_4615_D3_오셀로게임 {

	public static void main(String[] args) {

		String src = "1\r\n" + "4 12\r\n" + "1 2 1\r\n" + "1 1 2\r\n" + "4 3 1\r\n" + "4 4 2\r\n" + "2 1 1\r\n"
				+ "4 2 2\r\n" + "3 4 1\r\n" + "1 3 2\r\n" + "2 4 1\r\n" + "1 4 2\r\n" + "4 1 2\r\n" + "3 1 2";
		Scanner scanner = new Scanner(System.in);
		scanner = new Scanner(src);

		int TC = scanner.nextInt();

		for (int i = 1; i <= TC; i++) {
			int N = scanner.nextInt(); // 보드 한변의 길이
			int M = scanner.nextInt(); // 돌 놓는 횟수

			char[][] board = new char[N][N];

			// 4개 돌 놓고 시작
			board[N / 2][N / 2] = 'W';
			board[N / 2 - 1][N / 2 - 1] = 'W';
			board[N / 2 - 1][N / 2] = 'B';
			board[N / 2][N / 2 - 1] = 'B';

			// M번 돌 놓기
			for (int j = 0; j < M; j++) {
				int row = scanner.nextInt() - 1;
				int col = scanner.nextInt() - 1;
				int color = scanner.nextInt(); // 1=흑돌, 2=백돌

				if (color == 1) { //흑돌 놓기
					board[row][col] = 'B';
					
					for(int d=0; d<dirs.length; d++) {
						// 팔방 탐색
						int nr = dirs[d][0] + row;
						int nc = dirs[d][1] + col;
						
						while (nr>=0 && nc>=0 && nr<N && nc<N) {
							
							if(board[nr][nc]=='W') { // W 만나면 방향 그대로 계속 탐색
								nr += dirs[d][0];
								nc += dirs[d][1];
							}else if(board[nr][nc]=='B'){ // B 만나면 탐색한 만큼 돌 바꾸기
								int r=row;
								int c=col;
								while (r!=nr || c!=nc) {
									r += dirs[d][0];
									c += dirs[d][1];
									board[r][c] = 'B';
								}
								break;
							}else { // 빈공간이면 탈출 -> 다음 방향 탐색
								break;
							}
						}
					}
				}else if(color == 2) {
					board[row][col] = 'W';
					
					for(int d=0; d<dirs.length; d++) {
						
						int nr = dirs[d][0] + row;
						int nc = dirs[d][1] + col;
						
						while (nr>=0 && nc>=0 && nr<N && nc<N) {
							
							if(board[nr][nc]=='B') {
								nr += dirs[d][0];
								nc += dirs[d][1];
							}else if(board[nr][nc]=='W'){
								int r=row;
								int c=col;
								while (r!=nr || c!=nc) {
									r += dirs[d][0];
									c += dirs[d][1];
									board[r][c] = 'W';
								}
								break;
							}else {
								break;
							}
						}
					}
				}
			}

			// 남은 흑/백돌 세기
			int[] nums = new int[2];
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(board[r][c] == 'B')
						nums[0]++;
					else if(board[r][c] == 'W'){
						nums[1]++;
					}
				}
			}
			//출력
			System.out.println("#" + i + " " + nums[0] + " " + nums[1]);
		}
	}

	static int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, { -1, -1 }, { 1, -1 }, { -1, 1 }, { 1, 1 } };
	
}
