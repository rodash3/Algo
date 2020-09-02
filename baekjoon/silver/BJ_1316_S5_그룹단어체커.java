package quest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author rodash3
 * @since 2020. 9. 2.
 * @see https://www.acmicpc.net/problem/1316
 * @memory 13036 KB
 * @time 80 ms
 * @caution
 */
public class BJ_1316_S5_�׷�ܾ�üĿ {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			String word = br.readLine();
			boolean[] alpha = new boolean[26]; // ���� ���� üũ
			
			char[] arr = word.toCharArray();
			for(int k=0; k<arr.length; k++) {
				if(k == arr.length-1) {
					if(!alpha[arr[k]-'a']) {
						// ������ ���ڱ��� �ٽ� �������� ������ �׷�ܾ�
						cnt++;
					}
				}else {
					if(alpha[arr[k]-'a']) {
						// ������ ���� ���ڰ� �ٽ� ������ �׷�ܾ �ƴ�
						break;
					}else if(arr[k] != arr[k+1] && !alpha[arr[k]-'a']) {
						// �޹��ڿ� �ٸ��鼭(���� ������ ��) ó�� ���� �ܾ�
						alpha[arr[k]-'a'] = true;
					}
				}
			}
		}
		
		System.out.println(cnt);
	}
}
