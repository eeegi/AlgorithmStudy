package quadtree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuadTreeKYT {

	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader("src/quadtree/InputData.txt"));
		} catch(FileNotFoundException e) {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		int testCase = Integer.parseInt(br.readLine().trim());
		while (testCase-- > 0) {
			String quadTree = new String(br.readLine());
			System.out.println(reverse(quadTree));
		}
	}
	
	public static String reverse(String quadTree){
        if( quadTree.charAt(0) != 'x' ) {
            return quadTree.charAt(0) + "";
        }
        
        String answer = "x";
        // reverseQuadTree
        String rqt[] = new String[4];
         
        // Left Top
        int beginIndex = 1;
        rqt[0] = reverse(quadTree.substring(beginIndex));
        // Right Top
        beginIndex += rqt[0].length();
        rqt[1] = reverse(quadTree.substring(beginIndex));
        // Left Bottom
        beginIndex += rqt[1].length();
        rqt[2] = reverse(quadTree.substring(beginIndex));
        // Right Bottom
        beginIndex += rqt[2].length();
        rqt[3] = reverse(quadTree.substring(beginIndex));
        
        //0 1 ---reverse---> 2 3
        //2 3 ---reverse---> 0 1
        
        answer += rqt[2] + rqt[3] + rqt[0] + rqt[1];
        return answer;
    }
	
}
