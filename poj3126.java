import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class poj3126 {
    static int num1;
    static int num2;
    static int[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int kcase = sc.nextInt();

        while (kcase > 0) {
            kcase--;
            visited = new int[10000];
            num1 = sc.nextInt();
            num2 = sc.nextInt();
            int res = BFS(num1, num2);
            System.out.println(res);
        }

        sc.close();
    }

    public static int BFS(int x, int y){
        if(x == y) return 0;
        int level = 0;

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(x);
        visited[x] = 1; // 需要在入队时就进行标记 不然会TLE

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int tmp = queue.poll();
                if(tmp == y){
                    return level;
                }
                String str = Integer.toString(tmp);
                for(int i = 1; i < 10; i++){
                    StringBuilder builder = new StringBuilder(str);
                    builder.setCharAt(0, (char)(i + '0'));
                    int z = Integer.parseInt(builder.toString());
                    if(judge(z) && visited[z] == 0){
                        visited[z] = 1;
                        queue.offer(z);
                    }
                }

                for(int i = 0; i < 10; i++){
                    StringBuilder builder = new StringBuilder(str);
                    builder.setCharAt(1, (char)(i + '0'));
                    int z = Integer.parseInt(builder.toString());
                    if(judge(z) && visited[z] == 0){
                        visited[z] = 1;
                        queue.offer(z);
                    }
                }

                for(int i = 0; i < 10; i++){
                    StringBuilder builder = new StringBuilder(str);
                    builder.setCharAt(2, (char)(i + '0'));
                    int z = Integer.parseInt(builder.toString());
                    if(judge(z) && visited[z] == 0){
                        visited[z] = 1;
                        queue.offer(z);
                    }
                }

                for(int i = 0; i < 10; i++){
                    StringBuilder builder = new StringBuilder(str);
                    builder.setCharAt(3, (char)(i + '0'));
                    int z = Integer.parseInt(builder.toString());
                    if(judge(z) && visited[z] == 0){
                        visited[z] = 1;
                        queue.offer(z);
                    }
                }
                size--;
            }
            level++; 
        }
        return level;
    }


    // 判断是否是素数
    public static boolean judge(int num){
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0)return false;
        }
        return true;
    }
}
