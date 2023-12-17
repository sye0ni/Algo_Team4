package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 356652kb	808ms 
 *
 * 못풀었던문제3
 * 후...항상 시간초과가 문제...
 * 맨앞 접시를 빼고 새로운접시가 들어왔을때 두개만 체크하는 방식으로 해결
 * 메모리랑 시간 둘다 별로라.... 일단 자구 아침에 큐를 하나만 쓰는 방법으로 바꿔봐야겠다...
 */

public class BJ15961 {
	
	static Deque<Integer> table, eat;
	static int isEat[];
	static int count, coupon, max, k, d;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		coupon = Integer.parseInt(st.nextToken());
		
		table  = new ArrayDeque<>();
		eat = new ArrayDeque<>();
		
		for(int i=0; i<N; i++) {
			table.add(Integer.parseInt(br.readLine()));
		}
		
		count = 0;
		max = 0;
		isEat = new int[d+1];
		// 쿠폰은 어차피 먹는거니까 미리 체크
		isEat[coupon] = 1;
		
		
		for(int i=0; i<N; i++) {
			// 최대값이 되면 더 할 필요가 없으므로
			if(max == k+1) break;
			eat(i);
		}
		
		System.out.println(max);
	
	}

	private static void eat(int cnt) {
		
		int out = 0;
		int in = 0;
		
		if(cnt == 0) {			
			for(int i=0; i<k; i++) {
				int x = table.poll();
				eat.add(x);
			}
		}else {
			// 접시 하나 빼기
			out = eat.poll();
			// 뺀 접시 회전 테이블에
			table.add(out);
			// 다른 접시 하나 추가 하기
			in = table.poll();
			eat.add(in);
		}
		
		checkDish(out, in);
	}

	private static void checkDish(int out, int in) {
		
		// 가장 처음 먹는 경우일단 전체 초밥 가짓 수 계산함
		if(out==0){
			for(int i=0; i<k; i++){
				int sushi = eat.poll();
				isEat[sushi]++;
				eat.add(sushi);
			}
			// 처음에 먹은 가짓수 계산
			for(int i=0; i<d+1; i++) {
				if(isEat[i]!=0) {
					count ++;
				}
			}
			
		}else {
			// 빠진것 체크, 들어온 것 체크
			isEat[out]--;
			if(isEat[out]==0) {
				// 빠진접시가 남은 접시중에 겹치는 게 없다면 count--;
				count--;
			}
			isEat[in]++;
			if(isEat[in]==1) {
				// 들어온 접시가 새로운 음식이면 count++;
				count++;
			}
		}

		max = Math.max(max, count);
		
	}

}
