package example_wildcard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WildCardKHG {
	private static int[][] mCache;
	private static String mPattern;
	private static String mTarget;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader("src/example_wildcard/InputData.txt"));
		} catch (FileNotFoundException e) {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		int testCase = Integer.parseInt(br.readLine().trim());
		while (testCase-- > 0) {
			mPattern = br.readLine().trim();
			int stringNum = Integer.parseInt(br.readLine().trim());
			for(int i=0;i<stringNum;i++) {
				mTarget = br.readLine().trim();
				clearCache();
				if(isMath(0, 0) == 1) {
					System.out.println(mTarget);
				}
			}

		}

	}
	
	private static int isMath(int patternPos, int targetPos) {
		int result = mCache[patternPos][targetPos];
		int inPatternPos = patternPos;
		int inTargetPos = targetPos;
		if(result != -1) {
			return result;
		} else {
			result = 0;
		}
		while(inTargetPos<mTarget.length() && inPatternPos<mPattern.length() &&
				(mPattern.charAt(inPatternPos) == '?' || mPattern.charAt(inPatternPos) == mTarget.charAt(inTargetPos))) {
			inPatternPos++;
			inTargetPos++;
		}
		if(inPatternPos == mPattern.length()) {
			if(inTargetPos == mTarget.length()) {
				result = 1;
			} 
		} else {
			if(mPattern.charAt(inPatternPos)=='*') {
				for(int skip =0; skip+inTargetPos<=mTarget.length();skip++) {
					if(isMath(inPatternPos+1, inTargetPos+skip) == 1) {
						result = 1;
						break;
					}
				}
			}
		}
		
		mCache[patternPos][targetPos] = result;
		return result;
		
			
	}
	
	private static void clearCache() {
		mCache = new int[100][100];
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				mCache[i][j] = -1;
			}
		}
	}

}
