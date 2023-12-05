/**
 * 
 */
package 프로그래머스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 김광연
 * 프로그래머스_k진수에서소수구하기_김광연
 * 아이디어
 * 0을 구분자로 해서 나누고 소수인지 판별하는 문제
 * 처음에 주어진 n을 k진수로 나타내기 위해 k로 나눈 나머지는 list에 넣어두고 n의 값을 몫으로 바꿈 -> list 만든 이유는 순서가 반대라서
 * list까지 만든 이유는 순서가 반대니까 우선 저장하고 새로운 문자열에 반대로 저장하려고 한건데 나중에 다른 사람들 풀이 보니 StringBuilder에 reverse함수가 있었음.
 * 이렇게 맞는 순서로 문자열 변수에 저장하고 "0"을 구분자로 해서 split으로 나누고 나눠진 숫자들 중에 소수의 개수를 구함
 * 유의할 점은 이때, 주어진 n의 범위가 백만까지여서 그냥 int로 해도 상관없겠다고 생각하고 제출했는데 런타임 에러나는 테케가 있었음
 * 생각해보니 n이 아닌 k진수로 나타낸 수는 중간에 0이 없거나 적으면 int 범위를 넘어갈 수 있겠다고 생각해서 long으로 바꾸니깐 됨
 * 전에도 int 범위 관련 문제 있었는데 항상 유의할 것!! 런타임 에러나면 범위부터 다시 보기
 */
public class 프로그래머스_k진수에서소수구하기_김광연 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();
		
		while (n > 0) { // k진수 구하기 (순서가 반대)
			list.add(n%k); // k로 나눈 나머지 저장
			n = n / k; // n은 k로 나눈 몫으로 바꿈
		}
		String tmp = "";
		for (int i = list.size()-1; i >= 0; i--) { // 맞는 순서로 바꿔주기
			tmp += list.get(i);
		}
		String[] primes = tmp.split("0"); // 0을 구분자로 숫자 나눔
		int cnt = 0;
		for (int i = 0; i < primes.length; i++) {
			//System.out.println(primes[i]);
			if (primes[i].equals("")) { // 공백 문자열은 변환이 안되므로 패스
				continue;
			}
			if (isPrime(Long.parseLong(primes[i]))) { // 해당 수가 소수인지 판별하기 
				cnt++; // 소수면 cnt 1 증가
			}
		}
		System.out.println(cnt);		
	}
	
	static boolean isPrime(long x) { // 소수 판별 메서드
		if (x < 2) { // 1인 경우 패스
			return false;
		}
		for (int i = 2; i <= Math.sqrt(x); i++) { // 주어진 수의 제곱근까지만 체크 
			if ((x % i) == 0) { // 나눠떨어지는 경우 false 반환
				return false;
			}
		}
		return true; // 한번도 나눠떨어지지 않은 경우 true 반환
	}
}
