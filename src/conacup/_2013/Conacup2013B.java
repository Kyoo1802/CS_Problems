package conacup._2013;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Conacup2013B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));        
        long c=-2*Long.parseLong(in.readLine());
        double r1=(-1+Math.sqrt(1-4*c))/2;
        double r2=(-1-Math.sqrt(1-4*c))/2;
        double r=Math.max(r1, r2);
        System.out.println((int)Math.ceil(r));
    }
}