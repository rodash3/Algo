package algo_basic.day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1231_D4_중위순회 {

	static void inOrder(Node node) {
		if (node == null) {
			return;
		}
		
		inOrder(node.l);
		System.out.print(node.v);
		inOrder(node.r);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 1; i <= 10; i++) {
			int N = Integer.parseInt(bReader.readLine()); // 트리의 정점의 총 개수
			Node[] nodes = new Node[N + 1];

			// 노드 초기화
			StringTokenizer sTokenizer;
			for (int j = 1; j <= N; j++) {
				nodes[j] = new Node("");
			}

			// 트리 만들기
			for (int j = 1; j <= N; j++) {
				sTokenizer = new StringTokenizer(bReader.readLine());
				sTokenizer.nextToken(); // 정점 번호 버림
				nodes[j].v = sTokenizer.nextToken();
				if (sTokenizer.hasMoreTokens()) {
					nodes[j].l = nodes[Integer.parseInt(sTokenizer.nextToken())];
				}
				if (sTokenizer.hasMoreTokens()) {
					nodes[j].r = nodes[Integer.parseInt(sTokenizer.nextToken())];
				}
			}

//			for(Node n: nodes)
//				System.out.println(n);
			System.out.print("#" + i + " ");
			inOrder(nodes[1]);
			System.out.println();

		}

	}

	static class Node {
		Node l;
		String v;
		Node r;

		public Node(String v) {
			super();
			this.v = v;
		}

		@Override
		public String toString() {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("[" + v + "-(").append(l == null ? "n" : l.v).append(", ").append(r == null ? "n" : r.v)
					.append(")]");
			return sBuilder.toString();
		}
	}

}
