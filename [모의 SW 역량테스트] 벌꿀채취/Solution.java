import java.util.Scanner;

public class Solution {
    static int[][] matrix;
    static int N;
    static int M;
    static int C;
    static int max_price = 0; //일꾼 한명이 고른 꿀의 가격
    static int result = 0;

    public static void cal_price(int x, int y, int sum, int count, int price){ //일꾼 한명이 고른 꿀에 대한 최대 가격 계산
        if(sum > C){
            return;
        }
        max_price = Math.max(max_price, price);
        if(count == M){
            return;
        }

        cal_price(x+1, y, sum, count+1, price);
        cal_price(x+1, y, sum + matrix[x][y], count+1, price + (matrix[x][y] * matrix[x][y]));

    }
    public static void solve(int x ,int y){//일군 한명 고정시켜놓고 나머지 일꾼을 남은 배열에 돌려 최대 가격 얻음
        max_price = 0;
        cal_price(x,y,0,0,0);
        int honey = max_price; //얻은 꿀 가격 저장할 변수
        int temp = honey; //고정된 일꾼이 얻은 꿀 가격
        for(int j = y; j<N; j++, x = -M){
            for(int i = x+M; i<=N-M; i++){
                max_price = 0;
                cal_price(i,j,0,0,0);
                honey += max_price; // 일꾼 두명이 얻은 꿀 가격
                result = Math.max(result,honey);
                honey = temp;

            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case<=T; test_case++){
            N = sc.nextInt();
            M = sc.nextInt();
            C = sc.nextInt();
            matrix = new int[N][N];

            for(int j = 0; j<N; j++){
                for(int i = 0; i<N; i++){
                    matrix[i][j] = sc.nextInt();
                }
            }
            for(int j = 0; j<N; j++) {
                for (int i = 0; i <= N-M; i++) {
                    solve(i,j);
                }
            }
            System.out.println("#" + test_case + " " + result);
            result = 0;
        }
    }
}
