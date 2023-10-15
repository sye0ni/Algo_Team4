import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
* BJ_14502_연구소
* 83612kb	412ms
*  
* 비어있는 칸 중에 3개를 조합으로 뽑아서 벽을 세우고 바이러스를 확산시킨후에 남아있는 빈칸의 갯수 구하기!!
* 벽을 세울때 원래의 배열을 복사한 새로운 배열을 사용하였다.
*/

public class Main {
    
    static List<int[]> blank, virus;
    static int[] selectBlank, dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static int N, M, size, max;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        blank = new ArrayList<>();
        virus = new ArrayList<>();
                
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==0) {
                    blank.add(new int[] {i, j});
                }else if(map[i][j]==2) {
                    virus.add(new int[] {i, j});
                }
            }
        }
        
        size = blank.size();
        max = 0;
        selectBlank = new int[3];
        selectBlank(0, 0);      
        
        System.out.println(max);
        
    }

    private static void selectBlank(int cnt, int start) {
        
        if(cnt==3) {
            makeWall();
            return;
        }
        
        for(int i=start; i<size; i++) {
            selectBlank[cnt] = i;
            selectBlank(cnt+1, i+1);
        }
        
    }

    private static void makeWall() {
        
    	int copy[][] = new int[N][M];
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<M; j++) {
    			copy[i][j] = map[i][j];
    		}
    	}
        // 벽세우기
        for(int i=0; i<3; i++) {
            int now[] = blank.get(selectBlank[i]);
            copy[now[0]][now[1]] = 1;
        }
        
        // 바이러스 퍼지기
        spread(copy);
    }

    private static void spread(int[][] copy) {

    	Deque<int[]> que = new ArrayDeque<>();
    	for(int i=0; i<virus.size(); i++) {
    		que.add(virus.get(i));
    		while(!que.isEmpty()) {
    			int vi[] = que.poll();
    			for(int j=0; j<4; j++) {
    				int nextX = vi[0] + dx[j];
    				int nextY = vi[1] + dy[j];
    				if(nextX<0 || nextX>=N || nextY<0 || nextY>=M) continue;
    				// 범위안에 있으면 빈칸인가 확인
    				if(copy[nextX][nextY]!=0) continue;
    				// 빈칸이면 바이러스 확산시키고 큐에 넣기
    				copy[nextX][nextY] = 2;
    				que.add(new int[] {nextX, nextY});
    			}		   
    		}    		
    	}
        // 확산후에 확산되지 않은 부분 세기
        safeZone(copy);
    }

	private static void safeZone(int[][] copy) {
		
		int safe = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copy[i][j]==0) safe++;
			}
		}
		
		max = Math.max(max, safe);
	}

}
