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
 * @caution 배열보다 리스트로 푸는 것이 좋다
 */
public class BJ_1158_S5_요세푸스문제 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 사람 N명
		int K = Integer.parseInt(st.nextToken()); // K번째 사람 제거

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(i + 1);
		}

		StringBuilder sb = new StringBuilder();
		int cur = 0; // 현재 인덱스
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
