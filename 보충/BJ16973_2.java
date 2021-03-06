package day11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*직사각형 탈출*/
public class BJ16973_2 {
	// 맵의 크기
	static int N;
	static int M;
	// 맵과 방문 체크
	static int[][] map;
	static boolean[][] visited;
	// 직사각형의 크기
	static int H;
	static int W;
	// 직사각형의 도착점
	static int Sr;
	static int Sc;
	// 직사각형의 도착점
	static int Fr;
	static int Fc;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		H = sc.nextInt();
		W = sc.nextInt();
		Sr = sc.nextInt();
		Sc = sc.nextInt();
		Fr = sc.nextInt();
		Fc = sc.nextInt();
		int ans = -1;
		// BFS를 위해서 큐를 준비
		Queue<Node> queue = new LinkedList<>();
		// 초기 상태(출발점)를 큐에 삽입하고, 초기상태의 좌표를 다시 검사하지 않기 위해 방문체크
		queue.add(new Node(Sr, Sc, 0));
		visited[Sr][Sc] = true;
		// 큐가 빌때까지
		while (!queue.isEmpty()) {
			// 큐에 상태를 하나 꺼내서
			Node node = queue.poll();
			// 현재 검사하는 상태가 종료점이라면 ans에 저장하고 break;
			if (node.r == Fr && node.c == Fc) {
				ans = node.cnt;
				break;
			}
			// 아니라면
			// 현재 상태로부터 전이될 수 있는 상태들을 큐에 삽입하고 다시 검사하지 않기 위해서 방문 체크
			for (int d = 0; d < 4; d++) {
				int nr = node.r + dr[d];
				int nc = node.c + dr[d];
				// 갈 수 있는지 검사해봐서, 괜춘 하면 큐에 삽입
				// 이미 간 곳은 안감
				if (visited[nr][nc])
					continue;
				// 가고자 하는 방향의 새로운 한줄이 모두 0이라면
				if(movable(nr, nc, d)) {
					visited[nr][nc] = true;
					queue.add(new Node(nr, nc, node.cnt + 1));
				}

			}
		}

		// break를 못만나고 큐가 비어서 반복이 종료됐다면 ans는 -1, break를 만났다면 이동횟수가 들어있음
		System.out.println(ans);

	} // end main

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static boolean movable(int r, int c, int dir) {
		// 위로
		if (dir == 0) {
			if (r - 1 > 0) {
				for (int i = 0; i < W; i++) {
//					if(map[r-1][사각형의 맨 왼쪽부터 맨 오른쪽 까지] == 1)
					if (map[r - 1][c + i] == 1)
						return false;
				}
			} else {
				return false;
			}
		} else if (dir == 1) {
			if (r + H <= N) {
				// map[r+H][직사각형의 맨 왼쪽부터 맨 오른쪽 까지] == 1
				for (int i = 0; i < W; i++) {
					if (map[r + H][c + i] == 1)
						return false;
				}
			} else {
				return false;
			}
		} else if (dir == 2) {
			if (c - 1 > 0) {
				for (int i = 0; i < H; i++) {
					// map[맨 윗 행부터 맨 아랫 행까지][c - 1] == 1
					if (map[r + i][c - 1] == 1) {
						return false;
					}
				}
			} else {
				return false;
			}
		} else if (dir == 3) {
			if (c + W <= M) {
				for (int i = 0; i < H; i++) {
					if (map[r + i][c + W] == 1)
						return false;
				}
			} else {
				return false;
			}

		}

		return true;
	} // end movable

	static class Node {
		int r, c, cnt;

		public Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

	}
}
