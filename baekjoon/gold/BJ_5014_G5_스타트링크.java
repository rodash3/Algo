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
 * @caution ���������ͷ� �� �� �ִ� ���� ���� ���鼭(1<= floor <= F) �湮 üũ bfs
 */
public class BJ_5014_G5_��ŸƮ��ũ {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int F = Integer.parseInt(st.nextToken()); // ���� ���� ��
		int S = Integer.parseInt(st.nextToken()); // ������
		int G = Integer.parseInt(st.nextToken()); // ��ǥ��
		int U = Integer.parseInt(st.nextToken()); // �� ��ư
		int D = Integer.parseInt(st.nextToken()); // �Ʒ� ��ư
		
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
		System.out.println("use the stairs"); // ���������ͷ� �̵� �Ұ�
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
