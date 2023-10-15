import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 19552 kb, 452 ms 
 *
 * 조합을 사용한 완탐
 * 나는 입력값을 하나씩 확인하며 가르쳐야하는 문자 리스트를 뽑은 뒤, 거기서 조합으로 선택하는 코드를 작성했는데
 * 다른 사람들의 풀이를 보니 그냥 a-z 까지 모두 뽑아보며 확인 -> 이게 더 간단하고 시간도 짧다 ㅠㅠ 
 *
 * "anta", "tica" 는 항상 포함되는 문자열이므로 antic 중 한 문자라도 가르칠 수 없다면 어떤 단어도 가르칠 수 없음 -> K<5 인 경우에는 항상 0 출력
 * 추가로, 등장하는 모든 문자를 가르켜도 K가 더 크다면 N 출력
 */
public class Main {

    static boolean[] alphabet; // a ~ z 까지 포함되어있는지 저장
    static int N,K,types; // N,K 는 입력값 types 는 문자 종류 수
    static int Max; // 정답
    static List<Character> chars; // antic 빼고 포함된 문자 저장
    static String[] str; // 입력값 저장
    static List<Integer> comb; // 조합으로 선택된 값 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        alphabet=new boolean[26]; // 해당 알파벳을 사용할 수 있는지 없는지 판단하는 용도
        st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        Max=0;
        types=0; // 모든 단어에 나오는 알파벳 종류 저장 ->  a,n,t,i,c 제외
        str=new String[N];
        chars=new ArrayList<>(); // 등장하는 알파벳 저장용
        comb=new ArrayList<>(); // 조합으로 뽑은 숫자 저장

        initAlphabet(); // antic true 처리

        for(int i=0;i<N;i++){
            str[i]=br.readLine();
            str[i]=str[i].substring(4,str[i].length()-4);
            countAlphabet(str[i]); // 등장하는 알파벳 종류 세기
        }

        if(K>=5) {
            if(K>types+5) Max=N; // 모든 문자를 가리켜도 K가 더 크다면

            else{
                combination(0); // K - 5 개 뽑기
            }
        }

        System.out.println(Max);

    }

    static void initAlphabet(){ // antic 포함 처리
        Arrays.fill(alphabet,false);
        // antic 담기
        alphabet['a'-'a']=true;
        alphabet['n'-'a']=true;
        alphabet['t'-'a']=true;
        alphabet['i'-'a']=true;
        alphabet['c'-'a']=true;
    }
    static void countAlphabet(String str){ // 문자열에 포함된 새로운 문자 개수 세기
        for(int i=0;i<str.length();i++){
            if(!alphabet[str.charAt(i)-'a']){ // 아직 추가되지 않은 문자라면
                alphabet[str.charAt(i)-'a']=true;
                types++;
                chars.add(str.charAt(i));
            }
        }
    }

    static void combination(int cnt){ // 문자 인덱스 뽑기

        if(cnt==K-5){ // 문자열 하나하나 확인
            initAlphabet(); // antic 을 추가하기

            // 뽑은 문자들 true 로 만들기 !
            for(int i=0;i<comb.size();i++){
                alphabet[(char)chars.get(comb.get(i))-'a']=true;
            }

            int check=0,count=0;

            for(int i=0;i<str.length;i++){ // 모든 단어 확인
                count=0;
                for(int j=0;j<str[i].length();j++){ // 이 단어가 가르칠 수 있는 단어인지 확인
                    if(!alphabet[str[i].charAt(j)-'a']){
                        break;
                    }
                    count++;
                }
                if(count==str[i].length()) { // 가르칠 수 있는 단어
                    check++;
                }
            }

            if(check>Max) {
                Max=check;
            }

            return;

        }

        for(int i=cnt;i<chars.size();i++){ // 조합
            if(comb.size()>0 && comb.get(cnt-1)>=i) continue;
            comb.add(i);
            combination(cnt+1);
            comb.remove(comb.size()-1);
        }
    }

}
