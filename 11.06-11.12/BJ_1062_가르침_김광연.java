/**
 * 
 */
package algorithm1107;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * 
 * BJ_1062_가르침_김광연 
 * 메모리 13,068kb 시간 232ms
 * 아이디어
 * 백트래킹 문제.
 * 처음에는 읽을 문자를 조합으로 고르고 해당 문자열을 모두 읽을 수 있는 알파벳의 최소 개수를 구하면서 풀려고 함 => 시간 초과
 * 고민하다가 분류가 백트래킹으로 돼 있는 걸 보고 문자열이 아닌 알파벳을 기준으로 백트래킹 함.
 * 우선 모든 alpha라는 배열을 만들어 모든 알파벳의 사용 여부를 확인하고 무조건 읽어햐 하는 a, n, t, i, c는 true로 지정
 * index는 'a'를 빼 0부터 25까지로 나타냈고 0부터 시작해 DFS
 * 읽을 수 있는 알파벳 개수가 K개가 됐을 때 모든 문자열들을 읽어 몇개 읽을 수 있는지 구하고 이를 반복해 최대값 구함
 */
public class BJ_1062_가르침_김광연 {
	static int N, K;
	static int ans;
	static boolean[] alpha;
	static String[] str;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 문자열 개수
		K = Integer.parseInt(st.nextToken()); // 배울 알파벳 개수
		
		
		ans = 0; // 읽을 수 있는 알파벳의 최대값을 저장할 변수
		str = new String[N]; // 문자열 배열
		for (int i = 0; i < N; i ++) {
			String tmp = br.readLine();
			str[i] = tmp.substring(4, tmp.length()-4); // 앞에 4글자와 뒤에 4글자는 읽을 필요 없으므로 슬라이싱해서 저장
		}	
		
//		for (String s: str) {
//			System.out.println(s);
//		}
		if (K < 5) { // 최소 5개의 알파벳은 읽을 줄 알아햐 하므로 
			System.out.println(0);
		} else if (K == 26) { // 모든 알파벳을 다 읽을 수 있으므로ㅓ
			System.out.println(N);
		} else {
			reset(); // 알파벳 체크 배열 리셋
			DFS(0, 0);
			System.out.println(ans);
		}
	}

	private static void reset() { // 체크 배열 리셋
		alpha = new boolean[26];
		// t, i, c, a, n => 무조건 필요
		alpha[0] = true;
		alpha[2] = true;
		alpha[8] = true;
		alpha[13] = true;
		alpha[19] = true;
	}
	private static void DFS(int L, int start) {
		if (L == K-5) { 
			int cnt = 0; // 읽을 수 있는 문자열 개수
			for (int j = 0; j < N; j++) {
				int len  = str[j].length(); // 해당 문자열의 길이
				int flag = 0; // 문자열을 읽을 수 있는 지 확인할 변수
				for (int i = 0; i < len; i++) {
					if (!alpha[str[j].charAt(i)-'a']) { // 배우지 않은 알파벳이면
						flag = 1;
						break; // 탈출
					}
				}
				if (flag == 0) { // 배우지 않은 알파벳이 없으면
					cnt++; // 문자열 개수+1
				}
			}
			ans = Math.max(ans, cnt); // 최대값 저장
			return;
		}
		for (int i = start; i < 26; i++) { // a부터 DFS
			if (!alpha[i]) { // 해당 알파벳을 배우지 않았으면
				alpha[i] = true; // 배우고
				DFS(L+1, i+1); // 다음 알파벳 체킹
				alpha[i] = false; // 롤백
			}
		}
	} 

}
