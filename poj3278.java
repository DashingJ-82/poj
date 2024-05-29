import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class poj3278 {
    static int MAX = 100000;    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        // 利用visited数组防止2次访问   注意边界值！！！！
        boolean[] visited = new boolean[MAX + 1];

        Queue<Integer> queue = new LinkedList<Integer>();
        int res = 0;
        queue.offer(x);
        visited[x] = true;

        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                int num = queue.poll();
                if(num == y){
                    System.out.println(res);
                    return;
                }
                if(num - 1 >= 0 && visited[num - 1] == false){
                    queue.offer(num - 1);
                    visited[num - 1] = true;
                }
                if(num + 1 <= MAX && visited[num + 1] == false){
                    queue.offer(num + 1);
                    visited[num + 1] = true;
                }
                if(num * 2 <= MAX && visited[num * 2] == false){
                    queue.offer(num * 2);
                    visited[num * 2] = true;
                }
                size--;
            }
            res++;
        }
        sc.close();
    }
}
