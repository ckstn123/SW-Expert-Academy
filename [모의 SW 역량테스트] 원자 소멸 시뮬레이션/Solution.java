import java.util.*;

public class Solution {
    static class Atom{
        int x,y,dir,energy;

        Atom(int x, int y, int dir, int energy){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }
    }
    static int N;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static float[][] matrix = new float[4001][4001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            int sum = 0;
            ArrayList<Atom> Atom_list = new ArrayList<>();
            for(int i = 0; i<N; i++){
                int x = sc.nextInt() * 2 + 2000;
                int y = sc.nextInt() * 2 + 2000;
                int dir = sc.nextInt();
                int energy = sc.nextInt();
                matrix[x][y] = energy;
                Atom_list.add(new Atom(x,y,dir,energy));
            }

            while(Atom_list.size() != 0){
                for(int i = 0; i< Atom_list.size(); i++){
                    if(matrix[Atom_list.get(i).x][Atom_list.get(i).y] != Atom_list.get(i).energy){ // 현재 위치의 원소가 이동하기 전에 현재 위치로 이동한 원소가 있을 경우
                        matrix[Atom_list.get(i).x][Atom_list.get(i).y] -= Atom_list.get(i).energy;
                    }
                    else{
                        matrix[Atom_list.get(i).x][Atom_list.get(i).y] = 0; //현재 원소만 위치한다면 현재 위치를 0으로 초기화
                    }
                    Atom_list.get(i).x += dx[Atom_list.get(i).dir];
                    Atom_list.get(i).y += dy[Atom_list.get(i).dir];
                    if(Atom_list.get(i).x >= 0 && Atom_list.get(i).x < 4001 && Atom_list.get(i).y >= 0 && Atom_list.get(i).y < 4001){
                        matrix[Atom_list.get(i).x][Atom_list.get(i).y] += Atom_list.get(i).energy;
                    }
                    else{ //좌표 밖을 벗어나면 더 이상 충돌이 일어나지 않으므로 원소 제거
                        Atom_list.remove(i);
                        i--;
                    }
                }
                for(int i = 0; i< Atom_list.size(); i++){
                    int x = Atom_list.get(i).x;
                    int y = Atom_list.get(i).y;
                    if(matrix[x][y] > Atom_list.get(i).energy){ //현재 위치의 에너지가 원소의 에너지보다 크면 충돌
                        matrix[x][y] = matrix[x][y] - Atom_list.get(i).energy + 0.1f; //충돌이지만 하나만 남으면 에너지가 자기 자신과 똑같아지므로 소수점을 더해준다.
                        if(matrix[x][y] < 1){// 현재 위치에 충돌이 다 끝나게 되면 0.*만 남게 되는데 0으로 초기화해준다.
                            matrix[x][y] = 0;
                        }
                        sum += Atom_list.get(i).energy;//충돌이 일어난 에너지를 더해준다.
                        Atom_list.remove(i);//충돌이 일어난 원소 제거
                        i--;
                    }
                }
            }
            System.out.println("#" + test_case + " " + sum);
        }
    }
}
