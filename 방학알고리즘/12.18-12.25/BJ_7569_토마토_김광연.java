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
 * BJ_7569_토마토_김광연
 * 메모리 105424 kb 시간 580 ms
 * 아이디어
 * 3차원 배열 활용한 BFS 문제
 * 이전 토마토 문제와 비슷하게 풀되 2차원을 3차원으로 바꿔주면 됨(상하좌우에서 위아래까지)
 * 
 */
public class BJ_7569_토마토_김광연 {
	static int[] dx = {1, 0, -1, 0, 0, 0};
	static int[] dy = {0, 1, 0, -1, 0, 0};
	static int[] dz = {0, 0, 0, 0, 1, -1};	
	static int N, M, H;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); // 가로칸 수
		N = Integer.parseInt(st.nextToken()); // 세로칸 수
		H = Integer.parseInt(st.nextToken()); // 높이
		int[][][] board = new int[N][M][H]; // 토마토들
		int none = 0; // 토마토가 없는 빈칸
		int ripen = 0; // 익은 토마토
		Queue<int[]> que = new ArrayDeque<>();
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					board[i][j][h] = Integer.parseInt(st.nextToken());
					if (board[i][j][h] == -1) { // 토마토가 없는 곳이면
						none++;
					}
					if (board[i][j][h] == 1) { // 이미 익은 토마토면
						ripen++;
						que.offer(new int[] {i, j, h, 0}); // 좌표와 시간을 큐에 넣기
					}
				}
			}
		}
		
		if (ripen == (N*M*H - none)) { // 이미 최대로 토마토가 익었으면 0 출력
			System.out.println(0);
		} else if (ripen == 0){
			System.out.println(-1); // 익은 토마토가 아예 없으면 -1 출력(불가능)
		} else {
			int time = 0; // 다 익기까지 걸리는 시간 저장할 변수
			while (!que.isEmpty()) {
				int[] cur = que.poll();
				for (int k = 0; k < 6; k++) { // 상하좌우위아래 탐색
					int x = cur[0] + dx[k];
					int y = cur[1] + dy[k];
					int z = cur[2] + dz[k];
					
					if (!isBoudary(x, y, z) || board[x][y][z] == 1 || board[x][y][z] == -1) {
						// 범위에서 벗어나거나 이미 토마토가 익었거나 빈칸인 경우 패스
						continue;
					}
					board[x][y][z] = 1; // 토마토 익히기
					ripen++; // 익힌 토마토 개수 +1
					que.offer(new int[] {x, y, z, cur[3]+1}); // 다시 큐에 넣고
					time = cur[3] + 1; // 시간 재설정
				}
			}
			if (ripen == (N*M*H - none)) { // 익은 토마토 개수가 최대면 해당 시간 출력
				System.out.println(time);
			} else { // 안익은 토마토 있으면 -1 출력
				System.out.println(-1);
			}
		}
	}
	private static boolean isBoudary(int x, int y, int z) { // 범위에 안에 있는지 확인하는 메서드
		return x >= 0 && x < N && y >= 0 && y < M && z >= 0 && z < H;
	}
}
