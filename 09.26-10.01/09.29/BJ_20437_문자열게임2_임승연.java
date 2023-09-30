import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 시간초과가 나지 않으려면 먼저 문자열 처음부터 끝까지 확인을 한 뒤 
 * 조건을 만족하는지 찾아가야함 !! 
 * ** 어떤 자료구조를 사용하여 어떻게 저장을 하고, 어떻게 조건을 확인할 지 생각하는게 쉽지 않았다 
 * 35468 kb, 328 ms 
 */

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        String str;
        List<Integer> arr;
        Map<Character,List<Integer>> map=new HashMap<>();
        int K;
        int temp;
        int pre,back;
        int game3,game4;

        for(int i=0;i<t;i++){   // t회 반복
            str=br.readLine();
            K=Integer.parseInt(br.readLine());
            map.clear();
            game3=Integer.MAX_VALUE;
            game4=Integer.MIN_VALUE;

            for(int j=0;j<str.length();j++){
                if(map.get(str.charAt(j))==null){
                    arr=new ArrayList<>();
                }
                else{
                    arr=map.get(str.charAt(j));
                }
                arr.add(j);
                map.put(str.charAt(j),arr);
            } // 최초에 문자열을 처음부터 끝까지 확인하여, 문자별로 등장 인덱스를 리스트로 저장

            for(Character c:map.keySet()){
                if(map.get(c).size()>=K){ // K회 이상 등장하는 문자들만 확인
                    arr=map.get(c);
                    for(int j=0;j<=arr.size()-K;j++){ // 문자가 정확히 K회 등장하는 순간을 찾아서 값 바꿔주기
                        pre=j;
                        back=j+K-1;
                        temp=arr.get(back)-arr.get(pre)+1;

                        if(temp<game3) game3=temp;
                        if(temp>game4) game4=temp;
                    }
                }
            }

            if(game3==Integer.MAX_VALUE) sb.append("-1\n");
            else sb.append(game3+" "+game4+"\n");
        }

        System.out.println(sb);
    }
}
