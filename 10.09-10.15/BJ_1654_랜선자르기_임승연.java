import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1654
 *
 * 입력 값이 매우 크기 때문에 단순히 반복문으로 값을 확인하면 시간 초과가 발생할 것이다
 * => 이분탐색을 이용 
 * 랜선 길이의 최댓값을 right 로 두고 1을 left 로 하여 이분탐색을 수행
 * mid=(left+right)/2 로 지정하며, 매번 생성 가능한 랜선 개수를 카운트하고
 * K 보다 큰 경우에는 랜선 길이를 늘리고, K 보다 작은 경우 랜선 길이를 줄인다
 *
 * ++ 이분탐색 문제는 주로 크기가 매우 큰 값이 입력되기 때문에, 데이터 타입을 int 가 아닌 long 으로 하는 것을 항상 생각해야한다
 *
 */

public class Main {

    static long[] wires;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); // 가지고 있는 랜선의 개수
        N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수

        wires = new long[K];

        long right = 0;

        for (int i = 0; i < K; i++) {
            wires[i] = Integer.parseInt(br.readLine());
            if(wires[i]>right){
                right=wires[i]; // 최댓값 저장
            }
        }


        long left=1;
        long mid;

        while(left<=right){

            mid=(left+right)/2;

            if(count(mid)>=N){ // 길이 늘리기
                left=mid+1;
            }
            else{
                right=mid-1;
            }

        }

        System.out.println(right);

    }

    static long count(long mid){ // mid 로 잘랐을 때 생성되는 랜선 개수 반환 
        long cnt=0;

        for(int i=0;i<wires.length;i++){
            cnt+=(long)wires[i]/mid;
        }

        return cnt;
    }

}
