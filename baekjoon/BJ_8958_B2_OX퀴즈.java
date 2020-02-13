package bronze;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_8958_B2_OX퀴즈 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int i = 1; i <= TC; i++) {
			int answer = 0;
			String quizString = br.readLine();
			
			int o = 0; //연속 O인 횟수
			for(int j=0; j<quizString.length(); j++) {
				char c = quizString.charAt(j);
				
				if(c=='O') {
					o++;
					answer += o;
				}else { //c='X'
					o = 0;
				}
			}

			System.out.println(answer);
		}
	}
}
