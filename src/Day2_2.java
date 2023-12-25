import java.util.*;
import java.io.*;
import java.util.regex.*;

public class Day2_2 {
    public static void main(String[] args) {
        Scanner input;

        try {
            input = new Scanner(new File("other_files/input2.txt"));
            int sum = 0;
            Pattern gameID = Pattern.compile("\\d+");
            Pattern numbers = Pattern.compile("(|\\b\\s+)\\d+\\s+");
            Pattern colors = Pattern.compile("(red|green|blue)");
            while(input.hasNextLine()) {
                String line = input.nextLine();
                Matcher matchColor = colors.matcher(line);
                Matcher matchNum = numbers.matcher(line);
                int maxR = 0, maxG = 0, maxB = 0;
                while(matchNum.find() && matchColor.find()) {
                    int n = Integer.parseInt(matchNum.group().trim());
                    String c = matchColor.group();
                    System.out.println(c);
                    System.out.println(n);
                    if(c.equals("red") && n > maxR) maxR = n;
                    if(c.equals("green") && n > maxG) maxG = n;
                    if(c.equals("blue") && n > maxB) maxB = n;
                }
                int p = maxR*maxG*maxB;
                System.out.println(p);
                sum += p;
                System.out.println();
            }
            System.out.println("\nsum =\n"+sum);
        }
        catch(IOException e) {
            e.getMessage();
        }
    }
}

