package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
* SWEA_1953_탈주범검거
* 26,364 kb 136 ms
* bfs사용해서 방문한 곳 다시 방문하지 않도록 체크
* 시작할때 시간이 다 되었으면 큐에 넣지 않는다.
* 금방 풀어서 쉽네 그랬는데 파이프가 없기도 하고 이어져 있지 않기도 해서 다시 풀었음^^
* 그래도 1시간 20분? 걸린듯? ㅎㅎㅎㅎㅎ
*/

public class SWEA1953 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			// 지하터널 지도의 세로
			int N = Integer.parseInt(st.nextToken());
			// 지하터널 지도의 가로
			int M = Integer.parseInt(st.nextToken());
			// 맨홀 뚜껑이 위치한 세로 위치
			int R = Integer.parseInt(st.nextToken());
			// 맨홀 뚜껑이 위치한 가로 위치
			int C = Integer.parseInt(st.nextToken());
			// 탈출 후 소요된 시간
			int L = Integer.parseInt(st.nextToken());
			
			// 지도
			int map[][] = new int[N][M];
			// 방문체크
			boolean isVisited[][] = new boolean[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			isVisited[R][C] = true;
			Deque<int[]> que = new ArrayDeque<>(); 
			List<int[]> list;
			que.add(new int[] {R, C, 1});
			while(!que.isEmpty()) {
				int now[] = que.poll();
				int nowX = now[0];
				int nowY = now[1];
				int time = now[2];
				// 시간이 같으면 더 할 필요 없음
				if(time == L) continue;
				int type = map[now[0]][now[1]];
				list = new ArrayList<>();
				
				int len = 0;
				switch(type) {
					case 1: // 상우하좌
						list.add(new int[] {-1, 0, 1});
						list.add(new int[] {0, 1, 2});
						list.add(new int[] {1, 0, 3});
						list.add(new int[] {0, -1, 4});
						break;
					case 2: // 상하
						list.add(new int[] {-1, 0, 1});
						list.add(new int[] {1, 0, 3});
						break;
					case 3: // 좌우
						list.add(new int[] {0, -1, 4});
						list.add(new int[] {0, 1, 2});
						break;
					case 4: // 상우
						list.add(new int[] {-1, 0, 1});
						list.add(new int[] {0, 1, 2});
						break;
					case 5: // 하우
						list.add(new int[] {1, 0, 3});
						list.add(new int[] {0, 1, 2});
						break;
					case 6: // 하좌
						list.add(new int[] {1, 0, 3});
						list.add(new int[] {0, -1, 4});
						break;
					case 7: // 상좌
						list.add(new int[] {-1, 0, 1});
						list.add(new int[] {0, -1, 4});
						break;
				}
				
				for(int i=0; i<list.size(); i++) {
					int next[] = list.get(i);
					int newX = nowX + next[0];
					int newY = nowY + next[1];
					// 범위 안에 있는지
					if(newX<0 || newX>=	N || newY<0 || newY>=M) continue;
					// 방문했는지 확인
					if(isVisited[newX][newY]) continue;
					// 파이프가 없거나 연결된 파이프가 없으면
					int nextType = map[newX][newY];
					if(nextType==0 || !isPossible(nextType, next[2])) continue;
					isVisited[newX][newY] = true;
					// 방문안했으면 방문 하기
					que.add(new int[] {newX, newY, time+1});
				}
				
			}
			
			int cnt = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(isVisited[i][j]) {
						cnt++;
					}
				}
			}
			
			System.out.println("#"+test_case+" "+cnt);
			
		}
		

	}

	private static boolean isPossible(int nextType, int type) {
		
		boolean result = false;
		
		switch(type) {
	 		case 1:
	 			if(nextType == 1 | nextType == 2 | nextType == 5 | nextType == 6) result = true;
	 			break;
	 		case 2:
	 			if(nextType == 1 | nextType == 3 | nextType == 6 | nextType == 7) result = true;
	 			break;
	 		case 3:
	 			if(nextType == 1 | nextType == 2 | nextType == 4 | nextType == 7) result = true;
	 			break;
	 		case 4:
	 			if(nextType == 1 | nextType == 3 | nextType == 4 | nextType == 5) result = true;
	 			break;
		}
		
		return result;
	}


}
