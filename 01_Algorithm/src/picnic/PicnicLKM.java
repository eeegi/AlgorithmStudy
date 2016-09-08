package picnic;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.omg.CORBA.Current;

public class PicnicLKM {
    class PicnicInfo {
        int mCaseNumber;
        int mStudentCount;
        int mFriendsCount;
        boolean[][] mFriendsData;
        boolean[]  mStudentData;
        
        @Override
        public String toString() {
            for (int i = 0 ; i < mFriendsData.length ; i++) {
                for (int j = 0; j < mFriendsData[i].length; j++) {
                    System.out.println("mFriendsData" + "[" + i + "][" + j + "] : " + mFriendsData[i][j]);
                }
            }

            return "PicnicInfo [mCaseNumber=" + mCaseNumber + ", mStudentCount=" + mStudentCount + ", mFriendsCount="
                    + mFriendsCount + ", mStudentData=" + Arrays.toString(mStudentData) + "]";
        }
    }

    private String mPath = "src/picnic/";
    public int mTestCount = -1;
    static public ArrayList<PicnicInfo> mPicnicInfoArray  = new ArrayList<PicnicInfo>();
    public static void main(String[] args) {
    	PicnicLKM co = new PicnicLKM();
        co.readInputFile();
        for (PicnicInfo picnicInfo : mPicnicInfoArray) {
            int pairCount = co.countPair(picnicInfo);
            System.out.println(pairCount);
        }
    }

    private int countPair(PicnicInfo picnicInfo) {
        int count = 0;
        int firstFree = getFirstFree(picnicInfo.mStudentData);
        if (firstFree == -1) {
            return 1;
        }
        for (int i = firstFree + 1; i < picnicInfo.mStudentCount; i++) {
            if (!picnicInfo.mStudentData[i] && picnicInfo.mFriendsData[firstFree][i]) {
                picnicInfo.mStudentData[i] = picnicInfo.mStudentData[firstFree] = true;
                count += countPair(picnicInfo);
                picnicInfo.mStudentData[i] = picnicInfo.mStudentData[firstFree] = false;
            }
        }
        return count;
    }
    
    private int getFirstFree(boolean[] student) {
        int firstFree = -1;
        for (int i = 0; i <student.length; i++) {
            if(!student[i]) {
                return i;
            }
        }
        return firstFree;
    }

    public void readInputFile() {
        BufferedReader br = null;
        String line = null;
        int readCount = 0;
        
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mPath+"/InputData.txt"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (!line.isEmpty()) {
            mTestCount = Integer.parseInt(line);
        }
        
        try {
            for (int i = 0; i < mTestCount; i++) {
                PicnicInfo pi = new PicnicInfo();
                if ((line = br.readLine()) == null) {
                    System.out.println("Check input data");
                    break;
                }
                String[] firstLine = line.trim().split(" ");
                
                if ((line = br.readLine()) == null) {
                    System.out.println("Check input data secnod line");
                    break;
                }
                String[] secondLine = line.trim().split(" ");
                
                readCount++;
                pi.mCaseNumber = readCount;
                pi.mStudentCount = Integer.valueOf(firstLine[0]);
                pi.mFriendsCount = Integer.valueOf(firstLine[1]);
                pi.mFriendsData = new boolean[pi.mStudentCount][pi.mStudentCount];
                pi.mStudentData = new boolean[pi.mStudentCount];
                
                for (int j = 0; j < secondLine.length ; j+=2) {
                    String firstFriendData = secondLine[j];
                    if(firstFriendData == null || firstFriendData.equals("")) {
                    	return;
                    }
                    int firstFriendNum = Integer.valueOf(firstFriendData);
                    String secondFriendData = secondLine[j+1];
                    int secondFriendNum = Integer.valueOf(secondFriendData);
                    if (firstFriendNum > secondFriendNum) {
                        pi.mFriendsData[secondFriendNum][firstFriendNum] = true;
                    } else {
                        pi.mFriendsData[firstFriendNum][secondFriendNum] = true;
                    }
                }
                // for debuging
                // System.out.println(pi.toString());
                mPicnicInfoArray.add(pi);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
