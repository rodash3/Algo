package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1258_D4_행렬찾기 {

	static int n;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=TC; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // n x n 행렬
			int[][] matrix = new int[n][n];
			
			// 행렬 데이터 입력
			for(int j=0; j<n; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<n; k++) {
					matrix[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean[][] visited = new boolean[n][n];
			Queue<Dot> queue = new LinkedList<>();
			List<Dot> list = new ArrayList<>();
			int listIdx = 0;
			
			for(int x=0; x<n; x++) {
				for(int y=0; y<n; y++) {
					if(!visited[x][y] && matrix[x][y] != 0) {
						queue.offer(new Dot(x, y));
						visited[x][y] = true;
						list.add(new Dot(x, y));  // 행렬 시작점
						listIdx++;
						while (!queue.isEmpty()) {
							Dot front = queue.poll();
							if(list.size() > listIdx) {
								list.remove(listIdx);
							}
							list.add(new Dot(front.x, front.y)); // 행렬 끝점
							
							for(int k=0; k<dx.length; k++) {
								int nx = front.x + dx[k];
								int ny = front.y + dy[k];
								
								if(isIn(nx, ny) && !visited[nx][ny] && matrix[nx][ny] != 0) {
									queue.offer(new Dot(nx, ny));
									visited[nx][ny] = true;
								}
							}
						}
						listIdx++;
					}
				}
			}
			
			// 행렬 시작점, 끝점을 통해 행렬 사이즈 구하기
			List<Dot> list2 = new ArrayList<>();
			for(int k=0; k<list.size(); k=k+2) {
				int row = list.get(k+1).x - list.get(k).x + 1;
				int col = list.get(k+1).y - list.get(k).y + 1;
				list2.add(new Dot(row, col));
			}
			
			StringBuilder sb= new StringBuilder();
			sb.append("#").append(i).append(" ").append(list2.size()).append(" ");
		
			list2.sort(new Comparator<Dot>() {
				@Override
				public int compare(Dot o1, Dot o2) {
					Integer size1 = o1.x * o1.y;
					Integer size2 = o2.x * o2.y;
					if(size1.equals(size2)) { // 행렬 크기가 같으면 행이 작은 것 먼저
						Integer x1 = o1.x;
						Integer x2 = o2.x;
						return x1.compareTo(x2);
					}
					else {
						return size1.compareTo(size2);
					}
				}
			});

			for(int k=0; k<list2.size(); k++) {
				sb.append(list2.get(k).x).append(" ").append(list2.get(k).y).append(" ");
			}
			
			System.out.println(sb);
		}

	}
	
	static int[] dx = {0, 1}; // 오른쪽, 아래쪽만 탐방
	static int[] dy = {1, 0};
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<n && c>=0 && c<n;
	}
	
	static class Dot{
		int x;
		int y;
		
		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
