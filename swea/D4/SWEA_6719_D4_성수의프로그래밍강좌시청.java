package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_6719_D4_성수의프로그래밍강좌시청 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=TC; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			double skill = 0;
			int[] lec = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				lec[j] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(lec);
			
			for(int j=N-K; j<N; j++) {
				skill = (double)(lec[j] + skill)/2;
			}
			
			System.out.println("#" + i + " "+skill);
		}
	}

}
