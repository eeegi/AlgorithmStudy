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

public class JumpGameKYT {
	private static int [][] mCache = null;
//	private static int [][] board;
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
			int board[][] = new int[n][n];
			for (int i = 0; i < n; i++) {
				String str[] = br.readLine().split(" ");
				
				for (int j = 0; j < str.length; j++) {
					board[i][j] = Integer.parseInt(str[j]);
				}
			}
			
			System.out.println(jump(0, 0, board));
		}
	}
	
	static boolean jump(int y, int x, int[][] board) {
		if (y >= n || x >= n) {
			return false;
		}
		
		if (x == n -1 && y == n-1) {
			return true;
		}
		
		int jumpSize = board[y][x];
		return jump(y + jumpSize, x, board) || jump(y, x + jumpSize, board);
	}

}
