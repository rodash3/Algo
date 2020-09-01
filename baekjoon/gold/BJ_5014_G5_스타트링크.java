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
 * @see https://www.acmicpc.net/problem/5014
 * @memory 61352 KB
 * @time 152 ms
 * @caution 엘리베이터로 갈 수 있는 곳을 전부 가면서(1<= floor <= F) 방문 체크 bfs
 */
public class BJ_5014_G5_스타트링크 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int F = Integer.parseInt(st.nextToken()); // 가장 높은 층
		int S = Integer.parseInt(st.nextToken()); // 현재층
		int G = Integer.parseInt(st.nextToken()); // 목표층
		int U = Integer.parseInt(st.nextToken()); // 위 버튼
		int D = Integer.parseInt(st.nextToken()); // 아래 버튼
		
		Queue<Floor> queue = new LinkedList<>();
		boolean[] visited = new boolean[F+1];
		queue.offer(new Floor(S, 0));
		visited[S] = true;
		
		while (!queue.isEmpty()) {
			Floor front = queue.poll();
			
			if(front.floor == G) {
				System.out.println(front.degree);
				return;
			}
			
			if (U != 0 && front.floor+U <= F && !visited[front.floor+U]) {
				queue.offer(new Floor(front.floor+U, front.degree+1));
				visited[front.floor+U] = true;
			}
			if (D != 0 && front.floor-D >= 1 && !visited[front.floor-D]) {
				queue.offer(new Floor(front.floor-D, front.degree+1));
				visited[front.floor-D] = true;
			}
		}
		System.out.println("use the stairs"); // 엘리베이터로 이동 불가
	}
	
	static class Floor{
		int floor, degree;

		public Floor(int floor, int degree) {
			super();
			this.floor = floor;
			this.degree = degree;
		}
	}
}
