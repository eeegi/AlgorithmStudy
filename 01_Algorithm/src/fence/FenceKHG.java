package example_fence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class FenceKHG {
	private static String mPath = "src/InputData.txt";
	private static int mTestCount = 0;
	private static int[] mFenceHeight;
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mPath))));
			mTestCount = Integer.valueOf(br.readLine().trim());
			for(int i = 0 ; i < mTestCount ; i++) {
				int fenceCount = Integer.valueOf(br.readLine().trim());
				mFenceHeight = new int[fenceCount];
				String[] heightArr = br.readLine().trim().split(" ");
				for(int j=0;j<heightArr.length;j++) {
					mFenceHeight[j] = Integer.valueOf(heightArr[j]);
				}
				int result = solve(0,fenceCount-1);
				System.out.println("result:"+result);
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int solve(int left, int right) {
		if(left == right) {
			return mFenceHeight[left];
		}
		int mid = (left+right)/2;
		int ret = Math.max(solve(left,mid), solve(mid+1,right));
		int lo = mid;
		int hi = mid+1;
		int height = Math.min(mFenceHeight[lo], mFenceHeight[hi]);
		ret = Math.max(ret,  height*2);
		while(left<lo || hi<right) {
			if(hi<right && (lo == left || mFenceHeight[lo-1] < mFenceHeight[hi+1])) {
				hi++;
				height = Math.min(height, mFenceHeight[hi]);
			} else {
				lo--;
				height = Math.min(height,  mFenceHeight[lo]);
			}
			ret = Math.max(ret,  height*(hi-lo+1));
		}
		return ret;
		
	}

}
