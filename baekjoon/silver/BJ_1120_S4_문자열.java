package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1120_S4_문자열 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());		
		String a = stringTokenizer.nextToken();
		String b = stringTokenizer.nextToken();
		
		int dif = b.length() - a.length() + 1;
		int[] difs = new int[dif];
		for(int i=0; i<dif; i++) {
			for(int j=i, k=0; k<a.length(); j++, k++) {
				if(!(a.charAt(k) == b.charAt(j))) {
					difs[i]++;
				}
			}
		}
		
		//최소 차이
		int min = difs[0];
		for(int k=1; k<difs.length; k++) {
			if(difs[k] < min)
				min = difs[k];
		}
		
		System.out.println(min);
	}

}
