import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    static class Group{
        int x,y,dir;
        long num;
        Group(int x, int y, long num, int dir){
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
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
            int result = 0;
            int count = 0;
            ArrayList<Group> groups = new ArrayList<>();
            for(int i = 0; i<K; i++){
                int y = sc.nextInt();
                int x = sc.nextInt();
                int num = sc.nextInt();
                int dir = sc.nextInt() - 1;
                groups.add(new Group(x,y,num,dir));
            }
            while(count != M){ //M만큼 미생물 이동
                for(int i = 0; i<groups.size(); i++){
                    int dir = groups.get(i).dir;
                    groups.get(i).x += dx[dir];
                    groups.get(i).y += dy[dir];
                    //가장자리에 도착하면 미생물 수 반으로 감소
                    if(groups.get(i).x == 0 || groups.get(i).x == N-1 || groups.get(i).y == 0 || groups.get(i).y == N-1){
                        groups.get(i).num /= 2;
                        //방향 반대로 변경
                        if(dir % 2 == 0)
                            groups.get(i).dir = dir + 1;
                        else
                            groups.get(i).dir = dir - 1;
                    }
                }
                for(int i = 0; i<groups.size(); i++){
                    int tempx = groups.get(i).x;
                    int tempy = groups.get(i).y;
                    long temp_num = groups.get(i).num;
                    long sum = groups.get(i).num;
                    for(int j = i+1; j<groups.size(); j++){
                        if(tempx == groups.get(j).x && tempy == groups.get(j).y){ //도착 위치가 같으면
                            if(temp_num < groups.get(j).num){//미생물 수 비교
                                groups.get(i).dir = groups.get(j).dir;//더 많은 쪽의 방향으로 변경
                                temp_num = groups.get(j).num;
                                sum += groups.get(j).num;//미생물 수 합침
                                groups.remove(j);//더 작은 그룹 삭제
                                j = j-1;
                            }
                            else{
                                sum += groups.get(j).num;
                                groups.remove(j);
                                j = j-1;
                            }
                        }
                    }
                    groups.get(i).num = sum;
                }
                count++;
            }
            for(int i = 0; i<groups.size(); i++){
                result += groups.get(i).num;
            }
            System.out.println("#" + test_case + " " + result);
        }
    }
}
