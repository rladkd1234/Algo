
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution_D4_5987_달리기_dfs터짐 {
    static int N,M;
    static int[] needs;
    static long[] memo;
    static int count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int f,s;
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; ++t) {
            st = new StringTokenizer(br.readLine(), " ");
            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());
 
            needs=new int[N];
            for(int m=0; m<M; ++m) {
                st = new StringTokenizer(br.readLine(), " ");
                f=Integer.parseInt(st.nextToken())-1;
                s=Integer.parseInt(st.nextToken())-1;
                needs[f] |=(1<<s); 
            }
            count=0;
            dfs(0);
            System.out.println("#"+t+" "+count);
        }
    }
     
    static void dfs(int flag) {
    	
        if(flag == (1<<N)-1){
        	count++;
            return ;
        }
      
        for(int i=0; i<N;++i) {
            if((flag &1<<i)==0 ) {
            	if((flag & needs[i])==needs[i]){
            		System.out.printf("flag : %d needs[%d] : %d\n", flag, i, needs[i]);
            		dfs(flag | 1<<i);
            	}
            }
        }
    }
}
