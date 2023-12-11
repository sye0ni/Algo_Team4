import java.util.*;
import java.io.*;

/**
 * 43932 kb, 528 ms
 * 아이디어
 * 최단경로 알고리즘 다익스트라 - 우선순위 큐를 사용하여 구현하였다
 */
public class BJ_5972_택배배송_임승연 {

    static class cow implements Comparable<cow>{
        int dest,cost;

        public cow(int dest,int cost){
            this.dest=dest;
            this.cost=cost;
        }

        @Override
        public int compareTo(cow c) {
            return Integer.compare(this.cost,c.cost);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N,M;
        int a,b,c;

        st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        long[] cost=new long[N];
        boolean[] visited=new boolean[N];
        List<cow>[] graph=new ArrayList[N];
        PriorityQueue<Integer> pq=new PriorityQueue<>((o1, o2) -> Long.compare(cost[o1], cost[o2]));


        for(int i=0;i<N;i++) graph[i]=new ArrayList<>();

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());

            a=Integer.parseInt(st.nextToken())-1;
            b=Integer.parseInt(st.nextToken())-1;
            c=Integer.parseInt(st.nextToken());

            graph[a].add(new cow(b,c));
            graph[b].add(new cow(a,c));
        } // 입력 받기 및 그래프 생성 완료

        Arrays.fill(cost,Integer.MAX_VALUE);
        cost[0]=0; // 시작점 초기화
        pq.add(0);
        int pop;
        cow tempCow;

        while(!pq.isEmpty()){
            pop=pq.poll();
            visited[pop]=true; // 방문처리

            for(int i=0;i<graph[pop].size();i++){
                tempCow=graph[pop].get(i);
                if(cost[tempCow.dest]>cost[pop]+tempCow.cost){
                    cost[tempCow.dest]=cost[pop]+tempCow.cost; // 최소 거리 초기화
                    if(!visited[tempCow.dest]){
                        pq.add(tempCow.dest);
                    }
                }
            }
        }
        System.out.println(cost[N-1]);

    }
}
