package algorithm0925;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * 
 * BJ_1581_락스타락동호_김광연
 * 메모리 11544 kb 시간 80 ms
 * 아이디어
 * FS와 SF를 위주로 비교
 * 그냥 나올 수 있는 경우들을 생각하면서 나눠보기
 * FS와 SF만 정해주면 FF와 SS는 알아서 맞춰서 넣을 수 있음
 */
public class BJ_1581_락스타락동호_김광연 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int FF = Integer.parseInt(st.nextToken());
		int FS = Integer.parseInt(st.nextToken());
		int SF = Integer.parseInt(st.nextToken());
		int SS = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		
		// 빠른 곡으로 시작해야하기 때문에 크게 FF와 FS의 상태로 조건을 나눔
		if (FS == 0 && FF != 0) { // FS가 없고 FF만 있을 때 => 느린 곡이 나올 수 X
			System.out.println(FF);
		} else if (FS != 0 && FF == 0) { // FS만 있을 때 => 이때는 다시 FS와 SF의 상태로 조건을 나눔 
			if (FS > SF) { // FS가 더 크면 FS가 SF보다 하나 더 올 수 있으므로 1 더해줌
				cnt = SF+SF+1+SS;
				System.out.println(cnt);
			} else {
				cnt = FS+FS+SS;
				System.out.println(cnt);
			}
		} else if (FS != 0 && FF != 0) { // 둘다 0이 아닐 때 => 위의 조건에서 FF만 더해주면 됨
			if (FS > SF) {
				cnt = SF+SF+1+SS+FF;
				System.out.println(cnt);
			} else { 
				cnt = SS+FF+FS+FS;
				System.out.println(cnt);
			}
		} else { // FS와 FF 둘다 0일 때
			if (SF > 0) { // SF는 마지막에 딱 한번만 나올 수 있으므로 SS에 1 더해줌
				cnt = SS + 1;
				System.out.println(cnt);
			} else {
				System.out.println(SS);
			}
		}
	}

}
