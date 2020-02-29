package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 2. 29.
 * @see https://www.acmicpc.net/problem/17140 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 20812 KB
 * @time 176 ms
 * @caution R 연산 이후 가장 큰 열 기준으로 모든 열의 크기가 변하고
 * 			C 연산 이후 가장 큰 행 기준으로 모든 행의 크기가 변한다. 
 */
public class BJ_17140_G4_이차열배열과연산 {

	static int r, c, k;
	static int[][] map = new int[100][100];
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()) - 1; // 인덱스 0~ 이므로 -1
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());

		// 배열 입력
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		// 연산
		calculation();
	}

	public static void calculation() {
		int row = 3; // 현재 행의 크기, 초기값 3
		int col = 3; // 현재 열의 크기, 초기값 3
		int time = 0; // 연산 시간
		
		while (true) {
			if(map[r][c] == k) {
				// A[r][c] == k 일 경우 연산 시간 출력
				System.out.println(time);
				return;
			}
			
			if(time > 100) {
				// 연산 시간이 100초를 넘어가면 -1
				System.out.println(-1);
				return;
			}
			
			// R 연산: 모든 행에 대해서 정렬
			if(row >= col) {
				int tempCol = Integer.MIN_VALUE; // 변하는 열 크기
				
				// 각 행에 대해 수행
				for(int i=0; i<row; i++) {
					int[] temp = map[i].clone(); // 행 복사
					Arrays.sort(temp); // 행 정렬
					
					// 정렬된(오름차순) 행을 뒤에서 부터 읽으면서 0 나오기 이전까지 숫자 세어서 list에 담기
					List<Count> list = new ArrayList<>();
					list.add(new Count(temp[temp.length-1], 1));
					int idx = 0;
					for(int j=temp.length-1; j >=0 ;j--) {
						if(j>0 && temp[j-1] == 0)
							break;
						
						if(temp[j-1] == temp[j]) {
							list.get(idx).cnt++;
						}else {
							list.add(new Count(temp[j-1], 1));
							idx++;
						}
					} // 숫자 세기
					Collections.sort(list); // 수의 등장 횟수가 커지는 순, 횟수가 같다면 수가 커지는 순으로 정렬
					
					// 정렬된 결과 배열에 넣기
					for(int j=0, k=0; k<list.size(); k++, j+=2) {
						map[i][j] = list.get(k).num;
						map[i][j+1] = list.get(k).cnt;
					}
					// 크기가 커진 곳에는 0 채우기
					for(int k=list.size()*2; k<row; k++) {
						map[i][k] = 0;
					}
					
					tempCol = Math.max(tempCol, list.size()*2);
				}
				col = tempCol; // 열 변화
			}
			
			// C 연산: 모든 열에 대해서 정렬
			else {
				int tempRow = Integer.MIN_VALUE; // 변하는 행 크기
				
				// 각 열에 대해 수행
				for(int i=0; i<col; i++) {
					int[] temp = new int[100];
					for(int j=0; j<100; j++) {
						temp[j] = map[j][i]; 
					} // 열 복사
					Arrays.sort(temp);
					
					// 정렬된(오름차순) 열을 뒤에서 부터 읽으면서 0 나오기 이전까지 숫자 세어서 list에 담기
					List<Count> list = new ArrayList<>();
					list.add(new Count(temp[temp.length-1], 1));
					int idx = 0;
					for(int j=temp.length-1; j >=0 ;j--) {
						if(j>0 && temp[j-1] == 0)
							break;
						
						if(temp[j-1] == temp[j]) {
							list.get(idx).cnt++;
						}else {
							list.add(new Count(temp[j-1], 1));
							idx++;
						}
					}
					Collections.sort(list);
					
					// 정렬된 결과 배열에 넣기
					for(int j=0, k=0; k<list.size(); k++, j+=2) {
						map[j][i] = list.get(k).num;
						map[j+1][i] = list.get(k).cnt;
					}
					// 크기가 커진 곳에는 0 채우기
					for(int k=list.size()*2; k<col; k++) {
						map[k][i] = 0;
					}
					
					tempRow = Math.max(tempRow, list.size()*2);
				}
				row = tempRow; // 행 변화
			}
			
			time++; // 연산 시간 증가
		}
	}
	
	static class Count implements Comparable<Count>{
		Integer num; // 숫자
		Integer cnt; // 수가 몇번 나왔는지
		
		public Count(Integer num, Integer cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Count o) {
			if(this.cnt.equals(o.cnt)) {
				// 등장 횟수가 같다면 숫자 순으로 정렬
				return this.num.compareTo(o.num);
			}else { // 숫자 등장 횟수 순으로 정렬
				return this.cnt.compareTo(o.cnt);
			}
		}

	}
}
