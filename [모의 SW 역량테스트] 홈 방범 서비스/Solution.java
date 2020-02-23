import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    static class house{
        int x,y;
        house(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int M;
    static int max = 1;
    static int[][] matrix;
    static ArrayList<house> houses = new ArrayList<>();

    public static int distance(int x1, int x2, int y1, int y2){//기준점과 집과의 거리계산
        int tempx;
        int tempy;
        if(x1 > x2){
            tempx = x1 - x2;
        }
        else{
            tempx = x2 - x1;
        }
        if(y1 > y2){
            tempy = y1 - y2;
        }
        else{
            tempy = y2 - y1;
        }
        return tempx + tempy;
    }
    public static int cost(int K){//운영비용 계산
        return K * K + (K-1) * (K-1);
    }
    public static void solve(int x, int y){//기준점에 대해서 범위안의 집을 포함시켜 손해를 보는지 않보는지 확인
        for(int i = 2; i<2*N;i++){
            int count = 0;

            for(int j = 0; j<houses.size(); j++){
                if(distance(x,houses.get(j).x, y, houses.get(j).y) < i){
                    count++;
                }
            }
            if(count * M - cost(i) >= 0){
                if(max < count){
                    max = count;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case<=T; test_case++){
            N = sc.nextInt();
            M = sc.nextInt();
            matrix = new int[N][N];
            houses.clear();
            for(int j = 0; j<N; j++){
                for(int i = 0; i<N; i++){
                    matrix[i][j] = sc.nextInt();
                    if(matrix[i][j] == 1){
                        houses.add(new house(i,j));
                    }
                }
            }
            for(int j = 0; j<N; j++) {
                for (int i = 0; i < N; i++) {
                    solve(i,j);
                }
            }
            System.out.println("#" + test_case + " " + max);
            max = 1;
        }
    }
}
