import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
public class 크루스칼 {
	static int[] parents;
	public static void main(String[] args) {
	/*	Scanner sc = new Scanner("7 11\n" + "0 1 31\r\n" + "0 2 31\r\n" + "0 6 31\r\n" + "0 5 60\r\n" + "1 2 21\r\n"
				+ "2 4 46\r\n" + "2 6 25\r\n" + "3 4 34\r\n" + "4 6 51\r\n" + "5 3 18\r\n" + "5 4 40\r\n");
	*/	
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt(); //정점의 갯수
		int E = sc.nextInt(); //간선의 개수
		int[][] paths = new int[E][3];
		for(int i = 0; i < E; i++) {
			paths[i][0] = sc.nextInt();
			paths[i][1] = sc.nextInt();
			paths[i][2] = sc.nextInt();
		}
		//간선을 가중치로 정렬
		Arrays.sort(paths, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[2] - o2[2];
			}
		});
		parents = new int[V + 1];
		for(int i = 1; i <= V; i++)
			parents[i] = i;
		int result = 0;
		for(int i = 0; i < E; i++) {
			int a = find(paths[i][0]);
			int b = find(paths[i][1]);
			if(a==b)
				continue;
			union(a, b);
			System.out.println(paths[i][0] + ", " + paths[i][1] + " : " + paths[i][2]);
			result += paths[i][2];
		}
		System.out.println(result);
	}
	static int find(int x) {
		if( parents[x] == x )
			return x;
		return parents[x] = find(parents[x]);
	}
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px != py )
			parents[px] = py;
	}
}
