package quest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author rodash3
 * @since 2020. 9. 2.
 * @see https://www.acmicpc.net/problem/1316
 * @memory 13036 KB
 * @time 80 ms
 * @caution
 */
public class BJ_1316_S5_그룹단어체커 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			String word = br.readLine();
			boolean[] alpha = new boolean[26]; // 나온 문자 체크
			
			char[] arr = word.toCharArray();
			for(int k=0; k<arr.length; k++) {
				if(k == arr.length-1) {
					if(!alpha[arr[k]-'a']) {
						// 마지막 문자까지 다시 나온적이 없으면 그룹단어
						cnt++;
					}
				}else {
					if(alpha[arr[k]-'a']) {
						// 이전에 나온 문자가 다시 나오면 그룹단어가 아님
						break;
					}else if(arr[k] != arr[k+1] && !alpha[arr[k]-'a']) {
						// 뒷문자와 다르면서(연속 문자의 끝) 처음 나온 단어
						alpha[arr[k]-'a'] = true;
					}
				}
			}
		}
		
		System.out.println(cnt);
	}
}
