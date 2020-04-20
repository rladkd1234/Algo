import java.util.Arrays;
import java.util.Scanner;

public class BJ12100 {
	static int N, ans;
	static int map[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		map = new int[N][N];

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				map[y][x] = sc.nextInt();
			}
		}

		dfs(0);

		System.out.println(ans);
	}

	private static void dfs(int depth) {
		if (depth == 5) {
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					ans = Math.max(ans, map[y][x]);
				}
			}
			return;
		}

		// 각각 상하좌우로 다 움직여본다.
		int[][] temp = new int[N][N];

		// 배열 복사
		for (int y = 0; y < N; y++) {
			temp[y] = Arrays.copyOf(map[y], map[y].length);
		}

		// 이동
		for (int d = 0; d < 4; d++) {
			play(d);
			dfs(depth + 1);
			// 복구
			for (int y = 0; y < N; y++) {
				map[y] = Arrays.copyOf(temp[y], temp[y].length);
			}
		}

	}

	private static void play(int d) {

		switch (d) {
		case 0:
			// 아래로 이동
			for (int x = 0; x < N; x++) {
				int sy = N - 1;
				for (int y = N - 2; y >= 0; y--) {
					if (map[y][x] != 0) {
						if (map[sy][x] == 0) {
							map[sy][x] = map[y][x];
							map[y][x] = 0;
						} else if (map[sy][x] != map[y][x]) {
							sy--;
							map[sy][x] = map[y][x];
							if (sy != y)
								map[y][x] = 0;
						} else if (map[sy][x] == map[y][x]) {
							map[sy][x] += map[y][x];
							map[y][x] = 0;
							sy--;
						}

					}
				}
			}
			break;

		case 1:
			// 위로 이동
			for (int x = 0; x < N; x++) {
				int sy = 0;
				for (int y = 1; y <= N -1; y++) {
					if (map[y][x] != 0) {
						if (map[sy][x] == 0) {
							map[sy][x] = map[y][x];
							map[y][x] = 0;
						} else if (map[sy][x] != map[y][x]) {
							sy++;
							map[sy][x] = map[y][x];
							if (sy != y)
								map[y][x] = 0;
						} else if (map[sy][x] == map[y][x]) {
							map[sy][x] += map[y][x];
							map[y][x] = 0;
							sy++;
						}

					}
				}
			}
			break;

		case 2:
			// 왼쪽으로 이동
			for (int y = 0; y < N; y++) {
				int sx = 0;
				for (int x = 1; x <= N -1; x++) {
					if (map[y][x] != 0) {
						if (map[y][sx] == 0) {
							map[y][sx] = map[y][x];
							map[y][x] = 0;
						} else if (map[y][sx] != map[y][x]) {
							sx++;
							map[y][sx] = map[y][x];
							if (sx != x)
								map[y][x] = 0;
						} else if (map[y][sx] == map[y][x]) {
							map[y][sx] += map[y][x];
							map[y][x] = 0;
							sx++;
						}

					}
				}
			}
			break;

		case 3:
			// 오른쪽으로 이동
			for (int y = 0; y < N; y++) {
				int sx = N - 1;
				for (int x = N - 2; x >= 0; x--) {
					if (map[y][x] != 0) {
						if (map[y][sx] == 0) {
							map[y][sx] = map[y][x];
							map[y][x] = 0;
						} else if (map[y][sx] != map[y][x]) {
							sx--;
							map[y][sx] = map[y][x];
							if (sx != x)
								map[y][x] = 0;
						} else if (map[y][sx] == map[y][x]) {
							map[y][sx] += map[y][x];
							map[y][x] = 0;
							sx--;
						}

					}
				}
			}
			break;

		}
	}
}
