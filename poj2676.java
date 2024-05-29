import java.util.Scanner;

public class poj2676 {

    // 数独矩阵
    static int[][] sudoku = new int[9][9];
    static int[][] flag = new int[90][2];
    static int[][] possible = new int [90][10];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine(); // 读取一个换行符
        // count 要 复原 ！！！
        int count = 0;
        char[][] demo = new char[9][9];
        for(int i = 0; i < num; i++){
            count = 0;

            // 循环读入数独
            for(int j = 0; j < demo.length; j++){
                demo[j] = sc.nextLine().toCharArray();
                for(int k = 0; k < demo[j].length; k++){
                    sudoku[j][k] = demo[j][k] - '0';
                    if(sudoku[j][k] == 0){
                        flag[count][0] = j;
                        flag[count][1] = k;
                        count++;
                    } 
                }
            }

            // 这里的count - 1 可以直接用于flag
            DFS(count - 1);

            // 循环输出数独
            for(int j = 0; j < sudoku.length; j++){
                for(int k = 0; k < sudoku[1].length; k++){
                    System.out.print(sudoku[j][k]);
                }
                System.out.println("");
            }
        }
        sc.close();
    }

    public static boolean DFS(int count){
        if(count < 0){
            return true;
        }
        for(int i = 1; i < 10; i++){
            int x = flag[count][0];
            int y = flag[count][1];
            if(judge(x, y, i)){
                sudoku[x][y] = i;
                if(DFS(count - 1)){
                    return true;
                }
                sudoku[x][y] = 0;
            }
        }
        return false;
    }

    // 判断此位置是否符合数独规则
    public static boolean judge(int x, int y, int tmp){
        // 同行、同列有相同
        for(int i = 0; i < sudoku.length; i++){
            if(sudoku[x][i] == tmp || sudoku[i][y] == tmp) return false;
        }
        // 小九宫格 有相同
        for(int i = x / 3 * 3; i < (x / 3 + 1) * 3; i++){
            for(int j = y / 3 * 3; j < (y / 3 + 1) * 3; j++){
                if(sudoku[i][j] == tmp) return false;
            }
        }
        return true;
    }
}
