/**
 * 
 */
package algorithm1015;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * BJ_1922_네트워크연결_김광연
 * 메모리 44,776 kb 352 ms
 * 아이디어
 * 모든 노드을 갈 수 있는 최소 가중치의 합 구하기 => 크루스칼 알고리즘
 * union과 find 메서드를 활용해 사이클의 여부를 확인하면서 간선 고르기
 * 우선순위 큐를 활용해 비용이 적은 순으로 간선을 고르고 싸이클이 생기지 않으면서 간선 N-1개를 고르기
 */
public class BJ_1922_네트워크연결_김광연 {
	
	static class Dot implements Comparable<Dot>{
		int s; // 시작점
		int e; // 도착점
		int c; // 비용
		
		public Dot(int s, int e, int c){
			this.s = s;
			this.e = e;
			this.c = c;
		}

		@Override
		public int compareTo(Dot o) { // 비용이 적은 순으로
			return Integer.compare(this.c, o.c);
		}
				
	}
	
	static int[] check;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 컴퓨터 수
		int M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수
		PriorityQueue<Dot> pq = new PriorityQueue<>();
		for (int i = 0; i < M; i++) { // 연결 정보 큐에 넣기
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()); // 시작점
			int e = Integer.parseInt(st.nextToken()); // 도착점
			int c = Integer.parseInt(st.nextToken()); // 비용
			
			pq.offer(new Dot(s, e, c));
		}
		
		make(); // 부모 배열 만들고 인덱스 값으로 초기화 하기
		
		int cnt = 0; // 사용한 간선 수
		//int flag = 0;
		int sum = 0;
		// 크루스칼 알고리즘
		while (!pq.isEmpty()) {
			Dot dot = pq.poll();

			if (find(dot.s) == find(dot.e)) { // 같은 부모를 가짐 -> 싸이클이 생겼으므로 패스
				continue;
			}
			cnt++;
			sum += dot.c; // 총합에 비용 추가해주기
			union(dot.s, dot.e); // 시작점과 도착점의 부모 합치기
			if (cnt == N-1) { // 사용한 간선 수가 노드 수-1 => 최소일 경우
				//flag = 1;
				break;
			}
		}
		
		System.out.println(sum);
		
	}
	static void make() { // 부모 배열 만들기 - 본인 값으로 초기화
		check = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			check[i] = i;
		}
	}
	
	static int find(int x) { // 부모 값 찾기 -> 본인과 다르면 그 값의 부모 찾기
		if (x == check[x]) {
			return x;
		}
		return check[x] = find(check[x]);
	}

	static void union (int x, int y) { // 두 노드 합치기 -> 뒤에 오는 노드의 부모를 앞에 오는 노드의 부모로 바꾸기
		if (find(y) == find(x)) {
			return;
		}
		check[find(y)] = find(x);	
	}
}
