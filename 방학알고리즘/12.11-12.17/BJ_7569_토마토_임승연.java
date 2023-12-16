import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 102464 kb, 704 ms
 * 높이가 추가된 그래프의 bfs 를 위해 3차원 배열을 만들고 똑같이 bfs 수행한다
 * 한 번 익은 토마토는 다시 담을 필요가 없다
 * 날짜를 저장하는 배열을 추가로 만들고, 방문 배열처럼 쓰기 위해 초기화 해주었음
 *
 */
public class BJ_7569_토마토_임승연 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N,M,H;

        st=new StringTokenizer(br.readLine());
        M=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());
        H=Integer.parseInt(st.nextToken());

        int[][][] original=new int[N][M][H];
        int[][][] day=new int[N][M][H];
        Deque<int[]> tomato=new ArrayDeque<>();

        for(int i=0;i<N*H;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                original[i%N][j][i/N]=Integer.parseInt(st.nextToken());
                if(original[i%N][j][i/N]==1) {
                    tomato.add(new int[]{i%N, j, i/N}); // 익은 토마토 저장
                    day[i%N][j][i/N]=0;
                }
                else{
                    day[i%N][j][i/N]=-1;
                }
            }
        } // 그래프 생성

        int[] dx=new int[]{-1,1,0,0};
        int[] dy=new int[]{0,0,-1,1};
        int[] dz=new int[]{-1,1};

        int[] curr;
        int x,y,z;

        while(!tomato.isEmpty()){
            curr=tomato.removeFirst();

            for(int i=0;i<4;i++){ // 상하좌우 확인
                x=curr[0]+dx[i];
                y=curr[1]+dy[i];
                z=curr[2];

                if(x<0 || x>=N || y<0 || y>=M ) continue;
                if(day[x][y][z]==-1 && original[x][y][z]==0) { // 아직 안익은 토마토
                    tomato.add(new int[]{x,y,z});
                    day[x][y][z]=day[curr[0]][curr[1]][curr[2]]+1;
                }
            }

            for(int i=0;i<2;i++) { // 위 아래 층 확인
                x = curr[0];
                y = curr[1];
                z = curr[2] + dz[i];

                if (z < 0 || z >= H) continue;
                if (day[x][y][z] == -1 && original[x][y][z] == 0) { // 아직 안익은 토마토
                    tomato.add(new int[]{x, y, z});
                    day[x][y][z] = day[curr[0]][curr[1]][curr[2]] + 1;
                }
            }
        }

        int max=-1;

        for(int h=0;h<H;h++){
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(original[i][j][h]==0 && day[i][j][h]==-1){ // 안 익은 토마토가 있다면 종료
                        System.out.println(-1);
                        return;
                    }
                    if(day[i][j][h]>max){
                        max=day[i][j][h];
                    }
                }
            }
        }

        System.out.println(max);

    }
}