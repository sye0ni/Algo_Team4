/**
 * 
 */
package 문자열;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author 김광연
 * BJ_9935_문자열폭발_김광연
 * 메모리 27016 kb 시간 256 ms
 * 아이디어
 * 처음에는 substring 메서드 활용해서 폭발 문자열 찾으면 해당 부분 빼고 합치는 식으로 했는데 메모리 초과
 * => 문자열 범위가 백만까지인데 자바에서 String은 불변이라서 값을 바꿀때마다 매번 새로운 String을 할당하기 때문
 * 결국 다른 사람 풀이 참고 => StringBuilder의 delete 메서드 활용
 * + 스택이나 데크로도 풀이 가능
 * 
 */
public class BJ_9935_문자열폭발_김광연 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine(); // 기본 문자열
		String bomb = br.readLine(); // 폭발 문자열

		int h = bomb.length(); // 폭발 문자열의 길이
		int len = str.length();
		//int idx = 0;

		
		for (int i = 0; i < len; i++) {
			sb.append(str.charAt(i));
			if (sb.length() >= h) { // sb에 저장된 문자열의 길이가 폭발 문자열의 길이면
				int flag = 1; // 폭발문자열과 같은지 확인하기 위한 변수
				for (int j = 0; j < h; j++){ // 폭발 문자열의 길이만큼 반복
					if (sb.charAt(sb.length()-h+j) != bomb.charAt(j)) { // 다르면
						flag = 0; 
						break; // 반복문 탈출
					}
				}
				if (flag == 1) { // 폭발 문자열과 같은 경우
					sb.delete(sb.length()-h, sb.length()); //  폭발 문자열의 길이 만큼을 sb의 뒷부분에서 삭제 
				}
			}
		}
		if (sb.length() == 0) {
			System.out.println("FRULA");
		} else {
			System.out.println(sb);
		}
	}

}
