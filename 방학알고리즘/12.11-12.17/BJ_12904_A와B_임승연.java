import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * 14904 kb, 92 ms
 *
 * 이미 만들어진 문자열에서 역으로 연산 수행
 * (1) 맨 뒤가 A 라면, 제거
 * (2) 맨 뒤가 B 라면, 제거 후 뒤집기
 * 연산 후에 문자열 S와 일치하는 경우가 있으면 1 출력
 */
public class BJ_12904_A와B_임승연 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S=br.readLine();
        String T=br.readLine();
        char[] t=T.toCharArray();

        int result=0;
        boolean flag=true;
        String str=new String(t);

        while(true){
            str=new String(t);

            if(S.equals(str)) {
                result=1;
                break;
            }

            if(str.length()==0){ // 더이상 연산을 수행할 수 없음
                break;
            }

            if(str.charAt(str.length()-1)=='A'){
                str=str.substring(0,str.length()-1);
                t=str.toCharArray();
            }

            else if(str.charAt(str.length()-1)=='B'){
                str=str.substring(0,str.length()-1);
                t=str.toCharArray();

                // 뒤집기
                char temp;
                for(int i=0;i<t.length/2;i++){
                    temp=t[i];
                    t[i]=t[t.length-1-i];
                    t[t.length-1-i]=temp;
                }
            }
        }

        System.out.println(result);

    }
}