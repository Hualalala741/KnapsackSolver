package tx;

import com.sun.org.apache.xpath.internal.objects.XNumber;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class txBags{
    private static int[] v;//物品的价值数组
    private static int[] w;//物品的重量数组
    private static int c;//最大可以拿的重量
    private static int n;//物品的个数
    private static int[] uv;//单位价值
    private static int bv;//现有价值
    private static int[] num;
    private static int[] x;

    public static void main(String[] args) {
        int[] weight= new int[]{35, 30, 45, 35, 45, 10, 25};
        int[] value= new int[]{10, 40, 32, 35, 35, 12, 24};
        int[]num=new int[]{1,2,3,4,5,6,7};
        int capacity=150;
        int[] uvalue=new int[weight.length];
        uvalue[0]=0;
        for(int i=1;i<weight.length;i++){
            uvalue[i]=value[i]/weight[i];
        }
        csh(weight,value,capacity,num);
        tx();
        System.out.println(Arrays.toString(uv));
        System.out.println(bv);
    }
    public static String getbestvalue(){
        bv=0;
        tx();
        return String.valueOf(bv);
    }
    public static void csh(int[] weight,int[] value,int capacity ,int[]number){
        //初始化
        int[] uvalue=new int[weight.length];
        //uvalue[0]=0;
        for(int i=0;i<weight.length;i++){
            uvalue[i]=value[i]/weight[i];
        }
        num= number;
        n=weight.length;
        w=weight;
        v=value;
        c=capacity;
        uv=uvalue;
        mp();//降序排序
    }
    public static void mp(){//冒泡排序
        //System.out.println(Arrays.toString(uv));
        for(int i=0;i<uv.length-1;i++){
            for(int j=0;j<uv.length-i-1;j++){
                if(uv[j]<uv[j+1]){//降序
                    int temp1=uv[j+1];
                    uv[j+1]=uv[j];
                    uv[j]=temp1;

                    int temp2=w[j+1];
                    w[j+1]=w[j];
                    w[j]=temp2;

                    int temp3=v[j+1];
                    v[j+1]=v[j];
                    v[j]=temp3;

                    int temp4=num[j+1];
                    num[j+1]=num[j];
                    num[j]=temp4;

                }
            }
        }
        //System.out.println(Arrays.toString(uv));
    }

    public static void tx(){
        //已经排序好了
        x=new int[w.length];//记录01重排后的 最后一位是0
        //Arrays.sort(uv);
        int r=c;
        for(int i=0;i<w.length;i++){//最后一个是0，不用看
            if(w[i]<r){//如果能装进去
                x[i]=1;
                r-=w[i];//剩余容量
                bv+=v[i];//现有价值
            }
        }

    }
    public  static  String getx(){
        String snum=new String("最优选择物品编号为：");
        for(int i=0;i<x.length;i++){//4 0,1,2,3 小于4
            if(x[i]==1) {//23
                snum=snum+String.valueOf(num[i])+" ";
            }
        }
        return snum;
    }
}