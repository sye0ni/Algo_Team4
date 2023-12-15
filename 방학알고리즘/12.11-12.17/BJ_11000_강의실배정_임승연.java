import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

/**
 * 75436 kb, 672 ms
 *
 * 예전에 고민 없이 단순 반복문으로 풀어서 시간초과 발생 ...!
 *
 * 해결 방법
 * 입력 값을 정렬하는 우선순위큐에 추가로 진행중인 수업을 저장하는 우선순위큐를 따로 만듦
 * 어차피 현재 진행중인 수업 중 가장 빨리 끝나는 수업과 같은 강의실을 쓸 수 없다면 강의실 하나 추가해줘야한다
 */
public class BJ_11000_강의실배정_임승연 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]) return Integer.compare(o1[0],o2[0]);
                return Integer.compare(o1[1],o2[1]);
            }
        });

        PriorityQueue<Integer> addPq=new PriorityQueue<>();

        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            pq.add(new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
        } // 입력 받아 pq 에 삽입

        int temp;
        int cnt=0; // 필요한 강의실 개수
        int[] input;

        // pq 에는 시작 시간 오름차순, 종료 시간 오름차순 정렬
        while(!pq.isEmpty()){
            input=pq.poll(); // 추가할 강의

            if(addPq.size()>0){
                temp=addPq.peek();
                if(temp<=input[0]) { // 먼저 끝나면?
                    addPq.poll();
                    cnt--;
                }
            }
            addPq.add(input[1]); // 지금 강의 담기
            cnt++;
        }

        System.out.println(cnt);
    }

}
