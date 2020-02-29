package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 2. 29.
 * @see https://www.acmicpc.net/problem/14501 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 13502 KB
 * @time 80 ms
 * @caution N-1날 걸리는 기간이 1일인 경우까지는 상담 가능
 */
public class BJ_14501_S4_퇴사 {

	static int N;
	static int[][] work;
	static int MAX = Integer.MIN_VALUE; // 최대 수익
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		work = new int[N][2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			work[i][0] = Integer.parseInt(st.nextToken()); // 걸리는 기간
			work[i][1] = Integer.parseInt(st.nextToken()); // 받을 수 있는 금액
		}
		
		// 첫째날부터 일정에 넣어보면서 가능한 최대 금액 구하기
		for(int i=0; i<N; i++) {
			dfs(i, 0);
		}

		System.out.println(MAX);
	}

	static void dfs(int day, int price) {
		// 퇴사날을 넘어가면 이전까지 한 금액과 MAX 비교
		if(day >= N || day + work[day][0] > N) {
			MAX = Math.max(price, MAX);
			return;
		}
		
		// 날짜가 퇴사날을 넘어가지 않으면 일 수행 가능
		price += work[day][1];
		day += work[day][0];

		// 이후 날짜부터 가능한 일 하기
		for(int i=day; i<=N; i++) {
			dfs(i, price);
		}
	
	}
}
