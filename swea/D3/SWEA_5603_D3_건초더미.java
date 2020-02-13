package Solving_Club;

import java.util.Scanner;

public class SWEA_5603_D3_건초더미 {

	public static void main(String[] args) {

		String src = "1\r\n" + 
				"4\r\n" + 
				"2\r\n" + 
				"10\r\n" + 
				"7\r\n" + 
				"1";
		Scanner scanner = new Scanner(System.in);
		scanner = new Scanner(src);
		int TC = scanner.nextInt();
		
		for(int i=1; i<=TC; i++) {
			
			int N = scanner.nextInt(); // 건초더미 개수
			int[] hay = new int[N];
			
			int sum = 0;
			for(int k=0; k<N; k++) {
				hay[k] = scanner.nextInt();
				sum += hay[k];
			}
			
			int average = sum/N;
			int abs_sum =0;
			for(int k=0; k<N; k++) {
				abs_sum += Math.abs(hay[k]-average);
			}
			
			System.out.println("#"+i+" "+abs_sum/2);
		}
	}

}
