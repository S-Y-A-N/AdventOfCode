import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day4_1 {
    public static void main(String[] args) {
        Scanner input;

        try {
            input = new Scanner(new File("other_files/input4.txt"));

            ArrayList<Integer> allPoints = new ArrayList<>();

            while(input.hasNextLine()) {
                String line = input.nextLine();

                // win list
                ArrayList<String> winList = new ArrayList<>(Arrays.asList(line.split("(Card\\s+\\d+|\\|(.*)|\\s|:)")));
                winList.removeAll(Arrays.asList("", null));

                // num list
                ArrayList<String> numList = new ArrayList<>(Arrays.asList(line.split("(Card\\s\\d|(.*)\\||\\s|:)")));
                numList.removeAll(Arrays.asList("", null));

                // counting points
                int points = 0, count = 0;
                for (int i = 0; i < winList.size(); i++)
                    if (numList.contains(winList.get(i)))
                        points = (int)Math.pow(2, count++);

                // adding to all points list
                allPoints.add(points);
            }

            // sum of all points
            int sum = 0;
            for (int i = 0; i < allPoints.size(); i++)
                sum += allPoints.get(i);
            System.out.println(sum);
        }
        catch(IOException e) { e.getMessage(); }
    }
}