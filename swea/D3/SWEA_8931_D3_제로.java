package D3;

import java.util.Scanner;
import java.util.Stack;

public class SWEA_8931_D3_제로 {
	
	public static void main(String[] args) {

		String srcString = "2\r\n" + "6\r\n" + "10000\r\n" + "10000\r\n" + "0\r\n" + "0\r\n" + "100000\r\n"
				+ "0\r\n" + "10\r\n" + "1\r\n" + "3\r\n" + "5\r\n" + "4\r\n" + "0\r\n" + "0\r\n" + "7\r\n" + "0\r\n"
				+ "0\r\n" + "6";

		Scanner scanner = new Scanner(System.in);
		scanner = new Scanner(srcString);
		
		Stack<Integer> stack = new Stack<>();
		
		int tc = scanner.nextInt();
		
		for(int i=1; i<=tc; i++) {
			int answer = 0;
			
			int K = scanner.nextInt();
			
			for(int k=0; k<K; k++) {
				int price = scanner.nextInt();
				if(price != 0) {
					stack.push(price);
				}else {
					if(!stack.isEmpty())
						stack.pop();
				}
			}
			
			for(int k=0; k<stack.size(); k++) {
				answer += stack.get(k);
			}
			
			System.out.println("#"+i+" "+answer);
			stack.clear();
		}
		
		scanner.close();
	}
	
}
