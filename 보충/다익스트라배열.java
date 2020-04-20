import java.util.Arrays;
import java.util.Scanner;

public class 다익스트라배열 {
	   public static void main(String[] args) throws Exception{
	      Scanner sc = new Scanner(System.in);
	      int V = sc.nextInt();
	      int E = sc.nextInt();
	      int[][] adj = new int[V][V];
	         
	      for (int i = 0; i < E; i++) {
	         adj[sc.nextInt()][sc.nextInt()] = sc.nextInt();
	      }
	      int[] D = new int[V];
	      Arrays.fill(D, Integer.MAX_VALUE);
	      boolean[] check = new boolean[V];
	      
	      D[0] = 0;
	      for (int i = 0; i < V-1; i++) {
	         int min = Integer.MAX_VALUE;
	         int index = -1;
	         for (int j = 0; j < V; j++) {
	            //방문한적 없으면서, 가장 짧은 거리라면
	            if(!check[j] && min >D[j]) {
	               min = D[j];
	               index = j;
	            }
	         }
	         
	         //새로운 정점으로 부터 갈수 있는 경로들을 업데이트
	         for (int j = 0; j < V; j++) {
	            //아직 처리하지 않았으면서, 경로가 존재하고, 
	            if(!check[j] && adj[index][j] !=0 & D[index]+adj[index][j]<D[j]) {
	               D[j ] = D[index]+adj[index][j];
	            }
	         }
	         check[index] =true;
	      }
	      System.out.println(Arrays.toString(D));
	   }
	}