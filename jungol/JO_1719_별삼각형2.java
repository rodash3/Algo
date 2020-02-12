package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//삼각형의 크기 n(n의 범위는 100 이하의 홀수)과 종류 m(m은 1부터 4사이의 정수)을 입력받는다.
public class JO_1719_별삼각형2 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stringTokenizer.nextToken());
		int m = Integer.parseInt(stringTokenizer.nextToken());
		
		if(n<=0 || n>=100 || m<=0 || m>4 || n%2==0) {
			System.out.println("INPUT ERROR!");
			return;
		}

		int mid = n/2;
		switch (m) {
		case 1:
			for(int i=1; i<=mid+1; i++) {
				for(int j=0; j<i; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			for(int i=mid; i>0; i--) {
				for(int j=0; j<i; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		case 2:
			for(int i=1; i<=mid+1; i++) {
				for(int j=i; j<mid+1; j++) {
					System.out.print(" ");
				}
				for(int j=0; j<i; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			for(int i=1; i<=mid; i++) {
				for(int j=0; j<i; j++) {
					System.out.print(" ");
				}
				for(int j=0; j<=mid-i; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		case 3:
			for(int i=1; i<=mid+1; i++) {
				for(int j=1; j<i; j++) {
					System.out.print(" ");
				}
				for(int j=0; j<2*(mid-i+1)+1; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			for(int i=1; i<=mid; i++) {
				for(int j=i; j<mid; j++) {
					System.out.print(" ");
				}
				for(int j=0; j<2*i+1; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			
			break;
		case 4:
			for(int i=1; i<=mid+1; i++) {
				for(int j=1; j<i; j++) {
					System.out.print(" ");
				}
				for(int j=i; j<=mid+1; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			for(int i=1; i<=mid; i++) {
				for(int j=0; j<mid; j++) {
					System.out.print(" ");
				}
				for(int j=0; j<i+1; j++) {
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
