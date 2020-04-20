import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ3954_3 {
	static int T, M, C, I;
	static char[] inst;
	static int instPos;
	static char[] input;
	static int inputPos;
	static int[] mem;
	static int memPos;
	static Stack<LoopInst> stack;
	static int loopInstPos1 = -1;
	static int loopInstPos2 = -1;
	static int instCnt;
	static ArrayList<LoopPair> loopPairs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken()); // 메모리 크기
			C = Integer.parseInt(st.nextToken()); // 프로그램 코드 크기
			I = Integer.parseInt(st.nextToken()); // 입력 크기

			st = new StringTokenizer(br.readLine(), " ");
			inst = st.nextToken().toCharArray();
			instPos = 0;

			st = new StringTokenizer(br.readLine(), " ");
			input = st.nextToken().toCharArray();
			inputPos = 0;

			mem = new int[M];
			memPos = 0;

			stack = new Stack<>();
			loopInstPos1 = -1;
			loopInstPos2 = -1;

			instCnt = 0;

			// makeLoopPair
			loopPairs = new ArrayList<>();
			for (instPos = 0; instPos < C; instPos++) {
				char ch = inst[instPos];
				if (ch == '[') {
					stack.push(new LoopInst(instPos, 0));
				}

				if (ch == ']') {
					LoopInst loop1 = stack.pop();
					LoopInst loop2 = new LoopInst(instPos, 1);
					loopPairs.add(new LoopPair(loop1, loop2));
				}
			}

			/*
			 * for(int i = 0; i < loopPairs.size(); i++) {
			 * System.out.println(loopPairs.get(i).loop1 + ", " + loopPairs.get(i).loop2); }
			 * System.out.println();
			 */

			for (instPos = 0; instPos < C; ) {
//				if (instCnt >= 500) {
				if (instCnt >= 50_000_000) {
					System.out.print("Loops ");
					findLoops();
					break;
				}
				
				instCnt++;
				char ch = inst[instPos];
//				System.out.printf("before ch : %c instPos : %d instCnt : %d memPos : %d mem[memPos] : %d\n", ch, instPos, instCnt, memPos, mem[memPos]);
				switch (ch) {
				case '-':
					instMemValMinus();
					instPos++;
					break;
				case '+':
					instMemValPlus();
					instPos++;
					break;
				case '<':
					instMemPosLeft();
					instPos++;
					break;
				case '>':
					instMemPosRight();
					instPos++;
					break;
				case '[':
					instLoop1();
					break;
				case ']':
					instLoop2();
					break;
				case '.' :
					instPos++;
					break;
				case ',':
					instReadInput();
					instPos++;
					break;

				}
//				System.out.printf("after ch : %c instPos : %d instCnt : %d memPos : %d mem[memPos] : %d\n", ch, instPos, instCnt, memPos, mem[memPos]);
//				System.out.println();
			}

			if(instCnt < 50_000_000) {
				System.out.println("Terminates");
			}
		} // end testCase

	} // end main



	private static void findLoops() {
		/*
		 * for (int i = C - 1; i >= 0; i--) { if (touch[i] > 0) {
		 * System.out.println(loop[i] + " " + i); break; } }
		 */

		for (int i = loopPairs.size() - 1; i >= 0; i--) {
			if (loopPairs.get(i).loop1.pos <= instPos && instPos <= loopPairs.get(i).loop2.pos) {
				System.out.println(loopPairs.get(i).loop1.pos + " " + loopPairs.get(i).loop2.pos);
				break;
			}
		}

	}
	
	private static void instReadInput() {
		if (inputPos == I) {
			mem[memPos] = 255;
		} else {
			mem[memPos] = input[inputPos];
			inputPos++;
		}
	}

	private static void instLoop2() {
		if(mem[memPos] != 0) {
			for(int i = 0; i < loopPairs.size(); i++) {
				if(loopPairs.get(i).loop2.pos == instPos) {
					instPos = loopPairs.get(i).loop1.pos;
					
					break;
				}
			}
		} else {
			instPos++;
		}
	}

	private static void instLoop1() {
		if(mem[memPos] == 0) {
			for(int i = 0; i < loopPairs.size(); i++) {
				if(loopPairs.get(i).loop1.pos == instPos) {
					instPos = loopPairs.get(i).loop2.pos;
					
					break;
				}
			}
			
		} else {
			instPos++;
		}
	}

	private static void instMemPosRight() {
		memPos++;
		if (memPos == M) {
			memPos = 0;
		}
	}

	private static void instMemPosLeft() {
		memPos--;
		if (memPos == -1) {
			memPos = M - 1;
		}
	}

	private static void instMemValPlus() {
		mem[memPos]++;
		if (mem[memPos] > ((1 << 8) - 1)) {
			mem[memPos] = 0;
		}
	}

	private static void instMemValMinus() {
		mem[memPos]--;
		if (mem[memPos] < 0) {
			mem[memPos] = (1 << 8) - 1;
		}
	}

	static class LoopPair {
		LoopInst loop1;
		LoopInst loop2;

		public LoopPair(LoopInst loop1, LoopInst loop2) {
			this.loop1 = loop1;
			this.loop2 = loop2;
		}

	}

	static class LoopInst {
		int pos;
		int kinds;

		@Override
		public String toString() {
			return "LoopInst [pos=" + pos + ", kinds=" + kinds + "]";
		}

		public LoopInst(int pos, int kinds) {
			this.pos = pos;
			this.kinds = kinds;
		}

	}
}