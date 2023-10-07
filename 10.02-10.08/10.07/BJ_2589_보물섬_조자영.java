package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 
 * BJ_2589_보물섬
 * 218920kb	456ms
 * 
 * 처음에 조합을 해서 시작점과 목적지를 아예 정해서 다른 경로로 가는 경우를 없애면 더 빠르지 않을까 했는데 시간초과가 남...
 * 그냥 그래서 L인 경우에 다른 L로 도착하는 경우 bfs사용시 최단거리이므로 그때의 최장시간을 찾는방법으로 했는데 시간초과가 안남
 * 시작점과 목적지를 정해버리면 가는 길에 다른 L로 가는 경우를 또 구하는 셈이라 더 오래걸리는 것 같음...
 * 어쨌든 해결!!
 *
 */

public class BJ2589 {
	
	static int W, H, max;
	static int dx[] = {0, -1, 0, 1}, dy[] = {-1, 0, 1, 0};
	static char map[][];

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new char[W][H];
		
		for(int i=0; i<W; i++) {
			String input = br.readLine();
			for(int j=0; j<H; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		max = 0;
		
		for(int i=0; i<W; i++) {
			for(int j=0; j<H; j++) {
				if(map[i][j]=='L') {
					findTreasure(i, j);
				}
			}
		}
		
		System.out.println(max);
	}

	private static void findTreasure(int x, int y) {
		
		// 방문체크
		boolean isVisited[][] = new boolean[W][H];
		isVisited[x][y] = true;
		
		Deque<int[]> dque = new ArrayDeque<>();
		dque.add(new int[] {x, y, 1});
		
		while(!dque.isEmpty()) {
			int[] start = dque.poll();
			for(int i=0; i<4; i++) {
				int nextX = start[0] + dx[i];
				int nextY = start[1] + dy[i];
				// 범위확인
				if(nextX<0 || nextX>=W || nextY<0 || nextY>=H) continue;
				// 방문한적 있다면 다음으로
				if(isVisited[nextX][nextY]) continue;
				// 땅인지 확인 아니면 다음으로
				if(map[nextX][nextY]=='W') continue;
				
				// 땅이면 큐에 추가
				dque.add(new int[] {nextX, nextY, start[2]+1});
				isVisited[nextX][nextY] = true;
				
				max = Math.max(max, start[2]);
			}
		}
	}
}
