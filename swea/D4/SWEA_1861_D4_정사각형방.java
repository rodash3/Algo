package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1861_D4_정사각형방 {

	static int N;
	static int[][] room;
	static int cnt;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=TC; i++) {
			N = Integer.parseInt(br.readLine()); //N*N개의 방
			room = new int[N][N];
			
			// 방에 숫자 입력
			for(int r=0; r<N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; c++) {
					room[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			List<Integer> list = new ArrayList<>();
			int[][] roomCnt = new int[N][N];
			int maxCnt = 1; // 최대 이동 가능한 방 개수
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					cnt = 1;
					dfs(r, c);
					roomCnt[r][c] = cnt; // 각 방마다 이동할 수 있는 방 개수 입력
					if(maxCnt < cnt) maxCnt = cnt;
				}
			}
			
			// 이동할 수 있는 방의 개수가 최대인 방이 여럿이라면 그 중에서 적힌 수가 가장 작은 것을 출력한다.
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(roomCnt[r][c] == maxCnt) {
						list.add(room[r][c]);
					}
				}
			}
			
			Collections.sort(list);
			System.out.println("#" + i + " " + list.get(0) + " " + maxCnt);
		}

	}
	
	static void dfs(int r, int c) {		
		for(int i=0; i<dx.length; i++) {
			int nr = r + dx[i];
			int nc = c + dy[i];
			if(isIn(nr, nc) && room[r][c] + 1 == room[nr][nc]) {
				dfs(nr, nc);
				cnt++;
			}
		}
	}
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

}
