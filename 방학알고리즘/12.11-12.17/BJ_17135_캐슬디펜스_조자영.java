package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 44932kb	340ms
 *
 * 못풀었던 문제2
 * 문제를 몇번 다시 읽었는지.... 조건을 제대로 안봐서 삽질제대로함^^
 * 우선순위큐를 이용해서 해결했는데... 다음에 다른 방법도 생각해봐야겠다...먼가 어렵게 푼 기분...
 * 이거 푼사람 방법 공유좀^^
 */

public class BJ17135 {
    
    static int N, M, D, kill, max;
    static int archer[], castle[][], copy[][];
    static boolean isdie[][];

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        archer = new int[3];
        castle = new int[N][M];
        copy = new int[N][M];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                castle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        max = 0;
        per(0, 0);   
        
        System.out.println(max);

    }

    private static void per(int st, int cnt) {
        
        if(cnt == 3) {
            seat();
            return;
        }
        
        for(int i=st; i<M; i++) {
            archer[cnt] = i; 
            per(i+1, cnt+1);
        }
        
    }

    private static void seat() {
        
        // 죽은 적의 수 저장할 변수
        kill = 0;
        // 높이 만큼 반복, 중간에 적들어오면 멈춤
        // 배열 복사해서 사용
        copy();
        
        for(int i=0; i<N; i++) {
            // 궁수 3명
        	// 죽었는지 확인할 배열
        	isdie = new boolean[N][M];
            for(int j=0; j<3; j++) {
                int now = archer[j]; // 궁수 한명씩 나옴
                // 궁수 좌표 N, now
                // 지금 궁수가 적을 맞출 수 있는 지 확인
                shoot(now);
            }
            
            // 죽은 적 없애기
            kill += delete();
            move();
            
        }
        
        max = Math.max(max, kill);
        
    }

    private static int delete() {
    	
    	int cnt = 0;
		
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<M; j++) {
    			if(isdie[i][j]) {
    				copy[i][j] = 0;
    				cnt++;
    			}
    		}
    	}
    	
    	return cnt;
		
	}

	private static void move() {
    	
    	// 마지막줄부터 두번째 줄까지 옮겨줌
    	for(int i=N-1; i>0; i--) {
    		for(int j=0; j<M; j++) {
    			copy[i][j] = copy[i-1][j];
    		}
    	}
    	
    	for(int i=0; i<M; i++) {
    		copy[0][i] = 0;
    	}
    	
	}

	private static void shoot(int pos) {
		
		PriorityQueue <int[]> pque = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]){
					return Integer.compare(o1[2], o2[2]);
				}
				return Integer.compare(o1[0], o2[0]);

			}
		});
		
		
    	for(int i=N-1; i>=0; i--) {
    		for(int j=0; j<M; j++) {
    			if(copy[i][j]==0) continue;
    			// 0이 아니면 이면 범위인지 확인
    			int a = Math.abs(i-N);
    			int b = Math.abs(j-pos);
    			if(a+b <= D){  // 범위이면 우선순위 큐
    				pque.add(new int[] {a+b, i, j});
    			}
    		}
    	}	
    	
    	if(pque.isEmpty()) return;
    	
    	int del[] = pque.poll();
    	
    	isdie[del[1]][del[2]] = true;    		 

	}

	private static void copy() {
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                copy[i][j] = castle[i][j];
            }
        }
    }

}
