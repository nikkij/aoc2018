import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.ListIterator;

public class Day2 {

    int linesWithTwoOfAKind = 0;
    int linesWithThreeOfAKind = 0;

    public static void main(String args[]) {

        System.out.println("Starting ....");
        Day2 day2 = new Day2();

        List<String> begin = day2.readFile();
        begin.stream().forEach(line -> day2.visit(line));

        System.out.println("Part 1 Answer:");
        int answer = day2.linesWithTwoOfAKind * day2.linesWithThreeOfAKind;
        System.out.println(answer);

        System.out.println("Part 2 Answer:");
        String same = "";
        String answer2 = "";
        for(String line1 : begin) {
            for(String line2 : begin) {
                same = day2.same(line1, line2);
                if(same.length() == 25) {
                    answer2 = same;
                    break;
                }
            }
            if(answer2 != "") break;
        }
        System.out.println(answer2);
    }

    public void visit(String line) {
        Map<String,Integer> seen = new HashMap<>();

        char lineArr[] = line.toCharArray();
        Arrays.sort(lineArr);
        for(char c : lineArr) {
            String stringified = Character.toString(c);
            if(seen.get(stringified) == null) {
                seen.put(stringified, 1);
            } else {
                seen.put(stringified, seen.get(stringified) + 1);
            }
        }

        if(seen.containsValue(2)) { linesWithTwoOfAKind++; }
        if(seen.containsValue(3)) { linesWithThreeOfAKind++; }
    }

    /* Let's do it with charAt() this time, should be faster than toCharArray() above. */
    public String same(String line1, String line2) {
        String found = "";
        for(int i=0; i < line1.length(); i++) {
            char c = line1.charAt(i);
            char cc = line2.charAt(i);
            if(c == cc) {
                found += c;
            }
        }
        return found;
    }

    public static List<String> readFile() {
        String fileName = "Day2Input";
        List<String> parsedInput = null;

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            parsedInput = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parsedInput;
    }
}