package algo_ad.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 재귀는 사전순으로 돌지 않는다, 배치된 순서로 돈다.
public class BJ_10972_S4_다음순열 {

	static int[] src;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		src = new int[N];
		for(int i=0; i<src.length; i++) {
			src[i] = Integer.parseInt(stringTokenizer.nextToken());
		}

		int i;
		for(i=src.length-2; i>=0; i--) {
			if(src[i] < src[i+1]) {
				break;
			}
		}
		if(i<0) {
			System.out.println(-1);
			return;
		}
		int j;
		for(j=src.length-1; j>i; j--) {
			if(src[i] < src[j]) {
				break;
			}
		}
		int temp = src[i];
		src[i] = src[j];
		src[j] = temp;
		
		for(int a=i+1, b=src.length-1; a<b; a++,b--) {
			temp = src[a];
			src[a] = src[b];
			src[b] = temp;
		}
		for(int n: src)
			System.out.print(n+" ");
	}

}
