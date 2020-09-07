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
public class BJ_1966_S3_������ť {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		
		for(int tc=0; tc<TC; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // ���� ����
			int M = Integer.parseInt(st.nextToken()); // Ÿ�� ����
			int P = 0; // Ÿ�� ������ �߿䵵
			
			st = new StringTokenizer(br.readLine());
			Queue<Doc> queue = new LinkedList<>();
			for(int i=0; i<N; i++) {
				if(i == M) { // Ÿ�� ������ ��� �߿䵵�� P�� ����
					P = Integer.parseInt(st.nextToken());
					queue.offer(new Doc(i, P));
				}
				else queue.offer(new Doc(i, Integer.parseInt(st.nextToken())));
			}
			
			int cnt = 1; // ����� ���� �� + 1
loop:		while (true) {
				// iterator�� ���鼭 ���� �� �ִ� �߿䵵 ã��
				Iterator<Doc> iterator = queue.iterator();
				int MAX = P; // �ִ� �߿䵵
				while (iterator.hasNext()) {
					Doc temp = iterator.next();
					if(MAX < temp.priority) {
						MAX = temp.priority;
					}
				}

				// �߿䵵 ���� ������ ���� ���
				while (true) {
					Doc front = queue.poll();
					if(front.index == M && front.priority == MAX) {
						// Ÿ�� ������ ����� ����
						System.out.println(cnt);
						break loop;
					}
					// �߿䵵�� ���� ��� �ٽ� ť�� �ֱ�
					if(front.priority < MAX) queue.offer(front);
					// �߿䵵�� MAX�� ������ �ش� ���� ��� -> �ٽ� �ִ� �߿䵵 ã��
					else {
						cnt++;
						break;
					}
				}
			}
		}
	}
	
	static class Doc{
		int index, priority; // ������ ���� ��ġ, �߿䵵

		public Doc(int index, int priority) {
			super();
			this.index = index;
			this.priority = priority;
		}
	}
}
