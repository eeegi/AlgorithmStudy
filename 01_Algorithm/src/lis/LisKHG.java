package example_lis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static String[] mNumbers;
	private static int mSize;
	private static int[] mResult;

	public static void main(String[] args)throws NumberFormatException, IOException {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader("src/example_lis/InputData.txt"));
		} catch (FileNotFoundException e) {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		int testCase = Integer.parseInt(br.readLine().trim());
		while (testCase-- > 0) {
			mNumbers = br.readLine().trim().split(" ");
			mSize = mNumbers.length;
			mResult = new int[mSize];
			for(int i=0;i<mSize;i++) {
				mResult[i] = -1;
			}
			System.out.println("mSize:"+mSize);
						
			System.out.println(getLength(0));
			
		}
		

	}
	
	
	
	
	private static int getLength(int pos) {
		int result = mResult[pos];
		if(result != -1) {
			return result;
		}
		result = 1;
		for(int i = pos+1;i<mSize;i++) {
			if(pos == 0|| Integer.parseInt(mNumbers[pos])<Integer.parseInt(mNumbers[i])) {
				result = Math.max(result,  getLength(i)+1);
			}
		}
		mResult[pos] = result;
		return result;
		
		
	}

}
