/**
 * 
 */
package algorithm1009;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * SW_2112_보호필름_김광연
 * 메모리 91,312 kb 실행시간 480 ms
 * 아이디어
 * 조합 + DFS 활용한 구현 문제
 * D, W, K의 범위가 크지는 않지만 문제 그대로 완전 구현하면 시간 초과가 날 거라고 생각해서 최대한 불필요한 반복을 줄이려고 함 => 한번에 바꾸고 체크하지 않고 부분만 바꾸고 맞는지 확인해서 틀리면 바로 중단
 * 우선 조합으로 어느 행의 값을 바꿀지 정함. 이 때 조합의 size는 작을 수록 좋으므로 1부터 오름차순으로 찾음
 * 어느 행을 바꿀지 정한 후 부분집합 구하는 방법으로 0 또는 1을 갖는 배열을 만든 후 정한 행에 해당 값을 넣어 배열을 바꿔준 후 검사함 -> 이때도 역시 하나의 열만 우선 체크하고 검사 실패하면 바로 중단하고 다음으로 넘어감
 */
public class SW_2112_보호필름_김광연 {

	static int D, W, ans;
	static int[][] board;
	static int K;
	static int[] res;
	static int[][] tmp;
	static int[] sub;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" "); // 출력 양식 저장
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); // 세로
			W = Integer.parseInt(st.nextToken()); // 가로
			K = Integer.parseInt(st.nextToken()); // 검사용 수

			board = new int[D][W];
			tmp = new int[D][W]; // 원래 배열 저장할 변수
			for (int i = 0; i < D; i++) { // 필름 정보 입력 받아 저장하기
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					tmp[i][j] = board[i][j];
				}
			}

			// 열 검사하기
			int flag = 0; // 모든 열이 검사를 통과했는지 확인할 변수
			for (int j = 0; j < W; j++) {
				if (!checkCol(j)) { // 검사 통과 못하면 
					flag = 1; // flag를 1로 
					break; // 탈출
				}
			}
			if (flag == 0 || K == 1) { // 모든 열이 검사 통과하거나 검사할 문자수가 1인 경우 => 약물 투입 없이 바로 테스트 통과
				sb.append(0).append("\n");
				continue;
			}

			ans = 0;
			for (int i = 1; i <= K; i++) { // 바꿀 행 조합하기
				res = new int[i]; // 바꿇 행 번호 저장할 배열
				combi(0, 0, i); 
				if (ans != 0) { // 모든 열이 검사를 통과한 경우 최소값을 구하는 것이므로 반복문 탈출
					break;
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void combi(int cnt, int start, int size) { // 조합하기
		if (cnt == size) { // 조합할 size와 개수가 같아지면
			int len = res.length;
			sub = new int[len];
			dfs(0, len); // 부분집합 만들기

			// 배열 다시 원상복구
			for (int i = 0; i < D; i++) {
				board[i] = tmp[i].clone();
			}
			return;
		}
		for (int i = start; i < D; i++) { // 조합 구현
			res[cnt] = i;
			combi(cnt + 1, i + 1, size);
		}
	}

	private static void dfs(int cnt, int size) { // 부분집합 만들기 => dfs로
		if (ans != 0) { // ans가 0이 아니면 => 이미 검사를 통과한 것이므로 더이상 확인 X
			return;
		}
		if (cnt == size) { 
			int flag = 0; // 검사에 통과했는지 확인할 변수
			for (int i = 0; i < W; i++) { // 반복문으로 각 열들 검사하기
				changeAtt(i); // 해당 열의 특성 바꾸기 (정한 행과 특성으로)
				if (!checkCol(i)) { // 검사를 통과 못하면 나머지 열들은 볼필요 없므으로 반복문 탈출
					flag = 1; // 검사 통과 못했다는 의미로 flag는 1로 
					break; // 탈출
				}
			}
			if (flag == 0) { // 모든 열에서 검사 통과하면
				ans = size; // ans를 size로 바꿔주기
			}
//			// 배열 다시 원상복구
//			for (int i = 0; i < D; i++) {
//				board[i] = tmp[i].clone();
//			}
			return;
		}
		sub[cnt] = 0; // 0을 쓴 경우
		dfs(cnt + 1, size);
		sub[cnt] = 1; // 1을 쓴 경우
		dfs(cnt + 1, size);
	}

	private static boolean checkCol(int col) { // 해당 열 검사하기
		int cnt = 1;
		for (int i = 0; i < D - 1; i++) {
			if (board[i][col] == board[i + 1][col]) { // 현재 행과 다음 행의 수가 같으면 
				cnt++; // cnt 1 증가
				if (cnt >= K) { // cnt가 검사 조건인 K 이상일 때
					return true; // true 반환 => 그 이후는 확인할 필요 X
				}
			} else { // 다르면 다시 cnt를 1로 초기화
				cnt = 1;
			}
		}
		return false; // 다 돌 때가지 cnt가 K보다 작았으므로 false 반환
	}

	private static void changeAtt(int col) { // 특성 바꾸기
		int len = sub.length;
		for (int i = 0; i < len; i++) { 
			board[res[i]][col] = sub[i]; // 해당 열에서 조합으로 정한 행의 특성을 부분집합으로 정한 값으로 바꿔주기
		}
	}
}
