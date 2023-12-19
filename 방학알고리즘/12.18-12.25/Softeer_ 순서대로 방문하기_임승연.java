import java.io.*;
import java.util.*;

/**
* 모든 경로를 탐색해아하므로 dfs 수행 
* 목표 지점을 나타내는 index 를 인자로 전달 
*/
public class Main {

    static int N,M; 
    static int cnt;
    static int[][] graph;
    static boolean[][] visited;
    static int[][] spots;
    static int[] dx=new int[]{-1,1,0,0};
    static int[] dy=new int[]{0,0,-1,1}; 
  
    public static void main(String[] args) throws IOException {
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st=new StringTokenizer(br.readLine());
      N=Integer.parseInt(st.nextToken());
      M=Integer.parseInt(st.nextToken());

      graph=new int[N][N];
      cnt=0;
      visited=new boolean[N][N];
      spots=new int[M][2]; // 거쳐갈 경로 저장 

      for(int i=0;i<N;i++){
        st=new StringTokenizer(br.readLine());
        for(int j=0;j<N;j++){
          graph[i][j]=Integer.parseInt(st.nextToken()); 
        }
      } // 그래프 생성 완료 

      for(int i=0;i<M;i++){
        st=new StringTokenizer(br.readLine());
        spots[i][0]=Integer.parseInt(st.nextToken())-1;
        spots[i][1]=Integer.parseInt(st.nextToken())-1; 
      } // 거쳐갈 위치 저장 

      visited[spots[0][0]][spots[0][1]]=true; 
      dfs(spots[0][0],spots[0][1],1); // 0번 점에서 dfs 수행 , 1번점을 찾아야함 
      System.out.println(cnt);
          
    }

    static void dfs(int x,int y,int index){ // x,y 는 현재 위치, index 는 찾아야하는 점 나타냄 
      if(x==spots[index][0] && y==spots[index][1]) {
         if(index==M-1){
           cnt++;
           return;
         } // 최종 목적지 도착 
        index++;
        if(index>=M) return; // 실패 
      } 

      int xx,yy;
      for(int i=0;i<4;i++){
        xx=x+dx[i];
        yy=y+dy[i]; 

          if(xx<0 || xx>=N || yy<0 || yy>=N) continue;
          if(visited[xx][yy] || graph[xx][yy]==1) continue;
            visited[xx][yy]=true;
            dfs(xx,yy,index);
            visited[xx][yy]=false;
        }
      }
}
