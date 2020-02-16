
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    public static class Node{
        int x, y, time;
        public Node(int x, int y, int time){
            this.x = x;
            this.y = y;
            this. time = time;
        }
    }
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N;
    static int M;
    static int L;
    static int time;

    static Queue<Node> queue = new LinkedList<>();
    static int[][] matrix;
    static int[][] visited;

    public static void bfs(int x, int y){
        queue.offer(new Node(x,y,1));
        visited[x][y] = 1;

        while(!queue.isEmpty()){
            Node node = queue.poll();
            x = node.x;
            y = node.y;
            time = node.time;
            if(time == L){
                continue;
            }

            for(int dir = 0; dir<4; dir++){
                int dis_x = x+dx[dir];
                int dis_y = y+dy[dir];

                if(dis_x >= 0 && dis_x < M && dis_y >= 0 && dis_y < N){
                    if(visited[dis_x][dis_y] == 0){

                        if(matrix[x][y] == 2){
                            if(dir == 0 || dir == 2){
                                continue;
                            }
                        }
                        else if(matrix[x][y] == 3){
                            if(dir == 1 || dir == 3){
                                continue;
                            }
                        }
                        else if(matrix[x][y] == 4){
                            if(dir == 1 || dir == 2){
                                continue;
                            }
                        }
                        else if(matrix[x][y] == 5){
                            if(dir == 2 || dir == 3){
                                continue;
                            }
                        }
                        else if(matrix[x][y] == 6){
                            if(dir == 0 || dir == 3){
                                continue;
                            }
                        }
                        else if(matrix[x][y] == 7){
                            if(dir == 0 || dir == 1){
                                continue;
                            }
                        }
                        if(dir == 0){
                            if(matrix[dis_x][dis_y] == 1 || matrix[dis_x][dis_y] == 3 || matrix[dis_x][dis_y] == 6 || matrix[dis_x][dis_y] == 7){
                                queue.offer(new Node(dis_x, dis_y, time+1));
                                visited[dis_x][dis_y] = 1;
                            }
                        }
                        else if(dir == 3){
                            if(matrix[dis_x][dis_y] == 1 || matrix[dis_x][dis_y] == 2 || matrix[dis_x][dis_y] == 5 || matrix[dis_x][dis_y] == 6){
                                queue.offer(new Node(dis_x, dis_y, time+1));
                                visited[dis_x][dis_y] = 1;
                            }
                        }
                        else if(dir == 2){
                            if(matrix[dis_x][dis_y] == 1 || matrix[dis_x][dis_y] == 3 || matrix[dis_x][dis_y] == 4 || matrix[dis_x][dis_y] == 5){
                                queue.offer(new Node(dis_x, dis_y, time+1));
                                visited[dis_x][dis_y] = 1;
                            }
                        }
                        else{
                            if(matrix[dis_x][dis_y] == 1 || matrix[dis_x][dis_y] == 2 || matrix[dis_x][dis_y] == 4 || matrix[dis_x][dis_y] == 7){
                                queue.offer(new Node(dis_x, dis_y, time+1));
                                visited[dis_x][dis_y] = 1;
                            }
                        }
                    }

                }


            }
        }

    }
    public static void main(String args[]) throws Exception
    {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        //System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = sc.nextInt();
            M = sc.nextInt();
            int R = sc.nextInt();
            int C = sc.nextInt();
            L = sc.nextInt();
            int result = 0;

            matrix = new int[M][N];
            visited = new int[M][N];
            for(int i = 0; i<N; i++){
                for(int j = 0; j<M; j++){
                    matrix[j][i] = sc.nextInt();
                    visited[j][i] = 0;
                }
            }
            queue.clear();
            bfs(C,R);
            for(int i = 0; i<N; i++){
                for(int j = 0; j<M; j++){
                    if(visited[j][i] == 1){
                        result++;
                    }
                }
            }
            if(L == 1)
                result = 1;
            System.out.println("#" + (test_case) + " " + result);

        }
    }
}