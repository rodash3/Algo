package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 2. 27.
 * @see https://www.acmicpc.net/problem/14891 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 13208 KB
 * @time 80 ms
 * @caution 지문을 잘 이해하기 - 톱니바퀴가 차례로 회전하면서 변한 상태에서 맞닿은 극이 다를 경우 톱니가 회전하는 것이 아니라,
 * 			회전하기 전에 톱니바퀴의 상태를 보고 맞닿은 극이 다를경우 각각 회전하는 것
 */
public class BJ_14891_S1_톱니바퀴 {

	static String[] gears = new String[4]; // 톱니바퀴 상태
	static Queue<gear> queue = new LinkedList<>();
	static List<Integer>[] graph = new List[4];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		for(int i=0; i<4; i++) {
			gears[i] = br.readLine();
			
			// 인접한 톱니바퀴의 인덱스를 담는 리스트
			graph[i] = new ArrayList<>();
			if(i-1 >= 0) {
				graph[i].add(i-1);
			}
			if(i+1 <4) {
				graph[i].add(i+1);
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 회전 횟수
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int rotateGear = Integer.parseInt(st.nextToken());
			int rotateDir = Integer.parseInt(st.nextToken());
			
			// 톱니바퀴 번호는 1~4로 주어지지만, gears 인덱스는 0~3이기 때문에 -1
			queue.offer(new gear(rotateGear-1, rotateDir));
			// 회전 톱니바퀴를 큐에 먼저 넣은 후 회전
			rotate(new boolean[4], gears.clone());
		}
		
		// 점수 구하기
		int sum = 0;
		for(int j=0; j<4; j++) {
			if(gears[j].charAt(0) == '1') {
				sum += Math.pow(2, j);
			}
		}
		System.out.println(sum);
	}
	
	// 인접한 톱니바퀴의 회전
	public static void rotate(boolean[] visited, String[] tempgears) {
		
		while (!queue.isEmpty()) {
			gear front = queue.poll();
			visited[front.num] = true;
			if(front.dir == 1) { // 시계방향 회전
				gears[front.num] = clockwiseRotate(gears[front.num]);
			}else { // 반시계방향 회전
				gears[front.num] = counterclockwiseRotate(gears[front.num]);
			}
			
			// 인접한 톱니바퀴가 회전할 것인지
			for(int i=0; i<graph[front.num].size(); i++) {
				int nextGearNum = graph[front.num].get(i); // 인접 톱니바퀴의 인덱스
				
				if(!visited[nextGearNum]) {
					if(front.num < nextGearNum) { // front의 오른쪽 기어
						// 다른 극이라 회전하는 경우
						if(tempgears[front.num].charAt(2) != tempgears[nextGearNum].charAt(6)) {
							queue.offer(new gear(nextGearNum, front.dir*(-1)));
							visited[nextGearNum] = true;
						}
					}else { // front의 왼쪽 기어
						// 다른 극이라 회전하는 경우
						if(tempgears[front.num].charAt(6) != tempgears[nextGearNum].charAt(2)) {
							queue.offer(new gear(nextGearNum, front.dir*(-1)));
							visited[nextGearNum] = true;
						}
					}
				}

			}
		}
	}

	// 시계방향 회전
	public static String clockwiseRotate(String str) {
		return str.charAt(str.length()-1) + str.substring(0, str.length()-1);
	}
	
	// 반시계방향 회전
	public static String counterclockwiseRotate(String str) {
		return str.substring(1) + str.charAt(0);
	}
	
	static class gear {
		int num; // 회전하는 톱니바퀴 번호
		int dir; // 회전 방향
		
		public gear(int num, int dir) {
			super();
			this.num = num;
			this.dir = dir;
		}
	}
}
