package Solving_Club;

import java.util.Scanner;
import java.util.Stack;

public class SWEA_1224_D4_계산기3 {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		for(int i=1; i<=10; i++) {
			int len = scanner.nextInt();
			String data = scanner.next();
			
			String postData = getPostfix(data);
			
			Stack<Integer> stack = new Stack<>();
			for(int k=0; k<postData.length(); k++) {
				char c = postData.charAt(k);
				if(getOrder(c) == 0) {
					stack.push(c - '0');
				}else {
					Integer num2 = stack.pop();  //num1 num2 순서 주의!
					Integer num1 = stack.pop();
					if(c=='+') {
						stack.push(num1+num2);
					}else {
						stack.push(num1*num2);
					}
				}
			}
			// 마지막에 남아있는 것이 결과값
			System.out.println("#"+ i+" " + stack.pop());
		}

	}
	
	public static String getPostfix(String data) {
		String postfix = "";
		Stack<Character> stack = new Stack<>();
		
		for(int k=0; k<data.length(); k++) {
			char c = data.charAt(k);
			switch (getOrder(c)) {
			case 4: //*
			case 3: //+
				while (!stack.isEmpty()) {
					if(getOrder(stack.peek()) >= getOrder(c)) {
						postfix += stack.pop();
					}else {
						break;
					}
				}
				stack.push(c);
				break;
			case 2: // (
				stack.push(c);
				break;
			case 1: // )
				while(stack.peek() != '(') {
					postfix += stack.pop();
				}
				stack.pop(); // ( 버리기
				break;
			default: // 숫자
				postfix += c;
				break;
			}
		}
		// 스택에 남아있는 것 출력
		while (!stack.isEmpty()) {
			postfix += stack.pop();
		}
		return postfix;
	}
	
	public static int getOrder(char c) {
		if(c=='*') {
			return 4;
		}else if (c=='+') {
			return 3;
		}else if (c=='(') {
			return 2;
		}else if (c==')') {
			return 1;
		}else {
			return 0;
		}
	}
	

}
