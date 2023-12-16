import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

/**
 * 14912 kb, 132 ms
 * 한참을 고민하다가 모르겠어서 질문 게시판을 봤는데 문제를 완전 잘못 이해한 것이었다 ..!
 *
 * 풀이
 * 각 센서들을 오름차순 정렬하고, 각각의 거리를 구한 배열을 만들어 이 또한 오름차순 정렬
 * 센서 사이의 거리 배열에서 가장 큰 값들을 문제에 주어진 집중국의 갯수에 맞춰서 제거하면 정답
 */
public class BJ_2212_센서_임승연 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N=Integer.parseInt(br.readLine());
        int K=Integer.parseInt(br.readLine());
        int[] arr=new int[N];
        int[] between=new int[N-1];
        st=new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        for(int i=0;i<N-1;i++){
            between[i]=arr[i+1]-arr[i];
        }
        Arrays.sort(between);

        long sum=0;
        for(int i=0;i<N-K;i++){
            sum+=Math.abs(between[i]);
        }
        System.out.println(sum);

    }

}
