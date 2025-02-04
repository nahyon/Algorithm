import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 14852 _ 타일채우기3
// DP 점화식 찾기..
public class Main {

   public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        System.out.println(cal(n));
    }
    static final int MOD = 1000000007;
    public static int cal(int n){
        if (n==1) return 2;
        else if (n==2) return 2*2 + 3;
        else if (n==3) return (2*2 + 3)*2 + (2)*3 + 2;
        
        long[] dp = new long[n+1];
        dp[1] = 2;
        dp[2] = 7 ; // dp[1] * 2 + 3; 
        dp[3] = 22; // dp[2] * 2 + dp[1] * 3 + 2
        
        // n>= 4
        // dp[i] = dp[i-1]*2 + dp[i-2]*3 + {dp[i-3]*2 + dp[i-4]*2  + ... + dp[1]*2} + 2
        // dp[0] = 1 로 두면
        dp[0] = 1;
        // dp[i] = dp[i-1]*2 + dp[i-2]*3 + {dp[i-3]* + dp[i-4] + ... + dp[1] + dp[0]} * 2
        long prefixSum = dp[1]+dp[0]; // (dp[i-3] 부터 dp[0]까지) sum 
        for (int i = 4; i <= n ; i++ ){
            dp[i] = (dp[i-1]*2%MOD + dp[i-2]*3%MOD + prefixSum*2%MOD) % MOD;
            
            prefixSum = (prefixSum + dp[i-2] )%MOD; //계속 추가해주기 (이 다음에 [i-3]인 수)
        }
        return (int) dp[n];
    }

}
