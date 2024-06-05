import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class poj2251 {
    static int L, R ,C;
    static char[][][] dungeon;
    static boolean[][][] visited;
    static boolean flag;
    static int res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            L = sc.nextInt();
            R = sc.nextInt();
            C = sc.nextInt();
            sc.nextLine();

            if(L == 0 && R == 0 && C == 0) break;

            res = 0;
            flag = false;
            int x = 0, y = 0, z = 0;
            dungeon = new char[L][R][C];
            visited = new boolean[L][R][C];

            for(int i = 0; i < L; i++){
                for(int j = 0; j < R; j++){
                    dungeon[i][j] = sc.nextLine().toCharArray();
                    for(int k = 0; k < C; k++){
                        if(dungeon[i][j][k] == 'S'){
                            x = i; y = j; z = k;
                        }
                    }
                }
                sc.nextLine();
            }

            BFS(x, y, z, 0);

            if(flag == false){
                System.out.println("Trapped!");
            }else{
                System.out.println("Escaped in " + res + " minute(s).");
            }
        }
        sc.close();
    }

    
    public static void BFS(int x, int y, int z, int s){
        Queue<point> queue = new LinkedList<point>();
        point p = new point(x, y, z, s);
        visited[x][y][z] = true;
        queue.offer(p);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                point temp = queue.poll();
                int xx = temp.x;
                int yy = temp.y;
                int zz = temp.z;
                int ss = temp.step;
                if(dungeon[xx][yy][zz] == 'E'){
                    flag = true;
                    res = ss;
                    return;
                }
        if(xx + 1 < L && dungeon[xx + 1][yy][zz] != '#' && visited[xx + 1][yy][zz] == false){
            visited[xx + 1][yy][zz] = true;
            queue.offer(new point(xx + 1,yy,zz,ss + 1));
        }
        if(xx - 1 >= 0 && dungeon[xx - 1][yy][zz] != '#' && visited[xx - 1][yy][zz] == false){
            visited[xx - 1][yy][zz] = true;
            queue.offer(new point(xx - 1,yy,zz,ss + 1));
        }
        if(yy + 1 < R && dungeon[xx][yy + 1][zz] != '#' && visited[xx][yy + 1][zz] == false){
            visited[xx][yy + 1][zz] = true;
            queue.offer(new point(xx,yy + 1,zz,ss + 1));
        }
        if(yy - 1 >= 0 && dungeon[xx][yy - 1][zz] != '#' && visited[xx][yy - 1][zz] == false){
            visited[xx][yy - 1][zz] = true;
            queue.offer(new point(xx,yy - 1,zz,ss + 1));
        }
        if(zz + 1 < C && dungeon[xx][yy][zz + 1] != '#' && visited[xx][yy][zz + 1] == false){
            visited[xx][yy][zz + 1] = true;
            queue.offer(new point(xx,yy,zz + 1,ss + 1));
        }
        if(zz - 1 >= 0 && dungeon[xx][yy][zz - 1] != '#' && visited[xx][yy][zz - 1] == false){
            visited[xx][yy][zz - 1] = true;
            queue.offer(new point(xx,yy,zz - 1,ss + 1));
        }
                size--;
            }
        }

    }

    public static class point {
        public int x, y, z, step;
        public point(int a, int b, int c, int d){
            x = a; y = b; z = c; step = d;
        }
        
    }
}
