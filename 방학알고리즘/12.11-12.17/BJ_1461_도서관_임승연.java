import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 11624kb, 80ms
 *
 * ----------------------------------------------------------------------------------------------------------------------------
 * 아이디어
 * 크기 순으로 정렬하고, 양수와 음수의 절댓값의 max끼리 비교하여, 더 큰 쪽을 마지막에 옮기며 왕복하지 않도록 한다
 * 모두 부호가 같은 경우에는 상관 없지만, 부호가 다를 경우에는 끊어주기 , 항상 절댓값이 큰 쪽에서 작은 쪽으로 묶어주기
 * ----------------------------------------------------------------------------------------------------------------------------
 *
 */
public class BJ_1461_도서관_임승연 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N,M;
        st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        int[] arr=new int[N];
        st=new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); // 정렬

        int change=-1;

        if(Math.max(Math.abs(arr[0]),Math.abs(arr[N-1]))==Math.abs(arr[N-1])){
            int temp;
            for(int i=0;i<N/2;i++){
                temp=arr[i];
                arr[i]=arr[N-i-1];
                arr[N-i-1]=temp;
            }
        } // 항상 왼쪽부터 묶기 위한 처리

        for(int i=0;i<N-1;i++){
            if(arr[i]*arr[i+1]<0) change=i+1; // 부호가 바뀌는 위치 = change
        }

        int sum=0;
        if(change==-1){ // 모두 같은 부호 -> 양방향 신경 쓸 필요 없음
            for(int i=0;i<N;i+=M){
                if(i==0) sum+=Math.abs(arr[0]);
                else sum+=Math.abs(arr[i])*2;
            }
        }
        else{ // 양수, 음수 존재
            for(int i=0;i<change;i+=M){
                if(i==0) sum+=Math.abs(arr[0]);
                else sum+=Math.abs(arr[i])*2;
            }
            for(int i=N-1;i>=change;i-=M){
                sum+=Math.abs(arr[i])*2;
            }
        }
        System.out.println(sum);

    }
}
