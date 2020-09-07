package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author rodash3
 * @since 2020. 9. 7.
 * @see https://www.acmicpc.net/problem/2661
 * @memory 13788 KB
 * @time 88 ms
 * @caution ���� ���� üũ�� ��ü ������ ��� �˻��ϴ� �ͺ���
 * 			������ �߰��� �� �������� �˻��ؾ� �ð��� �پ���
 */
public class BJ_2661_G4_�������� {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // ����
		
		dfs("", 0, N);
	}
	
	private static void dfs(String sequence, int cur, int N) {
		if(cur == N) {
			// 1,2,3 ������ �߰��ǹǷ� ó�� N�� �����Ѱ� ���� ���� ��
			System.out.println(sequence);
			System.exit(0);
		}
		
		if(isGoodSequence(sequence+"1")){
			dfs(sequence+"1", cur+1, N);
		}
		if(isGoodSequence(sequence+"2")){
			dfs(sequence+"2", cur+1, N);
		}
		if(isGoodSequence(sequence+"3")){
			dfs(sequence+"3", cur+1, N);
		}
	}

	private static boolean isGoodSequence(String sequence) {
		int len = sequence.length();
		for(int i=1; i<=len/2; i++) { // i: �˻��� ����
			// �������� 1�� ���ڰ� �߰� �Ǿ����Ƿ� ���ʸ� �˻�
			if(sequence.substring(len-i)
					.startsWith(sequence.substring(len-i-i, len-i))) {
				// �� ��Ʈ���� �� ��Ʈ������ ���� -> ���� ���� ����
				return false;
			}
		}
		return true;
	}
}
