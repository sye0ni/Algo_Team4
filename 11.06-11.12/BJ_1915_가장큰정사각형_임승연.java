import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 처음에는 탐색 문제인 줄 알아서 그렇게 생각을 하다가 입력 값이 커서 다른 방법을 생각했다!
 * dp 로 접근을 하는 데도 오래 걸리고, 풀이도 오래 걸림
 *
 * dp[i][j] : i,j 에서 만들어질 수 있는 정사각형의 최대 크기 (한 변의 길이)
 * 왼쪽 , 위, 왼쪽 위 대각선 값을 보며 현재 위치에서 만들어질 수 있는 정사각형 길이를 저장한다
 *
 * 이래서 꾸준히 풀어야 하는데 ,,,,,,,,
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        String str;

        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        int[][] arr=new int[n+1][m+1];
        int[][] dp=new int[n+1][m+1]; // 범위 확인을 하지 않기 위해 1칸씩 크게 만들기

        for(int i=1;i<=n;i++){
            str=br.readLine();
            for(int j=1;j<=str.length();j++){
                arr[i][j]=str.charAt(j-1)-'0';
            }
        }

        int max=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(arr[i][j]==0) continue;
                dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                dp[i][j]+=1;
                if(dp[i][j]>max) max=dp[i][j];
            }
        }

        System.out.println(max*max);

    }


}
