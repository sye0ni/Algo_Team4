/**
 * 
 */
package algorithm1004;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 김광연
 * 
 * BJ_20437_문자열게임2_김광연
 * 메모리 30,032 kb 시간 292 ms
 * 아이디어
 * 인접 리스트 활용 
 * 우선 소문자 알파벳 개수만큼의 크기를 갖는 인접 리스트 생성 => 문자열에서 해당 알파벳이 나오면 그 인덱스를 저장 용도
 * 알파벳의 개수를 세기 위한 1차원 배열도 생성 => K개인지 확인할 용도
 * 유의할 점은 K가 1일 때만 생각해주면 될듯
 */
public class BJ_20437_문자열게임2_김광연 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String W = br.readLine(); // 주어진 문자열
			int K = Integer.parseInt(br.readLine()); // 포함할 어떤 문자의 개수
			
			int l = W.length();
			// 인접리스트
			List<Integer>[] list = new ArrayList[26];
			for (int i = 0; i < 26; i++) {
				list[i] = new ArrayList<>();
			}

			int[] cnts = new int[26]; // 개수 저장할 배열
			
			int tmp = 0;
			int ansMin = 2147000000;
			int ansMax = 0;
			if (K == 1) {
				sb.append(1).append(" ").append(1).append("\n");
			} else {
				for (int i = 0; i < l; i++) {
					tmp = W.charAt(i) - 'a'; // 'a':0, 'b':1, ~, 'z':25
					list[tmp].add(i); // 해당 알파벳의 인덱스 저장
					cnts[tmp]++; // 개수 1 증가
					if (cnts[tmp] == K) { // 해당 알파벳의 개수가 K개일 경우	
						ansMin = Math.min(ansMin, list[tmp].get(K-1) - list[tmp].get(0)+1); // 첫 인덱스와 끝 인덱스의 길이와 최소값 비교
						ansMax = Math.max(ansMax, list[tmp].get(K-1) - list[tmp].get(0)+1); // 첫 인덱스와 끝 인덱스의 길이와 최대값 비교
						// 해당 문자가 K개보다 많아질 경우 다음 문자열의 길이를 확인하기 위해
						cnts[tmp] = K-1; // 개수 1 감소
						list[tmp].remove(0); // 시작 인덱스 1 증가	
					}
				}				
				if (ansMax == 0) { // 최대값이 초기 설정값이면 -1 출력
					sb.append(-1).append("\n");
				} else {
					sb.append(ansMin).append(" ").append(ansMax).append("\n");
				}
			}
		}
		System.out.println(sb);
	}
}
