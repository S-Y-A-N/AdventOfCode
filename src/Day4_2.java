import java.io.*;
import java.util.*;

public class Day4_2 {
    public static void main(String[] args) {


        try {
            String file = "other_files/input4.txt";
            Scanner input = new Scanner(new File(file));

            ArrayList<Integer> cardsCopies = new ArrayList<>(Collections.nCopies(countLines(file), 1));

            int l = 0;
            while(input.hasNextLine()) {
                String line = input.nextLine();

                // win list
                ArrayList<String> winList = new ArrayList<>(Arrays.asList(line.split("(Card\\s+\\d+|\\|(.*)|\\s|:)")));
                winList.removeAll(Arrays.asList("", null));

                // num list
                ArrayList<String> numList = new ArrayList<>(Arrays.asList(line.split("(Card\\s\\d|(.*)\\||\\s|:)")));
                numList.removeAll(Arrays.asList("", null));

                // counting number of wins
                int count = 0;
                for (int i = 0; i < winList.size(); i++)
                    if (numList.contains(winList.get(i)))
                        count++;

                // updating number of cards
                for (int i = l+1; i <= l+count; i++) {
                    int newCopies = cardsCopies.get(i) + cardsCopies.get(l);
                    cardsCopies.set(i, newCopies);
                }

                l++;
            }

            int sum = 0;
            for (int i = 0; i < cardsCopies.size(); i++)
                sum += cardsCopies.get(i);
            System.out.println(sum);

        }
        catch(IOException e) { e.getMessage(); }
    }

    public static int countLines(String file) {
        int lines = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.readLine() != null) lines++;
            reader.close();
            System.out.println(lines);
            return lines;
        } catch (IOException e) { e.getMessage(); return lines; }
    }
}
