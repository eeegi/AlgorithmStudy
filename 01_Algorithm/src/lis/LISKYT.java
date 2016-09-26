package lis;

import java.io.*;

public class LISKYT {

    static int C;
    static int N;
    static int[] res;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = null;
    	
    	try {
    		br = new BufferedReader(new FileReader("src/lis/InputData.txt"));
    	} catch(FileNotFoundException e) {
    		br = new BufferedReader(new InputStreamReader(System.in));
    	}
    	// 첫번째 줄 : Test Case의 수 C
        C = Integer.parseInt(br.readLine());
        res = new int[C];
        for (int c = 0; c < C; c++) {

            String[] tmp = br.readLine().split(" ");
            // 수열의 길이 N
            N = tmp.length;
            int[] input = new int[N];
            // Input 배열 생성
            for (int n = 0; n < N; n++) {
               input[n] = Integer.parseInt(tmp[n]);
            }
            res[c] = lis(input);
        }
        for (int r : res) {
            System.out.println(r);
        }
    }
    
    static int lis(int[] input) {
        int[] ret = new int[input.length];
        ret[0] = 1;
        
        int maxLen = Integer.MIN_VALUE;//겁나 작은 값
        
        for( int k = 1; k < input.length; k++) {//1~N까지 반복
            int max = Integer.MIN_VALUE;
            int iMax = Integer.MIN_VALUE;
            for (int j = 0; j < k; j++) {
                if (input[k] > input[j]//앞자리 숫자보다 큰 숫자를 만나면
                		&& ret[j] > max) {//지금껏 찾은 max보다 크면(처음엔 무조건 true)
                    max = ret[j];//처음엔 무조건 1
                    iMax = j;//max를 찾은 자리수
                }
            }
            ret[k] = (max == Integer.MIN_VALUE) ? 1 : ret[iMax] + 1;
            if (ret[k] > maxLen) {
                maxLen = ret[k]; // maxLen갱신
            }
        }
        return maxLen;
    }

}
