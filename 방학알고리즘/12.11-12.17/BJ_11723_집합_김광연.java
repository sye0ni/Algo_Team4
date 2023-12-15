/**
 * 
 */
package 구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * BJ_11723_집합_김광연
 * 메모리 324980 kb 시간 952 ms
 * 아이디어
 * 주어지는 연산에 맞게 수행하는 구현문제
 * 조금이라도 시간 줄이려고 첫문자 또는 두번째 문자만 비교해서 알맞은 연산 수행
 * List를 활용해 구현 => 통과는 됐지만 메모리와 시간이 크게 나옴
 * 찾아보니 비트마스킹으로 푸는 방법이 있대서 풀어봤는데 List 활용했을 때와 비슷하게 나옴
 * 비트 마스킹 참고: https://myeongju00.tistory.com/30
 * 유의할 점
 * remove 메서드는 인덱스로 삭제하는 경우와 원소를 찾아서 삭제하는 경우 둘다 있으므로 int를 삭제하려면 new Integer()처럼 래퍼 형식으로 해야 함
 */
public class BJ_11723_집합_김광연 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		List<Integer> list = new ArrayList<>();
		
		int N = Integer.parseInt(br.readLine());
		String order = "";
		int num = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			order = st.nextToken();
			if (order.charAt(1) == 'l') { // all인 경우
				list.clear();
				for (int j = 1; j <= 20; j++) {
					list.add(j);
				}
			} else if (order.charAt(0) == 'a') { // add일 때
				num = Integer.parseInt(st.nextToken());
				if (!list.contains(num)) {
					list.add(num);
				}
			} else if (order.charAt(0) == 'c') { // check일 때
				num = Integer.parseInt(st.nextToken());
				if (list.contains(num)) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
			} else if (order.charAt(0) == 'r') { // remove일 때
				num = Integer.parseInt(st.nextToken());
				if (list.contains(num)) {
					list.remove(new Integer(num));
				}
			} else if (order.charAt(0) == 'e') { // empty일 때
				list.clear();
			} else if (order.charAt(0) == 't') { // toggle일 때
				num = Integer.parseInt(st.nextToken());
				if (list.contains(num)) {
					list.remove(new Integer(num));
				} else {
					list.add(num);
				}
			}
			//System.out.println(list.toString());
		}
		
		System.out.println(sb);
		
		// 비트마스킹으로 풀기
		int ans = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			order = st.nextToken();
			if (order.charAt(1) == 'l') { // all인 경우
				ans = (1 << 21) - 1;
			} else if (order.charAt(0) == 'a') { // add일 때
				num = Integer.parseInt(st.nextToken());
				ans |= (1 << num); // 원소 추가하기
			} else if (order.charAt(0) == 'c') { // check일 때
				num = Integer.parseInt(st.nextToken());
				if ((ans & (1 << num)) == (1 << num)) { // 해당 원소 포함하고 있으면
					sb.append(1).append("\n"); // 1 출력
				} else { // 없으면
					sb.append(0).append("\n"); // 0출력
				}
			} else if (order.charAt(0) == 'r') { // remove일 때
				num = Integer.parseInt(st.nextToken());
				ans &= ~(1 << num); // 원소 삭제하기
			} else if (order.charAt(0) == 'e') { // empty일 때
				ans = 0; 
			} else if (order.charAt(0) == 't') { // toggle일 때
				num = Integer.parseInt(st.nextToken());
				ans ^= (1 << num); // 해당 원소가 있으면 삭제하고, 없으면 추가하기
			}
			//System.out.println(list.toString());
		}
	}

}
