package algorithm0926;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * BJ_14500_테트로미노_김광연
 * 메모리 36976 kb 시간 704 ms
 * 아이디어
 * DFS 활용 및 구현
 * 주어진 폴리오미노 중 하나를 사용해서 최대값을 구하는 문제. 이 때, 폴리오미노를 회전하거나 대칭시킬 수 있으므로 결국 한 시작점에서 4칸 움직이는 모든 경우와 같음
 * 따라서 이러한 경우들을 DFS를 활용해 최대값을 구함
 * 이 때, ㅗ, ㅜ, ㅓ, ㅏ 이렇게 4가지 모양은 DFS로 나올 수 없기 때문에 직접 구현해서 구함 => 세로와 가로일 때로 나눠 가운데 사각형의 왼쪽 또는 오른쪽에 값을 더하는 방식으로 구함
 * 방문체크는 한 시작점을 고르고 DFS할 때 쓰기 위한 visit배열과 시작점을 바꿔 가면서 다른 시작점에서 DFS할 때 끝점이 이미 시작점으로 썼던 곳일 경우를 체크하기 위해 check배열을 활용함
 * 유의할 점으로는 처음에 visit 배열을 각 시작점마다 초기화하기 위해 for문 안에 넣었었는데 이거 때문에 시간초과 남 
 * => DFS 특성상 어짜피 시작점으로 돌아오니깐 방문 체크도 다시 false 값으로 돌아오므로 매번 모든 점을 초기화할 필요 없이 시작점만 초기화 해주면 되므로 그렇게 해결
 */
public class BJ_14500_테트로미노_김광연 {

	static int[] dx = {1, 0, -1, 0}; // 상하좌우 확인할 배열
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] check; // 시작점과 끝점 확인할 배열
	static int[][] board; // 주어진 배열값
	static int ans, res; // 최대값 비교할 변수들
	static int N, M; // 가로, 세로 크기
	static boolean[][] visit; // 하나의 DFS 안에서 방문 체크할 배열
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visit = new boolean[N][M]; 
		check = new boolean[N][M];
		ans = 0;
		res = 0;
		for (int i = 0; i < N; i++) { // (0, 0)부터 시작점으로 해 DFS
			for (int j = 0; j < M; j++) {
				visit[i][j] = true; // 시작점 방문 체크
				dfs(1, i, j, board[i][j]); // 시작점, 크기, 합을 매개변수로 받고 dfs
				visit[i][j] = false; // 시작점 방문 체크 초기화
				ans = Math.max(ans, res);
				check[i][j] = true; // 해당 시작점 방문 체크 (나중에 끝점으로 쓰게 될 경우 확인하기 위해)
			}
		}
		
		// ㅓ, ㅏ 모형 최대값 찾기
		int sum = 0;
		int r = 0;

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N-2; j++) {
				sum = board[j][i] + board[j+1][i] + board[j+2][i];
				if (i-1 < 0) { // 범위상 왼쪽으로 붙일 수 없을 때 
					r = sum + board[j+1][i+1];
				} else if (i+1 >= M) { // 범위상 오른쪽으로 붙일 수 없을 때
					r = sum + board[j+1][i-1];
				} else { // 둘다 붙일 수 있을 때는 둘 중에 더 큰 값으로
					r = Math.max(sum + board[j+1][i+1], sum + board[j+1][i-1]);
				}
				ans = Math.max(ans, r);
			}
		}
		// ㅗ, ㅜ 모형 최대값 찾기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M-2; j++) {
				sum = board[i][j] + board[i][j+1] + board[i][j+2];
				if (i-1 < 0) { // 범위상 위쪽으로 붙일 수 없을 때
					r = sum + board[i+1][j+1];
				} else if (i+1 >= N) { // 범위상 아래쪽으로 붙일 수 없을 때
					r = sum + board[i-1][j+1];
				} else { // 둘다 붙일 수 있을 때는 둘 중에 더 큰 값으로
					r = Math.max(sum + board[i+1][j+1], sum + board[i-1][j+1]);
				}
				ans = Math.max(ans, r);
			}
		}	
		System.out.println(ans);
	}
	private static void dfs(int L, int x, int y, int sum) { // dfs
		if (L == 4) { // 4번 움직였을 때 그 합을 res와 비교 => 더 큰 값을 res에 저장
			res = Math.max(res, sum);
			return;
		}
		for (int k = 0; k < 4; k++) { // 상하좌우 이동
			int xx = x + dx[k];
			int yy = y + dy[k];
			if (!isBoundary(xx, yy) || visit[xx][yy]) { // 범위에서 벗어나거나 이미 방문했으면 패스
				continue;
			}
			if (L == 3 && check[xx][yy]) { // 3번 움직인 상태에서 끝 지점이 이미 간 곳이면
				continue;
			} else { 
				visit[xx][yy] = true;
				dfs(L+1, xx, yy, sum+board[xx][yy]);
				visit[xx][yy] = false;
			}
		}
	}
	private static boolean isBoundary(int x, int y) { // 범위 안에 있는지 확인하는 메서드
		return x >= 0 && x < N && y >= 0 && y < M;
	}
	

}
