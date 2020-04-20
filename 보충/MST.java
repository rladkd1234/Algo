import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class MST {
	
	static class Edge implements Comparable<Edge> {
		int x, y;
		int weight;

		public Edge(int x, int y, int weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Edge [x=" + x + ", y=" + y + ", weight=" + weight + "]";
		}

	}

	static int N, E;
	static int[] parent;

	static ArrayList<Edge> list;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		E = sc.nextInt();

		parent = new int[N + 1];
		/**
		 * 1 5 21 1 4 5 2 3 4 2 5 7 2 7 9 3 4 2 3 7 19 4 5 9 5 6 1 5 7 19
		 */
		list = new ArrayList<Edge>();

		makeSet();
		
		for (int i = 0; i < E; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int w = sc.nextInt();

			list.add(new Edge(x, y, w));
		}
		// input

		/*
		 * for(int i = 0; i < E; i++) { System.out.println(list.poll()); }
		 */

		Collections.sort(list);

		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			Edge edge = list.get(i);

			if (find(edge.x) == find(edge.y))
				continue;

			sum += edge.weight;
			union(edge.x, edge.y);
//			System.out.println(edge);
		}

		System.out.println(sum);

	} // end main

	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
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

}
