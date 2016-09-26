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
    	// ù��° �� : Test Case�� �� C
        C = Integer.parseInt(br.readLine());
        // LinkedList ����
        res = new LinkedList[C];
        
        for (int t = 0; t < C; t++) {
        	// �ι�° �� : Wildcard pattern W
            W = br.readLine();
            
            // ����° �� : ���ϸ��� �� n
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
        
        // �־��� ���Ͽ� �����Ǵ� ���ϵ��� �̸��� �� �ٿ� �ϳ��� ���ĺ� ������� ���
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
                    // wildcard �� �������� ���ڿ� ��Ī�ǹǷ� ���� wildcard�� �����ų� ������ ���� ���ö����� List�� ����
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
