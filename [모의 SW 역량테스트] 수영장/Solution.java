import java.util.Scanner;

public class Solution {
    static int result = 0;
    static int[] ticket;
    static int[] plan;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ticket = new int[4];
        plan = new int[13];

        int T = sc.nextInt();
        for(int i = 1; i<=T; i++){
            int[] dp = new int[13];

            for(int j = 0; j<ticket.length; j++){
                ticket[j] = sc.nextInt();
            }

            for(int k = 1; k<plan.length; k++){
                plan[k] = sc.nextInt();
                plan[k] = Math.min(ticket[0] * plan[k] , ticket[1]);
            }
            for(int m = 1; m<plan.length; m++){
                dp[m] = dp[m-1] + plan[m];
                if(m >= 3){
                    dp[m] = Math.min(dp[m], dp[m-3] + ticket[2]);
                }
            }
            if(dp[12] < ticket[3]){
                System.out.println("#" + i + " " + dp[12]);
            }
            else
                System.out.println("#" + i + " " + ticket[3]);

        }

    }
}
