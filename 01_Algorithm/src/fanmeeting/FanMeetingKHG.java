package fanmeeting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class FanMeetingKHG {

	private static String mPath = "src/InputData.txt";
	private static int mTestCount = 0;
	private static int mFansCount;
	private static String mFansGender;
	private static int[] mFansGenderArr;
	private static int mMembersCount;
	private static String mMembersGender;
	private static int[] mMembersGenderArr;
	
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mPath))));
			mTestCount = Integer.valueOf(br.readLine().trim());
			for(int i = 0 ; i < mTestCount ; i++) {
				mMembersGender = br.readLine().trim();
				mMembersCount = mMembersGender.length();
				mMembersGenderArr = new int[mMembersCount];
				for(int j=0;j<mMembersCount;j++) {
					if(mMembersGender.charAt(j) == 'F') {
						mMembersGenderArr[j] = 0;
					} else {
						mMembersGenderArr[j] = 1;
					}
				}
				
				mFansGender = br.readLine().trim();
				mFansCount = mFansGender.length();
				mFansGenderArr = new int[mFansCount];
				for(int j=0;j<mFansCount;j++) {
					if(mFansGender.charAt(j) == 'F') {
						mFansGenderArr[j] = 0;
					} else {
						mFansGenderArr[j] = 1;
					}
				}
				
				
				int result = solve();
				System.out.println("result:"+result);
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int solve() {
		int hug = 0;
		for(int i=0;i<=mFansCount-mMembersCount;i++) {
			boolean isHug = true;
			for(int j=0;j<mMembersCount;j++) {
				if(!getHug(j, i+j)) {
					isHug = false;
					break;
				}
			}
			if(isHug) {
				hug++;
			}
			
		}
		
		
		
		return hug;
			
	}
	
	public static boolean getHug(int memberIndex, int fanIndex) {
		if(mFansGenderArr[fanIndex] * mMembersGenderArr[memberIndex] == 1) {
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
//	public static int[] getKaratsuba(int[] a, int[] b) {
//		int sizeA = a.length;
//		int sizeB = b.length;
//		if(sizeA < sizeB) {
//			return getKartsuba(b,a);
//		}
//		if(sizeA == 0 || sizeB ==0) {
//			return new int[0];
//		}
//		int resultCount = a.length+b.length-1;
//		int half = a.length/2;
//		int[] a0 = new int[half];
//		int[] a1 = new int[a.length-half];
//		System.arraycopy(a, 0, a0, 0, half);
//		System.arraycopy(a, half, a1, 0, a1.length);
//		int[] b0 = new int[Math.min(b.length, half)];
//		System.arraycopy(b, 0, b0, 0, Math.min(b.length, half));
//		int [] b1 = (b.length > half)? new int[b.length-half] : new int[0];
//		if(b.length>half) {
//			System.arraycopy(b, half, b1, 0, b1.length);
//		}
//		int[] z2 = getKaratsuba(a1, b1);
//		int[] z0 = getKaratsuba(a0, b0);
//		addTo(a0,a1,0);
//		addTo(b0,b1,0);
//		int[] z1 = getKaratsuba(a0, b0);
//		subFrom(z1, z0);
//		subFrom(z1, z2);
//		int[] resultArr = new
//	}
//	
//	public static void addTo(int[] a, int b[], int k) {
//		for(int i=0;i<a.length;i++) {
//			a[i] = (int)(a[i]+(b[i]*Math.pow(10,k)));
//		}
//	}
//	public static void subFrom(int[] a, int[] b) {
//		for(int i=0;i<a.length;i++) {
//			a[i] -= b[i];
//		}
//	}
	
	
	
	

}
