import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW5987 {

	static int T, N, M;
	static int[] player;
	static long[] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			player = new int[N]; 
			memo = new long[1 << N];

			int x, y;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				x = Integer.parseInt(st.nextToken()) - 1;
				y = Integer.parseInt(st.nextToken()) - 1;
				player[x] |= (1 << y);
			}

			running(0);
			System.out.println("#" + tc + " " + memo[0]);
			
		} // end testCase
	} // end main

	private static long running(int people) {
		if (people == (1 << N) - 1) {
			return 1;
		}

		if (memo[people] > 0) {
			return memo[people];
		}

		for (int i = 0; i < N; i++) {
			if ((people & (1 << i)) == 0) {
				if ((player[i] & people) != player[i])
					continue;
				memo[people] += running(people | (1 << i));
			}
		}
		

		return memo[people];
	}
}