package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 5. 22.
 * @see https://www.acmicpc.net/problem/13023
 * @memory 26764 KB
 * @time 220 ms
 * @caution dfs시 visited 처리 유의
 */
public class BJ_13023_G5_ABCDE {

	static boolean flag; //ABCDE 관계가 있는지 여부
	static boolean[] visited; //방문 여부
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 사람수
		int M = Integer.parseInt(st.nextToken()); // 친구 관계 수

		// 친구 관계 그래프 생성
		List<Integer>[] graph = new List[N];
		for(int i=0; i<N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			graph[n1].add(n2);
			graph[n2].add(n1);
		}// input over
		
		for(int i=0; i<N; i++) {
			if(!flag) { // ABCDE 관계 찾을 때까지 탐색
				visited = new boolean[N];		
				dfs(i, graph, 1);
			}
		}
		
		if(flag) System.out.println(1);
		else System.out.println(0);
	}

	static void dfs(int v, List<Integer>[] graph, int depth) {
		if(visited[v])
			return;
		
		// depth가 5 = ABCDE 관계 발견
		if(depth == 5) {
			flag = true;
			return;
		}
		
		visited[v] = true; //현재 번호 방문 처리
		// 친구 관계 깊이 탐색
		for(int g: graph[v]) {
			dfs(g, graph, depth+1);
		}
		visited[v] = false; //다른 친구관계를 타고 다시 방문할 수 있음
	}
}
