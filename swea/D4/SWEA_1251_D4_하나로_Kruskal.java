package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author rodash3
 * @since 2020. 4. 9.
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD&categoryId=AV15StKqAQkCFAYD&categoryType=CODE
 * @memory 113,280 kb
 * @time 621 ms
 * @caution Kruskal 알고리즘으로 최소신장트리 구성
 */
public class SWEA_1251_D4_하나로_Kruskal {

	static int N;
	static List<Edge> edges;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=TC; i++) {
			N = Integer.parseInt(br.readLine()); // 섬의 개수
			
			edges = new ArrayList<>();
			// x 좌표
			int[] x = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				x[j] = Integer.parseInt(st.nextToken());
			}
			// y 좌표
			int[] y = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				y[j] = Integer.parseInt(st.nextToken());
			}
			// 환경 부담 세율
			double E = Double.parseDouble(br.readLine());
			
			//makeSet
			parent = new int[N];
			for (int j=0; j < N; j++) {
				parent[j] = j;
			}
			
			// 가중치 계산
			long[] costs = new long[N*(N-1)/2];
			int idx = 0;
			for(int j=0; j<N; j++) {
				for(int k=j+1; k<N; k++) {
					costs[idx] = ((long)(x[j]-x[k])*(x[j]-x[k])) + ((long)(y[j]-y[k])*(y[j]-y[k]));
					edges.add(new Edge(j, k, costs[idx++]));
				}				
			}
			
			Collections.sort(edges);
			
			double cost = 0;
			for(int k=0; k<edges.size(); k++) {
				Edge edge = edges.get(k);
				if (findSet(edge.no1) != findSet(edge.no2)) {
					cost += edge.cost;
					union(edge.no1, edge.no2);
				}
			}
			
			System.out.println("#"+i+" "+Math.round(cost*E));
		}
	}
	
	public static void union(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if (x != y)
			parent[y] = x;
	}

	public static int findSet(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = findSet(parent[x]);
	}
	
	static class Edge implements Comparable<Edge>{
		int no1;
		int no2;
		long cost;
	
		public Edge(int no1, int no2, long cost) {
			this.no1 = no1;
			this.no2 = no2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(cost, o.cost);
		}
	}
}

