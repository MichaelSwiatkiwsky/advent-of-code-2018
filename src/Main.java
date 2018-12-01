import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Challenge 1A solution: " + solveChallenge1A("input/input1A.txt"));
        System.out.println("Challenge 1B solution: " + solveChallenge1B("input/input1A.txt"));
    }

    public static int solveChallenge1A(String inputFileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFileName));

        int sum = 0;

        for (String line : lines) {
            sum += Integer.parseInt(line);
        }

        return sum;
    }

    public static int solveChallenge1B(String inputFileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFileName));

        Map<Integer,Integer> seen = new HashMap<Integer,Integer>();

        int sum = 0;
        seen.put(sum, seen.getOrDefault(sum, 0) + 1);

        // Is an infinite loop actually possible?
        while (true) {
            for (String line : lines) {
                sum += Integer.parseInt(line);
                if (seen.get(sum) != null) {
                    return sum;
                }
                seen.put(sum, seen.getOrDefault(sum, 0) + 1);
            }
        }
    }
}

