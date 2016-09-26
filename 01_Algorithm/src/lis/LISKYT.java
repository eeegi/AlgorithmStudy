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
    	// ù��° �� : Test Case�� �� C
        C = Integer.parseInt(br.readLine());
        res = new int[C];
        for (int c = 0; c < C; c++) {

            String[] tmp = br.readLine().split(" ");
            // ������ ���� N
            N = tmp.length;
            int[] input = new int[N];
            // Input �迭 ����
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
        
        int maxLen = Integer.MIN_VALUE;//�̳� ���� ��
        
        for( int k = 1; k < input.length; k++) {//1~N���� �ݺ�
            int max = Integer.MIN_VALUE;
            int iMax = Integer.MIN_VALUE;
            for (int j = 0; j < k; j++) {
                if (input[k] > input[j]//���ڸ� ���ں��� ū ���ڸ� ������
                		&& ret[j] > max) {//���ݲ� ã�� max���� ũ��(ó���� ������ true)
                    max = ret[j];//ó���� ������ 1
                    iMax = j;//max�� ã�� �ڸ���
                }
            }
            ret[k] = (max == Integer.MIN_VALUE) ? 1 : ret[iMax] + 1;
            if (ret[k] > maxLen) {
                maxLen = ret[k]; // maxLen����
            }
        }
        return maxLen;
    }

}
