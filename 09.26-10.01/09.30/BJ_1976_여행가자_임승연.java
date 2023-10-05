package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 아이디어 
 * union find 이용하여,같은 구역끼리 묶어주기 
 * 만약 다른 구역을 가고자하면 불가능! 
 *
 * 16164 kb, 136 ms
 */

public class Main {

	static int N;
	static int[] parents;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N=Integer.parseInt(br.readLine()); // 도시의 수
        parents=new int[N];
        int M=Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시 수
        int[] schedule=new int[M];
        
        for(int i=0;i<N;i++) parents[i]=i; 
        
        for(int i=0;i<N;i++) {
        	st=new StringTokenizer(br.readLine()," ");
        	for(int j=0;j<N;j++) {
        		if(Integer.parseInt(st.nextToken())==1) {
        			union(i,j);
        		} // 같은 땅끼리 연결하기 
        	}
        }
        
        // 여행 계획 저장 
        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<M;i++) {
        	schedule[i]=Integer.parseInt(st.nextToken())-1;
        }
        
        boolean flag=true;
        for(int i=0;i<M-1;i++) { 
        	if(find(schedule[i])!=find(schedule[i+1])) {
        		flag=false;
        		break;
        	} // 이동 불가능한 점이 하나라도 있다면 종료한다 
        }
        
        if(flag) System.out.println("YES");
        else System.out.println("NO");
    }
    
    static int find(int x) { // 루트 노드 찾기 
    	if(x==parents[x]) {
    		return x;
    	}
    	return parents[x]=find(parents[x]);
    }
    
    static void union(int x,int y) { // 같은 땅으로 만들기 
    	x=find(x);
    	y=find(y);
    	
    	if(x!=y) { // 큰 수를 작은 수에 붙이기 
    		if(x>y) {
    			parents[x]=y;
    		}
    		else {
    			parents[y]=x;
    		}
    	}
    }
}
