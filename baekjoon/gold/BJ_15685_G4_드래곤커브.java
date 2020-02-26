package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 2. 26.
 * @see https://www.acmicpc.net/problem/15685 (백준 문제집> 삼성 SW 역량 테스트 기출 문제)
 * @memory 13528 KB
 * @time 88 ms
 * @caution 드래곤 커브의 규칙 파악하기 (K 세대 커브의 방향은 K-1세대의 방향 역순으로 +1)
 * 			ex) 2세대까지 각 선분의 방향이 시작점에서 끝점으로 볼때 0 1 2 1 이었다면 3세대 방향은 2 3 2 1로 진행됨
 */
public class BJ_15685_G4_드래곤커브 {

	static boolean[][] map = new boolean[101][101]; // 격자판
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 드래곤 커브의 개수
		
		// 각 리스트에 드래곤 커브를 담음
		List<Dot>[] list = new List[N];
		for(int i=0; i<list.length; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			// 리스트에 0세대의 시작점과 끝점을 담는다
			list[i].add(new Dot(x, y, d)); // 시작점
			map[x][y] = true; // 점을 담으면서 격자판에 표시
			if(d == 0) {
				//0: x좌표가 증가하는 방향 (→)
				x += 1;
			}else if (d == 1) {
				//1: y좌표가 감소하는 방향 (↑)
				y += -1;
			}else if (d == 2) {
				//2: x좌표가 감소하는 방향 (←)
				x += -1;
			}else {
				//3: y좌표가 증가하는 방향 (↓)
				y += 1;
			}
			list[i].add(new Dot(x, y, d)); // 끝점
			map[x][y] = true;
			// 1세대 이상 점들 담기
			for(int j=0; j<g; j++) {
				generateCurve(list[i]);
			}
		}// 드래곤 커브 생성 완료
		
		// 각 점에서 정사각형을 이루는 점이 모두 true이면 개수 증가
		int count = 0;
		for(int j=0; j<100; j++) {
			for(int k=0; k<100; k++) {
				if(map[j][k] && map[j][k+1] && map[j+1][k] && map[j+1][k+1]) {
					count++;
				}
			}
		}
		
		System.out.println(count);
	}
	
	// 1세대 이상 커브 만들때 (0세대에는 적용X)
	private static void generateCurve(List<Dot> list) {
		int before_size = list.size(); // 이전 세대까지 저장한 선분 개수
		int end_x = list.get(before_size-1).x; // 끝점 x좌표 (초기값: 이전 세대 끝점)
		int end_y = list.get(before_size-1).y; // 끝점 y좌표
		
		for(int i=before_size-1; i>0; i--) {
			int d = (list.get(i).d + 1)%4;
			if(d == 0) {
				//0: x좌표가 증가하는 방향 (→)
				end_x += 1;
			}else if (d == 1) {
				//1: y좌표가 감소하는 방향 (↑)
				end_y += -1;
			}else if (d == 2) {
				//2: x좌표가 감소하는 방향 (←)
				end_x += -1;
			}else {
				//3: y좌표가 증가하는 방향 (↓)
				end_y += 1;
			}
			list.add(new Dot(end_x, end_y, d));
			map[end_x][end_y] = true; // 점을 담으면서 격자판에 표시
		}
	}

	static class Dot{
		int x;
		int y;
		int d; //방향
		
		public Dot(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}

	}
}
