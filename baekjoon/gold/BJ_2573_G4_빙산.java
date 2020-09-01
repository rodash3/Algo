package quest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 9. 1.
 * @see https://www.acmicpc.net/problem/2573
 * @memory 107744 KB
 * @time 500 ms
 * @caution �ش� �⿡�� ��Ƽ� 0�� �� ��(����)�� ���� 0�� ��(�ٴ�) ����
 */
public class BJ_2573_G4_���� {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 0���϶� �ε�� �̻����� Ȯ��
		int cnt = 0; // ���� ��� ����
		visited = new boolean[N][M];
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(map[r][c] > 0 && !visited[r][c]) {
					countIceBergs(r, c);
					cnt++;
				}
			}
		}

		if(cnt == 1){
			// 0���϶� �ѵ���� ���� ���̱�
			int year = 0; // �ð�(��)
			while (cnt <= 1) {
				visited = new boolean[N][M];
				cnt = 0;
				
				for(int r=0; r<N; r++) {
					for(int c=0; c<M; c++) {
						if(map[r][c] > 0 && !visited[r][c]) {
							meltIceBergs(r, c);
							cnt++;
						}
					}
				}
				if(cnt == 0) { // ��Ƶ� ���� �и� �Ұ���
					System.out.println(0);
					return;
				}else if (cnt == 1) {
					year++;
				}
			}//while
			System.out.println(year);
			return;
		}else {
			// 0���϶� ��� 2�� �̻� or 0�� �̸� 0 ���
			System.out.println(0);
		}
	}//main
	
	private static void countIceBergs(int r, int c) {
		Queue<Dot> queue = new LinkedList<>();
		queue.offer(new Dot(r, c));
		visited[r][c] = true;
		
		while (!queue.isEmpty()) {
			Dot front = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nx = dx[d] + front.x;
				int ny = dy[d] + front.y;
				
				if(map[nx][ny] > 0 && !visited[nx][ny]) {
					queue.offer(new Dot(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
	}

	private static void meltIceBergs(int r, int c) {
		Queue<Dot> queue = new LinkedList<>();
		queue.offer(new Dot(r, c));
		visited[r][c] = true;
		
		while (!queue.isEmpty()) {
			Dot front = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nx = dx[d] + front.x;
				int ny = dy[d] + front.y;
				
				if(map[nx][ny] == 0 && !visited[nx][ny]) {
					// �������� 0�̸� 1 ���� (map=0 ������ visited=true �� ���� ����, ��µ� ����X)
					if(map[front.x][front.y] > 0) map[front.x][front.y]--;
				}else if(map[nx][ny] > 0 && !visited[nx][ny]) {						
					queue.offer(new Dot(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}	
	}

	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static class Dot{
		int x, y;

		public Dot(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
