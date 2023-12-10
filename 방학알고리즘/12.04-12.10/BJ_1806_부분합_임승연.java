import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 23104 kb, 224 ms
 * 투포인터를 활용하여 풀었다
 * 초기 조건과 종료 조건을 지정해주는데서 조금 헤맸음
 */
public class algo {
    public static void main(String[] args) throws IOException {
        int N;
        long S;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        S=Long.parseLong(st.nextToken());

        int[] numbers=new int[N+1];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            numbers[i]=Integer.parseInt(st.nextToken());
        }
        numbers[N]=0;

        int left=0;
        int right=0;
        int min=Integer.MAX_VALUE; // 수열의 길이
        long sum=0;

        while(left<=N && right<=N){
            if(sum<S) {
                sum+=numbers[right++];
            }
            else{
                if(right-left<min){
                    min=right-left;
                } // 길이 확인
                sum-=numbers[left++];
            }
        }

        if(min==Integer.MAX_VALUE) min=0;
        System.out.println(min);
    }
}
