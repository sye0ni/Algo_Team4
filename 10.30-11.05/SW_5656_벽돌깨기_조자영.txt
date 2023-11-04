import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SW_5656_벽돌깨기
 * 109,956kb 1,634 ms => 96,368 kb 451 ms
 * 
 * 중복 순열로 벽돌을 떨어트리는 순서를 정하고 가장 위에 블록을 찾은 후에 크기 만큼 벽돌 부시기
 * 나름 블록이 남은 거가 없으면 그만 하도록 끊어봤는데 시간이 1/4로 줄었음^^
 * W랑 H가 헷갈려서 한참 걸렸다ㅠㅜ 이런 문제 너무 싫어....
 */
 
public class Solution {
     
    static int block[][], balls[], copy[][];
    static int dx[] = {0, -1, 0, +1}, dy[] = {-1, 0, 1, 0};
    static int N, W, H, min;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
 
        for(int test_case=1; test_case<=T; test_case++) {
             
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
             
            block = new int[H][W];
            copy = new int[H][W];
             
            for(int i=0; i<H; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++) {
                    block[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            balls = new int[N];
            min = Integer.MAX_VALUE;
            // 벽돌깰 순서 순열로 만들기
            ball(0);
             
            System.out.println("#"+test_case+" "+min);
        }
    }
 
    private static void ball(int cnt) {
         
        if(cnt==N) {
//          for(int i=0; i<N; i++) {
//              System.out.print(balls[i]);
//          }
//          System.out.println("");
             
            // 벽돌깨기
            fall();
            int remain = countBlock();
            min = Math.min(min, remain);
            return;
        }
         
        for(int i=0; i<W; i++) {
            if(min==0) break;
            balls[cnt] = i;
            ball(cnt+1);
        }
         
    }
 
    private static void fall() {
         
        copy(copy, block);
        for(int i=0; i<N; i++) {
            // 깨뜨릴 위치
            int now = balls[i]; 
            // 남은 블럭 갯수가 0이면 더 할 필요 없음
            if(countBlock()==0) break;
             
            for(int j=0; j<H; j++) {
                if(copy[j][now]!=0) {
                    if(copy[j][now]==1) {
                        copy[j][now] = 0;
                        break;
                    }
                    crush(j, now);
                    break;
                }
            }
            // 깨뜨린 후에 비어있는 공간 땡기기
            push();
        }
         
    }
 
    private static void crush(int x, int y) {
         
        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[] {x,y});
 
         
        while(!que.isEmpty()) {
            int now[] = que.poll();
            int range = copy[now[0]][now[1]];
            copy[now[0]][now[1]] = 0;
             
            for(int i=0; i<4; i++) {
                int newX = now[0];
                int newY = now[1];
                for(int j=1; j<range; j++) {
                    newX += dx[i];
                    newY += dy[i];
                    if(newX<0 || newX>=H || newY<0 || newY>=W) break;
                    if(copy[newX][newY]!=0) {
                        que.add(new int[] {newX, newY});
                    }
                }
            }
        }
    }
     
    private static void push() {
         
        for(int i=0; i<W; i++) {
            List<Integer> save = new ArrayList<>();
            for(int j=H-1; j>=0; j--) {
                if(copy[j][i]!=0) {
                    save.add(copy[j][i]);
                } 
                copy[j][i] = 0;
            }
             
            for(int j=0; j<save.size(); j++) {
                copy[H-j-1][i] = save.get(j); 
            }
        }
         
         
    }
     
    private static void copy(int[][]to, int[][] from) {
         
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                to[i][j] = from[i][j];
            }
        }
         
    }
     
    private static int countBlock() {
         
        int remain = 0;
                 
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                if(copy[i][j]!=0) {
                    remain ++;
                }
            }
        }
         
        return remain;
         
    }
 
}
