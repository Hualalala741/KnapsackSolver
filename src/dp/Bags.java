package dp;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Bags {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入物品数量");

        //读取数据
        int number = sc.nextInt();//物品数量
        int[] weight = new int[number + 1];
        int[] value = new int[number + 1];
        weight[0] = 0;
        System.out.println("请输入物品重量");
        for (int i = 0; i < number; i++) {
            weight[i + 1] = sc.nextInt();
        }
        System.out.println("请输入物品价值");
        for (int i = 0; i < number; i++) {
            value[i + 1] = sc.nextInt();
        }
        System.out.println("请输入背包容量");
        int capacity = sc.nextInt();//背包容量

        int[][] m=knapsack(weight, value, capacity);
        showArray(m);
        //showArray(traceback(m,weight,capacity));
//        int[][] arr=knapsack(weight,value,capacity);
        //System.out.println(Arrays.toString(traceback(m, weight, capacity)));//knapsack(weight,value,capacity)
    }
    public static String getbestvalue(int[] weight,int capacity,int[][]m){
        int n = weight.length;
        int i=m[n-1][capacity];
        return String.valueOf(i);
    }

    public static int[][] knapsack(int[] weight, int[] value, int capacity) {
        int n = weight.length;
        int[][] m = new int[n][capacity + 1];
        //第0行0列都是0
        for (int i = 0; i < capacity + 1; i++) {
            m[0][i] = 0;
        }


        for (int i = 0; i < n; i++) {
            m[i][0] = 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < capacity + 1; j++) {
                m[i][j] = m[i - 1][j];
                if (weight[i] <= j) {
                    if (m[i - 1][j - weight[i]] + value[i] > m[i - 1][j]) {
                        m[i][j] = m[i - 1][j - weight[i]] + value[i];
                    }
                }
            }
        }
        return m; //[n-1][capacity]
    }

    public static void showArray(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(" " + a[i][j]);
            }
            System.out.println("");
        }

    }

    public static String traceback(int[][] m,int[] weight,int capacity,int[] num) {
        String snum=new String("最优选择物品编号为：");
        int[] x=new int[m.length];
        x[0]=0;
        int j=capacity;
        for(int i=m.length-1;i>0;i--){
            if (m[i][j]>m[i-1][j]){
                x[i]=1;
                snum=snum+String.valueOf(num[i])+" ";
                j=j-weight[i];
            }
            else {
                x[i]=0;
        }
    }
        return snum;
    }
}

