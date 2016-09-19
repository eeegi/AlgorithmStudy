package trianglepath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TrianglePathLKM {

	private static String mPath = "src/trianglepath/InputData.txt";
	private static int mTestCount;
	private static int[][][] mCache;
	private static final int MAX_NUMBER = 1000;
	
	private static int[][] mCache2;
	
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mPath))));
			mTestCount = Integer.valueOf(br.readLine().trim());
			for (int i = 0 ; i < mTestCount ; i++) {
				int triangleLength = Integer.valueOf(br.readLine().trim());
				int[][] triangle = new int[triangleLength][triangleLength];
				for (int j = 0; j < triangleLength ; j++) {
					String[] stringArray = br.readLine().trim().split(" ");
					for (int k = 0; k < stringArray.length ; k++) {
						triangle[j][k] = Integer.valueOf(stringArray[k]);
					}
//					System.out.println(Arrays.toString(triangle[j]));
				}
				System.out.println(path1(0,0,0,triangleLength, triangle));
				System.out.println(path2(0,0,triangleLength, triangle));
				mCache = null;
				mCache2 = null;
			}
		} catch (Exception e) {
			e.printStackTrace();;
		}
		
	}
	
	static int path1(int y, int x, int sum, int triangleLength, int[][] triangle) {
		int n = triangleLength;
		if (mCache == null) {
			mCache = new int[n][n][1000 * n +1];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n ; j++) {
					Arrays.fill(mCache[i][j], -1);
				}
			}
		}
		
		// base case
		if (y == n-1) {
			return sum + triangle[y][x];
		}
		int ret = mCache[y][x][sum];
		if (ret != -1) {
			return ret;
		}
		sum += triangle[y][x];
		ret = Math.max(path1(y+1, x, sum, n, triangle), path1(y+1, x+1, sum, n, triangle));
		mCache[y][x][sum] = ret;
		return ret;
	}
	
	static int path2(int y, int x, int triangleLength, int[][] triangle) {
		int n = triangleLength;
		if (mCache2 == null) {
			mCache2 = new int[n][n];
			for (int i = 0; i < n; i++) {
				Arrays.fill(mCache2[i], -1);
			}
		}
		
		if (y == n - 1) {
//			System.out.println(x + ", " + y);
//			System.out.println(triangle[y][x]);
			return triangle[y][x];
		}
		int ret = mCache2[y][x];
		if (ret != -1) {
			return ret;
		}
		ret = triangle[y][x] + Math.max(path2(y+1, x, n, triangle), path2(y+1, x+1, n, triangle));
		mCache2[y][x] = ret;
		return ret;
	}
	
}
