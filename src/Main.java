import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("input/input1A.txt"));

        int sum = 0;

        for (String line : lines) {
            sum += Integer.parseInt(line);
        }

        System.out.println(sum);
    }
}
