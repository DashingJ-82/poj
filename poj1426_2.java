import java.util.Arrays;
import java.util.Scanner;

public class poj1426_2 {

    static int input;
    static int count;
    static int ttmp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            input = sc.nextInt();
            if(input == 0) break;
            if(input == 1){
                System.out.println(1);
                continue;
            }
            count = 0;
            ttmp = 0;
            result(input);
            int[] output = new int[count + 1];
            output[count] = 1;
            
            while(ttmp != 0){
                count = 0;
                result(ttmp);
                output[count] = 1;
            }
            
            for(int i = output.length - 1; i >= 0; i--){
                System.out.print(output[i]);
            }
            System.out.println("");
            
        }
        sc.close();
    }

    public static void result(int num){
       if(num == 1){
            count = 0;
            ttmp = 0;
        }
        int tmp = 1;
        while(tmp < num){
            tmp = tmp * 2;
            count++;
        }
        if( tmp > num){
            count--;
            tmp = tmp / 2;
        }
        ttmp = num - tmp;
    }
    
}


