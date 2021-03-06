import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
7 11
0 1 9
0 2 5
0 5 19
2 3 1
2 5 15
3 0 2
3 1 7
3 5 17
3 4 8
4 6 4
6 1 3
 * */
public class 프림_PQ2_완성 {
	static class Edge implements Comparable<Edge> {
		int dest;
		int cost;

		Edge(int d, int c) {
			dest = d;
			cost = c;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		// 각 정점별로 인접리스트 참조변수
		ArrayList<Edge>[] adj = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
		}
		// 입력되어지는 간선 배열을 인접리스트에 저장
		for (int i = 0; i < E; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			int c = sc.nextInt();
			adj[a].add(new Edge(b, c));
			adj[b].add(new Edge(a, c));
		}
		// dist와 pq를 동기화
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] mst = new boolean[V];
		Edge[] dist = new Edge[V];
		// dist안의 각 거리들은 무한대

		for (int i = 0; i < V; i++) {
			// 원하는 출발지
			if (i == 0) {
				dist[i] = new Edge(i, 0);
			} else {
				dist[i] = new Edge(i, Integer.MAX_VALUE);
			}
			pq.add(dist[i]);
		}

		// 시작점은 0
		int result = 0;
		while (!pq.isEmpty()) {
			// 현재 dist가 가장 작은 친구를 데려와서
			Edge e = pq.poll();

			// 그 친구로부터 출발하는 모든 간선에 대해서
			for (Edge next : adj[e.dest]) {
				// 그 간선의 목적지가 아직 pq에 들어있는 정점이라면
				// 그리고 더 빨리 도착할 수 있다면
				if (!mst[next.dest] && dist[next.dest].cost > next.cost) {
					// dist갱신
					dist[next.dest].cost = next.cost;
					// decrease key연산
					pq.remove(dist[next.dest]);
					pq.add(dist[next.dest]);
				}
			}
			result += e.cost;
			mst[e.dest] = true;
		}
		System.out.println(result);
		// System.out.println(Arrays.toString(p));
	}
}
