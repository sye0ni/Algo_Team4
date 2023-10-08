package algorithm1008;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * 
 * BJ_2589_보물섬_김광연
 * 메모리 217,236 kb 시간 432 ms
 * 아이디어
 * BFS + 구현 문제
 * 최대한 BFS를 중복해서 하지 않기 위해 처음에 연결된 육지들을 구할 때 끝점으로 가능한 후보군들을 미리 저장해 두고 해당 좌표들만 BFS해서 구하려고 했음
 * 끝점으로 가능한 후보군으로는 처음에는 BFS 시작한 좌표에서 가장 거리가 먼 좌표들로 저장하려고 했지만 뭔가 있을 것만 같아서 다른 방법 생각함
 * 만약 한 좌표에서 상하좌우로 갈 때 3방향을 갈 수 없고 단 한 방향만 육지와 연결됐는데 그 방향도 이미 방문한 육지인 경우, 즉 육지와 연결된 곳이 하나고 더이상 갈 곳이 없는 경우를 후보군으로 넣음
 * 만약 위와 같은 점이 없는 경우는 육지가 순환이 가능한 경우인데 어느 점에서 시작하느냐에 따라 최대 거리가 달라지므로 이때는 모든 좌표에서 BFS를 실행해 값을 비교함
 * 반례 찾느라 되게 고심하면서 풀었는데 시간이 생각보다 안줄어서 너무 슬펐음. 메모리는 어떻게 줄일지 생각해봐야할듯?
 */
public class BJ_2589_보물섬_김광연 {

	static int s, g;
	static char[][] board;
	static boolean[][] visit;
	static Queue<int[]> qu;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] check;
	static int ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		s = Integer.parseInt(st.nextToken()); // 세로 길이
		g = Integer.parseInt(st.nextToken()); // 가로 길이
		
		board = new char[s][g];
		for (int i = 0; i < s; i ++) {
			String tmp = br.readLine();
			board[i] = tmp.toCharArray();		
		}
//		for (int i = 0; i < s; i++) {
//			System.out.println(Arrays.toString(board[i]));
//		}
		visit = new boolean[s][g];
		qu = new ArrayDeque<>();
		ans = 0;
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < g; j++) {
				if (board[i][j] == 'L' && !visit[i][j]) { // 육지고 방문한 적 없으면
					qu.offer(new int[] {i, j, 0}); // 좌표와 시작점과의 거리를 큐에 넣기
					visit[i][j] = true; // 방문 체크
					makeLand(); // 연결된 육지 찾기
				}
			}
		}
		System.out.println(ans);
		
	}
	private static void makeLand() {
		List<int[]> list = new ArrayList<>(); // 가장 멀리 있을 후보군들 저장할 리스트
		List<int[]> list2 = new ArrayList<>();
		int cnt = 0;
		int flag = 0;
		while (!qu.isEmpty()) {
			int[] tmp = qu.poll();
			cnt = 0;
			flag = 0;
			for (int k = 0; k < 4; k++) { // 상하좌우 탐색
				int xx = tmp[0] + dx[k];
				int yy = tmp[1] + dy[k];
				
				if (!isBoundary(xx, yy) || board[xx][yy] == 'W') { // 범위에서 벗어나거나 물인 경우 -> cnt 1 증가하고 패스
					cnt++; 
					continue;
				}
				if (visit[xx][yy]) { // 이미 방문한 곳인 경우 -> flag 1 증가하고 패스
					flag = 1;
					continue;
				}
				qu.offer(new int[] {xx, yy, tmp[2]+1}); // 큐에 넣기
				visit[xx][yy] = true; // 방문 체크				
			}
			if (cnt == 3 && flag == 1) { // 상하좌우 중 오직 한 곳만 육지와 연결돼 있고 이미 방문했던 곳인 경우(끝점인 경우)
				list.add(new int[] {tmp[0], tmp[1]}); // list에 넣기
			} else {
				list2.add(new int[] {tmp[0], tmp[1]}); // 아니면 list2에 넣기
			}
		}
		
		// 최대거리 찾기
		if (list.isEmpty()) {
			for (int[] t: list2) { // 연결된 육지들 모두 확인
				qu.clear();
				check = new boolean[s][g]; // 방문 체크 초기화
				qu.add(new int[] {t[0], t[1], 0}); // 시작점 큐에 넣기
				check[t[0]][t[1]] = true; // 방문체크
				ans = Math.max(ans, getDistance()); // 최대값 찾기
			}
		} else {
			for (int[] t: list) { // 끝점만 확인
				qu.clear();
				check = new boolean[s][g]; // 방문 체크 초기화
				qu.add(new int[] {t[0], t[1], 0}); // 큐에 시작점 넣기
				check[t[0]][t[1]] = true; // 방문 체크
				ans = Math.max(ans, getDistance()); // 최대값 찾기
			}
		}	
	}
	private static int getDistance() { // 최대 거리 찾기
		while (!qu.isEmpty()) {
			int[] tmp = qu.poll();
			for (int k = 0; k < 4; k++) { // 상하좌우 탐색
				int xx = tmp[0] + dx[k];
				int yy = tmp[1] + dy[k];
				
				if (!isBoundary(xx, yy) || check[xx][yy] || board[xx][yy] == 'W') {
					continue;
				}
				qu.offer(new int[] {xx, yy, tmp[2]+1}); 
				check[xx][yy] = true;			
			}
			if (qu.isEmpty()) { // 큐에 더이상 아무것도 없으면 마지막으로 뺀 값의 거리를 반환
				return tmp[2];
			}
		}
		return 0;
	}
	private static boolean isBoundary(int x, int y) { // 범위 안에 있는지 확인하는 메서드
		return x >= 0 && x < s && y >= 0 && y < g;
	}
}
