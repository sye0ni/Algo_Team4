import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 32692 kb, 284 ms
 *
 * 입력값이 매우 크기 때문에 투포인터를 사용하여 풀이하였음
 * 투포인터나 이진탐색 문제들을 풀고 있는데
 * left, right 의 값을 지정하고 탈출 조건을 설정하는 것이 어렵게 느껴져서
 * 계속 비슷한 유형을 풀어볼 생각이다
 *
 * 풀이
 * 1. 입력을 받은 뒤 정렬을 하고, left, right 를 각각 양 끝점으로 지정
 * 2. 매번 합을 구하고, 최대한 left+right 의 값이 0에 가깝도록 만드는 것을 목표로 하여
 *     음수인 경우에는 왼쪽에 쏠려 있다고 해석하여 left++, 반대의 경우는 right-- 를 반복
 * 3. 만약 합이 0이 되는 경우에는 바로 반복을 종료하고, 절댓값을 계산하여 최솟값을 갱신해준다
 *
 * + 언제나 long 타입에 주의
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N;
        long sum,min=Long.MAX_VALUE;
        long[] solution;

        N=Integer.parseInt(br.readLine());
        solution=new long[N];
        int left,right,saveLeft=0,saveRight=0;

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            solution[i]=Long.parseLong(st.nextToken());
        }

        Arrays.sort(solution); // 오름차순 정렬

        left=0;
        right=N-1; // 초기 left, right 값 설정

        while(left<right){ // left==right 가 되면 종료

            sum=solution[left]+solution[right];

            if(Math.abs(sum)<min) { // 절댓값을 구하여 최솟값 변경
                min=Math.abs(sum);
                saveLeft=left;
                saveRight=right;
            }

            if(sum<0){ // 왼쪽에 더 쏠려있음
                left++;
            }
            else if(sum>0){ // 오른쪽에 더 쏠려있음
                right--;
            }
            else{ // sum==0 -> 바로 종료
                saveLeft=left;
                saveRight=right;
                break;
            }
        }

        System.out.println(solution[saveLeft]+" "+solution[saveRight]);
    }


}
