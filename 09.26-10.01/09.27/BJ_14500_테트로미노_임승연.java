import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 모든 점을 시작점으로 두고 dfs 의 깊이가 3이 될 때까지 수행
 * 단, ㅗ 모양은 따로 구현을 해야 한다!
 *
 * 처음에 이렇게 짰는데 바로 시간초과가 나버려서 이상해서 게시판을 봤더니
 * main 함수에서 dfs 를 수행할 때마다 visited=new boolean[N][M] 초기화를 해줘서 그런 거였음 ,,
 * 초기화는 꼭 필요한 곳에서만 하기
 *
 * 	31340 kb, 596 ms
 *
 */
public class Main {

    static int[][] graph;
    static int Max; // 최댓값 저장
    static int N,M;
    static boolean[][] visited;

    static int[] dx=new int[]{-1,1,0,0};
    static int[] dy=new int[]{0,0,-1,1}; // 상하좌우 저장

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        graph=new int[N][M];
        Max=0;
        visited=new boolean[N][M];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<M;j++){
                graph[i][j]=Integer.parseInt(st.nextToken());
            }
        } // 그래프 생성

        // dfs 반복
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                visited[i][j]=true;
                dfs(i,j,graph[i][j],0);
                visited[i][j]=false;
            }
        }

        // ㅗ, ㅜ, ㅓ , ㅏ 모양 처리
        hnjk();

        System.out.println(Max);

    }

    static void dfs(int x,int y,int sum,int depth){
        if(depth==3) {
            if(sum>Max){
                Max=sum;
            }
            return;
        }

        int xx,yy;
        for(int i=0;i<4;i++){
            xx=x+dx[i];
            yy=y+dy[i];

            if(xx<0 || xx>=N || yy<0 || yy>=M) continue;
            if(visited[xx][yy]) continue;
            sum+=graph[xx][yy];
            visited[xx][yy]=true;
            dfs(xx,yy,sum,depth+1);
            sum-=graph[xx][yy];
            visited[xx][yy]=false;
        }
    }

    static void hnjk(){
        int sum=0;
        int oh,woo,ah,uh;

        // ㅗ, ㅜ : 맨 윗줄, 맨 아랫줄만 제외하면 같이 처리
        for(int i=0;i<N;i++){
            sum=0;
            for(int j=0;j<M-2;j++){
                if(i==0){
                    woo=graph[0][j]+graph[0][j+1]+graph[0][j+2];
                    woo+=graph[1][j+1];
                    if(woo>Max) Max=woo;
                }
                else if(i==N-1){
                    oh=graph[N-1][j]+graph[N-1][j+1]+graph[N-1][j+2];
                    oh=graph[N-2][j+1];
                    if(oh>Max) Max=oh;
                }
                else {
                    sum = graph[i][j] + graph[i][j + 1] + graph[i][j + 2];
                    oh = sum + graph[i - 1][j + 1];
                    woo = sum + graph[i + 1][j + 1];
                    if (oh > Max) Max = oh;
                    if (woo > Max) Max = woo;
                }
            } // 3칸씩 나누기
        }

        // ㅓ, ㅏ 모양의 처리도 맨 앞 줄, 맨 뒷줄만 제외하면 같이 처리
        for(int i=0;i<N-2;i++){
            for(int j=0;j<M;j++){
                if(j==0){
                    ah=graph[i][0]+graph[i+1][0]+graph[i+2][0];
                    ah+=graph[i+1][1];
                    if(ah>Max) Max=ah;
                }
                else if(j==M-1){
                    uh=graph[i][M-1]+graph[i+1][M-1]+graph[i+2][M-1];
                    uh+=graph[i+1][M-2];
                    if(uh>Max) Max=uh;
                }
                else{
                    sum=graph[i][j]+graph[i+1][j]+graph[i+2][j];
                    uh=sum+graph[i+1][j-1];
                    ah=sum+graph[i+1][j+1];
                    if(uh>Max) Max=uh;
                    if(ah>Max) Max=ah;
                }
            }
        }   // for
    }
}
