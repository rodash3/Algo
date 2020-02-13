package D4;

import java.util.Scanner;
import java.util.Stack;

public class SWEA_1218_D4_괄호짝짓기 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		Stack<Character> stack = new Stack<Character>();
		
		String opener = "(<{[";

		
		for(int tc=1; tc<=10; tc++) {
			int answer = 1;
			
			int leng = scanner.nextInt();
			String chars = scanner.next();
			
			for(int k=0; k<leng; k++) {
				if(opener.contains(chars.charAt(k)+"")) {
					stack.push(chars.charAt(k));
				}else {
					char closer = chars.charAt(k);
					char pop = stack.pop();
					if(closer == '>' && pop == '<') {
						continue;
					}else if (closer == '}' && pop == '{') {
						continue;
					}else if (closer == ']' && pop == '[') {
						continue;
					}else if (closer == ')' && pop == '(') {
						continue;
					}else {
						answer = 0;
						break;
					}
				}
			}
			
			System.out.println("#"+tc+" "+answer);
			stack.clear();
		}
	}

}
