package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1012_S1_유기농배추 {

	static int M;
	static int N;
	static boolean[][] visited;
	static int [][] farm;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=TC; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());  // 배추밭 가로길이
			N = Integer.parseInt(st.nextToken());  // 배추밭 세로길이
			farm = new int[M][N];
			visited = new boolean[M][N];
			
			int K = Integer.parseInt(st.nextToken()); // 심어진 배추 개수
			for(int j=0; j<K; j++) {
				st = new StringTokenizer(br.readLine());
				// 배추 위치 저장
				farm[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}
			
			int cnt = 0;
			for(int r=0; r<M; r++) {
				for(int c=0; c<N; c++) {
					if(!visited[r][c] && farm[r][c] == 1) {
						dfs(r,c);
						cnt++;
					}
				}
			}
			
			System.out.println(cnt);
		}
	}

	
	static void dfs(int r, int c) {
		if(visited[r][c])
			return;
		
		visited[r][c] = true;
		for(int i=0; i<dx.length; i++) {
			int nr = r + dx[i];
			int nc = c + dy[i];
			if(isIn(nr, nc) && !visited[nr][nc] && farm[nr][nc] == 1) {
				dfs(nr, nc);
			}
		}
	}
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};

	static boolean isIn(int r, int c) {
		return r>=0 && r<M && c>=0 && c<N;
	}
}
