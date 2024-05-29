import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class poj3083{

    static int maze_left;
    static int maze_right;
    static int maze_min;
    // 矩阵
    static char[][] maze;
    // w d
    static int[] square = new int[2];
    static int[] direct = new int[2];

    static boolean hasAns;

    static Queue<int[]> queue = new ArrayDeque<int[]>();
   
    // 寻找起始点
    public static void FindDirect(){
        for(int i = 0; i < square[1]; i++){
            for(int j = 0; j < square[0]; j++){
                if(maze[i][j] == 'S'){
                    direct[0] = i;
                    direct[1] = j;
                }
            }
        }
    }

    // 左 优先搜索 似乎不需要回溯
    public static void DFS_L(int x, int y, int step){
        maze_left += 1;
        if(maze[x][y] == 'E' || hasAns){
            hasAns = true;
            return;
        }
        if(step == 0){
            if( x >= 0 && x < square[1] && y - 1 >= 0 && y - 1 < square[0] && (maze[x][y - 1] == '.' || maze[x][y - 1] == 'E')){
                DFS_L(x, y - 1, 1);
            }else if( x - 1 >= 0 && x - 1 < square[1] && y >= 0 && y < square[0] && (maze[x - 1][y] == '.' || maze[x - 1][y] == 'E')){
                DFS_L(x - 1, y, 0);
            }else if( x >= 0 && x < square[1] && y + 1 >= 0 && y + 1 < square[0] && (maze[x][y + 1] == '.' || maze[x][y + 1] == 'E')){
                DFS_L(x, y + 1, 3);
            }else if(x + 1 >= 0 && x + 1 < square[1] && y >= 0 && y < square[0] && (maze[x + 1][y] == '.' || maze[x + 1][y] == 'E')){
                DFS_L(x + 1, y, 2);
            }
        }else if(step == 1){
            if(x + 1 >= 0 && x + 1 < square[1] && y >= 0 && y < square[0] && (maze[x + 1][y] == '.' || maze[x + 1][y] == 'E')){
                DFS_L(x + 1, y, 2);
            }else if( x >= 0 && x < square[1] && y - 1 >= 0 && y - 1 < square[0] && (maze[x][y - 1] == '.' || maze[x][y - 1] == 'E')){
                DFS_L(x, y - 1, 1);
            }else if( x - 1 >= 0 && x - 1 < square[1] && y >= 0 && y < square[0] && (maze[x - 1][y] == '.' || maze[x - 1][y] == 'E')){
                DFS_L(x - 1, y, 0);
            }else if( x >= 0 && x < square[1] && y + 1 >= 0 && y + 1 < square[0] && (maze[x][y + 1] == '.' || maze[x][y + 1] == 'E')){
                DFS_L(x, y + 1, 3);
            } 
        }else if(step == 2){
            if( x >= 0 && x < square[1] && y + 1 >= 0 && y + 1 < square[0] && (maze[x][y + 1] == '.' || maze[x][y + 1] == 'E')){
                DFS_L(x, y + 1, 3);
            }else if(x + 1 >= 0 && x + 1 < square[1] && y >= 0 && y < square[0] && (maze[x + 1][y] == '.' || maze[x + 1][y] == 'E')){
                DFS_L(x + 1, y, 2);
            }else if( x >= 0 && x < square[1] && y - 1 >= 0 && y - 1 < square[0] && (maze[x][y - 1] == '.' || maze[x][y - 1] == 'E')){
                DFS_L(x, y - 1, 1);
            }else if( x - 1 >= 0 && x - 1 < square[1] && y >= 0 && y < square[0] && (maze[x - 1][y] == '.' || maze[x - 1][y] == 'E')){
                DFS_L(x - 1, y, 0);
            } 
        }else if(step == 3){
            if(x - 1 >= 0 && x - 1 < square[1] && y >= 0 && y < square[0] && (maze[x - 1][y] == '.' || maze[x - 1][y] == 'E')) {
                DFS_L(x - 1, y, 0);
            }else if( x >= 0 && x < square[1] && y + 1 >= 0 && y + 1 < square[0] && (maze[x][y + 1] == '.' || maze[x][y + 1] == 'E')){
                DFS_L(x, y + 1, 3);
            }else if(x + 1 >= 0 && x + 1 < square[1] && y >= 0 && y < square[0] && (maze[x + 1][y] == '.' || maze[x + 1][y] == 'E')){
                DFS_L(x + 1, y, 2);
            }else if( x >= 0 && x < square[1] && y - 1 >= 0 && y - 1 < square[0] && (maze[x][y - 1] == '.' || maze[x][y - 1] == 'E')){
                DFS_L(x, y - 1, 1);
            } 
        }
    }

    // 右 优先搜索
    public static void DFS_R(int x, int y, int step){
        maze_right += 1;
        if (maze[x][y] == 'E' || hasAns) {
            hasAns = true;
            return;
        }
        if(step == 0){
            if( x >= 0 && x < square[1] && y + 1 >= 0 && y + 1 < square[0] && (maze[x][y + 1] == '.' || maze[x][y + 1] == 'E')){
                DFS_R(x, y + 1, 3);
            }else if( x - 1 >= 0 && x - 1 < square[1] && y >= 0 && y < square[0] && (maze[x - 1][y] == '.' || maze[x - 1][y] == 'E')){
                DFS_R(x - 1, y, 0);
            }else if( x >= 0 && x < square[1] && y - 1 >= 0 && y - 1 < square[0] && (maze[x][y - 1] == '.' || maze[x][y - 1] == 'E')){
                DFS_R(x, y - 1, 1);
            }else if(x + 1 >= 0 && x + 1 < square[1] && y >= 0 && y < square[0] && (maze[x + 1][y] == '.' || maze[x + 1][y] == 'E')){
                DFS_R(x + 1, y, 2);
            }
        }else if(step == 1){
            if( x - 1 >= 0 && x - 1 < square[1] && y >= 0 && y < square[0] && (maze[x - 1][y] == '.' || maze[x - 1][y] == 'E')){
                DFS_R(x - 1, y, 0);
            }else if( x >= 0 && x < square[1] && y - 1 >= 0 && y - 1 < square[0] && (maze[x][y - 1] == '.' || maze[x][y - 1] == 'E')){
                DFS_R(x, y - 1, 1);
            }else if(x + 1 >= 0 && x + 1 < square[1] && y >= 0 && y < square[0] && (maze[x + 1][y] == '.' || maze[x + 1][y] == 'E')){
                DFS_R(x + 1, y, 2);
            }else if( x >= 0 && x < square[1] && y + 1 >= 0 && y + 1 < square[0] && (maze[x][y + 1] == '.' || maze[x][y + 1] == 'E')){
                DFS_R(x, y + 1, 3);
            }
        }else if(step == 2){
            if( x >= 0 && x < square[1] && y - 1 >= 0 && y - 1 < square[0] && (maze[x][y - 1] == '.' || maze[x][y - 1] == 'E')){
                DFS_R(x, y - 1, 1);
            }else if(x + 1 >= 0 && x + 1 < square[1] && y >= 0 && y < square[0] && (maze[x + 1][y] == '.' || maze[x + 1][y] == 'E')){
                DFS_R(x + 1, y, 2);
            }else if( x >= 0 && x < square[1] && y + 1 >= 0 && y + 1 < square[0] && (maze[x][y + 1] == '.' || maze[x][y + 1] == 'E')){
                DFS_R(x, y + 1, 3);
            }else if( x - 1 >= 0 && x - 1 < square[1] && y >= 0 && y < square[0] && (maze[x - 1][y] == '.' || maze[x - 1][y] == 'E')){
                DFS_R(x - 1, y, 0);
            }
        }else if(step == 3){
            if(x + 1 >= 0 && x + 1 < square[1] && y >= 0 && y < square[0] && (maze[x + 1][y] == '.' || maze[x + 1][y] == 'E')){
                DFS_R(x + 1, y, 2);
            }else if( x >= 0 && x < square[1] && y + 1 >= 0 && y + 1 < square[0] && (maze[x][y + 1] == '.' || maze[x][y + 1] == 'E')){
                DFS_R(x, y + 1, 3);
            }else if(x - 1 >= 0 && x - 1 < square[1] && y >= 0 && y < square[0] && (maze[x - 1][y] == '.' || maze[x - 1][y] == 'E')){
                DFS_R(x - 1, y, 0);
            }else if( x >= 0 && x < square[1] && y - 1 >= 0 && y - 1 < square[0] && (maze[x][y - 1] == '.' || maze[x][y - 1] == 'E')){
                DFS_R(x, y - 1, 1);
            }
        }
    }

    // 最短路径 多叉树 找叶子
    public static void BFS(){
        // 这里标签记录路径
        int[][] flag = new int[square[1]][square[0]];
        queue.clear();
        queue.offer(new int[]{direct[0], direct[1], 1});

        int dist = 0; // 记录层数
        int[] poll = new int[3];

        while (!queue.isEmpty()) {
            // 判断上下左右是否可以走
            poll = queue.poll();
            dist = poll[2];
            if(maze[poll[0]][poll[1]] == 'E'){
                maze_min = dist;
                return;
            }
            //BFS记得Visit数组,对于访问过的节点,不要再加入到队列中,否则会内存溢出
            if(flag[poll[0]][poll[1]] == 1){
                continue;
            }

            flag[poll[0]][poll[1]] = 1;

            if(poll[0] - 1 >= 0 && poll[0] - 1 < square[1] && poll[1] >= 0 && poll[1] < square[0] && (maze[poll[0] - 1][poll[1]] == '.' || maze[poll[0] - 1][poll[1]] == 'E') && flag[poll[0] - 1][poll[1]] == 0){
                queue.offer(new int[]{poll[0] - 1, poll[1], dist + 1});
            }
            if(poll[0] + 1 >= 0 && poll[0] + 1 < square[1] && poll[1] >= 0 && poll[1] < square[0] && (maze[poll[0] + 1][poll[1]] == '.' || maze[poll[0] + 1][poll[1]] == 'E') && flag[poll[0] + 1][poll[1]] == 0){
                queue.offer(new int[]{poll[0] + 1, poll[1], dist + 1});
            }
            if(poll[0] >= 0 && poll[0] < square[1] && poll[1] - 1 >= 0 && poll[1] - 1 < square[0] && (maze[poll[0]][poll[1] - 1] == '.' || maze[poll[0]][poll[1] - 1] == 'E') && flag[poll[0]][poll[1] - 1] == 0){
                queue.offer(new int[]{poll[0], poll[1] - 1, dist + 1});
            }
            if(poll[0] >= 0 && poll[0] < square[1] && poll[1] + 1 >= 0 && poll[1] + 1 < square[0] && (maze[poll[0]][poll[1] + 1] == '.' || maze[poll[0]][poll[1] + 1] == 'E') && flag[poll[0]][poll[1] + 1] == 0){
                queue.offer(new int[]{poll[0], poll[1] + 1, dist + 1});
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // 读入总数
        int num = Integer.parseInt(sc.nextLine());
        // 循环读入 循环输出
        for(int i = 0; i < num; i++){
            // 读入maze长宽
            square[0] = sc.nextInt();
            square[1] = sc.nextInt();
            sc.nextLine();
            // square = sc.nextLine().toCharArray();
            maze = new char[square[1]][square[0]];
            // 读入maze
            for(int j =0; j < square[1]; j++){
                maze[j] = sc.nextLine().toCharArray();
            }
            maze_left = 0; maze_right = 0; maze_min = (square[1]) * (square[0]);
            // 找到S的坐标
            FindDirect();
            // 判断初始方向
            int step = 0;
            if(direct[0] == 0) step = 2;               // 代表向下
            if(direct[0] == square[1] - 1) step = 0; // 代表向上
            if(direct[1] == 0) step = 3;               // 代表向右
            if(direct[1] == square[1] - 1) step = 1; // 代表向左
            // 进行左、右、最短操作
            hasAns = false;
            DFS_L(direct[0], direct[1], step);
            hasAns = false;
            DFS_R(direct[0], direct[1], step);
            BFS();
            System.out.println(maze_left + " " + maze_right + " " + maze_min);
        }
        sc.close();
    }
}
