package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 4. 3.
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AXCjsn0KJzcDFAX0
 * @memory 26,656 kb
 * @time 102 ms
 * @caution 재귀를 사용하지 않고 fn(x)값을 계산하여 미리 저장해놓는다
 */
public class SWEA_9659_D4_다항식계산 {

	static int[] t, a, b, x;
	static int N, M;
	static long[][] fn; //fn[x1] ~ fn[xM] 값 저장
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=TC; i++) {
			/* input start */
			N = Integer.parseInt(br.readLine());
			
			t = new int[N+1];
			a = new int[N+1];
			b = new int[N+1];
			for(int j=2; j<=N; j++) {
				st = new StringTokenizer(br.readLine());
				t[j] = Integer.parseInt(st.nextToken());
				a[j] = Integer.parseInt(st.nextToken());
				b[j] = Integer.parseInt(st.nextToken());
			}
			
			M = Integer.parseInt(br.readLine());
			x = new int[M+1];
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				x[j] = Integer.parseInt(st.nextToken());
			}
			/* input end */
			
			fn = new long[N+1][M+1];
			calFnX();
			
			// 출력
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(i).append(" ");
			for(int j=1; j<=M; j++) {
				sb.append(fn[N][j]).append(" ");
			}
			System.out.println(sb.toString());
		}
	}

	//fn[x1] ~ fn[xM] 값 저장하는 함수
	public static void calFnX() {
		for(int n=0; n<=N; n++) {
			for(int m=1; m<=M; m++) {
				if(n == 0) {
					fn[n][m] = 1;
				}else if (n == 1) {
					fn[n][m] = x[m];
				}else {
					if(t[n] == 1) {
						// ti =1이면 fi(x) = fai(x) + fbi(x)
						fn[n][m] = (fn[a[n]][m] + fn[b[n]][m]) % 998244353;
					}else if (t[n] == 2) {						
						// ti =2이면 fi(x) = ai × fbi(x)
						fn[n][m] = (a[n] * fn[b[n]][m]) % 998244353;
					}else {					
						// ti= 3이면 fi(x)= fai(x) × fbi(x)
						fn[n][m] = (fn[a[n]][m] * fn[b[n]][m]) % 998244353;
					}
				}
			}
		}
	}
}
