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

public class BJ_1260_S1_DFS와BFS {

	static List<Integer>[] graph;
	static boolean[] visited;
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점 개수
		int M = Integer.parseInt(st.nextToken()); // 간선 개수
		int V = Integer.parseInt(st.nextToken()); // 탐색 시작 정점
		
		graph = new List[N+1];
		
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		// 그래프 만들기
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 간선 시작점
			int b = Integer.parseInt(st.nextToken()); // 간선 끝점
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for(int i=1; i<=N; i++) {
			Collections.sort(graph[i]);
//			System.out.println(graph[i]);
		}

		visited = new boolean[N+1];
		dfs(V);
		System.out.println();
		bfs(V);
	}

	static void dfs(int v) {
		if(visited[v])
			return;
		
		visited[v] = true;
		System.out.print(v + " ");
		for(int i=0; i<graph[v].size(); i++) {
			int n = graph[v].get(i);
			dfs(n);
		}
	
	}
	
	static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<>();
		visited = new boolean[N+1];
		visited[v] = true;
		queue.offer(v);
		while (!queue.isEmpty()) {
			int front = queue.poll();
			System.out.print(front + " ");
			for(int i=0; i<graph[front].size(); i++) {
				int n = graph[front].get(i);
				if(!visited[n]) {
					queue.offer(n);
					visited[n] = true;
				}
			}
		}
	}
}
