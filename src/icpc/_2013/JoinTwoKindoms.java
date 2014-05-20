package icpc._2013;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Kyoo
 */
public class JoinTwoKindoms {
    private static double calcRes(int[] maxN, int[] maxQ,int maxi) {
 
        Arrays.sort(maxQ);
        int[] sumFreqQ=new int[maxQ.length];
        for (int i = 0; i < sumFreqQ.length; i++) 
            sumFreqQ[i]=i==0?maxQ[i]:maxQ[i]+sumFreqQ[i-1];
        
        //System.out.println(maxi);
        //System.out.println(Arrays.toString(maxN));
        //System.out.println(Arrays.toString(maxQ));
        //System.out.println(Arrays.toString(sumFreqQ));
        double sum=0;
        for (int i = 1; i < maxN.length; i++) {
            int a=0,b=maxQ.length-1, half=(a+b)/2;
            while(a<=b){
                //System.out.println(a+" "+b+" "+half);
                if((maxQ[half]+maxN[i])<maxi)
                    a=half+1;
                else 
                    b=half-1;
                half=(a+b)/2;
            }
            a=(b<0)?0:b;
            //System.out.println("---"+a);
            sum+=a*maxi;
            sum+=sumFreqQ[sumFreqQ.length-1]-sumFreqQ[a]+(sumFreqQ.length-1-a)*maxN[i];
        }
        //System.out.println("SUm "+sum);
        return sum;
    }

    static class Graph {
        final int V;
        int[] max;
        private List<Integer>[] adj;
        public Graph(int V){
            this.V=V;
            adj= new LinkedList[V];
            max= new int[V];
            for (int i=0;i<adj.length;i++)
                adj[i] = new LinkedList<>();
        }
        public void add(int v, int w){
            adj[v].add(w);
            adj[w].add(v);                
        }
        public Iterable<Integer> adj(int v){
            return adj[v];
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        while ((s = in.readLine()) != null) {
            StringTokenizer sT= new StringTokenizer(s);
            int n = Integer.parseInt(sT.nextToken());
            int q = Integer.parseInt(sT.nextToken());
            Graph tN= new Graph(n+1);
            Graph tQ= new Graph(q+1);
            for (int i = 0; i < n-1; i++) {
                sT= new StringTokenizer(in.readLine());
                int a=Integer.parseInt(sT.nextToken());
                int b=Integer.parseInt(sT.nextToken());
                tN.add(a, b);
            }
            for (int i = 0; i < q-1; i++) {
                sT= new StringTokenizer(in.readLine());
                int a=Integer.parseInt(sT.nextToken());
                int b=Integer.parseInt(sT.nextToken());
                tQ.add(a, b);
            }
            boolean[] marksN= new boolean[tN.V];
            int maxN=dfsMax(tN,marksN,1,0)[0];
            Arrays.fill(marksN, false);
            int pathN=dfs(tN,marksN,maxN,0);            
            
            boolean[] marksQ= new boolean[tQ.V];            
            int maxQ=dfsMax(tQ,marksQ,1,0)[0];
            Arrays.fill(marksQ, false);
            int pathQ=dfs(tQ,marksQ,maxQ,0);
            
            for (int i = 1; i < tN.max.length; i++) 
                tN.max[i]++;
            
            int maxi=Math.max(pathN, pathQ);
            double res=calcRes(tN.max,tQ.max,maxi);
            res/=n*q;
            System.out.printf("%.3f%n",res);
        }
    }
    
    private static int dfs(Graph g,boolean[] marks, int v, int c) {
        int max=c;
        marks[v]=true;
        for (Integer w : g.adj(v))
            if(!marks[w]){
                int maxPath=dfs(g,marks,w,c+1);
                max=Math.max(maxPath,max);
            }
        
        g.max[v]=Math.max(max-c,c);
        return max;
    }
    
    private static int[] dfsMax(Graph g,boolean[] marks, int v, int c) {
        int[] maxVC={v,c};
        marks[v]=true;
        for (Integer w : g.adj(v)){
            if(!marks[w]){
                int[] WC=dfsMax(g,marks,w,c+1);
                if(WC[1]>maxVC[1])
                    maxVC=WC;
            }
        }
        return maxVC;
    }
}