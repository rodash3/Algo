package algo_basic.day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JO_1810_백설공주 {

	static int[] dwarfs;
	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		
		dwarfs = new int[9]; 		
		for(int i=0; i<9; i++) {
			dwarfs[i] = Integer.parseInt(bReader.readLine());
		}
		
		combination(0, 7, new int[7], 0);
	}

	public static void combination(int k, int r, int[] temp, int start) {
		if(k==r) {
			int sum = 0;
			for(int t: temp) {
				sum += t;
			}
			if(sum == 100) {
				for(int t: temp)
					System.out.println(t);
			}
		}else {
			for(int i=start; i<dwarfs.length; i++) {
				temp[k] = dwarfs[i];
				combination(k+1, r, temp, i+1);
			}
		}
	}
}
