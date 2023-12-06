/**
 * 
 */
package 그래프이론;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * BJ_2573_빙산_김광연
 * 메모리 74296 kb 시간 388 ms
 * 아이디어
 * BFS + 구현 문제. 금방 풀 줄 알았는데 생각보다 헷갈렸음
 * 빙산의 상하좌우 탐색해서 물의 개수만큼 수를 줄이고 빙산들끼리 한번에 연결이 안 될 때를 구하는 문제
 * 우선 빙산을 찾기 시작하는 좌표를 지정해서 해당 좌표에서 BFS 했을 때 나오는 빙산의 개수가 총 빙산의 개수와 다를 때를 구하려고 함
 * 이를 위해, 사라지는 빙산 개수와 시작 위치에서의 빙산 수를 구해서 총 개수와 비교함
 * 유의할 점
 * 한 덩어리가 한번에 사라지는 경우를 생각 못했음. 더 꼼꼼하게 생각할것
 * 그리고 방문 체크할 때 BFS할 때마맏 방문 배열을 초기화해줬는데 다른 사람이 푼거 보니깐 boolean으로 안하고 int 타입으로 만들어서 time을 인수로 넣으면 초기화를 계속 안해도 되는 방법 있었음.
 * 
 */
public class BJ_2573_빙산_김광연 {
	static int[][] board;
	static int N, M;
	static int sx, sy;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int gone;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		int sum = 0; // 빙산 총 개수
		sx = 0; // 빙산 덩어리 시작 좌표
		sy = 0;
		gone = 0;
		board = new int[N][M]; 
		int time = 0;
		int flag = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] > 0) { // 빙산의 개수
					sum++;
				}
				if (board[i][j] > 0 && flag == 0) { // 시작점
					sx = i;
					sy = j;
					flag = 1; 
				}
			}
		}
		
		while (true) {
			int iceCnt = melt(sx, sy);
			if (gone == sum) { // 빙산이 한번에 다 사라지면
				System.out.println(0);
				break;
			}
			if (iceCnt != sum) {
				System.out.println(time);
				break;
			}
			sum -= gone; // 빙산 사라진 수만큼 총합에서 빼기
			time++; // 시간 +1
			gone = 0; // 녹은 빙산 수 초기화
		}
		
	}
	
	static int melt(int x, int y){ // 빙산 녹이기
		Queue<int[]> que = new ArrayDeque<>();
		que.offer(new int[] {x, y});
		boolean[][] visit = new boolean[N][M];
		visit[x][y] = true;
		int cnt = 0;
		
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			for (int k = 0; k < 4; k++) { // 상하좌우 탐색
				int xx = cur[0] + dx[k];
				int yy = cur[1] + dy[k];
				
				if (!isBoundary(xx, yy) || visit[xx][yy]) { // 범위에서 벗어나거나 이미 방문했으면 패스
					continue;
				}			
				if (board[xx][yy] < 1) { // 물인 경우
					board[cur[0]][cur[1]]--; // 이동하기 전 빙산을 -1
					continue;
				}
				visit[xx][yy] = true;
				que.add(new int[] {xx, yy}); // 이동한 곳 큐에 넣어주기
			}
			if (board[cur[0]][cur[1]] < 1) { // 다 녹으면
				gone++; // 없어진 빙산 +1
			} else { // 빙산 찾기 시작 좌표 설정
				sx = cur[0];
				sy = cur[1];
			}
			cnt++; // 상하좌우 탐색 완료한 빙산 개수 +1
		}
		return cnt;
	}
	
	static boolean isBoundary(int x, int y) { // 범위 안에 드는지 확인하는 메서드
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
