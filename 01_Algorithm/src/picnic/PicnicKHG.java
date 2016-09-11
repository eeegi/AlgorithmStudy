package example_picnic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PicnicKHG {
	public static final String FILE_NAME= "test.txt";
	public static boolean PRINT_LOG = false;
	public static ArrayList<Integer>[] pairList;

	public static void main(String[] args) {
        
        String path = Paths.get(".").toAbsolutePath().normalize().toString();
	    try {
	      BufferedReader in = new BufferedReader(new FileReader(path+"/test/"+FILE_NAME));
	      int caseNum = Integer.valueOf(in.readLine());
	      for(int i=0;i<caseNum;i++) {
	    	  printLog("case["+i+"]=================================================");
	    	  String[] student = in.readLine().split(" ");
	    	  int studentNum = Integer.valueOf(student[0]);
	    	  int pairNum = Integer.valueOf(student[1]);
	    	  pairList = new ArrayList[studentNum];
	    	  for(int j=0;j<studentNum;j++) {
	    		  pairList[j] = new ArrayList<Integer>();
	    	  }
	    	  String[] pair = in.readLine().split(" ");
	    	  for(int j=0;j<pairNum;j++) {
	    		  int pair1 = Integer.valueOf(pair[2*j]);
	    		  int pair2 = Integer.valueOf(pair[2*j+1]);
	    		  pairList[pair1].add(pair2);
	    		  pairList[pair2].add(pair1);
	    		  
	    	  }
	    	  ArrayList<Integer> temp = new ArrayList<>();
	    	  int count = getPairCount(0, studentNum, temp, 0);
	    	  System.out.println("result:"+count);
	      }
	      in.close();
	    } catch (IOException e) {
	        System.err.println(e);
	        System.exit(1);
	    }

	}
	
	public static int getPairCount(int currentStudentNum, int totalStudentNum, ArrayList<Integer> alreadPairedList, int count) {
		if(alreadPairedList.size() == totalStudentNum) {
			printLog("All student paired. count up");
			return count+1;
		} else if(alreadPairedList.contains(currentStudentNum)) {
			printLog(currentStudentNum+" already paired. find next");
			return getPairCount(currentStudentNum+1, totalStudentNum, alreadPairedList, count);
		} else {
			
			for(int i=0;i<pairList[currentStudentNum].size();i++) {
				printLog("find "+currentStudentNum+" friend");
				int friend = pairList[currentStudentNum].get(i);
				ArrayList<Integer> list = new ArrayList<>();
				list.addAll(alreadPairedList);
				if(!list.contains(friend)) {
					printLog("paired. " +currentStudentNum+" and "+friend);
					list.add(currentStudentNum);
					list.add(friend);
					count = getPairCount(currentStudentNum+1, totalStudentNum, list, count); 
				} else {
					printLog(friend + " already paired. find next friend.");
				}
			}
			return count;
		}
	}
	
	public static void printLog(String log) {
		if(PRINT_LOG) {
			System.out.println(log);
		}
	}
	

}
