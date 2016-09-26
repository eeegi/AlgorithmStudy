package example_trianglepath;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int mHeight;
	private static int[][] mTriangle;
	private static int[][] mCache;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader("src/example_trianglepath/InputData.txt"));
		} catch (FileNotFoundException e) {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		int testCase = Integer.parseInt(br.readLine().trim());
		while (testCase-- > 0) {
			System.out.println("case start=======================================================================");
			mHeight = Integer.parseInt(br.readLine().trim());
			mTriangle = new int[mHeight][mHeight];
			clearCache();
			for(int i=0;i<mHeight;i++) {
				String[] stringArray = br.readLine().trim().split(" ");
				for (int j = 0; j < stringArray.length ; j++) {
					mTriangle[j][i] = Integer.valueOf(stringArray[j]);
				}
			}
			
			System.out.println(path2(0,0));
			
		}

	}
	
	private static void clearCache() {
		mCache = new int[mHeight][mHeight];
		for(int i=0;i<mHeight;i++) {
			for(int j=0;j<mHeight;j++) {
				mCache[i][j] = -1;
			}
		}
	}
	
	
	private static int path2(int x, int y) {
		if(y == mHeight-1) {
			System.out.println("x:"+x+", y:"+y);
			System.out.println("return triangle:"+mTriangle[x][y]);
			return mTriangle[x][y];
		}
		int result = mCache[x][y];
		if(result != -1) {
			System.out.println("x:"+x+", y:"+y);
			System.out.println("return cache:"+result);
			return result;
		}
		result = Math.max(path2(x,y+1), path2(x+1,y+1)) + mTriangle[x][y];
		mCache[x][y] = result;
		System.out.println("x:"+x+", y:"+y);
		System.out.println("return:"+result);
		return result;
	}

}
