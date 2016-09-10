package quadtree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QuadTreeLKM {
    class Quad{
        char value = NONE;
        Quad[] childs = new Quad[4];
    }
    static String mPath = "src/quadtree/";
    static int mTestCount = -1;
    static final char WHITE = 'w';
    static final char BLACK = 'b';
    static final char NONE = 'n';
    static final char NOT_SAME_COLOR = 'x';
    static Integer mCount = 0;
    static ArrayList<String> mArrayList = new ArrayList<String>();
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mPath + "InputData.txt"))));
            mTestCount = Integer.valueOf(br.readLine());
            for (int i = 0; i < mTestCount ; i++) {
                mArrayList.add(br.readLine().trim().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Quad quad = null;
        QuadTreeLKM qt = new QuadTreeLKM();
        for (String str : mArrayList) {
            mCount = 0;
            System.out.println(str);
            quad = qt.decompress(str, mCount);
            Quad reversedQuad = qt.reverse(quad);
            String result = qt.compress(reversedQuad);
            System.out.println("result : " + result);
        }
        
    }

    private String compress(Quad quad) {
        String result = "";
        char value = quad.value;
        result += value; // check point
        if (value == NOT_SAME_COLOR) {
            for (int i = 0; i < quad.childs.length ; i++) {
                result += compress(quad.childs[i]);
            }
        }
        return result;
    }

    private Quad reverse(Quad quad) {
        if (quad.value != NOT_SAME_COLOR) {
            return quad;
        } else {
            Quad q = new Quad();
            q.value = quad.value;
            q.childs[0] = quad.childs[2];
            q.childs[1] = quad.childs[3];
            q.childs[2] = quad.childs[0];
            q.childs[3] = quad.childs[1];
    
            // 하위 child를 다시 뒤집어 준다.
            for (int i = 0 ; i < q.childs.length ; i++) {
                q.childs[i] = reverse(q.childs[i]);
            }
            return q;
        }
    }

    private Quad decompress(String str, int count) {
        Quad quad = new Quad();

        if(str.charAt(count) == WHITE) {
            quad.value = WHITE;
            return quad;
        }
        if (str.charAt(count) == BLACK) {
            quad.value = BLACK;
            return quad;
        }
        if (str.charAt(count) == NOT_SAME_COLOR) {
            quad.value = NOT_SAME_COLOR;
            quad.childs = new Quad[4];
            for (int i = 0 ; i < quad.childs.length ; i++) {
                quad.childs[i] = decompress(str, ++mCount);
            }
        }
        
        return quad;
    }
}
