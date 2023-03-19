package udp;

public class Bags {
//    public static void main(String[] args) {
//        int[] weight= new int[]{0,3, 4, 5};
//        int[] value= new int[]{0,3, 4, 5};
//        int capacity=5;
//        System.out.println(knapsack(weight,value,capacity));
//
//    }

//    public static int knapsack(int[] weight,int[] value,int c){
//        int n = weight.length;
//        int[] head=new int[n+1];
//        int[][] p=new int[n][n];
//        head[n]=0;
//        p[0][0]=0;
//        p[0][1]=0;
//        int left=0,right=0,next=1;
//        head[n-1]=1;
//        for(int i=n-1;i>=1;i--){//p[][]
//            int k=left;
//            for (int j=left;j<=right;j++) {
//                if (p[j][0] + weight[i] > c) {
//                    break;
//                }
//                int y = p[j][0] + weight[i];//第i个物品
//                int m = p[j][1] + value[i];
//                while (k <= right && p[k][0] < y) {
//                    p[next][0] = p[k][0];
//                    p[next++][1] = p[k++][1];
//                }
//                if (k <= right && p[k][0] == y) {
//                    if (m < p[k][1]) {
//                        m = p[k][1];
//                    }
//                    k++;
//                }
//                if (m > p[next - 1][1]) {
//                    p[next][0] = y;
//                    p[next++][1] = m;
//                }
//                while (k < right && p[k][1] <= p[next - 1][1]) {
//                    k++;
//                }
//            }
//            while (k<right) {
//                p[next][0] = p[k][0];
//                p[next++][1] = p[k++][1];
//            }
//                left = right+1;
//                right = next-1;
//                head[i-1] = next;
//            }
//            Traceback(n, weight, value, p, head);
//            return p[next-1][1];
//    }

//    public static void Traceback(int n, int[] weight, int[] value, int[][]p, int[]head) {
//        int[] x=new int[weight.length];
//        x[0]=0;
//        int j = p[head[0]-1][0];
//        int m = p[head[0]-1][1];
//        for (int i=1; i <= n; i++) {
//            x[i]=0;
//            for (int k=head[i+1]; k <= head[i]-1; k++) {
//                if (p[k][0]+weight[i] == j && p[k][1]+value[i] == m) {
//                    x[i] = 1;
//                    j=p[k][0];
//                    m=p[k][1];
//                    break;
//                }
//            }
//        }
//    }
static int[][] p;
    static int[] x;
    static int[] head;

    public static int[][] getp(int[]w,int c){
        p=new int[w.length*c][2];
        return p;
    }


    public static void main(String[] args){
        int[] w={3,4,5,6,7};      //可自行修改代码使w,v,c为用户进行输入
        int[] v={4,5,6,7,8};
        int[] num={1,2,3,4,5};
        int c=10;
        p=new int[w.length*c][2];     //((1+(w.length+1))*(w.length+1))/2 p用于保存所有可能的最优值，行数为（（1+项数加1）*项数加1）/2，列数为2，（项数加1，因为假设除了存在w.length个物品外，还存在一个第w.length号物品，且重量为0，价值为0））
        x=new int[w.length+1];             //在求得最优值时，x[i]记录第i个物品是否放进背包，0为不放进，1为放进去
        int[] head=new int[w.length+2];     //head记录每个p[i]方式的记录的第一个跳跃点，具体看代码前面注释
        Bags pack =new Bags();
        System.out.println("具体实例为w={2,2,6,5,4};v={6,3,5,4,6};c=10时，有：");
        System.out.println("求得的最优值为："+pack.knapsack(w,v,c,p));  //求解最优值
        //System.out.println(show_traceback(w,v,c,num));
        //pack.traceback(w,v,c,p,head,x);                                     //求得最优值后，求取放进背包的物品
        //pack.show_traceback();                                              //求得最优值后，显示放进背包的物品
    }


    //求得最优值后，显示被放进背包的物品
    public static String show_traceback(int[] w, int[] v, int c, int[] num) {
        //int[] head=new int[w.length+2];
        traceback(w,v,c,p);
        String snum=new String("最优选择物品编号为：");
        for(int i=2;i<x.length;i++){
            if(x[i]==1) {//i=1
                snum=snum+String.valueOf(num[i-1])+" ";
            }
        }
        return snum;
    }

    /*注意：以下注释的p[i]均指代码前注解所指的p[i],而不是指数组p的第i行*/
    //p[i][0]保存当前最优值的质量（即可能占用的背包空间），p[i][1]保存当前最优值的价值
    public int knapsack(int[] w,int[] v,int c,int[][] p){
        //int[] head=new int[w.length+2];
        head=new int[w.length+2];
        int n=v.length-1;
        head[n+2] = 0; //指向p[6]的第一个元素(0,0)在p中的位置
        p[0][0] = 0;  //默认存在第六个物品，质量为0，价值为0，放进背包
        p[0][1] =0;
        int left = 0,right = 0,   //left和right分别指向p[i]的第一个元素和最后一个元素
                next = 1;             //next指向p中接下来还没存数据的空行
        head[n+1] =1;             //指向p[5]的第一个元素(0,0)在p中的位置
        for(int i=n;i>=0;i--){    //从倒数第一个物品开始，对物品逐个进行判断是否放进背包，注：w[0]为第一个物品的重量
            int k=left;
            for(int j=left;j<=right;j++){
                if(p[j][0]+w[i]>c) break;    //当第i个物品加进背包超出10（背包承受最大重量），跳出循环，不放进去
                int y=p[j][0]+w[i],       //当第i个物品加进背包时，产生重量保存在y中
                        m=p[j][1]+v[i];       //当第i个物品加进背包时，产生价值保存在m中
                while(k<=right && p[k][0]<y){//当不将第i个物品加进背包的重量小于加入背包的重量时，直接添加p[k][0]和p[k][1]到next指向的行上
                    p[next][0] = p[k][0];
                    p[next++][1] = p[k++][1];//赋值后,k,next先后分别进行自增
                }
                if(k<=right && p[k][0]==y){  //当不将第i个物品加进背包与加入背包的重量相同时
                    if(m<p[k][1]){           //若不将第i个物品加进背包产生的价值更大，
                        m = p[k][1];         //则把第i个物品没有加进背包时产生的价值赋值给m
                    }
                    k++;
                }
                if(m>p[next-1][1]){    //当将第i个物品加进背包产生的价值更大，将其加入背包
                    p[next][0]=y;
                    p[next++][1]=m;
                }
                while(k<=right && p[k][1]<=p[next-1][1]){k++;}      //当不将第i个物品加进背包产生的价值更大，k++
            }
            while(k<=right){           //将余下的值（k到right,即没有进行比较的数据），直接追加到p上
                p[next][0] = p[k][0];
                p[next++][1] = p[k++][1];
            }
            left = right+1;   //指向当前p[i]的第一个元素(0,0)的位置
            right = next-1;   //指向当前p[i]的最后一个元素的位置
            head[i] = next;   //记录下一组p[i]的第一个元素(0,0)的位置
        }
        return p[next-1][1];        //返回最优值
    }

    //分别对每个物品是否在背包中
    public static void traceback(int[] w, int[] v, int c, int[][] p){

        int n = w.length-1;
        double j = p[head[0]-1][0],   //j为最优值的重量
                m = p[head[0]-1][1];   //m为最优值的价值
        x=new int[w.length+1];
        x[0]=0;
        for(int i=1;i<=n+1;i++){
            x[i] = 0;            //设定第i个物品不在背包中，即x[i] = 0
            for(int k = head[i+1];k<=head[i]-1;k++){  //head[i+1]head[i]-1依次分别为p[2],p[3],p[4],p[5],p[6]的第一个元素和最后一个元素的位置
                if(p[k][0]+w[i-1]==j && p[k][1]+v[i-1]==m){  //eg:(6,9)+(2,6)=(8,15),即第一个物品在背包中，(4,6)+(2,3)=(6,9),即第二个物品在背包中，(0,0)+(4,6)=(4,6),即第五个物品在背包中
                    x[i]=1;      //第i个物品位于背包中，可产生最优值，即x[i] = 1
                    j = p[k][0]; //j变化为：j=8=>>j=6=>>j=4
                    m = p[k][1]; //m变化为：m=15=>>m=9=>>m=6
                    break;      //跳出循环，从而对下一个物品进行判断是否在背包中
                }
            }
        }
    }
}
