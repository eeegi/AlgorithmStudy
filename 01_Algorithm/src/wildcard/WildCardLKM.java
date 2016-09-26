package wildcard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class WildCardLKM {
	private static String mPath = "src/wildcard/InputData.txt";
	private static int mTestCount = 0;
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mPath))));
			mTestCount = Integer.valueOf(br.readLine().trim());
			for (int test = 0; test < mTestCount ; test++) {
				String pattern = br.readLine().trim();
				int fileNameNumber = Integer.valueOf(br.readLine().trim());
				String[] fileNameArray = new String[fileNameNumber];
				for (int i = 0 ; i < fileNameNumber ; i++) {
					String fileName = br.readLine().trim();
					fileNameArray[i] = fileName;
				}
				checkPattern(pattern, fileNameArray);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void checkPattern(String pattern, String[] fileNameArray) {
		String wildCardType = null;
		String prefixPattern = null;
		String centerPattern = null;
		String subfixPattern = null;
		for (int i = 0; i < pattern.length() ; i++) {
			char patterChar = pattern.charAt(i);
			if (patterChar == '?') {
				wildCardType = "?";
				prefixPattern = pattern.substring(0, i);
				subfixPattern = pattern.substring(i+1, pattern.length());
			} else if (patterChar == '*') {
				wildCardType = "*";
				for (int j = i+1; j < pattern.length() ; j++) {
					if (pattern.charAt(j) == '*') {
						centerPattern = pattern.substring(i+1, j);
					}
				}
			}
		}
//		System.out.println("pattern : " + pattern);
//		System.out.println("wildCardType : " + wildCardType);
//		System.out.println("prefixPattern : " + prefixPattern);
//		System.out.println("centerPattern : " + centerPattern);
//		System.out.println("subfixPattern : " + subfixPattern);
		
		for (String str : fileNameArray) {
			if (wildCardType.equals("?")) {
				if (str.length() == pattern.length()) {
					String prefix = str.substring(0, prefixPattern.length());
					String subfix = str.substring(prefix.length()+1, str.length());
//					System.out.println("prefix : " + prefix);
//					System.out.println("subfix : " + subfix);
					if (prefixPattern.equals(prefix) && subfixPattern.equals(subfix)) {
						System.out.println(str);
					}
				}
				
			} else if (wildCardType.equals("*")) {
				if (str.contains(centerPattern)) {
					System.out.println(str);
				}
			}
		}
	}
}
