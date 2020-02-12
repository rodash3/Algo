package Solving_Club;

import java.util.Scanner;
import java.util.Stack;

public class SWEA_1224_D4_계산기3 {

	static String src = "113\r\n" + 
			"(9+(5*2+1)+(3*3*7*6*9*1*7+1+8*6+6*1*1*5*2)*4*7+4*3*8*2*6+(7*8*4*5)+3+7+(2+6+5+1+7+6+7*3*(6+2)+6+6)*2+4+2*2+4*9*3)\r\n" + 
			"85\r\n" + 
			"(4+8+4*(8*5*(7*(6*8)+3+(6+(3+7+1*7*5*4)*3)*2*3+5)+6+7*7)*4+2+9*4+7+2*3*(7*6*1*8)+9+9)\r\n" + 
			"97\r\n" + 
			"(9*5+7+8+6+3+9*2+1+7+4+3+9*3*1+4*(4+4*3*1+9*3+(9*5)*1*7*8+2+8+8*7+9*4*9)+(1+1*8+8*9*7+1*4*5*2*5))\r\n" + 
			"89\r\n" + 
			"((3*1*4+6*3+8+4+5+4+2*1+5+3*4)*1+1+(3*2*2+9+5*4*6*9+9+4+1*8+9)*4*8+9+3*7+9*6*9*5+8+3*8+1)\r\n" + 
			"125\r\n" + 
			"(2+(6*5+6+5+3*9+6+2+8*2+2)+6+2*2+2*5*1*2+1*8+1*(4+7*5+8+9+7+3*8*5)+(2+9+5*4*4+1+3*9*6*4*5+(5*(3+4)*9+8+7+9*2)+7+7+2)+8+2+7+5)\r\n" + 
			"113\r\n" + 
			"(8+8*6+3*9*8*7*6*3+5*(7*6*6+3*5+2*4*9*3+5+2+1*4)*1*7+6*8+9+3+2+8*3+8*(2+6*9+2*2*7+8*1*2+9*3+1+5)*(5*8+4*1*2*4*2))\r\n" + 
			"115\r\n" + 
			"(7+9*2+6+5*7+1*7*(9+8+6)*1*2+7+5*9*6*3+4*8*9*6*1*3+7*1+2+5+1*4*9+6*4+7*1*2*4*2+3+((3*4+9+7*1+7+5+3*7*1*7+8*3*8)+7))\r\n" + 
			"99\r\n" + 
			"(9*4+(1+5*7*8+9+1+2)+5+(6*(7+4*5*2+4+8*4+7)+9)*1*3*1+1*2*8+3+(2+9*(1*5*9+7*1+1+7+3*2))+1+3*7*8+9*6)\r\n" + 
			"75\r\n" + 
			"(2*2+((7+8*8*6+(6*6)*7+7*1)*5)*7+3+1*5+1*8*4+(9+6+(7*5+3+1*8*8*9+4+7+9)*3))\r\n" + 
			"117\r\n" + 
			"(8+6*9+2*3+4+2+(6+9+3+7*5*1+2+2+2)*9+4+6*1+6*4+7+7*2+5+2*6*(8*9*8*6+4*2)*5+5*8*3+(6+1+3+3*8*1*2*1+5+6)+1+5+4*7*1*3+1)\r\n";
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		scanner = new Scanner(src);
		
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
