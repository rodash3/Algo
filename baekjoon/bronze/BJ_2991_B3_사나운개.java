package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2991_B3_사나운개 {

	public static void main(String[] args) throws IOException {

		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
		int a = Integer.parseInt(stringTokenizer.nextToken()); // 개1 공격
		int b = Integer.parseInt(stringTokenizer.nextToken()); // 개1 쉼
		int c = Integer.parseInt(stringTokenizer.nextToken()); // 개2 공격
		int d = Integer.parseInt(stringTokenizer.nextToken()); // 개2 쉼
		
		stringTokenizer = new StringTokenizer(bReader.readLine());
		int p = Integer.parseInt(stringTokenizer.nextToken()); // 우체부 도착
		int m = Integer.parseInt(stringTokenizer.nextToken()); // 우유배달원 도착
		int n = Integer.parseInt(stringTokenizer.nextToken()); // 신문배달원 도착
		
		if(p%(a+b)<=a && p%(a+b)>=1 && p%(c+d)<=c && p%(c+d)>=1) {
			System.out.println(2);
		}else if ((p%(a+b)<=a && p%(a+b)>=1) || (p%(c+d)<=c && p%(c+d)>=1)) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
		if(m%(a+b)<=a && m%(a+b)>=1 && m%(c+d)<=c && m%(c+d)>=1) {
			System.out.println(2);
		}else if ((m%(a+b)<=a && m%(a+b)>=1) || (m%(c+d)<=c && m%(c+d)>=1)) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
		if(n%(a+b)<=a && n%(a+b)>=1 && n%(c+d)<=c && n%(c+d)>=1) {
			System.out.println(2);
		}else if ((n%(a+b)<=a && n%(a+b)>=1) || (n%(c+d)<=c && n%(c+d)>=1)) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}

	}

}
