import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 나무 - r, c, 나이
 * 
 * 문제 구현은 쉽지만 시간제한을 빡빡하게 둬서 시간 초과를 내는 문제
 * 리스트를 탐색하면서 add, remove 할 때 리스트의 인덱스가 바뀌는 점에 유의
 * 
 * 각 자료 구조의 시간 복잡도와 유불리를 이해해야 풀 수 있는 문제
 * 나도 시간초과 많이 나서 검색해봄
 * ArrayList : add, get O(1)
 * 				인덱스를 활용한 검색이 빠름
 * 
 * LinkedList : add, remove, iterator.remove O(1)
 * 				추가 삭제가 빠름 / 검색이 느림
 * 
 * 
 * 297956kb		840ms
 * @author 김무준
 *
 */


public class BJ_16235_나무재테크_김무준 {
	
	// 땅크기, 나무개수, K년 후 정답출력
	static int N, M, K;
	static int[][] soil;
	static int[][] fill;
	static LinkedList<int[]> alive;
	static Queue<int[]> dead;
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		soil = new int[N][N];
		fill = new int[N][N];
		alive = new LinkedList<>();
		dead = new LinkedList<>();
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				fill[i][j] = Integer.parseInt(st.nextToken());
				soil[i][j] = 5;
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			alive.add(new int[] {r, c, age});
		}
		
		
		for(int i=0; i<K; i++) {
			spring();
			summer();
			fall();
			winter();
		}
		
		System.out.println(alive.size());
		
	}
	
	// 나이만큼 양분을 먹음 -> 나이 1 증가
	// 나이가 어린 나무부터 양분을 먹음
	// 양분을 먹지 못하면 즉사
	static void spring() {
		Iterator<int[]> it = alive.iterator();
		while(it.hasNext()) {
			int[] tmp = it.next();
			int r = tmp[0];
			int c = tmp[1];
			int age = tmp[2];
			if(soil[r][c] < age) {
				dead.offer(tmp);
				it.remove();
			} else {
				soil[r][c] -= age;
				tmp[2]++;
			}
		}
	}
	
	// 봄에 죽은 나무가 나이/2 만큼의 양분으로 변함
	static void summer() {
		while(!dead.isEmpty()) {
			int[] tmp = dead.poll();
			soil[tmp[0]][tmp[1]] += tmp[2]/2;
		}
	}
	
	// 나이가 5의 배수인 나무 번식
	static void fall() {
		List<int[]> list = new LinkedList<>();
		for(int[] tmp : alive) {
			int r = tmp[0];
			int c = tmp[1];
			int age = tmp[2];
			
			if(age % 5 == 0) {
				for(int i=0; i<8; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
					list.add(new int[] {nr, nc, 1});
				}
			}
		}
		alive.addAll(0, list);
	}
	
	// fill 배열에 저장되어 있는 값 만큼 양분 추가
	static void winter() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				soil[i][j] += fill[i][j];
			}
		}
	}
	
}
