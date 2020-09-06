package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 9. 6.
 * @see https://www.acmicpc.net/problem/2583
 * @memory 13580 KB
 * @time 88 ms
 * @caution ������ ũ��� NxM, �迭 �ε����� �ٸ� ��ǥ ��ǲ ����
 */
public class BJ_2583_S1_�������ϱ� {

	static List<Integer> areas = new ArrayList<>();
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // NxM ����
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); // ���簢�� ����

		boolean[][] map = new boolean[N][M];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for (int r = x1; r < x2; r++) {
				for (int c = y1; c < y2; c++) {
					// ���簢�� ���� ǥ��
					if (!map[r][c])
						map[r][c] = true;
				}
			}
		} // input

		visited = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				// ���� ũ�� ���ϱ�
				if (!map[r][c] && !visited[r][c]) {
					bfs(r, c, map, M, N);
				}
			}
		}
		System.out.println(areas.size()); // �и� ���� ����
		// �и� ���� ���� �������� ���
		Collections.sort(areas);
		for (Integer area : areas) {
			System.out.print(area + " ");
		}
	}

	private static void bfs(int r, int c, boolean[][] map, int m, int n) {
		Queue<Dot> queue = new LinkedList<>();
		queue.offer(new Dot(r, c));
		visited[r][c] = true;
		int area = 0; // �и� ���� ����

		while (!queue.isEmpty()) {
			Dot front = queue.poll();
			area++;

			for (int d = 0; d < 4; d++) {
				int nx = front.x + dx[d];
				int ny = front.y + dy[d];

				// ������ ������ ���簢�� ���ΰ� �ƴϰ� �湮���� �ʾ����� ť�� �߰�
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && !map[nx][ny] && !visited[nx][ny]) {
					queue.offer(new Dot(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
		areas.add(area); // ���� ���� �߰�
	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static class Dot {
		int x, y;

		public Dot(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
