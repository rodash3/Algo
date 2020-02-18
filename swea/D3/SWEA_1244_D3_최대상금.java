package hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SWEA_1244_D3_최대상금 {

	static Integer[] nums;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= TC; i++) {
			st = new StringTokenizer(br.readLine());
			String num = st.nextToken();
			nums = new Integer[num.length()];
			for (int j = 0; j < num.length(); j++) {
				nums[j] = num.charAt(j) - '0';
			}

			int chance = Integer.parseInt(st.nextToken()); // 교환 횟수
			int cnt = 0; // 교환횟수 카운트

			Integer[] sortedNums = nums.clone();
			Arrays.sort(sortedNums, new Comparator<Integer>() {
				// 내림차순 정렬 -> 교환으로 얻을 수 있는 최댓값
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1.compareTo(o2) * (-1);
				}
			});
			int maxIdx = 0;

			while (chance != cnt) {
				int max = sortedNums[maxIdx];
				int maxCnt = 1;

				if (Arrays.equals(nums, sortedNums)) {
					// 교환 횟수 남았는데 이미 최댓값이 되었음
					// 같은 값이 존재하는가?
					boolean isDup = false;
					for (int k = 0; k < sortedNums.length - 1; k++) {
						if (sortedNums[k] == sortedNums[k + 1]) {
							isDup = true;
							break;
						}
					}
					if (!isDup && (chance-cnt) % 2 == 1) { // 같은 값도 없고 교환횟수도 홀수
						swap(nums.length - 2, nums.length - 1);
					}
					break;
				}

				// 최대값이 몇개인지
				for (int ai = maxIdx; ai < sortedNums.length - 1; ai++) {
					if (sortedNums[ai] == sortedNums[ai + 1]) {
						maxCnt++;
					} else {
						break;
					}
				}

				if (maxCnt == 1) {
					int idx = 0;
					for (int k = 0; k < nums.length; k++) {
						if (nums[k] == max) {
							idx = k;
							break;
						}
					}
					if (maxIdx == idx) {
						maxIdx++;
						continue;
					}
					swap(maxIdx, idx);
					cnt++;
					maxIdx++;
				} else {

						Integer[] change = new Integer[maxCnt];
						for (int k = maxIdx, m = 0; k < nums.length; k++) {
							if (m == maxCnt)
								break;
							if (nums[k] <= max) {
								change[m] = nums[k];
								m++;
							}
						}
						Arrays.sort(change);

						int m = 0;
						int[] idx = new int[maxCnt];
						for (int k = 0; k < nums.length; k++) {
							if(m >= maxCnt) break;
							if (nums[k] == change[m]) {
								idx[m] = k;
								m++;
							}
						}

						m = 0;
						for (int k = nums.length -1; k >=0 ; k--) {
							if(m>=maxCnt) break;
							if(cnt >= chance) break;
							if (nums[k] == max) {
								swap(idx[m++], k);
								cnt++;
							}
						}

				}
			} // while over

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(i).append(" ");
			for(int k=0; k<nums.length; k++)
				sb.append(nums[k]);
			System.out.println(sb);
		}
	}

	public static void swap(int a, int b) {
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}
}
