package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//삼각형의 크기 n(n의 범위는 100 이하의 자연수)과 종류 m(m은 1부터 3사이의 자연수)을 입력받는다.
public class JO_1523_별삼각형1 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stringTokenizer.nextToken());
		int m = Integer.parseInt(stringTokenizer.nextToken());
		
		if(n<=0 || n>100 || m<=0 || m>3) {
			System.out.println("INPUT ERROR!");
			return;
		}
		
		switch (m) {
		case 1:
			for(int i=1; i<=n; i++) {
				for(int j=0; j<i; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		case 2:
			for(int i=n; i>0; i--) {
				for(int j=0; j<i; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		case 3:
			for(int i=1; i<=n; i++) {
				for(int j=0; j<n-i; j++) {
					System.out.print(" ");
				}
				for(int j=0; j<2*i-1; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		default:
			break;
		}
		
	}

}
