package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 5. 14.
 * @see https://www.acmicpc.net/problem/1766
 * @memory 46700 KB
 * @time 452 ms
 * @caution 위상 정렬 - 우선순위 큐 사용
 */
public class BJ_1766_G2_문제집 {

	static int N;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //문제 수
		int M = Integer.parseInt(st.nextToken()); //문제 정보 수

		int[] indegree = new int[N+1]; //진입 차수
		List<Integer>[] graph = new List[N+1]; //인접 리스트
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			// n1 -> n2
			graph[n1].add(n2); //인접리스트에 추가
			indegree[n2]++; //진입차수 증가
		}
		
		topologySort(indegree, graph);
	}
	
	private static void topologySort(int[] indegree, List<Integer>[] graph) {
		StringBuilder sb = new StringBuilder(); // 결과 출력
		// 문제 번호가 작을수록 쉬운 문제, 쉬운 문제부터 먼저 풀어야 함 ->  큐에 작은 것들부터 정렬되어야 함.. pq 사용
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		// 진입 차수 0인 문제 담기
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) {
				pq.offer(i);
			}
		}
		
		while (!pq.isEmpty()) {
			// 가장 쉬운 문제 - 작은 번호부터 큐에서 꺼내기
			int front = pq.poll();
			sb.append(front).append(" ");
			
			// 큐에서 꺼낸 문제와 이어진 문제의 진입 차수 감소 -> 0일 경우 pq에 담기
			for(int i : graph[front]) {
				if(--indegree[i] == 0) {
					pq.offer(i);
				}
			}
		}//while over
		
		System.out.println(sb);
	}

}
