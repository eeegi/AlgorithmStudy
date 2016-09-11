package quadtree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class QuadTreeKHG {
	public static final String FILE_NAME= "test.txt";
	public static int index;
	
	public static void main(String[] args) {
		String path = Paths.get(".").toAbsolutePath().normalize().toString();
	    try {
	      BufferedReader in = new BufferedReader(new FileReader(path+"/test/"+FILE_NAME));
	      int caseNum = Integer.valueOf(in.readLine());
	      for(int i=0;i<caseNum;i++) {
	    	  String problem=in.readLine().trim();
	    	  
	    	  index = 0;
	        	String result = reverse(problem);
	    		System.out.println("result:"+result);
	      }
	      in.close();
	    } catch (IOException e) {
	        System.err.println(e);
	        System.exit(1);
	    }
	}
	
	public static String reverse(String problem) {
		char head = problem.charAt(index);
		index++;
		if(head == 'w' || head == 'b') {
			return new String(""+head);
		}
		String upperLeft = reverse(problem);
		String upperRight = reverse(problem);
		String lowerLeft = reverse(problem);
		String lowerRight = reverse(problem);
		return new String("x"+lowerLeft+lowerRight+upperLeft+upperRight);
		
	}

}
