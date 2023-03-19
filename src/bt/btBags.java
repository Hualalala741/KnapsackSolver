package bt;

import java.util.Arrays;
import java.util.Scanner;

public class btBags {
    private static int[] v;//物品的价值数组
    private static int[] w;//物品的重量数组
    private static int c;//最大可以拿的重量
    private static int n;//物品的个数

    private static int pw;//当前的重量
    private static int pv;//当前的价值
    private static int bv;//目前最优装载的价值
    private static int r;//剩余物品的价值

    private static int[] px;//存放当前解
    private static int[] bx;//存放最终解


    public static void csh(int[] weight,int[] value,int capacity ){
        //初始化
        n=weight.length-1;
        w=weight;
        v=value;
        c=capacity;
        pw=0;
        pv=0;
        bv=0;
        bx=new int[n+1];
        px=new int[n+1];
        r=0;
        for(int i=0;i<=n;i++){
            r+=v[i];
        }
    }
    public static void BackTrack(int i){//初始0节点？但输入是1，从要走到的下一个开始算
        if (i>n){//到达叶节点
            if (pv>bv){
                //System.out.println("改xxxxxxx了！！！！！！！！！");
                bv=pv;//现在的v就是最佳的
                for(int k=0;k<=n;k++){
                    bx[k]=px[k];
                }
            }
            return;
        }
        r-=v[i];//判断第i个物体
        if (pw+w[i]<=c) {//当前的总物重+第i个物重<=c
            //进入左子树
            px[i]=1;
            pw += w[i];
            pv += v[i];
            BackTrack(i + 1);//下一个层。。
            pw -= w[i];
            pv -= v[i];//回溯
        }
        if (pv+r>bv){//如果现在的价值+剩下所有的价值有机会大于bestvalue 那么就走右边
            //进入右子树
            px[i]=0;
            BackTrack(i+1);
        }
        r += v[i];//这里结束也是return吧
    }
    public static String getbestvalue(){
        String bbv=String.valueOf(bv);
        return bbv;
    }
    public static String getbestx(int[] num){
        String snum=new String("最优选择物品编号为：");
        for(int i=0;i<bx.length;i++){//4 0,1,2,3 小于4
             if(bx[i]==1) {//23
                snum=snum+String.valueOf(num[i])+" ";
            }
        }
        return snum;
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入物品数量");
//
//        //读取数据
//        int number = sc.nextInt();//物品数量
//        int[] weight = new int[number + 1];
//        int[] value = new int[number + 1];
//        weight[0] = 0;
//        System.out.println("请输入物品重量");
//        for (int i = 0; i < number; i++) {
//            weight[i + 1] = sc.nextInt();
//        }
//        System.out.println("请输入物品价值");
//        for (int i = 0; i < number; i++) {
//            value[i + 1] = sc.nextInt();
//        }
//        System.out.println("请输入背包容量");
//        int capacity = sc.nextInt();//背包容量

        int[] weight= new int[]{0,12,35,23,34,2};//35, 30, 45, 35, 45, 10, 25
        int[] value= new int[]{0,46,56,25,34,34};//10, 40, 32, 35, 35, 12, 24
        int[]num=new int[]{0,1,2,3,7,9};
        int capacity=30;
        csh(weight,value,capacity);
        BackTrack(1);
        System.out.println(bv);
        System.out.println(Arrays.toString(bx));
        System.out.println(getbestx(num));


//        csh(weight,value,capacity);
//        BackTrack(1);
//        System.out.println(bv);
//        System.out.println(Arrays.toString(bx));
//        System.out.println(getbestx(num));
    }

}
