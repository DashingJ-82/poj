import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class poj3414 {
    static int x, y, z;
    static int[] say;
    static int[][] visited;

    static String[] words = {
        "",
        "DROP(1)",
        "FILL(1)",
        "POUR(1,2)",
        "DROP(2)",
        "FILL(2)",
        "POUR(2,1)"
    };

    static class Node {
        int a1;
        int a2;
        int step;
        int[] way = new int[1003];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        x = sc.nextInt();
        y = sc.nextInt();
        z = sc.nextInt();
        visited = new int[1000][1000];

        int res = BFS();
        if(res == -1){
            System.out.println("impossible");
        }

    }

    public static int BFS(){
        Queue<Node> q = new LinkedList<Node>();
        Node node = new Node();
        node.a1 = 0;
        node.a2 = 0;
        node.step = 0;
        visited[0][0] = 1;
        q.offer(node);
        while (!q.isEmpty()) {
            Node top = q.poll();
            if(top.a1 == z || top.a2 == z){
                System.out.println(top.step);
                for(int i = 1; i <= top.step; i++){
                    System.out.println(words[top.way[i]]);
                }
                return top.step;
            }
            // 这里难点在于判断什么时候进行fill pour 和 drop操作
            for(int i = 0; i < 2; i++){ // 这里是分别对两个水壶进行操作
                if(i == 0){
                    for(int j = 0; j < 3; j++){ // 这里是对水壶的三种操作
                        if(j == 0 && top.a1 != 0){  // drop
                            Node temp = new Node();
                            temp.a1 = 0;
                            temp.a2 = top.a2;
                            temp.step = top.step + 1;
                            System.arraycopy(top.way, 1, temp.way, 1, 1000);
                            temp.way[temp.step] = 1;    // 对应words里面的操作
                            if(visited[temp.a1][temp.a2] == 0){ // 判断有没有访问过
                                visited[temp.a1][temp.a2] = 1;
                                q.add(temp);
                            }

                        }else if(j == 1 && top.a1 != x){    //fill
                            Node temp = new Node();
                            temp.a1 = x;
                            temp.a2 = top.a2;
                            temp.step = top.step + 1;
                            System.arraycopy(top.way, 1, temp.way, 1, 1000);

                            temp.way[temp.step] = 2;
                            if(visited[temp.a1][temp.a2] == 0){
                                visited[temp.a1][temp.a2] = 1;
                                q.add(temp);
                            }

                        }else if(j == 2 && top.a1 != 0 && top.a2 != y){ //pour(1,2)
                            Node temp = new Node();
                            temp.a1 = Math.max(0, top.a1 + top.a2 - y);
                            temp.a2 = Math.min(y, top.a2 + top.a1);
                            temp.step = top.step + 1;
                            System.arraycopy(top.way, 1, temp.way, 1, 1000);

                            temp.way[temp.step] = 3;
                            if(visited[temp.a1][temp.a2] == 0){
                                visited[temp.a1][temp.a2] = 1;
                                q.add(temp);
                            }
                        }
                    }

                }else if(i == 1){
                    for(int j = 0; j < 3; j++){
                        if(j == 0 && top.a2 != 0){  // drop
                            Node temp = new Node();
                            temp.a1 = top.a1;
                            temp.a2 = 0;
                            temp.step = top.step + 1;
                            System.arraycopy(top.way, 1, temp.way, 1, 1000);
                            
                            temp.way[temp.step] = 4;
                            if(visited[temp.a1][temp.a2] == 0){
                                visited[temp.a1][temp.a2] = 1;
                                q.add(temp);
                            }

                        }else if(j == 1 && top.a2 != y){    // fill
                            Node temp = new Node();
                            temp.a1 = top.a1;
                            temp.a2 = y;
                            temp.step = top.step + 1;
                            System.arraycopy(top.way, 1, temp.way, 1, 1000);
                            temp.way[temp.step] = 5;

                            if(visited[temp.a1][temp.a2] == 0){
                                visited[temp.a1][temp.a2] = 1;
                                q.add(temp);
                            }
                        }else if(j == 2 && top.a1 != x && top.a2 != 0){    // pour(2, 1)
                            Node temp = new Node();
                            temp.a1 = Math.min(x, top.a1 + top.a2);
                            temp.a2 = Math.max(0, top.a1 + top.a2 - x);
                            temp.step = top.step + 1;
                            System.arraycopy(top.way, 1, temp.way, 1, 1000);
                            temp.way[temp.step] = 6;
                            if(visited[temp.a1][temp.a2] == 0){
                                visited[temp.a1][temp.a2] = 1;
                                q.add(temp);
                            }
                        }
                    }
                }
            }

            
        }

        return -1;
    }
}
