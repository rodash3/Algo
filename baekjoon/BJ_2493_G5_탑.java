package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//메모리 초과로 스캐너, sysout 대신 버퍼드리더, 스트링빌더 사용
public class BJ_2493_G5_탑 {

	public static void main(String[] args) throws IOException {
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bReader.readLine());
		Stack<Tower> stack = new Stack<>();
		int[] answer = new int[N];

        StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());		
		for (int i = 1; i <= N; i++) {
			Tower tower = new Tower(i, Integer.parseInt(stringTokenizer.nextToken()));

			while (!stack.isEmpty()) {
				Tower tempTower = stack.peek();
				if (tempTower.height > tower.height) {
					answer[i - 1] = tempTower.index;
					break;
				} else {
					stack.pop();
				}
			}
			stack.push(tower);
		}

        StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
            sb.append(answer[i] + " ");
		}
        System.out.println(sb);
	}

	static class Tower {
		int index;
		int height;

		public Tower(int index, int height) {
			super();
			this.index = index;
			this.height = height;
		}
	}
}
