package wildcard;

import java.io.*;
import java.util.*;

public class WildCardKYT {
    static int testCase;
    static int N;
    static String wildStr;
    static String[] normalStrArr;
    static LinkedList<String>[] res;

    public static void main(String[] args) throws Exception {
    	BufferedReader br = null;
    	
    	try {
    		br = new BufferedReader(new FileReader("src/wildcard/InputData.txt"));
    	} catch(FileNotFoundException e) {
    		br = new BufferedReader(new InputStreamReader(System.in));
    	}
        testCase = Integer.parseInt(br.readLine());
        res = new LinkedList[testCase];
        
        for(int t = 0; t < testCase; t++) {
            wildStr = br.readLine();
            N = Integer.parseInt(br.readLine().trim());
            
            res[t] = new LinkedList<>();
            normalStrArr = new String[N];
            
            for(int n = 0; n < N; n++) {
                normalStrArr[n] = br.readLine();
                if(isMatched(wildStr.toCharArray(), 0,
                             normalStrArr[n].toCharArray(), 0))
                    res[t].add(normalStrArr[n]);
            }
        }
        
        for(LinkedList<String> resRow : res) {
            Collections.sort(resRow);
            for(String r : resRow)
                System.out.println(r);
        }
    }
    
    static boolean isMatched(char[] wild, int i,
                             char[] normal, int j) {
        final int wildLen = wild.length;
        final int normalLen = normal.length;
        
        while(i < wildLen && j < normalLen) {
            int c = wild[i++];
            switch(c) {
                case '?' :
                    j++;
                    break;
                case '*' :
                    if(i == wildLen) {
                        j = normalLen;
                        break;
                    }
                    List<Character> token = new ArrayList<>();
                    while(i < wildLen
                            && wild[i] != '?'
                            && wild[i] != '*')
                        token.add(wild[i++]);
                    List<Integer> idxList = getIndex(normal, j, token);
                    for(int d = idxList.size() - 1; d >= 0; d--)
                        if(isMatched(wild, i, normal,
                                     idxList.get(d) + token.size()))
                            return true;
                    return false;
                default :
                    if(c == normal[j])
                        j++;
                    else
                        return false;
            }
        }
        
        return (i == wildLen - 1 && wild[i] == '*')
                || (i == wildLen && j == normalLen);
    }
    
    static List<Integer> getIndex(char[] normal, int j, 
                                  List<Character> token) {
        List<Integer> idxList = new ArrayList<>();
        LOOP :
        for(int k = j; k < normal.length - token.size() + 1; k++) {
            for(int m = 0; m < token.size(); m++)
                if(normal[k + m] != token.get(m))
                    continue LOOP;
            idxList.add(k);
        }
        return idxList;
    }
}
