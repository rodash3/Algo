package Solving_Club;

import java.util.Scanner;

public class SWEA_1234_D3_비밀번호 {

	public static void main(String[] args) {
		
		String src = "10 1238099084\r\n" + 
				"16 4100112380990844\r\n" + 
				"26 12380990844100112380990844\r\n" + 
				"42 123809908444100112380990844100112380990844\r\n" + 
				"55 1238099084441001123809908441001321238099084432180990844\r\n" + 
				"60 123809908444100145351123809908441001321238099084432180990844\r\n" + 
				"71 12380990844410013218099084441001123809908441001321238099084432180990844\r\n" + 
				"99 123809908444100180990844410013211238099084410013212380990844123809908441238099084410013232180990844\r\n" + 
				"82 1238099084441001410011238099084412380990844100132123809908441238099084432180990844\r\n" + 
				"58 0899809812380990844100132123809908441238099084432180990844";

		Scanner scanner = new Scanner(System.in);
		scanner = new Scanner(src);
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
