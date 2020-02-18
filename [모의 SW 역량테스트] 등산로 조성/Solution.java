import java.util.Scanner;

public class Solution {
    static int N;
    static int K;
    static int max = 0;
    static int[][] matrix;
    static int[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void dfs(int x, int y, int count, int cut){
        visited[x][y] = 1;
        if(max < count)
            max = count;
        for(int dir = 0; dir<4; dir++){
            int dis_x = x+dx[dir];
            int dis_y = y+dy[dir];

            if(dis_x >= 0 && dis_x < N && dis_y >= 0 && dis_y < N){
                if(matrix[x][y] > matrix[dis_x][dis_y] && visited[dis_x][dis_y] != 1){
                    dfs(dis_x, dis_y, count+1, cut);
                }
                else{
                    for(int n = 1; n<=K; n++){
                        if(cut < 1 && matrix[x][y] > matrix[dis_x][dis_y] - n && visited[dis_x][dis_y] != 1){
                            matrix[dis_x][dis_y] = matrix[dis_x][dis_y] - n;
                            dfs(dis_x, dis_y, count+1, cut+1);
                            matrix[dis_x][dis_y] = matrix[dis_x][dis_y] + n;
                        }
                    }
                }

            }
        }
        visited[x][y] = 0;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();


        for(int test_case = 1; test_case<=T; test_case++){
            int max_height = 0;
            N = sc.nextInt();
            K = sc.nextInt();
            matrix = new int[N][N];
            visited = new int[N][N];
            for(int i = 0; i<N; i++){
                for(int j = 0; j<N; j++){
                    matrix[j][i] = sc.nextInt();
                    max_height = Math.max(max_height, matrix[j][i]);
                }
            }
            for(int i = 0; i<N; i++){
                for(int j = 0; j<N; j++){
                    if(matrix[j][i] == max_height){
                        dfs(j,i,1,0);
                    }
                }
            }
            System.out.println("#" + test_case + " " + max);
            max = 0;
        }
    }
}
