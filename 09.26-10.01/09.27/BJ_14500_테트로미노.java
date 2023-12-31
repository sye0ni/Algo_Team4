import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 광연이가 사방탐색을 4번 돌면 무조건 테트로미노 중 하나의 모양이 된다고 알려줌
 * dfs로 사방탐색을 돌면 4번째 칸부터 3번째 칸, 2번째 칸 방향을 바꿔가면서 모든 테트로미노 탐색 가능
 * 문제는 ㅗ 모양이 4, 3, 2번째 칸을 움직이면서 만들지 못함
 * ㅗ 모양을 만들기 위해서 2번째 칸 도달 시 따로 dfs를 돌려서 ㅗ 모양의 튀어나온 부분을 움직여가면서 ㅗ 모양도 탐색 가능
 * 
 * 30908kb	712ms
 * @author 김무준
 *
 */
public class BJ_14500_테트로미노 {

	static int n, m, res;
	static int[][] arr;
	static boolean[][] visit;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static boolean valid(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= m) {
			return false;
		}
		return true;
	}

	static void dfs(int r, int c, int cnt, int sum) {
		// 4개 칸을 dfs 했으면 값을 모두 더해서 최대값 저장
		if (cnt == 4) {
			res = Math.max(res, sum);
			return;
		}

		// 사방탐색
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 배열을 벗어나지 않으면
			if (valid(nr, nc)) {
				// 다음 칸을 방문한 적 없으면
				if (!visit[nr][nc]) {
					// ㅗ 모양을 찾기 위해 현재 좌표로 dfs 따로 돌림
					if (cnt == 2) {
						visit[nr][nc] = true;
						dfs(r, c, cnt + 1, sum + arr[nr][nc]);
						visit[nr][nc] = false;
					}
					// 다음 좌표 dfs
					visit[nr][nc] = true;
					dfs(nr, nc, cnt+1, sum+arr[nr][nc]);
					visit[nr][nc] = false;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visit = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 배열의 모든 좌표에 대해 dfs
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int sum = arr[i][j];
				visit[i][j] = true;
				dfs(i, j, 1, sum);
				visit[i][j] = false;
			}
		}
		System.out.println(res);
	}
}
