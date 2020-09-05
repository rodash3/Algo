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
public class BJ_2609_S5_�ִ��������ּҰ���� {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n1 = scanner.nextInt();
		int n2 = scanner.nextInt();

		List<Integer> list1 = divide(n1); // n1�� ���μ����� ���
		List<Integer> list2 = divide(n2); // n2�� ���μ����� ���

		int multiple = 1; // �ִ�����
		int divisor = 1; // �ּҰ����

		for (Integer n : list1) {
			if (list2.contains(n)) {
				// n1�� �μ��� n2���� ���Եȴٸ�
				multiple *= n; // �ּҰ������ ���ϱ�
				divisor *= n; // �ִ������� ���ϱ�
				list2.remove(n);
			} else {
				// n1���� �����ϴ� �μ� �ּҰ������ ���ϱ�
				multiple *= n;
			}
		}
		// n2�� �����ִ� �μ� �ּҰ������ ���ϱ�
		if (!list2.isEmpty()) {
			for (Integer n : list2) {
				multiple *= n;
			}
		}

		scanner.close();
		System.out.println(divisor + "\n" + multiple);
	}

	// n�� ���μ������Ͽ� ����Ʈ�� ��� ��ȯ (ex. n=8, list=[2,2,2])
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
