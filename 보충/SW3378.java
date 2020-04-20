import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW3378 {
	static int T;
	static int b1, b2, b3;
	static int dotCnt;
	static int p, q;
	static int b1_1, b1_2, b2_1, b2_2, b3_1, b3_2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			b1 = -1;
			b2 = -1;
			b3 = -1;
			st = new StringTokenizer(br.readLine(), " ");

			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());

			b1_1 = 0;
			b1_2 = 0;
			b2_1 = 0;
			b2_2 = 0;
			b3_1 = 0;
			b3_2 = 0;

			// 스타일리쉬 코드
			for (int i = 0; i < p; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				char[] ch = st.nextToken().toCharArray();
				dotCnt = 0;
				int noDotFlag = 0;
				for (int s = 0; s < ch.length; s++) {
					if (ch[s] == '.' && noDotFlag == 0) {
						dotCnt++;
					}

					if (ch[s] != '.') {
						noDotFlag = 1;
					}

					switch (ch[s]) {
					case '(':
						b1_1++;
						break;

					case ')':
						b1_2++;
						break;

					case '{':
						b2_1++;
						break;

					case '}':
						b2_2++;
						break;

					case '[':
						b3_1++;
						break;

					case ']':
						b3_2++;
						break;

					}
					System.out.printf("( : %d ) : %d { : %d } : %d [ : %d ] : %d\n", b1_1, b1_2, b2_1, b2_2, b3_1, b3_2);
					if (b1_1 - b1_2 > 0 && b2_1 - b2_2 == 0 && b3_1 - b3_2 == 0) {
						b1 = dotCnt * (b1_1 - b1_2);
					}

					if (b1_1 - b1_2 == 0 && b2_1 - b2_2 > 0 && b3_1 - b3_2 == 0) {
						b2 = dotCnt * (b2_1 - b2_2);
					}

					if (b1_1 - b1_2 == 0 && b2_1 - b2_2 == 0 && b3_1 - b3_2 > 0) {
						b3 = dotCnt * (b3_1 - b3_2);
					}

					if (b1_1 - b1_2 > 0 && b2_1 - b2_2 > 0 && b3_1 - b3_2 == 0) {
						if (b1 != -1 && b2 == -1) {
							b2 = dotCnt - b1;
						}

						if (b1 == -1 && b2 != -1) {
							b1 = dotCnt - b2;
						}
					}

					if (b1_1 - b1_2 == 0 && b2_1 - b2_2 > 0 && b3_1 - b3_2 > 0) {
						if (b2 != -1 && b3 == -1) {
							b3 = dotCnt - b2;
						}

						if (b2 == -1 && b3 != -1) {
							b2 = dotCnt - b3;
						}
					}

					if (b1_1 - b1_2 > 0 && b2_1 - b2_2 == 0 && b3_1 - b3_2 > 0) {
						if (b1 != -1 && b3 == -1) {
							b3 = dotCnt - b1;
						}

						if (b1 == -1 && b3 != -1) {
							b1 = dotCnt - b3;
						}
					}
					System.out.printf("dotCnt : %d b1 : %d b2: %d b3 : %d\n", dotCnt, b1, b2, b3);
				}

				
				
				
			} // end 스타일리쉬 코드

			// 내 코드
			for (int i = 0; i < q; i++) {

			}

		} // end testCase
	} // end main
}
