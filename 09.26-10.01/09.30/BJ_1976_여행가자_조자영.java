package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
* BJ_1976_여행가자
* 18512kb	212ms
*
* 후...같은 곳에서 같은 곳을 갈줄이야... 이것때문에 한참 걸림...
* bfs 사용해서 현재 위치에서 다음 위치로 갈 수 있는지 여부를 확인
* 처음에 방문체크 안해서 메모리초과,,, 방문체크 까먹지말기,,,,
*/

public class BJ1976 {

	static int N, M;
	static int plan[];
	static int city[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		city = new int[N][N];
		plan = new int[M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			plan[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		boolean possible = true;
		for(int i=0; i<M-1; i++) {
			if(!possible) break;
			if(city[plan[i]][plan[i+1]]==1 || plan[i]==plan[i+1]) continue;
			possible = trip(plan[i], plan[i+1]);
		}
		
		System.out.println(possible? "YES": "NO");
	}

	private static boolean trip(int start, int end) {
		
		Deque<Integer> que = new ArrayDeque<>();
		que.add(start);
		boolean isVisited[] = new boolean[N];
		isVisited[start] = true;
		
		while(!que.isEmpty()){
			int now =  que.poll();
			for(int i=0; i<N; i++) {
				// 방문학적있으면 continue;
				if(isVisited[i]) continue;
				if(city[now][i]==0) continue;
				// 바로 갈 수 있으면 바로 스탑
				if(i==end) return true;
				// 아니면 일단 큐에 넣기
				isVisited[i] = true;
				que.add(i);
			} 
		}
		
		// 다 돌았는데 목적지로 가는게 없음
		return false;
		
	}

}
