package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author rodash3
 * @since 2020. 3. 14.
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWqU0zh6rssDFARG
 * @memory 116860 KB
 * @time 753 ms
 * @caution
 */
public class SWEA_7701_D4_염라대왕의이름정렬 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=TC; i++) {
			int N = Integer.parseInt(br.readLine()); // 이름 개수
			String[] names = new String[N]; // 이름 담는 배열
			// 이름 데이터 입력
			for(int j=0; j<N; j++) {
				names[j] = br.readLine();
			}
			// 이름 정렬 - 이름 길이순, 같은 길이면 사전 순
			Arrays.sort(names, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					Integer leng1 = o1.length();
					Integer leng2 = o2.length();
					if(leng1.equals(leng2)) return o1.compareTo(o2);
					return leng1.compareTo(leng2);
				}
			});
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(i).append("\n");
			
			String temp="";
			for(String s: names) {
				// 중복 제거 해서 sb에 붙이기
				if(!temp.equals(s)) {
					sb.append(s).append("\n");
				}
				temp = s;
			}
			// 결과 출력 - 마지막 \n 제거
			String result = sb.toString().substring(0, sb.length()-1);
            System.out.println(result);
		}
	}

}
