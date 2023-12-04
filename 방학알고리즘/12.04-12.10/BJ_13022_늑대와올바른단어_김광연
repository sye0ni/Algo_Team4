/**
 * 
 */
package 문자열;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author 김광연
 * BJ_13022_늑대와올바른단어_김광연
 * 메모리 11484 kb 시간 80 ms
 * 아이디어
 * 처음에는 cnt 배열을 만들어 각 문자의 개수를 저장하면서 가려고 했는데 굳이 그럴 필요 없이 그냥 'w' 문자 개수만 저장해 놓으면 된다고 생각
 * 문자가 순서에 맞게 오는지 알기 위해 char 배열을 만들어 w, o, l, f를 순서대로 넣어두고 주어진 문자열에서 현재 문자가 다음 문자와 다르면 idx값을 증가시키며 비교 
 * 처음에 4의 배수가 아니거나 시작을 'w'로 안하거나 끝을 'f'로 안한 경우는 제외함
 */
public class BJ_13022_늑대와올바른단어_김광연 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//int[] cnt = new int[4]; // 문자 개수 카운트할 배열
		int c = 0; // 'w'의 연속된 개수 저장할 변수
		int tmp = 0; // 'w' 개수 담아 놓을 임시 변수
		char[] res = {'w', 'o', 'l', 'f'};
		int idx = 0; // 문자가 순서에 맞는지 확인할 인덱스
		char[] str = br.readLine().toCharArray();
		int len = str.length;
		int flag = 1; // 옳은 단어인지 확인하는 용도
		if ((len % 4) != 0 || str[0] != 'w' || str[len-1] != 'f') {
			System.out.println(0);
		} else {
			for (int i = 0; i < len-1; i++) {
				if (idx == 0) { // 'w'일 경우 개수 세기
					c++;
				} else { // 다른 문자들은 정해진 개수('w'개수)에서 1씩 빼기
					c--;
					if (c < 0) { // 해당 문자 수가 'w'보다 많은 경우
						System.out.println(0);
						flag = 0;
						break;
					}
				}
				if (str[i+1] != res[idx]) { // 다음 문자가 같지 않을 때				
					if (str[i+1] == res[(idx+1)%4]) { // 순서에 맞는 문자가 왔을 때
						if (idx == 0) { // 'w' 문자 수 저장
							tmp = c; // 임시 변수에 'w'의 개수 넣어두기
							idx++; // 다음 문자로 
							continue;
						}					
						if (c > 0) { // 개수가 다를 때
							System.out.println(0);
							flag = 0;
							break;
						} else {
							if (idx+1 < 4) { // 다음 단어가 'w'가 아닌 경우
								c = tmp;
								idx++;
							} else { // 다음 단어가 'w'인 경우
								c = 0;
								idx = 0;
							}
						}
						
					} else { // 순서에 맞지 않는 문자가 왔을 때
						System.out.println(0);
						flag = 0;
						break;
					}
				}
			}
			if (flag == 1) {
				System.out.println(1);
			}
		}
	}

}
