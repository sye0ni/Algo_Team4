import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 단순히 모든 육지에서 bfs 수행하는 문제 
 * 1. 큐에 depth 값을 넣는 경우와
 * 2. depth를 나타내는 2차원 배열 중 어떤 경우가 더 메모리가 크게 나오는지 궁금해서
 * 둘 다 해봤음
 *
 * 전자는 217612kb	432ms
 * 후자가 245112kb	528ms
 * 미세하지만 큐에 depth 값까지 넣어주는 것이 더 메모리가 작았다 !! 그래서 코드도 1번 버전 
 */

public class Main {

    static char[][] map;
    static boolean[][] isVisited;
    static Deque<int[]> queue=new ArrayDeque<>();
    static int N,M,Max;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        String line;
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        isVisited=new boolean[N][M];

        map=new char[N][M];
        for(int i=0;i<N;i++){
            line=br.readLine();
            for(int j=0;j<M;j++){
                map[i][j]=line.charAt(j);
            }
        } // 맵 생성

        Max=0;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){

                if(map[i][j]=='L'){
                    bfs(i,j);
                }
            }
        }
        System.out.println(Max);

    }

    static void bfs(int x,int y){
        isVisited=new boolean[N][M];
        queue.clear();
        //   int[][] depth=new int[N][M]; // bfs 거리 저장용
        int[] dx=new int[]{-1,1,0,0};
        int[] dy=new int[]{0,0,-1,1};
        int[] pop;
        int xx,yy;

        queue.add(new int[]{x,y,0});
        isVisited[x][y]=true;

        while(!queue.isEmpty()){
            pop=queue.removeFirst();
            x=pop[0];
            y=pop[1];

            for(int i=0;i<4;i++){
                xx=x+dx[i];
                yy=y+dy[i];

                if(xx<0 || xx>=N || yy<0 || yy>=M) continue;
                if(isVisited[xx][yy]) continue;
                if(map[xx][yy]=='W') continue;

                isVisited[xx][yy]=true;
                queue.addLast(new int[]{xx,yy,pop[2]+1});
                if(pop[2]+1>Max){
                    Max=pop[2]+1;
                }
            }
        }
    }
}
