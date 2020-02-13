package algo_basic.day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_11724_S3_연결요소의개수 {

	static List<Integer>[] graph;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sTokenizer = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(sTokenizer.nextToken()); // 정점 개수
		int M = Integer.parseInt(sTokenizer.nextToken()); // 간선 개수

		graph = new List[N + 1];
		visited = new boolean[N + 1];
		// graph 초기화
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		// 그래프에 데이터 넣기
		for (int i = 0; i < M; i++) {
			sTokenizer = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(sTokenizer.nextToken()); // u 간선 시작점
			int v = Integer.parseInt(sTokenizer.nextToken()); // v 간선 끝점

			graph[u].add(v);
			graph[v].add(u); // 양방향
		}
		
		int sum = 0;
		for(int i=1; i<=N; i++) {
			if(!visited[i])
				sum += bfs(i);
		}

		System.out.println(sum);
	}
	
	public static int bfs(int v) {
		int cnt = 1;
		// 지점 방문 처리
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(v);
		visited[v] = true;
		while(!queue.isEmpty()) {
			int x = queue.poll();
			for(int k=0; k<graph[x].size(); k++) {
				int nx = graph[x].get(k);
				if(!visited[nx]) {
					queue.offer(nx);
					visited[nx] = true;
				}
			}
		}
		return cnt;
	}
}
