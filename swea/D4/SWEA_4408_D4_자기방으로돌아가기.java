package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4408_D4_자기방으로돌아가기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=TC; i++) {
			int N = Integer.parseInt(br.readLine()); // 돌아가야할 학생 수
			int[] rooms = new int[201];

			for(int j=0; j<N; j++) {
				StringTokenizer st= new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				if(a%2==1) a++;  // 현재방
				int b = Integer.parseInt(st.nextToken());
				if(b%2==1) b++;  // 돌아가야할 방
				
				if(a > b) { // 현재방을 항상 돌아갈 방 번호보다 작게 만들어줌
					int temp = a;
					b = a;
					a = temp;
				}
				
				for(int k=a/2; k<=b/2; k++) {
					rooms[k]++;
				}
			}
			
			int answer = 0;
			for(int j=0; j<rooms.length; j++) {
				if(rooms[j] > answer)
					answer = rooms[j];
			}
			
			System.out.println("#" + i + " " +answer);
		}

	}

}
