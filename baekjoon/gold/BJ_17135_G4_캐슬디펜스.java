package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_17135_G4_캐슬디펜스 {

	static int N, M, D;
	static int[][] map;
	static int max = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 격자판 N*M
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken()); // 궁수의 공격거리(D 이하)

		map = new int[N][M];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		combination(0, 3, new int[3], 0);
		System.out.println(max);
	}

	static void attack(int[] temp) {
		int deadman = 0; // 죽은 병사 수
		List<Enemy> enemies = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 1) {
					enemies.add(new Enemy(r, c, false));
				}
			}
		}

		while (true) {
			// 궁수 한명당 타겟 지정하기
			for (int i = 0; i < temp.length; i++) {
				int archer = temp[i]; // 궁수가 위치한 열
				PriorityQueue<Enemy> targetedEnemies = new PriorityQueue<>();

				for (int j = 0; j < enemies.size(); j++) {
					Enemy enemy = enemies.get(j);

					enemy.d = Math.abs(N - enemy.x) + Math.abs(archer - enemy.y);

					// 거리내에 있으면 사망 가능
					if (enemy.d <= D) {
						targetedEnemies.offer(enemy);
					}
				}

				if (!targetedEnemies.isEmpty()) {
					targetedEnemies.poll().targeted = true;
				}
			}

			for (int i = 0; i < enemies.size(); i++) {
				Enemy e = enemies.get(i);
				if (e.targeted) {
					enemies.remove(i--);
					deadman++;
				} else if (e.x == N - 1) {
					enemies.remove(i--);
				} else {
					e.x++; // 한줄 아래로
				}
			}

			if (enemies.isEmpty()) {
				break; // 병사 없으면 게임 끝
			}
		}
		max = Math.max(deadman, max);
	}

	static void combination(int k, int r, int[] temp, int start) {
		if (k == r) {
//			System.out.println(Arrays.toString(temp));
			attack(temp);
			return;
		}
		for (int i = start; i < M; i++) {
			temp[k] = i;
			combination(k + 1, r, temp, i + 1);
		}

	}

	static class Enemy implements Comparable<Enemy> {
		Integer x;
		Integer y;
		Integer d; // 궁수와의 거리
		boolean targeted;
		
		public Enemy(int x, int y, boolean targeted) {
			super();
			this.x = x;
			this.y = y;
			this.targeted = targeted;
		}

		// 적 가장 가까운 거리, 거리 같을 경우 왼쪽으로 우선순위 주기
		@Override
		public int compareTo(Enemy o) {
			if (this.d.equals(o.d)) {
				return this.y.compareTo(o.y);
			} else {
				return this.d.compareTo(o.d);
			}
		}

	}
}
