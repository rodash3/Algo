package d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author rodash3
 * @since 2020. 4. 2.
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWgqsAlKr9sDFAW0
 * @memory 43,912 kb
 * @time 202 ms
 * @caution 계속 +1을 하지 않고 가장 가까운 제곱수가 되도록 차이값을 바로 더하기
 */
public class SWEA_6782_D5_현주가좋아하는제곱근놀이 {

	static int i;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(i=1; i<=TC; i++) {
			long N = Long.parseLong(br.readLine());
			cal(N, 0);
		}
		
	}

	public static void cal(long n, int cnt) {
		while (n != 2) {
			if(Math.sqrt(n) - (int)Math.sqrt(n) == 0) { // n이 제곱수일 경우
				n = (long) Math.sqrt(n); // n을 제곱근으로 만든다
				cnt++;
			}else { // 제곱수가 아닐 경우 제곱수가 될때까지 +1을 해야한다
				int root = (int)Math.sqrt(n) + 1; // 가장 가까운 정수 제곱근
				cnt += root*root - n + 1; // 가까운 제곱수가 되도록 1 더하는 횟수 + 루트 씌우기 1회
				n = root;
			}
		}
		System.out.println("#" + i + " " + cnt); // 출력
	}
}
