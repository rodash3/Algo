import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 3. 14.
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo
 * @memory 100076 KB
 * @time 282 ms
 * @caution 중복순열을 통해 구슬 떨어뜨릴 위치(열)를 뽑는다
 * 	뽑은 위치에 따라 구슬을 떨어뜨려 벽돌을 제거하고 빈공간을 내리는 작업을 N번 반복
 * 	다 떨어뜨린 후 각 경우마다 벽돌의 개수를 세어서 최솟값을 찾는다
 */
public class SWEA_5656_벽돌깨기 {

	static int N, W, H;
	static int[][] map; //H행 W열
	static int[][] tmap;
	static int MIN; // 남은 벽돌 개수
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=TC; i++) {
			//input
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			for(int r=0; r<H; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}//input over
			
			MIN = Integer.MAX_VALUE;
			combination(0, new int[N]);
			System.out.println("#"+i+" "+MIN);
		}
	}
	
	// 구슬 떨어뜨리기
	public static void dropBeads(int[] temp) {
		// 맵 복사
		tmap = new int[H][W];
		for(int r=0; r<H; r++) {
			tmap[r] = map[r].clone();
		}
		
		// N번 구슬 떨어뜨리고 빈공간 내리기
		for(int t: temp) {
			breakBricks(t);
			fallBricks();
		}

		//남은 벽돌 개수 구하기
		int cnt = 0;
		for(int r=0; r<H; r++) {
			for(int c=0; c<W; c++) {
				if(tmap[r][c] > 0) cnt++;
			}
		}
		MIN = Math.min(cnt, MIN);
	}

	// w열에 구슬이 떨어짐, 벽돌 제거
	public static void breakBricks(int w) {
		Queue<Dot> queue = new LinkedList<>();
		
		// 맨 위의 벽돌 큐에 넣기
		for(int r=0; r<H; r++) {
			if(tmap[r][w] != 0) {
				queue.offer(new Dot(r, w, tmap[r][w]));
				tmap[r][w] = 0;
				break;
			}
		}
		
		// bfs
		while(!queue.isEmpty()) {
			Dot front = queue.poll();
			
			// 상하좌우 0으로 만들기
			for(int d=0; d<4; d++) {
				// 벽돌에 적힌 숫자 - 1 칸 만큼 제거
				for(int i=1; i<front.n; i++) {
					int nx = front.x + dx[d]*i;
					int ny = front.y + dy[d]*i;
					
					if(isIn(nx, ny)) {
						if(tmap[nx][ny] > 1)
							queue.offer(new Dot(nx, ny, tmap[nx][ny]));
						
						if(tmap[nx][ny] > 0)
							tmap[nx][ny] = 0;
					}
				}
			}
		}//while over
	}
	
	// 벽돌이 제거되고 난 후 빈공간이 있을 경우 벽돌은 밑으로 떨어짐
	public static void fallBricks() {
		for(int c=0; c<W; c++) {
			int bottom = H-1;
			// 각 열마다 바닥 행부터 탐색
			for(int r=H-1; r>=0; r--) {
				if(bottom == r && tmap[r][c] > 0) {
					bottom--;
				}
				// 내려야함
				if(bottom > r && tmap[r][c] > 0) {
					tmap[bottom][c] = tmap[r][c];
					tmap[r][c] = 0;
					bottom--;
				}
			}
		}

	}
	
	// 중복순열로 구슬 떨어뜨릴 위치 뽑기
	public static void combination(int k, int[] temp) {
		if(k==N) {
			if(MIN == 0) return;
			dropBeads(temp);
		}else {
			for(int i=0; i<W; i++) {
				temp[k] = i;
				combination(k+1, temp);
			}
		}
	}
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<H && y<W;
	}
	
	static class Dot{
		int x;
		int y;
		int n; // 벽돌에 적힌 숫자
		
		public Dot(int x, int y, int n) {
			this.x = x;
			this.y = y;
			this.n = n;
		}
	}
}
