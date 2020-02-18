package algo_ad.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_11728_S5_배열합치기 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 배열 A의 크기
		int M = Integer.parseInt(st.nextToken()); // 배열 B의 크기
		
		List<Integer> a = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			a.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(a);
		st = new StringTokenizer(br.readLine());
		List<Integer> b = new ArrayList<>();
		while (st.hasMoreTokens()) {
			b.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(b);

		int[] total = new int[N+M];
		
		int ai=0;
		int bi=0;
		for(int i=0; i<N+M; i++) {
			if(ai >= a.size()) {
				total[i] = b.get(bi);
				bi++;
			}else if (bi >= b.size()) {
				total[i] = a.get(ai);
				ai++;
			}else {
				if(a.get(ai)< b.get(bi)) {
					total[i] = a.get(ai);
					ai++;
				}else {
					total[i] = b.get(bi);
					bi++;
				}
			}

		}
		
		StringBuilder sb = new StringBuilder();
		for(int n: total)
			sb.append(n).append(" ");
		
		System.out.println(sb);
	}

}
