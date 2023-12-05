import java.util.*;

/*
* 숫자가 있는 점에서 bfs 하여 값을 구하고, X 로 초기화 하는 과정 반복 
* 
* 많이 풀어봤던 그래프 탐색 문제인데, 프로그래머스에서 풀어보려고 골랐다
* 생각보다 자동완성 없이 푸는데 익숙하지 않음 
* 에러도 안 뜨기 때문에 중간중간 헷갈리면 계속 디버깅을 해야한다 
*
* 어제는 stringbuilder 를 까먹고 오늘은 문자열 길이를 구할때 length가 아닌 length() 라는거 ㅜ_ㅜ, 리스트 정렬시 Collections.sort() 
*/

class Solution {

    List<Integer> days=new ArrayList<>(); 
    char[][] graph;
    int[] dx=new int[]{-1,1,0,0};
    int[] dy=new int[]{0,0,-1,1};
    int garo,sero;
    
    public int[] solution(String[] maps) {
        int[] answer; // 리턴은 배열로 
        
        graph=new char[maps.length][maps[0].length()];
        garo=maps[0].length();
        sero=maps.length;
        
        for(int i=0;i<maps.length;i++){
            for(int j=0;j<maps[i].length();j++){
                graph[i][j]=maps[i].charAt(j);
            }
        } // 그래프 생성 
        
        for(int i=0;i<maps.length;i++){
            for(int j=0;j<maps[i].length();j++){
                if(graph[i][j]!='X'){
                    bfs(i,j);
                }
            }
        } // 그래프 생성 
        
        if(days.size()==0) {
            answer=new int[1];
            answer[0]=-1;
        }
        else{
            answer=new int[days.size()];
            Collections.sort(days);
            for(int i=0;i<days.size();i++){
                answer[i]=days.get(i);
            }
        }
        
        return answer;
    }
    
    public void bfs(int x,int y){
        int sum=0;
        int currX,currY;
    
        
        Deque<int[]> queue=new ArrayDeque<>();
        queue.add(new int[]{x,y});
        sum+=graph[x][y]-'0'; 
        graph[x][y]='X';
        
        while(!queue.isEmpty()){
            int[] pop=queue.removeFirst();
            x=pop[0];
            y=pop[1];
            
            for(int i=0;i<4;i++){
                currX=x+dx[i];
                currY=y+dy[i];
                if(currX<0 || currX>=sero || currY<0 || currY>=garo) continue;
                if(graph[currX][currY]=='X') continue; 
                queue.add(new int[]{currX,currY});
                sum+=graph[currX][currY]-'0'; 
                graph[currX][currY]='X';
            }
        }
        
        days.add(sum);
    }
    
}
