import java.util.Scanner;

public class Solution {
    static int N;
    static int X;
    static int count = 0;
    static int[][] matrix;
    static int[] visited;
    static void horizen(int y){
        for(int i = 0; i<N-1; i++){
            if(matrix[i][y] - matrix[i+1][y] == 1){
                if(i+X < N){
                    for(int j = 1; j<=X; j++){
                        if(matrix[i][y] - matrix[i+j][y] != 1 || visited[i+j] == 1){
                            return;
                        }
                    }
                    for(int n = 1; n<=X; n++){
                        visited[i+n] = 1;
                    }
                }
                else
                    return;
            }
            else if(matrix[i][y] - matrix[i+1][y] == -1){
                if(i-X >= -1){
                    for(int j = 0; j<X; j++){
                        if(matrix[i+1][y] - matrix[i-j][y] != 1 || visited[i-j] == 1){
                            return;
                        }
                    }
                    for(int n = 0; n<X; n++){
                        visited[i-n] = 1;
                    }
                }
                else
                    return;
            }
            else if(matrix[i][y] - matrix[i+1][y] != 0){
                return;
            }
        }
        count++;
    }
    static void vertical(int x){
        for(int i = 0; i<N-1; i++){
            if(matrix[x][i] - matrix[x][i+1] == 1){
                if(i+X < N){
                    for(int j = 1; j<=X; j++){
                        if(matrix[x][i] - matrix[x][i+j] != 1 || visited[i+j] == 1){
                            return;
                        }
                    }
                    for(int n = 1; n<=X; n++){
                        visited[i+n] = 1;
                    }
                }
                else
                    return;
            }
            else if(matrix[x][i] - matrix[x][i+1] == -1){
                if(i-X >= -1){
                    for(int j = 0; j<X; j++){
                        if(matrix[x][i] - matrix[x][i-j] != 0 || visited[i-j] == 1){
                            return;
                        }
                    }
                    for(int n = 0; n<X; n++){
                        visited[i-n] = 1;
                    }
                }
                else
                    return;
            }
            else if(matrix[x][i] - matrix[x][i+1] != 0){
                return;
            }
        }
        count++;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case<=T; test_case++){
            N = sc.nextInt();
            X = sc.nextInt();
            matrix = new int[N][N];

            for(int j = 0; j<N; j++){
                for(int i = 0; i<N; i++){
                    matrix[i][j] = sc.nextInt();
                }
            }
            for(int i = 0; i<N; i++){
                visited = new int[N];
                horizen(i);
                visited = new int[N];
                vertical(i);
            }
            System.out.println("#" + test_case + " " + count);
            count = 0;
        }
    }
}
