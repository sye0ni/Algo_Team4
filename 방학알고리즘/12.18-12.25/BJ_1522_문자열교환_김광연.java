/**
 * 
 */
package 문자열;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author 김광연
 * BJ_1522_문자열교환_김광연
 * 메모리 11472 kb 시간 80 ms
 * 아이디어
 * 슬라이딩 윈도우와 투포인터 활용한 구현 문제
 * 처음에는 어떤 식으로 풀지 몰라서 고민했는데 슬라이딩 윈도우와 투포인터를 활용하라는 힌트를 보고 나서야 풀었음
 * 먼저 a 개수를 구한 후 인덱스 0부터 a 개수만큼의 문자열을 확인 => b의 개수만큼이 교환해야하는 수
 * 이때, 문자열이 원형이기 때문에 문자열의 마지막 인덱스까지 슬라이딩 윈도우의 시작인덱스로 확인하고 인덱스가 문자열의 총 길이보다 늘어날 경우를 대비해 총길이로 나눈 나머지로 확인함
 * b의 개수를 확인하는 방법은 투포인터를 활용해서 구함
 * b 개수 구하기는 2가지 방법으로 해봄 => 1. 투포인터로 각 경우마다 다 구해보기 2. 인덱스 0에서의 b의 개수만 구한 후, 다음 인덱스로 갈 때마다 제외되는 문자와 추가되는 문자만 확인해서 개수 구하기
 * 둘다 메모리와 시간은 비슷하게 나옴 
 * 유의할 점
 * 투포인터 활용할 때 while 조건을 left < right 로 뒀는데 이러면 둘이 같은 경우는 확인을 못하므로 따로 둘이 같은 경우까지 확인해줘야 함.
 * 
 */
public class BJ_1522_문자열교환_김광연 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = br.readLine(); // 문자열 입력 받기
		int len = tmp.length(); // 문자열의 총 길이
		int cnt = 0; // 문자열에서 a 개수 
		for (int i = 0; i < len; i++) { // a 개수 세기
			if (tmp.charAt(i) == 'a') {
				cnt++;
			}
		}
		int min = 2147000000; // b의 최소 개수 
		int bCnt = 0; // b 개수
		int left = 0; // 왼쪽 포인터
		int right = 0; // 오른쪽 포인터
		for (int i = 0; i < len; i++) { // 인덱스 0부터 윈도우 슬라이싱 
			bCnt = 0; // b의 개수 초기화
			left = i; // 왼쪽 포인터 인덱스
			right = i + cnt - 1; // 오른쪽 포인터 인덱스 (a의 개수만큼 슬라이싱)
			while (left < right) { // 투포인터로 b 개수 구하기 => 왼쪽 포인터 인덱스가 오른쪽 포인터 인덱스보다 작을 경우
				// 문자가 b인 경우 카운트
				if (tmp.charAt(left%len) == 'b') { 
					bCnt++;
				}
				if (tmp.charAt(right%len) == 'b') {
					bCnt++;
				}
				// 인덱스 조정
				left++;
				right--;
			}
			// 두 포인터가 같은 경우
			if (left == right) {
				if (tmp.charAt(left%len) == 'b') {
					bCnt++;
				}
			}
			min = Math.min(min, bCnt); // 최소값 조정
		}
		System.out.println(min);
	}
// 다른 방법으로 b 개수 구하기	
//	int min = 2147000000; // b의 최소 개수 
//	int bCnt = 0; // b 개수
//	int left = 0;
//	int right = cnt-1;
//	if (len == 2 || cnt == 1) {
//		System.out.println(0);
//	} else {
//		while (left  < right ) {
//			if (tmp.charAt(left) == 'b') {
//				bCnt++;
//			}
//			if (tmp.charAt(right) == 'b') {
//				bCnt++;
//			}
//			left++;
//			right--;
//		}
//		if (left == right) {
//			if (tmp.charAt(left%len) == 'b') {
//				bCnt++;
//			}
//		}
//		min = Math.min(min, bCnt);
//		for (int i = 1; i < len; i++) {
//			if (tmp.charAt((i-1)%len) == 'b') {
//				bCnt--;
//			}
//			if (tmp.charAt((i+cnt-1)%len) == 'b') {
//				bCnt++;
//			}
//			min = Math.min(min, bCnt);
//		}
//		System.out.println(min);
//	}

}
