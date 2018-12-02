import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {
    public static void main(String args[]) {
        List<Integer> begin = readFile();

        int answer = reduceFrequencies(begin);
        System.out.println(answer);
    }

    public static int reduceFrequencies(List<Integer> frequencies) {
        return frequencies.stream().reduce(0, (x,y) -> x+y);
    }

    public static List<Integer> readFile() {
        String fileName = "Day1Input";
        List<Integer> parsedInput = null;

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            parsedInput = stream.map(Integer::parseInt).collect(Collectors.toList());
            //stream.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return parsedInput;
    }
}