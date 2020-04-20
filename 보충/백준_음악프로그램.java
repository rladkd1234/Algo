package day12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 백준_음악프로그램 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();// 정점의 갯수
		int M = sc.nextInt();// 입력의 라인 수
		List<Integer>[] adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adj[i] = new ArrayList<>();
		int[] pre = new int[N + 1];
		for (int i = 0; i < M; i++) {
			int m = sc.nextInt();
			int start = sc.nextInt();
			int end = 0;
			for (int j = 0; j < m - 1; j++) {
				end = sc.nextInt();
				adj[start].add(end);
				pre[end]++;
				start = end;
			}
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			if (pre[i] == 0)
				queue.add(i);
		}
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		while (!queue.isEmpty()) {
			cnt++;
			int n = queue.poll();
			sb.append(n).append("\n");
			for (int i = 0; i < adj[n].size(); i++) {
				if (--pre[adj[n].get(i)] == 0)
					queue.add(adj[n].get(i));
			}
		}
		if (cnt != N)
			System.out.println(0);
		else
			System.out.println(sb);
	}
}