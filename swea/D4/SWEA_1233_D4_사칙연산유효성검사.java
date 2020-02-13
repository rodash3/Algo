package algo_basic.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1233_D4_사칙연산유효성검사 {

	public static void main(String[] args) throws IOException {

        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
 
        for (int i = 1; i <= 10; i++) {
            int N = Integer.parseInt(bReader.readLine()); // 정점 개수
            int answer = 1;
            for (int j = 0; j < N; j++) {
                StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
                stringTokenizer.nextToken(); // 정점 번호 버림
                if (stringTokenizer.countTokens() >= 2) { // 자식이 존재 --> 연산자여야함
                    char c = stringTokenizer.nextToken().charAt(0);
                    if(c - '0' >= 0) {
                        answer = 0;
                    }
                } else { // 자식 없음, 단말 노드 --> 숫자여야함
                    char c = stringTokenizer.nextToken().charAt(0);              
                    if(c - '0' < 0) {
                        answer = 0;
                    }
                }
            }
            System.out.println("#"+i+" "+answer);
        }
	}

}
