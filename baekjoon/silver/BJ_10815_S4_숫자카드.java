package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 9. 10.
 * @see https://www.acmicpc.net/problem/10815
 * @memory 143276 KB
 * @time 1104 ms
 * @caution ��Ž�� �ð��ʰ�, ���� Ž�� �����ϴ� �� ���� �ʱ�
 */
public class BJ_10815_S4_����ī�� {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ������ �ִ� ���� ī�� ����
		
		int[] cards = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cards); // ī�� ����
		
		int M = Integer.parseInt(br.readLine()); // ���� ����
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			if(binarySearch(cards, Integer.parseInt(st.nextToken()))) {
				sb.append(1).append(" ");
			}else {
				sb.append(0).append(" ");
			}
		}//while
		
		System.out.println(sb);
	}

	// ���� Ž��
	private static boolean binarySearch(int[] cards, int target) {
		int start = 0;
		int end = cards.length-1;
		int middle;
		
		while (start <= end) {
			middle = (start + end)/2;
			if(cards[middle] == target) return true;
			else if (cards[middle] < target) {
				start = middle + 1;
			}else {
				end = middle - 1;
			}
		}
		return false;
	}
}
