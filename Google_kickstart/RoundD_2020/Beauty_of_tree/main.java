package com.company;

import javafx.util.Pair;

import java.util.*;

public class Main {

public static double expectation(int []nums,int N ,int a, int  b){
    ArrayList<Integer>[] edges=new ArrayList[N+1];

    for(int i=1;i<=N;++i) edges[i]=new ArrayList<>();

    for(int i=0;i<nums.length;++i){
        int u=nums[i],v=i+2;
        edges[u].add(v);
    }
    double []prob_a= new double[N+1];
    double[] prob_b=new double[N+1];
    LinkedList<Integer> path_a=new LinkedList<>(),path_b=new LinkedList<>();
    int []times_a=new int[N+1], times_b=new int[N+1];

    dfs(edges,path_a,times_a,1,a);
    dfs(edges,path_b,times_b,1,b);

    for(int i=1;i<times_a.length;++i){
        prob_a[i]=(double) times_a[i]/(double) N;
        prob_b[i]=(double) times_b[i]/(double) N;
    }
    double exp=0;
    for(int i=1;i<=N;++i){
        exp += prob_a[i] + prob_b[i] - prob_a[i]* prob_b[i];
    }
    return exp;
}

public static void dfs(ArrayList<Integer>[]edges,LinkedList<Integer>path,int []times,int cur,int step){
    //reach the leaf node
    if(edges[cur]==null) {
        times[cur]=1;
        path.addLast(cur);
        if(path.size()>step){
            int prev= path.get(path.size()-step-1);
            times[prev]++;
        }
        path.removeLast();
        return;
    }
    path.addLast(cur);
    for(int v:edges[cur]) dfs(edges,path,times,v,step);
    times[cur]++;
    if(path.size()>step){
        int prev= path.get(path.size()-step-1);
        times[prev]++;
    }
    path.removeLast();

}



    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int test_num=scanner.nextInt();
        double []result=new double[test_num];
        for(int i=0;i<test_num;++i){
            int N=scanner.nextInt(),a=scanner.nextInt(),b=scanner.nextInt();
            int []nums=new int[N-1];
            for(int j=0;j<N-1;++j){
                nums[j]=scanner.nextInt();
            }
            result[i]= expectation(nums,N,a,b);

        }

        for(int i=0;i<test_num;++i) System.out.println("Case #"+(i+1)+":  "+result[i]);
    }


}
