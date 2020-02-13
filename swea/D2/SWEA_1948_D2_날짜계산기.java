package Solving_Club;

import java.util.Scanner;

public class SWEA_1948_D2_날짜계산기 {

	public static void main(String[] args) {
		String src = "10\r\n" + 
				"3 1 3 31\r\n" + 
				"5 5 8 15\r\n" + 
				"7 17 12 24\r\n" + 
				"3 22 5 10\r\n" + 
				"2 13 6 12\r\n" + 
				"4 30 11 9\r\n" + 
				"7 10 9 28\r\n" + 
				"6 30 9 20\r\n" + 
				"4 19 12 12\r\n" + 
				"1 1 12 31\r\n" + 
				"5 23 9 12\r\n" + 
				"2 12 9 4";
		Scanner scanner = new Scanner(System.in);
		scanner = new Scanner(src);
		
		int TC = scanner.nextInt();
		int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		for(int i=1; i<=TC; i++) {
			int month = scanner.nextInt();
			int day = scanner.nextInt();
			int nMonth = scanner.nextInt();
			int nDay = scanner.nextInt();
			
			if(month == nMonth) {
				System.out.println("#"+i+" "+(nDay-day+1));
			}else {
				int answer = 0;
				answer += days[month] - day + 1;
				for(int j=month+1; j<nMonth; j++) {
					answer += days[j];
				}
				answer += nDay;
				System.out.println("#"+i+" "+answer);
			}
		}

	}

}
