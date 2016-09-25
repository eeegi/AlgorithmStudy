package example_jumpgame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JumpGameKHG {
	private static int [][] mCache = null;
	private static int n;
	private static int [][] mBoard;

	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("src/example_jumpgame/InputData.txt"));
		} catch(FileNotFoundException e) {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		int testCase = Integer.parseInt(br.readLine().trim());
		while (testCase-- > 0) {
			
			n = Integer.parseInt(br.readLine().trim());
			mBoard = new int[n][n];
			mCache = new int[n][n];
			clearCache();
			
			for (int i = 0; i < n; i++) {
				String str[] = br.readLine().split(" ");
				
				for (int j = 0; j < str.length; j++) {
					mBoard[i][j] = Integer.parseInt(str[j]);
				}
			}
			
			System.out.println(jump(0, 0));
		}
	}
	private static void clearCache() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				mCache[i][j] = -1;
			}
		}
	}
	
	private static int jump(int y, int x) {
		if(y>=n || x>=n) {
			return 0;
		}
		if(y==n-1 && x==n-1) {
			return 1;
		}
		int result = mCache[y][x];
		if(result != -1) {
			return result;
		}
		int jumpSize = mBoard[y][x];
		result = jump(y+jumpSize, x) + jump(y,x+jumpSize);
		if(result>0) {
			result = 1;
		} else {
			result =0;
		}
		mCache[y][x] = result;
		return result;
	}

}
