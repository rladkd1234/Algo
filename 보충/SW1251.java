import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SW1251 {

	static int T, N;
	static double E;
	static int[][] graph;
	static int[] parent;
	static long[][] input;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {

			N = sc.nextInt();
			parent = new int[N];
			input = new long[N][2];

			for (int i = 0; i < N; i++) {
				input[i][0] = sc.nextLong();
			}

			for (int i = 0; i < N; i++) {
				input[i][1] = sc.nextLong();
			}

			E = sc.nextDouble();

//			System.out.println(Arrays.deepToString(input));

			PriorityQueue<Edge> list = new PriorityQueue<Edge>();

			for (int e = 0; e < N; e++) {
				for (int e2 = e + 1; e2 < N; e2++) {
				/*	System.out.println("e : " + e + "e2: " + e2 + "wieght : "
							+ E * ((input[e][0] - input[e2][0]) * (input[e][0] - input[e2][0]))
							+ (input[e][1] - input[e2][1]) * (input[e][1] - input[e2][1]));*/
					list.offer(new Edge(e, e2, ((input[e][0] - input[e2][0]) * (input[e][0] - input[e2][0]))
							+ (input[e][1] - input[e2][1]) * (input[e][1] - input[e2][1])));
				}
			}

			for (int i = 0; i < N; i++)
				makeSet(i);
//			System.out.println(Arrays.toString(parent));

			double sum = 0;

			int cnt = 0;
			while(!list.isEmpty()){
				if(cnt == N - 1)
					break;
				Edge edge = list.poll();

				if (find(edge.x) == find(edge.y))
					continue;

				union(edge.x, edge.y);
				sum += edge.weight;
				cnt++;
//				System.out.println(edge);
//				System.out.println(Arrays.toString(parent));
			}
			System.out.println("#" + tc + " " + Math.round(E * sum));
		}
	} // end main

	static void makeSet(int i) {
		parent[i] = i;
	}

	static int find(int x) {
		if (parent[x] == x)
			return x;

		int nx = find(parent[x]);
		parent[x] = nx;
		return nx;
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return;
		parent[y] = x;

	}

	static class Edge implements Comparable<Edge> {
		int x, y;
		double weight;

		public Edge(int x, int y, double weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Double.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Edge [x=" + x + ", y=" + y + ", weight=" + weight + "]";
		}

	}

}
