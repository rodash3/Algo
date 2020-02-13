package Solving_Club;

import java.util.Arrays;
import java.util.Scanner;

public class SWEA_1966_D2_숫자를정렬하자 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int TC = scanner.nextInt();
		
		for(int i=1; i<=TC; i++) {
			int N = scanner.nextInt();
			int[] nums = new int[N];
			
			for(int j=0; j<N; j++) {
				nums[j] = scanner.nextInt();
			}
			Arrays.sort(nums);
			
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("#").append(i).append(" ");
			for(int j=0; j<N; j++) {
				sBuilder.append(nums[j]+" ");
			}
			System.out.println(sBuilder);
		}
	}

}
