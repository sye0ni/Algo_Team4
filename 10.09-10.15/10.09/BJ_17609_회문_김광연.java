/**
 * 
 */
package algorithm1009;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author 김광연
 * BJ_17609_회문_김광연
 * 메모리 36,808 kb 시간 260 ms
 * 아이디어
 * 윈도우 머시기 문제. 인덱스 2개 만들어서 양쪽에서 좁혀가면서 풀었음
 * 처음에는 start와 end가 다른 경우에는 start가 end-1과 같을 때와 start+1과 end가 같을 때로 나눠서 풂
 * 그리고 둘다 같은 경우에는 그 다음 인덱스까지만 비교해도 된다고 생각함. 둘다 맞는 경우가 더이상 안나올거라고 생각했는데 내니깐 틀림
 * 다시 생각해보니 abababbabab 처럼 다음 인덱스까지 비교해도 또 둘다 같은 경우가 나올 수 있었다
 * 그래서 먼저 end 인덱스를 감소시킨 경우를 먼저 해본후 cnt가 2가 되면 바로 탈출시켜서 start인덱스를 증가시킨 경우를 답으로 하고 
 * cnt가 1이면 start인덱스를 증가시킨 경우는 해보지 않고 바로 탈출해 답으로 했다.
 * 유의할 점은 별 생각없이 SWEA 문제처럼 출력 양식을 지정했었는데 다시 문제를 보니 그냥 답만 출력하는 거였음. 문제 꼼꼼하게 읽기! 
 */
public class BJ_17609_회문_김광연 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			//sb.append("#").append(tc).append(" "); // 문제 꼼꼼하게 보기
			String str = br.readLine(); // 문자열 입력 받기
			int start = 0; // 시작 인덱스
			int end = str.length() - 1; // 끝 인덱스

			int cnt = 0; // 틀린 개수
			while (start < end) { // 서로 만나기 전까지 반복
				if (str.charAt(start) == str.charAt(end)) { // 해당 위치의 문자들이 같으면
					start++; // 한칸 증가
					end--; // 한칸 감소
				} else { // 다르면
					cnt++; // 틀린 횟수 1 증가
					
					if (cnt == 2) { // 틀린 횟수가 2번이면
						break; // 탈출 -> 더이상 볼필요 X
					}
					if (str.charAt(start) == str.charAt(end - 1) && str.charAt(start + 1) == str.charAt(end)) { // end를 빼고 start와 end-1을 비교할 때와 start를 빼고 start+1과 end를 비교할 때 둘다 같으면
						// end를 뺀 경우부터 해보기
						int ts = start; // 다시 변수 지정
						int te = end;
						te--;
						while (ts < te) {
							if (str.charAt(ts) == str.charAt(te)) {
								ts++;
								te--;
							} else { // 한번이라도 틀리면 바로 회문이 아니므로 바로 탈출
								cnt++; // 틀린 횟수
								break;
							}
						}
						if (cnt == 1) { // 위에서 한번도 틀리지 않았다면 유사회문이므로 start를 뺀 경우는 할 필요 X
							break; // 탈출
						} else { // end를 뺀 경우는 회문이 아니므로 start를 뺀 경우로 다시 해보기
 							start++;
							cnt = 1; // cnt 값 조정
						}						
					} else { // 둘 중 하나만 같은 경우
						if (str.charAt(start) == str.charAt(end - 1)) { // end 위치 문자 빼주기
							end--;
						} else if (str.charAt(start + 1) == str.charAt(end)) { // start 위치 문자 빼주기
							start++;
						}	
					}				
				}
			}
			sb.append(cnt).append("\n"); // 불일치 문자 개수를 의미하는 cnt가 0 -> 회문, 1 -> 유사회문, 2 -> 회문이 아님
		}
		System.out.println(sb);
	}
}
