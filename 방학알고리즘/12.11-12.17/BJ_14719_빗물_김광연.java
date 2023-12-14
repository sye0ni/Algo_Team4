/**
 * 
 */
package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * BJ_14719_빗물_김광연
 * 메모리 12420 kb 시간 926ms
 * 아이디어
 * 2차원 배열을 활용한 구현 문제
 * 주어진 높이 정보들을 2차원 배열로 표현
 * flag 변수를 만들어 시작점이 주어지면 flag를 1로 바꾸고 같은 높이의 기둥을 만날 때마다 그동안 더한 값들을 총합에 더해줌
 * 시간을 좀 줄이기 위해 max 높이에서 시작
 * 유의할 점
 * 최대 높이에서 시작하지만 배열 상으로는 총높이-최대높이다. 꼼꼼하게 풀기
 */
public class BJ_14719_빗물_김광연 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken()); // 행 길이
		int W = Integer.parseInt(st.nextToken()); // 열 길이
		int[][] board = new int[H][W]; // 2차원 배열
		int max = -1; // 최대 높이 저장할 변수
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			int h = Integer.parseInt(st.nextToken());
			max = Math.max(max, h); // 최대 높이 구하기 => 이 높이에서 시작하기 위해서
			for (int j = H-h; j < H; j++) {
				board[j][i] = 1; // 해당 높이부터 바닥까지 다 1로 채우기
			}
		}
		
		int flag = 0; // 시작점 존재 확인할 변수
		int cnt = 0; // 시작점으로부터 길이 저장할 변수
		int sum = 0; // 총합 
		for (int i = H-max; i < H; i++) { // 최대 높이에서 바닥까지 완전 탐색하기
			flag = 0; 
			cnt = 0;
			for (int j = 0; j < W; j++) { // 첫번째 열부터 마지막 열까지 확인
				if (board[i][j] == 0 && flag == 0) { // 빈 공간이고 flag가 0(아직 시작점 X)면
					continue; // 패스
				} else if (board[i][j] == 1 && flag == 0) { // 블록이 있고 시작점이 없으면
					flag = 1; // flag를 1로 => 여기가 시작점
				} else if (board[i][j] == 0 && flag == 1) { // 빈공간인데 시작점이 있으면 
					cnt++; // 시작점으로부터 길이 1 증가
//					board[i][j] = 2;
				} else { // 블록이 있고 시작점도 있을 때 => 물이 고일 수 있음
					sum += cnt; // 지금까지의 길이 총합에 더해주기
					cnt = 0; // cnt는 초기화
				}
			}
		}
//		for (int i = 0; i <  H; i++) {
//			for (int j = 0; j < W; j++) {
//				System.out.print(board[i][j]);
//			}
//			System.out.println();
//		}
		System.out.println(sum);
	}

}
