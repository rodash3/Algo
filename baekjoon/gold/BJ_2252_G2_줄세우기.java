package gold;

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
 * @since 2020. 5. 14.
 * @see https://www.acmicpc.net/problem/2252
 * @memory 50536 KB
 * @time 436 ms
 * @caution 위상 정렬 이해
 */
public class BJ_2252_G2_줄세우기 {

	static int N;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //학생 수
		int M = Integer.parseInt(st.nextToken()); //키 비교 횟수
		
		int[] indegree = new int[N+1]; // 진입 차수
		// 인접 리스트 만들기
		List<Integer>[] graph = new List[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s1 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());
			
			// s1 -> s2
			graph[s1].add(s2); 
			indegree[s2]++;
		}
		
		topologySort(indegree, graph);
	}
	
	public static void topologySort(int[] indegree, List<Integer>[] graph) {
		Queue<Integer> result = new LinkedList<>(); //결과 큐
		Queue<Integer> queue = new LinkedList<>();
		
		// 처음에 집입 차수 0인 노드들 큐에 담기
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) queue.offer(i);
		}
		
		while (!queue.isEmpty()) {
			int front = queue.poll();
            result.offer(front);
            
            // 큐에서 꺼낸 노드과 이어진 노드들 진입 차수 감소 -> 0 되면 큐에 담기
            for(int i : graph[front]) {
                if (--indegree[i] == 0) {
                    queue.offer(i);
                }
            }
		}//while over
		
		// 출력
		StringBuilder sb = new StringBuilder();
		while (!result.isEmpty()) {
			sb.append(result.poll()).append(" ");
		}
		System.out.println(sb);
	}
}
