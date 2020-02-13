package D3;

import java.util.Scanner;

public class SWEA_1220_D3_Magnetic {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
//		scanner = new Scanner(src);

		for (int i = 1; i <= 10; i++) {
			scanner.nextInt(); // 한변 주어짐 - 모두 100
			
			int[][] table = new int[100][100];
			for(int r=0; r<100; r++) {
				for(int c=0; c<100; c++) {
					table[r][c] = scanner.nextInt();
				}
			}
			
			int deadlock = 0;
			// 한 열씩 살펴본다
			for(int c=0; c<100; c++) {
				boolean isN = false;
				for(int r=0; r<100; r++) {
					
					if(isN) { // 이 열에서 이미 N 자성체가 나왔음
						if(table[r][c]==1) {
							table[r][c] = 0;
						}else if (table[r][c]==2) { // S 자성체 발견
							deadlock++;
							isN = false;
						}
					}else {
						if(table[r][c]==1) { //N극
							isN = true;
						}
					}
					
				}
			}
			System.out.println("#" + i+ " " + deadlock);
		}
	}

}
