/**
 * 
 */
package 구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * BJ_8979_올림픽_김광연
 * 메모리 12368 kb 시간 104 ms
 * 아이디어
 * comparable을 활용해서 우선순위 큐로 순서대로 빼내려고 함
 * 우선 국가 번호, 금, 은, 동 수를 갖는 Nation 클래스를 만들고 compareTo 메서드를 활용해 금, 은, 동 순으로 내림차순하도록 함
 * 우선순위 큐에 입력값들 다 넣고 차례대로 빼면서 원하는 국가 나오면 등수 출력하고 break
 * 이때, 이전 국가와 같은 금, 은, 동 수를 가지면 등수를 올리지 않고 eq라는 변수만 올려줌. 다른 값일 경우는 rank값을 rank+eq로 갱신
 * 유의할 점
 * 문제를 제대로 안읽고 공동 2등을 경우 그 수만큼 다음 순위로 안가고 바로 3위로 감. 꼼꼼히 풀자
 */
public class BJ_8979_올림픽_김광연 {
	
	static class Nation implements Comparable<Nation>{
		int nation; // 국가 번호
		int gold; // 금메달 수
		int silver; // 은메달 수
		int bronze; // 동메달 수
		
		public Nation(int nation, int gold, int silver, int bronze) {
			this.nation = nation;
			this.gold = gold;
			this.silver = silver;
			this.bronze = bronze;
		}
		
		@Override
		public int compareTo(Nation o) {
			if (this.gold == o.gold) {
				if (this.silver == o.silver) {
					return Integer.compare(o.bronze, this.bronze);
				}
				return Integer.compare(o.silver, this.silver);
			}
			return Integer.compare(o.gold, this.gold);
		}	
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 국가의 수
		int K = Integer.parseInt(st.nextToken()); // 등수를 알고 싶은 국가
		
		PriorityQueue<Nation> pq = new PriorityQueue<>();
		//int[][] board = new int[N][3]; // 국가별 금, 은, 동 수
		int nation = 0;
		int g = 0;
		int s = 0;
		int b = 0;
		
		for (int i = 0; i < N; i++) { // 입력값 받아서 우선순위 큐에 넣기
			st = new StringTokenizer(br.readLine());
			nation = Integer.parseInt(st.nextToken()); // 국가 번호
			g = Integer.parseInt(st.nextToken()); // 금메달 수
			s = Integer.parseInt(st.nextToken()); // 은메달 수
			b = Integer.parseInt(st.nextToken()); // 동메달 수
			
			pq.offer(new Nation(nation, g, s, b));
		}
		
		int rank = 1; // 순위 변수
		int eq = 1; // 공동 순위 변수
		Nation past = pq.poll(); // 첫 값 먼저 확인 (past로 쓰기 위해)
		if (past.nation == K) { // 등수를 알아낼 국가면
			System.out.println(rank);
		} else {
			while (!pq.isEmpty()) { // 우선순위 큐가 빌 때까지
				Nation cur = pq.poll();

				if (past.gold == cur.gold && past.silver == cur.silver && past.bronze == cur.bronze) { // 전에 나온 국가와 금은동 수가 같은 경우
					eq++; // 같은 순위이므로 공동 순위를 +1
				} else { // 이전 국가와 메달 수가 다르면 
					rank += eq; // 순위값을 공동 순위를 더한 값으로 갱신 
					eq = 1; // 공동 순위 값은 다시 1로 초기화
				}	
				
				//System.out.println(cur.nation + " " + eq + " " + rank);
				if (cur.nation == K) { // 등수를 알아낼 국가면
					System.out.println(rank);
					break;
				}
				past = cur; // 현재 국가 정보를 이전 국가 정보로 갱신
			}
		}
	}
}
