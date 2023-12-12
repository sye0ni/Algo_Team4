/**
 * 
 */
package 문자열;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 김광연
 * BJ_2179_비슷한단어_김광연
 * 메모리 15824 kb 시간 200 ms
 * 아이디어
 * 완전 탐색
 * 문자열 길이를 비교해서 완텀하는 방식으로 구현. 입력값의 범위가 그렇게 크지 않아서 완탐 가능한듯
 * 다른 풀이 보니깐 map 활용해서 정렬하는 식으로도 풀 수 있는 거 같아서 하루종일 해봤는데 도저히 반례 못찾겠음
 */
public class BJ_2179_비슷한단어_김광연 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 문자열의 개수
		String[] words = new String[N]; // 문자열들을 담을 배열
		
		for (int i = 0; i < N; i++) { // 입력 저장
			words[i] = br.readLine();
		}
		
		int max = -1;
		String S = "";
		String T = "";
		for (int i = 0; i < N; i++) {
			if (words[i].length() < max) { // 해당 문자열의 길이가 공통 접두사의 최대값보다 작으면 패스
				continue;
			}
			for (int j = i+1; j < N; j++) {
				if (words[j].length() < max) { // 해당 문자열의 길이가 공통 접두사의 최대값보다 작으면 패스
					continue;
				}
				if (prefix(words[i], words[j]) > max) { // 두문자열의 공통 접두사 길이가 더 크면
					max = prefix(words[i], words[j]); // max, S, T 값 update
					S = words[i]; 
					T = words[j];
				}
			}
		}
		
		System.out.println(S);
		System.out.println(T);
		
	}
	
	static int prefix(String a, String b) { // 두 문자열의 공통 접두사 길이 구하는 메서드
		int lenA = a.length(); // 문자열 a의 길이
		int lenB = b.length(); // 문자열 b의 길이
		int cnt = 0; // 공통 접두사 길이 변수
		
		for (int i = 0; i < Math.min(lenA, lenB); i++) { // 두 문자열 중 더 길이가 짧은 길이까지 비교
			if (a.charAt(i) != b.charAt(i)) { // 해당 위치의 문자가 다르면 탈출
				break;
			}
			cnt++; // 같으면 cnt 1 증가
		}
		return cnt;
	}
}


//Map<String, Integer> words = new HashMap<>();
//
//for (int i = 0; i < N; i++) { // 입력 저장
//	words.put(br.readLine(), i);
//}
//List<String> keySet = new ArrayList<>(words.keySet());
//Collections.sort(keySet);
//System.out.println(keySet.toString());
//int max = -1;
//String ans = "";
//String S = "";
//String T = "";
//
//List<Integer> pre = new ArrayList<>();
//
//for (int i = 0; i < N-1; i++) {
//	if (prefix(keySet.get(i), keySet.get(i+1)).length() > max) { // 두문자열의 공통 접두사 길이가 더 크면
//		max = prefix(keySet.get(i), keySet.get(i+1)).length(); // max, S, T 값 update
//		ans = prefix(keySet.get(i), keySet.get(i+1));
//		if (words.get(keySet.get(i)) > words.get(keySet.get(i+1))) {
//			S = keySet.get(i+1);
//			T = keySet.get(i);
//		} else {
//			S = keySet.get(i);
//			T = keySet.get(i+1);
//		}
//	} else if (prefix(keySet.get(i), keySet.get(i+1)).length() == max) { // 두 문자열의 공통 접두사 길이가 같으면
//		if (!prefix(keySet.get(i), keySet.get(i+1)).equals(ans)) { // 공통 접두사가 다른 경우
//			if (words.get(S) < Math.min(words.get(keySet.get(i)), words.get(keySet.get(i+1)))) { // S의 인덱스가 더 작으면 그대로
//				continue;
//			} else if (words.get(S) > Math.min(words.get(keySet.get(i)), words.get(keySet.get(i+1)))) { // S의 인덱스가 더 크면 새로운 문자열로 교체
//				if (words.get(keySet.get(i)) > words.get(keySet.get(i+1))) { // 
//					S = keySet.get(i+1);
//					T = keySet.get(i);
//				} else {
//					S = keySet.get(i);
//					T = keySet.get(i+1);
//				}
//			}
//		} else { // 같은 경우
//			pre.add(words.get(S));
//			pre.add(words.get(T));
//			pre.add(words.get(keySet.get(i)));
//			pre.add(words.get(keySet.get(i+1)));
//			
//			Collections.sort(pre);
//			String[] cand = {S, T, keySet.get(i), keySet.get(i+1)};
//			for (int k = 0; k < 4; k++) {
//				if (pre.get(0) == words.get(cand[k])) {
//					S = cand[k];
//				}
//				if (pre.get(1) == words.get(cand[k])) {
//					T = cand[k];
//				}
//			}
//			
////			int sIdx = Math.min(Math.min(words.get(S), words.get(keySet.get(i))), words.get(keySet.get(i+1)));
////			if (sIdx == words.get(S)) {
////				int tIdx = Math.min(Math.min(words.get(T), words.get(keySet.get(i))), words.get(keySet.get(i+1))); 
////				if (tIdx == words.get(T)) {
////					continue;
////				} else if (tIdx == words.get(keySet.get(i))) {
////					T = keySet.get(i);
////				} else {
////					T = keySet.get(i+1);
////				}
////			} else if (sIdx == words.get(keySet.get(i))) {
////				//S = keySet.get(i);
////				int tIdx = Math.min(words.get(S), words.get(keySet.get(i+1))); 
////				if (tIdx == words.get(S)) {
////					T = S;
////				} else {
////					T = keySet.get(i+1);
////				}
////				S = keySet.get(i);
////			} else {
////				//S = keySet.get(i+1);
////				int tIdx = Math.min(words.get(S),words.get(keySet.get(i))); 
////				if (tIdx == words.get(S)) {
////					T = S;
////				} else {
////					T = keySet.get(i);
////				}
////				S = keySet.get(i+1);
////			}
//		}
//	}
//}
//
//System.out.println(S);
//System.out.println(T);
