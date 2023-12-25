import java.util.*;
import java.io.*;
import java.util.regex.*;

public class Day1_2 {
    public static void main(String[] args) {
        Scanner input;
        String nFirst = "", nLast = "";
        try {
            input = new Scanner(new File("other_files/input1.txt"));
            int sum = 0;
            Pattern first = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine|[1-9])");
            Pattern last = Pattern.compile("(eno|owt|eerht|ruof|evif|xis|neves|thgie|enin|[1-9])");
            while(input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(line);
                Matcher firstDigit = first.matcher(line);
                Matcher lastDigit = last.matcher(new StringBuilder(line).reverse().toString());
                if(firstDigit.find() && lastDigit.find()) {
                    nFirst = firstDigit.group();
                    nLast = lastDigit.group();
                    if(nFirst.length() > 1)
                        nFirst = wordToNum(nFirst);
                    if(nLast.length() > 1)
                        nLast = reWordToNum(nLast);
                    String fullNum = nFirst.concat(nLast);
                    int num = Integer.parseInt(fullNum);
                    System.out.println(num);
                    sum += num;
                }
            }
            System.out.println("\nsum =\n"+sum);
        }
        catch(IOException e) {
            e.getMessage();
        }
    }

    public static String wordToNum(String num) {
        switch(num) {
            case "one":
                return "1";
            case "two":
                return "2";
            case "three":
                return "3";
            case "four":
                return "4";
            case "five":
                return "5";
            case "six":
                return "6";
            case "seven":
                return "7";
            case "eight":
                return "8";
            case "nine":
                return "9";
        }
        return "null";
    }

    public static String reWordToNum(String num) {
        switch(num) {
            case "eno":
                return "1";
            case "owt":
                return "2";
            case "eerht":
                return "3";
            case "ruof":
                return "4";
            case "evif":
                return "5";
            case "xis":
                return "6";
            case "neves":
                return "7";
            case "thgie":
                return "8";
            case "enin":
                return "9";
        }
        return "null";
    }
}
