package codejam._2014.QualificationRound;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author Kyoo
 */
public class ProblemA {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(in.readLine());
        for (int i = 1; i <= T; i++) {
            String r="";
            
            int answ2=Integer.parseInt(in.readLine());
            System.out.printf("Case #%d: %s%n",i,r);
        }
    }
}
