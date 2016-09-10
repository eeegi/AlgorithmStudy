package fence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class FenceLKM {
	class FenceInfo {
		int totalFence;
		int[] heights;
		@Override
		public String toString() {
			return "FenceInfo [totalFence=" + totalFence + ", heights=" + Arrays.toString(heights) + "]";
		}
		
	}
	private static String mPath = "src/fence/InputData.txt";
	private static int mTestCount = 0;
	private static ArrayList<FenceInfo> mAl = new ArrayList<FenceInfo>();
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mPath))));
			mTestCount = Integer.valueOf(br.readLine().trim());
			for(int i = 0 ; i < mTestCount ; i++) {
				FenceInfo fenceInfo = new FenceLKM().new FenceInfo();
				String firstLine = br.readLine().trim();
				String[] secondLine = br.readLine().trim().split(" ");
				fenceInfo.totalFence = Integer.valueOf(firstLine);
				fenceInfo.heights = new int[secondLine.length];
				for (int j = 0 ; j < secondLine.length ; j++) {
					fenceInfo.heights[j] = Integer.valueOf(secondLine[j]);
				}
				mAl.add(fenceInfo);
				System.out.println(fenceInfo.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (FenceInfo fenceInfo : mAl) {
			int result = solve(fenceInfo);
			System.out.println(result);
		}
	}
	private static int solve(FenceInfo fenceInfo) {
		int area = 0;
		for (int left=0; left < fenceInfo.totalFence ;left++ ) {
			int minHeight = fenceInfo.heights[left];
			for (int right = left; right < fenceInfo.totalFence ; right++) {
				minHeight = Math.min(minHeight, fenceInfo.heights[right]);
				area = Math.max(area, (right - left + 1) * minHeight);
			}
		}
		return area;
	}
}
