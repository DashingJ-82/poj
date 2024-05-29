import java.util.*;

public class poj2488 {

    static int[][] ss;
    static String[] tmp;
    static boolean success;
    // 矩阵的长宽
    static int[] square = new int[2]; // 记录棋盘的长宽

    static int count;

    static String[] A =new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    // 注意xy跳的顺序
    static int[] xx = new int[]{-1, +1, -2, +2, -2, +2, -1, +1};
    static int[] yy = new int[]{-2, -2, -1, -1, +1, +1, +2, +2};

    public static void DFS(int x, int y, int count){
        
        // 进入前已经判断过 所以可以直接标记
        // 需要把x，y转换
        tmp[count] = (A[y] + (x + 1));
        ss[x][y] = 1;
        // 判断是否已经有结果
        if (count + 1 == square[0] * square[1]) {
            success = true;
            return;
        }
        // 然后继续递归其他的
        for(int i = 0; i < 8; i++){
            int xn = x + xx[i];
            int yn = y + yy[i];
            if(xn < 0 || xn >= square[0] || yn < 0 || yn >= square[1] || ss[xn][yn] == 1){
                continue;
            }
            DFS(xn, yn, count + 1);
            // 终止循环
            if (success) return;
        }
        // 这里是回溯 一个子树走不到success的时候 需要退回到上一步
        ss[x][y] = 0;
        tmp[count] = null;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // 记录输入的组数
        int num = sc.nextInt();

        for(int i = 1; i <= num; i++){
            square[0] = sc.nextInt();
            square[1] = sc.nextInt();
            
            int x = square[0];
            int y = square[1];

            success = false;
            
            String res = new String();
            ss = new int[x][y];
            tmp = new String[x * y];
            count = 0;

            DFS(0, 0, count);

            if(success){
                for(int j = 0; j < x * y; j++){
                    res += tmp[j];
                }
            }else {
                res = "impossible";
            }
            System.out.println("Scenario #" + i + ":");
			System.out.println(res.toString() + "\n");
        }
        sc.close();
    }
}