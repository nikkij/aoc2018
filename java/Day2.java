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

public class Day2 {

    int linesWithTwoOfAKind = 0;
    int linesWithThreeOfAKind = 0;

    public static void main(String args[]) {

        System.out.println("Starting ....");
        Day2 day2 = new Day2();

        List<String> begin = day2.readFile();
        begin.stream().forEach(line -> day2.visit(line));

        System.out.println("Final answer:");
        int answer = day2.linesWithTwoOfAKind * day2.linesWithThreeOfAKind;
        System.out.println(answer);
    }

    public void visit(String line) {
        Map<String,Integer> seen = new HashMap<>();

        char lineArr[] = line.toCharArray();
        Arrays.sort(lineArr);
        for(char c : lineArr) {
            System.out.println(c);
            String stringified = Character.toString(c);
            if(seen.get(stringified) == null) {
                seen.put(stringified, 1);
            } else {
                System.out.println("oooo more than one");
                seen.put(stringified, seen.get(stringified) + 1);
            }
        }
        System.out.println(seen);

        if(seen.containsValue(2)) { linesWithTwoOfAKind++; }
        if(seen.containsValue(3)) { linesWithThreeOfAKind++; }
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