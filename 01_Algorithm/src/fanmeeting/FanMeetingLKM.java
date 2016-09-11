package fanmeeting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FanMeetingLKM {
	private static int mTestCount = 0;
	private static String mPath = "src/fanmeeting/InputData.txt";
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mPath))));
			mTestCount = Integer.valueOf(br.readLine().trim());
		} catch (IOException e) {
			e.printStackTrace();
		}
		int[] result = new int[3 + 3 + 1];
		System.out.println(Arrays.toString(result));
		
	}
	
	private static int[] karasuba(int[] a, int[] b) {
		int sizeA = a.length;
		int sizeB = b.length;
		if (sizeA < sizeB) {
			return karasuba(b,a);
		}
		
		if (sizeA == 0 || sizeB == 0) {
			return new int[sizeA + sizeB];
		}
		
		if(sizeA <= 50) {
			return multiple(a, b); 
		}
		
		int halfA = sizeA / 2;
		
		int[] a0 = Arrays.copyOfRange(a, 0, halfA);
		int[] a1 = Arrays.copyOfRange(a, halfA, sizeA);
		
		int[] b0 = Arrays.copyOfRange(b, 0, Math.min(sizeB, halfA));
		int[] b1 = Arrays.copyOfRange(b, Math.min(sizeB, halfA), sizeB);
		
		int[] z2 = karasuba(a1, b1);
		int[] z0 = karasuba(a0, b0);
		
		a0 = add(a0, a1, 0);
		b0 = add(b0, b1, 0);
		
		int[] z1 = karasuba(a0, b0);
		z1 = sub(z1, z0);
		z1 = sub(z1, z2);
		
		int[] ret = new int[0];
		ret = add(ret, z0, 0);
		ret = add(ret, z1, halfA);
		ret = add(ret, z2, halfA + halfA);

		return ret;
	}
	
	private static int[] multiple (int[] first, int[] second) {
		int sizeFirst = first.length;
		int sizeSecond = second.length;
		int[] result = new int[sizeFirst + sizeSecond + 1];
		
		for (int i = 0; i < sizeFirst ; i++) {
			for (int j =0; j < sizeSecond ; j++) {
				// 시작은 첫번째 자리가 아니라 0번째 자리
				// 곱셈을 생각해 보면 result의 자리 i+j가 이해된다.
				result[i+j] += first[i] * second[j];
			}
		}
		System.out.println(Arrays.toString(result));
		result = normalize(result);
		System.out.println(Arrays.toString(result));
		return result;
	}

	private static int[] normalize(int[] num) {
		int sizeNum = num.length;
		for (int i = 0; i < sizeNum; i++) {
			if (num[i] < 0) {
				// 0보다 작으면 
				num[i+1] -= 1;
				num[i] += 10;
			} else {
				num[i+1] += num[i] / 10;
				num[i] %= 10;
			}
		}
		return num;
	}
	
	private static int[] sub(int[] a, int[] b) {
		int size = Math.max(a.length, b.length);
		int[] result = new int[size];
		for(int i = 0; i < a.length ; i++) {
			result[i] = a[i];
		}
		for (int j = 0; j < b.length; j++) {
			result[j] -= b[j];
		}
		result = normalize(result);
		return result;
	}
	
	private static int[] add(int[] a, int[] b, int k) {
		int size = Math.max(a.length, b.length + k) + 1;
		int[] result = new int[size];
		for (int i = 0; i < a.length ; i++) {
			result[i] = a[i];
		}
		for(int j=0;j < b.length; j++) {
			result[j + k] += b[j];
		}
		result = normalize(result);
		return result;
	}
}
