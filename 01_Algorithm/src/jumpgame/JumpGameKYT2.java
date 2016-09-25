package jumpgame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JumpGameKYT2 {
	private static int [][] sCache;
	private static int [][] sBoard;
	private static int n;
    public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("src/jumpgame/InputData.txt"));
		} catch(FileNotFoundException e) {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		int testCase = Integer.parseInt(br.readLine().trim());

		while (testCase-- > 0) {
			n = Integer.parseInt(br.readLine().trim());
			sBoard = new int[n][n];
			sCache = new int[n][n];
			for (int k = 0; k < n; k++) {
				String str[] = br.readLine().split(" ");

				for (int l = 0; l < str.length; l++) {
					sBoard[k][l] = Integer.parseInt(str[l]);
					sCache[k][l] = -1;
				}
			}
			System.out.println(jump2(0, 0) == 1 ? "Yes" : "No");
		}
	}

    static int jump2(int y, int x) {
		if (y >= n || x >= n) {
			return 0;
		}
		
		if (x == n - 1 && y == n - 1) {
			return 1;
		}
		int jumpSize = sBoard[y][x];
		int ret = sCache[y][x];
		if (ret != -1) {
			return ret;
		}		

		return ret = jump2(y + jumpSize, x) + jump2(y, x + jumpSize);
	}
}
