package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 8. 27.
 * @see https://www.acmicpc.net/problem/2644
 * @memory 13172 KB
 * @time 88 ms
 * @caution
 */
public class BJ_2644_S2_촌수계산 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 전체 사람 수
		
		st = new StringTokenizer(br.readLine());
		// 촌수 계산해야하는 두 사람
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int E = Integer.parseInt(br.readLine()); // 관계 개수
		
		// 그래프 생성
		List<Integer>[] graph = new List[V+1];
		for(int i=1; i<=V; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}
		// 그래프 생성
		
		bfs(graph, start, end);
	}

	private static void bfs(List<Integer>[] graph, int start, int end) {
		boolean[] visited = new boolean[graph.length];
		Queue<Node> queue = new LinkedList<>();
		visited[start] = true;
		queue.offer(new Node(start, 0));
		int result = 0; // start와 end 사이 촌수
		
		while (!queue.isEmpty()) {
			Node front = queue.poll();
			
			for (Integer i : graph[front.num]) {
				if(!visited[i]) {					
					if(i == end) {
						result = front.degree + 1;
					}else {
						queue.offer(new Node(i, front.degree+1));
						visited[i] = true;
					}
				}
			}
		}
	
		System.out.println(result==0? -1:result);
	}
	
	static class Node {
		int num;
		int degree;
		
		public Node(int num, int degree) {
			super();
			this.num = num;
			this.degree = degree;
		}
	}
}
