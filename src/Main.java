import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Challenge 1A solution: " + solveChallenge1A("input/input1A.txt"));
    }

    public static int solveChallenge1A(String inputFileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFileName));

        int sum = 0;

        for (String line : lines) {
            sum += Integer.parseInt(line);
        }

        return sum;
    }
}
