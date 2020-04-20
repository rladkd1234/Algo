import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class SW5987_3 {
    static int n, m;
    static int[] arr;
    static long[] memo;
    static int ans;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());
        for(int testcase = 1; testcase <= TC; testcase++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
             
            arr = new int[n];
            memo = new long[1<<n];
            ans = 0;
             
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                arr[Integer.parseInt(st.nextToken()) - 1] |= 1<<(Integer.parseInt(st.nextToken()) - 1);
            }
             
            long r = dfs(0);
             
            sb.append('#').append(testcase).append(' ').append(r).append('\n');
        }
        System.out.print(sb);
    }
     
    public static long dfs(int flag) {
        if(flag==(1<<n) - 1) {
            return 1;
        }
         
        if(memo[flag] > 0) {
            return memo[flag];
        }
         
        for(int i = 0; i < n; i++) {
            if((flag & 1 << i) == 0) {
                if((flag & arr[i]) == arr[i]) {
                    //System.out.println(flag + " " + i);
                    memo[flag] += dfs(flag | 1<<i);
                }
            }
        }
         
        return memo[flag];
    }
}