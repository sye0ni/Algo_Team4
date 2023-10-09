package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * BJ_14501_퇴사
 * 11868kb	76ms
 *
 * dfs이용
 * 2일부터 시작하는 경우에 전날이 누적되야 하는데 그 부분을 잘 모르겠어서..참고함,,,ㅠㅜ
 * 전에 풀어봤을 때도 비슷한 문제가 있었는데,,,이번에도 해결이 어려웠음,,,
 * 다음에 다시 풀어보는 걸로,,, dp는 생각도 안해봤는데 dp로 한번 풀어보겠음,,,,
 *
 */

public class BJ14501 {
	
	static int T, max, sum, time, schedule[][], list[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		schedule = new int[T][2];
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<2; j++) {
				schedule[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		start(0, 0);
		
		System.out.println(max);

	}

	private static void start(int st, int sum) {
		
		if(st >= T) { // 상담중단
			max = Math.max(max, sum);
			return;
		}
		
		if(st + schedule[st][0]<=T) { // 상담이 더 가능하면 다음 상담 진행
			start(st + schedule[st][0], sum + schedule[st][1]);
		}else { // 상담을 더 할 수 없음
			start(st + schedule[st][0], sum);
		}
		
		
		// 다음날로
		start(st+1, sum);
	}
		

}
