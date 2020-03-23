import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    static class Cell{
        int x,y,num,check,time;
        //0: 비활성, 1: 활성, -1: 죽은 상태
        Cell(int x, int y, int num, int check, int time){
            this.x = x;
            this.y = y;
            this.num = num;
            this.check = check;
            this.time = time;
        }
    }
    static int N;
    static int M;
    static int K;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();
            int[][] matrix = new int[M+K+1][N+K+1];
            int count = 0;
            ArrayList<Cell> container = new ArrayList<>();

            for(int i = 0; i<N; i++){
                for(int j = 0; j<M; j++){
                    int num = sc.nextInt();
                    if(num != 0){
                        matrix[K/2 + j][K/2 + i] = num;
                        container.add(new Cell(K/2 + j,K/2 + i,num,0, 0));
                    }
                }
            }

            while(count<K){
                count++;

                for(int i = 0; i<container.size(); i++){
                    if(container.get(i).check == -1)
                        continue;
                    if(container.get(i).check == 0 && container.get(i).time < container.get(i).num){
                        container.get(i).time++;
                        if(container.get(i).time == container.get(i).num){
                            container.get(i).check = 1;
                            container.get(i).time = 0;
                            continue;
                        }
                    }

                    if(container.get(i).check == 1 && container.get(i).time == 0){
                        int length = container.size();
                        for(int dir = 0; dir<4; dir++){
                            int dis_x = container.get(i).x + dx[dir];
                            int dis_y = container.get(i).y + dy[dir];

                            if(matrix[dis_x][dis_y] == 0){
                                container.add(new Cell(dis_x, dis_y, container.get(i).num, 0, -1));
                                matrix[dis_x][dis_y] = container.get(i).num;
                            }
                            else if(matrix[dis_x][dis_y] != -1){
                                for(int j = 0; j<length; j++){
                                    if(i != j && container.get(j).x == dis_x && container.get(j).y == dis_y){

                                        if(container.get(j).check == 0 && container.get(j).time == -1){
                                            if(container.get(j).num < container.get(i).num){
                                                container.get(j).num = container.get(i).num;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }


                        }
                    }
                    if(container.get(i).check == 1 && container.get(i).time < container.get(i).num){
                        container.get(i).time++;
                        if(container.get(i).time == container.get(i).num){
                            matrix[container.get(i).x][container.get(i).y] = -1;
                            container.remove(i);
                            i--;
                        }
                    }

                }
            }

            System.out.println("#" + test_case + " " + container.size());
        }
    }
}
