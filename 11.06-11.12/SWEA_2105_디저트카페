import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* SWEA_2105_디저트카페
* 27,748 kb 197 ms
* DFS사용 - 좌하, 우하, 우상, 좌상 순서로 움직여서 사각형을 만든다.
*/

public class Solution {
     
    static int N, nowX, nowY, max;
    static int dx[] = {1, 1, -1, -1}, dy[] = {-1, 1, 1, -1};
    static int dessert[][];
    static boolean visited[][], eat[];
     
    public static void main(String[] args) throws IOException {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
         
        for(int test_case=1; test_case<=T; test_case++) {     
                    
            N = Integer.parseInt(br.readLine());
            dessert = new int[N][N];
            max = -1;
             
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    dessert[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            for(int i=0; i<N-2; i++) {
                for(int j=1; j<N-1; j++) {
                    visited = new boolean[N][N];
                    eat = new boolean[101];
                    visited[i][j] = true;
                    eat[dessert[i][j]] = true;
                    nowX = i;
                    nowY = j;
                    go(i, j, 0, 1);                 
                }
            }
             
            System.out.println("#"+test_case+" "+max);
 
        }
         
    }
 
    private static void go(int sx, int sy, int now, int cnt) {
         
        for(int i=now; i<4; i++) {
            int newX = sx + dx[i];
            int newY = sy + dy[i];
             
            if(newX<0 || newX >=N || newY<0 || newY>=N) continue;
            if((newX == nowX)&&(newY == nowY)&&cnt>2) {
                max = Math.max(max, cnt);
                return;
            }
            if(!visited[newX][newY] && !eat[dessert[newX][newY]]) {
                visited[newX][newY] = true;
                eat[dessert[newX][newY]] = true;
                go(newX, newY, i, cnt+1);
                visited[newX][newY] = false;
                eat[dessert[newX][newY]] = false;
            }
             
        }
         
    }
 
}
