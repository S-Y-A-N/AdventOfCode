import java.util.*;
import java.io.*;
import java.util.regex.*;

public class Day3_2 {
    public static void main(String[] args) {
        Scanner input;

        try {
            input = new Scanner(new File("other_files/input3.txt"));

            Pattern numRegex = Pattern.compile("\\d+");
            Pattern gearRegex = Pattern.compile("\\*");


            ArrayList<Integer> gearList = new ArrayList<>();

            ArrayList<Integer> currentDigitList = new ArrayList<>();
            ArrayList<Integer> prevDigitList = new ArrayList<>();
            ArrayList<Integer> nextDigitList = new ArrayList<>();

            ArrayList<Integer> currentNumIndexList = new ArrayList<>();
            ArrayList<Integer> prevNumIndexList = new ArrayList<>();
            ArrayList<Integer> nextNumIndexList = new ArrayList<>();

            ArrayList<Integer> currentNumList = new ArrayList<>();
            ArrayList<Integer> prevNumList = new ArrayList<>();
            ArrayList<Integer> nextNumList = new ArrayList<>();

            ArrayList<Integer> toSumList = new ArrayList<>();

            int sum = 0;
            String prevLine = null, nextLine = null, line = null;
            String prevLineCopy = null, nextLineCopy = null, lineCopy = null;

            MainLoop:
            while(input.hasNextLine() || nextLine != null) {


                gearList.clear();

                currentDigitList.clear();
                currentNumIndexList.clear();
                currentNumList.clear();

                prevDigitList.clear();
                prevNumIndexList.clear();
                prevNumList.clear();

                nextDigitList.clear();
                nextNumIndexList.clear();
                nextNumList.clear();

                prevLine = line;

                if(nextLine == null) line = input.nextLine();
                else line = nextLine;

                if(input.hasNextLine()) nextLine = input.nextLine();
                else nextLine = null;

                lineCopy = line;
                prevLineCopy = prevLine;
                nextLineCopy = nextLine;

                Matcher matchGear = gearRegex.matcher(line);
                Matcher matchCurrentNum = numRegex.matcher(line);
                Matcher matchPrevNum, matchNextNum;

                // match gears in current line
                while (matchGear.find()) {
                    String gear = matchGear.group();
                    int matchIndex = lineCopy.indexOf(gear);
                    gearList.add(matchIndex);
                    lineCopy = lineCopy.replaceFirst(fixMetaChar(), ".");
                }

                // match numbers in (prev, next, current) lines
                if(prevLine != null) {
                    matchPrevNum = numRegex.matcher(prevLineCopy);
                    findNumbers(prevLineCopy, matchPrevNum, prevNumIndexList, prevDigitList, prevNumList);
                }

                if(nextLine != null) {
                    matchNextNum = numRegex.matcher(nextLineCopy);
                    findNumbers(nextLineCopy, matchNextNum, nextNumIndexList, nextDigitList, nextNumList);
                }

                findNumbers(lineCopy, matchCurrentNum, currentNumIndexList, currentDigitList, currentNumList);


                // check adjacency of numbers to gears
                for (int i = 0; i < gearList.size(); i++) {
                    boolean adjC = false, adjP = false, adjN = false;
                    ArrayList<Integer> toMultiply = new ArrayList<>();

                    for (int j = 0; j < prevNumList.size(); j++) {
                        adjP = isAdjacent(gearList.get(i), prevNumIndexList.get(j), prevDigitList.get(j));
                        if(adjP) {
                            toMultiply.add(prevNumList.get(j));
                        }
                    }

                    for (int j = 0; j < nextNumList.size(); j++) {
                        adjN = isAdjacent(gearList.get(i), nextNumIndexList.get(j), nextDigitList.get(j));
//                        System.out.println(gearList.get(i) +" "+ nextNumIndexList.get(j) +" "+ nextDigitList.get(j));
                        if(adjN) {
                            toMultiply.add(nextNumList.get(j));
                        }
                    }

                    for (int j = 0; j < currentNumList.size(); j++) {
                        if(adjP && adjN) break;
                        adjC = isAdjacent(gearList.get(i), currentNumIndexList.get(j), currentDigitList.get(j));
                        if(adjC) {
                            toMultiply.add(currentNumList.get(j));
                        }
                    }

                    if(toMultiply.size() == 2)  {
                        int a = toMultiply.get(0);
                        int b = toMultiply.get(1);
                        toSumList.add(a*b);
                    }

                }

            } // end of MainLoop

            // sum
            for (int i = 0; i < toSumList.size(); i++)
                sum += toSumList.get(i);

            System.out.println(sum);

        }
        catch(IOException e) { e.getMessage(); }
    }


    // Methods
    public static boolean isAdjacent(int gearIndex, int numIndex, int digit) {
        boolean adjacent = (gearIndex <= numIndex+digit) && (gearIndex >= numIndex-1);
        if(adjacent) return true;
        return false;
    }

    public static String fixMetaChar() { return "\\*"; }

    public static String repeatChar(int length, String chara) {
        StringBuffer str = new StringBuffer(length);
        for (int i = 0; i < length; i++)
            str.append(chara);
        return str.toString();
    }

    public static void findNumbers(String line, Matcher matcher, ArrayList<Integer> numIndexList, ArrayList<Integer> digitList, ArrayList<Integer> numList) {
        while (matcher.find()) {
            String num = matcher.group();

            int matchIndex = line.indexOf(num);
            int digit = num.length();

            numIndexList.add(matchIndex);
            digitList.add(digit);
            numList.add(Integer.parseInt(num));

            line = line.replaceFirst(num, repeatChar(digit, "."));
        }
    }
}