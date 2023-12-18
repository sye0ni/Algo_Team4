import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 57084 kb, 692 ms
 *
 * 아이디어는 쉬운데, (예상 등수 정렬하여, 앞에서부터 등수 부여하기)
 * 입력값의 크기에 따른 자료형 사용이 관건이다
 */

public class BJ_2012_등수매기기_임승연 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq=new PriorityQueue<>();

        int pop;
        long sum=0;
        for(int i=0;i<N;i++){
            pq.add(Integer.parseInt(br.readLine()));
        }

        for(int i=1;i<=N;i++){
            pop=pq.poll();
            sum+=Math.abs(pop-i);
        }

        System.out.println(sum);

    }
}