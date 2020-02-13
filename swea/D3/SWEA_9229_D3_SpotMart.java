package Solving_Club;

import java.util.Scanner;

public class SWEA_9229_D3_SpotMart {

	public static void main(String[] args) {
	      Scanner scanner = new Scanner(System.in);
	        int testcase = scanner.nextInt(); // 전체 테스트케이스
	        StringBuilder sb = new StringBuilder();
	 
	        for (int i = 0; i < testcase; i++) {
	            sb.append("#").append(i + 1).append(" ");
	            int snackNum = scanner.nextInt(); // 과자 개수
	            int[] snacks = new int[snackNum];
	            int limitWeight = scanner.nextInt(); // 무게 제한
	             
	            // 과자 무게 읽기
	            for(int k=0; k<snackNum; k++)
	                snacks[k] = scanner.nextInt();
	             
	            int[] shopping = new int[2];
	            int maxWeight = 0; // 양손에 들 수 있는 최대 무게, 항상 두봉지로
	            for(int j=0; j<snackNum-1; j++) {
	                for(int k=j+1; k<snackNum; k++) {
	                    shopping[0] = snacks[j];
	                    shopping[1] = snacks[k];
	                    int temp = shopping[0] + shopping[1];
	                    if(temp <= limitWeight && maxWeight < temp) {
	                        maxWeight = shopping[0] + shopping[1];
	                    }
	                }
	            }
	            if(maxWeight == 0) sb.append(-1);
	            else {
	                sb.append(maxWeight);
	            }
	            sb.append("\n");
	        }
	        System.out.println(sb);
	        scanner.close();
	}

}
