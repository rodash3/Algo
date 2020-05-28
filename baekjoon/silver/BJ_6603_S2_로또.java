package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 5. 29.
 * @see https://www.acmicpc.net/problem/6603
 * @memory 14564 KB
 * @time 116 ms
 * @caution 조합
 */
public class BJ_6603_S2_로또 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			if(N == 0) break; // 입력 종료
			
			int[] nums = new int[N];
			for(int i=0; i<N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			combi(0, 0, new int[6] ,nums);
			System.out.println();
		}
	}//main

	private static void combi(int r, int start, int[] temp, int[] nums) {
		if(r==6) {
			// 조합 경우마다 출력
			StringBuilder sb = new StringBuilder();
			for(int t: temp) {
				sb.append(t).append(" ");
			}
			sb.append("\n");
			System.out.print(sb);
		}else {
			for(int i=start; i<nums.length; i++) {
				temp[r] = nums[i];
				combi(r+1, i+1, temp, nums);
			}
		}
	}

}
