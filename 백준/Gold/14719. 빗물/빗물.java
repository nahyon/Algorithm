import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14719 _ 빗물 
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        if (W <= 2) {
            System.out.println(0);
            return;
        }

        st = new StringTokenizer(br.readLine());
        int[] height = new int[W];
        for (int i = 0; i < W; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int totalWater = 0;
        int[] leftMax = new int[W];
        int[] rightMax = new int[W];

        leftMax[0] = height[0];
        for (int i = 1; i < W; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[W - 1] = height[W - 1];
        for (int i = W - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        for (int i = 1; i < W - 1; i++) {
            totalWater += Math.max(0, Math.min(leftMax[i], rightMax[i]) - height[i]);
        }

        System.out.println(totalWater);
    }
}
