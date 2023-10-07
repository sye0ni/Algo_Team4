/**
 * 
 */
package algorithm1007;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * BJ_14501_퇴사_김광연
 * 메모리 11,604 kb 시간 76 ms
 * 아이디어
 * DFS 문제.
 * 해당 날짜의 상담을 하거나 안하거나인데 했을 때의 끝나는 날이 N보다 크면 다음날로 그냥 넘어가도록 구현
 * 최대값을 구하기 위해 더이상 상담을 할 수 없을 때의 금액을 ans와 비교 후 큰 값을 ans에 저장
 */
public class BJ_14501_퇴사_김광연 {

	static int[] T;
	static int[] P;
	static int ans, N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		T = new int[N];
		P = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken()); // 해당 상담에 걸리는 일수 저장
			P[i] = Integer.parseInt(st.nextToken()); // 해당 상담으로 얻는 금액 저장
		}
		
		ans = 0; // 최대 수익을 구하기 위한 변수
		DFS(0, 0);
		System.out.println(ans);
		
	}

	private static void DFS(int cnt, int sum) {
		if (cnt == N) { // 총 일수와 같아지면
			ans = Math.max(ans, sum);
			return;
		}
		if (cnt + T[cnt] < N+1) { // 해당 날짜의 상담을 했을 때 끝나는 날이 N보다 크면 그 상담은 못하므로 
			DFS(cnt+T[cnt], sum+P[cnt]); // 해당 날 상담 진행 => 그날 상담이 끝난 후를 시작점으로 
		}
		DFS(cnt+1, sum); // 다음날로
	}

}
