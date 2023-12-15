import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 75552 kb, 752 ms
 *
 * 틀린 아이디어
 * 처음에는 단순히 데드라인 오름차순, 컵라면 수 내림차순 우선순위 큐를 만들어서 데드라인마다 최댓값만 더해주면 된다고 생각했는데 틀림 << 테스트 케이스 때문에 이렇게 생각을 해버렸다 >>
 *
 * 해결 
 * (1) 데드라인이 지나지 않았다면 항상 추가가 가능하고, (2) 앞서 추가해준 갯수보다 더 많은 갯수를 더할 수 있는지 확인하는 과정이 필요함
 * 따라서 지금까지 더해준 값들의 최솟값을 항상 알아야 할 필요가 있기 때문에 추가한 값들을 저장하는 우선순위 큐를 한 개 더 만들어서 해결했다
 *
 * 테케만 믿지 말 것 ,,,,
 */

public class BJ_1781_컵라면_임승연 {

    static class question implements Comparable<question>{
        int deadline;
        long ramen;

        public question(int deadline, long ramen){
            this.deadline=deadline;
            this.ramen=ramen;
        }

        @Override
        public int compareTo(question q) {
            if(this.deadline!=q.deadline){
                return Integer.compare(this.deadline,q.deadline);
            }
            else {
                return Long.compare(q.ramen,this.ramen);
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n=Integer.parseInt(br.readLine());
        PriorityQueue<question> pq=new PriorityQueue<>();
        PriorityQueue<Long> addPq=new PriorityQueue<>();

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            pq.add(new question(Integer.parseInt(st.nextToken()),Long.parseLong(st.nextToken())));
        }

        long sum=0;
        int day=0; // 현재까지 처리한 문제 수
        question quest;

        for(int i=0;i<n;i++){

            quest=pq.poll();

            if(day==quest.deadline){ // 앞서 추가한 값들 중 가장 작은 값과 나를 비교한 뒤 최댓값 바꾸기
                if(quest.ramen>addPq.peek()){
                    sum-=addPq.poll();
                    sum+=quest.ramen;
                    addPq.add(quest.ramen);
                }
            }

            if(day<quest.deadline) {
                sum+=quest.ramen;
                day++;
                addPq.add(quest.ramen);
            }
        }

        if(sum>Math.pow(2,31)) sum= (long) Math.pow(2,31);
        System.out.println(sum);
    }
}