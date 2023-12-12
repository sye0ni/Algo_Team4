import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11792 kb, 88 ms
 * 각 위치를 기준으로 왼쪽, 오른쪽을 탐색하며 각각 최대 높이를 찾고
 * 두 값 중 작은 값을 이용하면 현재 위치에 고이는 물의 양을 알 수 있다 !!
 * 크기가 크지 않기 때문에 이런 풀이가 가능했음
 */
public class BJ_14719_빗물_임승연 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int H,W;
        int[] height;

        st=new StringTokenizer(br.readLine());
        H=Integer.parseInt(st.nextToken());
        W=Integer.parseInt(st.nextToken());

        height=new int[W];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<W;i++){
            height[i]=Integer.parseInt(st.nextToken());
        }

        int sum=0;
        int left=0,right=0;

        for(int i=1;i<W-1;i++){
            left=-1;
            right=-1;
            for(int j=0;j<i;j++){
                if(height[j]>height[i]){
                    if(height[j]>left){
                        left=height[j];
                    }
                }
            } // 왼쪽 최댓값 찾기
            for(int j=i+1;j<W;j++){
                if(height[j]>height[i]){
                    if(height[j]>right){
                        right=height[j];
                    }
                }
            } // 오른쪽

            if(left==-1 || right==-1) continue; // 고이지 않는 경우
            sum+=(Math.min(left,right)-height[i]);
        }

        System.out.println(sum);
    }
}
