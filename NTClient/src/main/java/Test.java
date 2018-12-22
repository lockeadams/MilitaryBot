import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import org.apache.commons.lang3.StringUtils;
import java.lang.Object;

public class Test {
    public void Test(){

    }

    public void run() {
        String data = "";
        char key = 'n';
        int slashCount = 0;

        System.out.println("enter data");
        Scanner in = new Scanner(System.in);

        if(in.hasNextLine()) {
            data = in.nextLine();
            key = data.charAt(data.length() - 1);
            System.out.println("keysting: " + key);
            slashCount = countChar(data, '/');
            System.out.println("slashcount: " + slashCount);
        }

        //check for protocol: <x/y/z>
        if (key == '>' && data.contains("/") && 5 < data.length() && data.length() < 17 && data.contains("<") && slashCount == 2) {
            System.out.println(data);

            //get indexes to split data into 3 values
            int ind1 = data.indexOf('/', 0);
            int ind2 = data.indexOf('/', ind1 + 1);
            int ind3 = data.indexOf('>');
            int startind = data.indexOf('<');

            //use indexes to split data
            String xString = data.substring(startind + 1, ind1);
            String yString = data.substring(ind1 + 1, ind2);
            String zString = data.substring(ind2 + 1, ind3);

            //parse strings as ints
            int xVal = extractDigits(xString);
            int yVal = extractDigits(yString);
            int zVal = extractDigits(zString);

            //sysout
            System.out.println("xValue: " + xVal);
            System.out.println("yValue: " + yVal);
            System.out.println("zValue: " + zVal);
        } else {
            System.out.println("error: incorrect data");
        }

        in.close();
    } 

    int countChar(String str, char c) {
        int count = 0;
        for(int i=0; i < str.length(); i++) {
            if(str.charAt(i) == c) count++;
        }
        return count;
    }

    public int extractDigits(String src) {
        StringBuilder builder = new StringBuilder();
        boolean neg = false;
        if(src.charAt(0) == '-') neg = true;
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (Character.isDigit(c)) {
                builder.append(c);
            }
        }
        int output = (neg) ? -Integer.parseInt(builder.toString()) : Integer.parseInt(builder.toString());
        return output;
    }
}