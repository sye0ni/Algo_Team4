import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어 - 간단한 bfs 문제라고 생각
 * 불 확산 -> 지훈이 이동 순서로 조건들을 잘 살피면서 따라간다
 * 불은 사라지지 않으므로 arraylist 에 계속해서 저장해나가고,
 * 지훈이가 이동할 수 있는 위치는 계속해서 삽입 삭제가 이루어지므로 deque 사용
 *
 * 으로 생각했다가 불을 arraylist 에 저장하고 매번 확신시키니 시간 초과가 발생했다
 * 생각해보니 한 번 확산한 불은 다시 볼 필요가 없으므로 deque 로 바꿔주니 통과 ㅠㅠ
 *
 *
 */
public class BJ_4179_불_임승연 {
    static Deque<int[]> jihoon;
    static Deque<int[]> fire;
    static int[] dx=new int[]{-1,1,0,0};
    static int[] dy=new int[]{0,0,-1,1};
    static int R,C;
    static char[][] maze; // 미로
    static boolean[][] isVisited;
    static int sec;
    static boolean flag;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        maze=new char[R][C];
        isVisited=new boolean[R][C];
        jihoon=new ArrayDeque<>();
        fire=new ArrayDeque<>();
        flag=true;
        sec=0;

        String str;

        for(int i=0;i<R;i++){
            str=br.readLine();
            for(int j=0;j<C;j++){
                maze[i][j]=str.charAt(j);
                if(maze[i][j]=='J'){
                    jihoon.addLast(new int[]{i,j});
                    maze[i][j]='.';
                    isVisited[i][j]=true;
                }
                else if(maze[i][j]=='F'){
                    fire.addLast(new int[]{i,j});
                }
            }
        }

        bfs();
        if(!flag) System.out.println("IMPOSSIBLE");
        else System.out.println(sec);
    }

    static void bfs(){
        int[] temp;
        int tempx,tempy;
        int x,y;

        while(true){
            if(jihoon.size()==0){
                flag=false;
                break;
            } // 탈출 실패 !

            sec++;

            // 불 확산
            int iter=fire.size();
            for(int i=0;i<iter;i++){
                temp=fire.removeFirst();
                tempx=temp[0];
                tempy=temp[1];


                for(int j=0;j<4;j++){
                    x=tempx+dx[j];
                    y=tempy+dy[j];

                    if(x<0 || x>=R || y<0 || y>=C) continue;
                    if(maze[x][y]=='.'){
                        maze[x][y]='F';
                        fire.add(new int[]{x,y});
                    }
                }
            }

            // move jihoon
            iter=jihoon.size();
            for(int i=0;i<iter;i++){
                temp=jihoon.removeFirst();
                tempx=temp[0];
                tempy=temp[1];

                for(int j=0;j<4;j++){
                    x=tempx+dx[j];
                    y=tempy+dy[j];

                    if(x<0 || x>=R || y<0 || y>=C){ // 탈출 성공
                        return;
                    }
                    if(maze[x][y]=='.' && !isVisited[x][y]){
                        jihoon.addLast(new int[]{x,y});
                        isVisited[x][y]=true;
                    }
                }

            }
        }
    }
}