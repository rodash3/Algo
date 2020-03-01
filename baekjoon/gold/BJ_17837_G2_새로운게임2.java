package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 3. 1.
 * @see https://www.acmicpc.net/problem/17837 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 25612 KB
 * @time 208 ms
 * @caution String을 사용해서 판에 올려진 말 번호를 관리했는데 메모리, 시간이 크게 사용되는 듯
 */
public class BJ_17837_G2_새로운게임2 {

	static int N, K;
	static Space[][] map; // 체스판
	static List<Piece> list = new ArrayList<>(); // 말 정보
	static boolean isOver; // 종료 조건 달성했는지 (말이 4개 이상 쌓였는지)
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 체스판 크기 N*N
		K = Integer.parseInt(st.nextToken()); // 말의 개수

		// 체스판 데이터 입력
		map = new Space[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = new Space();
				map[r][c].color = Integer.parseInt(st.nextToken());
			}
		}

		// 말 데이터 입력
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1; // 시작 위치 x좌표 
			int y = Integer.parseInt(st.nextToken()) - 1; // 시작 위치 y좌표
			list.add(new Piece(x, y, Integer.parseInt(st.nextToken())-1)); // x, y, 방향
			map[x][y].piece = String.valueOf(i); // 판에 초기 말 쌓기
		}
		
		int turn = 1;
		while (true) {
			// 턴이 1,000보다 크거나 게임이 종료되지 않는 경우에는 -1 출력
			if(turn>1000) {
				System.out.println(-1);
				break;
			}
			
			// 1번 말부터 K번 말까지 순서대로 이동
			for(int i=0; i<K; i++) {
				movePiece(list.get(i), i);
				if(isOver) {
					System.out.println(turn);
					return;
				}
			}
			
			turn++; // 턴 증가
		}//while over
		
	}
	
	public static void movePiece(Piece p, int num) {
		int nx = p.x + dx[p.d]; // 이동칸 x좌표
		int ny = p.y + dy[p.d]; // 이동칸 y좌표
		
		// 이동할 칸이 판 범위 안에 있을 때
		if(isIn(nx, ny)) {
			// 옮겨야할 말 번호 임시 저장
			int idx = map[p.x][p.y].piece.indexOf(num+"");
			String moveP = map[p.x][p.y].piece.substring(idx);
			
			if(map[nx][ny].color == 0) { // 흰색
				// 이동칸으로 말 이동
				map[nx][ny].piece = map[nx][ny].piece.concat(moveP);
				
				if(map[nx][ny].piece.length() >= 4) {
					isOver = true; // 말이 4개 이상 쌓임
				}

				// 이전 칸에 있던 말 비우기
				map[p.x][p.y].piece = map[p.x][p.y].piece.replace(moveP, "");
				
				// 말의 좌표 이동
				for(int i=0; i<moveP.length(); i++) {
					list.get(moveP.charAt(i) - '0').x = nx;
					list.get(moveP.charAt(i) - '0').y = ny;
				}
				
			}else if (map[nx][ny].color == 1) { // 빨간색		
				// 이동칸으로 말 이동
				map[nx][ny].piece = stackRed(moveP, nx, ny);
				
				if(map[nx][ny].piece.length() >= 4) {
					isOver = true; // 말이 4개 이상 쌓임
				}

				// 이전 칸에 있던 말 비우기
				map[p.x][p.y].piece = map[p.x][p.y].piece.replace(moveP, "");
				
				// 말의 좌표 이동
				for(int i=0; i<moveP.length(); i++) {
					list.get(moveP.charAt(i) - '0').x = nx;
					list.get(moveP.charAt(i) - '0').y = ny;
				}

			}else { // 파란색
				// 방향 전환 ( 0->1, 1->0, 2->3, 3->2)
				if(p.d %2 == 0) {
					p.d++;
				}else {
					p.d--;
				}
				nx = p.x + dx[p.d];
				ny = p.y + dy[p.d];
				// 바뀐 방향으로 이동할 칸이 흰색 or 빨간색이라면 이동
				if(isIn(nx, ny) && map[nx][ny].color != 2) {
					movePiece(p, num);
				}
			}
		}else { // 판을 벗어났을 때
			// 방향 전환 ( 0->1, 1->0, 2->3, 3->2)
			if(p.d %2 == 0) {
				p.d++;
			}else {
				p.d--;
			}
			nx = p.x + dx[p.d];
			ny = p.y + dy[p.d];
			// 바뀐 방향으로 이동할 칸이 흰색 or 빨간색이라면 이동
			if(isIn(nx, ny) && map[nx][ny].color != 2) {
				movePiece(p, num);
			}
		}		
	}
	
	//→, ←, ↑, ↓
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	public static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}

	// 빨간색 칸으로 이동시 역순으로 쌓기
	public static String stackRed(String moveP, int nx, int ny) {
		StringBuilder tempsb = new StringBuilder();
		tempsb.append(moveP).reverse(); // 현재 칸 말을 역순으로
		StringBuilder sb = new StringBuilder();
		sb.append(map[nx][ny].piece).append(tempsb); // 이동칸에 있는 말과 더함
		
		return sb.toString();
	}

	// 체스판의 각 칸 정보
	static class Space {
		int color; // 색깔
		String piece; // 칸에 쌓인 말 번호
		
		public Space() {
			super();
			this.piece = "";
		}
	}
	
	// 말 정보
	static class Piece {
		int x; // 말이 위치한 x좌표
		int y; // 말이 위치한 y좌표
		int d; // 말이 움직이는 방향
		
		public Piece(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
}
