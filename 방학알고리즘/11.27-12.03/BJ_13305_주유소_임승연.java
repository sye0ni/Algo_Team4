import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 	42804kb, 336ms
 * 그리디 알고리즘 연습하기 !
 * 왼쪽에서 오른쪽으로만 이동하기 때문에 , 어떤 주유소에 도착했을때까지의 기름 값 최소 금액을 알고 있으면 그게 바로 최소다
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        long[] size=new long[N-1]; // 도로의 길이
        long[] price=new long[N];
        long min=Long.MIN_VALUE;
        long sum=0;

        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<N-1;i++){
            size[i]=Long.parseLong(st.nextToken());
        }
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            price[i]=Long.parseLong(st.nextToken());
        }
        min=price[0];
        sum=min*size[0];

        for(int i=1;i<N-1;i++){
            if(min>price[i]){
                min=price[i];
            }
            sum+=min*size[i];
        }

        System.out.println(sum);
    }

}
