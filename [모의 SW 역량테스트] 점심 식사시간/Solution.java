import java.util.*;

public class Solution {
    static int N;
    static int[] selected;
    static int[][] matrix;
    static int[][] distance;
    static int result = Integer.MAX_VALUE;
    static ArrayList<Person> people = new ArrayList<>();
    static ArrayList<Stair> stairs = new ArrayList<>();
    static Queue<Integer> stair1 = new LinkedList<>();
    static Queue<Integer> stair2 = new LinkedList<>();
    public static class Person{
        int x,y;
        Person(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static class Stair{
        int x,y,time;
        Stair(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time  = time;
        }
    }

    public static int cal_time(){
        int time = 0;
        int exit_count = 0;

        while(true){
            if(result == time){
                return time;
            }
            if(exit_count == people.size()){
                return time;
            }

            for(int i = 0; i<2; i++){//계단을 내려갈때
                if (i == 0) {
                    int size = stair1.size();
                    for(int j = 0; j<size; j++){
                        int temp = stair1.poll();
                        temp--;
                        if(temp <= 0)
                            exit_count++;
                        else
                            stair1.offer(temp);
                    }
                }
                else{
                    int size = stair2.size();
                    for(int j = 0; j<size; j++){
                        int temp = stair2.poll();
                        temp--;
                        if(temp <= 0)
                            exit_count++;
                        else
                            stair2.offer(temp);
                    }
                }
            }
            for(int i = 0; i<people.size(); i++){//계단에 도착
                if(distance[i][selected[i]] == time){
                    if(selected[i] == 0){
                        if(stair1.size() < 3){
                            stair1.offer(stairs.get(selected[i]).time);
                        }
                        else{
                            stair1.offer(stairs.get(selected[i]).time + stair1.peek());
                        }
                    }
                    else{
                        if(stair2.size() < 3){
                            stair2.offer(stairs.get(selected[i]).time);
                        }
                        else{
                            stair2.offer(stairs.get(selected[i]).time + stair2.peek());
                        }
                    }
                }
            }
            time++;
        }

    }
    public static void solve(int count){
        if(count == people.size()){
            result = Math.min(result,cal_time());
            stair1.clear();
            stair2.clear();
            return;
        }
        selected[count] = 0;
        solve(count+1);
        selected[count] = 1;
        solve(count+1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case<=T; test_case++){
            N = sc.nextInt();
            matrix = new int[N][N];
            people.clear();
            stairs.clear();
            for(int j = 0; j<N; j++){
                for(int i = 0; i<N; i++){
                    matrix[i][j] = sc.nextInt();
                    if(matrix[i][j] == 1)
                        people.add(new Person(i,j));
                    else if(matrix[i][j] >= 2){
                        stairs.add(new Stair(i,j,matrix[i][j]));
                    }
                }
            }
            selected = new int[people.size()];
            distance = new int[people.size()][2];
            for(int j = 0; j<2; j++){
                for(int i = 0; i<people.size(); i++){
                    distance[i][j] = Math.abs(people.get(i).x - stairs.get(j).x) + Math.abs(people.get(i).y - stairs.get(j).y);
                }
            }
            solve(0);
            System.out.println("#" + test_case + " " + result);
            result = Integer.MAX_VALUE;
        }

    }
}
