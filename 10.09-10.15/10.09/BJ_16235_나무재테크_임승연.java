import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 단순구현 문제!!
 * 나이가 어린 순으로 나무를 관리해야하는데, for 문을 사용해서 나무를 삽입하지 않고 우선순위 큐를 사용해서 시간초과가 안난 것 같다
 * 299544 kb, 1436 ms
 * 
 */

public class Main {

    static class tree implements Comparable<tree>{
        int x,y,age;

        public tree(int x,int y,int age){
            this.x=x;
            this.y=y;
            this.age=age;
        }

        @Override
        public int compareTo(tree o) {
            return Integer.compare(this.age,o.age);
        }
    }

    static int[][] ground; // 현재 땅의 양분 상태
    static int[][] food; // 추가되는 양분 저장
    static PriorityQueue<tree> pq;
    static int N,M,K;


    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");


        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        food=new int[N][N];
        ground=new int[N][N];
        pq=new PriorityQueue<>();

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                food[i][j]=Integer.parseInt(st.nextToken());
                ground[i][j]=5; // 초기 상태
            }
        }

        int a,b,c;
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine()," ");
            a=Integer.parseInt(st.nextToken())-1;
            b=Integer.parseInt(st.nextToken())-1;
            c=Integer.parseInt(st.nextToken());
            pq.add(new tree(a,b,c));
        } // 입력 완료

        for(int i=0;i<K;i++){
            SpringToSummer();
            fall();
            winter();
        }

        System.out.println(pq.size());

    }

    static void SpringToSummer(){

        Deque<tree> alive=new ArrayDeque<>(); // 봄에 살아남는 나무 저장
        tree pop;
        int[] popDead;
        Deque<int[]> deadTree=new ArrayDeque<>(); // 죽은 나무의 좌표, 양분으로 변하게 될 값 저장

        // spring
        while(!pq.isEmpty()){
          pop=pq.poll();
          if(ground[pop.x][pop.y]>=pop.age){
              alive.add(new tree(pop.x,pop.y,pop.age+1));
              ground[pop.x][pop.y]-=pop.age;
          }
          else{
              deadTree.add(new int[]{pop.x,pop.y,(int)pop.age/2});
          }
        }

        // 살아남은 나무들 pq 에 다시 추가
        while(!alive.isEmpty()){
            pop=alive.poll();
            pq.add(new tree(pop.x,pop.y,pop.age));
        }

        // summer -> 땅에 양분 추가
        while(!deadTree.isEmpty()){
            popDead=deadTree.poll();
            ground[popDead[0]][popDead[1]]+=popDead[2];
        }

    }

    static void fall(){
        Deque<tree> temp=new ArrayDeque<>();
        tree pop;
        int[] dx=new int[]{-1,-1,-1,0,0,1,1,1};
        int[] dy=new int[]{-1,0,1,-1,1,-1,0,1};
        int x,y;

        while(!pq.isEmpty()){
            pop=pq.poll();

            if(pop.age%5==0) { // 나무 번식
                for (int i = 0; i < 8; i++) {
                    x = pop.x + dx[i];
                    y = pop.y + dy[i];
                    if (x < 0 || x >= N || y < 0 || y >= N) continue;
                    temp.add(new tree(x, y, 1));
                }
            }

            temp.add(new tree(pop.x,pop.y,pop.age)); // 자기 자신도 추가
        }

        while(!temp.isEmpty()){
            pop=temp.poll();
            pq.add(new tree(pop.x,pop.y,pop.age));
        }
    }

    static void winter(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                ground[i][j]+=food[i][j];
            }
        }
    }

}
