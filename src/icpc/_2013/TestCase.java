
package icpc._2013;

import java.util.HashSet;
import java.util.Random;

public class TestCase {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            generar();
        }
    }
    public static void generar(){
        Random r= new Random();
        int a=r.nextInt(7)+1;
        int b=r.nextInt(7)+1;
        System.out.println(a+" "+b);
        HashSet<String> hs= new HashSet<>();
        
        if(a>1){
            hs.add((a-1)+" "+a);
            System.out.println(a-1+" "+a);
        }
        for (int i = 1; i < a-1; i++){
            int y;
            do{
                while((y=r.nextInt(a)+1)==i);
            }while(hs.contains(i+" "+y)|| hs.contains(y+" "+i));
            hs.add(i+" "+y);
            System.out.println(i+" "+y);
        }
        
        HashSet<String> hs2= new HashSet<>();
        if(b>1){
            hs.add((b-1)+" "+b);
            System.out.println(b-1+" "+b);
        }
        for (int i = 1; i < b-1; i++){
            int y;
            do{
                while((y=r.nextInt(b)+1)==i);
            }while(hs.contains(i+" "+y)|| hs.contains(y+" "+i) );
            hs.add(i+" "+y);
            System.out.println(i+" "+y);
        }
        
    }
}
