/**
 * 
 */
package algorithm1005;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * SW_5656_벽돌깨기_김광연
 * 구현하려고 했는데 실패했습니다
 * 조원들에게 좀더 물어보고 다시 풀어서 내도록 하겠습니다. 죄송합니다.
 */
public class SW_5656_벽돌깨기_김광연 {
	static int N, ans, W, H;
	static int[] res;
	static int[][] board;
	static int[][] tmp;
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 떨어뜨릴 구슬 개수
			W = Integer.parseInt(st.nextToken()); // 가로
			H = Integer.parseInt(st.nextToken()); // 세로

			board = new int[H][W];
			for (int i = 0; i < H; i++) { // 벽돌 정보 입력 받기
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			res = new int[N];
			ans = 2147000000;

			// 시작열 정하기
			for (int i = 0; i < W; i++) {
				res[0] = i;
				DFS(1);
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);

	}

	private static void DFS(int cnt) {
		if (cnt == N) {
			tmp = new int[H][W];
			for (int i = 0; i < H; i++) {
				tmp[i] = board[i].clone();
			}
			
			destroyBricks(res);
			ans = Math.min(ans, getBricks());
			return;
		}
		for (int i = 0; i < W; i++) {
			res[cnt] = i;
			DFS(cnt + 1);
		}
	}

	private static int getBricks() {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (tmp[i][j] != 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void destroyBricks(int[] r) { //
		for (int x : r) { // 해당 열에서 구슬 떨어뜨림
			int row = 0;
			int sx = 0;
			int sy = 0;
			while (row < H) {
				visit = new boolean[H][W];
				if (tmp[row][x] != 0) { // 벽돌과 만나면
					tmp[row][x] = 0;
					sx = row;
					sy = x;
					break;
				}
				row++;
			}
			splashBricks(sx, sy);
			locateBricks();
			
		}
	}

	private static void splashBricks(int row, int col) {
		int cnt = board[row][col] - 1; // 벽돌 깰 횟수
		visit[row][col] = true;
		// 위로 벽깨기
		int up = row; // 가장 상단 사이에 있는 벽돌 개수
		if (up > 0 && cnt > 0) {
			if (up < cnt) {
				for (int i = row - 1; i >= 0; i--) {
					tmp[i][col] = 0;
				}
			} else if (up == cnt) {
				for (int i = row - 1; i >= 0; i--) {
					tmp[i][col] = 0;
				}
				if (!visit[0][col]) {
					splashBricks(0, col);
				}
			} else {
				for (int i = row - 1; i > row - 1 - cnt; i--) {
					tmp[i][col] = 0;
				}
				if (!visit[row-cnt][col]) {
					splashBricks(row - cnt, col);
				}
			}
		}
		// 아래로 벽깨기
		int down = H - row - 1; // 가장 하단 사이에 있는 벽돌 개수
		if (down > 0 && cnt > 0) {
			if (down < cnt) {
				for (int i = down + 1; i < H; i++) {
					tmp[i][col] = 0;
				}
			} else if (down == cnt) {
				for (int i = row + 1; i < H; i++) {
					tmp[i][col] = 0;
				}
				if (!visit[H-1][col]) {
					splashBricks(H - 1, col);
				}
			} else {
				for (int i = row + 1; i < row + 1 + cnt; i++) {
					tmp[i][col] = 0;
				}
				if (!visit[row+cnt][col]) {
					splashBricks(row + cnt, col);
				}
			}
		}
		// 왼쪽으로 벽깨기
		int left = col; // 가장 좌측 사이에 있는 벽돌 개수
		if (left > 0 && cnt > 0) {
			if (left < cnt) {
				for (int i = col - 1; i >= 0; i--) {
					tmp[row][i] = 0;
				}
			} else if (left == cnt) {
				for (int i = col - 1; i >= 0; i--) {
					tmp[row][i] = 0;
				}
				if (!visit[row][0]) {
					splashBricks(row, 0);
				}
			} else {
				for (int i = col - 1; i > col - 1 - cnt; i--) {
					tmp[row][i] = 0;
				}
				if (!visit[row][col-cnt]) {
					splashBricks(row, col - cnt);
				}
			}
		}
		// 오른쪽으로 벽깨기
		int right = W - col - 1; // 가장 하단 사이에 있는 벽돌 개수
		if (right > 0 && cnt > 0) {
			if (right < cnt) {
				for (int i = col + 1; i < W; i++) {
					tmp[row][i] = 0;
				}
			} else if (right == cnt) {
				for (int i = col + 1; i < W; i++) {
					tmp[row][i] = 0;
				}
				if (!visit[row][W-1]) {
					splashBricks(row, W - 1);
				}
			} else {
				for (int i = col + 1; i < col + 1 + cnt; i++) {
					tmp[i][col] = 0;
				}
				if (!visit[row][col+cnt]) {
					splashBricks(row, col + cnt);
				}
			}
		}
	}

	private static void locateBricks() {
		// 공중에 떠 있는 벽돌 떨어뜨려주기
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < W; i++) { // 첫번째 열부터 벽돌 떨구기
			// 0이 아닌 값만 스택에 쌓기
			for (int j = 0; j < H; j++) {
				if (tmp[j][i] != 0) {
					stack.add(tmp[j][i]);
					tmp[j][i] = 0;
				}
			}
			// 스택에 쌓인 값을 다시 바닥부터 쌓기
			int idx = H - 1;
			while (!stack.isEmpty()) {
				tmp[idx--][i] = stack.pop();
			}
		}
	}

}
