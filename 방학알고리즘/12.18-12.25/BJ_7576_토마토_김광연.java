/**
 * 
 */
package 구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * BJ_7576_토마토_김광연
 * 메모리 109168 kb 시간 536 ms
 * 아이디어
 * 자영이가 추천하길래 풀었는데 이름만 같은 다른 문제였음
 * 그래도 푸는 방식은 비슷한듯. BFS 활용해서 상하좌우 탐색
 * 입력받으면서 빈칸의 개수와 익은 토마토 개수를 세서 개수를 비교해 토마토가 최대로 익었는지 확인함
 * 우선 처음에 익은 토마토들을 큐에 넣음. 큐에 넣을 때는 해당 좌표와 시간을 같이 넣어줌
 * 큐가 빌때까지 BFS 탐색. 큐가 비었을 때는 개수 비교해서 뭘 출력할지 확인
 * 방문 체크는 방문한 곳은 익은 토마토로(1)로 바꿔서 따로 visit 배열 만들 필요 없었음
 * 유의할 점
 * N과 M 입력 받을 때 뭐가 먼저인지 확인하기! 
 */
public class BJ_7576_토마토_김광연 {
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); // 가로칸 수
		N = Integer.parseInt(st.nextToken()); // 세로칸 수
		int[][] board = new int[N][M]; // 토마토들
		int none = 0; // 토마토가 없는 빈칸
		int ripen = 0; // 익은 토마토
		Queue<int[]> que = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == -1) { // 토마토가 없는 곳이면
					none++;
				}
				if (board[i][j] == 1) { // 이미 익은 토마토면
					ripen++;
					que.offer(new int[] {i, j, 0}); // 좌표와 시간을 큐에 넣기
				}
			}
		}
		
		if (ripen == (N*M - none)) { 
			System.out.println(0);
		} else if (ripen == 0){
			System.out.println(-1);
		} else {
			int time = 0;
			while (!que.isEmpty()) {
				int[] cur = que.poll();
				for (int k = 0; k < 4; k++) { // 상하좌우 탐색
					int x = cur[0] + dx[k];
					int y = cur[1] + dy[k];
					
					if (!isBoudary(x, y) || board[x][y] == 1 || board[x][y] == -1) {
						// 범위에서 벗어나거나 이미 토마토가 익었거나 빈칸인 경우 패스
						continue;
					}
					board[x][y] = 1; // 토마토 익히기
					ripen++;
					que.offer(new int[] {x, y, cur[2]+1});
					time = cur[2] + 1;
				}
			}
			if (ripen == (N*M - none)) { // 익은 토마토 개수가 전체에서 빈칸 뺀 수와 같으면 => 최대면
				System.out.println(time); // 시간 출력
			} else { // 안익은 토마토 있으면 -1 출력
				System.out.println(-1);
			}
		}
	}
	private static boolean isBoudary(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
