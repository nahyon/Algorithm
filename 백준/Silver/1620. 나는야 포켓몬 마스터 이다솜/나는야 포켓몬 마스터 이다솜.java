import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> m1 = new HashMap<>();
        HashMap<Integer, String> m2 = new HashMap<>();

        for (int i = 1 ; i <= n ; i++   ) {
            String s = br.readLine();
            m1.put(s, i);
            m2.put(i, s);
        }

        StringBuilder sb = new StringBuilder();
        for (int i= 0 ; i < m ; i++) {
            String s = br.readLine();
            if (m1.containsKey(s)) sb.append(m1.get(s)).append("\n");
            else sb.append(m2.get(Integer.parseInt(s))).append("\n");
        }
        
        System.out.println(sb);
    }
}