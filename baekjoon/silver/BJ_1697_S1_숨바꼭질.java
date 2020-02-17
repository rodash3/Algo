package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1697_S1_숨바꼭질 {

	static int N;
	static int K;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치

		bfs();
	}

	static void bfs() {
		Queue<Pos> queue = new LinkedList<>();
		boolean[] visited = new boolean[100001];
		queue.offer(new Pos(N, 0));
		visited[N] = true;

		int second = 0;

		while (!queue.isEmpty()) {
			Pos front = queue.poll();
			second = front.sec;

			if (front.x == K)
				break;

			if (front.x+1 <= 100000 && !visited[front.x + 1] ) {
				queue.offer(new Pos(front.x + 1, second + 1));
				visited[front.x + 1] = true;
			}

			if (front.x-1 <= 100000 && front.x-1 >=0 && !visited[front.x - 1] ) {
				queue.offer(new Pos(front.x - 1, second + 1));
				visited[front.x - 1] = true;
			}

			if (front.x * 2 <= 100000 && !visited[front.x * 2] ) {
				queue.offer(new Pos(front.x * 2, second + 1));
				visited[front.x*2] = true;
			}
		}

		System.out.println(second);
	}

	static class Pos {
		int x;
		int sec;

		public Pos(int x, int sec) {
			this.x = x;
			this.sec = sec;
		}
	}
}
