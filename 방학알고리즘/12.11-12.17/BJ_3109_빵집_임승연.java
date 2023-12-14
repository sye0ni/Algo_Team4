import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 47260 kb, 336 ms
 * 
 * 아이디어
 * 위에서부터 시작점으로 지정하여, 최대한 위쪽 경로로 연결할 수 있는 경우에 파이프를 연결한다
 *
 * 문제점
 * 아이디어는 맞았는데, 자꾸 시간 초과가 발생했다
 * 모든 경로를 따로 생각하여 isVisited 를 여러번 초기화하고 계속해서 같은 점을 방문하게 하는 문제때문이었음 ..!
 * 생각해보니, 한 번 방문 했으면 그 경로로 파이프가 연결 되었는지 유무에 상관 없이 다시 방문할 필요가 x
 * 조건까지 꼼꼼하게 정리를 한 뒤에 코딩하기
 */
public class BJ_3109_빵집_임승연 {
    static int R,C;
    static char[][] graph;
    static int cnt;
    static boolean[][] isVisited; // dfs 방문 처리
    static int[] dx=new int[]{-1,0,1}; // y 는 항상 전진

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        graph=new char[R][C];
        isVisited=new boolean[R][C];
        String str;
        cnt=0;

        for(int i=0;i<R;i++){
            str=br.readLine();
            for(int j=0;j<C;j++){
                graph[i][j]=str.charAt(j);
            }
        } // 그래프 생성

        for(int i=0;i<R;i++){
            if(dfs(i,0)){
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    static boolean dfs(int x,int y){

        if(y==C-1){ // 연결 성공
            return true;
        }

        int xx,yy;
        for(int i=0;i<3;i++){
            xx=x+dx[i];
            yy=y+1;

            if(xx<0 || xx>=R || yy<0 || yy>=C) continue;
            if(isVisited[xx][yy]) continue;
            if(graph[xx][yy]=='x') continue;

            isVisited[xx][yy]=true;
            if(dfs(xx,yy)){
                return true;
            }
        }
        return false;

    }
}