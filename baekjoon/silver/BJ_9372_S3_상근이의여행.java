package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 주어지는 비행 스케줄은 항상 연결 그래프를 이룬다. - 연결 그래프: 모든 두 꼭짓점 사이에 경로가 존재하는 그래프
// 상근이가 모든 도시를 여행하기 위해 타야 하는 비행기 종류의 최소 개수를 출력
public class BJ_9372_S3_상근이의여행 {

	static List<Integer>[] graph;
	static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= TC; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 국가의 수
			int M = Integer.parseInt(st.nextToken()); // 비행기 종류

			graph = new List[N + 1];
			for (int j = 1; j <= N; j++) {
				graph[j] = new ArrayList<Integer>();
			}

			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a].add(b);
				graph[b].add(a);
			}

			bfs();
//			System.out.println(N - 1); // 얘만 해도 정답이 됨
		}

	}

	static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N + 1];
		queue.offer(1);
		visited[1] = true;
		int cnt = 0;

		while (!queue.isEmpty()) {
			Integer front = queue.poll();
			cnt++;
			for (int i = 0; i < graph[front].size(); i++) {
				int child = graph[front].get(i);
				if (!visited[child]) {
					queue.offer(child);
					visited[child] = true;
				}
			}
		}
		System.out.println(cnt - 1); // 시작지점 가는 것까지 포함했으므로 -1
	}
}
