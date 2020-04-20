package day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2623 {
	static int N, M;
	static int[] cnt;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cnt = new int[N];
		graph = new ArrayList[N];

		for (int n = 0; n < N; n++)
			graph[n] = new ArrayList<>();

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int I = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = 0;
			for(int i = 0; i < I - 1; i++) {
				end = Integer.parseInt(st.nextToken()) - 1;
				graph[start].add(end);
				cnt[end]++;
				start = end;
			}
			/*
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			
			while (st.hasMoreTokens()) {
				int a = Integer.parseInt(st.nextToken());
				list.add(a - 1);
			}

			for (int i = 0; i < list.size() - 1; i++) {
				graph[list.get(i)].add(list.get(i + 1));
				cnt[list.get(i + 1)]++;
			}*/
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int n = 0; n < N; n++) {
			if (cnt[n] == 0) {
				queue.add(n);
			}
		}

		StringBuilder sb = new StringBuilder();
		int depth = 0;
		
		while (!queue.isEmpty()) {
			depth++;
			int cur = queue.poll();
			sb.append(cur + 1).append("\n");
			for (int i = 0; i < graph[cur].size(); i++) {
				cnt[graph[cur].get(i)]--;
				if (cnt[graph[cur].get(i)] == 0) {
					queue.add(graph[cur].get(i));
				}
			}

		}
		if (depth != N) {
			System.out.println(0);
		} else {
			System.out.println(sb);
		}
	} // end main
}
