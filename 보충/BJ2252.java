package day12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BJ2252 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		List<Integer>[] adj = new List[N];

		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();

		int[] cnt = new int[N]; // 각 학생별로 나보다 작은 녀석의 수

		// a 다음으로 b가 올 수 있으니까 a에 b추가
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			adj[a].add(b);
			cnt[b]++;
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			if (cnt[i] == 0)
				queue.add(i);
		}

		StringBuilder sb = new StringBuilder();
		int depth = 0;
		while (!queue.isEmpty()) {
			depth++;
			int n = queue.poll();
			// 출력
			sb.append(n + 1).append("\n");
			// n번친구가 갖고 있는 다음 친구들(나보다 큰놈들)에 대해서 cnt를 1씩 줄여주자.
			for (int i = 0; i < adj[n].size(); i++) {
				// adj[n].get(i)인 친구에 대해서 cnt를 하나 줄여주고
				cnt[adj[n].get(i)] -= 1;
				// 그 줄인값이 0이 됐는지 확인
				if (cnt[adj[n].get(i)] == 0) {
					queue.add(adj[n].get(i));
				}
			}
		}
		System.out.println(sb);
	} // end main
}
