package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//색종이를 1로 표시하고 1 주위에 있는 0 개수를 세면 둘레 길이가 된다
//모서리는 세면 안된다!
public class BJ_2567_S5_색종이2 {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
		int paper_num = Integer.parseInt(stringTokenizer.nextToken()); // 색종이 개수

		int[][] paper = new int[102][102];

		for (int i = 0; i < paper_num; i++) {
			stringTokenizer = new StringTokenizer(bReader.readLine());

			int left = Integer.parseInt(stringTokenizer.nextToken())+1;
			int down = Integer.parseInt(stringTokenizer.nextToken())+1;

			// 색종이 면적에 1 쓰기
			for (int j = left; j < 10 + left; j++) {
				for (int m = down; m < 10 + down; m++) {
					paper[j][m] = 1;
				}
			}
		}

		int[] dx = { 0, 0, 1, -1 };
		int[] dy = { 1, -1, 0, 0 };
		
		// 1 주위에 있는 0 개수 = 둘레 길이 구하기
		int answer = 0;
		for (int j = 0; j < 102; j++) {
			for (int k = 0; k < 102; k++) {
				
				if(paper[j][k] == 1) {
					for(int d = 0; d<4; d++) {
						int nx = j + dx[d];
						int ny = k + dy[d];
						
						if(nx >= 0 && nx < 102 && ny >= 0 && ny < 102 && paper[nx][ny] == 0) {
							answer++;
						}
					}
				}
				
			}
		}
		System.out.println(answer);
	}

}
