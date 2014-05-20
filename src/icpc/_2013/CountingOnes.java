package icpc._2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 *
 * @author Kyoo
 */
public class CountingOnes {
    public static void main(String[] args) throws IOException{
        BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
        String s=null;
        while((s=in.readLine())!=null){
            StringTokenizer st= new StringTokenizer(s);
            long a=Long.parseLong(st.nextToken());
            long b=Long.parseLong(st.nextToken());
            System.out.println(counting1(b).subtract(counting1(a-1)));
        }
    }

    private static BigInteger counting1(long n) {
        BigInteger count= BigInteger.ZERO;
        for (int i = 1; i <= 63; i++) {
            long pow=1L<<i;
            long pow_1=1L<<(i-1);
            //System.out.println(pow);
            //System.out.println((n/pow)*i+" "+Math.max(0,(n+1)%pow-pow_1));
            count=count.add(BigInteger.valueOf((n/pow)*pow_1));
            count=count.add(BigInteger.valueOf(Math.max(0,(n%pow+1)-pow_1)));
        }
        return count;
    }
}