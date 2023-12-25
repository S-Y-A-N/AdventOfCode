import java.util.*;
import java.io.*;
import java.util.regex.*;

public class Day1_1 {
    public static void main(String[] args) {
        Scanner input;
        String nFirst = "", nLast = "";
        try {
            input = new Scanner(new File("other_files/input1.txt"));
            int sum = 0;
            Pattern first = Pattern.compile("[\\d]");
            Pattern last = Pattern.compile("(\\d)(?!.*\\d)");
            while(input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(line);
                Matcher firstDigit = first.matcher(line);
                Matcher lastDigit = last.matcher(line);
                if(firstDigit.find() && lastDigit.find()) {
                    nFirst = firstDigit.group();
                    nLast = lastDigit.group();
                    String fullNum = nFirst.concat(nLast);
                    int num = Integer.parseInt(fullNum);
                    System.out.println(num);
                    sum += num;
                }
            }
            System.out.println(sum);
        }
        catch(IOException e) {
            e.getMessage();
        }
    }
}
