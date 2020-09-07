package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author rodash3
 * @since 2020. 9. 7.
 * @see https://www.acmicpc.net/problem/2661
 * @memory 13788 KB
 * @time 88 ms
 * @caution 좋은 수열 체크시 전체 수열을 계속 검사하는 것보다
 * 			마지막 추가된 수 기준으로 검사해야 시간이 줄어든다
 */
public class BJ_2661_G4_좋은수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 길이
		
		dfs("", 0, N);
	}
	
	private static void dfs(String sequence, int cur, int N) {
		if(cur == N) {
			// 1,2,3 순서로 추가되므로 처음 N에 도달한게 가장 작은 수
			System.out.println(sequence);
			System.exit(0);
		}
		
		if(isGoodSequence(sequence+"1")){
			dfs(sequence+"1", cur+1, N);
		}
		if(isGoodSequence(sequence+"2")){
			dfs(sequence+"2", cur+1, N);
		}
		if(isGoodSequence(sequence+"3")){
			dfs(sequence+"3", cur+1, N);
		}
	}

	private static boolean isGoodSequence(String sequence) {
		int len = sequence.length();
		for(int i=1; i<=len/2; i++) { // i: 검사할 길이
			// 마지막에 1개 문자가 추가 되었으므로 뒤쪽만 검사
			if(sequence.substring(len-i)
					.startsWith(sequence.substring(len-i-i, len-i))) {
				// 뒤 스트링이 앞 스트링으로 시작 -> 같은 수열 포함
				return false;
			}
		}
		return true;
	}
}
