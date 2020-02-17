package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_2606_S2_바이러스 {

	static List<Integer>[] graph;
	static boolean[] visited;
	static int cnt;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine()); // 컴퓨터 수
		int E = Integer.parseInt(br.readLine()); // 간선 수
		graph = new List[V+1];
		visited = new boolean[V+1];
		
		// 그래프 생성
		for(int i=1; i<graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		cnt = 0;
		dfs(1);
		System.out.println(cnt-1);  // 자기자신(1)도 셌기 때문에 -1

	}

	static void dfs(int v) {
		if(visited[v])
			return;
		
		visited[v] = true;
		cnt++;
		for(int i=0; i<graph[v].size(); i++) {
			int nv = graph[v].get(i);
			dfs(nv);
		}
	}
}
