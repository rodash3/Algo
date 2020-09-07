package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 9. 7.
 * @see https://www.acmicpc.net/problem/1966
 * @memory 13896 KB
 * @time 104 ms
 * @caution
 */
public class BJ_1966_S3_프린터큐 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		
		for(int tc=0; tc<TC; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 문서 개수
			int M = Integer.parseInt(st.nextToken()); // 타겟 문서
			int P = 0; // 타겟 문서의 중요도
			
			st = new StringTokenizer(br.readLine());
			Queue<Doc> queue = new LinkedList<>();
			for(int i=0; i<N; i++) {
				if(i == M) { // 타겟 문서일 경우 중요도를 P에 저장
					P = Integer.parseInt(st.nextToken());
					queue.offer(new Doc(i, P));
				}
				else queue.offer(new Doc(i, Integer.parseInt(st.nextToken())));
			}
			
			int cnt = 1; // 출력한 문서 수 + 1
loop:		while (true) {
				// iterator를 돌면서 문서 중 최대 중요도 찾기
				Iterator<Doc> iterator = queue.iterator();
				int MAX = P; // 최대 중요도
				while (iterator.hasNext()) {
					Doc temp = iterator.next();
					if(MAX < temp.priority) {
						MAX = temp.priority;
					}
				}

				// 중요도 높은 문서를 먼저 출력
				while (true) {
					Doc front = queue.poll();
					if(front.index == M && front.priority == MAX) {
						// 타겟 문서를 출력할 순서
						System.out.println(cnt);
						break loop;
					}
					// 중요도가 낮을 경우 다시 큐에 넣기
					if(front.priority < MAX) queue.offer(front);
					// 중요도가 MAX와 같으면 해당 문서 출력 -> 다시 최대 중요도 찾기
					else {
						cnt++;
						break;
					}
				}
			}
		}
	}
	
	static class Doc{
		int index, priority; // 문서의 최초 위치, 중요도

		public Doc(int index, int priority) {
			super();
			this.index = index;
			this.priority = priority;
		}
	}
}
