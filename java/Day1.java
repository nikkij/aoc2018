import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {

    List<Integer> seen = new ArrayList<>();
    int found = 0;

    public static void main(String args[]) {
        List<Integer> begin = readFile();
        Day1 day1 = new Day1();

        int answer = day1.reduceFrequencies(begin, 0);

        System.out.println(answer);
    }

    public int reduceFrequencies(List<Integer> frequencies, int initValue) {

        int answer = frequencies.stream().reduce(initValue, (x, y) -> {
            int n = x + y;
            Optional<Integer> foundIt = seen.stream().filter(e -> e == n).findFirst();
            if (foundIt.isPresent()) {
                found = found == 0 ? foundIt.get() : found;
                return found;
            } else {
                seen.add(n);
                return n;
            }
        });
        
        if(found != 0) {
            return found;
        } else {
            return reduceFrequencies(frequencies, answer);
        }
    }

    public static List<Integer> readFile() {
        String fileName = "Day1Input";
        List<Integer> parsedInput = null;

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            parsedInput = stream.map(Integer::parseInt).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parsedInput;
    }
}