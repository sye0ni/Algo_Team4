import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 12296 kb, 128 ms
 * 별똥별의 수는 최대 100개 밖에 안 되니까 조건을 잘 세워서 완탐을 하면 되는데
 * 처음에는 별 하나를 꼭짓점으로 지정하고, 사분면을 모두 탐색했는데 반례가 있음
 * 별 두개를 골라서 모든 경우를 탐색할 수 있는 조건을 지정하고 완탐
 * ++ 경계 조건 필요 없음, 동일한 점으로 설정 가능 한 것 때문에 여러번 틀림
 */
public class algo {
    public static void main(String[] args) throws IOException {
        int N,M,L,K;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        List<int[]> stars=new ArrayList<>(); // 별똥별 저장

        N=Integer.parseInt(st.nextToken()); // 가로
        M=Integer.parseInt(st.nextToken()); // 세로
        L=Integer.parseInt(st.nextToken()); // 트램펄린 길이
        K=Integer.parseInt(st.nextToken());

        for(int i=0;i<K;i++){
            st=new StringTokenizer(br.readLine());
            stars.add(new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())}); // 별똥별 저장
        }

        int x,y;
        int xx,yy;
        int cnt=0;
        int max=0;
        int[] star;

        for(int i=0;i<K;i++){
            for(int j=0;j<K;j++){
                cnt=0;
                x=stars.get(i)[0];
                y=stars.get(j)[1];
                xx=x+L;
                yy=y+L; // (x,y) ~ (xx,yy)

                for(int k=0;k<K;k++){
                    star=stars.get(k);
                    if(star[0] >=x && star[0]<=xx && star[1]>=y && star[1]<=yy){
                        cnt++;
                    }
                }

                if(cnt>max){
                    max=cnt;
                }
            }
        }

        System.out.println(K-max);
    }
}
