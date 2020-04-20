import java.util.Scanner;

public class SW5987_2 {
	static int T;
	static int N, M;
	static long ans;
	static int[] player;
	static long[] memo;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();

			ans = 0;

			player = new int[N];
			memo = new long[1 << N];

			for (int m = 0; m < M; m++) {
				int x = sc.nextInt() - 1;
				int y = sc.nextInt() - 1;
				player[x] |= (1 << y);
			}

			dfs(0);
			System.out.println("#" + tc + " " + memo[0]);
		}

	}

	private static long dfs(int flag) {
		if (flag == (1 << N) - 1) {
			return 1;
		}

		if (memo[flag] > 0)
			return memo[flag];

		for (int i = 0; i < N; i++) {
			if ((flag & (1 << i)) == 0) {
				if ((flag & player[i]) == player[i]) {
					memo[flag] += dfs(flag | 1 << i);
				}
			}
		}
		return memo[flag];
	}
	
}
