package algo_basic.hw;

import java.util.Scanner;

/**
 * @author rodash3
 * @since 2020. 1. 29.
 * @see http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=458&sca=99&sfl=wr_subject&stx=%EC%A3%BC%EC%82%AC%EC%9C%84
 * @memory 19MB
 * @time 366ms
 * @caution 기본적으로 순열인데 중복을 허용하기 때문에 visited를 체크할 필요가 없다.
 */

public class JO_1175_주사위던지기2 {

	public static int[] dices;
	public static int N;
	public static int M;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt(); // 주사위를 던진 횟수
		M = scanner.nextInt(); // 주사위 눈의 합
		dices = new int[N];

		game(0);
		scanner.close();
	}

	public static void game(int idx) {
		if (idx == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				sum += dices[i];
			}
			if (sum == M) {
				for (int dice : dices) {
					System.out.print(dice + " ");
				}
				System.out.println();
			}
			return;
		}
		for (int i = 1; i <= 6; i++) {
			dices[idx] = i;
			game(idx + 1);
		}
	}
}
