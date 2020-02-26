package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 2. 26.
 * @see https://www.acmicpc.net/problem/14889 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 26976 KB
 * @time 260 ms
 * @caution
 */
public class BJ_14889_S3_스타트와링크 {

	static int N; // N명
	static int[][] ability; // 능력치 Sij
	static int MIN = Integer.MAX_VALUE; // 각 팀의 능력치 차이의 최솟값
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		// 능력치 데이터 입력
		ability = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				ability[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		combination(1, N/2, new int[N/2], 1);
		System.out.println(MIN);
	}
	
	// 각 팀의 능력치 계산
	public static void calAbility(int[] team1) {
		boolean[] isInTeam1 = new boolean[N]; // team1에 누가 포함되어있는지

		// 팀1의 능력치 계산
		int ability1 = 0;
		for(int i=0; i<team1.length; i++) {
			isInTeam1[team1[i]] = true;
			for(int j=i+1; j<team1.length; j++) {
				ability1 += ability[team1[i]][team1[j]] + ability[team1[j]][team1[i]];
			}
		}
		
		// 팀2 구성
		int[] team2 = new int[N/2];
		int k=0; // team1에 없는 사람 team2에 넣기
		for(int i=0; i<isInTeam1.length; i++) {
			if(!isInTeam1[i]) team2[k++] = i;
		}
		
		// 팀 2의 능력치 계산
		int ability2 = 0;
		for(int i=0; i<team2.length; i++) {
			for(int j=i+1; j<team2.length; j++) {
				ability2 += ability[team2[i]][team2[j]] + ability[team2[j]][team2[i]];
			}
		}
		
		// 최솟값 취하기
		MIN = Math.min(Math.abs(ability1-ability2), MIN);
	}

	// 팀1을 만드는 조합 - 팀1: 0을 무조건 포함하는 조합, 팀2: 팀1 외 나머지로 이루어짐
	public static void combination(int k, int r, int[] temp, int start) {
		if(k==r) {
			temp[0] = 0;
			calAbility(temp);
		}else {
			for(int i=start; i<N; i++) {
				temp[k] = i;
				combination(k+1, r, temp, i+1);
			}
		}
		
	}
}
