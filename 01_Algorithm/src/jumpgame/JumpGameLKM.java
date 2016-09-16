package jumpgame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class JumpGameLKM {
	// 점화식 : 재귀적으로 정의되는 수학적 함수
	private static String mPath = "src/jumpgame/InputData.txt";
	private static int mTestCount = 0;
	private static int [][] mCache = null;
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mPath))));
			mTestCount = Integer.valueOf(br.readLine().trim());
			for (int i = 0; i < mTestCount ; i++) {
				int squreLine = Integer.valueOf(br.readLine().trim());
				int[][] readSqureNumber = new int[squreLine][squreLine];
				for (int j = 0; j < squreLine ; j++) {
					String[] stringNum = br.readLine().trim().split(" ");
//					System.out.println(Arrays.toString(stringNum));
					for (int k = 0; k < stringNum.length ; k++) {
						readSqureNumber[j][k] = Integer.valueOf(stringNum[k]); 
					}
				}
				
				long startTime = System.nanoTime();
				if (jump(0,0, squreLine, readSqureNumber)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
				System.out.println((System.nanoTime() - startTime));
				
				startTime = System.nanoTime();
				if (jump2(0, 0, squreLine, readSqureNumber) == 1) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
				System.out.println((System.nanoTime() - startTime));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Solve of recursive
	static boolean jump(int y, int x, int squreLength, int[][] board) {
		int n = squreLength;
		// base case
		// 1) Out of game size
		if (y >= n || x >= n) {
			return false;
		}
		// 2) Reach the end of game
		if (x == n -1 && y == n-1) {
			return true;
		}
		
		int jumpSize = board[y][x];
		return jump(y + jumpSize, x, n, board) || jump(y, x + jumpSize, n, board);
	}
	
	// Solve of dynamic programming
	static int jump2(int y, int x, int squareLength, int[][] board) {
		if (mCache == null) {
			mCache = new int[squareLength][squareLength];
			for (int i = 0; i < mCache.length; i++) {
				Arrays.fill(mCache[i], -1);
			}
		}
		int n = squareLength;
		// base case
		// 1) Out of game size
		if (y >= n || x >= n) {
			return 0;
		}
		// 2) Reach the end of game
		if (y == n-1 && x == n-1) {
			return 1;
		}
		
		if (mCache[y][x] != -1) {
			return mCache[y][x];
		}
		int jumpSize = board[y][x];
		int ret = jump2(y+jumpSize, x, n, board) + jump2(y, x+jumpSize, n, board);
		return ret;
	}
	
	// n개의 서로 다는 원소 중에서 r개의 원소를 순서 없이 골라내는 방법의 수
	// 재귀 호출을 이용한 이항 계수의 계산
	int bino(int n, int r) {
		// base case
		// n=r(모든 원소를 다 고르는 경우)
		// r=0(고를 원소가 없는 경우)
		if (r == 0 || n == r) {
			return 1;
		}
		return bino(n-1, r-1) + bino(n-1, r);
	}
	
	int bino2(int n, int r) {
		int[][] cache = new int[30][30];
		for (int i= 0; i < cache.length; i++) {
			for (int j=0; j < cache[i].length; j++) {
				cache[i][j]= -1;
			}
		}
		
		for (int i = 0; i < cache.length; i++) {
			Arrays.fill(cache[i], -1);
		}
		if (r == 0 || n == r) {
			return 1;
		}
		if(cache[n][r] != -1) {
			return cache[n][r];
		}
		return cache[n][r] = (bino2(n-1, r-1) + bino2(n-1, r));
	}
}
