package icpc._2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author Kyoo
 */
public class Football {
    public static void main(String[] args)  throws IOException{
        BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
        String s=null;
        while((s=in.readLine())!=null){
            StringTokenizer st= new StringTokenizer(s);
            int n=cInt(st.nextToken());
            int g=cInt(st.nextToken());
            int points=0,draws=0;
            ArrayList<Integer> lose= new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                st= new StringTokenizer(in.readLine());
                int result=cInt(st.nextToken())-cInt(st.nextToken());
                if(result>0)
                    points+=3;
                else if(result==0){
                    points++;
                    draws++;
                }
                else
                    lose.add(result);
            }
            if(g>=draws){
                g-=draws;
                points+=draws*2;
            }
            else{
                points+=g*2;
                g=0;
            }
            if(g>0){
                Collections.sort(lose);
                int idx=lose.size()-1;
                while(g>0 && idx>=0){
                    int loseV=lose.get(idx);
                    if(g+loseV>0){
                        points+=3;
                        g+=loseV-1;
                    }
                    else if(g+loseV==0){
                        points++;
                        g+=loseV;
                    }
                    else
                        break;                    
                    idx--;
                }
            }
            System.out.println(points);
        }
    }

    private static int cInt(String readLine) {
        return Integer.parseInt(readLine);
    }
}