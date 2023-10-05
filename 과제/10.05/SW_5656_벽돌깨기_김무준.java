import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 구현문제
 * 1. 구슬을 떨어뜨릴 곳 행을 중복 가능한 조합으로 구함
 * 2. n만큼 반복하면서 구슬을 떨어트림
 * 3. 구슬이 처음으로 만난 0이 아닌 숫자가 폭발함
 * 4. bfs를 통해 4방탐색을 하면서 적혀있던 숫자의 크기-1 만큼 폭발
 * 5. 빈칸이 있으면 블록이 떨어짐
 * 
 * 4,696 kb		1,284 ms
 * @author 김무준
 *
 */
public class SW_5656_벽돌깨기_김무준 {
	
	// 높이, 너비, 구슬 개수
	static int w, h, n;
	// 원본 배열
	static int[][] arr;
	// 배열 복사본
	static int[][] copy;
	// 조합을 저장해줄 배열
	static int[] res;
	// 남은 블록 개수의 최소값
	static int min;
	// 4방탐색
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	// 조합
	static void comb(int cnt) {
		// 구슬 개수만큼 뽑았으면 실행
 		if(cnt == n) {
 			// 배열 복사 - 원본 배열은 유지
			for(int i=0; i<h; i++) {
 				copy[i] = Arrays.copyOf(arr[i], w);
			}
			// 정해진 행 위치에 구슬 개수만큼 떨어트림
			for(int i=0; i<n; i++) {
				drop(res[i]);
			}
			// 남은 블록 개수 계산
			int sum = 0;
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(copy[i][j] != 0) sum++;
				}
			}
			min = Math.min(min, sum);
			return;
		}
		
 		// 조합이 가능하므로 0부터 재귀
		for(int i=0; i<w; i++) {
			res[cnt] = i;
  			comb(cnt+1);
		}
		
	}
	
	// 구슬 떨어트리는 메서드, 구슬을 떨어트릴 행을 매개변수로 받음
	static void drop(int k) {
		// 열을 0부터 내려가면서 0이 아닌 숫자를 만나면 폭발
		for(int i=0; i<h; i++) {
			if(copy[i][k] == 0) continue;
			else {
				// 폭발 메서드
				boom(i, k);
				// 0인 곳이 있으면 블록이 떨어지는 메서드
				zeros();
				break;
			}
		}
	}
	
	// 구슬이 처음 닿은 블록이 폭발하는 메서드
	static void boom(int row, int col) {
		// bfs를 위한 큐
		Queue<int[]> q = new ArrayDeque<>();
		
		// 매개변수로 받은 처음 만난 블록의 행, 열 값과 블록의 숫자 집어넣음
		q.add(new int[] {row, col, copy[row][col]});
		// 해당 블록이 터졌음을 의미
		copy[row][col] = 0;
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int r = tmp[0];
			int c = tmp[1];
			// 블록의 숫자는 터지는 범위
			int num = tmp[2];
			
			// 터지는 범위는 크기-1이므로 num미만까지 반복
			for(int i=1; i<num; i++) {
				// 4방탐색하면서 폭발하는 크기를 위해 방향델타*i
				for(int j=0; j<4; j++) {
					int nr = r + dr[j]*i;
					int nc = c + dc[j]*i;
					
					// 배열을 벗어나면 패스
					if(nr<0 || nr>=h || nc<0 || nc>=w || copy[nr][nc] == 0) continue;
					
					// 0이 아닌 블록을 만나면
					if(copy[nr][nc] != 0) {
						// 해당 블록의 행, 열, 숫자 큐에 입력
						q.offer(new int[] {nr, nc, copy[nr][nc]});
						// 해당 블록이 터졌으므로 0처리
						copy[nr][nc] = 0;
					}
				}
			}
		}
	}
	
	// 블록 밑으로 0이 있으면 아래로 내리는 메서드
	static void zeros() {
		// 내려가야 할 블록의 값을 저장할 스택 - 위에서 아래로 탐색하므로 스택을 사용하여 마지막에 저장한 값을 먼저 꺼냄
		Stack<Integer> s = new Stack<>();
		// 세로로 우선 탐색
		for(int j=0; j<w; j++) {
			for(int i=0; i<h; i++) {
				if(copy[i][j] != 0) {
					// 값 저장
					s.push(copy[i][j]);
				}
			}
			// 아래에서 위로 탐색
			for(int i=h-1; i>=0; i--) {
				// 스택이 비었으면 나머지는 다 0
				if(s.isEmpty()) copy[i][j] = 0;
				// 스택에 저장된 값 배치
				else copy[i][j] = s.pop();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int t=1; t<=tc; t++) {
			sb.append("#").append(t).append(" ");
			
			st = new StringTokenizer(br.readLine());
			// 값 입력
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			// 값들 초기화
			arr = new int[h][w];
			copy = new int[h][w];
			res = new int[n];
			min = Integer.MAX_VALUE;
			
			for(int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			comb(0);
			sb.append(min).append("\n");
		}
		System.out.println(sb.toString());
	}
}
