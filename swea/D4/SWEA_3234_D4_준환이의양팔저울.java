package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 5. 22.
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWAe7XSKfUUDFAUw
 * @memory 19,764 KB
 * @time 1,744 ms
 * @caution static 변수보다 함수 인자로 넘겨 사용하는 것이 시간초과가 나지 않는다.
 */
public class SWEA_3234_D4_준환이의양팔저울 {

	static long cnt; // 무게추 올리는 경우의 수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=TC; tc++) {
			int N = Integer.parseInt(br.readLine()); //무게추 개수
			int[] weight = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			// 순열로 무게추 놓을 순서 정하기
			cnt = 0;
			permutation(0, 0, 0, N, weight, new boolean[N]);
			
			System.out.println("#"+tc+" "+cnt); //답 출력
		}
	}//main
	
	private static void permutation(int left, int right, int r, int n, int[] weight, boolean[] visited) {
		if(r == n) {
			cnt++;
		}else {
			for(int i=0; i<n; i++) {
				if(!visited[i]) {
					visited[i] = true;
					permutation(left+weight[i], right, r+1, n, weight, visited);
					if(right+weight[i] <= left) // 오른쪽 무게합이 왼쪽보다 작을 경우만 올린다
						permutation(left, right+weight[i], r+1, n, weight, visited);
					visited[i] = false;
				}
			}
		}
	}//permutation
}
