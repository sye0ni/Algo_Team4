package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 복잡하게 생각해서 긴 고민 후 답 참고함
 * 크게 F_ 가 있는 경우와 없는 경우로 나누고, 각각 가능한 경우를 잘 따지기  
 * 11516 kb 76 ms 
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st=new StringTokenizer(br.readLine()," ");
        int FF=Integer.parseInt(st.nextToken());
        int FS=Integer.parseInt(st.nextToken());
        int SF=Integer.parseInt(st.nextToken());
        int SS=Integer.parseInt(st.nextToken());
        int temp;
        
        if(FF==0 && FS==0) { // FF 와 FS 가 없음 
        	temp=SF>0 ? 1:0;
        	temp+=SS;
        } // SS , SF 만 존재하는 경우 -> SS 는 언제나 가능, SF 는 맨 마지막에 한 번만 추가 
        
        else { // FF 혹은 FS 존재 
        	
        	if(FF==0 && FS!=0) {
        		temp=SS; // FS 만 있으면 전체 재생 가능 
        	
        		if(FS > SF) { // FS -> SF -> FS ... 언제나 FS가 앞에서 시작하므로 
        			temp+= 2*SF + 1;
        		}
        		
        		else { 
        			temp+= 2*FS;
        		}
        	}
        	
        	else if(FF!=0 && FS==0) { // FF 만 재생 가능 
        		temp=FF;
        	}
        	
        	else { // FF, FS 모두 존재 
        		
        		temp=FF+SS; // 항상 재생 가능 
        		
        		// 남은 조건은 FF=0 이고 FS!=0 일때와 동일해진다 
        		if(FS > SF) {
        			temp+= 2*SF + 1;
        		}
        		else { 
        			temp+= 2*FS;
        		}
        	}
        }
        System.out.println(temp);

    }
}
