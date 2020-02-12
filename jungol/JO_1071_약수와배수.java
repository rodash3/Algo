package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1071_약수와배수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 정수의 개수
		int[] nums = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int m = Integer.parseInt(br.readLine());
		
		int sum_of_divisor = 0;
		int sum_of_multiple = 0;
		
		for(int i=0; i<n; i++) {
			if(m%nums[i] == 0) {
				sum_of_divisor += nums[i];
			}
			if(nums[i]%m == 0) {
				sum_of_multiple += nums[i];
			}
		}
		
		System.out.println(sum_of_divisor);
		System.out.println(sum_of_multiple);
	}

}
