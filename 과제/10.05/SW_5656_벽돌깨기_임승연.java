import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 순서대로 차근차근 구현하면 된다
 *
 * 1. 구슬을 떨어트릴 수 있는 위치를 중복순열로 N개 뽑기
 * 2. 뽑은 뒤에는
 *      (1) 구슬 떨어트림
 *      (2) 연쇄적으로 벽돌 터짐  ( bfs 를 통해 벽톨 깨기 )
 *      (3) 다 깨지면 떠있는 블록 아래로 밀어주기
 * 3. 남은 벽돌 수 카운트하여 비교
 *
 *
 * 107,828 kb 1,596 ms
 *
 *
 * 메모리가 왜 이렇게 큰 지 ????????????????????????????!!!!!!! 
 *
 */
public class Solution {
    static int N, W, H;
    static int[][] brick; // 원본
    static int[][] copyBrick;
    static int[] perm; // 순열 결과 저장
    static int Min; // 남은 벽돌 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine()); // tc 수

        for (int T = 1; T <= tc; T++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            perm = new int[N]; // 순열 N개 뽑기
            brick = new int[H][W];
            copyBrick = new int[H][W];
            Min = Integer.MAX_VALUE;

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < W; j++) {
                    brick[i][j] = Integer.parseInt(st.nextToken());
                }
            } // 입력 받기 완료

            permutation(0);
            sb.append("#" + T + " " + Min + "\n");
        }
        System.out.println(sb.toString());
    }

    static void permutation(int cnt) { // 중복 순열 (N개 뽑기)
        if (cnt == N) {
            arrayCopy(); // 기존 배열 복사

            for (int i = 0; i < N; i++) {
                throwMarble(perm[i]);  // 구슬 던져서 벽돌 깨트리기 (0으로 만들기)
                removeBrick();  // 깨진 벽돌 밀어버리기
            }

            countRemain(); // 남은 벽돌 수 카운트

            return;
        }
        for (int i = 0; i < W; i++) {
            perm[cnt] = i;
            permutation(cnt + 1);
        }
    }

    static void arrayCopy() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                copyBrick[i][j] = brick[i][j];
            }
        }
    }

    static void throwMarble(int col) { // col 번 열에 구슬 던지기 -> 깨진 곳은 0으로 바꾸기

        Deque<int[]> queue = new ArrayDeque<>(); // 같이 터지는 벽돌 저장용
        int[] pop; // 큐에서 뺀 배열
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        int level;
        int x, y, xx, yy;

        for (int i = 0; i < H; i++) {
            if (copyBrick[i][col] != 0) {
                queue.add(new int[]{i, col});
                break;
            }
        } // 구슬을 맞는 칸 찾기

        while (!queue.isEmpty()) { // 벽돌이 연쇄적으로 모두 깨질 때 까지
            pop = queue.removeFirst();
            x = pop[0];
            y = pop[1];
            level = copyBrick[x][y]; // 벽돌에 쓰인 숫자
            copyBrick[x][y]=0;

            if (level != 1) { // 다른 칸에도 영향을 준다
                level -= 1;
                for (int t = 1; t <= level; t++) { //
                    for (int i = 0; i < 4; i++) {
                        xx = x + t * dx[i];
                        yy = y + t * dy[i];

                        if (xx < 0 || xx >= H || yy < 0 || yy >= W) continue;
                        if (copyBrick[xx][yy] == 0) continue;

                        queue.addLast(new int[]{xx, yy});
                    }
                }
            }
        }
    }

    static void removeBrick() { // 열을 하나씩 보면서 깨진 벽돌 치우기

        List<Integer> save = new ArrayList<>();

        for (int j = 0; j < W; j++) {  // 모든 열 확인

            save.clear();

            for (int i = H - 1; i >= 0; i--) { // 해당 열의 0 이 아닌 숫자 담기 ( 아래에서부터 담기)
                if (copyBrick[i][j] != 0) {
                    save.add(copyBrick[i][j]);
                }
            }

            int idx = 0;
            for (int i = H - 1; i >= 0; i--) { // 아래부터 넣어주기
                if (idx == save.size()) {
                    copyBrick[i][j] = 0;
                } else {
                    copyBrick[i][j] = save.get(idx);
                    idx++;
                }
            }
        } // for
    }

    static void countRemain(){
        int cnt=0;
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                if(copyBrick[i][j]!=0) cnt++;
            }
        }

        if(cnt<Min){
            Min=cnt;
        }
    }
}
