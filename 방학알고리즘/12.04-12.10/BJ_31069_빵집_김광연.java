/**
 * 
 */
package 그래프이론;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * BJ_31069_빵집_김광연
 * 메모리 36928 kb 시간 308 ms
 * 아이디어
 * 그리디 + 그래프 구현
 * 에전에 못풀고 넘어갔던 문제. 그때는 그리디를 생각못해서 시간초과로 못풀었음
 * 우선 무조건 대각선 위부터 탐색 + 한번 탐색한 좌표는 건물로 방문체크해서 중복 체크 방지 + 마지막 열 도착하면 카운트 후 true 반환
 * 탐색할 때 성공, 실패 여부와 상관없이 방문체크릃 하는데 해당 좌표로 파이프 연결에 성공한 경우는 이미 파이프가 있으므로 못가고 실패한 경우는 굳이 해볼 필요 없으므로 성공, 실패 여부 상관없이 방문 체크
 *
 */
public class BJ_31069_빵집_김광연 {
	static int[] dx = {-1, 0, 1}; // 오른쪽 대각위, 오른쪽, 오른쪽 대각아래 순서로
	static int R, C;
	static char[][] board;
	static int cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행 수
		C = Integer.parseInt(st.nextToken()); // 열 수
		
		board = new char[R][C]; 
		for (int i = 0; i < R; i++) { // 입력 받기
			String tmp = br.readLine();
			for (int j = 0; j  < C; j++) {
				board[i][j] = tmp.charAt(j);
			}
		}
		
		cnt = 0;
		for (int i = 0; i < R; i++) { // 첫번째 열을 시작점으로
			board[i][0] = 'x'; // 방문 표시
			DFS(i, 0); // 시작점에서 DFS 탐색
		}
		System.out.println(cnt);
	}

	private static boolean DFS(int i, int j) {
		if (j == C-1) { // 마지막 열에 도착하면 카운트 후 true 반환
			cnt++;
			return true;
		}
		for (int k = 0; k < 3; k++) { // 다음 좌표로 이동 (오른쪽 위 대각선 -> 오른쪽 -> 오른쪽 아래 대각선 순으로)
			int x = i + dx[k];
			int y = j + 1;
			
			if (!isBoundary(x, y) || board[x][y] == 'x') { // 범위에서 벗어나거나 건물로 막힌 경우 패스
				continue;
			}
			board[x][y] = 'x'; // 건물로 방문 체크 
			if (DFS(x, y)) { // 해당 좌표에서 끝까지 갈 수 있으면 true 반환 후 더이상 탐색 X
				return true;
			}
		}
		return false;
	}

	private static boolean isBoundary(int x, int y) { // 범위 안에 드는지 확인하는 메서드
		return x >= 0 && x < R && y >= 0 && y < C;
	}

}
