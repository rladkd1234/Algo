package day13;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

// 0. 각 맵의 S2D2가 겨울에 추가할 양분의 양을 저장할 자료 (2차원)
// 0. 각 맵의 현재 양분양을 저장할 자료 (2차원)
// 1. 현재 살아있는 모든 나무를 담을 Priority Queue(좌표, 나이)
// 2. 봄이 지나 살아있는 나무들이 모여있을 큐
// 3. 봄이 지나 죽을 나무들이 모여있을 큐

// 봄 : 1을 하나씩 꺼내며 산놈은 2로 죽은놈은 3으로
// 여름 : 2를 모두 꺼내며 양분추가 처리
// 가을 : 3을 모두 꺼내며 8방에 생기는 나무를 다시 1에 삽입
// 겨울 : 0을 한바퀴 돌면서 양분 업데이트

public class BJ16235 {
	// s2d2가 겨울에 뿌릴 양분의 양
	static int[][] s2d2;
	// 각 칸 별로 현재 양분의 양
	static int[][] map;
	// 현재 살아있는 모든 나무들
	static PriorityQueue<Tree> pq = new PriorityQueue<>();
	// 봄을 지나 살아남은 나무들이 잠시 머물 곳
	static Queue<Tree> alive = new LinkedList<>();
	// 봄을 지나 죽은 나무들이 잠시 머물 곳
	static Queue<Tree> dead = new LinkedList<>();
	static int N, M, K;

	static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 땅의 크기
		M = sc.nextInt(); // 나무의 수
		K = sc.nextInt(); // 몇년 후?
		map = new int[N + 1][N + 1];
		s2d2 = new int[N + 1][N + 1];
		// 입력

		for (int y = 1; y < N + 1; y++) {
			for (int x = 1; x < N + 1; x++) {
				map[y][x] = 5;
			}
		}

		for (int y = 1; y < N + 1; y++) {
			for (int x = 1; x < N + 1; x++) {
				s2d2[y][x] = sc.nextInt();
			}
		}

		for (int m = 0; m < M; m++) {
			pq.add(new Tree(sc.nextInt(), sc.nextInt(), sc.nextInt()));
		}

		for (int k = 0; k < K; k++) {
			// 봄
			spring();
			// 여름
			summer();
			// 가을
			autumn();
			// 겨울
			winter();
		}
		
		System.out.println(pq.size());
	}

	private static void spring() {
		// trees 큐를 하나씩 꺼내면서
		// 나무의 위치에 양분이 충분하다면 나무의 나이만큼 양분을 없애고 alive 큐로 삽입
		// 아니라면 dead 큐로 삽입
		while (!pq.isEmpty()) {
			Tree cur = pq.poll();

			if (map[cur.r][cur.c] >= cur.age) {
				map[cur.r][cur.c] -= cur.age;
				alive.add(new Tree(cur.r, cur.c, cur.age + 1));
			} else {
				dead.add(cur);
			}
		}
	}

	private static void summer() {
		// dead 큐를 모두 돌면서, 나무의 나이의 반만큼을 나무의 위치에 양분 누적합
		while (!dead.isEmpty()) {
			Tree cur = dead.poll();

			map[cur.r][cur.c] += cur.age / 2;
		}
	}

	private static void autumn() {
		// alive 큐를 모두 돌면서, 나이가 5의 배수라면 8방에 대해서 나무를 생성해 trees에 삽입, 현재 나무도 삽입
		while (!alive.isEmpty()) {
			Tree cur = alive.poll();
			pq.add(cur);
			if (cur.age % 5 == 0) {

				for (int d = 0; d < 8; d++) {
					int ny = cur.r + dy[d];
					int nx = cur.c + dx[d];

					if (ny < 1 || nx < 1 || ny > N || nx > N)
						continue;

					pq.add(new Tree(ny, nx, 1));
				}

			}

		}
	}

	private static void winter() {
		// N * N 을 탐색하면 map의 각 자리에 s2d2의 각 자리 값만큼을 누적합
		for (int y = 1; y < N + 1; y++) {
			for (int x = 1; x < N + 1; x++) {
				map[y][x] += s2d2[y][x];
			}
		}
	}

	static class Tree implements Comparable<Tree> {
		int r, c;
		int age;

		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public String toString() {
			return "Tree [r=" + r + ", c=" + c + ", age=" + age + "]";
		}

		@Override
		public int compareTo(Tree o) {
			int a = this.age - o.age;
			return a;
		}

	}
}
