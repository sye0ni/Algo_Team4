package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 106820kb 572ms
 * 어우.....한 10번만에 성공한듯....당분간 토마토 처다도 안볼꺼임....
 * 처음에도 비슷한 방법으로 풀었는데 큐에 4크기의 배열을 저장하면 메모리가 클 것 같아서 bfs를 돌릴 때 크기를 구해서
 * 그만큼만 bfs돌리고 더이상 할 필요가 없으면 break하도록 구현했었는데 bfs를 다 돌리고 나서 확인하는 거보디
 * 중간에 더 할 필요가 있는지 체크하고 멈추는게 시간이 더 많이 소모되나봄 시간초과 진심 9번남
 * 그래서 그냥 bfs 끝까지 돌리고 다 돌린후에 체크하는 방법으로 바꾸니까 시간초과 안남
 * 그래도 4개 저장안 할려고 bfs크기 구한만큼 돌리는 거는 똑같이 사용했는데 3개나 4개나 메모리 차이도 별로 없는듯^^
 * 시간은 나쁘지 않게 나와서 만족~~ 어쨌든 해결~~~~
 */

public class BJ7569 {
    
    static int N, M, H, dif;
    static int box[][][];
    static int dx[] = {-1, 0, 1, 0, 0, 0}, dy[] = {0, 1, 0, -1, 0, 0}, dz[] = {0, 0, 0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        box = new int[H][N][M];
        Deque<int[]> ripe = new ArrayDeque<>();
        
        for(int i=0 ;i<H; i++) {
            for(int j=0; j<N; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<M; k++) {
                	int egg = Integer.parseInt(st.nextToken());
                    box[i][j][k] = egg;
                    if(egg == 1) {
                    	ripe.add(new int[] {i, j, k});
                    }
                }
            }
        }
        
        int day = 0;
        int size = ripe.size();

        // 익은 토마토 수만큼만 돌리기
        // 모두 안익은 토마토인 경우도 있어서 어차피 다음 while문 실행안되므로 0일때도 실행함
        while(size >= 0) {

        	int cnt = 0;
        	
        	while(cnt < size) {
        		// 익은 토마토 하나
        		int ripeTomato[] = ripe.poll();
        		int h = ripeTomato[0];
        		int n = ripeTomato[1];
        		int m = ripeTomato[2];
        		// 익은 계란 근처에 안익은 토마토 있나 확인
                for(int i=0; i<6; i++) {
                    int newX = n+dx[i];
                    int newY = m+dy[i];
                    int newZ = h+dz[i];
                    if(newX<0 || newX>=N || newY<0 || newY>=M || newZ<0 || newZ>=H) continue; 
                    // 범위이면 멀쩡토마토 인지 확인, 아니면 다음
                    if(box[newZ][newX][newY]!=0) continue;
                    // 멀쩡토마토이고 방문안했으면 익힌후에 방문처리하고 ripeEgg에 집어넣자
                    box[newZ][newX][newY] = 1;
                    ripe.add(new int[] {newZ, newX, newY});
                }
                
                cnt ++;
        	}
        	// 익은 토마토 갯수 확인
        	size = ripe.size();
        	// 익은 토마토가 더 이상 없다면
        	if(size == 0) {
        		// 남은 안익토 계산
        		int toma = check();
        		// 남은 안익토가 있으면 -1
        		if(toma != 0) {
        			day = -1;
        		}
            // 0인 경우도 돌기때문에 break 걸어줘야함
        		break;
        	}else {        		
        		day++;
        	}
        	
        }
        
        System.out.println(day);
   }
        


    private static int check() {
        
        int toma = 0;
        
        for(int i=0 ;i<H; i++) {
            for(int j=0; j<N; j++) {
                for(int k=0; k<M; k++) {
                    if(box[i][j][k] == 0) toma++;
                }
            }
        }
        
        return toma;
    }

}
