import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 11716 kb, 84 ms
 * 아이디어
 * 1. 다리 만들기에서 푼 것처럼, 이어진 섬들끼리 마킹한다 (bfs 사용)
 * 2. 처음에는 dfs 를 이용해 세울 수 있는 다리들을 찾고, 다리의 개수 = 섬의 개수 -1 의 조건만 맞춰주면 된다고 생각하여 그렇게 풀다가 뭔가 이상해서 다시 생각해보니
 *    단순히 다리의 개수만으로는 사이클 발생을 잡아낼 수 없다는 점을 늦게 생각함
 * 3. 그래서 지을 수 있는 모든 다리를 우선순위 큐에 추가하고, mst 를 활용하여 구하기로 결정!  kruskal 알고리즘을 사용하였다
 *
 */

public class Main {

    static class bridge implements Comparable<bridge>{
        int a,b,weight;
        public bridge(int a,int b,int weight){
           this.a=a;
           this.b=b;
           this.weight=weight;
        }

        @Override
        public int compareTo(bridge o) {
            return Integer.compare(this.weight,o.weight);
        }
    }

    static int[][] map;
    static int N,M;
    static boolean[][] isVisited;
    static PriorityQueue<bridge> pq=new PriorityQueue<>(); // 현재까지 건설된 다리 저장 -> 총 islandCnt-1 개 저장하기
    static int[] dx=new int[] {-1,1,0,0};
    static int[] dy=new int[] {0,0,-1,1};
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        map=new int[N][M];
        isVisited=new boolean[N][M];

        for(int i=0;i<N;i++) {
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<M;j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        } // 그래프 생성

        // 섬별로 마킹 1부터 ~ islandCnt
        int islandCnt=0;
        int id=1;
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j]==1 && !isVisited[i][j]) {
                    bfs_mark(i,j,id);
                    id++;
                    islandCnt++;
                }
            }
        }

        // 바다와 접한 점을 찾아 dfs 수행 -> 건설 가능한 (방향이 바뀌지 않고, 다른 섬을 연결하고, 길이가 2 이상인) 다리 찾아서 pq 에 저장하기
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j]!=0 && adjacentOcean(i,j)) { // 바다가 아니면서 바다와 접한 점이라면 건설가능한 다리 찾기
                    for(int k=0;k<4;k++){
                        dfs(i,j,i,j,k,0,map[i][j]); // k 방향으로 깊이 우선 탐색
                    }
                }
            }
        }


        int bridgeCnt=0;
        bridge pop;
        int weight=0;

        // kruskal 알고리즘을 수행하기 전에 전처리
        parent=new int[islandCnt];
        for(int i=0;i<parent.length;i++) parent[i]=i;

        while(!pq.isEmpty()){
            if(bridgeCnt==islandCnt-1) break; // 다리 건설 완료
            pop=pq.poll();
            if(find(pop.a-1)==find(pop.b-1)) continue; // 이미 연결
            else{
                union(pop.a-1,pop.b-1);
                bridgeCnt++;
                weight+=pop.weight;
            }

        }

        if(bridgeCnt==islandCnt-1){
            System.out.println(weight);
        }
        else{
            System.out.println(-1);
        }
    }

    static int find(int x){
        if(x==parent[x]) return x;
        return parent[x]=find(parent[x]);
    }

    static void union(int x,int y){
        x=find(x);
        y=find(y);

        if(x!=y){ // 큰 수를 작은 수에 add
            if(x>y) parent[x]=y;
            else parent[y]=x;
        }
    }

    static void bfs_mark(int x,int y,int id) { // 현재 위치에서 이어진 점들을 id 로 마킹하기

        Deque<int[]> queue=new ArrayDeque<>();
        int[] pop;
        int xx,yy;

        queue.addLast(new int[] {x,y});
        isVisited[x][y]=true;
        map[x][y]=id;

        while(!queue.isEmpty()) {
            pop=queue.removeFirst();
            xx=pop[0];
            yy=pop[1];

            for(int i=0;i<4;i++) {
                x=xx+dx[i];
                y=yy+dy[i];

                if(isBoundary(x,y)) continue;
                if(map[x][y]==0 || isVisited[x][y]) continue;
                queue.addLast(new int[] {x,y});
                isVisited[x][y]=true;
                map[x][y]=id;
            }
        }
    }

    static void dfs(int x,int y,int cx,int cy,int dir,int depth,int islandNum){ // x,y 는 맨 처음 탐색 시작점, cs, cy 는 현재 위치, dir 은 탐색 방향 인덱스, depth 는 다리 길이 , islandNum 은 탐색 시작하는 섬의 번호
        int xx=cx+dx[dir];
        int yy=cy+dy[dir];

        if(isBoundary(xx,yy)) return; // 경계

        if(map[xx][yy]==islandNum) return; // 같은 섬

        if(map[xx][yy]==0) { // 바다 -> 계속 탐색
            depth=depth+1;
            dfs(x,y,xx,yy,dir,depth,islandNum);
            depth--;
        }

        if(map[xx][yy] !=0 && map[xx][yy] != islandNum){
            if(depth>=2){
                pq.add(new bridge(map[x][y],map[xx][yy],depth));
            }
            return;
        }

    }

    static boolean isBoundary(int x, int y) {
        return x<0 || x>=N || y<0 || y>=M;
    }

    static boolean adjacentOcean(int x,int y) {
        boolean flag=false;
        int xx,yy;

        for(int i=0;i<4;i++) {
            xx=x+dx[i];
            yy=y+dy[i];

            if(isBoundary(xx,yy)) continue;
            if(map[xx][yy]==0) {
                flag=true;
                break;
            }
        }
        return flag;
    }

}
