package algo_basic.day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1229_D3_암호문2 {

	public static void main(String[] args) throws IOException {

		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=1; i<=10; i++) {
			int N = Integer.parseInt(bReader.readLine()); // 원본 암호문의 길이
			List<Integer> list = new ArrayList<Integer>();
			StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
			while (stringTokenizer.hasMoreTokens()) {
				list.add(Integer.parseInt(stringTokenizer.nextToken()));
			}

			int opNum = Integer.parseInt(bReader.readLine()); // 명령문 개수
			stringTokenizer = new StringTokenizer(bReader.readLine());
			for(int j=0; j<opNum; j++) {
				if(stringTokenizer.nextToken().equals("I")) {
					int x = Integer.parseInt(stringTokenizer.nextToken());
					int y = Integer.parseInt(stringTokenizer.nextToken());
					
					for(int k=0; k<y; k++) {
						list.add(x+k, Integer.parseInt(stringTokenizer.nextToken()));
					}
					
				}else { // D
					int x = Integer.parseInt(stringTokenizer.nextToken());
					int y = Integer.parseInt(stringTokenizer.nextToken());
					
					for(int k=1; k<=y; k++) {
						list.remove(x);
					}
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(i).append(" ");
			for(int k=0; k<10; k++) {
				sb.append(list.get(k)).append(" ");
			}
			System.out.println(sb);
		}	
		
	}

}
