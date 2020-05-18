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
 * @since 2020. 5. 18.
 * @see https://www.acmicpc.net/problem/2623
 * @memory 13736 KB
 * @time 96 ms
 * @caution 위상 정렬 - 사이클 있는지 확인
 */
public class BJ_2623_G2_음악프로그램 {

	static int N;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 가수 수
		int M = Integer.parseInt(st.nextToken()); // 보조 PD 수
		
		int[] indegree = new int[N+1]; // 진입 차수
		List<Integer>[] graph = new List[N+1]; // 그래프
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 피디가 맡은 가수 수
			
			int[] nums = new int[n];
			for(int k=0; k<n; k++) { // 가수 순서 받기
				nums[k] = Integer.parseInt(st.nextToken());
			}
			for(int k=0; k<n-1; k++) { // 순서에 맞게 그래프 생성
				graph[nums[k]].add(nums[k+1]);
				indegree[nums[k+1]]++; // 진입 차수 증가
			}
		}// input over
	
		topologySort(indegree, graph);
	}

	private static void topologySort(int[] indegree, List<Integer>[] graph) {
		StringBuilder sb = new StringBuilder(); // 결과 출력
		Queue<Integer> queue = new LinkedList<>();
		
		// 진입 차수 0인 번호는 큐에 담기
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		for(int i=1; i<=N; i++) {
			// N개 방문 전 큐가 비면 사이클이 있는것
			if(queue.isEmpty()) {
				// 순서 정하기 불가능
				System.out.println(0);
				return;
			}
			
			int front = queue.poll();
			sb.append(front).append("\n");
			
			// 큐에서 꺼낸 번호와 이어진 번호의 진입 차수 감소 -> 0일 경우 큐에 담기
			for(int g : graph[front]) {
				if(--indegree[g] == 0) {
					queue.offer(g);
				}
			}
		}//for over
		System.out.println(sb); // 출력
		
	}//topologySort
}
