import java.util.*;
import java.io.*;
import java.util.regex.*;

public class Day2_1 {
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
                Matcher matcherGame = gameID.matcher(line);
                boolean possible = true;
                while(matchNum.find() && matchColor.find()) {
                    int n = Integer.parseInt(matchNum.group().trim());
                    String c = matchColor.group();
                    System.out.println(c);
                    System.out.println(n);
                    if((c.equals("red") && n > 12) || (c.equals("green") && n > 13) || (c.equals("blue") && n > 14))
                        possible = false;
                }
                System.out.println(possible);
                if(possible && matcherGame.find()) {
                    sum += Integer.parseInt(matcherGame.group());
                    System.out.println(matcherGame.group());
                }
                System.out.println();
            }
            System.out.println("\nsum =\n"+sum);
        }
        catch(IOException e) {
            e.getMessage();
        }
    }
}
