package algo_ad.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1240_D3_단순2진암호코드 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());

		String[] nums = {"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};
		int[] deciNums = new int[8];
		
		for (int i = 1; i <= TC; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			boolean done = false;
			for (int j = 0; j < N; j++) {
				String string = br.readLine();
				if(string.lastIndexOf("1") > 0 && !done) {
					int end = string.lastIndexOf("1") + 1;
					String code = string.substring(end-56 , end);
					int idx=0;
					for(int k=0; k<code.length(); k=k+7) {
						for(int n=0; n<nums.length; n++) {
							if(code.substring(k, k+7).equals(nums[n])) {
								deciNums[idx++] = n;
							}
						}
					}
//					System.out.println(Arrays.toString(deciNums));
					// 검증코드 확인
					int codeCheck = (deciNums[0] + deciNums[2] + deciNums[4] +deciNums[6])*3 + deciNums[1] + deciNums[3] + deciNums[5] +deciNums[7];
					if(codeCheck %10 ==0) {
						int sum = 0;
						for(int d:deciNums) {
							sum += d;
						}
						System.out.println("#" + i + " " +sum);
					}else {
						System.out.println("#" + i + " " +0);
					}
					done = true;
				}else {
					continue; // 암호코드가 없음
				}
			}
		}

	}

}
