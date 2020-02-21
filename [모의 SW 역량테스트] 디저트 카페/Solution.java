import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    static int[][] matrix;
    static int[][] visited;
    static int[] dx = {1,-1,-1,1};
    static int[] dy = {1,1,-1,-1};
    static ArrayList<Integer> desert = new ArrayList<>();
    static int N;
    static int max = 0;
    static int start_x;
    static int start_y;
    public static void recursive(int x, int y, int count, int d){
        visited[x][y] = 1;
        desert.add(matrix[x][y]);

        for(int dir = d; dir<4; dir++) {
            int dis_x = x + dx[dir];
            int dis_y = y + dy[dir];
            if(dis_x >= 0 && dis_x < N && dis_y >= 0 && dis_y < N){
                if(visited[dis_x][dis_y] == 0 && !desert.contains(matrix[dis_x][dis_y])){
                    recursive(dis_x,dis_y,count+1, dir);
                }
                else if(start_x == dis_x && start_y == dis_y){

                    if(max < count + 1 && count + 1 >= 4){
                        max = count + 1;
                        return;
                    }

                }
            }
        }
        visited[x][y] = 0;
        desert.remove((Object)matrix[x][y]);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case<=T; test_case++) {
            N = sc.nextInt();
            matrix = new int[N][N];
            visited = new int[N][N];
            for(int j = 0; j<N; j++){
                for(int i = 0; i<N; i++){
                    matrix[i][j] = sc.nextInt();
                }
            }
            for(int j = 0; j<N; j++){
                for(int i = 0; i<N; i++){
                    start_x = i;
                    start_y = j;
                    recursive(i,j,0,0);
                    desert.clear();
                }
            }
            if(max == 0)
                System.out.println("#" + test_case + " " + -1);
            else
                System.out.println("#" + test_case + " " + max);
            max = 0;
        }
    }
}
