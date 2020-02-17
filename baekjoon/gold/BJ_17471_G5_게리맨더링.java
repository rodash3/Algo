package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17471_G5_게리맨더링 {

	static List<Integer>[] graph; // 인접 리스트
	static int[] people; // 각 구역의 인구수
	static int V; // 구역 개수

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		graph = new List[V + 1];
		people = new int[V + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= V; i++) { // 인구수 입력
			people[i] = Integer.parseInt(st.nextToken());
			graph[i] = new ArrayList<Integer>();
		}

		// 그래프 만들기
		for (int i = 1; i <= V; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 인접한 구역의 수
			for (int j = 0; j < n; j++) {
				int b = Integer.parseInt(st.nextToken());
				graph[i].add(b);
			}
		}
//		for (List n : graph)
//			System.out.println(n);

		int min = Integer.MAX_VALUE;
		// 부분집합을 구해서 두 그룹으로 나누기
		for (int i = 1; i < (1 << V) - 1; i++) {
			List<Integer> g1 = new ArrayList<>(); // 부분집합
			List<Integer> g2 = new ArrayList<>(); // 여집합

			for (int j = 0; j < V; j++) {
				if ((i & 1 << j) > 0) {
					g1.add(j + 1);
				} else {
					g2.add(j + 1);
				}
			}

			int result1 = bfs(g1);
			if (result1 > 0) {
				int result2 = bfs(g2);
				if (result2 > 0) {
					min = Math.min(min, Math.abs(result1 - result2));
				}
			}

		}

		// 그룹 구성에 실패해서 min 값이 여전히 MAX일 경우는 -1로 변경
		if (min == Integer.MAX_VALUE) {
			min = -1;
		}
		System.out.println(min);
	}

	static int bfs(List<Integer> group) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[V + 1];
		int cnt = 0; // 탐색한 노드의 수 - group.size() == cnt ==> 완탐 성공
		int popSum = 0; // 인구 수
		queue.offer(group.get(0));

		while (!queue.isEmpty()) {
			int front = queue.poll();

			if (visited[front])
				continue;

			visited[front] = true;
			cnt++;
			popSum += people[front];

			for (int child : graph[front]) {
				if (!visited[child] && group.contains(child)) {
					queue.offer(child);
				}
			}
		}

		if (cnt == group.size()) {
			return popSum;
		} else {
			return -1;
		}
	}
}
