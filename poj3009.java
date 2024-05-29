import java.util.Scanner;

public class poj3009 {
    
    static int row; // 行
    static int col; // 列
    static int[][] square;
    static int[][] coordinate;
    static int minsteps;
    static char[] direct = {'d', 'r', 'u', 'l'};
    static int[] tmp = new int[2];  // 用于记录当前位置

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (true) {
            col = sc.nextInt();
            row = sc.nextInt();
            coordinate = new int[2][2];
            minsteps = col * row + 1;

            if(col == 0 || row == 0) break;

            square = new int[row][col];
            for(int i = 0; i < row; i++){
                for(int j = 0; j < col; j++){
                    square[i][j] = sc.nextInt();
                    if(square[i][j] == 2){
                        coordinate[0][0] = i;
                        coordinate[0][1] = j;
                    }
                    if(square[i][j] == 3){
                        coordinate[1][0] = i;
                        coordinate[1][1] = j;
                    }
                }
            }
            tmp[0] = coordinate[0][0];
            tmp[1] = coordinate[0][1];
            DFS(coordinate[0][0], coordinate[0][1], 0);

            if(minsteps == col * row + 1){
                System.out.println(-1);
            }else{
                System.out.println(minsteps);
            }
        }

        sc.close();
    }

    
    public static void DFS(int x, int y, int steps){
        // 超过10步 直接结束
        if(steps > 10) return;
        
        if(square[x][y] == 3){
            if(minsteps > steps){
                minsteps = steps;
            }
            return;
        }
        // direct 顺序为 d r u l
        for(int i = 0; i < direct.length; i++){
            // 判断各个方向是否可以走 如果可以就继续
            int aa = tmp[0];
            int bb = tmp[1];
            if(check(x, y, direct[i])){
                int xx = tmp[0]; 
                int yy = tmp[1];
                // 这里把1 修改为0
                if(direct[i] == 'd'){
                    if(square[xx][yy] != 3){
                        square[xx + 1][yy] = 0;
                    }
                }else if(direct[i] == 'r'){
                    if(square[xx][yy] != 3){
                        square[xx][yy + 1] = 0;
                    }
                }else if(direct[i] == 'u'){
                    if(square[xx][yy] != 3){
                        square[xx - 1][yy] = 0;
                    }
                }else if(direct[i] == 'l'){
                    if(square[xx][yy] != 3){
                        square[xx][yy - 1] = 0;
                    }
                }

                DFS(xx, yy, steps + 1);
                // 这里把修改的0 复原为 1
                if(direct[i] == 'd'){
                    if(square[xx][yy] != 3){
                        square[xx + 1][yy] = 1;
                    }
                }else if(direct[i] == 'r'){
                    if(square[xx][yy] != 3){
                        square[xx][yy + 1] = 1;
                    }
                }else if(direct[i] == 'u'){
                    if(square[xx][yy] != 3){
                        square[xx - 1][yy] = 1;
                    }
                }else if(direct[i] == 'l'){
                    if(square[xx][yy] != 3){
                        square[xx][yy - 1] = 1;
                    }
                }

                tmp[0] = aa;
                tmp[1] = bb;
            }

        }

    }
    // 判断是否可以走
    public static boolean check(int x, int y, char direct){
        if(direct == 'd'){
            if( x == row - 1) return false;
            int tmpx = tmp[0];
            int tmpy = tmp[1];
            for(int i = x + 1; i < row; i++){
                if(square[i][y] == 1){
                    tmp[0] = i - 1;
                    tmp[1] = y;
                    break;
                    
                }
                if(square[i][y] == 3){
                    tmp[0] = i;
                    tmp[1] = y;
                    break;
                }
            }
            if(tmp[0] == tmpx){
                return false;
            }else{
                return true;
            }
        }
        else if(direct == 'r'){
            if( y == col - 1) return false;
            int tmpx = tmp[0];
            int tmpy = tmp[1];
            for(int i = y + 1; i < col; i++){
                if(square[x][i] == 1){
                    tmp[0] = x;
                    tmp[1] = i - 1;
                    break;
                }
                if(square[x][i] == 3){
                    tmp[0] = x;
                    tmp[1] = i;
                    break;
                }
            }
            if(tmp[1] == tmpy){
                return false;
            }else{
                return true;
            }
        }
        else if(direct == 'u'){
            if( x == 0) return false;
            int tmpx = tmp[0];
            int tmpy = tmp[1];
            for(int i = x - 1; i >= 0; i--){
                if(square[i][y] == 1){
                    tmp[0] = i + 1;
                    tmp[1] = y;
                    break;
                    
                }
                if(square[i][y] == 3){
                    tmp[0] = i;
                    tmp[1] = y;
                    break;
                }
            }
            if(tmp[0] == tmpx){
                return false;
            }else{
                return true;
            }
        }
        else if(direct == 'l'){
            if( y == 0) return false;
            int tmpx = tmp[0];
            int tmpy = tmp[1];
            for(int i = y - 1; i >= 0; i--){
                if(square[x][i] == 1){
                    tmp[0] = x;
                    tmp[1] = i + 1;
                    break;
                }
                if(square[x][i] == 3){
                    tmp[0] = x;
                    tmp[1] = i;
                    break;
                }
            }
            if(tmp[1] == tmpy){
                return false;
            }else{
                return true;
            }
        }
        return false;
    }
}
