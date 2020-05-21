package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 5. 21.
 * @see https://www.acmicpc.net/problem/1062
 * @memory 15816 KB
 * @time 232 ms
 * @caution 시간 초과 유의 - 비트마스크 또는 boolean 배열 이용
 */
public class BJ_1062_G4_가르침 {

	static List<Character> option; // 주어진 단어중 antic 제외한 글자
	static boolean[] alpha = new boolean[26]; // true: 해당 알파벳을 배웠음
	static int N, K, MAX;
	static String[] words; // 단어 배열
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 단어 개수
		K = Integer.parseInt(st.nextToken()); // 글자 수
		
		words = new String[N];
		for(int i=0; i<N; i++) {
			words[i] = br.readLine();
		}//input over
		
		//anta~tica -> a n t i c 5개 필수
		MAX = 0;
		if(K >= 5) { // K < 5 이면 어떤 단어도 읽을 수 없다
			// a n t i c 항상 배우기
			alpha['a'-'a'] = true;
			alpha['n'-'a'] = true;
			alpha['t'-'a'] = true;
			alpha['i'-'a'] = true;
			alpha['c'-'a'] = true;
			
			// antic를 제외한 배워야할 글자 찾기
			option = new ArrayList<>();
			
			for(String word: words) {
				//~ 부분만 글자 추출
				for(int i=4; i<word.length()-4; i++) {
					if(!alpha[word.charAt(i)-'a'] && !option.contains(word.charAt(i))) {
						option.add(word.charAt(i));
					}
				}
			}//for each over
			
			// K가 전체 글자 수보다 크거나 같으면 N개를 모두 읽을 수 있다
			if(option.size() <= K-5) MAX = N;
			// 그렇지 않으면 조합을 통해 일부 단어만 읽을 수 있다
			else combination(0, 0);
		}
		
		System.out.println(MAX);
	}//main
	
	private static void max() {
		int cnt = N; // 현재 배운 글자로 읽을 수 있는 단어 수
		
		for(String word: words) {
			if(cnt <= MAX) break;
			//~ 부분만 글자 추출
			for(int i=4; i<word.length()-4; i++) {
				// 읽을 수 없는 경우
				if(!alpha[word.charAt(i) - 'a']) {
					cnt--;
					break;
				}
			}
		}
		
		MAX = Math.max(cnt, MAX); // 최대로 읽을 수 있는 단어 수 설정
	}
	
	// 조합을 통해 배울 글자 K-5개 뽑기 (antic 제외)
	private static void combination(int k, int start) {
		if(K-5 == k) {
			max();
		}else {
			for(int i=start; i<option.size(); i++) {
				alpha[option.get(i) - 'a'] = true;
				combination(k+1, i+1);
				alpha[option.get(i) - 'a'] = false;
			}
		}
	}

}