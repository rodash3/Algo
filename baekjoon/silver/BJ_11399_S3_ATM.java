package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11399_S3_ATM {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int people = Integer.parseInt(br.readLine());

		int [] wait = new int[people];
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

		for (int i = 0; i < people; i++) {
			wait[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		
		Arrays.sort(wait);
		int answer = 0;
		for(int i=0; i<people; i++) {
			answer += wait[i];
			if(i<people-1) wait[i+1] += wait[i];
		}
		
		System.out.println(answer);
	}

}
