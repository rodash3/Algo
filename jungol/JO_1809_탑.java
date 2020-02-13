package algo_basic.hw;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author 서정하
 * @since 2020. 2. 3.
 * @see http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1082&sca=3020
 * @memory 30mb
 * @time 2796ms
 * @caution 필요없는 탑을 순회하면 시간 초과, 필요없는(작은) 탑들을 제거하기 위해 스택을 사용한다.
 *			새로운 타워가 왔을 때 이 타워보다 작은 탑들은 pop으로 버린다.
 */

public class JO_1809_탑 {

	public static void main(String[] args) {
		String src = "5\r\n" + "6 9 5 7 4";
		Scanner scanner = new Scanner(System.in);
		scanner = new Scanner(src);

		int N = scanner.nextInt(); // 탑의 개수
		Stack<Tower> stack = new Stack<>();
		int[] answer = new int[N];

		for (int i = 1; i <= N; i++) {
			Tower tower = new Tower(i, scanner.nextInt());

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

		for (int i = 0; i < N; i++) {
			System.out.print(answer[i] + " ");
		}
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
