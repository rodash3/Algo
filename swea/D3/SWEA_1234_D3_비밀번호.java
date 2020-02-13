package Solving_Club;

import java.util.Scanner;

public class SWEA_1234_D3_비밀번호 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		for(int i=1; i<=10; i++) {
			scanner.next(); //length
			String string = scanner.next();
			String originString;
			
			do {
				originString = string;
				for(int j=0; j<10; j++) {
					string = string.replace(j+""+j, "");
				}
			} while (!originString.equals(string));

			System.out.println("#"+i+" "+string);
		}
	}

}
