package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3109_G2_빵집 {

	static boolean[][] space;
	static int pipe = 0;
	static int R, C;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		space = new boolean[R][C];
		for(int r=0; r<R; r++) {
			String string = br.readLine();
			for(int c=0; c<C; c++) {
				if(string.charAt(c)==('.')) { // . = 빈공간
					continue;
				}else { // x = 건물
					space[r][c] = true;
				}
			}
		}
		
		for(int r=0; r<R; r++) {
			flag = false;
			dfs(r, 0);
			if(flag) pipe++;
		}
		System.out.println(pipe);
		
	}
	
	static void dfs(int r, int c) {
		if(c == C-1) {
			flag = true;
			return;
		}
		
		space[r][c] = true;
		for(int i=0; i<dx.length; i++) {
			int nr = r + dx[i];
			int nc = c + dy[i];
			if(isIn(nr, nc) && !space[nr][nc]) {
				if(!flag) // 가장 위쪽 경로를 하나 찾았으면 그 뒤로는 탐색하지 않음
					dfs(nr, nc);
			}
		}
	}

	static int[] dx = {-1, 0, 1}; //오른쪽위, 오른쪽, 오른쪽 아래
	static int[] dy = {1, 1, 1};
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}
}
