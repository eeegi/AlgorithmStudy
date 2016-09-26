package wildcard;

import java.io.*;
import java.util.*;

public class WildCardKYT {
    static int C;
    static int N;
    static String W;
    static String[] normalStrArr;
    static LinkedList<String>[] res;

    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = null;
    	
    	try {
    		br = new BufferedReader(new FileReader("src/wildcard/InputData.txt"));
    	} catch(FileNotFoundException e) {
    		br = new BufferedReader(new InputStreamReader(System.in));
    	}
    	// 첫번째 줄 : Test Case의 수 C
        C = Integer.parseInt(br.readLine());
        // LinkedList 생성
        res = new LinkedList[C];
        
        for (int t = 0; t < C; t++) {
        	// 두번째 줄 : Wildcard pattern W
            W = br.readLine();
            
            // 세번째 줄 : 파일명의 수 n
            N = Integer.parseInt(br.readLine().trim());
            
            res[t] = new LinkedList<>();
            normalStrArr = new String[N];
            
            // 
            for (int n = 0; n < N; n++) {
                normalStrArr[n] = br.readLine();
                if (isMatched(W.toCharArray(), 0, normalStrArr[n].toCharArray(), 0)) {
                	res[t].add(normalStrArr[n]);
                }
            }
        }
        
        // 주어진 패턴에 대응되는 파일들의 이름을 한 줄에 하나씩 알파벳 순서대로 출력
        for (LinkedList<String> resRow : res) {
            Collections.sort(resRow);
            for (String r : resRow) {
                System.out.println(r);
            }
        }
    }
    
    static boolean isMatched(char[] wildcard, int i, char[] normal, int j) {
        final int wildLen = wildcard.length;
        final int normalLen = normal.length;
        
        while(i < wildLen && j < normalLen) {
            int c = wildcard[i++];
            switch (c) {
                case '?' :
                    j++;
                    break;
                case '*' :
                    if(i == wildLen) {
                        j = normalLen;
                        break;
                    }
                    // wildcard 는 여러개의 문자와 매칭되므로 다음 wildcard가 나오거나 문자의 끝이 나올때까지 List로 저장
                    List<Character> token = new ArrayList<>();
                    while(i < wildLen
                            && wildcard[i] != '?'
                            && wildcard[i] != '*') {
                        token.add(wildcard[i++]);
                    }
                    
                    List<Integer> idxList = getIndex(normal, j, token);
                    for (int d = idxList.size() - 1; d >= 0; d--) {
                        return isMatched(wildcard, i, normal,
                                     idxList.get(d) + token.size());
                    }
                    return false;
                default :
                    if (c == normal[j]) {
                        j++;
                    } else {
                        return false;
                    }
                    break;
            }
        }
        
        return (i == wildLen - 1 && wildcard[i] == '*')
                || (i == wildLen && j == normalLen);
    }
    
    static List<Integer> getIndex(char[] normal, int j, List<Character> token) {
        List<Integer> idxList = new ArrayList<>();
        LOOP :
        for (int k = j; k < normal.length - token.size() + 1; k++) {
            for (int m = 0; m < token.size(); m++) {
                if (normal[k + m] != token.get(m)) {
                    continue LOOP;
                }
                idxList.add(k);
            }
            
        }
        return idxList;
    }
}
