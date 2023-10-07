/**
 * 
 */
package algorithm1005;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * BJ_1976_여행가자_김광연
 * 메모리 18,668 kb 시간 232 ms
 * 아이디어
 * BFS 문제 => 여행지 경유가 가능 + 경유로 가는 곳은 중복 가능
 * 그냥 목적지가 바뀔 때마다 방문 배열만 초기화 해주면 됨
 * 유의할 점은 출발지와 목적지가 같은 경우까지 생각해주기
 */
public class BJ_1976_여행가자_김광연 {

	static int[][] board;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 도시 수
		int M = Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시 수
		board = new int[N][N];
		for (int i = 0; i < N; i++) { // 도시 연결 정보 받기
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] plan = new int[M]; // 여행 계획 정보 받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		int len = plan.length; // 여행 계획 길이
		int flag = 0; // 목적지에 다 갔는지 확인할 변수 -> 그대로면 성공 / 1로 바뀌면 실패
		for (int i = 0; i < len-1; i++) {
			if (board[plan[i]][plan[i+1]] == 1 || plan[i] == plan[i+1]) { // 출발지와 목적지가 같거나 경유할 필요 없이 한번에 갈 수 있으면 패스
				continue;
			}
			if (!isAlright(plan[i], plan[i+1])) { // 경유해도 못가면 
				flag = 1; // 플래그 1로
				break; // 반복문 탈출
			}
		}
		if (flag == 1) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
		}
		
	}
	static boolean isAlright(int start, int end) { // 경유해서 갈 수 있는지 확인하는 메서드
		 boolean[] visit = new boolean[N]; // 방문배열 초기화
		 Queue<Integer> qu = new ArrayDeque<>(); // 이동할 좌표 넣을 큐
		 qu.offer(start);
		 visit[start] = true; // 방문 체크
		 while (!qu.isEmpty()) {
			 int tmp = qu.poll();
			 for (int i = 0; i < N; i++) {
				 if (board[tmp][i] == 0 || visit[i]) { // 갈 수 없거나 방문한 적 있으면 패스
					 continue;
				 }
				 if (i == end) { // 목적지에 도착하면
					 return true; // true 반환
				 }
				 visit[i] = true; // 목적지가 아니면 방문 체크
				 qu.offer(i); // 큐에 넣기
			 }
		 }
		 return false; // 큐에 빌 때까지 목적지에 도착하지 못하면 false 반환
	}

}
