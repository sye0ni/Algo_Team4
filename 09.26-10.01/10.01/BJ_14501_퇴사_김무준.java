import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 광연아 너의 문제 풀어봤어
 * 
 * dp 문제
 * memo 배열을 만들어서 얻을 수 있는 수익의 최대값을 갱신해 감
 * 상담을 끝낸 날에 해당하는 인덱스에 0 ~ n-1 일 까지 순회하며 각각의 상담을 했을 때 받을 수 있는 최대값을 저장함
 * 또한 상담을 진행중일 땐 수익을 얻을 수 없으므로
 * memo[i] ~ memo[상담 끝나는 날] 의 값은 (지금까지의 수익 + 현재 진행중인 상담의 수익) 으로 넣어준다. 
 * 
 * 이를 점화식으로 써보면
 * memo[i+arr[i][0]] = Math.max(memo[i+arr[i][0]], memo[i]+arr[i][1])
 * memo[i] = Math.max(memo[i], memo[i+1]
 * 
 * n+1일 째에 퇴사하기 때문에 memo[n]값이 구하고자 하는 답
 * 14260kb	120ms
 * @author 김무준
 *
 */
public class BJ_14501_퇴사_김무준 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 소요 일, 수익 저장
		int[][] arr = new int[n][2];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		// 0, 1, 2, ..., n-1 일 까지 상담이 가능하기 때문에 n+1 짜리 memo 배열 생성
		int[] memo = new int[n+1];
		
		for(int i=0; i<n; i++) {
			// 퇴사를 n+1일 때 할 수 있기 때문에 상담 소요시간이 n+1을 넘지 않으면 상담 진행
			if(i + arr[i][0] < n+1) {
				// 상담이 끝나는 날에
				// i번째 상담의 수익과 
				// (현재까지의 수익 + i 번째 상담 진행 기간 중 다른 상담을 진행했을 때 얻을 수 있는 수익) 중 최대값을 저장함
				memo[i + arr[i][0]] = Math.max(memo[i+arr[i][0]], memo[i] + arr[i][1]);
			}
			// 현재까지의 수익은 다음날에도 갖고 감
			memo[i+1] = Math.max(memo[i], memo[i+1]);
		}
		// n+1일 째의 수익 출력
		System.out.println(memo[n]);
	}
}
