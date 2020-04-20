import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, H, W;
	static int[][] map;
	static int ans = Integer.MAX_VALUE;
	static int[] dy = { 0, 0, -1, 1 };
	static int[] dx = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			ans = Integer.MAX_VALUE;

			map = new int[H][W];
			for (int y = 0; y < H; y++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int x = 0; x < W; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}

			}

			// 부술 벽돌을 고름

			dfs(0, map);

			if (ans == Integer.MAX_VALUE) {
				System.out.println("#" + tc + " " + 0);
			} else {
				System.out.println("#" + tc + " " + ans);
			}

		} // end testCase

	} // end main

	private static void dfs(int depth, int[][] map) {
		if (depth == N) {
			// 벽돌 수 구하기
			// ans 갱신
			int cnt = 0;
			for (int y = 0; y < H; y++) {
				for (int x = 0; x < W; x++) {
					if (map[y][x] > 0) {
						cnt++;
					}
				}
			}

			/*
			 * for (int y = 0; y < H; y++) System.out.println(Arrays.toString(map[y]));
			 * System.out.println();
			 */

//			System.out.println("cnt : " + cnt);
			ans = Math.min(ans, cnt);
			return;
		}

		for (int x = 0; x < W; x++) {
			// 부술 벽돌 선택

			int y = 0;
			for (y = 0; y < H; y++) {
				if (map[y][x] != 0)
					break;
			}
			if (y == H)
				continue;

			int temp[][] = new int[H][W];
			for (int t = 0; t < H; t++)
				temp[t] = Arrays.copyOf(map[t], map[t].length);

			// 벽돌 부수기
			map = play(y, x, map);
			map = settingMap(map);
			/**/

			dfs(depth + 1, map);

			for (int t = 0; t < H; t++)
				map[t] = Arrays.copyOf(temp[t], temp[t].length);
		}

		return;
	} // end dfs

	private static int[][] play(int y, int x, int[][] map) {
		// chose 벽돌

		/*
		 * for (int i = 0; i < H; i++) System.out.println(Arrays.toString(map[i]));
		 * System.out.println();
		 */

//		System.out.printf("y : %d x : %d\n", y, x);

		int range = map[y][x];
		map[y][x] = 0;

		for (int k = 0; k < range; k++) {
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d] * k;
				int nx = x + dx[d] * k;

				if (ny < 0 || nx < 0 || ny > H - 1 || nx > W - 1)
					continue;

				if (map[ny][nx] > 1) {
					map = play(ny, nx, map);
				} else {
					map[ny][nx] = 0;
				}
			}
		}

		return map;
	}

	private static int[][] settingMap(int [][]map) {
		int[][] temp = new int[H][W];

		for (int x = 0; x < W; x++) {
			int sy = H - 1;
			for (int y = H - 1; y >= 0; y--) {
				if (map[y][x] != 0) {
					temp[sy][x] = map[y][x];
					sy--;
				}
			}
		}
		return temp;
	}

}