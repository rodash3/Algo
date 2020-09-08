package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 9. 9.
 * @see https://www.acmicpc.net/problem/1158
 * @memory 13800 KB
 * @time 96 ms
 * @caution �迭���� ����Ʈ�� Ǫ�� ���� ����
 */
public class BJ_1158_S5_�似Ǫ������ {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ��� N��
		int K = Integer.parseInt(st.nextToken()); // K��° ��� ����

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(i + 1);
		}

		StringBuilder sb = new StringBuilder();
		int cur = 0; // ���� �ε���
		sb.append("<");
		for (int n = 0; n < N; n++) {
			cur = (cur + K - 1) % list.size();
			if (n == N - 1)
				sb.append(list.remove(cur));
			else
				sb.append(list.remove(cur)).append(", ");
		}
		sb.append(">");
		System.out.println(sb);
	}
}
