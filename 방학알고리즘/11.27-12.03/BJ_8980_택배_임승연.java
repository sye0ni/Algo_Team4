import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 18668 kb, 296 ms
 * 그리디 연습하기 2
 * 최대한 많이 배송하기 위해서 빨리 싣고 빨리 내리도록 하고 항상 최대한 가득 채울 수 있게 하기
 * 도착지 기준 오름차순 정렬하고, 같은 경우 출발지 기준 내림차순 정렬한 뒤 앞에서부터 채우며 가능한 만큼 가득 채우기
 * ++++ 출발지 정렬은 하지 않아도 상관 없음
 *
 * 그리디적으로 생각하고 푸는 연습하려고 시도하다가 잘 모르겠어서 힌트를 봤는데 봐도 어렵다 ..
 */
public class Main {

    static class box implements Comparable<box>{
        int from,to,weight;

        box(int from,int to,int weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }

        @Override
        public int compareTo(box o) {
            if(this.to != o.to){
                return Integer.compare(this.to,o.to);
            }
            return Integer.compare(o.from,this.from);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N,C; // 마을수, 용량
        int M; // 박스의 수
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(br.readLine());

        box[] boxes=new box[M];
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            boxes[i]=new box(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(boxes); // 내가 원하는대로 정렬

        int sum=0;
        int[] load=new int[N]; // 마을에 싣고 있는 짐
        int max=0;
        int add=0;

        for(int i=0;i<M;i++){
            max=-1;
            // boxes[i] 에 a->(b-1) 의 최댓값에서 더 넣을 수 있는지 확인
            for(int j=boxes[i].from;j<boxes[i].to;j++){
                if(load[j]>max){
                    max=load[j];
                }
            }
            if(max<C){ // 더 채울 수 있다면 가득 채우기
                add=C-max;
                if(add>boxes[i].weight){
                    add=boxes[i].weight;
                }
                for(int j=boxes[i].from;j<boxes[i].to;j++){
                    load[j]+=add;
                }
                sum+=add;
            }
        }
        System.out.println(sum);
    }

}
