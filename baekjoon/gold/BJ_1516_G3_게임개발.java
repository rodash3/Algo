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
 * @since 2020. 5. 28.
 * @see https://www.acmicpc.net/problem/1516
 * @memory 21772 KB
 * @time 208 ms
 * @caution 위상정렬 - 진입차수가 0으로 같아도 건설 시간이 짧은 건물을 먼저 지어야한다 -> 우선순위 큐 사용
 * 			ex) A: 10, B: 20 -> C: 10
 * 			A, B 이후에 C가 건설되어야 한다면 동시에 짓더라도 B는 A이후에 지어짐, 결과적으로 B이후 C가 건설되어야 함
 */
public class BJ_1516_G3_게임개발 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 건물 개수
		
		// 진입 차수, 그래프 생성
		int[] indegree = new int[N+1];
		List<Integer>[] graph = new List[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
	
		int[] time= new int[N+1]; // 건물을 짓는데 걸리는 시간
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			
			int n = Integer.parseInt(st.nextToken());
			while (n != -1) {
				graph[n].add(i);
				indegree[i]++;
				n = Integer.parseInt(st.nextToken());
			}
		}//input over

		topologySort(indegree, graph, time);
	}

	private static void topologySort(int[] indegree, List<Integer>[] graph, int[] time) {
		PriorityQueue<building> pq = new PriorityQueue<>();
		long[] result = new long[indegree.length]; //총 걸리는 시간 결과 배열

		// 진입차수 0인 경우 큐에 넣기
		for(int i=1; i<indegree.length; i++) {
			if(indegree[i]==0) pq.offer(new building(i, time[i]));
		}
		
		while (!pq.isEmpty()) {
			building front = pq.poll();
			result[front.no] = front.time;
			
			// 자식 탐색 하면서 진입 차수 감소 -> 0될 경우 pq에 담기
			for(Integer g: graph[front.no]) {
				if(--indegree[g] == 0) {
					pq.offer(new building(g, time[g] + front.time));
				}
			}
		}
		
		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<result.length; i++)
			sb.append(result[i]).append("\n");
		System.out.println(sb);
	}

}

class building implements Comparable<building>{
	int no; // 건물 번호
	long time; // 짓는데 걸리는 시간

	public building(int no, long time) {
		super();
		this.no = no;
		this.time = time;
	}

	@Override
	public int compareTo(building o) {
		return Long.compare(this.time, o.time);
	}
}