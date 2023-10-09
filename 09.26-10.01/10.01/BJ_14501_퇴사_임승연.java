import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어 : dp
 * dp[i][0] : i 번째 날에 진행한 상담이 끝나는 날짜
 * dp[i][1] : i 번째 날의 상담을 할 때 현재까지의 상담 수익의 최대 값
 *
 * dp 배열 값 생성 완료 후 dp[i][1] 값 중 최댓값이 정답이 된다
 *
 * 대략 n*2 만큼의 시간이 소요되지만 n의 크기가 최대 15라서 가능
 * 11556 kb	80 ms
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N=Integer.parseInt(br.readLine());
        int[][] dp;
        int t,p,max;

        dp=new int[N][2];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            t=Integer.parseInt(st.nextToken());
            p=Integer.parseInt(st.nextToken());
            max=0;

            for(int j=0;j<i;j++){
                if(dp[j][0]<i){
                    if(dp[j][1]>max){
                        max=dp[j][1];
                    }
                }
            }

            dp[i][0]=i+t-1;
            if(dp[i][0]<N){ // 지금 회의를 진행할 수 있을 때에만 값 지정
                dp[i][1]=max+p;
            }
        } // dp 배열 생성 완료

        max=0;
        for(int i=0;i<N;i++){
            if(dp[i][1]>max){
                max=dp[i][1];
            }
        }

        System.out.println(max);
    }

}
