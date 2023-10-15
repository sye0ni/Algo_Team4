import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * 아이들을 순서대로 정렬하려면 몇 번 움직여야하는지 찾는 문제
 * DP 문제에 있었으므로 dp 배열을 만들어서 품
 * 
 * 가장 적은 수의 사람을 옮겨야 하므로 순서대로 정렬되어 있는 사람들의 수를 구하면 됨 = 최장증가수열 문제
 * 
 * 
 * 14156kb	124ms
 * @author 김무준
 *
 */
public class BJ_2631_줄세우기_김무준 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		// 최장증가 수열의 길이를 저장하는 배열
		int[] dp = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int res = 0;
		for(int i=0; i<n; i++) {
			// 혼자 있으면 수열의 길이 = 1
			// 1로 초기값 설정
			arr[i] = 0;
			for(int j=0; j<n; j++) {
				// arr[i]가 뒤의 숫자들보다 크면 수열 개수만큼 1씩 증가
				if(arr[i] > arr[j]) dp[i] = Math.max(dp[i], dp[j]+1);
			}
			// 저장된 수열 길이 중 최대값 구함
			res = Math.max(res, dp[i]);
		}
		// 전체 아이들 수에서 수열길이 최대값 빼면 정답
		System.out.println(n - res);
	}
}
