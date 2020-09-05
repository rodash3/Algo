package baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author rodash3
 * @since 2020. 9. 6.
 * @see https://www.acmicpc.net/problem/2609
 * @memory 14348 KB
 * @time 116 ms
 * @caution
 */
public class BJ_2609_S5_최대공약수와최소공배수 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n1 = scanner.nextInt();
		int n2 = scanner.nextInt();

		List<Integer> list1 = divide(n1); // n1의 소인수분해 결과
		List<Integer> list2 = divide(n2); // n2의 소인수분해 결과

		int multiple = 1; // 최대공배수
		int divisor = 1; // 최소공약수

		for (Integer n : list1) {
			if (list2.contains(n)) {
				// n1의 인수가 n2에도 포함된다면
				multiple *= n; // 최소공배수에 곱하기
				divisor *= n; // 최대공약수에 곱하기
				list2.remove(n);
			} else {
				// n1에만 존재하는 인수 최소공배수에 곱하기
				multiple *= n;
			}
		}
		// n2에 남아있는 인수 최소공배수에 곱하기
		if (!list2.isEmpty()) {
			for (Integer n : list2) {
				multiple *= n;
			}
		}

		scanner.close();
		System.out.println(divisor + "\n" + multiple);
	}

	// n을 소인수분해하여 리스트에 담아 반환 (ex. n=8, list=[2,2,2])
	private static List<Integer> divide(int n) {
		List<Integer> list = new ArrayList<>();
		for (int i = 2; n != 1;) {
			if (n % i == 0) {
				list.add(i);
				n = n / i;
			} else {
				i++;
			}
		}

		return list;
	}
}
