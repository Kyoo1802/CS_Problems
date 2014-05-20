package codejam._2014.RoundA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Kyoo
 */
public class ProblemB {
    public static void main(String[] args)throws IOException{
        
        System.out.println(Integer.toBinaryString(7));
        System.out.println(Integer.toBinaryString(8));
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(45));
        System.out.println(Integer.toBinaryString(56));
        System.out.println(Integer.toBinaryString(35));
        BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(in.readLine());
        for (int i = 1; i <= T; i++) {
            StringTokenizer st= new StringTokenizer(in.readLine());
            int A =Integer.parseInt(st.nextToken());
            int B =Integer.parseInt(st.nextToken());
            int K =Integer.parseInt(st.nextToken());
            int r=solve(A,B,K);
            System.out.printf("Case #%d: %s%n",i,r+"");
        }
    }

    private static int solve(int A, int B, int K) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
