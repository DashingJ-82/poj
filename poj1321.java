import java.util.Scanner;


public class poj1321 {

    static int num1;
    static int num2;
    static char[][] chess;  // 棋盘
    static boolean[] col;
    static int count;
    static int res;
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);

        while(true){
            num1 = sc.nextInt();    // 棋盘边长
            num2 = sc.nextInt();    // 需要摆放的棋子数目
            sc.nextLine();
            
            if(num1 == -1 && num2 == -1) break;

            col = new boolean[num1];
            chess = new char[num1][num1];
            count = 0;
            res = 0;

            for(int i = 0; i < num1; i++){
                chess[i] = sc.nextLine().toCharArray();
            }

            DFS(0);
            System.out.println(res);
        }

        sc.close();
    }

    public static void DFS(int row){

        if(row == num1) return;

        for(int i = 0; i < num1; i++){
            if(chess[row][i] == '#' && col[i] == false){
                col[i] = true;
                count++;
                if(count == num2){
                    res += 1;
                }
                DFS(row + 1);
                col[i] = false;
                count--;
            }
        }
        DFS(row + 1);
    }

}
