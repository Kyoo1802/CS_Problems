package conacup._2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Conacup2013D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String data = in.readLine().trim();
        int[] numbers= new int[data.length()];
        
        for (int i = 0; i < data.length(); i++)
            numbers[i]=data.charAt(i)-'0';
        System.out.println(getQ(data, numbers));
    }
    private static String getQ(String data, int[] numbers){
        for (int i = data.length()/2; i >=0 ; i--) {
            for (int k = i+1; k-i<=7 && k < data.length() ; k++) {
                int T=Integer.parseInt(data.substring(i,k));
                if(T%numbers[0]==0){
                    int Q=T/numbers[0];
                    if(Q<=1000000 && validarQ(numbers,data,i, Q)){
                        StringBuilder sb= new StringBuilder("");
                        return sb.append(data.substring(0,i)).append("\n").append(Q).toString();
                    }
                }
            }
        }
        return "";
    }
    private static boolean validarQ(int[] numbers, String data,int idxI, int Q) {        
        int b=idxI;
        for (int j = 0; j < idxI; j++) {
            String r= String.valueOf(numbers[j]*Q);
            for (int a = 0; a < r.length(); a++)
                if(b>=data.length() || r.charAt(a)!=data.charAt(b++))
                    return false;
        }
        return b==data.length();
    }
}