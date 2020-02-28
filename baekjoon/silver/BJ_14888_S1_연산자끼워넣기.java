package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 2. 29.
 * @see https://www.acmicpc.net/problem/14888 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 14720 KB
 * @time 88 ms
 * @caution
 */
public class BJ_14888_S1_연산자끼워넣기 {

	static int N; // 숫자의 개수
	static int[] nums; // 숫자 배열
	static int MIN = Integer.MAX_VALUE; // 식 최솟값
	static int MAX = Integer.MIN_VALUE; // 식 최댓값
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 숫자 N개
		
		// 숫자 입력
		nums= new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// 연산자 개수 입력
		st = new StringTokenizer(br.readLine());
		int[] ops = new int[N-1];
		int idx = 0;
		for(int i=0; i<4; i++) {
			int j = Integer.parseInt(st.nextToken());
			// 연산자 개수 만큼 인덱스를 중복으로 넣기 - ex) input: 2 0 1 0 -> ops: 0 0 2
			for(int k=0; k<j; k++) {
				ops[idx++] = i;
			}
		}
		
		// nextPermutation으로 연산자의 순서를 정한 후 계산
		nextPermutation(ops);
		System.out.println(MAX);
		System.out.println(MIN);
	}

	// 식 계산
	public static void calculation(int[] nums, int[] ops) {
		for(int i=0; i<N-1; i++) {
			// nums[i+1]에 현재까지 계산 결과 저장
			switch (ops[i]) {
			case 0: // +
				nums[i+1] = nums[i] + nums[i+1];
				break;
			case 1: // -
				nums[i+1] = nums[i] - nums[i+1];
				break;
			case 2: // *
				nums[i+1] = nums[i] * nums[i+1];
				break;
			case 3: // /
				nums[i+1] = nums[i] / nums[i+1];
				break;
			default:
				break;
			}
		}
		
		MIN = Math.min(nums[N-1], MIN);
		MAX = Math.max(nums[N-1], MAX);
	}
	
	// 연산자 순서 만들기
	public static void nextPermutation(int[] src) {
		int i;
		calculation(nums.clone(), src);
		while (true) {
			for (i = src.length - 2; i >= 0; i--) {
				if (src[i] < src[i + 1]) {
					break;
				}
			}
			if(i<0) break;
			int j;
			for (j = src.length - 1; j > i; j--) {
				if (src[i] < src[j]) {
					break;
				}
			}
			int temp = src[i];
			src[i] = src[j];
			src[j] = temp;

			for (int a = i + 1, b = src.length - 1; a < b; a++, b--) {
				temp = src[a];
				src[a] = src[b];
				src[b] = temp;
			}
			calculation(nums.clone(), src);
		}
	}
}
