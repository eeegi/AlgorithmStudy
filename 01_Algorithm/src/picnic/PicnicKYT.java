package picnic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PicnicKYT {
	static boolean sIsFriend[][];

 
    public static void main(String args[]) throws NumberFormatException, IOException{
    	BufferedReader br = null;//new BufferedReader(new InputStreamReader(System.in));

		try {
			br = new BufferedReader(new FileReader("src/picnic/picnic.in"));
		} catch(FileNotFoundException e) {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
        int student = 0;
        int pair = 0;
        String s;
        int numberOfLine = 0;
        int testCase = 0;
        while ((s = br.readLine()) != null) {
            numberOfLine++;
            String ar[] = s.split(" ");
            if (numberOfLine == 1) {
                testCase = Integer.valueOf(ar[0]);
            } else if (numberOfLine % 2 == 0) {
                student = Integer.valueOf(ar[0]);
                pair = Integer.valueOf(ar[1]);
                sIsFriend = new boolean[student][student];
        	} else {
                StringTokenizer st = new StringTokenizer(s);

                sIsFriend = new boolean[student][student];
                for(int i = 0; i < pair; i++){
                    String first = st.nextToken();
                    String second = st.nextToken();
//                    System.out.println("first : " + first);
//                    System.out.println("second : " + second);
                    int a = Integer.parseInt(first);
                    int b = Integer.parseInt(second);
                    if (!sIsFriend[a][b] && !sIsFriend[b][a]) {
                        sIsFriend[a][b] = true;
                        sIsFriend[b][a] = true;              
                    }
                }

                System.out.println( countParings(new boolean[student] , student));
        	}
        }
    }
 
    static int countParings(boolean taken[], int student){
        // 비어있는 사람 찾기
        int firstFree = -1;
        for (int i = 0; i < student; i++){
            if( !taken[i] ){
                firstFree = i;
                break;
            }
        }
         
        // 기저사례 : 모두 짝지었다면 끝
        if( firstFree == -1 ) {
            return 1;
        }
         
        int ret = 0;        
        for(int pair = firstFree + 1; pair < student; pair++){
            if( !taken[pair] && sIsFriend[firstFree][pair] ){
                taken[firstFree] = true;
                taken[pair] = true;
                 
                ret += countParings(taken, student);
                 
                taken[firstFree] = false;
                taken[pair] = false;
            }
        }
         
        return ret;
    }
}

