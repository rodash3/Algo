package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 5. 1.
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIseXoKEUcDFAWN&categoryId=AWIseXoKEUcDFAWN&categoryType=CODE
 * @memory 86,812 kb
 * @time 442 ms
 * @caution 단순 정렬
 */
public class SWEA_4050_D4_재근이의대량할인 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=TC; i++) {
			
			int N = Integer.parseInt(br.readLine());
			
			Integer[] nums = new Integer[N];
			
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				nums[j] = Integer.parseInt(st.nextToken());
				sum += nums[j];
			}
			
			Arrays.sort(nums, Comparator.reverseOrder());
			
			for(int j=0; j<N; j+=3) {
				if(j+2 < N)
					sum -= nums[j+2];
			}

			System.out.println("#"+i+" "+sum);
		}
	}

}
