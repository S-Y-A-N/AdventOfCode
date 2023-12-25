import java.util.*;
import java.io.*;
import java.util.regex.*;

public class Day3_1 {
    public static void main(String[] args) {
        Scanner input;

        try {
            input = new Scanner(new File("other_files/input3.txt"));
            Pattern numbers = Pattern.compile("\\d+");
            Pattern symbols = Pattern.compile("[-!@$%^&*()_+|~=`{}\\[\\]:\";'<>?,#/]");

            ArrayList<Integer> numList = new ArrayList<>();
            ArrayList<Integer> digitList = new ArrayList<>();
            ArrayList<Integer> currentSymList = new ArrayList<>();
            ArrayList<Integer> prevSymList = new ArrayList<>();
            ArrayList<Integer> nextSymList = new ArrayList<>();
            ArrayList<Integer> numbersList = new ArrayList<>();
            ArrayList<Integer> toSumList = new ArrayList<>();

            int sum = 0;

            String prevLine = null, nextLine = null, line = null;
            String prevLineCopy = null, nextLineCopy = null, lineCopy = null;

            MainLoop:
            while(input.hasNextLine() || nextLine != null) {

                numList.clear();
                digitList.clear();
                prevSymList.clear();
                currentSymList.clear();
                nextSymList.clear();
                numbersList.clear();

                prevLine = line;

                if(nextLine == null) line = input.nextLine();
                else line = nextLine;

                if(input.hasNextLine()) nextLine = input.nextLine();
                else nextLine = null;

                lineCopy = line;
                prevLineCopy = prevLine;
                nextLineCopy = nextLine;

                Matcher matchNum = numbers.matcher(line);
                Matcher currentMatchSym = symbols.matcher(line);
                Matcher prevMatchSym, nextMatchSym;
                boolean adjC = false, adjP = false, adjN = false;

                while (matchNum.find()) {
                    String num = matchNum.group();
                    int digit = num.length();
                    int matchIndex = lineCopy.indexOf(num);
                    numList.add(matchIndex);
                    digitList.add(digit);
                    numbersList.add(Integer.parseInt(num));
                    lineCopy = lineCopy.replaceFirst(num, repeatChar(digit, "."));
                }

                if(prevLine != null) {

                    prevMatchSym = symbols.matcher(prevLineCopy);

                    while (prevMatchSym.find()) {
                        String symbol = prevMatchSym.group();
                        int matchIndex = prevLineCopy.indexOf(symbol);
                        prevSymList.add(matchIndex);
                        prevLineCopy = prevLineCopy.replaceFirst(fixMetaChar(symbol), ".");
                    }

                }

                if(nextLine != null) {

                    nextMatchSym = symbols.matcher(nextLineCopy);

                    while (nextMatchSym.find()) {
                        String symbol = nextMatchSym.group();
                        int matchIndex = nextLineCopy.indexOf(symbol);
                        nextSymList.add(matchIndex);
                        nextLineCopy = nextLineCopy.replaceFirst(fixMetaChar(symbol), ".");
                    }

                }

                while (currentMatchSym.find()) {
                    String symbol = currentMatchSym.group();
                    int matchIndex = lineCopy.indexOf(symbol);
                    currentSymList.add(matchIndex);
                    lineCopy = lineCopy.replaceFirst(fixMetaChar(symbol), ".");
                }

                for (int i = 0; i < numList.size(); i++) {
                    for (int j = 0; j < prevSymList.size(); j++) {
                        adjP = isAdjacentPrev(digitList.get(i), numList.get(i), prevSymList.get(j));
                        if(adjP) break;
                    }

                    for (int j = 0; j < nextSymList.size(); j++) {
                        adjN = isAdjacentNext(digitList.get(i), numList.get(i), nextSymList.get(j));
                        if(adjN) break;
                    }

                    for (int j = 0; j < currentSymList.size(); j++) {
                        adjC = isAdjacentCurrent(digitList.get(i), numList.get(i), currentSymList.get(j));
                        if(adjC) break;
                    }

                    if(adjC || adjP || adjN) toSumList.add(numbersList.get(i));

                }

            }

            for (int i = 0; i < toSumList.size(); i++)
                sum += toSumList.get(i);

            System.out.println(sum);

        }
        catch(IOException e) { e.getMessage(); }
    }



    /*
       let num index = 8 / +3 or +2 EX (999) or (99)
       prev, next index = FROM num-index minus 1 TO num-index + digit ===> greater than numIndex-1 AND less than numIndex+digit
       current index = num-index minus 1 OR num-index + digit
    */
    public static boolean isAdjacentCurrent(int digit, int numIndex, int currentIndex) {
        boolean currentAdjacent = currentIndex == numIndex-1 || currentIndex == numIndex+digit;
        if(currentAdjacent) return true;
        return false;
    }

    public static boolean isAdjacentPrev(int digit, int numIndex, int prevIndex) {
        boolean prevAdjacent = prevIndex >= numIndex-1 && prevIndex <= numIndex+digit;
        if(prevAdjacent) return true;
        return false;
    }

    public static boolean isAdjacentNext(int digit, int numIndex, int nextIndex) {
        boolean nextAdjacent = nextIndex >= numIndex-1 && nextIndex <= numIndex+digit;
        if(nextAdjacent) return true;
        return false;
    }

    public static String fixMetaChar(String metaChar) {
        switch (metaChar) {
            case "+": return "\\+";
            case "*": return "\\*";
            case "^": return "\\^";
            case "$": return "\\$";
        }
        return metaChar;
    }

    public static String repeatChar(int length, String chara) {
        StringBuffer str = new StringBuffer(length);
        for (int i = 0; i < length; i++)
            str.append(chara);
        return str.toString();
    }
}
